package studetnsEducationModel.activity.knowledgeSource;

import studetnsEducationModel.KnowledgeCategory;
import studetnsEducationModel.activity.Knowledge;
import studetnsEducationModel.activity.Practice;

import java.util.Random;

public class SelfStudy implements KnowledgeSource {
    @Override
    public void teach(Student student) {
        KnowledgeCategory[] categories = KnowledgeCategory.values();
        KnowledgeCategory category = categories[random(categories.length - 1)];
        Knowledge knowledge = new Knowledge(category, random(100));
        Practice practice = new Practice(category, random(24));

        student.learn(knowledge);
        student.practice(practice);
    }

    private int random(int max) {
        return new Random().nextInt(max + 1);
    }
}
