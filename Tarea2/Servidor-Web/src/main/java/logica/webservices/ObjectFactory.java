
package logica.webservices;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the logica.webservices package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _IOException_QNAME = new QName("http://webServices.logica/", "IOException");
    private final static QName _KeywordNoExisteException_QNAME = new QName("http://webServices.logica/", "KeywordNoExisteException");
    private final static QName _KeywordYaExisteException_QNAME = new QName("http://webServices.logica/", "KeywordYaExisteException");
    private final static QName _OfertaLaboralNoExisteException_QNAME = new QName("http://webServices.logica/", "OfertaLaboralNoExisteException");
    private final static QName _OfertaLaboralNoTienePaquete_QNAME = new QName("http://webServices.logica/", "OfertaLaboralNoTienePaquete");
    private final static QName _OfertaLaboralYaExisteException_QNAME = new QName("http://webServices.logica/", "OfertaLaboralYaExisteException");
    private final static QName _PaquetePublicacionNoExisteException_QNAME = new QName("http://webServices.logica/", "PaquetePublicacionNoExisteException");
    private final static QName _PaquetePublicacionYaExisteException_QNAME = new QName("http://webServices.logica/", "PaquetePublicacionYaExisteException");
    private final static QName _PaquetePublicacionYaFueComprado_QNAME = new QName("http://webServices.logica/", "PaquetePublicacionYaFueComprado");
    private final static QName _TipoDePublicacionYaFueIngresado_QNAME = new QName("http://webServices.logica/", "TipoDePublicacionYaFueIngresado");
    private final static QName _TipoPublicacionNoExisteException_QNAME = new QName("http://webServices.logica/", "TipoPublicacionNoExisteException");
    private final static QName _TipoPublicacionYaExisteException_QNAME = new QName("http://webServices.logica/", "TipoPublicacionYaExisteException");
    private final static QName _UsuarioEmailRepetidoException_QNAME = new QName("http://webServices.logica/", "UsuarioEmailRepetidoException");
    private final static QName _UsuarioNoExisteException_QNAME = new QName("http://webServices.logica/", "UsuarioNoExisteException");
    private final static QName _UsuarioNoExistePostulacion_QNAME = new QName("http://webServices.logica/", "UsuarioNoExistePostulacion");
    private final static QName _UsuarioYaExisteException_QNAME = new QName("http://webServices.logica/", "UsuarioYaExisteException");
    private final static QName _UsuarioYaExistePostulacion_QNAME = new QName("http://webServices.logica/", "UsuarioYaExistePostulacion");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: logica.webservices
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link IOException }
     * 
     * @return
     *     the new instance of {@link IOException }
     */
    public IOException createIOException() {
        return new IOException();
    }

    /**
     * Create an instance of {@link KeywordNoExisteException }
     * 
     * @return
     *     the new instance of {@link KeywordNoExisteException }
     */
    public KeywordNoExisteException createKeywordNoExisteException() {
        return new KeywordNoExisteException();
    }

    /**
     * Create an instance of {@link KeywordYaExisteException }
     * 
     * @return
     *     the new instance of {@link KeywordYaExisteException }
     */
    public KeywordYaExisteException createKeywordYaExisteException() {
        return new KeywordYaExisteException();
    }

    /**
     * Create an instance of {@link OfertaLaboralNoExisteException }
     * 
     * @return
     *     the new instance of {@link OfertaLaboralNoExisteException }
     */
    public OfertaLaboralNoExisteException createOfertaLaboralNoExisteException() {
        return new OfertaLaboralNoExisteException();
    }

    /**
     * Create an instance of {@link OfertaLaboralNoTienePaquete }
     * 
     * @return
     *     the new instance of {@link OfertaLaboralNoTienePaquete }
     */
    public OfertaLaboralNoTienePaquete createOfertaLaboralNoTienePaquete() {
        return new OfertaLaboralNoTienePaquete();
    }

    /**
     * Create an instance of {@link OfertaLaboralYaExisteException }
     * 
     * @return
     *     the new instance of {@link OfertaLaboralYaExisteException }
     */
    public OfertaLaboralYaExisteException createOfertaLaboralYaExisteException() {
        return new OfertaLaboralYaExisteException();
    }

    /**
     * Create an instance of {@link PaquetePublicacionNoExisteException }
     * 
     * @return
     *     the new instance of {@link PaquetePublicacionNoExisteException }
     */
    public PaquetePublicacionNoExisteException createPaquetePublicacionNoExisteException() {
        return new PaquetePublicacionNoExisteException();
    }

    /**
     * Create an instance of {@link PaquetePublicacionYaExisteException }
     * 
     * @return
     *     the new instance of {@link PaquetePublicacionYaExisteException }
     */
    public PaquetePublicacionYaExisteException createPaquetePublicacionYaExisteException() {
        return new PaquetePublicacionYaExisteException();
    }

    /**
     * Create an instance of {@link PaquetePublicacionYaFueComprado }
     * 
     * @return
     *     the new instance of {@link PaquetePublicacionYaFueComprado }
     */
    public PaquetePublicacionYaFueComprado createPaquetePublicacionYaFueComprado() {
        return new PaquetePublicacionYaFueComprado();
    }

    /**
     * Create an instance of {@link TipoDePublicacionYaFueIngresado }
     * 
     * @return
     *     the new instance of {@link TipoDePublicacionYaFueIngresado }
     */
    public TipoDePublicacionYaFueIngresado createTipoDePublicacionYaFueIngresado() {
        return new TipoDePublicacionYaFueIngresado();
    }

    /**
     * Create an instance of {@link TipoPublicacionNoExisteException }
     * 
     * @return
     *     the new instance of {@link TipoPublicacionNoExisteException }
     */
    public TipoPublicacionNoExisteException createTipoPublicacionNoExisteException() {
        return new TipoPublicacionNoExisteException();
    }

    /**
     * Create an instance of {@link TipoPublicacionYaExisteException }
     * 
     * @return
     *     the new instance of {@link TipoPublicacionYaExisteException }
     */
    public TipoPublicacionYaExisteException createTipoPublicacionYaExisteException() {
        return new TipoPublicacionYaExisteException();
    }

    /**
     * Create an instance of {@link UsuarioEmailRepetidoException }
     * 
     * @return
     *     the new instance of {@link UsuarioEmailRepetidoException }
     */
    public UsuarioEmailRepetidoException createUsuarioEmailRepetidoException() {
        return new UsuarioEmailRepetidoException();
    }

    /**
     * Create an instance of {@link UsuarioNoExisteException }
     * 
     * @return
     *     the new instance of {@link UsuarioNoExisteException }
     */
    public UsuarioNoExisteException createUsuarioNoExisteException() {
        return new UsuarioNoExisteException();
    }

    /**
     * Create an instance of {@link UsuarioNoExistePostulacion }
     * 
     * @return
     *     the new instance of {@link UsuarioNoExistePostulacion }
     */
    public UsuarioNoExistePostulacion createUsuarioNoExistePostulacion() {
        return new UsuarioNoExistePostulacion();
    }

    /**
     * Create an instance of {@link UsuarioYaExisteException }
     * 
     * @return
     *     the new instance of {@link UsuarioYaExisteException }
     */
    public UsuarioYaExisteException createUsuarioYaExisteException() {
        return new UsuarioYaExisteException();
    }

    /**
     * Create an instance of {@link UsuarioYaExistePostulacion }
     * 
     * @return
     *     the new instance of {@link UsuarioYaExistePostulacion }
     */
    public UsuarioYaExistePostulacion createUsuarioYaExistePostulacion() {
        return new UsuarioYaExistePostulacion();
    }

    /**
     * Create an instance of {@link Dtpostulacion }
     * 
     * @return
     *     the new instance of {@link Dtpostulacion }
     */
    public Dtpostulacion createDtpostulacion() {
        return new Dtpostulacion();
    }

    /**
     * Create an instance of {@link LocalDate }
     * 
     * @return
     *     the new instance of {@link LocalDate }
     */
    public LocalDate createLocalDate() {
        return new LocalDate();
    }

    /**
     * Create an instance of {@link DtpaquetePublicacion }
     * 
     * @return
     *     the new instance of {@link DtpaquetePublicacion }
     */
    public DtpaquetePublicacion createDtpaquetePublicacion() {
        return new DtpaquetePublicacion();
    }

    /**
     * Create an instance of {@link BufferedImage }
     * 
     * @return
     *     the new instance of {@link BufferedImage }
     */
    public BufferedImage createBufferedImage() {
        return new BufferedImage();
    }

    /**
     * Create an instance of {@link Raster }
     * 
     * @return
     *     the new instance of {@link Raster }
     */
    public Raster createRaster() {
        return new Raster();
    }

    /**
     * Create an instance of {@link DtOfertaLaboral }
     * 
     * @return
     *     the new instance of {@link DtOfertaLaboral }
     */
    public DtOfertaLaboral createDtOfertaLaboral() {
        return new DtOfertaLaboral();
    }

    /**
     * Create an instance of {@link Dtusuario }
     * 
     * @return
     *     the new instance of {@link Dtusuario }
     */
    public Dtusuario createDtusuario() {
        return new Dtusuario();
    }

    /**
     * Create an instance of {@link DtcantidadTipoPublicacion }
     * 
     * @return
     *     the new instance of {@link DtcantidadTipoPublicacion }
     */
    public DtcantidadTipoPublicacion createDtcantidadTipoPublicacion() {
        return new DtcantidadTipoPublicacion();
    }

    /**
     * Create an instance of {@link DtCompraPaquete }
     * 
     * @return
     *     the new instance of {@link DtCompraPaquete }
     */
    public DtCompraPaquete createDtCompraPaquete() {
        return new DtCompraPaquete();
    }

    /**
     * Create an instance of {@link DttipoPublicacion }
     * 
     * @return
     *     the new instance of {@link DttipoPublicacion }
     */
    public DttipoPublicacion createDttipoPublicacion() {
        return new DttipoPublicacion();
    }

    /**
     * Create an instance of {@link DtpostulacionArray }
     * 
     * @return
     *     the new instance of {@link DtpostulacionArray }
     */
    public DtpostulacionArray createDtpostulacionArray() {
        return new DtpostulacionArray();
    }

    /**
     * Create an instance of {@link DtOfertaLaboralArray }
     * 
     * @return
     *     the new instance of {@link DtOfertaLaboralArray }
     */
    public DtOfertaLaboralArray createDtOfertaLaboralArray() {
        return new DtOfertaLaboralArray();
    }

    /**
     * Create an instance of {@link DtpaquetePublicacionArray }
     * 
     * @return
     *     the new instance of {@link DtpaquetePublicacionArray }
     */
    public DtpaquetePublicacionArray createDtpaquetePublicacionArray() {
        return new DtpaquetePublicacionArray();
    }

    /**
     * Create an instance of {@link DtusuarioArray }
     * 
     * @return
     *     the new instance of {@link DtusuarioArray }
     */
    public DtusuarioArray createDtusuarioArray() {
        return new DtusuarioArray();
    }

    /**
     * Create an instance of {@link DtcantidadTipoPublicacionArray }
     * 
     * @return
     *     the new instance of {@link DtcantidadTipoPublicacionArray }
     */
    public DtcantidadTipoPublicacionArray createDtcantidadTipoPublicacionArray() {
        return new DtcantidadTipoPublicacionArray();
    }

    /**
     * Create an instance of {@link DtCompraPaqueteArray }
     * 
     * @return
     *     the new instance of {@link DtCompraPaqueteArray }
     */
    public DtCompraPaqueteArray createDtCompraPaqueteArray() {
        return new DtCompraPaqueteArray();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IOException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link IOException }{@code >}
     */
    @XmlElementDecl(namespace = "http://webServices.logica/", name = "IOException")
    public JAXBElement<IOException> createIOException(IOException value) {
        return new JAXBElement<>(_IOException_QNAME, IOException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link KeywordNoExisteException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link KeywordNoExisteException }{@code >}
     */
    @XmlElementDecl(namespace = "http://webServices.logica/", name = "KeywordNoExisteException")
    public JAXBElement<KeywordNoExisteException> createKeywordNoExisteException(KeywordNoExisteException value) {
        return new JAXBElement<>(_KeywordNoExisteException_QNAME, KeywordNoExisteException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link KeywordYaExisteException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link KeywordYaExisteException }{@code >}
     */
    @XmlElementDecl(namespace = "http://webServices.logica/", name = "KeywordYaExisteException")
    public JAXBElement<KeywordYaExisteException> createKeywordYaExisteException(KeywordYaExisteException value) {
        return new JAXBElement<>(_KeywordYaExisteException_QNAME, KeywordYaExisteException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OfertaLaboralNoExisteException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link OfertaLaboralNoExisteException }{@code >}
     */
    @XmlElementDecl(namespace = "http://webServices.logica/", name = "OfertaLaboralNoExisteException")
    public JAXBElement<OfertaLaboralNoExisteException> createOfertaLaboralNoExisteException(OfertaLaboralNoExisteException value) {
        return new JAXBElement<>(_OfertaLaboralNoExisteException_QNAME, OfertaLaboralNoExisteException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OfertaLaboralNoTienePaquete }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link OfertaLaboralNoTienePaquete }{@code >}
     */
    @XmlElementDecl(namespace = "http://webServices.logica/", name = "OfertaLaboralNoTienePaquete")
    public JAXBElement<OfertaLaboralNoTienePaquete> createOfertaLaboralNoTienePaquete(OfertaLaboralNoTienePaquete value) {
        return new JAXBElement<>(_OfertaLaboralNoTienePaquete_QNAME, OfertaLaboralNoTienePaquete.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OfertaLaboralYaExisteException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link OfertaLaboralYaExisteException }{@code >}
     */
    @XmlElementDecl(namespace = "http://webServices.logica/", name = "OfertaLaboralYaExisteException")
    public JAXBElement<OfertaLaboralYaExisteException> createOfertaLaboralYaExisteException(OfertaLaboralYaExisteException value) {
        return new JAXBElement<>(_OfertaLaboralYaExisteException_QNAME, OfertaLaboralYaExisteException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PaquetePublicacionNoExisteException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PaquetePublicacionNoExisteException }{@code >}
     */
    @XmlElementDecl(namespace = "http://webServices.logica/", name = "PaquetePublicacionNoExisteException")
    public JAXBElement<PaquetePublicacionNoExisteException> createPaquetePublicacionNoExisteException(PaquetePublicacionNoExisteException value) {
        return new JAXBElement<>(_PaquetePublicacionNoExisteException_QNAME, PaquetePublicacionNoExisteException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PaquetePublicacionYaExisteException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PaquetePublicacionYaExisteException }{@code >}
     */
    @XmlElementDecl(namespace = "http://webServices.logica/", name = "PaquetePublicacionYaExisteException")
    public JAXBElement<PaquetePublicacionYaExisteException> createPaquetePublicacionYaExisteException(PaquetePublicacionYaExisteException value) {
        return new JAXBElement<>(_PaquetePublicacionYaExisteException_QNAME, PaquetePublicacionYaExisteException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PaquetePublicacionYaFueComprado }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PaquetePublicacionYaFueComprado }{@code >}
     */
    @XmlElementDecl(namespace = "http://webServices.logica/", name = "PaquetePublicacionYaFueComprado")
    public JAXBElement<PaquetePublicacionYaFueComprado> createPaquetePublicacionYaFueComprado(PaquetePublicacionYaFueComprado value) {
        return new JAXBElement<>(_PaquetePublicacionYaFueComprado_QNAME, PaquetePublicacionYaFueComprado.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TipoDePublicacionYaFueIngresado }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link TipoDePublicacionYaFueIngresado }{@code >}
     */
    @XmlElementDecl(namespace = "http://webServices.logica/", name = "TipoDePublicacionYaFueIngresado")
    public JAXBElement<TipoDePublicacionYaFueIngresado> createTipoDePublicacionYaFueIngresado(TipoDePublicacionYaFueIngresado value) {
        return new JAXBElement<>(_TipoDePublicacionYaFueIngresado_QNAME, TipoDePublicacionYaFueIngresado.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TipoPublicacionNoExisteException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link TipoPublicacionNoExisteException }{@code >}
     */
    @XmlElementDecl(namespace = "http://webServices.logica/", name = "TipoPublicacionNoExisteException")
    public JAXBElement<TipoPublicacionNoExisteException> createTipoPublicacionNoExisteException(TipoPublicacionNoExisteException value) {
        return new JAXBElement<>(_TipoPublicacionNoExisteException_QNAME, TipoPublicacionNoExisteException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TipoPublicacionYaExisteException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link TipoPublicacionYaExisteException }{@code >}
     */
    @XmlElementDecl(namespace = "http://webServices.logica/", name = "TipoPublicacionYaExisteException")
    public JAXBElement<TipoPublicacionYaExisteException> createTipoPublicacionYaExisteException(TipoPublicacionYaExisteException value) {
        return new JAXBElement<>(_TipoPublicacionYaExisteException_QNAME, TipoPublicacionYaExisteException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UsuarioEmailRepetidoException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UsuarioEmailRepetidoException }{@code >}
     */
    @XmlElementDecl(namespace = "http://webServices.logica/", name = "UsuarioEmailRepetidoException")
    public JAXBElement<UsuarioEmailRepetidoException> createUsuarioEmailRepetidoException(UsuarioEmailRepetidoException value) {
        return new JAXBElement<>(_UsuarioEmailRepetidoException_QNAME, UsuarioEmailRepetidoException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UsuarioNoExisteException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UsuarioNoExisteException }{@code >}
     */
    @XmlElementDecl(namespace = "http://webServices.logica/", name = "UsuarioNoExisteException")
    public JAXBElement<UsuarioNoExisteException> createUsuarioNoExisteException(UsuarioNoExisteException value) {
        return new JAXBElement<>(_UsuarioNoExisteException_QNAME, UsuarioNoExisteException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UsuarioNoExistePostulacion }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UsuarioNoExistePostulacion }{@code >}
     */
    @XmlElementDecl(namespace = "http://webServices.logica/", name = "UsuarioNoExistePostulacion")
    public JAXBElement<UsuarioNoExistePostulacion> createUsuarioNoExistePostulacion(UsuarioNoExistePostulacion value) {
        return new JAXBElement<>(_UsuarioNoExistePostulacion_QNAME, UsuarioNoExistePostulacion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UsuarioYaExisteException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UsuarioYaExisteException }{@code >}
     */
    @XmlElementDecl(namespace = "http://webServices.logica/", name = "UsuarioYaExisteException")
    public JAXBElement<UsuarioYaExisteException> createUsuarioYaExisteException(UsuarioYaExisteException value) {
        return new JAXBElement<>(_UsuarioYaExisteException_QNAME, UsuarioYaExisteException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UsuarioYaExistePostulacion }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UsuarioYaExistePostulacion }{@code >}
     */
    @XmlElementDecl(namespace = "http://webServices.logica/", name = "UsuarioYaExistePostulacion")
    public JAXBElement<UsuarioYaExistePostulacion> createUsuarioYaExistePostulacion(UsuarioYaExistePostulacion value) {
        return new JAXBElement<>(_UsuarioYaExistePostulacion_QNAME, UsuarioYaExistePostulacion.class, null, value);
    }

}
