package presentacion;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioYaExisteException;
import logica.controllers.Fabrica;
import logica.interfaces.IControladorOferta;
import logica.interfaces.IControladorUsuario;
import java.awt.BorderLayout;
import javax.swing.JInternalFrame;


@SuppressWarnings("serial")
public class Principal extends JFrame {

	private JPanel contentPanel;
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
        // De esta forma, no es necesario crear y destruir objetos lo que enlentece la ejecución.

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
        postulacionOfertaLabInternalFrame.setVisible(false);     

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
        ventanaPrincipal.setBounds(100, 100, 594, 502);
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
                cargarDatosDePrueba(arg0); //Esta funcion carga los datos de prueba
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
                // Muestro el InternalFrame para crear un tipo de publicacion de  oferta laboral
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
	protected void cargarDatosDePrueba(ActionEvent arg0) {
		try {
			Calendar fecha =  new GregorianCalendar(2023, 7, 10);
			ICO.altaTipoPublicacion("Premium", "Obtén máxima visibilidad", "1", 30, 4000f, fecha.getTime());//new Calendar().set(2023,7,10).getTime());
			fecha.set(2023, 7, 5);
			ICO.altaTipoPublicacion("Destacada", "Destaca tu anuncio", "2", 15, 500f, fecha.getTime());
			fecha.set(2023, 7, 15);
			ICO.altaTipoPublicacion("Estándar", "Mejora la posición de tu anuncio", "3", 20, 150f, fecha.getTime());
			fecha.set(2023, 7, 7);
			ICO.altaTipoPublicacion("Básica", "Publica de forma sencilla en la lista de ofertas", "4", 7, 50f, fecha.getTime());
			
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
			
			ICU.altaEmpresa("EcoTech", "Sophia", "Johnson", "info@EcoTech.com", "EcoTech Innovations es una empresa líder en soluciones tecnológicas sostenibles. Nuestro enfoque se centra en desarrollar y comercializar productos y servicios que aborden los desafíos ambientales más apremiantes de nuestro tiempo. Desde sistemas de energía renovable y dispositivos de monitorización ambiental hasta soluciones de gestión de residuos inteligentes, nuestra misión es proporcionar herramientas que permitan a las empresas y comunidades adoptar prácticas más ecológicas sin comprometer la eficiencia. Creemos en la convergencia armoniosa entre la tecnología la naturaleza, y trabajamos incansablemente para impulsar un futuro más limpio y sostenible.", "http://www.EcoTechInnovations.com");
			ICU.altaEmpresa("FusionTech", "William", "Smith", "contacto@FusionTech.net", "FusionTech Dynamics es una empresa pionera en el ámbito de la inteligencia artificial y la automatización avanzada. Nuestro equipo multidisciplinario de ingenieros, científicos de datos y desarrolladores crea soluciones innovadoras que aprovechan la potencia de la IA para transformar industrias. Desde la optimización de procesos industriales hasta la creación de asistentes virtuales altamente personalizados, nuestro objetivo es revolucionar la forma en que las empresas operan y se conectan con sus clientes. Creemos en la sinergia entre la mente humana y las capacidades de la IA, y trabajamos para construir un mundo donde la tecnología mejore y amplíe nuestras capacidades innatas.", "http://www.fusiontechdynamics.net");
			ICU.altaEmpresa("GlobalHealth", "Isabella", "Brown", "jobs@GlobalHealth.uy","GlobalHealth Dynamics es una empresa comprometida con el avance de la atención médica a nivel mundial. Como líderes en el campo de la salud digital, desarrollamos plataformas y herramientas que permiten a los profesionales de la salud ofrecer diagnósticos más precisos, tratamientos personalizados y seguimiento continuo de los pacientes. Nuestra visión es crear un ecosistema de salud conectado en el que los datos médicos se utilicen de manera ética y segura para mejorar la calidad de vida de las personas. A través de la innovación constante y la colaboración con expertos m´edicos, estamos dando forma al futuro de la atención médica, donde la tecnología y la compasión se unen para salvar vidas y mejorar el bienestar en todo el mundo", "http://www.globalhealthdynamics.uy/info");
			ICU.altaEmpresa("ANTEL", "Washington", "Rocha" , "jarrington@ANTEL.com.uy", "En Antel te brindamos servicios de vanguardia en tecnología de comunicación en Telefonía Móvil, Fija, Banda Ancha y Datos", "ANTEL.com.uy");
			ICU.altaEmpresa("MIEM", "Pablo" , "Bengoechea" , "eldiez@MIEM.org.uy" , "Balance Energético Nacional (BEN). La Dirección Nacional de Energía (DNE) del Ministerio de Industria, Energía y Minería (MIEM) presenta anualmente el BEN.", "MIEM.com.uy");
			ICU.altaEmpresa("TechSolutions", "Mercedes", "Venn", "Mercedes@TechSolutions.com.uy", "”TechSolutions Inc.” es una empresa líder en el sector de tecnología de la información y el software. Se especializa en el desarrollo de soluciones de software personalizadas para empresas de diversos tamaños y sectores. Su enfoque se centra en la creación de aplicaciones empresariales innovadoras que optimizan procesos, mejoran la eficiencia y brindan una ventaja competitiva a sus clientes.", "TechSolutions.com");
			
			fecha.set(2023, 7, 14);
			ICO.altaOfertaLaboral("Desarrollador Frontend", "Únete a nuestro equipo de desarrollo frontend y crea experiencias de usuario excepcionales.", "09:00", "18:00", 90000f,"Montevideo", "Montevideo", fecha.getTime(), ICO.obtenerTipoPublicacion("Premium"));
			ICU.obtenerEmpresa("EcoTech").agregarOferta(ICO.obtenerOfertaLaboral("Desarrollador Frontend"));
			ICO.agregarKeywordEnOfertaLaboral("Tiempo completo", "Desarrollador Frontend");
			ICO.agregarKeywordEnOfertaLaboral("Medio tiempo", "Desarrollador Frontend");
			ICO.agregarKeywordEnOfertaLaboral("Remoto", "Desarrollador Frontend");
			ICO.agregarKeywordEnOfertaLaboral("Freelance", "Desarrollador Frontend");
			ICO.agregarKeywordEnOfertaLaboral("Temporal", "Desarrollador Frontend");
			ICO.agregarKeywordEnOfertaLaboral("Permanente", "Desarrollador Frontend");
			
			ICO.altaOfertaLaboral("Estratega de Negocios", "Forma parte de nuestro equipo de estrategia y contribuye al crecimiento de las empresas clientes.",
					"08:00", "17:00", 80000f, "Punta del Este" , "Maldonado",fecha.getTime(),ICO.obtenerTipoPublicacion("Estándar"));
			ICU.obtenerEmpresa("GlobalHealth").agregarOferta(ICO.obtenerOfertaLaboral("Estratega de Negocios"));
			ICO.agregarKeywordEnOfertaLaboral("Temporal", "Estratega de Negocios");
			
			fecha.set(2023, 7, 13);
			ICO.altaOfertaLaboral("Diseñador UX/UI", "Trabaja en colaboración con nuestro talentoso equipo de diseño para crear soluciones impactantes.",
					"14:00", "18:00", 65000f, "Rosario", "Colonia", fecha.getTime(), ICO.obtenerTipoPublicacion("Estándar"));
			ICU.obtenerEmpresa("FusionTech").agregarOferta(ICO.obtenerOfertaLaboral("Diseñador UX/UI"));
			ICO.agregarKeywordEnOfertaLaboral("Medio tiempo", "Diseñador UX/UI");
			ICO.agregarKeywordEnOfertaLaboral("Remoto", "Diseñador UX/UI");
			ICO.agregarKeywordEnOfertaLaboral("Permanente", "Diseñador UX/UI");
			
			fecha.set(2023, 7, 11);
			ICO.altaOfertaLaboral("Analista de Datos", "Ayuda a nuestros clientes a tomar decisiones informadas basadas en análisis y visualizaciones de datos.",
					"09:00", "13:00", 40000f, "Maldonado", "Maldonado", fecha.getTime(), ICO.obtenerTipoPublicacion("Premium"));
			ICU.obtenerEmpresa("ANTEL").agregarOferta(ICO.obtenerOfertaLaboral("Analista de Datos"));
			ICO.agregarKeywordEnOfertaLaboral("Medio tiempo", "Analista de Datos");
			
			fecha.set(2023, 7, 20);
			ICO.altaOfertaLaboral("Content Manager", "Gestiona y crea contenido persuasivo y relevante para impulsar la presencia en línea de nuestros clientes",
					"18:00", "22:00", 10000f, "Montevideo", "Montevideo", fecha.getTime(), ICO.obtenerTipoPublicacion("Destacada"));
			ICU.obtenerEmpresa("MIEM").agregarOferta(ICO.obtenerOfertaLaboral("Content Manager"));
			ICO.agregarKeywordEnOfertaLaboral("Freelance", "Content Manager");
			
			fecha.set(2023, 7, 15);
			ICO.altaOfertaLaboral("Soporte Técnico", "Ofrece un excelente servicio de soporte técnico a nuestros clientes, resolviendo problemas y brindando soluciones.",
					"09:00", "18:00", 30000f, "Minas", "Lavalleja", fecha.getTime(), ICO.obtenerTipoPublicacion("Básica"));
			ICU.obtenerEmpresa("TechSolutions").agregarOferta(ICO.obtenerOfertaLaboral("Soporte Técnico"));
			ICO.agregarKeywordEnOfertaLaboral("Tiempo completo", "Soporte Técnico");
			
			ICO.altaOfertaLaboral("A. de Marketing Digital", "Únete a nuestro equipo de marketing y trabaja en estrategias digitales innovadoras.",
					"10:00", "19:00", 80000f, "Flores", "Flores", fecha.getTime(), ICO.obtenerTipoPublicacion("Premium"));
			ICU.obtenerEmpresa("EcoTech").agregarOferta(ICO.obtenerOfertaLaboral("A. de Marketing Digital"));
			
			fecha.set(2023, 7, 16);
			ICO.altaOfertaLaboral("Contador Senior", "Únete a nuestro equipo de marketing y trabaja en estrategias digitales innovadoras.",
					"08:30", "17:30", 100000f, "Colonia Suiza", "Colonia", fecha.getTime(), ICO.obtenerTipoPublicacion("Destacada"));
			ICU.obtenerEmpresa("GlobalHealth").agregarOferta(ICO.obtenerOfertaLaboral("Contador Senior"));
			
			fecha.set(2023, 7, 16);
			/*ICO.registrarPaquete("Básico","Publica ofertas laborales en nuestra plataforma por un período de 30 días" , 3, 30, 0.20f, fecha.getTime());
			ICO.agregarTipoPublicacionAlPaquete(1, "Premium", "Básico");*/
			

				
			JOptionPane.showMessageDialog(this, "Los Datos de prueba se ha creado con éxito", "Trabajo.uy", JOptionPane.INFORMATION_MESSAGE);
			
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
		} catch (TipoPublicacionNoExisteException e ) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Trabajo.uy", JOptionPane.ERROR_MESSAGE);
		} catch (OfertaLaboralNoExisteException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Trabajo.uy", JOptionPane.ERROR_MESSAGE);
		} catch (UsuarioNoExisteException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Trabajo.uy", JOptionPane.ERROR_MESSAGE);
		} catch (KeywordNoExisteException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Trabajo.uy", JOptionPane.ERROR_MESSAGE);
		} 
	}

  


}