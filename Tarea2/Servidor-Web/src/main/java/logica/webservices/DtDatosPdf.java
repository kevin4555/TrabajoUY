
package logica.webservices;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dtDatosPdf complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
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
     * Gets the value of the nombrePostulante property.
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
     * Sets the value of the nombrePostulante property.
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
     * Gets the value of the nombreEmpresa property.
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
     * Sets the value of the nombreEmpresa property.
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
     * Gets the value of the nombreOferta property.
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
     * Sets the value of the nombreOferta property.
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
     * Gets the value of the posicion property.
     * 
     */
    public int getPosicion() {
        return posicion;
    }

    /**
     * Sets the value of the posicion property.
     * 
     */
    public void setPosicion(int value) {
        this.posicion = value;
    }

    /**
     * Gets the value of the fechaPostulacion property.
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
     * Sets the value of the fechaPostulacion property.
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
     * Gets the value of the fechaResolucion property.
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
     * Sets the value of the fechaResolucion property.
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
