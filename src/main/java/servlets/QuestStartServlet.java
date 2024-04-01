package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.*;

import java.io.IOException;

@WebServlet(name = "StartServlet", value = "/start")
public class QuestStartServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        QuestService questService = new QuestService("archaeologist-quest.json");

        session.setAttribute("service", questService);
        session.setAttribute("exposition", questService.getExposition());
        session.setAttribute("decision", questService.getDecisions().get("first-stage"));
        session.setAttribute("quest-title", questService.getTitle());
        session.setAttribute("title", questService.getTitle());

        req.getRequestDispatcher("/quest-processing.jsp").forward(req, resp);
    }
}
