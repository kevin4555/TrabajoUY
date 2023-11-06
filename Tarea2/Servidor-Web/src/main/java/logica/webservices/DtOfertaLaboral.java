
package logica.webservices;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dtOfertaLaboral complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>{@code
 * <complexType name="dtOfertaLaboral">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="ciudad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="departamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="horarioInicio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="horarioFinal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="remuneracion" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/>
 *         <element name="postulaciones" type="{http://webservices.logica/}dtPostulacion" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="estadoOferta" type="{http://webservices.logica/}estadoOferta" minOccurs="0"/>
 *         <element name="imagenBase64" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="paqueteAsociado" type="{http://webservices.logica/}dtPaquetePublicacion" minOccurs="0"/>
 *         <element name="keywords" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="estaVencida" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         <element name="nombreTipoPublicacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="empresa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="visitas" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="exposicion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="fechaAltaString" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="fechaResolucionString" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="fechaFinalizacionString" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtOfertaLaboral", propOrder = {
    "nombre",
    "descripcion",
    "ciudad",
    "departamento",
    "horarioInicio",
    "horarioFinal",
    "remuneracion",
    "postulaciones",
    "estadoOferta",
    "imagenBase64",
    "paqueteAsociado",
    "keywords",
    "estaVencida",
    "nombreTipoPublicacion",
    "empresa",
    "visitas",
    "exposicion",
    "fechaAltaString",
    "fechaResolucionString",
    "fechaFinalizacionString"
})
public class DtOfertaLaboral {

    protected String nombre;
    protected String descripcion;
    protected String ciudad;
    protected String departamento;
    protected String horarioInicio;
    protected String horarioFinal;
    protected Float remuneracion;
    @XmlElement(nillable = true)
    protected List<DtPostulacion> postulaciones;
    @XmlSchemaType(name = "string")
    protected EstadoOferta estadoOferta;
    protected String imagenBase64;
    protected DtPaquetePublicacion paqueteAsociado;
    @XmlElement(nillable = true)
    protected List<String> keywords;
    protected Boolean estaVencida;
    protected String nombreTipoPublicacion;
    protected String empresa;
    protected int visitas;
    protected String exposicion;
    protected String fechaAltaString;
    protected String fechaResolucionString;
    protected String fechaFinalizacionString;

    /**
     * Gets the value of the nombre property.
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
     * Sets the value of the nombre property.
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
     * Gets the value of the descripcion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Sets the value of the descripcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcion(String value) {
        this.descripcion = value;
    }

    /**
     * Gets the value of the ciudad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * Sets the value of the ciudad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCiudad(String value) {
        this.ciudad = value;
    }

    /**
     * Gets the value of the departamento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartamento() {
        return departamento;
    }

    /**
     * Sets the value of the departamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartamento(String value) {
        this.departamento = value;
    }

    /**
     * Gets the value of the horarioInicio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHorarioInicio() {
        return horarioInicio;
    }

    /**
     * Sets the value of the horarioInicio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHorarioInicio(String value) {
        this.horarioInicio = value;
    }

    /**
     * Gets the value of the horarioFinal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHorarioFinal() {
        return horarioFinal;
    }

    /**
     * Sets the value of the horarioFinal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHorarioFinal(String value) {
        this.horarioFinal = value;
    }

    /**
     * Gets the value of the remuneracion property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getRemuneracion() {
        return remuneracion;
    }

    /**
     * Sets the value of the remuneracion property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setRemuneracion(Float value) {
        this.remuneracion = value;
    }

    /**
     * Gets the value of the postulaciones property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the postulaciones property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPostulaciones().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtPostulacion }
     * 
     * 
     * @return
     *     The value of the postulaciones property.
     */
    public List<DtPostulacion> getPostulaciones() {
        if (postulaciones == null) {
            postulaciones = new ArrayList<>();
        }
        return this.postulaciones;
    }

    /**
     * Gets the value of the estadoOferta property.
     * 
     * @return
     *     possible object is
     *     {@link EstadoOferta }
     *     
     */
    public EstadoOferta getEstadoOferta() {
        return estadoOferta;
    }

    /**
     * Sets the value of the estadoOferta property.
     * 
     * @param value
     *     allowed object is
     *     {@link EstadoOferta }
     *     
     */
    public void setEstadoOferta(EstadoOferta value) {
        this.estadoOferta = value;
    }

    /**
     * Gets the value of the imagenBase64 property.
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
     * Sets the value of the imagenBase64 property.
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
     * Gets the value of the paqueteAsociado property.
     * 
     * @return
     *     possible object is
     *     {@link DtPaquetePublicacion }
     *     
     */
    public DtPaquetePublicacion getPaqueteAsociado() {
        return paqueteAsociado;
    }

    /**
     * Sets the value of the paqueteAsociado property.
     * 
     * @param value
     *     allowed object is
     *     {@link DtPaquetePublicacion }
     *     
     */
    public void setPaqueteAsociado(DtPaquetePublicacion value) {
        this.paqueteAsociado = value;
    }

    /**
     * Gets the value of the keywords property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the keywords property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getKeywords().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     * @return
     *     The value of the keywords property.
     */
    public List<String> getKeywords() {
        if (keywords == null) {
            keywords = new ArrayList<>();
        }
        return this.keywords;
    }

    /**
     * Gets the value of the estaVencida property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEstaVencida() {
        return estaVencida;
    }

    /**
     * Sets the value of the estaVencida property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEstaVencida(Boolean value) {
        this.estaVencida = value;
    }

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
     * Gets the value of the empresa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmpresa() {
        return empresa;
    }

    /**
     * Sets the value of the empresa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmpresa(String value) {
        this.empresa = value;
    }

    /**
     * Gets the value of the visitas property.
     * 
     */
    public int getVisitas() {
        return visitas;
    }

    /**
     * Sets the value of the visitas property.
     * 
     */
    public void setVisitas(int value) {
        this.visitas = value;
    }

    /**
     * Gets the value of the exposicion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExposicion() {
        return exposicion;
    }

    /**
     * Sets the value of the exposicion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExposicion(String value) {
        this.exposicion = value;
    }

    /**
     * Gets the value of the fechaAltaString property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaAltaString() {
        return fechaAltaString;
    }

    /**
     * Sets the value of the fechaAltaString property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaAltaString(String value) {
        this.fechaAltaString = value;
    }

    /**
     * Gets the value of the fechaResolucionString property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaResolucionString() {
        return fechaResolucionString;
    }

    /**
     * Sets the value of the fechaResolucionString property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaResolucionString(String value) {
        this.fechaResolucionString = value;
    }

    /**
     * Gets the value of the fechaFinalizacionString property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaFinalizacionString() {
        return fechaFinalizacionString;
    }

    /**
     * Sets the value of the fechaFinalizacionString property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaFinalizacionString(String value) {
        this.fechaFinalizacionString = value;
    }

}
