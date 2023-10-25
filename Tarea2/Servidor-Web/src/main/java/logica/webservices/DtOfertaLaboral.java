
package logica.webservices;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtOfertaLaboral complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
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
 *         <element name="fechaAlta" type="{http://webServices.logica/}localDate" minOccurs="0"/>
 *         <element name="fechaResolucion" type="{http://webServices.logica/}localDate" minOccurs="0"/>
 *         <element name="postulaciones" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="estadoOferta" type="{http://webServices.logica/}estadoOferta" minOccurs="0"/>
 *         <element name="imagen" type="{http://webServices.logica/}bufferedImage" minOccurs="0"/>
 *         <element name="imagenBase64" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="paqueteAsociado" type="{http://webServices.logica/}dtpaquetePublicacion" minOccurs="0"/>
 *         <element name="keywords" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="estaVencida" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         <element name="nombreTipoPublicacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="empresa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "fechaAlta",
    "fechaResolucion",
    "postulaciones",
    "estadoOferta",
    "imagen",
    "imagenBase64",
    "paqueteAsociado",
    "keywords",
    "estaVencida",
    "nombreTipoPublicacion",
    "empresa"
})
public class DtOfertaLaboral {

    protected String nombre;
    protected String descripcion;
    protected String ciudad;
    protected String departamento;
    protected String horarioInicio;
    protected String horarioFinal;
    protected Float remuneracion;
    protected LocalDate fechaAlta;
    protected LocalDate fechaResolucion;
    protected String postulaciones;
    @XmlSchemaType(name = "string")
    protected EstadoOferta estadoOferta;
    protected BufferedImage imagen;
    protected String imagenBase64;
    protected DtpaquetePublicacion paqueteAsociado;
    protected String keywords;
    protected Boolean estaVencida;
    protected String nombreTipoPublicacion;
    protected String empresa;

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
     * Obtiene el valor de la propiedad descripcion.
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
     * Define el valor de la propiedad descripcion.
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
     * Obtiene el valor de la propiedad ciudad.
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
     * Define el valor de la propiedad ciudad.
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
     * Obtiene el valor de la propiedad departamento.
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
     * Define el valor de la propiedad departamento.
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
     * Obtiene el valor de la propiedad horarioInicio.
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
     * Define el valor de la propiedad horarioInicio.
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
     * Obtiene el valor de la propiedad horarioFinal.
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
     * Define el valor de la propiedad horarioFinal.
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
     * Obtiene el valor de la propiedad remuneracion.
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
     * Define el valor de la propiedad remuneracion.
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
     * Obtiene el valor de la propiedad fechaAlta.
     * 
     * @return
     *     possible object is
     *     {@link LocalDate }
     *     
     */
    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    /**
     * Define el valor de la propiedad fechaAlta.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalDate }
     *     
     */
    public void setFechaAlta(LocalDate value) {
        this.fechaAlta = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaResolucion.
     * 
     * @return
     *     possible object is
     *     {@link LocalDate }
     *     
     */
    public LocalDate getFechaResolucion() {
        return fechaResolucion;
    }

    /**
     * Define el valor de la propiedad fechaResolucion.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalDate }
     *     
     */
    public void setFechaResolucion(LocalDate value) {
        this.fechaResolucion = value;
    }

    /**
     * Obtiene el valor de la propiedad postulaciones.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostulaciones() {
        return postulaciones;
    }

    /**
     * Define el valor de la propiedad postulaciones.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostulaciones(String value) {
        this.postulaciones = value;
    }

    /**
     * Obtiene el valor de la propiedad estadoOferta.
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
     * Define el valor de la propiedad estadoOferta.
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
     * Obtiene el valor de la propiedad imagen.
     * 
     * @return
     *     possible object is
     *     {@link BufferedImage }
     *     
     */
    public BufferedImage getImagen() {
        return imagen;
    }

    /**
     * Define el valor de la propiedad imagen.
     * 
     * @param value
     *     allowed object is
     *     {@link BufferedImage }
     *     
     */
    public void setImagen(BufferedImage value) {
        this.imagen = value;
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
     * Obtiene el valor de la propiedad paqueteAsociado.
     * 
     * @return
     *     possible object is
     *     {@link DtpaquetePublicacion }
     *     
     */
    public DtpaquetePublicacion getPaqueteAsociado() {
        return paqueteAsociado;
    }

    /**
     * Define el valor de la propiedad paqueteAsociado.
     * 
     * @param value
     *     allowed object is
     *     {@link DtpaquetePublicacion }
     *     
     */
    public void setPaqueteAsociado(DtpaquetePublicacion value) {
        this.paqueteAsociado = value;
    }

    /**
     * Obtiene el valor de la propiedad keywords.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKeywords() {
        return keywords;
    }

    /**
     * Define el valor de la propiedad keywords.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKeywords(String value) {
        this.keywords = value;
    }

    /**
     * Obtiene el valor de la propiedad estaVencida.
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
     * Define el valor de la propiedad estaVencida.
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
     * Obtiene el valor de la propiedad empresa.
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
     * Define el valor de la propiedad empresa.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmpresa(String value) {
        this.empresa = value;
    }

}
