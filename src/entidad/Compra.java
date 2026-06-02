package entidad;

public class Compra {
    private int id;
    private Carrito carrito;
    private String direccion;
    private String metodo_pago;

    public Compra(int id, Carrito carrito, String direccion, String metodo_pago) {
        this.id = id;
        this.carrito = carrito;
        this.direccion = direccion;
        this.metodo_pago = metodo_pago;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Carrito getCarrito() { return carrito; }
    public void setCarrito(Carrito carrito) { this.carrito = carrito; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getMetodo_pago() { return metodo_pago; }
    public void setMetodo_pago(String metodo_pago) { this.metodo_pago = metodo_pago; }
}
