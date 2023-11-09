
package main.java.webservices;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtCantidadTipoPublicacion complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
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
     * Obtiene el valor de la propiedad nombreTipoPublicacion.
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
     * Define el valor de la propiedad nombreTipoPublicacion.
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
     * Obtiene el valor de la propiedad cantidad.
     * 
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Define el valor de la propiedad cantidad.
     * 
     */
    public void setCantidad(int value) {
        this.cantidad = value;
    }

}
