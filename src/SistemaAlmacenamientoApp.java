/**
 * 
 */

import entidad.Categoria;
import entidad.Medida;
import entidad.Producto;
import entidad.ProductoCarrito;
import entidad.Recipiente;
import entidad.Usuario;
import infraestructura.BdConnection;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import servicio.definicion.CarritoServiceDefinition;
import servicio.definicion.ProductServiceDefinition;
import servicio.definicion.UsuarioServiceDefinition;
import servicio.implementacion.CarritoServiceImplementation;
import servicio.implementacion.ProductoServiceImplementation;
import servicio.implementacion.UsuarioServiceImplementation;

import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Date;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import entidad.Alimento;
import entidad.Carrito;

/**
 * 
 */
public class SistemaAlmacenamientoApp {
	public static void main(String args[]) {
	
		Categoria carne = new Categoria("CARNE", "ROJO");
		Categoria pescado = new Categoria("PESCADO", "AZUL");
		Categoria aves = new Categoria("AVES", "AMARILLO");
		Categoria empaques = new Categoria("Empaques", "Blanco");

		Producto p1 = new Producto(0, "Pechuga de pollo", 24, 24500, aves, null);
		Producto p2 = new Producto(1, "Alas de pollo", 12, 12400, aves, null);
	
		Producto p3 = new Producto(2, "Lomo de res", 43, 44500, carne, null);
		Producto p4 = new Producto(3, "Pavo", 32, 22400, aves, null);
		
		Alimento salchicha = new Alimento(1, "Salchicha Ranchera", 12, 8500, carne, null, null, "Ranchera S.A");
		Producto salchicha1 = salchicha;
		Alimento huevos = new Alimento(2, "Panal Huevos AAA", 22, 18500, aves, null, null, "Huevos felices S.A");
		Alimento salchichon = new Alimento(10, "Salchichon cervezero", 42, 10500, carne, null, null, "Ranchera S.A");
		
		// SE CREA UN USUARIO
		Usuario pepe = new Usuario(1117511000, "Pepe", "PePe09@", 23);
		UsuarioServiceDefinition usuarioService = new UsuarioServiceImplementation();
		usuarioService.iniciarSesion(pepe);
		
		// SE CREA UN CARRITO 
		Carrito carritoPepe = new Carrito(12, new ArrayList<ProductoCarrito>(), pepe);
		
		CarritoServiceDefinition carritoService = new CarritoServiceImplementation(pepe);
		carritoService.agregarProducto(salchicha, 4, carritoPepe);
		carritoService.agregarProducto(p1, 12, carritoPepe);
		carritoService.agregarProducto(p2, 12, carritoPepe);
		carritoService.agregarProducto(p3, 7, carritoPepe);
		carritoService.agregarProducto(p4, 4, carritoPepe);
		carritoService.agregarProducto(huevos, 2, carritoPepe);
		carritoService.agregarProducto(salchichon, 22, carritoPepe);
		carritoService.calcularTotal(carritoPepe);
		imprimirCarrito(carritoPepe);
		
		carritoService.removerProducto(3, carritoPepe);
		
		carritoService.calcularTotal(carritoPepe);
		
		Categoria nuevaCategoria = new Categoria("Prueba service", "verde");
		
		ProductServiceDefinition productoService = new ProductoServiceImplementation();
		
		productoService.agregarCategoria(nuevaCategoria);
		
		/*BdConnection<Categoria> bdCat = new BdConnection<Categoria>();
		
		EntityManager em = bdCat.connect();
		bdCat.save(nuevaCategoria, em);*/
		
		//imprimirCarrito(carritoPepe);
		
		/*EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
        EntityManager em = emf.createEntityManager();
		
        
        
        try {
            em.getTransaction().begin();
            
            // Aquí se genera el INSERT
            em.persist(nuevaCategoria); 
            
            em.getTransaction().commit();
            System.out.println("¡Categoría guardada con éxito!");
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }*/
        
SwingUtilities.invokeLater(() -> {
            
            // 1. Create the desktop window frame
            JFrame frame = new JFrame("Sistema de Almacenamiento - Inventario");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 350);
            frame.setLayout(new FlowLayout());

            // 2. Create the UI components
            JLabel label = new JLabel("Presiona el botón para cargar datos de la BD:");
            JButton button = new JButton("Consultar Inventario");
            
            // A text area to display database results (Rows, Columns)
            JTextArea txtArea = new JTextArea(10, 30);
            txtArea.setEditable(false); // Prevents the user from typing inside it
            JScrollPane scrollPane = new JScrollPane(txtArea); // Adds scrollbars if text is long

            // 3. Button Click Action using Java 21 Lambda Syntax
            button.addActionListener(e -> {
                try {
                    txtArea.setText("¡Conectando a PostgreSQL via Hibernate...\n");
                    
                    // --- UNCOMMENT AND ADJUST THIS WHEN UNIFYING WITH YOUR HIBERNATE CODE ---
                    // EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
                    // var em = emf.createEntityManager();
                    // var lista = em.createQuery("FROM Producto", Producto.class).getResultList();
                    //
                    // txtArea.append("¡Datos cargados con éxito!\n");
                    // for (var producto : lista) {
                    //     txtArea.append("- " + producto.getNombre() + " (Stock: " + producto.getCantidad() + ")\n");
                    // }
                    // ------------------------------------------------------------------------
                    
                    txtArea.append("Simulación: Conexión exitosa a la base de datos 'Inventario'.");
                    
                } catch (Exception ex) {
                    txtArea.setText("Error al conectar a la base de datos:\n" + ex.getMessage());
                    ex.printStackTrace();
                }
            });

            // 4. Add components to the frame layout
            frame.add(label);
            frame.add(button);
            frame.add(scrollPane);
            
            // Center the window on your computer screen and make it visible
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    
    }
	
	
	public static void imprimirCarrito(Carrito carrito) {
		System.out.println("Productos del carrito de " + carrito.getUsuario().getNombre());
		for(ProductoCarrito p : carrito.getProductos()) {
			System.out.println("Producto: " + p.getProducto().getNombre() + " Cantidad: " + p.getCantidad()); 
			}
		System.out.println("Valor: " + carrito.getValor_total());
		
	}
}