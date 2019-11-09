package studetnsEducationModel.activity.schedule;

import java.time.LocalDate;

public class ComplexSchedule {
    public static Schedule and(Schedule... schedules) {
        return new ComplexScheduleAnd(schedules);
    }

    public static Schedule or(Schedule... schedules) {
        return new ComplexScheduleOr(schedules);
    }

    public static Schedule negate(Schedule schedule) {
        return new ComplexScheduleNegate(schedule);
    }

    private static class ComplexScheduleNegate implements Schedule {
        private Schedule schedule;

        ComplexScheduleNegate(Schedule schedule) {
            this.schedule = schedule;
        }

        @Override
        public boolean isCovers(LocalDate day) {
            return !schedule.isCovers(day);
        }
    }

    private static class ComplexScheduleOr implements Schedule {
        private Schedule[] schedules;

        ComplexScheduleOr(Schedule[] schedules) {
            this.schedules = schedules;
        }

        @Override
        public boolean isCovers(LocalDate day) {
            for (Schedule schedule : schedules) if (schedule.isCovers(day)) return true;
            return false;
        }
    }

    private static class ComplexScheduleAnd implements Schedule {
        private Schedule[] schedules;

        ComplexScheduleAnd(Schedule[] schedules) {
            this.schedules = schedules;
        }

        @Override
        public boolean isCovers(LocalDate day) {
            for (Schedule schedule : schedules) if (!schedule.isCovers(day)) return false;
            return true;
        }
    }
}
