package studetnsEducationModel.activity.schedule;

import java.time.LocalDate;
import java.util.function.Predicate;

public class CompositeSchedule implements Schedule {
    private Predicate<LocalDate> predicate;

    @Override
    public boolean isCovers(LocalDate day) {
        return predicate.test(day);
    }

    private CompositeSchedule(Predicate<LocalDate> predicate) {
        this.predicate = predicate;
    }

    public static CompositeSchedule and(Schedule ... schedules) {
        return new CompositeSchedule(day -> {
            for(Schedule schedule: schedules) if (!schedule.isCovers(day)) return false;
            return true;
        });
    }

    public static CompositeSchedule or(Schedule ... schedules) {
        return new CompositeSchedule(day -> {
            for(Schedule schedule: schedules) if (schedule.isCovers(day)) return true;
            return false;
        });
    }

    public static CompositeSchedule negate(Schedule schedule) {
        return new CompositeSchedule(day -> {
            return !schedule.isCovers(day);
        });
    }
}
