
package main.java.webservices;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtCantidadTipoPublicacionRestante complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dtCantidadTipoPublicacionRestante">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="cantidad" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="tipoPublicacion" type="{http://webservices.java.main/}dtTipoPublicacion" minOccurs="0"/>
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

    /**
     * Obtiene el valor de la propiedad tipoPublicacion.
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
     * Define el valor de la propiedad tipoPublicacion.
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
