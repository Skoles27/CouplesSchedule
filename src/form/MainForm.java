package form;

import util.TableOperation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static util.ComboBoxService.updateComboBoxFaculty;

public class MainForm extends JFrame {
    private JPanel mainPanel;
    private JTable scheduleTable;
    private JButton selectButton;
    protected JButton addScheduleButton;
    private JComboBox facultyComboBox;
    private JComboBox courseComboBox;
    private JComboBox groupComboBox;

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
                TableOperation.displayData(facultyComboBox, courseComboBox, groupComboBox, scheduleTable);
            }
        });

        facultyComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TableOperation.selectFaculty(facultyComboBox, courseComboBox);
            }
        });

        courseComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TableOperation.selectCourse(facultyComboBox, courseComboBox, groupComboBox);
            }
        });
    }
}
