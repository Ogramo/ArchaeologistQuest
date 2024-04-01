<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="service.*, objects.Decision" %>
<!DOCTYPE html>
<html lang="en-EN">
<head>
    <title>quest</title>
    <link rel="stylesheet" href="styles/style.css">
    <link href="https://fonts.googleapis.com/css2?family=Honk&family=Rakkas&display=swap" rel="stylesheet">
    <link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon">
</head>
<body>
<div class="finish-form-wrapper">
    <div class="finish-form">
        <p class="end">The End!</p>
        <form action="./welcome.jsp" target="_blank">
            <% session.setAttribute("varStatus", 1);
                QuestService service = (QuestService) session.getAttribute("service");
                Decision decision = service.getDecisions().get("end");
                String text = service.getExposition(decision);
            %>
            <p class ="finish-text"><%=text%>
            </p>
            <div class="finish-button-wrapper">
                <button class="finish-button" type="submit">
                    Return to welcome page
                </button>
            </div>
        </form>
    </div>
</div>
</body>
</html>