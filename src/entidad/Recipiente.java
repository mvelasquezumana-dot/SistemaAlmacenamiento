package entidad;

import jakarta.persistence.*;

@Entity
@Table(name = "recipiente")
public class Recipiente extends Producto {
    
    public Recipiente() {}

    public Recipiente(int id, String nombre, int cantidad_disponible, float precio, Categoria categoria, Medida medida) {
        super(id, nombre, cantidad_disponible, precio, categoria, medida);
    }
}