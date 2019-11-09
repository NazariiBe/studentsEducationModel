import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import studetnsEducationModel.activity.ActivityBuilder;
import studetnsEducationModel.DevelopmentPlan;
import studetnsEducationModel.DevelopmentPlanBuilder;
import studetnsEducationModel.activity.Activity;
import studetnsEducationModel.activity.knowledgeSource.*;
import studetnsEducationModel.activity.schedule.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class studentsEducationModel {
    private Set<DayOfWeek> workdays;
    private Set<Month> summer;

    @BeforeEach
    void startUp() {
        workdays = new HashSet<>();
        workdays.add(DayOfWeek.MONDAY);
        workdays.add(DayOfWeek.TUESDAY);
        workdays.add(DayOfWeek.WEDNESDAY);
        workdays.add(DayOfWeek.THURSDAY);
        workdays.add(DayOfWeek.FRIDAY);

        summer = new HashSet<>();
        summer.add(Month.JUNE);
        summer.add(Month.JULY);
        summer.add(Month.AUGUST);
    }

    private int countDays(Schedule schedule, LocalDate start, LocalDate end) {
        LocalDate date = start;
        long totalDays = ChronoUnit.DAYS.between(start, end);
        int count = 0;
        for (int i = 0; i < totalDays; i++) {
            if (schedule.isCovers(date)) count++;
            date = date.plusDays(1);
        }
        return count;
    }

    @Test
    void schedule__coverOneDayOfWeek() {
        Set<DayOfWeek> onedayset = new HashSet<>();
        onedayset.add(DayOfWeek.MONDAY);
        Schedule schedule = new DayOfWeekSchedule(onedayset);
        LocalDate start = LocalDate.of(2019, 11, 3);
        LocalDate end = start.plusDays(6);
        assertThat(countDays(schedule, start, end), is(1));
    }

    @Test
    void schedule__coverWorkWeek() {
        Schedule schedule = new DayOfWeekSchedule(workdays);
        LocalDate start = LocalDate.of(2019, 11, 3);
        LocalDate end = start.plusDays(6);
        assertThat(countDays(schedule, start, end), is(5));
    }

    @Test
    void schedule__coverSummer() {
        Schedule schedule = new MonthSchedule(summer);
        LocalDate date = LocalDate.now();
        int count = 0;
        for (int i = 0; i < 12; i++) {
            if (schedule.isCovers(date)) {
              count++;
            }
            date = date.plusMonths(1);
        }
        assertThat(count, is(3));
    }

    @Test
    void schedule__coverPeriod() {
        LocalDate start = LocalDate.of(2019, 2, 1);
        LocalDate end = LocalDate.of(2019, 2, 12);
        Schedule schedule = new PeriodSchedule(start, end);
        assertThat(countDays(schedule, start.minusMonths(1), end.plusYears(1)), is(10));
    }

    @Test
    void comlexSchedule__and() {
        Set<DayOfWeek> sunday = new HashSet<>();
        sunday.add(DayOfWeek.SUNDAY);

        Set<Month> november = new HashSet<>();
        november.add(Month.NOVEMBER);

        Schedule sundaySchedule = new DayOfWeekSchedule(sunday);
        Schedule novemberSchedule = new MonthSchedule(november);

        Schedule complex = ComplexSchedule.and(sundaySchedule, novemberSchedule);

        LocalDate start = LocalDate.of(2019, 11, 3);
        LocalDate end = start.plusWeeks(1);
        assertThat(countDays(complex, start, end), is(1));
    }

    @Test
    void comlexSchedule__or() {
        Set<DayOfWeek> sunday = new HashSet<>();
        sunday.add(DayOfWeek.SUNDAY);

        Set<DayOfWeek> monday = new HashSet<>();
        monday.add(DayOfWeek.MONDAY);

        Schedule sundayScheule = new DayOfWeekSchedule(sunday);
        Schedule mondayScheule = new DayOfWeekSchedule(monday);

        Schedule complex = ComplexSchedule.or(sundayScheule, mondayScheule);

        LocalDate start = LocalDate.of(2019, 11, 3);
        LocalDate end = start.plusWeeks(1);
        assertThat(countDays(complex, start, end), is(2));
    }

    @Test
    void comlexSchedule__negate() {
        Set<DayOfWeek> sunday = new HashSet<>();
        sunday.add(DayOfWeek.SUNDAY);

        Schedule sundayScheule = new DayOfWeekSchedule(sunday);
        Schedule complex = ComplexSchedule.negate(sundayScheule);

        LocalDate start = LocalDate.of(2019, 11, 3);
        LocalDate end = start.plusWeeks(1);
        assertThat(countDays(complex, start, end), is(6));
    }

    @Test
    void activityBuilder() {
        Activity activity = new ActivityBuilder()
                .source(new Meetup())
                .schedule(new DayOfWeekSchedule(workdays))
                .build();
    }

    @Test
    void developmentPlanBuilder() {
        Activity activity = new ActivityBuilder()
                .source(new Meetup())
                .schedule(new DayOfWeekSchedule(workdays))
                .build();

        DevelopmentPlan plan = new DevelopmentPlanBuilder()
                .add(activity)
                .build();
    }

    @Test
    void developmentPlanApply() {
        Student student = new Student(1, false);

        Institution institution = new Institution();
        institution.register(student);

        Activity activity = new ActivityBuilder()
                .source(institution)
                .schedule(new DayOfWeekSchedule(workdays))
                .build();

        DevelopmentPlan plan = new DevelopmentPlanBuilder()
                .add(activity)
                .build();

        LocalDate date = LocalDate.now();

        for (int i = 0; i < 7; i++) {
            plan.perform(student, date);
            date = date.plusDays(1);
        }

        assertThat(student.getKnowledge(), is(1000));
        assertThat(student.getPractice(), is(250));
    }
}



























