
package logica.webservices;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtPaquetePublicacion complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
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
     * Obtiene el valor de la propiedad nombre.
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
     * Define el valor de la propiedad nombre.
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
     * Obtiene el valor de la propiedad descripcion.
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
     * Define el valor de la propiedad descripcion.
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
     * Obtiene el valor de la propiedad periodoValidez.
     * 
     */
    public int getPeriodoValidez() {
        return periodoValidez;
    }

    /**
     * Define el valor de la propiedad periodoValidez.
     * 
     */
    public void setPeriodoValidez(int value) {
        this.periodoValidez = value;
    }

    /**
     * Obtiene el valor de la propiedad descuento.
     * 
     */
    public float getDescuento() {
        return descuento;
    }

    /**
     * Define el valor de la propiedad descuento.
     * 
     */
    public void setDescuento(float value) {
        this.descuento = value;
    }

    /**
     * Obtiene el valor de la propiedad costo.
     * 
     */
    public float getCosto() {
        return costo;
    }

    /**
     * Define el valor de la propiedad costo.
     * 
     */
    public void setCosto(float value) {
        this.costo = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaAltaString.
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
     * Define el valor de la propiedad fechaAltaString.
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
     * Obtiene el valor de la propiedad imagenBase64.
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
     * Define el valor de la propiedad imagenBase64.
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
