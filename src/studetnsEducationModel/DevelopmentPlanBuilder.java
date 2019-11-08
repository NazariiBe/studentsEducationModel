package studetnsEducationModel;

import studetnsEducationModel.activity.Activity;
import studetnsEducationModel.activity.knowledgeSource.Student;

import java.awt.image.AreaAveragingScaleFilter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DevelopmentPlanBuilder {
    private ArrayList<Activity> activities;

    public DevelopmentPlanBuilder() {
        activities = new ArrayList<>();
    }

    public DevelopmentPlanBuilder add(Activity activity) {
        activities.add(activity);
        return this;
    }

    public CustomDevelopmentPlan build() {
        CustomDevelopmentPlan plan = new CustomDevelopmentPlan(this.activities);
        activities = new ArrayList<>();
        return plan;
    }

    private static class CustomDevelopmentPlan implements DevelopmentPlan {
        private List<Activity> activities;

        CustomDevelopmentPlan(List<Activity> activities) {
            this.activities = activities;
        }

        @Override
        public void perform(Student student, LocalDate day) {
            for (Activity activity: activities) activity.tryToApply(student, day);
        }
    }
}



