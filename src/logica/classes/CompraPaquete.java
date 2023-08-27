package logica.classes;

import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CompraPaquete {
	private Empresa empresa;
	private PaquetePublicacion paquetePublicacion;
	private Date fechaCompra;
	private Date fechaVenciminto;
	private int cantidadDeOfertasRestantes;
	private Map<String, OfertaLaboral> ofertasLaborales;
	
	public CompraPaquete(Empresa empresa, PaquetePublicacion paquetePublicacion,Date fechaCompra)
	{
		this.setEmpresa(empresa);
		this.setFechaCompra(fechaCompra);
		this.setPaquetePublicacion(paquetePublicacion);
		this.cantidadDeOfertasRestantes = paquetePublicacion.getCantidadPublicaciones();
		this.ofertasLaborales = new HashMap<String, OfertaLaboral>();
		calcularFechaVenciminto(cantidadDeOfertasRestantes);
	}

	
	
	private void calcularFechaVenciminto(int diasVigentesPaquete) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, diasVigentesPaquete);
		this.fechaVenciminto = calendar.getTime();
	}



	public Empresa getEmpresa() {
		return empresa;
	}



	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	public int getCantidadDeOfertasRestantes() {
		return cantidadDeOfertasRestantes;
	}
	
	public void reducirCantidadDeOfertasRestantes() {
		this.cantidadDeOfertasRestantes -= 1;
	}



	public PaquetePublicacion getPaquetePublicacion() {
		return paquetePublicacion;
	}



	public void setPaquetePublicacion(PaquetePublicacion paquetePublicacion) {
		this.paquetePublicacion = paquetePublicacion;
	}



	public Date getFechaCompra() {
		return fechaCompra;
	}



	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}



	public Date getFechaVenciminto() {
		return fechaVenciminto;
	}



	public Map<String, OfertaLaboral> getOfertasLaborales() {
		return ofertasLaborales;
	}



	public void addOfertasLaboral( OfertaLaboral ofertaLaboral) {
		this.ofertasLaborales.put(ofertaLaboral.getNombre(), ofertaLaboral);
	}

	
	
}
