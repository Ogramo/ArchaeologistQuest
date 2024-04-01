package utility;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.SneakyThrows;
import objects.Answer;
import objects.Decision;
import org.junit.Before;
import org.junit.Test;
import service.QuestService;
import servlets.QuestProcessServlet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.mockito.Mockito.*;

public class QuestProcessServletTest{
    private QuestProcessServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;

    @Before
    public void setUp(){
        servlet = new QuestProcessServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);

        when(request.getSession()).thenReturn(session);
    }

    @SneakyThrows
    @Test
    public void testDoPost(){
        when(request.getParameter("answer")).thenReturn("0");

        QuestService mockService = mock(QuestService.class);
        Decision mockDecision = mock(Decision.class);
        List<Answer> answers = new ArrayList<>();
        Answer mockAnswer = mock(Answer.class);
        answers.add(mockAnswer);

        when(session.getAttribute("service")).thenReturn(mockService);
        when(session.getAttribute("decision")).thenReturn(mockDecision);
        when(mockService.getAnswers(mockDecision)).thenReturn(answers);
        when(mockService.getTitle(mockAnswer)).thenReturn("Title");
        when(mockService.getNextStage(mockAnswer)).thenReturn("nextStage");
        when(mockService.getDecisions()).thenReturn(new HashMap<>());

        servlet.doPost(request, response);

        verify(session).setAttribute("title", "Title");
        verify(session).setAttribute("exposition", null);
        verify(session).setAttribute("previousDecision", mockDecision);
        verify(session).setAttribute("decision", null);

        verify(response).sendRedirect("quest-processing.jsp");
    }
}