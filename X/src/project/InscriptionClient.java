package project;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class InscriptionClient
 */
@WebServlet("/InscriptionClient")
public class InscriptionClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InscriptionClient() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public String nomRecu=null, motPasseRecu=null;
	public static String nomCookie=null;
	public String motPasseCookie=null;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 // initialisation cookies et paramètres
		 nomRecu = request.getParameter("nom");
		 motPasseRecu = request.getParameter("motdepasse");
		 Cookie c1;
		 response.setContentType("text/html");
		 PrintWriter out = response.getWriter();

		 if (nomCookie==null && nomRecu==null){
		 // Cas 1 : cas où il n'y a ni de cookies ni de parametres
		 out.println("<html>");
		 out.println("<body>");
		 out.println("<head>");
		 out.println("<title> inscription d'un client </title>");
		 out.println("</head>");
		 out.println("<body bgcolor='white' >");
		 out.println( nomRecu +" | "+ motPasseRecu +" | "+ nomCookie +" | "+ motPasseCookie );
		 out.println("<h3>" + "Bonjour, vous devez vous inscrire " + "</h3>");
		 out.println("<h3>" + "Attention mettre nom et le mot de passe avec plus de 3 caracteres" + "</h3>");
		 out.print(" <form action='InscriptionClient' method='GET' > ");
		 out.println("nom");
		 out.println("<input type='text' size='20' name='nom' >");
		 out.println("<br>");
		 out.println("mot de passe");
		 out.println("<input type='password' size='20' name='motdepasse'> <br>");
		 out.println("<input type='submit' value='inscription'>");
		 out.println("</form>");
		 out.println("</body>");
		 out.println("</html>");
		 } else if (nomCookie==null && nomRecu!=null){

		 // Cas 2 : cas où il n'y a pas de cookies mais les paramètres nom et mot de passes sont présents :
			 
			nomCookie=nomRecu;
			motPasseCookie=motPasseRecu;
			c1= new Cookie(nomCookie,motPasseCookie);
			//c1.setMaxAge(300);
			response.addCookie(c1);
			response.setContentType("text/html"); 
			out.println("<html>");
			out.println("<body>");
			out.println("<head>");
			out.println("<title> case2 </title>");
			out.println("</head>");
			out.println("<body bgcolor='white' >");
			out.println( nomRecu +" | "+ motPasseRecu +" | "+ nomCookie +" | "+ motPasseCookie ); 
			out.print(" <form action='InscriptionClient' method='GET' > ");
			out.println("cookie enregistrer");
			out.println("<input type='submit' value='next'>"); 
			out.println("</form>");
			out.println("</body>");
			out.println("</html>");
				
		 }
		
		 else if (identique(nomRecu,nomCookie) && identique(motPasseRecu,motPasseCookie))
		 {
			 RequestDispatcher rd = request.getRequestDispatcher("AfficherLesDisques");
			 rd.forward(request,response);

		 }
		 else {
			 out.println("<html>");
			 out.println("<body>");
			 out.println("<head>");
			 out.println("<title> coonexion du client </title>");
			 out.println("</head>");
			 out.println("<body bgcolor='white' >");
			 out.println( nomRecu +" | "+ motPasseRecu +" | "+ nomCookie +" | "+ motPasseCookie );
			 out.println("<h3>" + "Bonjour, vous devez vous connecter " + "</h3>");
			 
			 out.print(" <form action='InscriptionClient' method='GET' > ");
			 out.println("nom");
			 out.println("<input type='text' size='20' name='nom' >");
			 out.println("<br>");
			 out.println("mot de passe");
			 out.println("<input type='password' size='20' name='motdepasse'> <br>");
			 out.println("<input type='submit' value='inscription'>");
			 out.println("</form>");
			 out.println("</body>");
			 out.println("</html>");

		 }
		 }
	

	boolean identique (String recu, String cookie) {
		 return ((recu != null) && (recu.length() >3) && (cookie != null) && (recu.equals(cookie) ));
		 }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
