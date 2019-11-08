package studetnsEducationModel;

import studetnsEducationModel.activity.knowledgeSource.Student;

import java.time.LocalDate;

public interface DevelopmentPlan {
    void perform(Student student, LocalDate day);
}
