package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Classroom")
public class Classroom {
    @Id
    private String classroomName;

    private String universityBuilding;

    private int numberOfSeats;

    public Classroom() {}

    public Classroom(String classroomName, String universityBuilding, int numberOfSeats) {
        this.classroomName = classroomName;
        this.universityBuilding = universityBuilding;
        this.numberOfSeats = numberOfSeats;
    }

    public String getClassroomName() {
        return classroomName;
    }

    public void setClassroomName(String classroomName) {
        this.classroomName = classroomName;
    }

    public String getUniversityBuilding() {
        return universityBuilding;
    }

    public void setUniversityBuilding(String universityBuilding) {
        this.universityBuilding = universityBuilding;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }
}
