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
import logica.controllers.Loader;
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
    private ModificarDatosUsuarios modificarDatosUsuarios;
    private Aceptar_Rechazar_Oferta aceptarRechazarOferta;
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
		
		modificarDatosUsuarios = new ModificarDatosUsuarios(ICU);
		modificarDatosUsuarios.setVisible(false);
		
		aceptarRechazarOferta = new Aceptar_Rechazar_Oferta(ICO, ICU);
		aceptarRechazarOferta.setVisible(false);
		

		ventanaPrincipal.getContentPane().setLayout(null);
		ventanaPrincipal.getContentPane().add(consultarUsuInternalFrame);
		ventanaPrincipal.getContentPane().add(crearUsuInternalFrame);
		ventanaPrincipal.getContentPane().add(crearOfertaLaboralInternalFrame);
		ventanaPrincipal.getContentPane().add(consultarOfertaInternalFrame);
		ventanaPrincipal.getContentPane().add(crearTipoPublicDeOfertaLabInternalFrame);
		ventanaPrincipal.getContentPane().add(postulacionOfertaLabInternalFrame);
		ventanaPrincipal.getContentPane().add(modificarDatosUsuarios);
		ventanaPrincipal.getContentPane().add(aceptarRechazarOferta);
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
		
		JMenuItem menuModificaraUsuario = new JMenuItem("Modificar Datos de Usuario");
		menuModificaraUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Muestro el InternalFrame para ver información de un usuario
				modificarDatosUsuarios.cargarUsuarios();
				modificarDatosUsuarios.setVisible(true);
			}
		});
		menuUsuarios.add(menuModificaraUsuario);

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
		
		JMenuItem menuItemaceptarRechazarOferta = new JMenuItem("Aceptar/Rechazar Oferta Laboral");
		menuItemaceptarRechazarOferta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Muestro el InternalFrame para postularse a una oferta laboral
				aceptarRechazarOferta.cargarEmpresas();
				aceptarRechazarOferta.setVisible(true);
			}
		});
		menuOfertaLaboral.add(menuItemaceptarRechazarOferta);
		
		

	}

	@SuppressWarnings("deprecation")
	protected void cargarDatosDePrueba(ActionEvent arg0)
			throws ParseException, KeywordNoExisteException, PaquetePublicacionYaExisteException, UsuarioEmailRepetidoException {
		try {

			Loader loader = new Loader();
			loader.cargarDatos();

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
