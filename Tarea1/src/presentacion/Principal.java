package presentacion;

import java.awt.EventQueue;
import java.text.DateFormat;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

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
import logica.DataTypes.DTCantidadTipoPublicacion;
import logica.classes.Keyword;
import logica.controllers.Fabrica;
import logica.handlers.ManejadorUsuario;
import logica.interfaces.IControladorOferta;
import logica.interfaces.IControladorUsuario;
import java.awt.BorderLayout;
import javax.swing.JInternalFrame;
import java.awt.Color;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Principal extends JFrame {

    private JPanel contentPane;
    private JFrame ventanaPrincipal;
    private IControladorUsuario ICU;
    private IControladorOferta ICO;
    private CrearUsuario crearUsuInternalFrame;
    private ConsultarUsuario consultarUsuInternalFrame;
    private AltaOfertaLaboral crearOfertaLaboralInternalFrame;
    private ConsultaOfertaLaboral consultarOfertaInternalFrame;
    private AltaTipoPublicacionDeOfertaLab crearTipoPublicDeOfertaLabInternalFrame;
    private PostulacionOfertaLaboral postulacionOfertaLabInternalFrame;
    private Date fecha;
    private String fechaS = "23/02/1923";

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Principal frame = new Principal();
                    frame.ventanaPrincipal.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

	/**
	 * Create the frame.
	 */
	public Principal() {
		initialize();

		Fabrica fabrica = Fabrica.getInstance();
		ICU = fabrica.obtenerControladorUsuario();
		ICO = fabrica.obtenerControladorOferta();
		this.fecha = new Date();
		// Se crean los InternalFrame y se incluyen al Frame principal ocultos.
		// De esta forma, no es necesario crear y destruir objetos lo que enlentece la
		// ejecución.

		crearUsuInternalFrame = new CrearUsuario(ICU);
		crearUsuInternalFrame.setVisible(false);

		consultarUsuInternalFrame = new ConsultarUsuario(ICU, ICO);
		consultarUsuInternalFrame.setVisible(false);

		crearOfertaLaboralInternalFrame = new AltaOfertaLaboral(ICO, ICU);
		crearOfertaLaboralInternalFrame.setVisible(false);

		consultarOfertaInternalFrame = new ConsultaOfertaLaboral(ICO, ICU);
		consultarOfertaInternalFrame.setVisible(false);

		crearTipoPublicDeOfertaLabInternalFrame = new AltaTipoPublicacionDeOfertaLab(ICO);
		crearTipoPublicDeOfertaLabInternalFrame.setVisible(false);

		postulacionOfertaLabInternalFrame = new PostulacionOfertaLaboral(ICO, ICU);
		postulacionOfertaLabInternalFrame.setBackground(new Color(240, 240, 240));
		postulacionOfertaLabInternalFrame.setResizable(false);
		postulacionOfertaLabInternalFrame.setVisible(false);

		ventanaPrincipal.getContentPane().setLayout(null);
		ventanaPrincipal.getContentPane().add(consultarUsuInternalFrame);
		ventanaPrincipal.getContentPane().add(crearUsuInternalFrame);

		
		
		ventanaPrincipal.getContentPane().add(crearOfertaLaboralInternalFrame);
		ventanaPrincipal.getContentPane().add(consultarOfertaInternalFrame);
		ventanaPrincipal.getContentPane().add(crearTipoPublicDeOfertaLabInternalFrame);
		ventanaPrincipal.getContentPane().add(postulacionOfertaLabInternalFrame);
	}

	private void initialize() {

		// Se crea el Frame con las dimensiones indicadas.
		ventanaPrincipal = new JFrame();
		ventanaPrincipal.setTitle("Trabajo.uy");
		ventanaPrincipal.setBounds(100, 100, 800, 700);
		ventanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Se crea una barra de menú (JMenuBar) con menú (JMenu) desplegables.
		// Cada menú contiene diferentes opciones (JMenuItem), los cuales tienen un
		// evento asociado que permite realizar una acción una vez se seleccionan.
		JMenuBar menuBar = new JMenuBar();
		ventanaPrincipal.setJMenuBar(menuBar);

		JMenu menuSistema = new JMenu("Sistema");
		menuBar.add(menuSistema);

		JMenuItem menuSalir = new JMenuItem("Salir");
		menuSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Salgo de la aplicación
				ventanaPrincipal.setVisible(false);
				ventanaPrincipal.dispose();
			}
		});
		menuSistema.add(menuSalir);

		JMenuItem menuCargarDatos = new JMenuItem("Cargar Datos");
		menuCargarDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					cargarDatosDePrueba(arg0);
				} catch (ParseException | KeywordNoExisteException | PaquetePublicacionYaExisteException | UsuarioEmailRepetidoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // Esta funcion carga los datos de prueba
			}
		});
		menuSistema.add(menuCargarDatos);

		JMenu menuUsuarios = new JMenu("Usuarios");
		menuBar.add(menuUsuarios);

		JMenuItem menuItemRegistrarUsuario = new JMenuItem("Registrar Usuario");
		menuItemRegistrarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Muestro el InternalFrame para registrar un usuario
				crearUsuInternalFrame.setVisible(true);
			}
		});
		menuUsuarios.add(menuItemRegistrarUsuario);

		JMenuItem menuConsultaUsuario = new JMenuItem("Consulta de Usuario");
		menuConsultaUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Muestro el InternalFrame para ver información de un usuario
				consultarUsuInternalFrame.cargarUsuarios();
				consultarUsuInternalFrame.setVisible(true);
			}
		});
		menuUsuarios.add(menuConsultaUsuario);

		JMenu menuOfertaLaboral = new JMenu("Ofertas Laborales");
		menuBar.add(menuOfertaLaboral);

        JMenuItem menuItemRegistrarOferta = new JMenuItem("Registrar Oferta Laboral");
        menuItemRegistrarOferta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Muestro el InternalFrame para registrar una oferta laboral
            	crearOfertaLaboralInternalFrame.cargarEmpresas();
            	crearOfertaLaboralInternalFrame.cargarTipoPublicaciones();
                crearOfertaLaboralInternalFrame.cargarKeywords();
            	crearOfertaLaboralInternalFrame.setVisible(true);
            }
        });
        menuOfertaLaboral.add(menuItemRegistrarOferta);

		JMenuItem menuItemConsultarOferta = new JMenuItem("Consultar Oferta Laboral");
		menuItemConsultarOferta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Muestro el InternalFrame para consultar una oferta laboral
				consultarOfertaInternalFrame.cargarEmpresas();
				consultarOfertaInternalFrame.setVisible(true);
			}
		});
		menuOfertaLaboral.add(menuItemConsultarOferta);

		JMenuItem menuItemAltaTipoPublicDeOferta = new JMenuItem("Registro Tipo de Publicacion de Oferta Laboral");
		menuItemAltaTipoPublicDeOferta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Muestro el InternalFrame para crear un tipo de publicacion de oferta laboral
				crearTipoPublicDeOfertaLabInternalFrame.setVisible(true);
			}
		});
		menuOfertaLaboral.add(menuItemAltaTipoPublicDeOferta);

		JMenuItem menuItemPostulacionOfertaLab = new JMenuItem("Postulacion a Oferta Laboral");
		menuItemPostulacionOfertaLab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Muestro el InternalFrame para postularse a una oferta laboral
				postulacionOfertaLabInternalFrame.cargarEmpresasPostulacion();
				postulacionOfertaLabInternalFrame.setVisible(true);
			}
		});
		menuOfertaLaboral.add(menuItemPostulacionOfertaLab);

	}

	@SuppressWarnings("deprecation")
	protected void cargarDatosDePrueba(ActionEvent arg0)
			throws ParseException, KeywordNoExisteException, PaquetePublicacionYaExisteException, UsuarioEmailRepetidoException {
		try {

			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

			ICU.altaPostulante("lgarcia", "Lucía", "García", "lgarcia85@gmail.com", dateFormat.parse("15/03/1985"),
					"Uruguaya");
			ICU.altaPostulante("matilo", "Matías", "López", "matias.lopez90@hotmail.com",
					dateFormat.parse("21/08/1990"), "Argentina");
			ICU.altaPostulante("maro", "María", "Rodríguez", "marrod@gmail.com", dateFormat.parse("10/11/1988"),
					"Uruguaya");
			ICU.altaPostulante("javierf", "Javier", "Fernández", "javierf93@yahoo.com", dateFormat.parse("05/06/1993"),
					"Mexicana");
			ICU.altaPostulante("valen25", "Valentina", "Martínez", "vale87@gmail.com", dateFormat.parse("25/02/1987"),
					"Uruguaya");
			ICU.altaPostulante("andpe12", "Andrés", "Pérez", "anpe92@hotmail.com", dateFormat.parse("12/04/1992"),
					"Chilena");
			ICU.altaPostulante("sicam", "Camila", "Silva", "camilasilva89@gmail.com", dateFormat.parse("30/09/1989"),
					"Uruguaya");
			ICU.altaPostulante("isabel", "Isabella", "López", "loisa@gmail.com", dateFormat.parse("07/07/1991"),
					"Uruguaya");
			ICU.altaPostulante("marram02", "Martín", "Ramírez", "marram@hotmail.com", dateFormat.parse("02/12/1986"),
					"Argentina");

			ICU.altaEmpresa("EcoTech", "Sophia", "Johnson", "info@EcoTech.com",
					"EcoTech Innovations es una empresa líder en soluciones tecnológicas sostenibles. Nuestro enfoque se centra en desarrollar y comercializar productos y servicios que aborden los desafíos ambientales más apremiantes de nuestro tiempo. Desde sistemas de energía renovable y dispositivos de monitorización ambiental hasta soluciones de gestión de residuos inteligentes, nuestra misión es proporcionar herramientas que permitan a las empresas y comunidades adoptar prácticas más ecológicas sin comprometer la eficiencia. Creemos en la convergencia armoniosa entre la tecnología la naturaleza, y trabajamos incansablemente para impulsar un futuro más limpio y sostenible.",
					"http://www.EcoTechInnovations.com");
			ICU.altaEmpresa("FusionTech", "William", "Smith", "contacto@FusionTech.net",
					"FusionTech Dynamics es una empresa pionera en el ámbito de la inteligencia artificial y la automatización avanzada. Nuestro equipo multidisciplinario de ingenieros, científicos de datos y desarrolladores crea soluciones innovadoras que aprovechan la potencia de la IA para transformar industrias. Desde la optimización de procesos industriales hasta la creación de asistentes virtuales altamente personalizados, nuestro objetivo es revolucionar la forma en que las empresas operan y se conectan con sus clientes. Creemos en la sinergia entre la mente humana y las capacidades de la IA, y trabajamos para construir un mundo donde la tecnología mejore y amplíe nuestras capacidades innatas.",
					"http://www.fusiontechdynamics.net");
			ICU.altaEmpresa("GlobalHealth", "Isabella", "Brown", "jobs@GlobalHealth.uy",
					"GlobalHealth Dynamics es una empresa comprometida con el avance de la atención médica a nivel mundial. Como líderes en el campo de la salud digital, desarrollamos plataformas y herramientas que permiten a los profesionales de la salud ofrecer diagnósticos más precisos, tratamientos personalizados y seguimiento continuo de los pacientes. Nuestra visión es crear un ecosistema de salud conectado en el que los datos médicos se utilicen de manera ética y segura para mejorar la calidad de vida de las personas. A través de la innovación constante y la colaboración con expertos m´edicos, estamos dando forma al futuro de la atención médica, donde la tecnología y la compasión se unen para salvar vidas y mejorar el bienestar en todo el mundo",
					"http://www.globalhealthdynamics.uy/info");
			ICU.altaEmpresa("ANTEL", "Washington", "Rocha", "jarrington@ANTEL.com.uy",
					"En Antel te brindamos servicios de vanguardia en tecnología de comunicación en Telefonía Móvil, Fija, Banda Ancha y Datos",
					"ANTEL.com.uy");
			ICU.altaEmpresa("MIEM", "Pablo", "Bengoechea", "eldiez@MIEM.org.uy",
					"Balance Energ´etico Nacional (BEN). La Dirección Nacional de Energía (DNE) del Ministerio de Industria, Energía y Minería (MIEM) presenta anualmente el BEN.",
					"MIEM.com.uy");
			ICU.altaEmpresa("TechSolutions", "Mercedes", "Venn", "Mercedes@TechSolutions.com.uy",
					" \"TechSolutions Inc.\" es una empresa líder en el campo de la tecnología de la información y el software. Su experiencia radica en la creación de soluciones de software a medida para compañías de distintos tamaños e industrias. Su principal enfoque se orienta hacia el desarrollo de aplicaciones empresariales vanguardistas, diseñadas para mejorar procesos, optimizar la eficiencia y proporcionar una ventaja competitiva a sus clientes. Se especializan en proporcionar innovación tecnológica para impulsar el éxito empresarial.",
					"TechSolutions.com");

			ICO.altaTipoPublicacion("Premium", "Obtén máxima visibilidad", "1", 30, 4000f,
					dateFormat.parse("10/08/2023"));
			ICO.altaTipoPublicacion("Destacada", "Destaca tu anuncio", "2", 15, 500f, dateFormat.parse("05/08/2023"));
			ICO.altaTipoPublicacion("Estándar", "Mejora la posición de tu anuncio", "3", 20, 150f,
					dateFormat.parse("15/08/2023"));
			ICO.altaTipoPublicacion("Básica", "Publica de forma sencilla en la lista de ofertas", "4", 7, 50f,
					dateFormat.parse("07/08/2023"));

			ICO.altaKeyword("Tiempo completo");
			ICO.altaKeyword("Medio tiempo");
			ICO.altaKeyword("Remoto");
			ICO.altaKeyword("Freelance");
			ICO.altaKeyword("Temporal");
			ICO.altaKeyword("Permanente");
			ICO.altaKeyword("Computación");
			ICO.altaKeyword("Administración");
			ICO.altaKeyword("Logística");
			ICO.altaKeyword("Contabilidad");

			Keyword k1 = ICO.obtenerKeywords("Tiempo completo");
			Keyword k2 = ICO.obtenerKeywords("Medio tiempo");
			Keyword k3 = ICO.obtenerKeywords("Remoto");
			Keyword k4 = ICO.obtenerKeywords("Freelance");
			Keyword k5 = ICO.obtenerKeywords("Temporal");
			Keyword k6 = ICO.obtenerKeywords("Permanente");
			Keyword k7 = ICO.obtenerKeywords("Computación");
			Keyword k8 = ICO.obtenerKeywords("Administración");
			Keyword k9 = ICO.obtenerKeywords("Logística");
			
			ArrayList<String> keywords = new ArrayList<String>();
			keywords.add(k1.getNombre());
			keywords.add(k2.getNombre());
			keywords.add(k3.getNombre());
			keywords.add(k4.getNombre());
			keywords.add(k5.getNombre());
			keywords.add(k6.getNombre());
			

			ICO.altaOfertaLaboral("Desarrollador Frontend",
					"Únete a nuestro equipo de desarrollo frontend y crea experiencias de usuario excepcionales.",
					"09:00", "18:00", 90000f, "Montevideo", "Montevideo", dateFormat.parse("14/08/2023"),
					"Premium", "EcoTech", keywords);
					
			ArrayList<String> keywords2 = new ArrayList<String>();
			keywords2.add(k5.getNombre());
			ICO.altaOfertaLaboral("Estratega de Negocios",
					"Forma parte de nuestro equipo de estrategia y contribuye al crecimiento de las empresas clientes.",
					"08:00", "17:00", 80000f, "Punta del Este", "Maldonado", dateFormat.parse("14/08/2023"),
					"Estándar","GlobalHealth", keywords2);
          
			ArrayList<String> keywords3 = new ArrayList<String>();
			keywords3.add(k2.getNombre());
			keywords3.add(k3.getNombre());
			keywords3.add(k6.getNombre());
			ICO.altaOfertaLaboral("Diseñador UX/UI",
					"Trabaja en colaboración con nuestro talentoso equipo de diseño para crear soluciones impactantes.",
					"14:00", "18:00", 65000f, "Rosario", "Colonia", dateFormat.parse("13/08/2023"), "Estándar", "FusionTech", keywords3);

			ArrayList<String> keywords4 = new ArrayList<String>();
			keywords4.add(k2.getNombre());
			ICO.altaOfertaLaboral("Analista de Datos",
					"Ayuda a nuestros clientes a tomar decisiones informadas basadas en análisis y visualizaciones de datos.",
					"09:00", "13:00", 40000f, "Maldonado", "Maldonado", dateFormat.parse("11/08/2023"),
					"Premium", "ANTEL", keywords4);

			ArrayList<String> keywords5 = new ArrayList<String>();
			keywords5.add(k4.getNombre());
			ICO.altaOfertaLaboral("Content Manager",
					"Gestiona y crea contenido persuasivo y relevante para impulsar la presencia en línea de nuestros clientes.",
					"18:00", "22:00", 10000f, "Montevideo", "Montevideo", dateFormat.parse("20/08/2023"),
					"Destacada", "MIEM", keywords5);

			ArrayList<String> keywords6 = new ArrayList<String>();
			keywords6.add(k1.getNombre());
			ICO.altaOfertaLaboral("Soporte Técnico",
					"Ofrece un excelente servicio de soporte técnico a nuestros clientes, resolviendo problemas y brindando soluciones",
					"09:00", "18:00", 30000f, "Minas", "Lavalleja", dateFormat.parse("15/08/2023"),
					"Básica", "TechSolutions", keywords6);

			ICO.altaOfertaLaboral("A. de Marketing Digital",
					"Unete a nuestro equipo de marketing y trabaja en estrategias digitales innovadoras.", "10:00",
					"19:00", 80000f, "Flores", "Flores", dateFormat.parse("15/08/2023"), "Premium", "EcoTech", new ArrayList<String>());

			ICO.altaOfertaLaboral("Contador Senior",
					"Unete a nuestro equipo contable y ayuda en la gestión financiera de la empresa.", "08:30", "17:30",
					100000f, "Colonia Suiza", "Colonia", dateFormat.parse("16/08/2023"),
					"Destacada", "GlobalHealth", new ArrayList<String>());

			ICU.registrarPostulacion(
					"Licenciada en Administración, experiencia en gestión de equipos y proyectos. Conocimientos Microsoft Office.",
					"Estoy emocionada por la oportunidad de formar parte de un equipo dinámico y contribuir con mis habilidades de liderazgo.",
					dateFormat.parse("16/08/2023"), "lgarcia", "Desarrollador Frontend");

			ICU.registrarPostulacion(
					"Estudiante de Comunicación, habilidades en redacción y manejo de redes sociales. Experiencia en prácticas en medios locales.",
					"Me encantaría formar parte de un equipo que me permita desarrollar mis habilidades en comunicación y marketing.",
					dateFormat.parse("15/08/2023"), "matilo", "Estratega de Negocios");

			ICU.registrarPostulacion(
					"Ingeniero en Sistemas, experiencia en desarrollo web y aplicaciones móviles. Conocimientos en JavaScript y React.",
					"Me entusiasma la posibilidad de trabajar en proyectos desafiantes y seguir creciendo como profesional en el campo de la tecnología.",
					dateFormat.parse("14/08/2023"), "maro", "Desarrollador Frontend");

			ICU.registrarPostulacion(
					"Técnico en Electricidad, experiencia en mantenimiento industrial. Conocimientos en lectura de planos eléctricos.",
					"Estoy interesado en formar parte de un equipo que me permita aplicar mis habilidades técnicas y contribuir al mantenimiento eficiente.",
					dateFormat.parse("13/08/2023"), "javierf", "Diseñador UX/UI");

			ICU.registrarPostulacion(
					"Músico profesional, experiencia en espectáculos en vivo. Habilidades en canto y guitarra.",
					"Me gustaría combinar mi pasión por la música con una oportunidad laboral que me permita seguir creciendo como artista.",
					dateFormat.parse("12/08/2023"), "valen25", "Estratega de Negocios");

			ICU.registrarPostulacion(
					"Licenciada en Administración, me considero genia, experiencia en gestión de equipos y proyectos. Conocimientos en Microsoft Office.",
					"Estoy emocionada por la oportunidad de formar parte de un equipo dinámico y contribuir con mis habilidades de liderazgo.",
					dateFormat.parse("16/08/2023"), "lgarcia", "Estratega de Negocios");

			ArrayList<DTCantidadTipoPublicacion> paquete1 = new ArrayList<DTCantidadTipoPublicacion>();
			ArrayList<DTCantidadTipoPublicacion> paquete2 = new ArrayList<DTCantidadTipoPublicacion>();
			ArrayList<DTCantidadTipoPublicacion> paquete3 = new ArrayList<DTCantidadTipoPublicacion>();
			ArrayList<DTCantidadTipoPublicacion> paquete4 = new ArrayList<DTCantidadTipoPublicacion>();

			DTCantidadTipoPublicacion tipo1 = new DTCantidadTipoPublicacion("Premium", 1);
			DTCantidadTipoPublicacion tipo2 = new DTCantidadTipoPublicacion("Destacada", 1);
			DTCantidadTipoPublicacion tipo3 = new DTCantidadTipoPublicacion("Estándar", 1);
			paquete1.add(tipo1);
			paquete1.add(tipo2);
			paquete1.add(tipo3);

			DTCantidadTipoPublicacion tipo4 = new DTCantidadTipoPublicacion("Estándar", 2);
			DTCantidadTipoPublicacion tipo5 = new DTCantidadTipoPublicacion("Básica", 1);
			paquete2.add(tipo4);
			paquete2.add(tipo5);

			DTCantidadTipoPublicacion tipo6 = new DTCantidadTipoPublicacion("Premium", 2);
			DTCantidadTipoPublicacion tipo7 = new DTCantidadTipoPublicacion("Estándar", 2);
			paquete3.add(tipo6);
			paquete3.add(tipo7);

			DTCantidadTipoPublicacion tipo8 = new DTCantidadTipoPublicacion("Destacada", 2);
			paquete4.add(tipo8);

			ICO.registrarPaquete("Básico", "Publica ofertas laborales en nuestra plataforma por un período de 30 días",
					30, 20f, dateFormat.parse("16/08/2023"), paquete1);

			ICO.registrarPaquete("Destacado",
					"Publica ofertas laborales destacadas que se mostrará en la parte superior de los resultados de búsqueda por 45 días",
					45, 10f, dateFormat.parse("15/08/2023"), paquete2);

			ICO.registrarPaquete("Premium",
					"Publica ofertas laborales premium que incluye promoción en nuestras redes sociales y listado en la sección destacada por 60 días",
					60, 15f, dateFormat.parse("14/08/2023"), paquete3);

			ICO.registrarPaquete("Express",
					"Publica ofertas laborales urgentes resaltada en color y se mostrará en la sección de urgente por 15 días.",
					15, 5f, dateFormat.parse("13/08/2023"), paquete4);

			JOptionPane.showMessageDialog(this, "Los Datos de prueba se ha creado con éxito", "Trabajo.uy",
					JOptionPane.INFORMATION_MESSAGE);

		} catch (TipoPublicacionYaExisteException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Trabajo.uy", JOptionPane.ERROR_MESSAGE);
		} catch (KeywordYaExisteException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Trabajo.uy", JOptionPane.ERROR_MESSAGE);
		} catch (java.lang.NullPointerException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Trabajo.uy", JOptionPane.ERROR_MESSAGE);
		} catch (UsuarioYaExisteException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Trabajo.uy", JOptionPane.ERROR_MESSAGE);
		} catch (OfertaLaboralYaExisteException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Trabajo.uy", JOptionPane.ERROR_MESSAGE);
		} catch (TipoPublicacionNoExisteException e)  {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Trabajo.uy", JOptionPane.ERROR_MESSAGE);
		} catch (OfertaLaboralNoExisteException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Trabajo.uy", JOptionPane.ERROR_MESSAGE);
		} catch (UsuarioNoExisteException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Trabajo.uy", JOptionPane.ERROR_MESSAGE);
		} catch (KeywordNoExisteException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Trabajo.uy", JOptionPane.ERROR_MESSAGE);
		} 
		catch(UsuarioEmailRepetidoException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Trabajo.uy", JOptionPane.ERROR_MESSAGE);
		}
	}

    
}