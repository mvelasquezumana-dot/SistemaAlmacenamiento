package entidad;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "cliente")
public class Cliente extends Usuario {
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id")
    private List<Compra> historial_compras;

    public Cliente() {}

    public Cliente(int id, String nombre, String contraseña, int edad, List<Compra> historial_compras) {
        super(id, nombre, contraseña, edad);
        this.historial_compras = historial_compras;
    }

    public List<Compra> getHistorial_compras() { return historial_compras; }
    public void setHistorial_compras(List<Compra> historial_compras) { this.historial_compras = historial_compras; }
}