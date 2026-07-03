package mx.edu.utez.sistemaalumnos.model;

public class Alumno {
    private int id_alumno;
    private String nombre;
    private String apellido;
    private int edad;
    private String matricula;
    private String correo;
    private String sexo;

    public Alumno() {
    }

    public Alumno(int id, String nombre, String apellido, int edad, String matricula, String correo, String sexo) {
        this.id_alumno = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.matricula = matricula;
        this.correo = correo;
        this.sexo = sexo;
    }

    public int getIdAlumno() {
        return id_alumno;
    }

    public void setIdAlumno(int id) {
        this.id_alumno = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "id=" + id_alumno +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", edad=" + edad +
                ", matricula='" + matricula + '\'' +
                ", correo='" + correo + '\'' +
                ", sexo='" + sexo + '\'' +
                '}';
    }


}
