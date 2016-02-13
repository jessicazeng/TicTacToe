<%--
  Created by IntelliJ IDEA.
  User: Jessica
  Date: 2/4/2016
  Time: 2:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Tic-Tac-Toe | Play</title>

    <link rel="stylesheet" type="text/css" href="stylesheets/main.css">
    <link rel="stylesheet" type="text/css" href="stylesheets/gamestyle.css">
</head>
<body>
    <h1>Tic-Tac-Toe</h1>

    <p id="instruction"></p>
    <canvas id="board" width="350" height="350">Your browser does not support the canvas element.</canvas>

    <a href="leaderboard.jsp">Go Back to Leaderboard</a>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    <script src="js/game.js"></script>
</body>
</html>
