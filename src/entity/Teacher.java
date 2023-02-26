package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Teacher")
public class Teacher {
    @Id
    private String fio;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "discipline")
    private Discipline discipline;

    public Teacher(){}

    public Teacher(String fio, Discipline discipline) {
        this.fio = fio;
        this.discipline = discipline;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }
}
