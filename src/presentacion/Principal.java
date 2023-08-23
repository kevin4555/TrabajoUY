package presentacion;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.Time;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import excepciones.KeywordYaExisteException;
import excepciones.TipoPublicacionYaExisteException;
import excepciones.UsuarioYaExisteException;
import logica.controllers.Fabrica;
import logica.interfaces.IControladorOferta;
import logica.interfaces.IControladorUsuario;

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
        
        // Se crean los InternalFrame y se incluyen al Frame principal ocultos.
        // De esta forma, no es necesario crear y destruir objetos lo que enlentece la ejecución.

        crearUsuInternalFrame = new CrearUsuario(ICU);
        crearUsuInternalFrame.setVisible(false);

        consultarUsuInternalFrame = new ConsultarUsuario(ICU);
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

	protected void cargarDatosDePrueba(ActionEvent arg0) {
		try {
			ICO.altaTipoPublicacion("Premium", "Obtén máxima visibilidad", "1", 30, 4000f, Date.valueOf("2023-8-10"));
			ICO.altaTipoPublicacion("Destacada", "Destaca tu anuncio", "2", 15, 500f, Date.valueOf("2023-8-5"));
			ICO.altaTipoPublicacion("Estándar", "Mejora la posición de tu anuncio", "3", 20, 150f, Date.valueOf("2023-8-15"));
			ICO.altaTipoPublicacion("Básica", "Publica de forma sencilla en la lista de ofertas", "4", 7, 50f, Date.valueOf("2023-8-7"));
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
			
			
			//ICO.altaOfertaLaboral("Desarrollador Frontend", "Únete a nuestro equipo de desarrollo frontend y crea experiencias de usuario excepcionales.",
				//Time.valueOf("09:00:00"), Time.valueOf("18:00:00"), 90000f, Date.valueOf("2023-8-14"), , );
			JOptionPane.showMessageDialog(this, "Los Datos de prueba se ha creado con éxito", "Trabajo.uy", JOptionPane.INFORMATION_MESSAGE);
			
		} catch (TipoPublicacionYaExisteException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Trabajo.uy", JOptionPane.ERROR_MESSAGE);
		} catch (KeywordYaExisteException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Trabajo.uy", JOptionPane.ERROR_MESSAGE);
		} catch (java.lang.NullPointerException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Trabajo.uy", JOptionPane.ERROR_MESSAGE);
		} catch (UsuarioYaExisteException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Trabajo.uy", JOptionPane.ERROR_MESSAGE);
		}
		
	}

  


}