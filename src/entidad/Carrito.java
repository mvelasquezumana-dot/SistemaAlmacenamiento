package entidad;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "carrito")
public class Carrito {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "carrito_id") // Clave foránea en la tabla producto_carrito
    private List<ProductoCarrito> productos; 
    
    private float valorTotal;
    
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Carrito() {}

    public Carrito(int id, List<ProductoCarrito> productos, Usuario usuario) {
        this.id = id;
        this.productos = productos;
        this.usuario = usuario;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public List<ProductoCarrito> getProductos() { return productos; }
    public void setProductos(List<ProductoCarrito> productos) { this.productos = productos; }
    public float getValor_total() { return valorTotal; }
    public void setValor_total(float valor_total) { this.valorTotal = valor_total; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}