package util;

import entity.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;

import java.util.List;

public class CriteriaQueryList {

    public static List<Faculty> getFacultyCriteriaQueryList() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Faculty> query = builder.createQuery(Faculty.class);
        Root<Faculty> root = query.from(Faculty.class);
        query.select(root);
        List<Faculty> list = session.createQuery(query).getResultList();
        session.close();
        return list;
    }

    public static List<Teacher> getTeacherCriteriaQueryList() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Teacher> query = builder.createQuery(Teacher.class);
        Root<Teacher> root = query.from(Teacher.class);
        query.select(root);
        List<Teacher> list = session.createQuery(query).getResultList();
        session.close();
        return list;
    }

    public static List<Discipline> getDisciplineCriteriaQueryList() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Discipline> query = builder.createQuery(Discipline.class);
        Root<Discipline> root = query.from(Discipline.class);
        query.select(root);
        List<Discipline> list = session.createQuery(query).getResultList();
        session.close();
        return list;
    }

    public static List<Classroom> getClassroomCriteriaQueryList() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Classroom> query = builder.createQuery(Classroom.class);
        Root<Classroom> root = query.from(Classroom.class);
        query.select(root);
        List<Classroom> list = session.createQuery(query).getResultList();
        session.close();
        return list;
    }

    public static List<StudentGroup> getGroupCriteriaQueryList() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<StudentGroup> query = builder.createQuery(StudentGroup.class);
        Root<StudentGroup> root = query.from(StudentGroup.class);
        query.select(root);
        List<StudentGroup> list = session.createQuery(query).getResultList();
        session.close();
        return list;
    }
}
