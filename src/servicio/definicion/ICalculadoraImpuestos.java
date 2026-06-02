package servicio.definicion;

import entidad.Producto;

public interface ICalculadoraImpuestos {
    // Este es el método que la clase abstracta va a implementar
    float calcularImpuestos(Producto producto);
}