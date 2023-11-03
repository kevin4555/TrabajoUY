package logica.controllers;

import excepciones.KeywordNoExisteException;
import excepciones.KeywordYaExisteException;
import excepciones.OfertaLaboralNoExisteException;
import excepciones.OfertaLaboralYaExisteException;
import excepciones.PaquetePublicacionNoExisteException;
import excepciones.PaquetePublicacionYaExisteException;
import excepciones.TipoPublicacionNoExisteException;
import excepciones.TipoPublicacionYaExisteException;
import excepciones.UsuarioEmailRepetidoException;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioYaExisteException;
import excepciones.UsuarioYaExistePostulacion;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import logica.classes.Keyword;
import logica.datatypes.DtCantidadTipoPublicacion;
import logica.datatypes.EstadoOferta;
import logica.interfaces.IcontroladorOferta;
import logica.interfaces.IcontroladorUsuario;

/**
 * Clase Loader.
 */
public class Loader {
  private static Boolean datosCargados = false;

  public Loader() {

  }

  /**
   * Metodo getBufferImage.
   */
  public BufferedImage getBufferImage(String path) {
    InputStream input = ClassLoader.getSystemClassLoader()
          .getResourceAsStream(path);
    BufferedImage imagen = null;
    try {
      imagen = ImageIO.read(input);
    } catch (IOException e) {
     //catchVacio
    }
    return imagen;
  }

  /**
   * Metodo cargar datos.
   */

