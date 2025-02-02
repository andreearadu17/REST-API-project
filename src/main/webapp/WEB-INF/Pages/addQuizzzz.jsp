<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:pageTemplate pageTitle="AddQuizController">


    <h2>Add Quizzao</h2>
    <form action="${pageContext.request.contextPath}/addQuiz" method="post">
        <label for="numQuestions">Number of questions:</label>
        <input type="number" id="numQuestions" name="numQuestions" required><br><br>
        <label for="quizName">Name:</label>
        <input type="text" id="quizName" name="quizName" required><br><br>

        <fieldset>
            <legend>Questions</legend>
            <div id="questionsContainer"></div>
            <button type="button" onclick="addQuestion()">Add Question</button>
        </fieldset>

        <button type="submit">Submit quiz</button>
    </form>

    <script>
        function addQuestion() {
            var questionsContainer = document.getElementById("questionsContainer");
            var questionNumber = questionsContainer.childElementCount + 1;

            var questionDiv = document.createElement("div");
            questionDiv.innerHTML = `
                <label for="question${questionNumber}">Question ${questionNumber}:</label>
                <input type="text" id="question${questionNumber}" name="question${questionNumber}" required><br><br>
                
                <label for="options${questionNumber}">Options:</label>
                <input type="text" id="options${questionNumber}" name="options${questionNumber}" placeholder="Enter options separated by comma" required><br><br>
                
                <label for="correctAnswer${questionNumber}">Correct Answer Index:</label>
                <input type="number" id="correctAnswer${questionNumber}" name="correctAnswer${questionNumber}" min="1" required><br><br>
            `;
            questionsContainer.appendChild(questionDiv);
        }
    </script>
</t:pageTemplate>