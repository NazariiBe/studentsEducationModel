package studetnsEducationModel.activity.schedule;

import java.time.LocalDate;

public class OneDaySchedule implements Schedule {
    private LocalDate date;

    public OneDaySchedule(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean isCovers(LocalDate day) {
        return day.isEqual(date);
    }
}
