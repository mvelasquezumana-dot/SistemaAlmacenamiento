import java.util.ArrayList;

import entidad.Alimento;
import entidad.Carrito;
import entidad.Categoria;
import entidad.Medida;
import entidad.Producto;
import entidad.ProductoCarrito;
import entidad.Recipiente;
import entidad.Usuario;
import servicio.definicion.CarritoServiceDefinition;
import servicio.definicion.ProductServiceDefinition;
import servicio.definicion.UsuarioServiceDefinition;
import servicio.implementacion.CarritoServiceImplementation;
import servicio.implementacion.ProductoServiceImplementation;
import servicio.implementacion.UsuarioServiceImplementation;

public class SistemaAlmacenamientoApp {
	
    public static void main(String[] args) {
        // 1. (Opcional) Ejecutar pruebas de lógica de negocio y base de datos
        ejecutarPruebasLogica();
        
     }
    
    private static void ejecutarPruebasLogica() {
        System.out.println("--- Ejecutando Pruebas de Lógica de Negocio y Mapeo JPA ---");
        
        // --- 1. CREACIÓN DE OBJETOS EN MEMORIA ---
        
        // Categorías
        Categoria catCarne = new Categoria("CARNE", "ROJO");
        Categoria catAves = new Categoria("AVES", "AMARILLO");
        Categoria catPescado = new Categoria("PESCADO", "AZUL");
        Categoria catEmpaques = new Categoria("EMPAQUES", "GRIS");

        // Medidas
        Medida kg = new Medida("Kilogramos", 1.0f);
        Medida gramo = new Medida("Gramos", 500.0f);
        Medida unidad = new Medida("Unidades", 1.0f);

        // Productos (Alimentos y Recipientes)
        java.util.Date fechaVencimiento = new java.util.Date(System.currentTimeMillis() + 864000000L); // Vence en 10 días
        
        Alimento p1 = new Alimento(0, "Pechuga de pollo", 24, 24500, catAves, kg, fechaVencimiento, "Avicola del Cafe");
        Alimento p2 = new Alimento(0, "Lomo de res", 43, 44500, catCarne, kg, fechaVencimiento, "Frigorrfico Pereira");
        Alimento p3 = new Alimento(0, "Trucha", 32, 22400, catPescado, gramo, fechaVencimiento, "Pesquera Local");
        Alimento salchicha = new Alimento(0, "Salchicha Ranchera", 12, 8500, catCarne, gramo, fechaVencimiento, "Ranchera S.A");
        
        Recipiente cajaPlastico = new Recipiente(0, "Caja Organizadora 5L", 15, 12000, catEmpaques, unidad);

        // Usuario y Carrito
        Usuario pepe = new Usuario(1117511000, "Pepe", "PePe09@", 23);
        Carrito carritoPepe = new Carrito(0, new java.util.ArrayList<>(), pepe);
        
        servicio.definicion.CarritoServiceDefinition carritoService = new servicio.implementacion.CarritoServiceImplementation(pepe);
        
        // Lógica de llenado y cálculo
        carritoService.agregarProducto(p1, 2, carritoPepe);
        carritoService.agregarProducto(p2, 1, carritoPepe);
        carritoService.agregarProducto(salchicha, 4, carritoPepe);
        carritoService.agregarProducto(cajaPlastico, 5, carritoPepe);
        
        carritoService.calcularTotal(carritoPepe);
        imprimirCarrito(carritoPepe);
        
        
        // --- 2. PERSISTENCIA EN BASE DE DATOS (Mapeo Relacional) ---
        System.out.println("\n--- Iniciando guardado Masivo en Base de Datos ---");
        
        infraestructura.BdConnection<Object> bd = new infraestructura.BdConnection<>();
        jakarta.persistence.EntityManager em = bd.connect();
        
        try {
            // Abrimos UNA SOLA transacción para guardar toda nuestra semilla de datos
            em.getTransaction().begin();
            
            // 1. Guardamos objetos base (sin dependencias)
            em.persist(catCarne);
            em.persist(catAves);
            em.persist(catPescado);
            em.persist(catEmpaques);
            
            em.persist(kg);
            em.persist(gramo);
            em.persist(unidad);
            
            // 2. Guardamos los productos (dependen de categoría y medida)
            em.persist(p1);
            em.persist(p2);
            em.persist(p3);
            em.persist(salchicha);
            em.persist(cajaPlastico);
            
            // 3. Guardamos el Usuario
            em.persist(pepe);
            
            // 4. Guardamos el carrito (Gracias a CascadeType.ALL, guardará automáticamente los ProductoCarrito)
            em.persist(carritoPepe);
            
            em.getTransaction().commit(); // Enviamos los datos a PostgreSQL
            System.out.println("✅ ¡Todas las entidades fueron mapeadas y guardadas exitosamente en PostgreSQL!");
            
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("❌ Error crítico en la persistencia JPA: " + e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();
        }
    }    

public static void imprimirCarrito(Carrito carrito) {
        System.out.println("--------------------------------------------------");
        System.out.println("Productos del carrito de " + carrito.getUsuario().getNombre());
        for(ProductoCarrito p : carrito.getProductos()) {
            System.out.println(" - Producto: " + p.getProducto().getNombre() + " | Cantidad: " + p.getCantidad()); 
        }
        System.out.println("Valor Total Calculado: $" + carrito.getValor_total());
        System.out.println("--------------------------------------------------");
    }
}