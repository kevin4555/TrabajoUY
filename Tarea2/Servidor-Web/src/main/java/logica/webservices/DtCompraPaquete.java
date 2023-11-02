
package logica.webservices;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtCompraPaquete complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dtCompraPaquete">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="publicacionesRestantes" type="{http://webServices.logica/}dtCantidadTipoPublicacionRestante" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="paquete" type="{http://webServices.logica/}dtPaquetePublicacion" minOccurs="0"/>
 *         <element name="fechaCompraString" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="fechaVencimientoString" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtCompraPaquete", propOrder = {
    "publicacionesRestantes",
    "paquete",
    "fechaCompraString",
    "fechaVencimientoString"
})
public class DtCompraPaquete {

    @XmlElement(nillable = true)
    protected List<DtCantidadTipoPublicacionRestante> publicacionesRestantes;
    protected DtPaquetePublicacion paquete;
    protected String fechaCompraString;
    protected String fechaVencimientoString;

    /**
     * Gets the value of the publicacionesRestantes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the publicacionesRestantes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPublicacionesRestantes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtCantidadTipoPublicacionRestante }
     * 
     * 
     * @return
     *     The value of the publicacionesRestantes property.
     */
    public List<DtCantidadTipoPublicacionRestante> getPublicacionesRestantes() {
        if (publicacionesRestantes == null) {
            publicacionesRestantes = new ArrayList<>();
        }
        return this.publicacionesRestantes;
    }

    /**
     * Obtiene el valor de la propiedad paquete.
     * 
     * @return
     *     possible object is
     *     {@link DtPaquetePublicacion }
     *     
     */
    public DtPaquetePublicacion getPaquete() {
        return paquete;
    }

    /**
     * Define el valor de la propiedad paquete.
     * 
     * @param value
     *     allowed object is
     *     {@link DtPaquetePublicacion }
     *     
     */
    public void setPaquete(DtPaquetePublicacion value) {
        this.paquete = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaCompraString.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaCompraString() {
        return fechaCompraString;
    }

    /**
     * Define el valor de la propiedad fechaCompraString.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaCompraString(String value) {
        this.fechaCompraString = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaVencimientoString.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaVencimientoString() {
        return fechaVencimientoString;
    }

    /**
     * Define el valor de la propiedad fechaVencimientoString.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaVencimientoString(String value) {
        this.fechaVencimientoString = value;
    }

}
