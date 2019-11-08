package studetnsEducationModel.activity.schedule;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Set;
import java.util.function.Predicate;

public class DayOfWeekSchedule implements Schedule {
    private Set<DayOfWeek> days;

    public DayOfWeekSchedule(Set<DayOfWeek> days) {
        this.days = days;
    }

    @Override
    public boolean isCovers(LocalDate day) {
        return days.contains(day.getDayOfWeek());
    }
}
