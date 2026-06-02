package entidad;

public class Recipiente extends Producto {
    public Recipiente(int id, String nombre, int cantidad_disponible, float precio, Categoria categoria, Medida medida) {
        super(id, nombre, cantidad_disponible, precio, categoria, medida);
    }

}