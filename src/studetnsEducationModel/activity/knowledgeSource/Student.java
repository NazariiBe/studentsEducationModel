package studetnsEducationModel.activity.knowledgeSource;

import studetnsEducationModel.activity.Knowledge;
import studetnsEducationModel.activity.Practice;

import java.util.ArrayList;
import java.util.List;

public class Student implements KnowledgeSource {
    private float learningRatio;
    private boolean laptop;

    private List<Knowledge> knowledges = new ArrayList<>();
    private List<Practice> practices = new ArrayList<>();

    public Student(float learningRatio, boolean laptop) {
        this.learningRatio = learningRatio;
        this.laptop = laptop;
    }

    public void learn(Knowledge knowledge) {
        knowledges.add(knowledge);
    }

    public void practice(Practice practice) {
        practices.add(practice);
    }

    public List<Knowledge> getKnowledges() {
        return knowledges;
    }

    public List<Practice> getPractices() {
        return practices;
    }

    public boolean hasLaptop() {
        return laptop;
    }

    @Override
    public void teach(Student student) {
        for (Knowledge knowledge: knowledges) {
            student.learn(knowledge);
        }
    }
}