  public void cargarDatos() throws UsuarioNoExisteException,
        OfertaLaboralNoExisteException, ParseException,
        UsuarioYaExisteException,
        UsuarioEmailRepetidoException,
        TipoPublicacionYaExisteException,
        KeywordYaExisteException, KeywordNoExisteException,
        TipoPublicacionNoExisteException,
        OfertaLaboralYaExisteException,
        PaquetePublicacionYaExisteException,
        UsuarioYaExistePostulacion, IOException,
        PaquetePublicacionNoExisteException {
    IcontroladorUsuario controladorUsuario = Fabrica
          .getInstance().obtenerControladorUsuario();
    controladorUsuario.altaPostulante("lgarcia", "Lucía",
          "García", "lgarcia85@gmail.com",
          LocalDate.parse("1985-03-15"), "Uruguaya",
          this.getBufferImage("U1.jpg"), "awdrg543");
    controladorUsuario.altaPostulante("matilo", "Matías",
          "López", "matias.lopez90@hotmail.com",
          LocalDate.parse("1990-08-21"), "Argentina",
          this.getBufferImage("U2.jpg"), "edrft543");
    controladorUsuario.altaPostulante("maro", "María",
          "Rodríguez", "marrod@gmail.com",
          LocalDate.parse("1988-11-10"), "Uruguaya",
          this.getBufferImage("U3.jpg"), "r5t6y7u8");
    controladorUsuario.altaPostulante("javierf", "Javier",
          "Fernández", "javierf93@yahoo.com",
          LocalDate.parse("1993-06-05"), "Mexicana",
          this.getBufferImage("U4.jpg"), "45idgaf67");
    controladorUsuario.altaPostulante("valen25",
          "Valentina", "Martínez", "vale87@gmail.com",
          LocalDate.parse("1987-02-25"), "Uruguaya",
          this.getBufferImage("U5.jpg"), "poiuy987");
    controladorUsuario.altaPostulante("andpe12", "Andrés",
          "Pérez", "anpe92@hotmail.com",
          LocalDate.parse("1992-04-12"), "Chilena",
          this.getBufferImage("U6.jpg"), "xdrgb657");
    controladorUsuario.altaPostulante("sicam", "Camila",
          "Silva", "camilasilva89@gmail.com",
          LocalDate.parse("1989-09-30"), "Uruguaya",
          this.getBufferImage("U7.jpg"), "mnjkiu89");
    controladorUsuario.altaPostulante("gonza95",
          "Sebastián", "González", "gonza95@yahoo.com",
          LocalDate.parse("1995-01-18"), "Colombiana",
          this.getBufferImage("U8.jpg"), "ytrewq10");
    controladorUsuario.altaPostulante("isabel", "Isabella",
          "López", "loisa@gmail.com",
          LocalDate.parse("1991-07-07"), "Uruguaya",
          this.getBufferImage("U9.jpg"), "sbsplol1");
    controladorUsuario.altaPostulante("marram02", "Martín",
          "Ramírez", "marram@hotmail.com",
          LocalDate.parse("1986-12-02"), "Argentina",
          this.getBufferImage("U10.jpg"), "okmnji98");

    controladorUsuario.altaEmpresa("EcoTech", "Sophia",
          "Johnson", "info@EcoTech.com",
          "EcoTech Innovations es una empresa líder en soluciones tecnológicas sostenibles. "
                + "Nuestro enfoque se centra en desarrollar y comercializar productos y servicios "
                + "que aborden los desafíos ambientales más apremiantes de nuestro tiempo. Desde "
                + "sistemas de energía renovable y dispositivos de monitorización ambiental hasta "
                + "soluciones de gestión de residuos inteligentes, nuestra misión es proporcionar "
                + "herramientas que permitan a las empresas y comunidades adoptar prácticas más "
                + "ecológicas sin comprometer la eficiencia. Creemos en la convergencia armoniosa "
                + "entre la tecnología la naturaleza, y trabajamos incansablemente para impulsar "
                + "un futuro más limpio y sostenible.",
          "http://www.EcoTechInnovations.com",
          this.getBufferImage("U11.jpg"), "qsxcdw43");
    controladorUsuario.altaEmpresa("FusionTech", "William",
          "Smith", "contacto@FusionTech.net",
          "FusionTech Dynamics es una empresa pionera en el ámbito de la inteligencia artificial y "
                + "la automatización avanzada. Nuestro equipo multidisciplinario de ingenieros,"
                + " científicos de datos y desarrolladores crea soluciones innovadoras que "
                + "aprovechan la potencia de la IA para transformar industrias. Desde la "
                + "optimización de procesos industriales hasta la creación de asistentes "
                + "virtuales altamente personalizados, nuestro objetivo es revolucionar la forma "
                + "en que las empresas operan y se conectan con sus clientes. Creemos "
                + "en la sinergia entre la mente humana y las capacidades de la IA, "
                + "y trabajamos para construir un mundo donde la "
                + "tecnología mejore y amplíe nuestras capacidades innatas.",
          "http://www.fusiontechdynamics.net",
          this.getBufferImage("U12.jpg"), "qpwoei586");
    controladorUsuario.altaEmpresa("GlobalHealth",
          "Isabella", "Brown", "jobs@GlobalHealth.uy",
          "GlobalHealth Dynamics es una empresa comprometida con el avance de la atención médica a "
                + "nivel mundial. Como líderes en el campo de la salud digital, "
                + "desarrollamos plataformas y herramientas que permiten a los profesionales de "
                + "la salud ofrecer diagnósticos más precisos, tratamientos personalizados y "
                + "seguimiento continuo de los pacientes. Nuestra visión es crear un ecosistema "
                + "de salud conectado en el que los datos médicos se utilicen de manera ética y "
                + "segura para mejorar la calidad de vida de las personas. A través de la "
                + "innovación constante y la colaboración con expertos m´edicos, estamos "
                + "dando forma al futuro de la atención médica, donde la tecnología y la "
                + "compasión se unen para salvar vidas y mejorar el bienestar en todo el mundo",
          "http://www.globalhealthdynamics.uy/info",
          this.getBufferImage("U13.jpg"), "asdfg654");
    controladorUsuario.altaEmpresa("ANTEL", "Washington",
          "Rocha", "jarrington@ANTEL.com.uy",
          "En Antel te brindamos servicios de vanguardia en tecnología de comunicación en "
                + "Telefonía Móvil, Fija, Banda Ancha y Datos",
          "ANTEL.com.uy", this.getBufferImage("U14.jpg"),
          "2nru096");
    controladorUsuario.altaEmpresa("MIEM", "Pablo",
          "Bengoechea", "eldiez@MIEM.org.uy",
          "Balance Energ´etico Nacional (BEN). La Dirección Nacional de Energía (DNE) del "
                + "Ministerio de Industria, Energía y Minería (MIEM) presenta anualmente el BEN.",
          "MIEM.com.uy", this.getBufferImage("U15.jpg"),
          "ibii4xo");
    controladorUsuario.altaEmpresa("TechSolutions",
          "Mercedes", "Venn",
          "Mercedes@TechSolutions.com.uy",
          " \"TechSolutions Inc.\" es una empresa líder en el campo de la tecnología de la "
                + "información y el software. Su experiencia radica en la creación de soluciones "
                + "de software a medida para compañías de distintos tamaños e industrias."
                + " Su principal enfoque se orienta hacia el desarrollo de aplicaciones "
                + "empresariales vanguardistas, diseñadas para mejorar procesos, "
                + "optimizar la eficiencia y proporcionar una ventaja competitiva "
                + "a sus clientes. Se especializan en proporcionar innovación tecnológica "
                + "para impulsar el éxito empresarial.",
          "TechSolutions.com",
          this.getBufferImage("U16.jpg"),
          "1ngs03p");

    IcontroladorOferta controladorOferta = Fabrica
          .getInstance().obtenerControladorOferta();

    controladorOferta.altaTipoPublicacion("Premium",
          "Obtén máxima visibilidad", "1", 30, 4000f,
          LocalDate.parse("2023-08-10"));
    controladorOferta.altaTipoPublicacion("Destacada",
          "Destaca tu anuncio", "2", 15, 500f,
          LocalDate.parse("2023-08-05"));
    controladorOferta.altaTipoPublicacion("Estándar",
          "Mejora la posición de tu anuncio", "3", 20, 150f,
          LocalDate.parse("2023-08-15"));
    controladorOferta.altaTipoPublicacion("Básica",
          "Publica de forma sencilla en la lista de ofertas",
          "4", 7, 50f, LocalDate.parse("2023-08-07"));

    controladorOferta.altaKeyword("Tiempo completo");
    controladorOferta.altaKeyword("Medio tiempo");
    controladorOferta.altaKeyword("Remoto");
    controladorOferta.altaKeyword("Freelance");
    controladorOferta.altaKeyword("Temporal");
    controladorOferta.altaKeyword("Permanente");
    controladorOferta.altaKeyword("Computación");
    controladorOferta.altaKeyword("Administración");
    controladorOferta.altaKeyword("Logística");
    controladorOferta.altaKeyword("Contabilidad");

    List<DtCantidadTipoPublicacion> paquete1 =
          new ArrayList<DtCantidadTipoPublicacion>();

    DtCantidadTipoPublicacion tipo1 =
          new DtCantidadTipoPublicacion(
                "Premium", 1);
    DtCantidadTipoPublicacion tipo2 =
          new DtCantidadTipoPublicacion(
                "Destacada", 1);
    DtCantidadTipoPublicacion tipo3 =
          new DtCantidadTipoPublicacion(
                "Estándar", 1);
    paquete1.add(tipo1);
    paquete1.add(tipo2);
    paquete1.add(tipo3);

    List<DtCantidadTipoPublicacion> paquete3 =
          new ArrayList<DtCantidadTipoPublicacion>();
    DtCantidadTipoPublicacion tipo4 =
          new DtCantidadTipoPublicacion(
                "Estándar", 2);
    DtCantidadTipoPublicacion tipo5 =
          new DtCantidadTipoPublicacion(
                "Básica", 1);

    List<DtCantidadTipoPublicacion> paquete2 =
          new ArrayList<DtCantidadTipoPublicacion>();

    paquete2.add(tipo4);
    paquete2.add(tipo5);

    DtCantidadTipoPublicacion tipo6 =
          new DtCantidadTipoPublicacion(
                "Premium", 2);
    DtCantidadTipoPublicacion tipo7 =
          new DtCantidadTipoPublicacion(
                "Estándar", 2);
    paquete3.add(tipo6);
    paquete3.add(tipo7);

    List<DtCantidadTipoPublicacion> paquete4 =
          new ArrayList<DtCantidadTipoPublicacion>();

    DtCantidadTipoPublicacion tipo8 =
          new DtCantidadTipoPublicacion(
                "Destacada", 2);
    paquete4.add(tipo8);

    controladorOferta.registrarPaquete("Básico",
          "Publica ofertas laborales en nuestra plataforma por un período de 30 días",
          30, 20f, this.getBufferImage("paq1.jpg"),
          LocalDate.parse("2023-08-16"), paquete1);

    controladorOferta.registrarPaquete("Destacado",
          "Publica ofertas laborales destacadas que se mostrará en la "
                + "parte superior de los resultados de búsqueda por 45 días",
          45, 10f, this.getBufferImage("paq2.jpg"),
          LocalDate.parse("2023-08-15"), paquete2);

    controladorOferta.registrarPaquete("Premium",
          "Publica ofertas laborales premium que incluye promoción en nuestras "
                + "redes sociales y listado en la sección destacada por 60 días",
          60, 15f, this.getBufferImage("paq3.jpg"),
          LocalDate.parse("2023-08-14"), paquete3);

    controladorOferta.registrarPaquete("Express",
          "Publica ofertas laborales urgentes resaltada en color y se mostrará en "
                + "la sección de urgente por 15 días.",
          15, 5f, this.getBufferImage("paq4.jpg"),
          LocalDate.parse("2023-08-13"), paquete4);

    controladorUsuario.comprarPaquete("EcoTech", "Básico",
          LocalDate.parse("2023-09-29"));
    controladorUsuario.comprarPaquete("TechSolutions",
          "Destacado", LocalDate.parse("2023-09-08"));
    controladorUsuario.comprarPaquete("EcoTech", "Premium",
          LocalDate.parse("2023-10-01"));
    controladorUsuario.comprarPaquete("FusionTech",
          "Destacado", LocalDate.parse("2023-10-23"));
    controladorUsuario.comprarPaquete("EcoTech", "Express",
          LocalDate.parse("2023-09-01"));

    List<String> keywords = new ArrayList<String>();
    Keyword keyword1 = controladorOferta
          .obtenerKeywords("Tiempo completo");
    keywords.add(keyword1.getNombre());
    Keyword keyword2 = controladorOferta
          .obtenerKeywords("Medio tiempo");
    keywords.add(keyword2.getNombre());
    Keyword keyword3 = controladorOferta
          .obtenerKeywords("Remoto");
    keywords.add(keyword3.getNombre());
    Keyword keyword4 = controladorOferta
          .obtenerKeywords("Freelance");
    keywords.add(keyword4.getNombre());
    Keyword keyword5 = controladorOferta
          .obtenerKeywords("Temporal");
    keywords.add(keyword5.getNombre());
    Keyword keyword6 = controladorOferta
          .obtenerKeywords("Permanente");
    keywords.add(keyword6.getNombre());

    controladorOferta.altaOfertaLaboral(
          "Desarrollador Frontend",
          "Únete a nuestro equipo de desarrollo frontend y crea "
                + "experiencias de usuario excepcionales.",
          "09:00", "18:00", 90000f, "Montevideo",
          "Montevideo", LocalDate.parse("2023-09-30"),
          "Premium", "EcoTech", keywords,
          this.getBufferImage("O1.jpg"), "Básico");

    List<String> keywords2 = new ArrayList<String>();
    keywords2.add(keyword5.getNombre());
    controladorOferta.altaOfertaLaboral(
          "Estratega de Negocios",
          "Forma parte de nuestro equipo de estrategia y contribuye al "
                + "crecimiento de las empresas clientes.",
          "08:00", "17:00", 80000f, "Punta del Este",
          "Maldonado", LocalDate.parse("2023-09-29"),
          "Estándar", "GlobalHealth", keywords2,
          this.getBufferImage("O2.jpg"), null);

    List<String> keywords3 = new ArrayList<String>();
    keywords3.add(keyword2.getNombre());
    keywords3.add(keyword3.getNombre());
    keywords3.add(keyword6.getNombre());
    controladorOferta.altaOfertaLaboral("Diseñador UX/UI",
          "Trabaja en colaboración con nuestro talentoso equipo de diseño "
                + "para crear soluciones impactantes.",
          "14:00", "18:00", 65000f, "Rosario", "Colonia",
          LocalDate.parse("2023-09-29"), "Estándar",
          "FusionTech", keywords3,
          this.getBufferImage("O3.jpg"), null);

    List<String> keywords4 = new ArrayList<String>();
    keywords4.add(keyword2.getNombre());
    controladorOferta.altaOfertaLaboral("Analista de Datos",
          "Ayuda a nuestros clientes a tomar decisiones informadas basadas "
                + "en análisis y visualizaciones de datos.",
          "09:00", "13:00", 40000f, "Maldonado",
          "Maldonado",
          LocalDate.parse("2023-09-19"), "Premium", "ANTEL",
          keywords4, this.getBufferImage("O4.jpg"), null);

    List<String> keywords5 = new ArrayList<String>();
    keywords5.add(keyword4.getNombre());
    controladorOferta.altaOfertaLaboral("Content Manager",
          "Gestiona y crea contenido persuasivo y relevante para impulsar "
                + "la presencia en línea de nuestros clientes.",
          "18:00", "22:00", 10000f, "Montevideo",
          "Montevideo", LocalDate.parse("2023-10-02"),
          "Destacada", "MIEM", keywords5,
          this.getBufferImage("O5.jpg"), null);

    List<String> keywords6 = new ArrayList<String>();
    keywords6.add(keyword1.getNombre());
    controladorOferta.altaOfertaLaboral("Soporte Técnico",
          "Ofrece un excelente servicio de soporte técnico a nuestros clientes, "
                + "resolviendo problemas y brindando soluciones",
          "09:00", "18:00", 30000f, "Minas", "Lavalleja",
          LocalDate.parse("2023-09-10"), "Básica",
          "TechSolutions", keywords6,
          this.getBufferImage("O6.jpg"), "Destacado");

    List<String> keywords7 = new ArrayList<String>();
    keywords7.add(keyword4.getNombre());
    controladorOferta.altaOfertaLaboral(
          "A. de Marketing Digital",
          "Unete a nuestro equipo de marketing y trabaja en estrategias digitales innovadoras.",
          "10:00", "19:00", 80000f, "Flores", "Flores",
          LocalDate.parse("2023-09-21"), "Premium",
          "EcoTech",
          keywords7, this.getBufferImage("O7.jpg"), null);

    List<String> keywords8 = new ArrayList<String>();
    keywords8.add(keyword1.getNombre());
    controladorOferta.altaOfertaLaboral("Contador Senior",
          "Unete a nuestro equipo contable y ayuda en la gestión financiera de la empresa.",
          "08:30", "17:30", 100000f, "Colonia Suiza",
          "Colonia", LocalDate.parse("2023-10-02"),
          "Destacada", "GlobalHealth", keywords8,
          this.getBufferImage("O8.jpg"), null);

    List<String> keywords9 = new ArrayList<String>();
    keywords9.add(keyword5.getNombre());
    controladorOferta.altaOfertaLaboral(
          "Técnico/a Básico Red",
          "RÉGIMEN DE CONTRATO EN FUNCION PUBLICA EN UN TODO DE ACUERDO A LA\r\n"
                + "NORMATIVA VIGENTE (LEY 16.127, DE 7 DE\r\n"
                + "AGOSTO DE 1990, ART. 1°, LITERAL A) Y B)\r\n"
                + "CON LA MODIFICACIÓN INTRODUCIDA POR \r\n"
                + "EL ART. 11 DE LA LEY 17.930 DE 19 DE DICIEMBRE DE 2005).\r\n"
                + "",
          "09:00", "17:00", 40000f, "Paysandú", "Paysandú",
          LocalDate.parse("2023-09-29"), "Premium", "ANTEL",
          keywords9, this.getBufferImage("O9.jpg"), null);

    List<String> keywords10 = new ArrayList<String>();
    keywords10.add(keyword1.getNombre());
    keywords10.add(keyword6.getNombre());
    Keyword keyword9 = controladorOferta
          .obtenerKeywords("Logística");
    keywords10.add(keyword9.getNombre());
    controladorOferta.altaOfertaLaboral(
          "Desarrollador de Software " + "Senior",
          "Únete a nuestro equipo y lidera proyectos de desar- \r\n"
                + "rollo de software sostenible y ecológico. Impulsa la\r\n"
                + "innovación y contribuye a un futuro más verde.",
          "09:00", "16:00", 123000f, "Montevideo",
          "Montevideo", LocalDate.parse("2023-10-02"),
          "Destacada", "EcoTech", keywords10,
          this.getBufferImage("O10.jpg"), "Básico");

    List<String> keywords11 = new ArrayList<String>();
    keywords11.add(keyword3.getNombre());
    controladorOferta.altaOfertaLaboral(
          "Desarrollador de Software " + "Full Stack" + "",
          "Únete a nuestro equipo para crear soluciones de soft- \r\n"
                + "ware personalizadas de extremo a extremo. Colabora\r\n"
                + "en proyectos emocionantes y desafiantes.",
          "04:00", "13:00", 135000f, "Fray Bentos",
          "Río Negro", LocalDate.parse("2023-09-25"),
          "Premium", "TechSolutions", keywords11,
          this.getBufferImage("O11.jpg"), null);

    List<String> keywords12 = new ArrayList<String>();
    keywords12.add(keyword3.getNombre());
    keywords12.add(keyword6.getNombre());
    controladorOferta.altaOfertaLaboral(
          "Gerente de Proyecto",
          "Únete a nuestro equipo de gestión de proyectos y lid- \r\n"
                + "era la entrega exitosa de soluciones de software personalizadas."
                + " Colabora con equipos multidisciplinarios y clientes exigentes.",
          "04:00", "12:00", 230000f, "Montevideo",
          "Montevideo", LocalDate.parse("2023-10-02"),
          "Destacada", "TechSolutions", keywords12,
          this.getBufferImage("O12.jpg"), null);

    List<String> keywords13 = new ArrayList<String>();
    keywords13.add(keyword1.getNombre());
    Keyword keyword10 = controladorOferta
          .obtenerKeywords("Contabilidad");
    keywords13.add(keyword10.getNombre());
    controladorOferta.altaOfertaLaboral(
          "Ingeniero de Calidad de " + "Software",
          "Asegura la calidad de nuestros productos de software\r\n"
                + "sostenibles. Únete a nosotros para garantizar un im- \r\n"
                + "pacto positivo en el medio ambiente.",
          "14:00", "18:00", 60000f, "Montevideo",
          "Montevideo", LocalDate.parse("2023-10-01"),
          "Premium", "EcoTech", keywords13,
          this.getBufferImage("O13.jpg"), null);

    controladorUsuario.registrarPostulacion(
          "Licenciada en Administración, experiencia en gestión de equipos y "
                + "proyectos. Conocimientos Microsoft Office.",
          "Estoy emocionada por la oportunidad de formar parte de un equipo "
                + "dinámico y contribuir con mis habilidades de liderazgo.",
          LocalDate.parse("2023-10-01"), "lgarcia",
          "Desarrollador Frontend", null);

    controladorUsuario.registrarPostulacion(
          "Estudiante de Comunicación, habilidades en redacción y manejo de redes "
                + "sociales. Experiencia en prácticas en medios locales.",
          "Me encantaría formar parte de un equipo que me permita desarrollar "
                + "mis habilidades en comunicación y marketing.",
          LocalDate.parse("2023-09-30"), "matilo",
          "Estratega de Negocios", null);

    controladorUsuario.registrarPostulacion(
          "Ingeniero en Sistemas, experiencia en desarrollo web y aplicaciones móviles."
                + " Conocimientos en JavaScript y React.",
          "Me entusiasma la posibilidad de trabajar en proyectos desafiantes y "
                + "seguir creciendo como profesional en el campo de la tecnología.",
          LocalDate.parse("2023-10-02"), "maro",
          "Desarrollador Frontend", null);

    controladorUsuario.registrarPostulacion(
          "Técnico en Electricidad, experiencia en mantenimiento industrial. "
                + "Conocimientos en lectura de planos eléctricos.",
          "Estoy interesado en formar parte de un equipo que me permita aplicar "
                + "mis habilidades técnicas y contribuir al mantenimiento eficiente.",
          LocalDate.parse("2023-09-30"), "javierf",
          "Diseñador UX/UI", null);

    controladorUsuario.registrarPostulacion(
          "Músico profesional, experiencia en espectáculos en vivo. "
          + "Habilidades en canto y guitarra.",
          "Me gustaría combinar mi pasión por la música con una oportunidad laboral "
                + "que me permita seguir creciendo como artista.",
          LocalDate.parse("2023-09-30"), "valen25",
          "Estratega de Negocios", null);

    controladorUsuario.registrarPostulacion(
          "Licenciada en Administración, me considero genia, experiencia en gestión "
                + "de equipos y proyectos. Conocimientos en Microsoft Office.",
          "Estoy emocionada por la oportunidad de formar parte de un equipo "
                + "dinámico y contribuir con mis habilidades de liderazgo.",
          LocalDate.parse("2023-10-02"), "lgarcia",
          "Estratega de Negocios", null);

    datosCargados = true;

  }
  
  
  /**
   * Metodo confirmar ofertas.
   */

