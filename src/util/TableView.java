package util;

import entity.Lecture;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TableView extends AbstractTableModel {
    List<Lecture> lectures;
    public TableView(List<Lecture> lectures) {
        this.lectures = lectures;
    }
    @Override
    public String getColumnName (int col){
        String colName="";
        if (col==0) colName = "День";
        if (col==1) colName = "Номер пары";
        if (col==2) colName = "Время начало";
        if (col==3) colName = "Аудитория";
        if (col==4) colName = "Дисциплина";
        if (col==5) colName = "Преподаватель";
        return colName;
    }
    @Override
    public int getRowCount() { return lectures.size(); }
    @Override
    public int getColumnCount() { return 6; }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Lecture l = lectures.get(rowIndex);
        Object toReturn = null;
        switch (columnIndex) {
            case 0:
                toReturn = l.getDayOfTheWeek();
                break;
            case 1:
                toReturn = l.getLectureNumber();
                break;
            case 2:
                toReturn = l.getTimeStart();
                break;
            case 3:
                toReturn = l.getClassroom().getClassroomName();
                break;
            case 4:
                toReturn = l.getDiscipline().getDisciplineName();
                break;
            case 5:
                toReturn = l.getTeacher().getFio();
                break;
            default:
                toReturn = l.getIdlecture();
        }
        return toReturn;
    }
}
