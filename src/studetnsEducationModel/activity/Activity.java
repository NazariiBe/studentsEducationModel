package studetnsEducationModel.activity;

import studetnsEducationModel.activity.knowledgeSource.Student;

import java.time.LocalDate;

public interface Activity {
    boolean tryToApply(Student student, LocalDate day);
}
