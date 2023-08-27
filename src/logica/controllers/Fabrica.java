package logica.controllers;

import logica.interfaces.IControladorOferta;
import logica.interfaces.IControladorUsuario;

public class Fabrica {
	private static Fabrica instancia = null;
	
	private Fabrica() {}
	
	public static Fabrica getInstance() {
		if(instancia == null) {
			return new Fabrica();
		}
		return instancia;
	}
	
	public IControladorUsuario obtenerControladorUsuario() {
		return new ControladorUsuario();
	}
	
	public IControladorOferta obtenerControladorOferta() {
		return new ControladorOferta();
	}
}
