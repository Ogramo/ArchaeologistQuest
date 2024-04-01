package utility;

import objects.Answer;
import objects.Decision;
import objects.Quest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import service.QuestService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuestServiceTest {

    QuestService questService = new QuestService("archaeologist-quest.json");

    static Quest quest;

    static Decision testDecision;
    static String testDecisionTitle = "temple-ruins";
    @BeforeAll
    public static void init(){
        Map<String, Decision> decisionMap = getNewStringDecisionMap();
        quest = new Quest("On the Trail of the Lost Artifact", "You're an intrepid archaeologist on the hunt for a legendary artifact rumored" +
                " to hold unimaginable power. As you journey through ancient ruins and perilous jungles, " +
                "you must make crucial decisions to uncover the truth.", decisionMap);
    }

    private static Map<String, Decision> getNewStringDecisionMap() {
        Answer answer1 = new Answer("Explore the Temple Ruins",
                "Amongst the overgrown ruins, you find a crumbling statue with a faint inscription." +
                        " 'The path to the artifact lies through the heart of the temple,' it seems to whisper.", "temple-ruins");
        Answer answer2 = new Answer("Venture into the Deep Jungle",
                "The jungle is dense and teeming with life. Strange markings on trees catch your eye," +
                        " possibly indicating a hidden path.", "deep-jungle");
        Answer answer3 = new Answer("Follow the Dark Corridor",
                "The dark corridor seems ominous, but perhaps it leads to the heart " +
                        "of the temple where the artifact awaits.", "dark-corridor");
        Answer answer4 = new Answer("Ascend the Crumbling Stairs",
                "The crumbling stairs may be treacherous, " +
                        "but they offer a glimpse of sunlight filtering through the foliage above.", "crumbling-stairs");
        Decision decision1 = new Decision("Which path will you take?", List.of(answer1, answer2));
        Decision decision2 = new Decision("As you delve deeper into the temple ruins, you encounter a fork in the path. Which way will you go?", List.of(answer3, answer4));
        testDecision = decision2;
        Map<String, Decision> decisionMap = new HashMap<>();
        decisionMap.put("first-stage", decision1);
        decisionMap.put("temple-ruins", decision2);
        return decisionMap;
    }


    @Test
    void getQuestTitle() {
        String title = questService.getTitle();
        String result = "On the Trail of the Lost Artifact";
        assertEquals(title, result);
    }

    @Test
    void getQuestExposition() {
        String exposition = questService.getExposition();
        String result = "You're an intrepid archaeologist on the hunt for a legendary artifact rumored to hold unimaginable power. As you journey through ancient ruins and perilous jungles, you must make crucial decisions to uncover the truth.";
        assertEquals(exposition, result);
    }

    @Test
    void getQuestDecisions() {
        Decision decision = questService.getDecisions().get(testDecisionTitle);
        Decision result = quest.getDecisions().get(testDecisionTitle);
        assertEquals(decision, result);
    }

    @Test
    void getDecisionExposition() {
        String exposition = questService.getExposition(testDecision);
        String result = "As you delve deeper into the temple ruins, you encounter a fork in the path. Which way will you go?";
        assertEquals(exposition, result);
    }

    @Test
    void getAnswers() {
        Decision decision = questService.getDecisions().get(testDecisionTitle);
        List<Answer> answers = questService.getAnswers(decision);
        List<Answer> result = testDecision.getAnswers();
        assertEquals(answers, result);
    }

    @Test
    void getAnswerTitle() {
        Answer answer = questService.getDecisions().get(testDecisionTitle).getAnswers().get(1);
        String title = questService.getTitle(answer);
        String result = testDecision.getAnswers().get(1).getTitle();
        assertEquals(title, result);
    }

    @Test
    void getAnswerDescription() {
        Answer answer = questService.getDecisions().get(testDecisionTitle).getAnswers().get(1);
        String title = questService.getDescription(answer);
        String result = testDecision.getAnswers().get(1).getDescription();
        assertEquals(title, result);
    }

    @Test
    void getAnswerNextStage() {
        Answer answer = questService.getDecisions().get(testDecisionTitle).getAnswers().get(1);
        String title = questService.getNextStage(answer);
        String result = testDecision.getAnswers().get(1).getNextStage();
        assertEquals(title, result);
    }
}