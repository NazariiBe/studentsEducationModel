package studetnsEducationModel.activity.schedule;

import java.time.LocalDate;

public class EveryDaySchedule implements Schedule {
    public EveryDaySchedule() { }

    @Override
    public boolean isCovers(LocalDate day) {
        return true;
    }
}
