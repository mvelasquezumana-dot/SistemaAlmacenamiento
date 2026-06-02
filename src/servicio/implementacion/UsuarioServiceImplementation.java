package servicio.implementacion;

import entidad.Usuario;
import servicio.definicion.UsuarioServiceDefinition;

public class UsuarioServiceImplementation implements UsuarioServiceDefinition {

	@Override
	public void iniciarSesion(Usuario usuario ) {
		
		
		
	}

	@Override
	public void cerrarSesion(Usuario usuario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean estaEnSesion(Usuario usuario) {
		// TODO Auto-generated method stub
		return usuario.isEnSesion();	
		}
	

}
