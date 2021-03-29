package project;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

/**
 * Servlet implementation class EnregistrerCommande
 */
@WebServlet("/EnregistrerCommande")
public class EnregistrerCommande extends HttpServlet {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Connection connexion=null;
	 Statement stmt=null;
	 PreparedStatement pstmt=null;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	 { String nom = null;
	 int nbreProduit = 0;
	 Cookie[] cookies = request.getCookies();
	 boolean connu = false;
	 nom = Identification.chercheNom(cookies);
	 OuvreBase();
	 AjouteNomBase(nom);

	 response.setContentType("text/html");
	 PrintWriter out = response.getWriter();
	 out.println("<html>");
	 out.println("<body>");
	 out.println("<head>");
	 out.println("<title> votre commande </title>");
	 out.println("</head>");
	 out.println("<body bgcolor=\"white\">");

	 out.println("<h3>" + "Bonjour " + nom + " voici ta nouvelle commande" + "</h3>");
	 HttpSession session = request.getSession();
	 Enumeration names = session.getAttributeNames();
	 while (names.hasMoreElements()) {
	 nbreProduit++;
	 String name = (String) names.nextElement();
	 String value = session.getAttribute(name).toString();
	 out.println(name + " = " + value + "<br>");
	 }
	 AjouteCommandeBase(nom,session);
	 out.println("<h3>" + "et voici " + nom + " ta commande complete" + "</h3>");
	 MontreCommandeBase(nom, out);
	 out.println("<A HREF=vider> Vous pouvez commandez un autre disque </A><br> ");
	 out.println("</body>");
	 out.println("</html>");
	 }
	 protected void OuvreBase() {
	 try {
		 Class.forName("com.mysql.jdbc.Driver");
	// connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/magasin","root","");
		 connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/magasin","root","mysql");
	 connexion.setAutoCommit(true);
	 stmt = connexion.createStatement();
	 }
	 catch (Exception E) {
	 log(" -------- probeme " + E.getClass().getName() );
	 E.printStackTrace();
	 }
	 }
	 protected void fermeBase() {
	 try {
	 stmt.close();
	 connexion.close();
	 }
	 catch (Exception E) {
	 log(" -------- probeme " + E.getClass().getName() );
	 E.printStackTrace();
	 }
	 }
	 protected void AjouteNomBase(String nom) {
		 try {
				pstmt = connexion.prepareStatement("select numero from personnel where nom=?");
				pstmt.setString(1, nom);
				ResultSet resultSet = pstmt.executeQuery();
				if (!resultSet.next()) {
					String query = "INSERT INTO `personnel` (`nom`) VALUES (?)";
					PreparedStatement preparedStatement = connexion.prepareStatement(query);
					preparedStatement.setString(1, nom);
					preparedStatement.executeUpdate();
				}}

		 catch (Exception E) {
		 log(" - probeme " + E.getClass().getName() );
		 E.printStackTrace();
		 }
		 }
		 protected void AjouteCommandeBase(String nom, HttpSession session ) {
		 // ajoute le contenu du panier dans la base
			 try {
					pstmt = connexion.prepareStatement("select numero from personnel where nom=?");
					pstmt.setString(1, nom);
					ResultSet resultSet = pstmt.executeQuery();
					int numeroUser = 0;
					if (resultSet.next()) {
						numeroUser = resultSet.getInt("numero");
					}

					Enumeration<String> names = session.getAttributeNames();
					while (names.hasMoreElements()) {
						String disque = (String) names.nextElement();
						String nomDisque = session.getAttribute(disque).toString();
						String query = "INSERT INTO `commande` (`article`, `qui`) VALUES (?, ?)";
						PreparedStatement preparedStatement = connexion.prepareStatement(query);

						preparedStatement.setString(1, nomDisque);
						preparedStatement.setInt(2, numeroUser);
						preparedStatement.executeUpdate();
					}

				} catch (Exception E) {
					log(" - probeme " + E.getClass().getName());
					E.printStackTrace();
				}
		 }
		 protected void MontreCommandeBase(String nom, PrintWriter out) {
		 // affiche les produits présents dans la base
			 try {
					pstmt = connexion.prepareStatement(
							"SELECT cm.article FROM commande cm inner join personnel pe on pe.numero = cm.qui WHERE pe.nom = ?");
					pstmt.setString(1, nom);
					ResultSet resultSet = pstmt.executeQuery();
					
				
					while (resultSet.next()) {
						out.println( resultSet.getString("article") );
					}
				
				} catch (Exception e) {
					e.printStackTrace();
				}
		 }
		 public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
		 {
		 doGet(request, response);
		 }
		 }