  public void confirmarOfertas()
        throws OfertaLaboralNoExisteException {
    IcontroladorOferta controladorOferta = Fabrica
          .getInstance().obtenerControladorOferta();
    controladorOferta.aceptarRechazarOfertaLaboral(
          "Desarrollador Frontend", EstadoOferta.CONFIRMADA,
          LocalDate.parse("2023-09-30"));
    controladorOferta.aceptarRechazarOfertaLaboral(
          "Estratega de Negocios", EstadoOferta.CONFIRMADA,
          LocalDate.parse("2023-09-29"));
    controladorOferta.aceptarRechazarOfertaLaboral(
          "Diseñador UX/UI", EstadoOferta.CONFIRMADA,
          LocalDate.parse("2023-09-29"));
    controladorOferta.aceptarRechazarOfertaLaboral(
          "Soporte Técnico", EstadoOferta.CONFIRMADA,
          LocalDate.parse("2023-10-09"));
    controladorOferta.aceptarRechazarOfertaLaboral(
          "A. de Marketing Digital",
          EstadoOferta.CONFIRMADA,
          LocalDate.parse("2023-09-21"));
    controladorOferta.aceptarRechazarOfertaLaboral(
          "Contador Senior", EstadoOferta.RECHAZADA,
          LocalDate.parse("2023-10-02"));
    controladorOferta.aceptarRechazarOfertaLaboral(
          "Técnico/a Básico Red", EstadoOferta.CONFIRMADA,
          LocalDate.parse("2023-09-29"));
    controladorOferta.aceptarRechazarOfertaLaboral(
          "Gerente de Proyecto", EstadoOferta.CONFIRMADA,
          LocalDate.parse("2023-10-01"));

  }

  public static Boolean datosCargados() {
    return datosCargados;
  }
}
