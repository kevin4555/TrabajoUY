
package main.java.webservices;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtUsuario complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dtUsuario">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="nickname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="apellido" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="imagenBase64" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="contrasenia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="ofertasColeccion" type="{http://webservices.java.main/}dtOfertaLaboral" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="seguidos" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="seguidores" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtUsuario", propOrder = {
    "nickname",
    "nombre",
    "apellido",
    "email",
    "imagenBase64",
    "contrasenia",
    "ofertasColeccion",
    "seguidos",
    "seguidores"
})
@XmlSeeAlso({
    DtEmpresa.class,
    DtPostulante.class
})
public class DtUsuario {

    protected String nickname;
    protected String nombre;
    protected String apellido;
    protected String email;
    protected String imagenBase64;
    protected String contrasenia;
    @XmlElement(nillable = true)
    protected List<DtOfertaLaboral> ofertasColeccion;
    @XmlElement(nillable = true)
    protected List<String> seguidos;
    @XmlElement(nillable = true)
    protected List<String> seguidores;

    /**
     * Obtiene el valor de la propiedad nickname.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Define el valor de la propiedad nickname.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNickname(String value) {
        this.nickname = value;
    }

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
     * Obtiene el valor de la propiedad apellido.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Define el valor de la propiedad apellido.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApellido(String value) {
        this.apellido = value;
    }

    /**
     * Obtiene el valor de la propiedad email.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define el valor de la propiedad email.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
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
     * Obtiene el valor de la propiedad contrasenia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContrasenia() {
        return contrasenia;
    }

    /**
     * Define el valor de la propiedad contrasenia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContrasenia(String value) {
        this.contrasenia = value;
    }

    /**
     * Gets the value of the ofertasColeccion property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the ofertasColeccion property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOfertasColeccion().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtOfertaLaboral }
     * 
     * 
     * @return
     *     The value of the ofertasColeccion property.
     */
    public List<DtOfertaLaboral> getOfertasColeccion() {
        if (ofertasColeccion == null) {
            ofertasColeccion = new ArrayList<>();
        }
        return this.ofertasColeccion;
    }

    /**
     * Gets the value of the seguidos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the seguidos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSeguidos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     * @return
     *     The value of the seguidos property.
     */
    public List<String> getSeguidos() {
        if (seguidos == null) {
            seguidos = new ArrayList<>();
        }
        return this.seguidos;
    }

    /**
     * Gets the value of the seguidores property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the seguidores property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSeguidores().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     * @return
     *     The value of the seguidores property.
     */
    public List<String> getSeguidores() {
        if (seguidores == null) {
            seguidores = new ArrayList<>();
        }
        return this.seguidores;
    }

}
