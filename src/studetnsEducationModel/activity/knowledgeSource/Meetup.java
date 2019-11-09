package studetnsEducationModel.activity.knowledgeSource;

import studetnsEducationModel.KnowledgeCategory;
import studetnsEducationModel.activity.Knowledge;
import studetnsEducationModel.activity.Practice;

import java.util.Random;

public class Meetup implements KnowledgeSource {
    @Override
    public void teach(Student student) {
        student.learn(new Knowledge(random(150)));
        if (student.hasLaptop()) {
            student.practice(new Practice(random(24)));
        }
    }

    private int random(int max) {
        return new Random().nextInt(max + 1);
    }
}
