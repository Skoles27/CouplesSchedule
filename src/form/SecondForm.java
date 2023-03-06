package form;

import entity.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static util.ComboBoxService.*;
import static util.CriteriaQueryList.*;

public class SecondForm extends JFrame {
    protected JPanel mainPanel;
    protected JButton backToScheduleButton;
    protected JButton addAndRemoveLection;
    private JComboBox facultyComboBox;
    private JButton addFacultyButton;
    private JButton removeFacultyButton;
    private JComboBox disciplineComboBox;
    private JButton addDisciplineButton;
    private JButton removeDisciplineButton;
    private JComboBox teacherComboBox;
    private JComboBox cbDiscipline;
    private JButton addDisciplineAndTeacherButton;
    private JButton removeDisciplineAndTeacherButton;
    private JComboBox classroomComboBox;
    private JTextField tfUniversityBuilding;
    private JTextField tfNumberOfSeats;
    private JButton addClassroomButton;
    private JButton removeClassroomButton;
    private JComboBox groupComboBox;
    private JComboBox cbCourse;
    private JComboBox cbSemestr;
    private JComboBox cbGroupFaculty;
    private JTextField tfSpecialty;
    private JTextField tfKolStudent;
    private JButton addGroupButton;
    private JButton removeGroupButton;
    private List<StudentGroup> groups = new ArrayList<>();

