package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Lecture")
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idlecture;

    private int lectureNumber;

    private String dayOfTheWeek;

    private String timeStart;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "studentGroup")
    private StudentGroup studentGroup;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher")
    private Teacher teacher;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "discipline")
    private Discipline discipline;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "classroom")
    private Classroom classroom;

    public Lecture() {
    }

    public Lecture(StudentGroup studentGroup, String dayOfTheWeek, int lectureNumber, String timeStart,
                   Classroom classroom, Discipline discipline, Teacher teacher) {
        this.studentGroup = studentGroup;
        this.dayOfTheWeek = dayOfTheWeek;
        this.lectureNumber = lectureNumber;
        this.timeStart = timeStart;
        this.classroom = classroom;
        this.discipline = discipline;
        this.teacher = teacher;
    }

    public int getIdlecture() {
        return idlecture;
    }

    public void setIdlecture(int idlecture) {
        this.idlecture = idlecture;
    }

    public int getLectureNumber() {
        return lectureNumber;
    }

    public void setLectureNumber(int lectureNumber) {
        this.lectureNumber = lectureNumber;
    }

    public String getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    public void setDayOfTheWeek(String dayOfTheWeek) {
        this.dayOfTheWeek = dayOfTheWeek;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public StudentGroup getStudentGroup() {
        return studentGroup;
    }

    public void setStudentGroup(StudentGroup studentGroup) {
        this.studentGroup = studentGroup;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

}
