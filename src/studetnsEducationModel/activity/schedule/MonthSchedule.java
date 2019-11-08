package studetnsEducationModel.activity.schedule;

import java.time.LocalDate;

public class MonthSchedule implements Schedule {
    private int month;

    public MonthSchedule(int month) {
        this.month = month;
    }

    @Override
    public boolean isCovers(LocalDate day) {
        return day.getMonthValue() == month;
    }
}
