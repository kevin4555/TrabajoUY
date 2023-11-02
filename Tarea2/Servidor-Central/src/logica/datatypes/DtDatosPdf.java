package logica.datatypes;

import java.io.Serializable;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtDatosPdf implements Serializable{

  private static final long serialVersionUID = 1L;
  String nombrePostulante;
  String nombreEmpresa;
  String nombreOferta;
  int posicion;
  String fechaPostulacion;
  String fechaResolucion;
  
  
  public DtDatosPdf() {
  }


  public DtDatosPdf(String nombrePostulante, String nombreEmpresa, String nombreOferta,
      int posicion, String fechaPostulacion, String fechaResolucion) {
    this.nombrePostulante = nombrePostulante;
    this.nombreEmpresa = nombreEmpresa;
    this.nombreOferta = nombreOferta;
    this.posicion = posicion;
    this.fechaPostulacion = fechaPostulacion;
    this.fechaResolucion = fechaResolucion;
  }


  public String getNombrePostulante() {
    return nombrePostulante;
  }


  public void setNombrePostulante(String nombrePostulante) {
    this.nombrePostulante = nombrePostulante;
  }


  public String getNombreEmpresa() {
    return nombreEmpresa;
  }


  public void setNombreEmpresa(String nombreEmpresa) {
    this.nombreEmpresa = nombreEmpresa;
  }


  public String getNombreOferta() {
    return nombreOferta;
  }


  public void setNombreOferta(String nombreOferta) {
    this.nombreOferta = nombreOferta;
  }


  public int getPosicion() {
    return posicion;
  }


  public void setPosicion(int posicion) {
    this.posicion = posicion;
  }


  public String getFechaPostulacion() {
    return fechaPostulacion;
  }


  public void setFechaPostulacion(String fechaPostulacion) {
    this.fechaPostulacion = fechaPostulacion;
  }


  public String getFechaResolucion() {
    return fechaResolucion;
  }


  public void setFechaResolucion(String fechaResolucion) {
    this.fechaResolucion = fechaResolucion;
  }
  
  
}
