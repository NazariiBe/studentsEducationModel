import studetnsEducationModel.ActivityBuilder;
import studetnsEducationModel.DevelopmentPlan;
import studetnsEducationModel.DevelopmentPlanBuilder;
import studetnsEducationModel.activity.Activity;
import studetnsEducationModel.activity.knowledgeSource.Meetup;
import studetnsEducationModel.activity.knowledgeSource.Student;
import studetnsEducationModel.activity.schedule.DayOfWeekSchedule;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set<DayOfWeek> workdays = new HashSet<>();
        workdays.add(DayOfWeek.MONDAY);
        Activity activity = new ActivityBuilder()
                .source(new Meetup())
                .schedule(new DayOfWeekSchedule(workdays))
                .build();

        DevelopmentPlan plan = new DevelopmentPlanBuilder()
                .add(activity)
                .build();

        List<Student> students = new ArrayList<>();

        students.add(new Student(1, true));

        runWeek(students, plan, LocalDate.now());
        log(students);
    }

    private static void log(List<Student> students) {
        for (Student student: students) {
            System.out.println(student.getKnowledges().size());
            System.out.println(student.getKnowledges().size());
        }
    }

    private static LocalDate runWeek(List<Student> students, DevelopmentPlan plan, LocalDate start) {
        LocalDate date = start;
        for (int i = 0; i < 7; i++) {
            for (Student student: students) plan.perform(student, date);
            date = date.plusDays(1);
        }
        return date;
    }
}
