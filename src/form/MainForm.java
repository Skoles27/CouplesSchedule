package form;

import entity.*;
import org.hibernate.Session;
import util.HibernateUtil;
import util.LectureComparator;
import util.TableView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

import static util.ComboBoxService.updateComboBoxFaculty;

public class MainForm extends JFrame {
    private JPanel mainPanel;
    private JTable scheduleTable;
    private JButton selectButton;
    protected JButton addScheduleButton;
    private JComboBox facultyComboBox;
    private JComboBox courseComboBox;
    private JComboBox groupComboBox;
    private String facultyCB;


    public static void main(String[] args) {
        JFrame mainFrame = new JFrame();
        MainForm mainForm = new MainForm();
        mainFrame.setSize(1130, 500);
        mainFrame.add(mainForm.mainPanel);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);

        JFrame secondFrame = new JFrame();
        SecondForm secondForm = new SecondForm();
        secondFrame.setSize(1130, 500);
        secondFrame.add(secondForm.mainPanel);
        secondFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        secondFrame.setLocationRelativeTo(null);
        secondFrame.setVisible(false);

        JFrame lectionFrame = new JFrame();
        LectionForm lectionForm = new LectionForm();
        lectionFrame.setSize(1130, 500);
        lectionFrame.add(lectionForm.mainPanel);
        lectionFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        lectionFrame.setLocationRelativeTo(null);
        lectionFrame.setVisible(false);


        mainForm.addScheduleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setVisible(false);
                secondFrame.setVisible(true);
            }
        });

        secondForm.backToScheduleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                secondFrame.setVisible(false);
                mainFrame.setVisible(true);
            }
        });

        secondForm.addAndRemoveLection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                secondFrame.setVisible(false);
                lectionFrame.setVisible(true);
            }
        });

        lectionForm.backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                secondFrame.setVisible(true);
                lectionFrame.setVisible(false);
            }
        });
    }

    public MainForm() {
        updateComboBoxFaculty(facultyComboBox);

        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String faculty = (String)facultyComboBox.getSelectedItem();
                Integer course = (Integer)courseComboBox.getSelectedItem();
                String group = (String)groupComboBox.getSelectedItem();

                String hql = "FROM Lecture JOIN StudentGroup ON studentGroup = groupName " +
                        "WHERE faculty = " + "'" + faculty + "'" + " AND " + "course = " + "'" + course + "'" +
                        " AND " + "studentGroup = " + "'" + group + "'";

                Session session = HibernateUtil.getSessionFactory().openSession();
                List<Lecture> lectures = session.createQuery(hql).getResultList();
                Comparator<entity.Lecture> comparator = new LectureComparator();
                lectures.sort(comparator);
                TableView tableView = new TableView(lectures);
                scheduleTable.setModel(tableView);
                session.close();
            }
        });

        facultyComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                facultyCB = (String)facultyComboBox.getSelectedItem();
                String hqlComboBoxes = "from StudentGroup where faculty = " + "'" + facultyCB + "'";
                Session session = HibernateUtil.getSessionFactory().openSession();
                List<StudentGroup> groups = session.createQuery(hqlComboBoxes).getResultList();

                courseComboBox.removeAllItems();
                Set<Integer> course = new LinkedHashSet<>();
                for (StudentGroup s : groups) {
                    course.add(s.getCourse());
                }
                for (Integer i : course) {
                    courseComboBox.addItem(i);
                }
                session.close();
            }
        });

        courseComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                groupComboBox.removeAllItems();
                Integer courseCB = (Integer)courseComboBox.getSelectedItem();
                String hqlComboBoxes = "from StudentGroup where faculty = " + "'" + facultyCB + "'" + " and "
                        + "course = " + "'" + courseCB + "'";

                Session session = HibernateUtil.getSessionFactory().openSession();
                List<StudentGroup> groups = session.createQuery(hqlComboBoxes).getResultList();
                for (StudentGroup s : groups) {
                    groupComboBox.addItem(s.getGroupName());
                }
                session.close();
            }
        });
    }
}
