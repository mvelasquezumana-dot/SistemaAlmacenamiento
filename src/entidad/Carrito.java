package entidad;
import java.util.List;

public class Carrito {
    private int id;
    private List<ProductoCarrito> productos; 
    private float valorTotal;
    private Usuario usuario;

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
