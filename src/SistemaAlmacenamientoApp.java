import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import entidad.Alimento;
import entidad.Carrito;
import entidad.Categoria;
import entidad.Producto;
import entidad.ProductoCarrito;
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
    
    /**
     * Método para inicializar datos falsos y probar la lógica de servicios
     * (Ideal para fase de desarrollo).
     */
    private static void ejecutarPruebasLogica() {
        System.out.println("--- Ejecutando Pruebas de Lógica de Negocio ---");
        
        // --- 1. CREACIÓN DE CATEGORÍAS Y PRODUCTOS ---
        Categoria carne = new Categoria("CARNE", "ROJO");
        Categoria pescado = new Categoria("PESCADO", "AZUL");
        Categoria aves = new Categoria("AVES", "AMARILLO");

        Producto p1 = new Producto(0, "Pechuga de pollo", 24, 24500, aves, null);
        Producto p2 = new Producto(1, "Alas de pollo", 12, 12400, aves, null);
        Producto p3 = new Producto(2, "Lomo de res", 43, 44500, carne, null);
        Producto p4 = new Producto(3, "Pavo", 32, 22400, aves, null);
        Producto p5 = new Producto(3, "trucha", 32, 22400, pescado, null);
        
        Alimento salchicha = new Alimento(1, "Salchicha Ranchera", 12, 8500, carne, null, null, "Ranchera S.A");
        Alimento huevos = new Alimento(2, "Panal Huevos AAA", 22, 18500, aves, null, null, "Huevos felices S.A");
        Alimento salchichon = new Alimento(10, "Salchichon cervezero", 42, 10500, carne, null, null, "Ranchera S.A");
        
        // --- 2. GESTIÓN DE USUARIO ---
        Usuario pepe = new Usuario(1117511000, "Pepe", "PePe09@", 23);
        UsuarioServiceDefinition usuarioService = new UsuarioServiceImplementation();
        usuarioService.iniciarSesion(pepe);
        
        // --- 3. LÓGICA DEL CARRITO ---
        Carrito carritoPepe = new Carrito(12, new ArrayList<ProductoCarrito>(), pepe);
        CarritoServiceDefinition carritoService = new CarritoServiceImplementation(pepe);
        
        carritoService.agregarProducto(salchicha, 4, carritoPepe);
        carritoService.agregarProducto(p1, 12, carritoPepe);
        carritoService.agregarProducto(p2, 12, carritoPepe);
        carritoService.agregarProducto(p3, 7, carritoPepe);
        carritoService.agregarProducto(p4, 4, carritoPepe);
        carritoService.agregarProducto(p5, 4, carritoPepe);
        carritoService.agregarProducto(huevos, 2, carritoPepe);
        carritoService.agregarProducto(salchichon, 22, carritoPepe);
        
        carritoService.calcularTotal(carritoPepe);
        imprimirCarrito(carritoPepe);
        
        // Remover un producto y recalcular
        carritoService.removerProducto(3, carritoPepe);
        carritoService.calcularTotal(carritoPepe);
        System.out.println("\nDespués de remover el producto 3:");
        imprimirCarrito(carritoPepe);
        
        // --- 4. PERSISTENCIA EN BASE DE DATOS ---
        Categoria nuevaCategoria = new Categoria("Prueba service", "verde");
        ProductServiceDefinition productoService = new ProductoServiceImplementation();
        // Nota: Asegúrate de tener la BD levantada, si no, esto lanzará excepción de conexión.
        try {
            productoService.agregarCategoria(nuevaCategoria);
            System.out.println("Categoría agregada a la BD exitosamente.");
        } catch (Exception e) {
            System.err.println("Aviso: No se pudo conectar a la base de datos para guardar la categoría.");
        }
    }
    
    /**
     * Método dedicado a configurar e iniciar la interfaz gráfica en Swing.
     */
    private static void iniciarInterfazGrafica() {
        SwingUtilities.invokeLater(() -> {
            
            JFrame frame = new JFrame("Sistema de Almacenamiento - Inventario");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 350);
            frame.setLayout(new FlowLayout());

            JLabel label = new JLabel("Presiona el botón para cargar datos de la BD:");
            JButton button = new JButton("Consultar Inventario");
            
            JTextArea txtArea = new JTextArea(10, 30);
            txtArea.setEditable(false); 
            JScrollPane scrollPane = new JScrollPane(txtArea); 

            button.addActionListener(e -> {
                try {
                    txtArea.setText("¡Conectando a PostgreSQL via Hibernate...\n");
                    // Aquí iría tu lógica real de consulta con el EntityManager
                    txtArea.append("Simulación: Conexión exitosa a la base de datos 'Inventario'.");
                } catch (Exception ex) {
                    txtArea.setText("Error al conectar a la base de datos:\n" + ex.getMessage());
                    ex.printStackTrace();
                }
            });

            frame.add(label);
            frame.add(button);
            frame.add(scrollPane);
            
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
	
    /**
     * Utilidad para mostrar el estado actual del carrito por consola.
     */
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