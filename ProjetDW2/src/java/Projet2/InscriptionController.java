package Projet2;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InscriptionController
 */
@WebServlet("/Inscription")
public class InscriptionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InscriptionController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		String mail = (String) request.getParameter("repId");
		String pw = (String) request.getParameter("mdpIns");
		String pw2 = (String) request.getParameter("mdpvIns");
		
		if(pw.length() < 7 || !pw.equals(pw2)) {
			request.setAttribute("erreur", 1);
			request.getRequestDispatcher("Inscription.html").forward(request, response);
		}
		
		Connect c = new Connect();
		c.nouvUtil(mail, pw);
		request.setAttribute("erreur", 0);

		request.getRequestDispatcher("menu.html").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}