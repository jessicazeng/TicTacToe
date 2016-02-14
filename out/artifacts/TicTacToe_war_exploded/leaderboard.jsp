<%--
  Created by IntelliJ IDEA.
  User: sicaz
  Date: 2/4/2016
  Time: 12:21 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Tic-Tac-Toe | Leaderboard</title>

    <link rel="stylesheet" type="text/css" href="stylesheets/main.css">
    <link rel="stylesheet" type="text/css" href="stylesheets/leaderboard.css">
</head>
<body>
    <sql:setDataSource var="conn" driver="${applicationScope['dbdriver']}"
                       url="${applicationScope['dburl']}"
                       user="root"  password="${applicationScope['dbpw']}"/>

    <sql:query dataSource="${conn}" var="result">
        SELECT * FROM Players ORDER BY Wins DESC;
    </sql:query>

    <div>
        ${applicationScope['Nickname']} | <a href="/logout">Logout</a>
    </div>

    <h1>Leaderboard</h1>

    <a href="/play" class="button orange">Start a New Game</a>

    <table>
        <tr>
            <th>Player</th>
            <th>Wins</th>
        </tr>
        <c:forEach var="player" items="${result.rows}">
            <tr>
                <td><c:out value="${player.Nickname}"/></td>
                <td><c:out value="${player.Wins}"/></td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>
