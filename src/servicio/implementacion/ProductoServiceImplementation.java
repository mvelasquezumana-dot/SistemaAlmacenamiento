package servicio.implementacion;

import entidad.Categoria;
import infraestructura.BdConnection;
import jakarta.persistence.EntityManager;
import servicio.definicion.ProductServiceDefinition;

public class ProductoServiceImplementation implements ProductServiceDefinition{
	
	private BdConnection<Categoria> bdCat = new BdConnection<Categoria>();
	private EntityManager em;
	
	public ProductoServiceImplementation() {
		em = bdCat.connect();
	}

	@Override
	public void agregarCategoria(Categoria categoria) {
		bdCat.save(categoria, em);
	}

}
