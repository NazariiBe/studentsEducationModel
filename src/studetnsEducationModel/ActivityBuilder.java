package studetnsEducationModel;

import studetnsEducationModel.activity.Activity;
import studetnsEducationModel.activity.knowledgeSource.KnowledgeSource;
import studetnsEducationModel.activity.knowledgeSource.Student;
import studetnsEducationModel.activity.schedule.Schedule;

import java.time.LocalDate;

public class ActivityBuilder {
    private KnowledgeSource source;
    private Schedule schedule;

    public ActivityBuilder() {}

    public ActivityBuilder source(KnowledgeSource source) {
        this.source = source;
        return this;
    }

    public ActivityBuilder schedule(Schedule schedule) {
        this.schedule = schedule;
        return this;
    }

    public CustomActivity build() {
        CustomActivity activity = new CustomActivity(source, schedule);
        source = null;
        schedule = null;
        return activity;
    }

    private static class CustomActivity implements Activity {
        private KnowledgeSource knowledgeSource;
        private Schedule schedule;

        CustomActivity(KnowledgeSource knowledgeSource, Schedule schedule) {
            this.knowledgeSource = knowledgeSource;
            this.schedule = schedule;
        }

        @Override
        public boolean tryToApply(Student student, LocalDate day) {
            if (schedule.isCovers(day)) {
                knowledgeSource.teach(student);
                return true;
            } else {
                return false;
            }
        }
    }
}



