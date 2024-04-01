<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome page!</title>
    <link rel="stylesheet" href="styles/style.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Honk&family=Rakkas&display=swap" rel="stylesheet">
    <link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon">
</head>
<body>
<div class="welcome-wrapper">
    <div class="welcome-block">
        <form method="get" action="./start">
        <p class="welcome-text"> Dear <%=(String)session.getAttribute("username")%>, welcome to 'The Trail of the Lost Artifact'!
            You find yourself standing at the entrance of an ancient temple,
            rumored to hold a long-lost artifact of immense power.
            Your quest is to navigate through the temple's chambers, decipher clues,
            and overcome obstacles to uncover the artifact's whereabouts.
            Be prepared for challenges and mysteries lurking in the shadows.
            Your choices will shape the outcome of your journey.
            Are you ready to embark on this adventure?
        </p>
            <div class="form-group">
                <button class="start-button" type="submit">
                    Start the journey!
                </button>
            </div>
        </form>
    </div>
</div>
</body>
</html>