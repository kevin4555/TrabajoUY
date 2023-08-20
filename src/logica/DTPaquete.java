package logica;

public class DTPaquete {
	private String nombre;
	private String descripcion;
	private int cantidadPublicaciones;
	private int periodoValidez;
	private double descuento;
	private double costo;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getCantidadPublicaciones() {
		return cantidadPublicaciones;
	}
	public void setCantidadPublicaciones(int cantidadPublicaciones) {
		this.cantidadPublicaciones = cantidadPublicaciones;
	}
	public int getPeriodoValidez() {
		return periodoValidez;
	}
	public void setPeriodoValidez(int periodoValidez) {
		this.periodoValidez = periodoValidez;
	}
	public double getDescuento() {
		return descuento;
	}
	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}
	public double getCosto() {
		return costo;
	}
	public void setCosto(double costo) {
		this.costo = costo;
	}
}
