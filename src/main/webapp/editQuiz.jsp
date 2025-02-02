<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Edit Quiz">
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

        .quiz-wrapper {
            display: flex;
            flex-direction: column;
            align-items: center;
            gap: 20px;
            padding: 20px;
        }

        .quiz-container {
            max-width: 1000px;
            background: #ffffff;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 10px 20px rgba(0, 0, 0, 0.15);
            width: 100%;
            margin-top: 50px;
        }

        .quiz-container h1 {
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

        .remove-button {
            background-color: red;
            color: white;
            border: none;
            padding: 5px 10px;
            border-radius: 5px;
            cursor: pointer;
            float: right;
        }
        .question-item {
            background: #f9f9f9;
            padding: 15px;
            border-radius: 8px;
            margin-bottom: 15px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            display: flex;
            flex-direction: row;
            gap: 10px;
            align-items: baseline;
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
    
    <div class="quiz-wrapper">
        <form id="editQuizForm" action="EditQuiz" method="post" class="quiz-container">
            <h1>Edit Quiz</h1>
            <label>Quiz Name: ${quiz.getQuizName()}</label>
            <c:forEach var="question" items="${questionsToEdit}" varStatus="status"> 
                <div class="quiz-info" id="question${question.getId()}Container">
                    <label for="question${question.getId()}">Question</label>
                    <div class="question-item">
                    <input type="text" id="question${question.getId()}" name="Question${question.getId()}" value="${question.getQText()}" required>
                    <button type="button" class="remove-button" onclick="removeQuestion(${question.getId()})">X</button>
                    </div>
                </div>
            </c:forEach>
            <input type="hidden" name="quizId" value="${quiz.getId()}">
            <input type="hidden" id="questionsToRemove" name="questionsToRemove" value="">
            <button type="submit" class="btn-primary">Update Quiz</button>
        </form>
    </div>
    
    <script>
        function removeQuestion(questionId) {
            var questionContainer = document.getElementById('question' + questionId + 'Container');
            if (questionContainer) {
                questionContainer.style.display = 'none';
                var questionsToRemove = document.getElementById('questionsToRemove');
                questionsToRemove.value += questionId + ",";
            }
        }
    </script>
</t:pageTemplate>
