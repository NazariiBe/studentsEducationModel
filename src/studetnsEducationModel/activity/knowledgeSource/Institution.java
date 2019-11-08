package studetnsEducationModel.activity.knowledgeSource;

import studetnsEducationModel.KnowledgeCategory;
import studetnsEducationModel.activity.Knowledge;
import studetnsEducationModel.activity.Practice;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Institution implements KnowledgeSource {
    private Set<Student> students = new HashSet<>();

    public void register(Student student) {
        students.add(student);
    }

    @Override
    public void teach(Student student) {
        if (students.contains(student)) {
            KnowledgeCategory[] categories = KnowledgeCategory.values();
            KnowledgeCategory category = categories[new Random().nextInt(categories.length)];

            student.learn(new Knowledge(category, 200));
            student.practice(new Practice(category, 50));
        }
    }
}
