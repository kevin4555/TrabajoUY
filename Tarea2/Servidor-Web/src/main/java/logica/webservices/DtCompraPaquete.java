
package logica.webservices;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
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
 *         <element name="fechaCompra" type="{http://webServices.logica/}localDate" minOccurs="0"/>
 *         <element name="fechaVencimiento" type="{http://webServices.logica/}localDate" minOccurs="0"/>
 *         <element name="publicacionesRestantes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="paquete" type="{http://webServices.logica/}dtpaquetePublicacion" minOccurs="0"/>
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
    "fechaCompra",
    "fechaVencimiento",
    "publicacionesRestantes",
    "paquete"
})
public class DtCompraPaquete {

    protected LocalDate fechaCompra;
    protected LocalDate fechaVencimiento;
    protected String publicacionesRestantes;
    protected DtpaquetePublicacion paquete;

    /**
     * Obtiene el valor de la propiedad fechaCompra.
     * 
     * @return
     *     possible object is
     *     {@link LocalDate }
     *     
     */
    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    /**
     * Define el valor de la propiedad fechaCompra.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalDate }
     *     
     */
    public void setFechaCompra(LocalDate value) {
        this.fechaCompra = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaVencimiento.
     * 
     * @return
     *     possible object is
     *     {@link LocalDate }
     *     
     */
    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    /**
     * Define el valor de la propiedad fechaVencimiento.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalDate }
     *     
     */
    public void setFechaVencimiento(LocalDate value) {
        this.fechaVencimiento = value;
    }

    /**
     * Obtiene el valor de la propiedad publicacionesRestantes.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPublicacionesRestantes() {
        return publicacionesRestantes;
    }

    /**
     * Define el valor de la propiedad publicacionesRestantes.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPublicacionesRestantes(String value) {
        this.publicacionesRestantes = value;
    }

    /**
     * Obtiene el valor de la propiedad paquete.
     * 
     * @return
     *     possible object is
     *     {@link DtpaquetePublicacion }
     *     
     */
    public DtpaquetePublicacion getPaquete() {
        return paquete;
    }

    /**
     * Define el valor de la propiedad paquete.
     * 
     * @param value
     *     allowed object is
     *     {@link DtpaquetePublicacion }
     *     
     */
    public void setPaquete(DtpaquetePublicacion value) {
        this.paquete = value;
    }

}
