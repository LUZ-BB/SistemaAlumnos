package mx.edu.utez.sistemaalumnos.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.sistemaalumnos.model.Alumno;
import mx.edu.utez.sistemaalumnos.model.dao.AlumnoDao;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AlumnoServlet", value = "/alumno")
public class AlumnoServlet extends HttpServlet {

    private final AlumnoDao AlumnoDao = new AlumnoDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Alumno> lista = AlumnoDao.getAll();
        request.setAttribute("listaAlumnos", lista);
        request.getRequestDispatcher("gestion-alumnos.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("especie");
            int edad = Integer.parseInt(request.getParameter("edad"));
            String matricula = request.getParameter("matricula");
            String correo = request.getParameter("corrreo");
            String sexo = request.getParameter("sexo");

            Alumno nuevaAlumno = new Alumno();
            nuevaAlumno.setNombre(nombre);
            nuevaAlumno.setApellido(apellido);
            nuevaAlumno.setEdad(edad);
            nuevaAlumno.setMatricula(matricula);
            nuevaAlumno.setCorreo(correo);
            nuevaAlumno.setSexo(sexo);

            AlumnoDao.create(nuevaAlumno);
        } catch (NumberFormatException e) {
            System.err.println("Error al transformar datos numéricos en el registro: " + e.getMessage());
            e.printStackTrace();
        }

        response.sendRedirect("alumno");
    }
}