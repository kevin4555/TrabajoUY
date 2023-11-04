
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

    private final static QName _IOException_QNAME = new QName("http://webservices.logica/", "IOException");
    private final static QName _KeywordNoExisteException_QNAME = new QName("http://webservices.logica/", "KeywordNoExisteException");
    private final static QName _KeywordYaExisteException_QNAME = new QName("http://webservices.logica/", "KeywordYaExisteException");
    private final static QName _OfertaLaboralNoExisteException_QNAME = new QName("http://webservices.logica/", "OfertaLaboralNoExisteException");
    private final static QName _OfertaLaboralNoSePuedeFinalizar_QNAME = new QName("http://webservices.logica/", "OfertaLaboralNoSePuedeFinalizar");
    private final static QName _OfertaLaboralNoTienePaquete_QNAME = new QName("http://webservices.logica/", "OfertaLaboralNoTienePaquete");
    private final static QName _OfertaLaboralYaExisteException_QNAME = new QName("http://webservices.logica/", "OfertaLaboralYaExisteException");
    private final static QName _PaquetePublicacionNoExisteException_QNAME = new QName("http://webservices.logica/", "PaquetePublicacionNoExisteException");
    private final static QName _PaquetePublicacionYaExisteException_QNAME = new QName("http://webservices.logica/", "PaquetePublicacionYaExisteException");
    private final static QName _PaquetePublicacionYaFueComprado_QNAME = new QName("http://webservices.logica/", "PaquetePublicacionYaFueComprado");
    private final static QName _PostulanteNoEsOfertaFavoritaException_QNAME = new QName("http://webservices.logica/", "PostulanteNoEsOfertaFavoritaException");
    private final static QName _PostulanteYaEsOfertaFavoritaException_QNAME = new QName("http://webservices.logica/", "PostulanteYaEsOfertaFavoritaException");
    private final static QName _TipoDePublicacionYaFueIngresado_QNAME = new QName("http://webservices.logica/", "TipoDePublicacionYaFueIngresado");
    private final static QName _TipoPublicacionNoExisteException_QNAME = new QName("http://webservices.logica/", "TipoPublicacionNoExisteException");
    private final static QName _TipoPublicacionYaExisteException_QNAME = new QName("http://webservices.logica/", "TipoPublicacionYaExisteException");
    private final static QName _UsuarioEmailRepetidoException_QNAME = new QName("http://webservices.logica/", "UsuarioEmailRepetidoException");
    private final static QName _UsuarioNoEstaSeguidoException_QNAME = new QName("http://webservices.logica/", "UsuarioNoEstaSeguidoException");
    private final static QName _UsuarioNoExisteException_QNAME = new QName("http://webservices.logica/", "UsuarioNoExisteException");
    private final static QName _UsuarioNoExistePostulacion_QNAME = new QName("http://webservices.logica/", "UsuarioNoExistePostulacion");
    private final static QName _UsuarioYaEstaSeguidoException_QNAME = new QName("http://webservices.logica/", "UsuarioYaEstaSeguidoException");
    private final static QName _UsuarioYaExisteException_QNAME = new QName("http://webservices.logica/", "UsuarioYaExisteException");
    private final static QName _UsuarioYaExistePostulacion_QNAME = new QName("http://webservices.logica/", "UsuarioYaExistePostulacion");

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
     * Create an instance of {@link OfertaLaboralNoSePuedeFinalizar }
     * 
     * @return
     *     the new instance of {@link OfertaLaboralNoSePuedeFinalizar }
     */
    public OfertaLaboralNoSePuedeFinalizar createOfertaLaboralNoSePuedeFinalizar() {
        return new OfertaLaboralNoSePuedeFinalizar();
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
     * Create an instance of {@link PostulanteNoEsOfertaFavoritaException }
     * 
     * @return
     *     the new instance of {@link PostulanteNoEsOfertaFavoritaException }
     */
    public PostulanteNoEsOfertaFavoritaException createPostulanteNoEsOfertaFavoritaException() {
        return new PostulanteNoEsOfertaFavoritaException();
    }

    /**
     * Create an instance of {@link PostulanteYaEsOfertaFavoritaException }
     * 
     * @return
     *     the new instance of {@link PostulanteYaEsOfertaFavoritaException }
     */
    public PostulanteYaEsOfertaFavoritaException createPostulanteYaEsOfertaFavoritaException() {
        return new PostulanteYaEsOfertaFavoritaException();
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
     * Create an instance of {@link UsuarioNoEstaSeguidoException }
     * 
     * @return
     *     the new instance of {@link UsuarioNoEstaSeguidoException }
     */
    public UsuarioNoEstaSeguidoException createUsuarioNoEstaSeguidoException() {
        return new UsuarioNoEstaSeguidoException();
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
     * Create an instance of {@link UsuarioYaEstaSeguidoException }
     * 
     * @return
     *     the new instance of {@link UsuarioYaEstaSeguidoException }
     */
    public UsuarioYaEstaSeguidoException createUsuarioYaEstaSeguidoException() {
        return new UsuarioYaEstaSeguidoException();
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
     * Create an instance of {@link DtTipoPublicacion }
     * 
     * @return
     *     the new instance of {@link DtTipoPublicacion }
     */
    public DtTipoPublicacion createDtTipoPublicacion() {
        return new DtTipoPublicacion();
    }

    /**
     * Create an instance of {@link DtPostulacion }
     * 
     * @return
     *     the new instance of {@link DtPostulacion }
     */
    public DtPostulacion createDtPostulacion() {
        return new DtPostulacion();
    }

    /**
     * Create an instance of {@link DtDatosPdf }
     * 
     * @return
     *     the new instance of {@link DtDatosPdf }
     */
    public DtDatosPdf createDtDatosPdf() {
        return new DtDatosPdf();
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
     * Create an instance of {@link DtPaquetePublicacion }
     * 
     * @return
     *     the new instance of {@link DtPaquetePublicacion }
     */
    public DtPaquetePublicacion createDtPaquetePublicacion() {
        return new DtPaquetePublicacion();
    }

    /**
     * Create an instance of {@link DtCantidadTipoPublicacion }
     * 
     * @return
     *     the new instance of {@link DtCantidadTipoPublicacion }
     */
    public DtCantidadTipoPublicacion createDtCantidadTipoPublicacion() {
        return new DtCantidadTipoPublicacion();
    }

    /**
     * Create an instance of {@link DtEmpresa }
     * 
     * @return
     *     the new instance of {@link DtEmpresa }
     */
    public DtEmpresa createDtEmpresa() {
        return new DtEmpresa();
    }

    /**
     * Create an instance of {@link DtUsuario }
     * 
     * @return
     *     the new instance of {@link DtUsuario }
     */
    public DtUsuario createDtUsuario() {
        return new DtUsuario();
    }

    /**
     * Create an instance of {@link DtPostulante }
     * 
     * @return
     *     the new instance of {@link DtPostulante }
     */
    public DtPostulante createDtPostulante() {
        return new DtPostulante();
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
     * Create an instance of {@link DtCompraPaquete }
     * 
     * @return
     *     the new instance of {@link DtCompraPaquete }
     */
    public DtCompraPaquete createDtCompraPaquete() {
        return new DtCompraPaquete();
    }

    /**
     * Create an instance of {@link DtCantidadTipoPublicacionRestante }
     * 
     * @return
     *     the new instance of {@link DtCantidadTipoPublicacionRestante }
     */
    public DtCantidadTipoPublicacionRestante createDtCantidadTipoPublicacionRestante() {
        return new DtCantidadTipoPublicacionRestante();
    }

    /**
     * Create an instance of {@link DtPostulacionArray }
     * 
     * @return
     *     the new instance of {@link DtPostulacionArray }
     */
    public DtPostulacionArray createDtPostulacionArray() {
        return new DtPostulacionArray();
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
     * Create an instance of {@link DtEmpresaArray }
     * 
     * @return
     *     the new instance of {@link DtEmpresaArray }
     */
    public DtEmpresaArray createDtEmpresaArray() {
        return new DtEmpresaArray();
    }

    /**
     * Create an instance of {@link DtCantidadTipoPublicacionArray }
     * 
     * @return
     *     the new instance of {@link DtCantidadTipoPublicacionArray }
     */
    public DtCantidadTipoPublicacionArray createDtCantidadTipoPublicacionArray() {
        return new DtCantidadTipoPublicacionArray();
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
     * Create an instance of {@link DtUsuarioArray }
     * 
     * @return
     *     the new instance of {@link DtUsuarioArray }
     */
    public DtUsuarioArray createDtUsuarioArray() {
        return new DtUsuarioArray();
    }

    /**
     * Create an instance of {@link DtPaquetePublicacionArray }
     * 
     * @return
     *     the new instance of {@link DtPaquetePublicacionArray }
     */
    public DtPaquetePublicacionArray createDtPaquetePublicacionArray() {
        return new DtPaquetePublicacionArray();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IOException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link IOException }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservices.logica/", name = "IOException")
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
    @XmlElementDecl(namespace = "http://webservices.logica/", name = "KeywordNoExisteException")
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
    @XmlElementDecl(namespace = "http://webservices.logica/", name = "KeywordYaExisteException")
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
    @XmlElementDecl(namespace = "http://webservices.logica/", name = "OfertaLaboralNoExisteException")
    public JAXBElement<OfertaLaboralNoExisteException> createOfertaLaboralNoExisteException(OfertaLaboralNoExisteException value) {
        return new JAXBElement<>(_OfertaLaboralNoExisteException_QNAME, OfertaLaboralNoExisteException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OfertaLaboralNoSePuedeFinalizar }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link OfertaLaboralNoSePuedeFinalizar }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservices.logica/", name = "OfertaLaboralNoSePuedeFinalizar")
    public JAXBElement<OfertaLaboralNoSePuedeFinalizar> createOfertaLaboralNoSePuedeFinalizar(OfertaLaboralNoSePuedeFinalizar value) {
        return new JAXBElement<>(_OfertaLaboralNoSePuedeFinalizar_QNAME, OfertaLaboralNoSePuedeFinalizar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OfertaLaboralNoTienePaquete }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link OfertaLaboralNoTienePaquete }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservices.logica/", name = "OfertaLaboralNoTienePaquete")
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
    @XmlElementDecl(namespace = "http://webservices.logica/", name = "OfertaLaboralYaExisteException")
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
    @XmlElementDecl(namespace = "http://webservices.logica/", name = "PaquetePublicacionNoExisteException")
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
    @XmlElementDecl(namespace = "http://webservices.logica/", name = "PaquetePublicacionYaExisteException")
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
    @XmlElementDecl(namespace = "http://webservices.logica/", name = "PaquetePublicacionYaFueComprado")
    public JAXBElement<PaquetePublicacionYaFueComprado> createPaquetePublicacionYaFueComprado(PaquetePublicacionYaFueComprado value) {
        return new JAXBElement<>(_PaquetePublicacionYaFueComprado_QNAME, PaquetePublicacionYaFueComprado.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PostulanteNoEsOfertaFavoritaException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PostulanteNoEsOfertaFavoritaException }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservices.logica/", name = "PostulanteNoEsOfertaFavoritaException")
    public JAXBElement<PostulanteNoEsOfertaFavoritaException> createPostulanteNoEsOfertaFavoritaException(PostulanteNoEsOfertaFavoritaException value) {
        return new JAXBElement<>(_PostulanteNoEsOfertaFavoritaException_QNAME, PostulanteNoEsOfertaFavoritaException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PostulanteYaEsOfertaFavoritaException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PostulanteYaEsOfertaFavoritaException }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservices.logica/", name = "PostulanteYaEsOfertaFavoritaException")
    public JAXBElement<PostulanteYaEsOfertaFavoritaException> createPostulanteYaEsOfertaFavoritaException(PostulanteYaEsOfertaFavoritaException value) {
        return new JAXBElement<>(_PostulanteYaEsOfertaFavoritaException_QNAME, PostulanteYaEsOfertaFavoritaException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TipoDePublicacionYaFueIngresado }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link TipoDePublicacionYaFueIngresado }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservices.logica/", name = "TipoDePublicacionYaFueIngresado")
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
    @XmlElementDecl(namespace = "http://webservices.logica/", name = "TipoPublicacionNoExisteException")
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
    @XmlElementDecl(namespace = "http://webservices.logica/", name = "TipoPublicacionYaExisteException")
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
    @XmlElementDecl(namespace = "http://webservices.logica/", name = "UsuarioEmailRepetidoException")
    public JAXBElement<UsuarioEmailRepetidoException> createUsuarioEmailRepetidoException(UsuarioEmailRepetidoException value) {
        return new JAXBElement<>(_UsuarioEmailRepetidoException_QNAME, UsuarioEmailRepetidoException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UsuarioNoEstaSeguidoException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UsuarioNoEstaSeguidoException }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservices.logica/", name = "UsuarioNoEstaSeguidoException")
    public JAXBElement<UsuarioNoEstaSeguidoException> createUsuarioNoEstaSeguidoException(UsuarioNoEstaSeguidoException value) {
        return new JAXBElement<>(_UsuarioNoEstaSeguidoException_QNAME, UsuarioNoEstaSeguidoException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UsuarioNoExisteException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UsuarioNoExisteException }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservices.logica/", name = "UsuarioNoExisteException")
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
    @XmlElementDecl(namespace = "http://webservices.logica/", name = "UsuarioNoExistePostulacion")
    public JAXBElement<UsuarioNoExistePostulacion> createUsuarioNoExistePostulacion(UsuarioNoExistePostulacion value) {
        return new JAXBElement<>(_UsuarioNoExistePostulacion_QNAME, UsuarioNoExistePostulacion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UsuarioYaEstaSeguidoException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UsuarioYaEstaSeguidoException }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservices.logica/", name = "UsuarioYaEstaSeguidoException")
    public JAXBElement<UsuarioYaEstaSeguidoException> createUsuarioYaEstaSeguidoException(UsuarioYaEstaSeguidoException value) {
        return new JAXBElement<>(_UsuarioYaEstaSeguidoException_QNAME, UsuarioYaEstaSeguidoException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UsuarioYaExisteException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UsuarioYaExisteException }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservices.logica/", name = "UsuarioYaExisteException")
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
    @XmlElementDecl(namespace = "http://webservices.logica/", name = "UsuarioYaExistePostulacion")
    public JAXBElement<UsuarioYaExistePostulacion> createUsuarioYaExistePostulacion(UsuarioYaExistePostulacion value) {
        return new JAXBElement<>(_UsuarioYaExistePostulacion_QNAME, UsuarioYaExistePostulacion.class, null, value);
    }

}
