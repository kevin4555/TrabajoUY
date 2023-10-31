
package logica.webservices;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dtCantidadTipoPublicacion complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>{@code
 * <complexType name="dtCantidadTipoPublicacion">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="nombreTipoPublicacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="cantidad" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtCantidadTipoPublicacion", propOrder = {
    "nombreTipoPublicacion",
    "cantidad"
})
public class DtCantidadTipoPublicacion {

    protected String nombreTipoPublicacion;
    protected int cantidad;

    /**
     * Gets the value of the nombreTipoPublicacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreTipoPublicacion() {
        return nombreTipoPublicacion;
    }

    /**
     * Sets the value of the nombreTipoPublicacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreTipoPublicacion(String value) {
        this.nombreTipoPublicacion = value;
    }

    /**
     * Gets the value of the cantidad property.
     * 
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Sets the value of the cantidad property.
     * 
     */
    public void setCantidad(int value) {
        this.cantidad = value;
    }

}
