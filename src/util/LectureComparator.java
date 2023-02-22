package util;

import entity.Lecture;

import java.util.Comparator;

public class LectureComparator implements Comparator<Lecture> {
    @Override
    public int compare(Lecture o1, Lecture o2) {
        if (o1.getDayOfTheWeek().equals("Понедельник") && o2.getDayOfTheWeek().equals("Понедельник")) {
            return 0;
        } else if (o1.getDayOfTheWeek().equals("Понедельник")) {
            return -1;
        }

        if (o1.getDayOfTheWeek().equals("Вторник") && (o2.getDayOfTheWeek().equals("Среда") ||
                o2.getDayOfTheWeek().equals("Четверг") || o2.getDayOfTheWeek().equals("Пятница"))) {
            return -1;
        } else if (o1.getDayOfTheWeek().equals("Вторник") && o2.getDayOfTheWeek().equals("Вторник")) {
            return 0;
        } else if (o1.getDayOfTheWeek().equals("Вторник") && o2.getDayOfTheWeek().equals("Понедельник")) {
            return 1;
        }

        if (o1.getDayOfTheWeek().equals("Среда") && (o2.getDayOfTheWeek().equals("Четверг") ||
                o2.getDayOfTheWeek().equals("Пятница"))) {
            return -1;
        } else if (o1.getDayOfTheWeek().equals("Среда") && o2.getDayOfTheWeek().equals("Среда")) {
            return 0;
        } else if (o1.getDayOfTheWeek().equals("Среда") && (o2.getDayOfTheWeek().equals("Понедельник")) ||
                o2.getDayOfTheWeek().equals("Вторник")) {
            return 1;
        }

        if (o1.getDayOfTheWeek().equals("Четверг") && o2.getDayOfTheWeek().equals("Пятница")) {
            return -1;
        } else if (o1.getDayOfTheWeek().equals("Четверг") && o2.getDayOfTheWeek().equals("Четверг")) {
            return 0;
        } else if (o1.getDayOfTheWeek().equals("Четверг") && (o2.getDayOfTheWeek().equals("Среда") ||
                o2.getDayOfTheWeek().equals("Вторник") || o2.getDayOfTheWeek().equals("Понедельник"))) {
            return 1;
        }

        if (o1.getDayOfTheWeek().equals("Пятница") && o2.getDayOfTheWeek().equals("Пятница")) {
            return 0;
        } else if (o1.getDayOfTheWeek().equals("Пятница")) {
            return 1;
        }

        return 0;
    }
}
