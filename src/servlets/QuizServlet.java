package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cn.model.Quiz;

/**
 * Servlet implementation class QuizServlet
 */
@WebServlet("/QuizServlet")
public class QuizServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Quiz quiz = new Quiz();
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QuizServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(request.getSession().getAttribute("user") == null){
			request.getSession().setAttribute("user","omar");
		}
		genQuizPage(quiz,response.getWriter(),quiz.getCurrentQuestion(),false,quiz.getCurrentQestionAnswer());
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(request.getSession().getAttribute("user") == null){
			request.getSession().setAttribute("user","omar");
			genQuizPage(quiz,response.getWriter(),quiz.getCurrentQuestion(),false,quiz.getCurrentQestionAnswer());
		}else{
			if(quiz.getCurrentQestionAnswer().equals(request.getParameter("txtAnswer"))){
				quiz.scoreAnswer();
				if(quiz.getNumCorrect() != quiz.getNumQuestions()){
					genQuizPage(quiz,response.getWriter(),quiz.getCurrentQuestion(),false, quiz.getCurrentQestionAnswer());
				}else{
					genQuizOverPage(response.getWriter());
					request.getSession().removeAttribute("user");
					quiz = new Quiz();
				}
			}else{
				genQuizPage(quiz,response.getWriter(),quiz.getCurrentQuestion(),true, quiz.getCurrentQestionAnswer());
			}
		}
		
	}

	private void genQuizPage(Quiz sessQuiz, PrintWriter out, String currQuest, boolean error, String answer) {

		out.print("<html>");
		out.print("<head>");
		out.print("	<title>NumberQuiz</title>");
		out.print("</head>");
		out.print("<body>");
		out.print("	<form method='post'>");
		out.print("		<h3>Have fun with NumberQuiz!</h3>");
		out.print("<p>Your current score is: ");
		out.print(sessQuiz.getNumCorrect() + "</br></br>");
		out.print("<p>Guess the next number in the sequence! ");
		out.print(currQuest + "</p>");

		out.print("<p>Your answer:<input type='text' name='txtAnswer' value='' /></p> ");

		/* if incorrect, then print out error message */
		if (error && (answer != null)) { // REFACTOR?-- assumes answer null only
											// when first open page
			out.print("<p style='color:red'>Your last answer was not correct! Please try again</p> ");
		}
		out.print("<p><input type='submit' name='btnNext' value='Next' /></p> ");

		out.print("</form>");
		out.print("</body></html>");
	}

	private void genQuizOverPage(PrintWriter out) {
		out.print("<html> ");
		out.print("<head >");
		out.print("<title>NumberQuiz is over</title> ");
		out.print("</head> ");
		out.print("<body> ");
		out.print("<p style='color:red'>The number quiz is over!</p>	</body> ");
		out.print("</html> ");
	}

}
