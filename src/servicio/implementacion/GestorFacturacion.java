package servicio.implementacion;

import entidad.Producto;

public class GestorFacturacion extends GestorFinancieroProducto {

    @Override
    public void registrarTransaccion(Producto producto) {
        // Por ahora puede estar vacío o imprimir un log
        System.out.println("Procesando impuestos para el producto: " + producto.getNombre());
    }
}