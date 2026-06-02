package servicio.implementacion;

import entidad.Producto;
import servicio.definicion.ICalculadoraImpuestos;
import entidad.Alimento;

public abstract class GestorFinancieroProducto implements ICalculadoraImpuestos {

    /**
     * 1. MÉTODO QUE SE HEREDA:
     * Al estar definido aquí, cualquier clase que extienda de GestorFinancieroProducto 
     * heredará automáticamente esta funcionalidad para aplicar descuentos genéricos.
     */
    public void aplicarDescuento(Producto producto, float porcentajeDescuento) {
        if (porcentajeDescuento > 0 && porcentajeDescuento <= 100) {
            float montoDescuento = producto.getPrecio() * (porcentajeDescuento / 100f);
            // Actualiza el precio del producto restando el descuento
            producto.setPrecio(producto.getPrecio() - montoDescuento);
        }
    }

    /**
     * 2. MÉTODO QUE SE IMPLEMENTA:
     * Traído desde la interfaz, aquí calculamos los impuestos según las leyes de Colombia.
     */
    @Override
    public float calcularImpuestos(Producto producto) {
        float impuestoFinal = 0f;

        // Validamos si el producto es un Alimento utilizando las entidades de tu proyecto
        if (producto instanceof Alimento) {
            String categoria = producto.getCategoria().getNombre().toUpperCase();
            
            // Canasta familiar básica (Carne, Pollo, Huevos, Pescado) suelen ser EXENTOS de IVA (0%)
            if (categoria.equals("CARNE") || categoria.equals("AVES") || categoria.equals("PESCADO")) {
                
                // Excepción colombiana: Carnes frías y embutidos (ej. salchichas, salchichón)
                // pueden aplicar IVA del 5% y están sujetos a Impuesto Saludable (ICUI).
                if (producto.getNombre().toLowerCase().contains("salchicha") || 
                    producto.getNombre().toLowerCase().contains("salchichon")) {
                    
                    impuestoFinal = producto.getPrecio() * 0.05f; // Tarifa del 5%
                    
                } else {
                    impuestoFinal = 0f; // 0% de IVA (Ej. Pechuga de pollo o Lomo de res)
                }
            } else {
                // Otros alimentos procesados suelen tener tarifa reducida del 5%
                impuestoFinal = producto.getPrecio() * 0.05f; 
            }
        } else {
            // Para el resto de productos (ej. Empaques, Recipientes u otros objetos no alimenticios)
            // Aplica la tarifa general del IVA en Colombia que es del 19%
            impuestoFinal = producto.getPrecio() * 0.19f;
        }

        return impuestoFinal;
    }
    
    /**
     * Método abstracto adicional opcional: 
     * Como es una clase abstracta, puedes forzar a las clases hijas a que ellas mismas
     * definan cómo procesar ciertas transacciones específicas.
     */
    public abstract void registrarTransaccion(Producto producto);
}