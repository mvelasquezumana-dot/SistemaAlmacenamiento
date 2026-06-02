package servicio.implementacion;

import java.util.ArrayList;

import entidad.Carrito;
import entidad.Producto;
import entidad.ProductoCarrito;
import entidad.Usuario;
import servicio.definicion.CarritoServiceDefinition;

public class CarritoServiceImplementation implements CarritoServiceDefinition{
	
	private Usuario usuario;


	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public CarritoServiceImplementation(Usuario usuario) {
		this.setUsuario(usuario);
	}

	@Override
	public void agregarProducto(Producto producto, int cantidad, Carrito carrito) {
		 
		if(carrito.getProductos()==null) {
			carrito.setProductos(new ArrayList<ProductoCarrito>());
		}
		 
		ProductoCarrito productoCarrito = new ProductoCarrito(producto.getId(), producto, cantidad);
		carrito.getProductos().add(productoCarrito);
		
	}

	@Override
	public void removerProducto(int productoId, Carrito carrito) {
		
		if(carrito.getProductos()==null) {
			return;
		}
		
		for(ProductoCarrito pc : carrito.getProductos()) {
			
			if(pc.getId() == productoId) {	
				carrito.getProductos().remove(pc);
				break;
			}
		}
		
		
	}

	@Override
	public void calcularTotal(Carrito carrito) {
		float total = 0;
		
		// 1. Instanciamos el gestor que creamos en el Paso 1
		GestorFacturacion gestorFinanciero = new GestorFacturacion();

		for(ProductoCarrito pc : carrito.getProductos()) {
			Producto producto = pc.getProducto();
			int cantidad = pc.getCantidad();
			
			// Opcional: Podrías aplicar un descuento antes de los impuestos
			// gestorFinanciero.aplicarDescuento(producto, 10); // Ejemplo: 10% de descuento
			
			// 2. Calculamos el impuesto para este producto específico
			float valorImpuesto = gestorFinanciero.calcularImpuestos(producto);
			
			// 3. Calculamos el precio real (Precio base + Impuesto)
			float precioConImpuesto = producto.getPrecio() + valorImpuesto;
			
			// 4. Sumamos al total multiplicando por la cantidad de productos (¡Importante!)
			total = total + (precioConImpuesto * cantidad);
		}
		
		carrito.setValor_total(total);
	}

}
