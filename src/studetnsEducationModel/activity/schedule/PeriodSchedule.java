package studetnsEducationModel.activity.schedule;

import java.time.LocalDate;

public class PeriodSchedule implements Schedule {
    private LocalDate start;
    private LocalDate end;

    public PeriodSchedule(LocalDate start, LocalDate end) {
        this.start = start.minusDays(1);
        this.end = end.plusDays(1);
    }

    @Override
    public boolean isCovers(LocalDate day) {
        return day.isBefore(end) && day.isAfter(start);
    }
}
