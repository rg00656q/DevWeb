

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SessionController
 */
@WebServlet("/Session")
public class SessionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SessionController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Connect c = new Connect();
		String mail = (String) request.getAttribute("id");
		String pw = (String) request.getAttribute("mdp");
		
		if(c.connection(mail, pw) == -1) {
			out.print("<p> Utilisateur introuvable </p>");
			request.getRequestDispatcher("menu.html").forward(request, response);
		}
		else {
			request.setAttribute("mail", mail);
			request.setAttribute("pw", pw);
			request.setAttribute("connection", c);
			request.getRequestDispatcher("site.html").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
