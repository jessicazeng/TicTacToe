<%--
  Created by IntelliJ IDEA.
  User: sicaz
  Date: 2/4/2016
  Time: 12:21 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Tic-Tac-Toe | Leaderboard</title>

    <link rel="stylesheet" type="text/css" href="stylesheets/main.css">
</head>
<body>
    <h1>Leaderboard</h1>
    <h3>Hello, ${applicationScope['Nickname']}</h3>

    <form action="/play">
        <input type="submit" value="Start a New Game"/>
    </form>

    <p>Sorry, there are currently no records. Game development in progress...</p>
</body>
</html>
