/*Nombre y Apellidos : David Elorza Gabilondo
  Asignatura y Grupo : Sistemas Web, Grupo 1
  Fecha de Entrega : 08/05//2023
  Nombre de la Tarea : Entrega Práctica 3-Gestor Imágenes Svg
  Descripción : Script .java con el servlet para eliminar una coleccion
*/

package servlets;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import HTTPeXist.HTTPeXist;

public class DeleteCollection extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private HTTPeXist eXist;

    public void init() {
        System.out.println("---> Entrando en init() de DeleteCollection");
        eXist = new HTTPeXist("http://localhost:8080");
        System.out.println("---> Saliendo de init() de DeleteCollection");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String collection = request.getParameter("collection");
        int status = eXist.delete(collection);
        if (status == HttpURLConnection.HTTP_OK) {
            request.setAttribute("informacion", "Colección eliminada exitosamente.");
        } else {
            request.setAttribute("informacion", "No se pudo eliminar la colección.");
        }
        request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
