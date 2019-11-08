import jdk.jshell.execution.LoaderDelegate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import studetnsEducationModel.ActivityBuilder;
import studetnsEducationModel.DevelopmentPlan;
import studetnsEducationModel.DevelopmentPlanBuilder;
import studetnsEducationModel.activity.Activity;
import studetnsEducationModel.activity.knowledgeSource.Meetup;
import studetnsEducationModel.activity.schedule.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class studentsEducationModel {
    private Set<DayOfWeek> workdays = new HashSet<>();

    @BeforeEach
    void startUp() {
        workdays.add(DayOfWeek.MONDAY);
        workdays.add(DayOfWeek.TUESDAY);
        workdays.add(DayOfWeek.WEDNESDAY);
        workdays.add(DayOfWeek.THURSDAY);
        workdays.add(DayOfWeek.FRIDAY);
    }

    private int countDays(Schedule schedule, LocalDate start, LocalDate end) {
        LocalDate date = start;
        int totalDays = Period.between(start, end).getDays();
        int count = 0;
        for (int i = 0; i < totalDays; i++) {
            if (schedule.isCovers(date)) count++;
            date = date.plusDays(1);
        }
        return count;
    }

    @Test
    void schedule__coverTwoDayOfWeek() {
        Set<DayOfWeek> OneSunday = new HashSet<>();
        OneSunday.add(DayOfWeek.SUNDAY);
        Set<DayOfWeek> OneMonday = new HashSet<>();
        OneMonday.add(DayOfWeek.MONDAY);

        Schedule schedule = CompositeSchedule.and(new DayOfWeekSchedule(OneSunday), new DayOfWeekSchedule(OneMonday));
        LocalDate start = LocalDate.of(2019, 11, 3);
        LocalDate end = start.plusDays(6);
        assertThat(countDays(schedule, start, end), is(0));
    }

    @Test
    void schedule__coverCompositeSchedule() {
        Schedule days = new DayOfWeekSchedule(workdays);
        Set<Month> summer = new HashSet<>();
        summer.add(Month.JUNE);
        summer.add(Month.JULY);
        summer.add(Month.AUGUST);
        Schedule month = CompositeSchedule.negate(new MonthSchedule(summer));
        Schedule period = new PeriodSchedule(LocalDate.of(2019, 1, 1), LocalDate.of(2019, 1, 6));
        Schedule composite = CompositeSchedule.and(days, month, period);
        int count = countDays(composite, LocalDate.of(2019, 1, 1), LocalDate.of(2019, 1, 6));
        assertThat(count, is(4));
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

    }
}



























