package studetnsEducationModel.activity.knowledgeSource;

import studetnsEducationModel.KnowledgeCategory;
import studetnsEducationModel.activity.Knowledge;
import studetnsEducationModel.activity.Practice;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Institution implements KnowledgeSource {
    private Set<Student> students = new HashSet<>();
    private int knoweledge;
    private int practice;

    public Institution() {
        this.knoweledge = 200;
        this.practice = 50;
    }

    public Institution(int knoweledge, int practice) {
        this.knoweledge = knoweledge;
        this.practice = practice;
    }

    public void register(Student student) {
        students.add(student);
    }

    @Override
    public void teach(Student student) {
        if (students.contains(student)) {
            student.learn(new Knowledge(knoweledge));
            student.practice(new Practice(practice));
        }
    }
}
