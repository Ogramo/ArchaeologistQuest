package service;

import lombok.EqualsAndHashCode;
import objects.*;
import interpreter.*;

import java.util.List;
import java.util.Map;

@EqualsAndHashCode
public class QuestService {
    private final Quest quest;

    public QuestService(String questName) {
        QuestInterpreter loader = new QuestInterpreter(questName);
        quest = loader.getQuest();
    }

    public String getTitle() {
        return quest.getTitle();
    }

    public String getExposition() {
        return quest.getExposition();
    }

    public Map<String, Decision> getDecisions() {
        return quest.getDecisions();
    }

    public String getExposition(Decision decision) {
        return decision.getExposition();
    }

    public List<Answer> getAnswers(Decision decision) {
        return decision.getAnswers();
    }

    public String getTitle(Answer answer) {
        return answer.getTitle();
    }

    public String getDescription(Answer answer) {
        return answer.getDescription();
    }

    public String getNextStage(Answer answer) {
        return answer.getNextStage();
    }
}
