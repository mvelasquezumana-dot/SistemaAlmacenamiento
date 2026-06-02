package entidad;
import java.util.List;

public class Cliente extends Usuario {
    private List<Compra> historial_compras;

    public Cliente(int id, String nombre, String contraseña, int edad, List<Compra> historial_compras) {
        super(id, nombre, contraseña, edad);
        this.historial_compras = historial_compras;
    }

    public List<Compra> getHistorial_compras() { return historial_compras; }
    public void setHistorial_compras(List<Compra> historial_compras) { this.historial_compras = historial_compras; }
}	