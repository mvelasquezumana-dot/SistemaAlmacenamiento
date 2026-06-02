package servicio.definicion;

import entidad.Carrito;
import entidad.Producto;

public interface CarritoServiceDefinition {
	public void agregarProducto(Producto producto, int cantidad, Carrito carrito);
	public void removerProducto(int productoId, Carrito carrito);
	public void calcularTotal(Carrito carrito);
}
