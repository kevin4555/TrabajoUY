
package logica.webservices;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dtPaquetePublicacion complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>{@code
 * <complexType name="dtPaquetePublicacion">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="periodoValidez" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="descuento" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         <element name="costo" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         <element name="fechaAltaString" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="imagenBase64" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="cantidadTipoPublicaciones" type="{http://webServices.logica/}dtCantidadTipoPublicacion" maxOccurs="unbounded" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtPaquetePublicacion", propOrder = {
    "nombre",
    "descripcion",
    "periodoValidez",
    "descuento",
    "costo",
    "fechaAltaString",
    "imagenBase64",
    "cantidadTipoPublicaciones"
})
public class DtPaquetePublicacion {

    protected String nombre;
    protected String descripcion;
    protected int periodoValidez;
    protected float descuento;
    protected float costo;
    protected String fechaAltaString;
    protected String imagenBase64;
    @XmlElement(nillable = true)
    protected List<DtCantidadTipoPublicacion> cantidadTipoPublicaciones;

    /**
     * Gets the value of the nombre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets the value of the nombre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

    /**
     * Gets the value of the descripcion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Sets the value of the descripcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcion(String value) {
        this.descripcion = value;
    }

    /**
     * Gets the value of the periodoValidez property.
     * 
     */
    public int getPeriodoValidez() {
        return periodoValidez;
    }

    /**
     * Sets the value of the periodoValidez property.
     * 
     */
    public void setPeriodoValidez(int value) {
        this.periodoValidez = value;
    }

    /**
     * Gets the value of the descuento property.
     * 
     */
    public float getDescuento() {
        return descuento;
    }

    /**
     * Sets the value of the descuento property.
     * 
     */
    public void setDescuento(float value) {
        this.descuento = value;
    }

    /**
     * Gets the value of the costo property.
     * 
     */
    public float getCosto() {
        return costo;
    }

    /**
     * Sets the value of the costo property.
     * 
     */
    public void setCosto(float value) {
        this.costo = value;
    }

    /**
     * Gets the value of the fechaAltaString property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaAltaString() {
        return fechaAltaString;
    }

    /**
     * Sets the value of the fechaAltaString property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaAltaString(String value) {
        this.fechaAltaString = value;
    }

    /**
     * Gets the value of the imagenBase64 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImagenBase64() {
        return imagenBase64;
    }

    /**
     * Sets the value of the imagenBase64 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImagenBase64(String value) {
        this.imagenBase64 = value;
    }

    /**
     * Gets the value of the cantidadTipoPublicaciones property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the cantidadTipoPublicaciones property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCantidadTipoPublicaciones().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtCantidadTipoPublicacion }
     * 
     * 
     * @return
     *     The value of the cantidadTipoPublicaciones property.
     */
    public List<DtCantidadTipoPublicacion> getCantidadTipoPublicaciones() {
        if (cantidadTipoPublicaciones == null) {
            cantidadTipoPublicaciones = new ArrayList<>();
        }
        return this.cantidadTipoPublicaciones;
    }

}
