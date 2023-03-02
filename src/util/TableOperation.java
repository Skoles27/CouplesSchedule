package util;

import entity.Lecture;
import entity.StudentGroup;
import org.hibernate.Session;

import javax.swing.*;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class TableOperation {

    public static void displayData(JComboBox facultyCB, JComboBox courseCB, JComboBox groupCB, JTable table) {
        String faculty = (String)facultyCB.getSelectedItem();
        Integer course = (Integer)courseCB.getSelectedItem();
        String group = (String)groupCB.getSelectedItem();

        String hql = "FROM Lecture JOIN StudentGroup ON studentGroup = groupName " +
                "WHERE faculty = " + "'" + faculty + "'" + " AND " + "course = " + "'" + course + "'" +
                " AND " + "studentGroup = " + "'" + group + "'";

        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Lecture> lectures = session.createQuery(hql).getResultList();
        Comparator<Lecture> comparator = new LectureComparator();
        lectures.sort(comparator);
        TableView tableView = new TableView(lectures);
        table.setModel(tableView);
        session.close();
    }

    public static void selectFaculty(JComboBox facultyCB, JComboBox courseCB) {
        String faculty = (String)facultyCB.getSelectedItem();
        String hql = "from StudentGroup where faculty = " + "'" + faculty + "'";
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<StudentGroup> groups = session.createQuery(hql).getResultList();

        courseCB.removeAllItems();
        Set<Integer> course = new LinkedHashSet<>();
        for (StudentGroup s : groups) {
            course.add(s.getCourse());
        }
        for (Integer i : course) {
            courseCB.addItem(i);
        }
        session.close();
    }

    public static void selectCourse(JComboBox facultyCB, JComboBox courseCB, JComboBox groupCB) {
        groupCB.removeAllItems();
        String faculty = (String)facultyCB.getSelectedItem();
        Integer course = (Integer)courseCB.getSelectedItem();
        String hql = "from StudentGroup where faculty = " + "'" + faculty + "'" + " and "
                + "course = " + "'" + course + "'";

        Session session = HibernateUtil.getSessionFactory().openSession();
        List<StudentGroup> groups = session.createQuery(hql).getResultList();
        for (StudentGroup s : groups) {
            groupCB.addItem(s.getGroupName());
        }
        session.close();
    }
}
