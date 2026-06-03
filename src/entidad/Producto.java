package entidad;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "producto")
public class Producto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String nombre;
    private int cantidad_disponible;
    private float precio;
    
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
    
    @ManyToOne
    @JoinColumn(name = "medida_id")
    private Medida medida;

    public Producto() {}

    public Producto(int id, String nombre, int cantidad_disponible, float precio, Categoria categoria, Medida medida) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad_disponible = cantidad_disponible;
        this.precio = precio;
        this.categoria = categoria;
        this.medida = medida;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public int getCantidad_disponible() { return cantidad_disponible; }
    public void setCantidad_disponible(int cantidad_disponible) { this.cantidad_disponible = cantidad_disponible; }
    public float getPrecio() { return precio; }
    public void setPrecio(float precio) { this.precio = precio; }
    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }
    public Medida getMedida() { return medida; }
    public void setMedida(Medida medida) { this.medida = medida; }
}