package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "StudentGroup")
public class StudentGroup {
    @Id
    private String groupName;

    private int course;

    private int semester;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "faculty")
    private Faculty faculty;

    private String specialty;

    private int kolStudent;

    public StudentGroup(){}

    public StudentGroup(String groupName, int course, int semester, Faculty faculty, String specialty, int kolStudent) {
        this.groupName = groupName;
        this.course = course;
        this.semester = semester;
        this.faculty = faculty;
        this.specialty = specialty;
        this.kolStudent = kolStudent;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public int getKolStudent() {
        return kolStudent;
    }

    public void setKolStudent(int kolStudent) {
        this.kolStudent = kolStudent;
    }
}
