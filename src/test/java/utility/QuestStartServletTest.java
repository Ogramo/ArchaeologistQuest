package utility;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import service.QuestService;
import servlets.QuestStartServlet;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuestStartServletTest {
    @Mock private HttpServletRequest request;
    @Mock private HttpServletResponse response;
    @Mock private RequestDispatcher dispatcher;
    @Mock private HttpSession session;
    private QuestStartServlet servlet;

    @BeforeEach
    public void setUp(){
        servlet = new QuestStartServlet();
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("/quest-processing.jsp")).thenReturn(dispatcher);
    }

    @Test
    void testDoGet() throws Exception {
        servlet.doGet(request, response);
        QuestService questService = new QuestService("archaeologist-quest.json");
        verify(session).setAttribute("service", questService);
        verify(session).setAttribute("exposition", questService.getExposition());
        verify(session).setAttribute("decision", questService.getDecisions().get("first-stage"));
        verify(session).setAttribute("quest-title", questService.getTitle());
        verify(session).setAttribute("title", questService.getTitle());

        verify(dispatcher).forward(request, response);
    }

}