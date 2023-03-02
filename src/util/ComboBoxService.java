package util;

import entity.*;
import org.hibernate.Session;

import javax.swing.*;

import java.util.List;

import static util.CriteriaQueryList.*;

public class ComboBoxService {

    public static void updateComboBoxFaculty(JComboBox comboBox) {
        comboBox.removeAllItems();
        for (Faculty f : getFacultyCriteriaQueryList()) {
            comboBox.addItem(f.getFaculty());
        }
    }

    public static void updateComboBoxDiscipline(JComboBox comboBox) {
        comboBox.removeAllItems();
        for (Discipline d : getDisciplineCriteriaQueryList()) {
            comboBox.addItem(d.getDisciplineName());
        }
    }

    public static void updateComboBoxTeacher(JComboBox comboBox) {
        comboBox.removeAllItems();
        for (Teacher t : getTeacherCriteriaQueryList()) {
            comboBox.addItem(t.getFio());
        }
    }

    public static void updateComboBoxTeacher(JComboBox disciplineCB, JComboBox updateComboBox) {
        updateComboBox.removeAllItems();
        String discipline = (String) disciplineCB.getSelectedItem();
        String hql = "FROM Teacher WHERE discipline = '" + discipline + "'";
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Teacher> list = session.createQuery(hql).getResultList();
        for (Teacher t : list) {
            updateComboBox.addItem(t.getFio());
        }
        session.close();
    }

    public static void updateComboBoxClassroom(JComboBox classroomCB, JTextField universityBuilding, JTextField numberOfSeats) {
        classroomCB.removeAllItems();
        universityBuilding.removeAll();
        numberOfSeats.removeAll();
        for (Classroom c : getClassroomCriteriaQueryList()) {
            classroomCB.addItem(c.getClassroomName());
        }
    }

    public static List<StudentGroup> updateComboBoxGroup(JComboBox groupCB, JComboBox groupFacultyCB,
                                           JTextField specialty, JTextField kolStudent) {
        groupCB.removeAllItems();
        specialty.setText("");
        kolStudent.setText("");
        String facultyCB = (String)groupFacultyCB.getSelectedItem();
        String hqlComboBoxes = "from StudentGroup where faculty = '" + facultyCB + "'";
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<StudentGroup> groupList = session.createQuery(hqlComboBoxes).getResultList();
        for (StudentGroup s : groupList) {
            groupCB.addItem(s.getGroupName());
        }
        session.close();
        return groupList;
    }

    public static void initialCourseAndSemestrComboBox(JComboBox course, JComboBox semestr) {
        initialNumComboBox(course);
        semestr.addItem(1);
        semestr.addItem(2);
    }

    public static void initialNumComboBox(JComboBox num) {
        for (int i = 1; i < 6; i++) {
            num.addItem(i);
        }
    }

    public static void initialDayComboBox(JComboBox day) {
        day.addItem("Понедельник");
        day.addItem("Вторник");
        day.addItem("Среда");
        day.addItem("Четверг");
        day.addItem("Пятница");
    }
}
