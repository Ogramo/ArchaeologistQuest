package utility;

import interpreter.QuestInterpreter;
import objects.Quest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class QuestInterpreterTest {

    @Test
    void loadQuestTest() {
        QuestInterpreter questInterpreter = new QuestInterpreter("archaeologist-quest.json");
        Quest quest = questInterpreter.getQuest();
        assertNotNull(quest);
    }
}