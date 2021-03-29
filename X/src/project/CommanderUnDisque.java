package project;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;






/**
 * Servlet implementation class CommanderUnDisque
 */
@WebServlet("/CommanderUnDisque")
public class CommanderUnDisque extends HttpServlet {
	 /**
	 * 
	 */
	ArrayList<Panier> list = new ArrayList<Panier>();
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request,
	 HttpServletResponse response)
	throws IOException, ServletException
	{ String nom = null;
	int nbreProduit = 0;
	HttpSession session=request.getSession();
	Cookie[] cookies = request.getCookies();
	nom = Identification.chercheNom(cookies);
	 response.setContentType("text/html");
	 PrintWriter out = response.getWriter();
	 out.println("<html>");
	 out.println("<body>");
	 out.println("<head>");
	 out.println("<title> votre commande </title>");
	 out.println("</head>");
	 out.println("<body bgcolor=\"white\">");
	 out.println("<h3>" + "Bonjour "+ nom + " voici votre commande" + "</h3>");
	 
	
	 Panier panier= new Panier(request.getParameter("code"),request.getParameter("prix"));
	 nbreProduit=list.size()+1;
	 session.setAttribute("Disque numero "+nbreProduit, panier);
	 //session.removeAttribute("code");
	// affichage de tous les disques présents dans le panier (éléments de la session)
	 out.println("<h2>" + "Affichage des disque de la session " + "</h2>");
	 Enumeration<String> attributes = session.getAttributeNames();

	 while (attributes.hasMoreElements()) {
	     String attribute = (String) attributes.nextElement();
	    out.println(attribute+" : "+session.getAttribute(attribute)+"<br>");
	 }
	// si parametre ordre == ajouter affichage du disque à ajouter au panier
	 
	 out.println("<h2>" + "Affichage de disque qu'on vient d'ajouter" + "</h2>");
	 if(request.getParameter("ordre").equals("ajouter")) {
		 
		 out.println("Disque numero "+nbreProduit+" : "+session.getAttribute("Disque numero "+nbreProduit)+"<br>");
	 }
	// ajout du nouveau disque dans le panier
	 
     list.add(panier);
	 
	
	out.println("<A HREF=achat> Vous pouvez commandez un autre disque </A><br> ");
	 out.println("<A HREF=enregistre> Vous pouvez enregistrer votre commande </A><br> ");
	 out.println("</body>");
	 out.println("</html>");
	}
	public void doPost(HttpServletRequest request,
	 HttpServletResponse response)
	throws IOException, ServletException
	 {
	 doGet(request, response);
	 }
	}

