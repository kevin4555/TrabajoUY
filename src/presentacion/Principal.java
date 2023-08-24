package presentacion;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import excepciones.KeywordYaExisteException;
import excepciones.OfertaLaboralYaExisteException;
import excepciones.TipoPublicacionNoExisteException;
import excepciones.TipoPublicacionYaExisteException;
import excepciones.UsuarioYaExisteException;
import logica.controllers.Fabrica;
import logica.interfaces.IControladorDatosDePrueba;
import logica.interfaces.IControladorOferta;
import logica.interfaces.IControladorUsuario;

public class Principal extends JFrame {

	private JPanel contentPane;
	private JFrame ventanaPrincipal;
	private IControladorUsuario ICU;
	private IControladorOferta ICO;
	private IControladorDatosDePrueba ICDP;
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
		ICDP = fabrica.obtenerControladorDatosDePrueba();

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

		JMenuItem menuCargarDatos = new JMenuItem("Cargar Datos");
		menuCargarDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ICDP.cargarDatosDePrueba();
					JOptionPane.showMessageDialog(menuCargarDatos, "Los Datos de prueba se ha creado con éxito",
							"Trabajo.uy", JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(menuCargarDatos, e.getMessage(), "Trabajo.uy",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		menuSistema.add(menuCargarDatos);

		menuSistema.add(menuSalir);

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

}