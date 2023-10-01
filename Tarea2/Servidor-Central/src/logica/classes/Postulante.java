package logica.classes;

import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.util.ArrayList;
import logica.DataTypes.DTOfertaLaboral;
import logica.DataTypes.DTPostulacion;
import logica.DataTypes.DTPostulante;
import logica.DataTypes.DTUsuario;

public class Postulante extends Usuario{
	private LocalDate fechaNacimiento;
	private String nacionalidad;
	private ArrayList<Postulacion> postulaciones;
	
	
	public Postulante(String nickname, String nombre, String apellido, String email, LocalDate fechaNacimiento, String nacionalidad, BufferedImage imagen, String contrasenia) {
		super(nickname, nombre, apellido, email, imagen, contrasenia);
		setFechaNacimiento(fechaNacimiento);
		setNacionalidad(nacionalidad);
		this.postulaciones = new ArrayList<Postulacion>();
	}
	
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
	
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	public String getNacionalidad() {
		return nacionalidad;
	}
	
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public ArrayList<Postulacion> getPostulaciones() {
		return postulaciones;
	}
	
	public DTPostulante obtenerDTPostulante() {
		return new DTPostulante(this.nickname, this.nombre, this.apellido, this.email,  this.getImagen(),  this.getContrasenia(),
				this.obtenerDTOfertas(),this.fechaNacimiento, this.nacionalidad);
	}
	
	public void agregarPostulacion(Postulacion postulacion) {
		this.postulaciones.add(postulacion);
	}
	
	@Override
	public ArrayList<String> listarOfertasUsuario(){
		ArrayList<String> listaOfertas = new ArrayList<String>();
		for(Postulacion postulacion : postulaciones ) {
			listaOfertas.add(postulacion.getNombreOfertaLaboral());
		}
		return listaOfertas;
	}

	@Override
	public DTUsuario obtenerDTUsuario() {
		
		return this.obtenerDTPostulante();
	}
	
	public DTPostulacion obtenerDTPostulacion(String nombreOferta) {
		Postulacion postulacionAOferta = null;
		for(Postulacion postulacion : postulaciones) {
			if (postulacion.getNombreOfertaLaboral() == nombreOferta) {
				postulacionAOferta = postulacion;
			}
		}
		return postulacionAOferta.obtenerDTPostulacion();
	}
	
	public ArrayList<DTOfertaLaboral> obtenerDTOfertas(){
		ArrayList<DTOfertaLaboral> listaResultado = new ArrayList<DTOfertaLaboral>();
		for(Postulacion postulacion : postulaciones) {
			listaResultado.add(postulacion.getOfertaLaboral().obtenerDTOfertaLaboral());
		}
		return listaResultado;
	}
	
	public ArrayList<DTPostulacion> obtenerDTPostulaciones(){
		ArrayList<DTPostulacion> listaResultado = new ArrayList<DTPostulacion>();
		for(Postulacion postulacion : postulaciones) {
			listaResultado.add(postulacion.obtenerDTPostulacion());
		}
		return listaResultado;
	}
	
	public Boolean estaPostulado(String nombreOferta) {
		Boolean resultado = false;
		for(Postulacion postulacion : postulaciones) {
			if(postulacion.getOfertaLaboral().getNombre() == nombreOferta) {
				resultado = true;
				break;
			}
		}
		return resultado;
		
	}
}
