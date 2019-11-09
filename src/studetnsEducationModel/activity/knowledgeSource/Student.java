package studetnsEducationModel.activity.knowledgeSource;

import jdk.jfr.Category;
import studetnsEducationModel.activity.Knowledge;
import studetnsEducationModel.activity.Practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student implements KnowledgeSource {
    private float learningRatio;
    private boolean laptop;

    private int knowledge = 0;
    private int practice = 0;

    public Student(float learningRatio, boolean laptop) {
        this.learningRatio = learningRatio;
        this.laptop = laptop;
    }

    public void learn(Knowledge knowledge) {
        this.knowledge += knowledge.getPoints();
    }

    public void practice(Practice practice) {
        this.practice += practice.getPoints();
    }

    public int getKnowledge() {
        return knowledge;
    }

    public int getPractice() {
        return practice;
    }

    public boolean hasLaptop() {
        return laptop;
    }

    public void forgetAll() {
        knowledge = 0;
        practice = 0;
    }

    public float getLearningRatio() {
        return learningRatio;
    }

    @Override
    public void teach(Student student) {
        student.learn(new Knowledge(knowledge / 2));
    }
}
