<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Add Quiz">
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
            max-width: 1000px;
            background: #ffffff;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 10px 20px rgba(0, 0, 0, 0.15);
            margin-top: 50px;
        }

        .quiz-container h2 {
            text-align: center;
            color: #333;
            margin-bottom: 20px;
        }

        label {
            font-weight: bold;
            display: block;
            margin-bottom: 5px;
        }

        input {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
            margin-bottom: 15px;
        }
        select {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
            margin-bottom: 15px;
            height: 300px;
        }

        fieldset {
            border: none;
            padding: 0;
            margin: 0;
        }

        legend {
            font-weight: bold;
            margin-bottom: 10px;
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
            text-align: center;
            display: block;
            width: 100%;
        }

        .btn-primary:hover {
            background-color: #0056b3;
        }
    </style>
    
    <div class="quiz-container">
        <h2>Add Quiz</h2>
        <form action="${pageContext.request.contextPath}/AddQuizController" method="post">
            <label for="quizName">Name:</label>
            <input type="text" id="quizName" name="quizName" required>
            
            <fieldset>
                <legend>Questions</legend>
                <label for="questionsSelect">Select Questions:</label>
                <select id="questionsSelect" name="questionsSelect" multiple required>
                    <c:forEach var="question" items="${questions}">
                        <option value="${question.id}">${question.getQText()}</option>
                    </c:forEach>
                </select>
            </fieldset>
            
            <button type="submit" class="btn-primary">Submit Quiz</button>
        </form>
    </div>
</t:pageTemplate>
