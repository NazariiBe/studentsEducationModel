package studetnsEducationModel.activity.schedule;

import java.time.LocalDate;
import java.time.Month;
import java.util.Set;

public class MonthSchedule implements Schedule {
    private Set<Month> monthSet;

    public MonthSchedule(Set<Month> monthSet) {
        this.monthSet = monthSet;
    }

    @Override
    public boolean isCovers(LocalDate day) {
        return monthSet.contains(day.getMonth());
    }
}
