
package controller;

import dao.QuestionDAO;
import dao.QuizDAO;
import dao.AnswerDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

import model.Answer;
import model.Question;
import model.Quiz;
import model.QuizCreator;

@WebServlet("/StartQuiz")
public class StartQuizController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        QuizCreator loggedInUser = (QuizCreator) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String quizIdParam = request.getParameter("quizId");
        if (quizIdParam != null) {
            try {
                Long quizId = Long.parseLong(quizIdParam);
                QuizDAO quizDAO = new QuizDAO();
                Quiz quiz = quizDAO.getQuizById(quizId);
                if (quiz != null) {
                    QuestionDAO questionDAO = new QuestionDAO();
                    List<Question> questions = questionDAO.getAllQuestionsForQuiz(quizId);

                    AnswerDAO answerDAO = new AnswerDAO();
                    for (Question question : questions) {
                        String answerText = request.getParameter("Question" + question.getId());
                        if (answerText != null && !answerText.isEmpty()) {
                            Answer answer = new Answer();
                            answer.setQuestion(question);
                            answer.setQuiz(quiz);
                            answer.setSolver(loggedInUser);
                            answer.setResponse(answerText);
                            answerDAO.insertAnswer(answer);
                        }
                    }

                    // display a message to the user
                    response.sendRedirect(request.getContextPath() + "/ViewQuizList?submitted=true");
                    // display a preview for the questions
                } else {
                    response.getWriter().println("Quiz not found.");
                }
            } catch (NumberFormatException e) {
                response.getWriter().println("Invalid quiz ID.");
                e.printStackTrace();
            }
        } else {
            response.getWriter().println("Quiz ID is missing.");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String quizId = request.getParameter("quizId");
        QuestionDAO questionDAO = new QuestionDAO();

        Long quizIdLong = Long.parseLong(quizId);

        try {
            List<Question> questions = questionDAO.getAllQuestionsForQuiz(quizIdLong);
            response.getWriter().println("sunt intrebari" + questions);
            request.setAttribute("questionsToDisplay", questions);
            request.setAttribute("quizId", quizId);
            request.getRequestDispatcher("startTest.jsp").forward(request, response);
        } catch (Exception e) {
            response.getWriter().println("nu sunt intrebari");
            e.printStackTrace();
        }

    }
}
