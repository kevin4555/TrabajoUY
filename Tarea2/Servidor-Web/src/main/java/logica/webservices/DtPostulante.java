
package logica.webservices;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtPostulante complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dtPostulante">
 *   <complexContent>
 *     <extension base="{http://webservices.logica/}dtUsuario">
 *       <sequence>
 *         <element name="nacionalidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="fechaNacimientoString" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="ofertasFavoritas" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtPostulante", propOrder = {
    "nacionalidad",
    "fechaNacimientoString",
    "ofertasFavoritas"
})
public class DtPostulante
    extends DtUsuario
{

    protected String nacionalidad;
    protected String fechaNacimientoString;
    @XmlElement(nillable = true)
    protected List<String> ofertasFavoritas;

    /**
     * Obtiene el valor de la propiedad nacionalidad.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNacionalidad() {
        return nacionalidad;
    }

    /**
     * Define el valor de la propiedad nacionalidad.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNacionalidad(String value) {
        this.nacionalidad = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaNacimientoString.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaNacimientoString() {
        return fechaNacimientoString;
    }

    /**
     * Define el valor de la propiedad fechaNacimientoString.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaNacimientoString(String value) {
        this.fechaNacimientoString = value;
    }

    /**
     * Gets the value of the ofertasFavoritas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the ofertasFavoritas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOfertasFavoritas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     * @return
     *     The value of the ofertasFavoritas property.
     */
    public List<String> getOfertasFavoritas() {
        if (ofertasFavoritas == null) {
            ofertasFavoritas = new ArrayList<>();
        }
        return this.ofertasFavoritas;
    }

}
