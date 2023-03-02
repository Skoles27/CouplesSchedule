package form;

import entity.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static util.ComboBoxService.*;

public class LectionForm {
    protected JPanel mainPanel;
    protected JButton backButton;
    private JComboBox facultyComboBox;
    private JComboBox courseComboBox;
    private JComboBox groupComboBox;
    private JComboBox dayComboBox;
    private JComboBox lectionNumComboBox;
    private JComboBox classroomComboBox;
    private JComboBox disciplineComboBox;
    private JComboBox teacherComboBox;
    private JComboBox delFacultyComboBox;
    private JComboBox delCourseComboBox;
    private JComboBox delGroupComboBox;
    private JButton addButton;
    private JButton selectButton;
    private JButton removeButton;
    private JTable table;
    private JLabel infoLabel;
    private String facultyCB;

    public LectionForm() {
        updateComboBoxFaculty(facultyComboBox);
        updateComboBoxDiscipline(disciplineComboBox);
        initialDayComboBox(dayComboBox);
        initialNumComboBox(lectionNumComboBox);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Session session = HibernateUtil.getSessionFactory().openSession();
                Transaction transaction = session.beginTransaction();

                StudentGroup group = session.get(StudentGroup.class, groupComboBox.getSelectedItem());
                Classroom classroom = session.get(Classroom.class, classroomComboBox.getSelectedItem());
                String day = dayComboBox.getSelectedItem().toString();
                int lNum = Integer.parseInt(lectionNumComboBox.getSelectedItem().toString());
                Discipline discipline = session.get(Discipline.class, disciplineComboBox.getSelectedItem());
                Teacher teacher = session.get(Teacher.class, teacherComboBox.getSelectedItem());

                String timeStart;
                if (lNum == 1) {
                    timeStart = "8:30";
                } else if (lNum == 2) {
                    timeStart = "10:00";
                } else if (lNum == 3) {
                    timeStart = "11:40";
                } else if (lNum == 4) {
                    timeStart = "13:10";
                } else {
                    timeStart = "14:40";
                }

                boolean exists = session.createQuery("from Lecture where dayOfTheWeek = '" + day +
                        "' AND lectureNumber = " + lNum).setMaxResults(1).uniqueResult() != null;

                if (exists) {
                    infoLabel.setText("Ошибка: Лекция в этот день и номер пары уже существует!");
                    Color color = new Color(0xB20700);
                    infoLabel.setForeground(color);
                    infoLabel.setVisible(true);
                } else {
                    Lecture lecture = new Lecture(group, day, lNum, timeStart, classroom, discipline, teacher);
                    session.saveOrUpdate(lecture);
                    transaction.commit();
                    infoLabel.setText("Лекция успешно добавлена!");
                    Color color = new Color(0x009802);
                    infoLabel.setForeground(color);
                    infoLabel.setVisible(true);
                }
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
                infoLabel.setVisible(false);
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

        lectionNumComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                classroomComboBox.removeAllItems();
                String day = dayComboBox.getSelectedItem().toString();
                Integer lNum = (Integer) lectionNumComboBox.getSelectedItem();
                String hql = "FROM Classroom WHERE classroomName NOT IN (SELECT classroom " +
                        "FROM Lecture WHERE dayOfTheWeek = '" + day + "' AND lectureNumber = " + lNum + ")";

                Session session = HibernateUtil.getSessionFactory().openSession();
                List<Classroom> freeClassroom = session.createQuery(hql).getResultList();
                for (Classroom c : freeClassroom) {
                    classroomComboBox.addItem(c.getClassroomName());
                }
                session.close();
            }
        });

        disciplineComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateComboBoxTeacher(disciplineComboBox, teacherComboBox);
            }
        });
    }
}
