<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="objects.*, service.*, java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title><%=session.getAttribute("quest-title")%></title>
    <link rel="stylesheet" href="styles/style.css">
    <link href="https://fonts.googleapis.com/css2?family=Honk&family=Rakkas&display=swap" rel="stylesheet">
    <link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon">
</head>
<body>
<div class="question-form-wrapper">
    <div class="question-form">
        <%
            QuestService service = (QuestService) session.getAttribute("service");
            String title = (String) session.getAttribute("title");
            String exposition = (String) session.getAttribute("exposition");
            List<Answer> answers = service.getAnswers((Decision) session.getAttribute("decision"));
        %>
        <div class="title-block"><%=title%></div>
        <div>
            <p id="description"><%=exposition%></p>
        </div>
        <div class="quest-process">
                <% int answerIndex = 0;
                    for (Answer answer : answers) { %>
                <div class="answer-block">
                    <div class="answer-title">
                        <p><%= service.getTitle(answer) %></p>
                    </div>
                    <div class="details-block">
                    <form action="./quest-processing" method="post">
                        <input type="hidden" name="answer" value="<%= answerIndex %>">
                        <button class="answer-button" type="submit">
                            <p class="answer-description"><%= service.getDescription(answer) %></p>
                        </button>
                    </form>
                    </div>
                </div>
                <% answerIndex++; } %>
        </div>
    </div>
</div>
</body>
</html>