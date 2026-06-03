package entidad;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "usuario")
public class Usuario {
    
    @Id
    private int id; // Sin GeneratedValue porque se ingresa la cédula manual
    
    private String nombre;
    private String contraseña;
    private int edad;
    private boolean enSesion;
    
    public Usuario() {}

    public Usuario(int id, String nombre, String contraseña, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.edad = edad;
        this.enSesion = false;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getContraseña() { return contraseña; }
    public void setContraseña(String contraseña) { this.contraseña = contraseña; }
    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }
    public boolean isEnSesion() { return enSesion; }
    public void setEnSesion(boolean enSesion) { this.enSesion = enSesion; }
}