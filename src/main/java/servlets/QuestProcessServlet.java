package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.SneakyThrows;
import objects.*;
import service.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "QuestProcessServlet", value = "/quest-processing")
public class QuestProcessServlet extends HttpServlet {
    @SneakyThrows
    private static void redirectOrForwardToNextStage(HttpServletRequest req, HttpServletResponse resp, String nextStage) throws IOException {
        if (nextStage.equals("end")) {
            req.getRequestDispatcher("quest-finish.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("quest-processing.jsp");
        }
    }

    private static String getNextStageAndSetNeededAttributes(HttpServletRequest req, List<Answer> answers, QuestService service, HttpSession session, Decision decision) {
        int answerIndex = Integer.parseInt(req.getParameter("answer"));
        Answer answer = answers.get(answerIndex);
        String title = service.getTitle(answer);
        session.setAttribute("title", title);
        String nextStage = service.getNextStage(answer);
        Decision nextDecision = service.getDecisions().get(nextStage);
        session.setAttribute("exposition", service.getExposition(nextDecision));
        session.setAttribute("previousDecision", decision);
        session.setAttribute("decision", nextDecision);
        return nextStage;
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        QuestService service = (QuestService) session.getAttribute("service");
        Decision decision = (Decision) session.getAttribute("decision");
        List<Answer> answers = service.getAnswers(decision);
        if (answers == null) {
            session.setAttribute("decision", session.getAttribute("previousDecision"));
            resp.sendRedirect("quest-finish.jsp");
        } else {
            String nextStage = getNextStageAndSetNeededAttributes(req, answers, service, session, decision);
            redirectOrForwardToNextStage(req, resp, nextStage);
        }
    }
}
