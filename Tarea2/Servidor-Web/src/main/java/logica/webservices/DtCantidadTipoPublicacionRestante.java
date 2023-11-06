
package logica.webservices;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dtCantidadTipoPublicacionRestante complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>{@code
 * <complexType name="dtCantidadTipoPublicacionRestante">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="cantidad" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="tipoPublicacion" type="{http://webservices.logica/}dtTipoPublicacion" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtCantidadTipoPublicacionRestante", propOrder = {
    "cantidad",
    "tipoPublicacion"
})
public class DtCantidadTipoPublicacionRestante {

    protected int cantidad;
    protected DtTipoPublicacion tipoPublicacion;

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

    /**
     * Gets the value of the tipoPublicacion property.
     * 
     * @return
     *     possible object is
     *     {@link DtTipoPublicacion }
     *     
     */
    public DtTipoPublicacion getTipoPublicacion() {
        return tipoPublicacion;
    }

    /**
     * Sets the value of the tipoPublicacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link DtTipoPublicacion }
     *     
     */
    public void setTipoPublicacion(DtTipoPublicacion value) {
        this.tipoPublicacion = value;
    }

}
