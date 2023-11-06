
package logica.webservices;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dtPostulante complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
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
     * Gets the value of the nacionalidad property.
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
     * Sets the value of the nacionalidad property.
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
     * Gets the value of the fechaNacimientoString property.
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
     * Sets the value of the fechaNacimientoString property.
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
