package mx.edu.utez.sistemaalumnos.model.dao;

import mx.edu.utez.sistemaalumnos.model.Alumno;
import mx.edu.utez.sistemaalumnos.utils.SQLConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlumnoDao implements Dao<Alumno, Integer>{
    @Override
    public boolean create(Alumno entidad) {
        String sql = "INSERT INTO ALUMNO(nombre, apellido, edad, matricula, correo, sexo) VALUES( ?, ?, ?, ?, ?, ?)";
        try (Connection con = SQLConnector.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, entidad.getNombre());
            ps.setString(2, entidad.getApellido());
            ps.setInt(3, entidad.getEdad());
            ps.setString(4, entidad.getMatricula());
            ps.setString(5, entidad.getCorreo());
            ps.setString(6, entidad.getSexo() );

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Alumno> getAll() {
        List<Alumno> datos = new ArrayList<>();
        try (Connection con = SQLConnector.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM ALUMNO");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Alumno a = new Alumno();
                a.setIdAlumno(rs.getInt("id_alumno"));
                a.setNombre(rs.getString("nombre"));
                a.setApellido(rs.getString("apellido"));
                a.setEdad(rs.getInt("edad"));
                a.setMatricula(rs.getString("matricula"));
                a.setCorreo(rs.getString("correo"));
                a.setSexo(rs.getString("sexo"));
                datos.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datos;
    }

    @Override
    public Alumno getById(Integer id) {
        String sql = "SELECT * FROM ALUMNO WHERE id_alumno = ?";
        try (Connection con = SQLConnector.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Alumno a = new Alumno();
                    a.setIdAlumno(rs.getInt("id"));
                    a.setNombre(rs.getString("nombre"));
                    a.setApellido(rs.getString("apellido"));
                    a.setEdad(rs.getInt("edad"));
                    a.setMatricula(rs.getString("matricula"));
                    a.setCorreo(rs.getString("correo"));
                    a.setSexo(rs.getString("sexo"));
                    return a;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(Alumno entidad) {
        String sql = "UPDATE ALUMNO SET nombre = ?, apellido = ?, edad = ?, mattricula = ?, correo = ?, sexo = ? WHERE id_alumno = ?";
        try (Connection con = SQLConnector.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {


            ps.setString(2, entidad.getApellido());
            ps.setInt(3, entidad.getEdad());
            ps.setString(4, entidad.getMatricula());
            ps.setString(5, entidad.getCorreo());
            ps.setString(6, entidad.getSexo() );
            ps.setInt(7, entidad.getIdAlumno());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM ALUMNO WHERE id_alumno = ?";
        try (Connection con = SQLConnector.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
