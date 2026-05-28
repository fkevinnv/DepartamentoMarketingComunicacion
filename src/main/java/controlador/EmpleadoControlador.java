package controlador;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmpleadoDAO;
import modelo.Empleado;

@WebServlet("/EmpleadoControlador")
public class EmpleadoControlador extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EmpleadoControlador() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String opcion = request.getParameter("opcion");
        EmpleadoDAO dao = new EmpleadoDAO();

        if (opcion == null) {
            opcion = "";
        }

        if (opcion.equals("listar")) {
            ArrayList<Empleado> lista = dao.obtenerTodos();
            request.setAttribute("empleados", lista);
            request.getRequestDispatcher("/vistas/gestionEmpleados.jsp").forward(request, response);

        } else {
            response.sendRedirect(request.getContextPath() + "/vistas/portalAdmin.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String opcion = request.getParameter("opcion");
        EmpleadoDAO dao = new EmpleadoDAO();

        if (opcion == null) {
            opcion = "";
        }

        if (opcion.equals("actualizarRol")) {
            int id = Integer.parseInt(request.getParameter("id"));
            String rol = request.getParameter("rol");
            dao.actualizarRol(id, rol);
            response.sendRedirect(request.getContextPath() + "/EmpleadoControlador?opcion=listar");

        } else if (opcion.equals("eliminar")) {
            int id = Integer.parseInt(request.getParameter("id"));
            dao.eliminar(id);
            response.sendRedirect(request.getContextPath() + "/EmpleadoControlador?opcion=listar");

        } else {
            response.sendRedirect(request.getContextPath() + "/vistas/portalAdmin.jsp");
        }
    }
}