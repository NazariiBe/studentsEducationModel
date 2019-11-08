package studetnsEducationModel.activity;

import studetnsEducationModel.KnowledgeCategory;

public class Knowledge {
    private KnowledgeCategory category;
    private int level;

    public Knowledge(KnowledgeCategory category, int level) {
        this.category = category;
        this.level = level;
    }
}
