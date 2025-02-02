<!-- filepath: /c:/Users/andre/Documents/Master/An2/mpfc/JPA-Tests/src/main/webapp/WEB-INF/Pages/viewQuizList.jsp -->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:pageTemplate pageTitle="ViewQuizList">
    <style>
        .quiz-container {
            position: relative;
            border: 1px solid #ccc;
            padding: 20px;
            margin-bottom: 20px;
            width: 300px;
            height: 140px;
            background-color: #FAEDF7;
            border-radius: 10px;
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2); /* Adjust the values here */
        }

        .quiz-container .quiz-info {
            text-align: center;
            margin-top: 25px;
        }

        .quiz-container h2 {
            margin-top: 0;
        }

        .quiz-wrapper {
            display: flex;
            flex-flow: wrap;
            gap: 40px;
            border-radius: 20px;
        }

        .quiz-container .top-right-button {
            position: absolute;
            top: 10px;
            right: 10px;
        }
        .quiz-container .top-left-button {
            position: absolute;
            top: 10px;
            left: 10px;
        }
        .notification {
            display: flex;
            align-items: center;
            background-color: #d4edda;
            color: #155724;
            padding: 10px 20px;
            border-radius: 5px;
            border: 1px solid #c3e6cb;
            margin-bottom: 20px;
            font-size: 16px;
        }

        .notification .icon {
            margin-right: 10px;
        }

        .notification .icon svg {
            fill: #28a745;
        }
        /* Add more styles as needed */
    </style>
    <h1>Quizzes</h1>
    <c:if test="${param.submitted == 'true' || param.edited == 'true'}">
        <div class="notification">
            <div class="icon">
                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24">
                    <path d="M20.285 2.285l-11.285 11.285-5.285-5.285-3.715 3.715 9 9 15-15z"/>
                </svg>
            </div>
            <c:choose>
                <c:when test="${param.submitted == 'true'}">
                    <span>Quiz submitted successfully</span>
                </c:when>
                <c:when test="${param.edited == 'true'}">
                    <span>Quiz updated successfully</span>
                </c:when>
            </c:choose>
        </div>
    </c:if>
    <div class="quiz-wrapper">
        <c:forEach var="quiz" items="${quizzes}" varStatus="status">
            <div class="quiz-container" onclick="window.location='${pageContext.request.contextPath}/StartQuiz?quizId=${quiz.getId()}'">
                <button class="top-right-button btn btn-secondary" onclick="event.stopPropagation(); window.location='${pageContext.request.contextPath}/DeleteQuiz?quizId=${quiz.getId()}'">Delete quiz</button>
                <button class="top-left-button btn btn-primary" onclick="event.stopPropagation(); window.location='${pageContext.request.contextPath}/EditQuiz?quizId=${quiz.getId()}'">Edit quiz</button>
                
                <div class="quiz-info">
                    <h2>${quiz.quizName}</h2>
                </div>
            </div>
        </c:forEach>
    </div>
</t:pageTemplate>