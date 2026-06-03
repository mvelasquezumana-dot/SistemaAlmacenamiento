package entidad;

import jakarta.persistence.*;

@Entity
@Table(name = "producto_carrito")
public class ProductoCarrito {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;
    
    private int cantidad;
    
    public ProductoCarrito() {}

    public ProductoCarrito(int id, Producto producto, int cantidad) {
        this.id = id; // Si la BD lo genera, Hibernate sobrescribirá este valor
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
}