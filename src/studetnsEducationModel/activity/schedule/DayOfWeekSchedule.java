package studetnsEducationModel.activity.schedule;

import java.time.LocalDate;

public class DayOfWeekSchedule implements Schedule {
    private boolean[] days;

    public DayOfWeekSchedule(boolean[] days) {
        this.days = days;
    }

    public DayOfWeekSchedule(boolean allWeek) {
        boolean[] days = new boolean[7];
        for (int i = 0; i < 7; i++) days[i] = allWeek;
        this.days = days;
    }

    @Override
    public boolean isCovers(LocalDate day) {
        for (int i = 0; i < days.length; i++) {
            if (days[i] & day.getDayOfWeek().getValue() == i) {
                return true;
            }
        }
        return false;
    }
}
