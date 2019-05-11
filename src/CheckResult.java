
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CheckResult")
public class CheckResult extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CheckResult() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		PrintWriter writer = response.getWriter();
		String url = request.getParameter("url");
		String[] urlArray = url.split("\n");
		String result = "";
		for (int i = 0; i < urlArray.length; i++) {
			result += "<b> Проверка орфографии сайта " + urlArray[i] + " : </b>"
					+ Speller.spellCheck(urlArray[i]).toString() + "<br /> <br />";
		}

		writer.println(
				"<html> <head> <title>Результат проверки</title></head>" + "<body>" + result + "</body>" + "</html>");

		/*
		 * writer.println("<html> <head> <title>Результат проверки</title></head>" +
		 * "<body>" + Speller.spellCheck(url) + "</body>" + "</html>");
		 */

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// doGet(request, response);
	}

}
