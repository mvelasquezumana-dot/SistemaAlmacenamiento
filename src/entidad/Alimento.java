package entidad;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "alimento")
public class Alimento extends Producto {
    
    @Temporal(TemporalType.DATE)
    private Date fecha_vencimiento;
    
    private String origen;

    public Alimento() {}

    public Alimento(int id, String nombre, int cantidad_disponible, float precio, Categoria categoria, Medida medida, Date fecha_vencimiento, String origen) {
        super(id, nombre, cantidad_disponible, precio, categoria, medida);
        this.fecha_vencimiento = fecha_vencimiento;
        this.origen = origen;
    }

    public Date getFecha_vencimiento() { return fecha_vencimiento; }
    public void setFecha_vencimiento(Date fecha_vencimiento) { this.fecha_vencimiento = fecha_vencimiento; }
    public String getOrigen() { return origen; }
    public void setOrigen(String origen) { this.origen = origen; }
}