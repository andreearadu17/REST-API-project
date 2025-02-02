<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:pageTemplate pageTitle="Your Questions">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }

        .questions-container {
            max-width: 1000px;
            background: #ffffff;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 10px 20px rgba(0, 0, 0, 0.15);
            margin-top: 20px;
            margin-bottom: 50px;
        }

        .questions-container h1 {
            text-align: center;
            color: #333;
            margin-bottom: 20px;
        }

        .question-item {
            background: #f9f9f9;
            padding: 15px;
            border-radius: 8px;
            margin-bottom: 15px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        .question-item h3 {
            margin: 0;
            color: #333;
        }

        .question-item hr {
            margin: 10px 0;
            border: none;
            height: 1px;
            background: #ddd;
        }

        .text-right {
            text-align: center;
            margin-top: 50px;
        }

        .btn-primary {
            background-color: #007bff;
            color: #fff;
            padding: 12px 24px;
            border: none;
            border-radius: 6px;
            font-size: 16px;
            cursor: pointer;
            transition: background 0.3s;
            text-decoration: none;
            display: inline-block;
        }

        .btn-primary:hover {
            background-color: #0056b3;
        }
    </style>
    <div class="text-right">
        <a href="${pageContext.request.contextPath}/AddQuestion" class="btn-primary">Add Question</a>
    </div>
    <div class="questions-container">
        <h1>Your Questions</h1>
        <c:forEach var="question" items="${questionList}">
            <div class="question-item">
                <h3>${question.getQText()}</h3>
                <hr>
            </div>
        </c:forEach>
    </div>
    
</t:pageTemplate>