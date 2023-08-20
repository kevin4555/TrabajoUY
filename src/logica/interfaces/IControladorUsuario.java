package logica.interfaces;

import logica.classes.Empresa;

public interface IControladorUsuario {
	
	public abstract Empresa obtenerEmpresa(String nicknameEmpresa);
}
