package studetnsEducationModel;

import studetnsEducationModel.activity.Activity;
import studetnsEducationModel.activity.knowledgeSource.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ActivityBuilder {
    public CustomActivity build() {
        return new CustomDevelopmentPlan(this.activities);
    }

    private static class CustomActivity implements Activity {
        @Override
        public boolean tryToApply(Student student, LocalDate day) {
            return false;
        }
    }
}



