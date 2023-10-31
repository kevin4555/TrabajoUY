
package logica.webservices;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dtPostulacion complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>{@code
 * <complexType name="dtPostulacion">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="nicknamePostulante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="descripMotivacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="cvReducido" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="nombreOferta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="linkVideo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="fechaPostulacionString" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtPostulacion", propOrder = {
    "nicknamePostulante",
    "descripMotivacion",
    "cvReducido",
    "nombreOferta",
    "linkVideo",
    "fechaPostulacionString"
})
public class DtPostulacion {

    protected String nicknamePostulante;
    protected String descripMotivacion;
    protected String cvReducido;
    protected String nombreOferta;
    protected String linkVideo;
    protected String fechaPostulacionString;

    /**
     * Gets the value of the nicknamePostulante property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNicknamePostulante() {
        return nicknamePostulante;
    }

    /**
     * Sets the value of the nicknamePostulante property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNicknamePostulante(String value) {
        this.nicknamePostulante = value;
    }

    /**
     * Gets the value of the descripMotivacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripMotivacion() {
        return descripMotivacion;
    }

    /**
     * Sets the value of the descripMotivacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripMotivacion(String value) {
        this.descripMotivacion = value;
    }

    /**
     * Gets the value of the cvReducido property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCvReducido() {
        return cvReducido;
    }

    /**
     * Sets the value of the cvReducido property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCvReducido(String value) {
        this.cvReducido = value;
    }

    /**
     * Gets the value of the nombreOferta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreOferta() {
        return nombreOferta;
    }

    /**
     * Sets the value of the nombreOferta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreOferta(String value) {
        this.nombreOferta = value;
    }

    /**
     * Gets the value of the linkVideo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLinkVideo() {
        return linkVideo;
    }

    /**
     * Sets the value of the linkVideo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLinkVideo(String value) {
        this.linkVideo = value;
    }

    /**
     * Gets the value of the fechaPostulacionString property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaPostulacionString() {
        return fechaPostulacionString;
    }

    /**
     * Sets the value of the fechaPostulacionString property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaPostulacionString(String value) {
        this.fechaPostulacionString = value;
    }

}
