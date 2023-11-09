
package main.java.webservices;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtDatosPdf complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dtDatosPdf">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="nombrePostulante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="nombreEmpresa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="nombreOferta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="posicion" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="fechaPostulacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="fechaResolucion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtDatosPdf", propOrder = {
    "nombrePostulante",
    "nombreEmpresa",
    "nombreOferta",
    "posicion",
    "fechaPostulacion",
    "fechaResolucion"
})
public class DtDatosPdf {

    protected String nombrePostulante;
    protected String nombreEmpresa;
    protected String nombreOferta;
    protected int posicion;
    protected String fechaPostulacion;
    protected String fechaResolucion;

    /**
     * Obtiene el valor de la propiedad nombrePostulante.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombrePostulante() {
        return nombrePostulante;
    }

    /**
     * Define el valor de la propiedad nombrePostulante.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombrePostulante(String value) {
        this.nombrePostulante = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreEmpresa.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    /**
     * Define el valor de la propiedad nombreEmpresa.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreEmpresa(String value) {
        this.nombreEmpresa = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreOferta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreOferta() {
        return nombreOferta;
    }

    /**
     * Define el valor de la propiedad nombreOferta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreOferta(String value) {
        this.nombreOferta = value;
    }

    /**
     * Obtiene el valor de la propiedad posicion.
     * 
     */
    public int getPosicion() {
        return posicion;
    }

    /**
     * Define el valor de la propiedad posicion.
     * 
     */
    public void setPosicion(int value) {
        this.posicion = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaPostulacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaPostulacion() {
        return fechaPostulacion;
    }

    /**
     * Define el valor de la propiedad fechaPostulacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaPostulacion(String value) {
        this.fechaPostulacion = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaResolucion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaResolucion() {
        return fechaResolucion;
    }

    /**
     * Define el valor de la propiedad fechaResolucion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaResolucion(String value) {
        this.fechaResolucion = value;
    }

}
