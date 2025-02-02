<!-- filepath: /c:/Users/andre/Documents/Master/An2/mpfc/JPA-Tests/src/main/webapp/WEB-INF/Pages/menu.jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<nav class="navbar navbar-expand-md navbar-dark bg-dark">
    <a class="navbar-brand" href="${pageContext.request.contextPath}">Online Tests</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarsExampleDefault">
        <ul class="navbar-nav mr-auto">
            <c:if test="${not empty sessionScope.loggedInUser}">
                <li class="nav-item ${activePage eq 'addQuiz' ? ' active' : ''}">
                    <a class="nav-link" href="${pageContext.request.contextPath}/AddQuizController">Add Quiz</a>
                </li>
                <li class="nav-item ${activePage eq 'ViewQuizList' ? ' active' : ''}">
                    <a class="nav-link" href="${pageContext.request.contextPath}/ViewQuizList">View Quizzes</a>
                </li>
                <li class="nav-item ${activePage eq 'ViewQuestions' ? ' active' : ''}">
                    <a class="nav-link" href="${pageContext.request.contextPath}/ViewQuestions">Your Questions</a>
                </li>
            </c:if>
        </ul>
        <ul class="navbar-nav ml-auto">
            <c:choose>
                <c:when test="${not empty sessionScope.loggedInUser}">
                    <li class="nav-item ${activePage eq 'logout' ? ' active' : ''}">
                        <a class="nav-link" href="${pageContext.request.contextPath}/logout">Logout</a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="nav-item ${activePage eq 'login' ? ' active' : ''}">
                        <a class="nav-link" href="${pageContext.request.contextPath}/login">Login</a>
                    </li>
                    <li class="nav-item ${activePage eq 'signup' ? ' active' : ''}">
                        <a class="nav-link" href="${pageContext.request.contextPath}/signup">Sign up</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</nav>