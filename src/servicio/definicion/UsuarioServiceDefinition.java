package servicio.definicion;

import entidad.Usuario;

public interface UsuarioServiceDefinition {

	public void iniciarSesion(Usuario usuario);
	public void cerrarSesion(Usuario usuario);
	public boolean estaEnSesion(Usuario usuario);
	
}