    public SecondForm() {
        updateComboBoxFaculty(facultyComboBox);
        updateComboBoxDiscipline(disciplineComboBox);
        updateComboBoxDiscipline(cbDiscipline);
        updateComboBoxTeacher(cbDiscipline, teacherComboBox);
        updateComboBoxClassroom(classroomComboBox, tfUniversityBuilding, tfNumberOfSeats);
        updateComboBoxFaculty(cbGroupFaculty);
        initialCourseAndSemestrComboBox(cbCourse, cbSemestr);

        addFacultyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Session session = HibernateUtil.getSessionFactory().openSession();
                    Transaction transaction = session.beginTransaction();

                    String comboBoxFaculty = facultyComboBox.getSelectedItem().toString();
                    Faculty faculty = new Faculty(comboBoxFaculty);
                    session.saveOrUpdate(faculty);

                    transaction.commit();
                    updateComboBoxFaculty(facultyComboBox);
                    updateComboBoxFaculty(cbGroupFaculty);
                    session.close();
                } catch (Exception ex) {
                    System.err.println("Неправильный формат ввода\n" + ex.getMessage());
                }
            }
        });

        removeFacultyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Session session = HibernateUtil.getSessionFactory().openSession();
                    Transaction transaction = session.beginTransaction();

                    String comboBoxFaculty = facultyComboBox.getSelectedItem().toString();
                    Faculty faculty = new Faculty(comboBoxFaculty);
                    session.delete(faculty);

                    transaction.commit();
                    updateComboBoxFaculty(facultyComboBox);
                    updateComboBoxFaculty(cbGroupFaculty);
                    session.close();
                } catch (Exception ex) {
                    System.err.println("Нет элементов для удаления\n" + ex.getMessage());
                }
            }
        });

        addDisciplineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Session session = HibernateUtil.getSessionFactory().openSession();
                    Transaction transaction = session.beginTransaction();

                    String comboBoxDiscipline = disciplineComboBox.getSelectedItem().toString();
                    Discipline discipline = new Discipline(comboBoxDiscipline);
                    session.saveOrUpdate(discipline);

                    transaction.commit();
                    updateComboBoxDiscipline(disciplineComboBox);
                    updateComboBoxDiscipline(cbDiscipline);
                    session.close();
                } catch (Exception ex) {
                    System.err.println("Неправильный формат ввода\n" + ex.getMessage());
                }
            }
        });

        removeDisciplineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Session session = HibernateUtil.getSessionFactory().openSession();
                    Transaction transaction = session.beginTransaction();

                    String comboBoxDiscipline = disciplineComboBox.getSelectedItem().toString();
                    Discipline discipline = new Discipline(comboBoxDiscipline);
                    session.delete(discipline);

                    transaction.commit();
                    updateComboBoxDiscipline(disciplineComboBox);
                    updateComboBoxDiscipline(cbDiscipline);
                    session.close();
                } catch (Exception ex) {
                    System.err.println("Нет элементов для удаления\n" + ex.getMessage());
                }
            }
        });

        addDisciplineAndTeacherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Session session = HibernateUtil.getSessionFactory().openSession();
                    Transaction transaction = session.beginTransaction();

                    String comboBoxTeacher = teacherComboBox.getSelectedItem().toString();
                    Discipline discipline = session.get(Discipline.class, cbDiscipline.getSelectedItem());
                    Teacher teacher = new Teacher(comboBoxTeacher, discipline);
                    session.saveOrUpdate(teacher);

                    transaction.commit();
                    updateComboBoxTeacher(cbDiscipline, teacherComboBox);
                    session.close();
                } catch (Exception ex) {
                    System.err.println("Неправильный формат ввода\n" + ex.getMessage());
                }
            }
        });

        removeDisciplineAndTeacherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Session session = HibernateUtil.getSessionFactory().openSession();
                    Transaction transaction = session.beginTransaction();

                    String comboBoxTeacher = teacherComboBox.getSelectedItem().toString();
                    Discipline discipline = session.get(Discipline.class, cbDiscipline.getSelectedItem());
                    Teacher teacher = new Teacher(comboBoxTeacher, discipline);
                    session.delete(teacher);

                    transaction.commit();
                    updateComboBoxTeacher(cbDiscipline, teacherComboBox);
                    session.close();
                } catch (Exception ex) {
                    System.err.println("Нет элементов для удаления\n" + ex.getMessage());
                }
            }
        });

        cbDiscipline.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateComboBoxTeacher(cbDiscipline, teacherComboBox);
            }
        });

        addClassroomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Session session = HibernateUtil.getSessionFactory().openSession();
                    Transaction transaction = session.beginTransaction();

                    String comboBoxClassroom = classroomComboBox.getSelectedItem().toString();
                    String universityBuilding = tfUniversityBuilding.getText();
                    int numberOfSeats = Integer.parseInt(tfNumberOfSeats.getText());

                    Classroom classroom = new Classroom(comboBoxClassroom, universityBuilding, numberOfSeats);
                    session.saveOrUpdate(classroom);

                    transaction.commit();
                    updateComboBoxClassroom(classroomComboBox, tfUniversityBuilding, tfNumberOfSeats);
                    session.close();
                } catch (Exception ex) {
                    System.err.println("Неправильный формат ввода\n" + ex.getMessage());
                }
            }
        });

        removeClassroomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Session session = HibernateUtil.getSessionFactory().openSession();
                    Transaction transaction = session.beginTransaction();

                    String comboBoxClassroom = classroomComboBox.getSelectedItem().toString();
                    String universityBuilding = tfUniversityBuilding.getText();
                    int numberOfSeats = Integer.parseInt(tfNumberOfSeats.getText());

                    Classroom classroom = new Classroom(comboBoxClassroom, universityBuilding, numberOfSeats);
                    session.delete(classroom);

                    transaction.commit();
                    updateComboBoxClassroom(classroomComboBox, tfUniversityBuilding, tfNumberOfSeats);
                    session.close();
                } catch (Exception ex) {
                    System.err.println("Нет элементов для удаления\n" + ex.getMessage());
                }
            }
        });

        classroomComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Classroom> list = getClassroomCriteriaQueryList();
                ArrayList<String> classroomName = new ArrayList<>();
                for(Classroom c : list) {
                    classroomName.add(c.getClassroomName());
                }
                int index = classroomName.indexOf(classroomComboBox.getSelectedItem());
                if (index == -1) {
                    tfUniversityBuilding.setText("");
                    tfNumberOfSeats.setText("");
                } else {
                    tfUniversityBuilding.setText(list.get(index).getUniversityBuilding());
                    tfNumberOfSeats.setText(String.valueOf(list.get(index).getNumberOfSeats()));
                }
                classroomName.clear();
            }
        });

        addGroupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Session session = HibernateUtil.getSessionFactory().openSession();
                    Transaction transaction = session.beginTransaction();

                    String comboBoxStudentGroup = groupComboBox.getSelectedItem().toString();
                    int comboBoxCourse = Integer.parseInt(cbCourse.getSelectedItem().toString());
                    int comboBoxSemester = Integer.parseInt(cbSemestr.getSelectedItem().toString());
                    Faculty comboBoxFaculty = session.get(Faculty.class, cbGroupFaculty.getSelectedItem());
                    String specialty = tfSpecialty.getText();
                    int kolStudent = Integer.parseInt(tfKolStudent.getText());

                    StudentGroup studentGroup = new StudentGroup(comboBoxStudentGroup, comboBoxCourse, comboBoxSemester,
                            comboBoxFaculty, specialty, kolStudent);
                    session.saveOrUpdate(studentGroup);

                    transaction.commit();
                    groups = updateComboBoxGroup(groupComboBox, cbGroupFaculty, tfSpecialty, tfKolStudent);
                    session.close();
                } catch (Exception ex) {
                    System.err.println("Неправильный формат ввода\n" + ex.getMessage());
                }
            }
        });

        removeGroupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Session session = HibernateUtil.getSessionFactory().openSession();
                    Transaction transaction = session.beginTransaction();

                    String comboBoxStudentGroup = groupComboBox.getSelectedItem().toString();
                    int comboBoxCourse = Integer.parseInt(cbCourse.getSelectedItem().toString());
                    int comboBoxSemester = Integer.parseInt(cbSemestr.getSelectedItem().toString());
                    Faculty comboBoxFaculty = session.get(Faculty.class, cbGroupFaculty.getSelectedItem());
                    String specialty = tfSpecialty.getText();
                    int kolStudent = Integer.parseInt(tfKolStudent.getText());

                    StudentGroup studentGroup = new StudentGroup(comboBoxStudentGroup, comboBoxCourse, comboBoxSemester,
                            comboBoxFaculty, specialty, kolStudent);
                    session.delete(studentGroup);

                    transaction.commit();
                    groups = updateComboBoxGroup(groupComboBox, cbGroupFaculty, tfSpecialty, tfKolStudent);
                    session.close();
                } catch (Exception ex) {
                    System.err.println("Нет элементов для удаления\n" + ex.getMessage());
                }
            }
        });

        cbGroupFaculty.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                groups = updateComboBoxGroup(groupComboBox, cbGroupFaculty, tfSpecialty, tfKolStudent);
            }
        });

        groupComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> groupName = new ArrayList<>();
                for (StudentGroup s : groups) {
                    groupName.add(s.getGroupName());
                }
                int index = groupName.indexOf(groupComboBox.getSelectedItem());
                if (index == -1) {
                    tfUniversityBuilding.setText("");
                    tfNumberOfSeats.setText("");
                } else {
                    cbCourse.setSelectedItem(groups.get(index).getCourse());
                    cbSemestr.setSelectedItem(groups.get(index).getSemester());
                    tfSpecialty.setText(groups.get(index).getSpecialty());
                    tfKolStudent.setText(String.valueOf(groups.get(index).getKolStudent()));
                }
            }
        });
    }
}
