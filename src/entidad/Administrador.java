package entidad;

import jakarta.persistence.*;

@Entity
@Table(name = "administrador")
public class Administrador extends Usuario {
    
    private String permisos;

    public Administrador() {}

    public Administrador(int id, String nombre, String contraseña, int edad, String permisos) {
        super(id, nombre, contraseña, edad);
        this.permisos = permisos;
    }

    public String getPermisos() { return permisos; }
    public void setPermisos(String permisos) { this.permisos = permisos; }
}