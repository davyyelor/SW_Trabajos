package shareinfo;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import helper.db.*;

public class LoginServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private MySQLdb mySQLdb;
	private int numConexiones=0;
	private int numConexionesCorrectas=0;
	
	public void init(ServletConfig config) {
		System.out.println("---> Entrando en init()de LoginServlet");
		mySQLdb = new MySQLdb();
		System.out.println("---> Saliendo de init() de LoginServlet");
	}
	
	public void destroy() {
		System.out.println("----> El n�mreo de conexiones ha sido: "+ numConexiones);
		System.out.println("----> El n�mero de conexiones correctas ha sido: "+ numConexionesCorrectas);
	}
 
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    	System.out.println("---> Entrando en doPost() de LoginServlet");
    	numConexiones= numConexiones+1;
    	
    	String email = request.getParameter("email");
		String password = request.getParameter("password");
		System.out.println("     Estrayendo par�metros de la petici�n: " + email + " " + password);

		String username = mySQLdb.getUsername(email, password);
		
		if(username == null) {
			System.out.println("     Login error: redireccionando al usuarioa a loginForm.jsp");
			request.setAttribute("error", "Usuario o contrase�a incorrecto");
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/loginForm.jsp");
			rd.forward(request, response);
		} else {
			HttpSession session = request.getSession(true);
			String sessionID = session.getId();
			
			session.setAttribute("username", username);
			
			System.out.println("     Sesion de Usuario para " + username + ": " + sessionID);
			System.out.print("     Cogiendo la lista de usuarios activos de contexto: ");
			numConexionesCorrectas = numConexionesCorrectas+1;
			ServletContext context = request.getServletContext();
			HashMap<String, String> loggedinUsers = (HashMap) context.getAttribute("loggedin_users");
			if(loggedinUsers == null) {
				System.out.println("Lista vacia");
				loggedinUsers = new HashMap();
				loggedinUsers.put(username, sessionID);
			} else {
				if(!loggedinUsers.containsKey(username)) {
					System.out.println(username + " no esta en la lista");
					loggedinUsers.put(username, sessionID);
				} else {
					System.out.println(username + " ya esta en la lista");
				}
			}
			context.setAttribute("loggedin_users", loggedinUsers);
			System.out.println("     Loggedin users: " + loggedinUsers.toString());
			
			System.out.println("     Redireccionar al usuario al MainServlet");
			RequestDispatcher rd = context.getNamedDispatcher("MainServlet");
			rd.forward(request, response);
		}
		
		System.out.println("---> Saliendo de doPost() en LoginServlet");
		destroy();
    }
}

