<%--
  Created by IntelliJ IDEA.
  User: sicaz
  Date: 2/4/2016
  Time: 12:21 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Tic-Tac-Toe | Leaderboard</title>

    <link rel="stylesheet" type="text/css" href="stylesheets/main.css">
</head>
<body>
    <sql:setDataSource var="conn" driver="com.mysql.jdbc.Driver"
                       url="jdbc:mysql://173.194.239.47:3306/tictactoe"
                       user="root"  password=""/>

    <sql:query dataSource="${conn}" var="result">
        SELECT * FROM Players ORDER BY Wins DESC;
    </sql:query>

    <h1>Leaderboard</h1>
    <h3>Hello, ${applicationScope['Nickname']}</h3>

    <form action="/play">
        <input type="submit" value="Start a New Game"/>
    </form>

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
