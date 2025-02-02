<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:pageTemplate pageTitle="Start Test">
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

        .quiz-container {
            background: #ffffff;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 10px 20px rgba(0, 0, 0, 0.15);
            margin-top: 50px;
            max-width: 1000px;
        }

        .quiz-container h1 {
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

        .question-item label {
            font-weight: 600;
            color: #333;
            display: block;
            margin-bottom: 5px;
        }

        .question-item input {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
        }

        .submit-button {
            text-align: center;
            margin-top: 20px;
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
        }

        .btn-primary:hover {
            background-color: #0056b3;
        }
    </style>
    
    <div class="quiz-container">
        <h1>Start Quiz</h1>
        <form action="StartQuiz" method="post">
            <c:forEach var="question" items="${questionsToDisplay}" varStatus="status">
                <div class="question-item">
                    <label for="${question.getId()}">${question.getQText()}</label>
                    <input type="text" id="${question.getId()}" name="Question${question.getId()}" required>
                </div>
            </c:forEach>
            
            <input type="hidden" name="quizId" value="${quizId}">
            
            <div class="submit-button">
                <button type="submit" class="btn-primary">Submit Test</button>
            </div>
        </form>
    </div>
</t:pageTemplate>