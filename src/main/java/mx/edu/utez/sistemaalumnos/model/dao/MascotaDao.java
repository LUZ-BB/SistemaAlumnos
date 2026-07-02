package mx.edu.utez.sistemaalumnos.model.dao;

import mx.edu.utez.sistemaalumnos.model.Alumno;
import mx.edu.utez.sistemaalumnos.utils.SQLConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MascotaDao implements Dao<Alumno, Integer>{
    @Override
    public boolean create(Alumno entidad) {
        String sql = "INSERT INTO MASCOTAS(nombre, especie, edad, personalidad, foto, vacunada) VALUES(?, ?, ?, ?, ?, ?)";
        try (Connection con = SQLConnector.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, entidad.getNombre());
            ps.setString(2, entidad.getEspecie());
            ps.setInt(3, entidad.getEdad());
            ps.setString(4, entidad.getPersonalidad());
            ps.setString(5, entidad.getFoto());
            ps.setInt(6, entidad.isVacunada() ? 1 : 0);

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
             PreparedStatement ps = con.prepareStatement("SELECT * FROM MASCOTAS");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Alumno m = new Alumno();
                m.setId(rs.getInt("id"));
                m.setNombre(rs.getString("nombre"));
                m.setEspecie(rs.getString("especie"));
                m.setEdad(rs.getInt("edad"));
                m.setPersonalidad(rs.getString("personalidad"));
                m.setFoto(rs.getString("foto"));
                m.setVacunada(rs.getInt("vacunada") == 1);
                datos.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datos;
    }

    @Override
    public Alumno getById(Integer id) {
        String sql = "SELECT * FROM MASCOTAS WHERE id = ?";
        try (Connection con = SQLConnector.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Alumno m = new Alumno();
                    m.setId(rs.getInt("id"));
                    m.setNombre(rs.getString("nombre"));
                    m.setEspecie(rs.getString("especie"));
                    m.setEdad(rs.getInt("edad"));
                    m.setPersonalidad(rs.getString("personalidad"));
                    m.setFoto(rs.getString("foto"));
                    m.setVacunada(rs.getInt("vacunada") == 1);
                    return m;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(Alumno entidad) {
        String sql = "UPDATE MASCOTAS SET nombre = ?, especie = ?, edad = ?, personalidad = ?, foto = ?, vacunada = ? WHERE id = ?";
        try (Connection con = SQLConnector.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, entidad.getNombre());
            ps.setString(2, entidad.getEspecie());
            ps.setInt(3, entidad.getEdad());
            ps.setString(4, entidad.getPersonalidad());
            ps.setString(5, entidad.getFoto());
            ps.setInt(6, entidad.isVacunada() ? 1 : 0);
            ps.setInt(7, entidad.getId());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM MASCOTAS WHERE id = ?";
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
