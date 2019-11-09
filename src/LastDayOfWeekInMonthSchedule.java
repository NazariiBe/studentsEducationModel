import studetnsEducationModel.activity.schedule.Schedule;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

public class LastDayOfWeekInMonthSchedule implements Schedule {
    private DayOfWeek dayOfWeek;

    public LastDayOfWeekInMonthSchedule(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    @Override
    public boolean isCovers(LocalDate day) {
        Month month = day.getMonth();
        return day.getDayOfWeek() == dayOfWeek & day.plusWeeks(1).getMonth() == month;
    }
}
