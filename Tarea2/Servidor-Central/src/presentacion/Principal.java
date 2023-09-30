package presentacion;

import excepciones.KeywordNoExisteException;
import excepciones.KeywordYaExisteException;
import excepciones.OfertaLaboralNoExisteException;
import excepciones.OfertaLaboralYaExisteException;
import excepciones.PaquetePublicacionYaExisteException;
import excepciones.TipoPublicacionNoExisteException;
import excepciones.TipoPublicacionYaExisteException;
import excepciones.UsuarioEmailRepetidoException;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioYaExisteException;
import excepciones.UsuarioYaExistePostulacion;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import logica.controllers.Fabrica;
import logica.controllers.Loader;
import logica.interfaces.IcontroladorOferta;
import logica.interfaces.IcontroladorUsuario;

/**
 * Clase Principal .
 */

@SuppressWarnings("serial")
public class Principal extends JFrame {
  
  private JFrame ventanaPrincipal;
  private IcontroladorUsuario icu;
  private IcontroladorOferta ico;
  private CrearUsuario crearUsuInternalFrame;
  private ConsultarUsuario consultarUsuInternalFrame;
  private AltaOfertaLaboral crearOfertaLaboralInternalFrame;
  private ConsultaOfertaLaboral consultarOfertaInternalFrame;
  private AltaTipoPublicacionDeOfertaLab crearTipoPublicDeOfertaLabInternalFrame;
  private PostulacionOfertaLaboral postulacionOfertaLabInternalFrame;
  private ModificarDatosUsuarios modificarDatosUsuarios;
  private AceptarRechazarOferta aceptarRechazarOferta;
  private AgregarTipoPublicacionAlPaquete agregarTipoPublicacionAlPaquete;
  private RegistrarPaquete registrarPaquete;
  private ConsultaPaquete consultaPaquete;
  
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
    icu = fabrica.obtenerControladorUsuario();
    ico = fabrica.obtenerControladorOferta();
    // Se crean los InternalFrame y se incluyen al
    // Frame principal ocultos.
    // De esta forma, no es necesario crear y destruir
    // objetos lo que enlentece la
    // ejecución.
    
    crearUsuInternalFrame = new CrearUsuario(icu);
    crearUsuInternalFrame.setVisible(false);
    
    consultarUsuInternalFrame = new ConsultarUsuario(icu, ico);
    consultarUsuInternalFrame.setVisible(false);
    
    crearOfertaLaboralInternalFrame = new AltaOfertaLaboral(ico, icu);
    crearOfertaLaboralInternalFrame.setVisible(false);
    
    consultarOfertaInternalFrame = new ConsultaOfertaLaboral(ico, icu);
    consultarOfertaInternalFrame.setVisible(false);
    
    crearTipoPublicDeOfertaLabInternalFrame = new AltaTipoPublicacionDeOfertaLab(ico);
    crearTipoPublicDeOfertaLabInternalFrame.setVisible(false);
    
    postulacionOfertaLabInternalFrame = new PostulacionOfertaLaboral(ico, icu);
    postulacionOfertaLabInternalFrame.setBackground(new Color(240, 240, 240));
    postulacionOfertaLabInternalFrame.setResizable(false);
    postulacionOfertaLabInternalFrame.setVisible(false);
    
    modificarDatosUsuarios = new ModificarDatosUsuarios(icu);
    modificarDatosUsuarios.setVisible(false);
    
    aceptarRechazarOferta = new AceptarRechazarOferta(ico, icu);
    aceptarRechazarOferta.setVisible(false);
    
    agregarTipoPublicacionAlPaquete = new AgregarTipoPublicacionAlPaquete(ico);
    agregarTipoPublicacionAlPaquete.setVisible(false);
    
    registrarPaquete = new RegistrarPaquete(ico);
    registrarPaquete.setVisible(false);
    
    consultaPaquete = new ConsultaPaquete(ico);
    consultaPaquete.setVisible(false);
    
