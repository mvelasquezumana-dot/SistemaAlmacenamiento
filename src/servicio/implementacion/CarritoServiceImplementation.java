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
		for(ProductoCarrito pc : carrito.getProductos()) {
			total = total + pc.getProducto().getPrecio();
		}
		carrito.setValor_total(total);
	}


}