    ventanaPrincipal.getContentPane().setLayout(null);
    ventanaPrincipal.getContentPane().add(consultarUsuInternalFrame);
    ventanaPrincipal.getContentPane().add(crearUsuInternalFrame);
    ventanaPrincipal.getContentPane().add(crearOfertaLaboralInternalFrame);
    ventanaPrincipal.getContentPane().add(consultarOfertaInternalFrame);
    ventanaPrincipal.getContentPane().add(crearTipoPublicDeOfertaLabInternalFrame);
    ventanaPrincipal.getContentPane().add(postulacionOfertaLabInternalFrame);
    ventanaPrincipal.getContentPane().add(modificarDatosUsuarios);
    ventanaPrincipal.getContentPane().add(aceptarRechazarOferta);
    ventanaPrincipal.getContentPane().add(agregarTipoPublicacionAlPaquete);
    ventanaPrincipal.getContentPane().add(registrarPaquete);
    ventanaPrincipal.getContentPane().add(consultaPaquete);
  }
  
  private void initialize() {
    
    // Se crea el Frame con las dimensiones indicadas.
    ventanaPrincipal = new JFrame();
    ventanaPrincipal.setTitle("Trabajo.uy");
    ventanaPrincipal.setBounds(100, 100, 800, 700);
    ventanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    // Se crea una barra de menú (JMenuBar) con menú
    // (JMenu) desplegables.
    // Cada menú contiene diferentes opciones
    // (JMenuItem), los cuales tienen un
    // evento asociado que permite realizar una acción
    // una vez se seleccionan.
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
        } catch (ParseException | KeywordNoExisteException
            | PaquetePublicacionYaExisteException | UsuarioEmailRepetidoException
            | UsuarioYaExistePostulacion e) {
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
        // Muestro el InternalFrame para registrar un
        // usuario
        crearUsuInternalFrame.setVisible(true);
      }
    });
    menuUsuarios.add(menuItemRegistrarUsuario);
    
    JMenuItem menuConsultaUsuario = new JMenuItem("Consulta de Usuario");
    menuConsultaUsuario.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // Muestro el InternalFrame para ver información
        // de un usuario
        consultarUsuInternalFrame.cargarUsuarios();
        consultarUsuInternalFrame.setVisible(true);
      }
    });
    menuUsuarios.add(menuConsultaUsuario);
    
    JMenuItem menuModificaraUsuario = new JMenuItem("Modificar Datos de Usuario");
    menuModificaraUsuario.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // Muestro el InternalFrame para ver información
        // de un usuario
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
        // Muestro el InternalFrame para registrar una
        // oferta laboral
        crearOfertaLaboralInternalFrame.cargarEmpresas();
        // crearOfertaLaboralInternalFrame.cargarTipoPublicaciones();
        crearOfertaLaboralInternalFrame.cargarComboBoxFormaDePago();
        crearOfertaLaboralInternalFrame.cargarKeywords();
        crearOfertaLaboralInternalFrame.setVisible(true);
      }
    });
    menuOfertaLaboral.add(menuItemRegistrarOferta);
    
    JMenuItem menuItemConsultarOferta = new JMenuItem("Consultar Oferta Laboral");
    menuItemConsultarOferta.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // Muestro el InternalFrame para consultar una
        // oferta laboral
        consultarOfertaInternalFrame.cargarEmpresas();
        consultarOfertaInternalFrame.setVisible(true);
      }
    });
    menuOfertaLaboral.add(menuItemConsultarOferta);
    
    JMenuItem menuItemAltaTipoPublicDeOferta = new JMenuItem(
        "Registro Tipo de Publicacion de Oferta Laboral");
    menuItemAltaTipoPublicDeOferta.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // Muestro el InternalFrame para crear un tipo de
        // publicacion de oferta laboral
        crearTipoPublicDeOfertaLabInternalFrame.setVisible(true);
      }
    });
    menuOfertaLaboral.add(menuItemAltaTipoPublicDeOferta);
    
    JMenuItem menuItemPostulacionOfertaLab = new JMenuItem("Postulacion a Oferta Laboral");
    menuItemPostulacionOfertaLab.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // Muestro el InternalFrame para postularse a una
        // oferta laboral
        postulacionOfertaLabInternalFrame.cargarEmpresasPostulacion();
        postulacionOfertaLabInternalFrame.setVisible(true);
      }
    });
    menuOfertaLaboral.add(menuItemPostulacionOfertaLab);
    
    JMenuItem menuItemaceptarRechazarOferta = new JMenuItem("Aceptar/Rechazar Oferta Laboral");
    menuItemaceptarRechazarOferta.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // Muestro el InternalFrame para postularse a una
        // oferta laboral
        aceptarRechazarOferta.cargarEmpresas();
        aceptarRechazarOferta.setVisible(true);
      }
    });
    menuOfertaLaboral.add(menuItemaceptarRechazarOferta);
    
    JMenu menuPaquete = new JMenu("Paquetes");
    menuBar.add(menuPaquete);
    
    JMenuItem menuItemAgregarTipoPublicacionAlPaquete = new JMenuItem(
        "Agregar tipo de publicacion al paquete");
    menuItemAgregarTipoPublicacionAlPaquete.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // Muestro el InternalFrame para postularse a una
        // oferta laboral
        agregarTipoPublicacionAlPaquete.cargarPaquetes();
        agregarTipoPublicacionAlPaquete.setVisible(true);
      }
    });
    menuPaquete.add(menuItemAgregarTipoPublicacionAlPaquete);
    
    JMenuItem menuItemRegistarPaquete = new JMenuItem("Registrar paquete");
    menuItemRegistarPaquete.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        registrarPaquete.setVisible(true);
      }
    });
    menuPaquete.add(menuItemRegistarPaquete);
    
    JMenuItem menuItemConsultaPaquete = new JMenuItem("Consultar paquete");
    menuItemConsultaPaquete.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // Muestro el InternalFrame para postularse a una
        // oferta laboral
        consultaPaquete.cargarPaquetes();
        consultaPaquete.setVisible(true);
      }
    });
    menuPaquete.add(menuItemConsultaPaquete);
  }
  
  protected void cargarDatosDePrueba(ActionEvent arg0)
      throws ParseException, KeywordNoExisteException, PaquetePublicacionYaExisteException,
      UsuarioEmailRepetidoException, UsuarioYaExistePostulacion {
    try {
      
      Loader loader = new Loader();
      loader.cargarDatos();
      
      JOptionPane.showMessageDialog(this, "Los Datos de prueba se ha creado con éxito",
          "Trabajo.uy", JOptionPane.INFORMATION_MESSAGE);
      
    } catch (TipoPublicacionYaExisteException e) {
      JOptionPane.showMessageDialog(this, e.getMessage(), "Trabajo.uy",
          JOptionPane.ERROR_MESSAGE);
    } catch (KeywordYaExisteException e) {
      JOptionPane.showMessageDialog(this, e.getMessage(), "Trabajo.uy",
          JOptionPane.ERROR_MESSAGE);
    } catch (java.lang.NullPointerException e) {
      JOptionPane.showMessageDialog(this, e.getMessage(), "Trabajo.uy",
          JOptionPane.ERROR_MESSAGE);
    } catch (UsuarioYaExisteException e) {
      JOptionPane.showMessageDialog(this, e.getMessage(), "Trabajo.uy",
          JOptionPane.ERROR_MESSAGE);
    } catch (OfertaLaboralYaExisteException e) {
      JOptionPane.showMessageDialog(this, e.getMessage(), "Trabajo.uy",
          JOptionPane.ERROR_MESSAGE);
    } catch (TipoPublicacionNoExisteException e) {
      JOptionPane.showMessageDialog(this, e.getMessage(), "Trabajo.uy",
          JOptionPane.ERROR_MESSAGE);
    } catch (OfertaLaboralNoExisteException e) {
      JOptionPane.showMessageDialog(this, e.getMessage(), "Trabajo.uy",
          JOptionPane.ERROR_MESSAGE);
    } catch (UsuarioNoExisteException e) {
      JOptionPane.showMessageDialog(this, e.getMessage(), "Trabajo.uy",
          JOptionPane.ERROR_MESSAGE);
    } catch (KeywordNoExisteException e) {
      JOptionPane.showMessageDialog(this, e.getMessage(), "Trabajo.uy",
          JOptionPane.ERROR_MESSAGE);
    } catch (UsuarioEmailRepetidoException e) {
      JOptionPane.showMessageDialog(this, e.getMessage(), "Trabajo.uy",
          JOptionPane.ERROR_MESSAGE);
    }
  }
  
}
