package main.java.presentacion;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import main.java.controllers.Fabrica;
import main.java.controllers.Loader;
import main.java.excepciones.KeywordNoExisteException;
import main.java.excepciones.KeywordYaExisteException;
import main.java.excepciones.OfertaLaboralNoExisteException;
import main.java.excepciones.OfertaLaboralNoSePuedeFinalizar;
import main.java.excepciones.OfertaLaboralYaExisteException;
import main.java.excepciones.PaquetePublicacionNoExisteException;
import main.java.excepciones.PaquetePublicacionYaExisteException;
import main.java.excepciones.PostulanteYaEsOfertaFavoritaException;
import main.java.excepciones.TipoPublicacionNoExisteException;
import main.java.excepciones.TipoPublicacionYaExisteException;
import main.java.excepciones.UsuarioEmailRepetidoException;
import main.java.excepciones.UsuarioNoExisteException;
import main.java.excepciones.UsuarioYaEstaSeguidoException;
import main.java.excepciones.UsuarioYaExisteException;
import main.java.excepciones.UsuarioYaExistePostulacion;
import main.java.interfaces.IcontroladorOferta;
import main.java.interfaces.IcontroladorUsuario;
import main.java.webservices.Publicador;

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
  private OfertasLaboralesMasVisitadas ofertasLaboralesMasVisitadas;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          Principal frame = new Principal();
          frame.ventanaPrincipal.setVisible(true);
          // Para publicar el servidor (Cristhian)
          Publicador publicador = new Publicador();
          publicador.publicar();
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

    consultarUsuInternalFrame = new ConsultarUsuario(icu,
          ico);
    consultarUsuInternalFrame.setVisible(false);

    crearOfertaLaboralInternalFrame = new AltaOfertaLaboral(
          ico, icu);
    crearOfertaLaboralInternalFrame.setVisible(false);

    consultarOfertaInternalFrame =
          new ConsultaOfertaLaboral(
                ico, icu);
    consultarOfertaInternalFrame.setVisible(false);

    crearTipoPublicDeOfertaLabInternalFrame =
          new AltaTipoPublicacionDeOfertaLab(
                ico);
    crearTipoPublicDeOfertaLabInternalFrame
          .setVisible(false);

    postulacionOfertaLabInternalFrame =
          new PostulacionOfertaLaboral(
                ico, icu);
    postulacionOfertaLabInternalFrame
          .setBackground(new Color(240, 240, 240));
    postulacionOfertaLabInternalFrame.setResizable(false);
    postulacionOfertaLabInternalFrame.setVisible(false);

    modificarDatosUsuarios = new ModificarDatosUsuarios(
          icu);
    modificarDatosUsuarios.setVisible(false);

    aceptarRechazarOferta = new AceptarRechazarOferta(ico,
          icu);
    aceptarRechazarOferta.setVisible(false);

    agregarTipoPublicacionAlPaquete =
          new AgregarTipoPublicacionAlPaquete(
                ico);
    agregarTipoPublicacionAlPaquete.setVisible(false);

    registrarPaquete = new RegistrarPaquete(ico);
    registrarPaquete.setVisible(false);

    consultaPaquete = new ConsultaPaquete(ico);
    consultaPaquete.setVisible(false);

    ofertasLaboralesMasVisitadas =
          new OfertasLaboralesMasVisitadas(ico);
    ofertasLaboralesMasVisitadas.setVisible(false);

    ventanaPrincipal.getContentPane().setLayout(null);
    ventanaPrincipal.getContentPane()
          .add(consultarUsuInternalFrame);
    ventanaPrincipal.getContentPane()
          .add(crearUsuInternalFrame);
    ventanaPrincipal.getContentPane()
          .add(crearOfertaLaboralInternalFrame);
    ventanaPrincipal.getContentPane()
          .add(consultarOfertaInternalFrame);
    ventanaPrincipal.getContentPane()
          .add(crearTipoPublicDeOfertaLabInternalFrame);
    ventanaPrincipal.getContentPane()
          .add(postulacionOfertaLabInternalFrame);
    ventanaPrincipal.getContentPane()
          .add(modificarDatosUsuarios);
    ventanaPrincipal.getContentPane()
          .add(aceptarRechazarOferta);
    ventanaPrincipal.getContentPane()
          .add(agregarTipoPublicacionAlPaquete);
    ventanaPrincipal.getContentPane().add(registrarPaquete);
    ventanaPrincipal.getContentPane().add(consultaPaquete);
    ventanaPrincipal.getContentPane()
          .add(ofertasLaboralesMasVisitadas);
  }

  private void initialize() {

    // Se crea el Frame con las dimensiones indicadas.
    ventanaPrincipal = new JFrame();
    ventanaPrincipal.setTitle("Trabajo.uy");
    ventanaPrincipal.setBounds(100, 100, 800, 700);
    ventanaPrincipal
          .setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

    JMenuItem menuCargarDatos = new JMenuItem(
          "Cargar Datos");
    menuCargarDatos.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        try {
          try {
            cargarDatosDePrueba(arg0);
          } catch (UsuarioYaEstaSeguidoException e) {
            // TODO Auto-generated catch block
            
          } catch (PostulanteYaEsOfertaFavoritaException e) {
            // TODO Auto-generated catch block
            
          } catch (OfertaLaboralNoSePuedeFinalizar e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        } catch (ParseException | KeywordNoExisteException
              | PaquetePublicacionYaExisteException
              | UsuarioEmailRepetidoException
              | UsuarioYaExistePostulacion
              | PaquetePublicacionNoExisteException e) {
          // TODO Auto-generated catch block

        } // Esta funcion carga los datos de prueba
      }
    });
    menuSistema.add(menuCargarDatos);

    JMenu menuUsuarios = new JMenu("Usuarios");
    menuBar.add(menuUsuarios);

    JMenuItem menuItemRegistrarUsuario = new JMenuItem(
          "Registrar Usuario");
    menuItemRegistrarUsuario
          .addActionListener(new ActionListener() {
            public void
                  actionPerformed(ActionEvent evento) {
              // Muestro el InternalFrame para registrar un
              // usuario
              crearUsuInternalFrame.setVisible(true);
            }
          });
    menuUsuarios.add(menuItemRegistrarUsuario);

    JMenuItem menuConsultaUsuario = new JMenuItem(
          "Consulta de Usuario");
    menuConsultaUsuario
          .addActionListener(new ActionListener() {
            public void
                  actionPerformed(ActionEvent evento) {
              // Muestro el InternalFrame para ver
              // información
              // de un usuario
              consultarUsuInternalFrame.cargarUsuarios();
              consultarUsuInternalFrame.setVisible(true);
            }
          });
    menuUsuarios.add(menuConsultaUsuario);

    JMenuItem menuModificaraUsuario = new JMenuItem(
          "Modificar Datos de Usuario");
    menuModificaraUsuario
          .addActionListener(new ActionListener() {
            public void
                  actionPerformed(ActionEvent evento) {
              // Muestro el InternalFrame para ver
              // información
              // de un usuario
              modificarDatosUsuarios.cargarUsuarios();
              modificarDatosUsuarios.setVisible(true);
            }
          });
    menuUsuarios.add(menuModificaraUsuario);

    JMenu menuOfertaLaboral = new JMenu(
          "Ofertas Laborales");
    menuBar.add(menuOfertaLaboral);

    JMenuItem menuItemRegistrarOferta = new JMenuItem(
          "Registrar Oferta Laboral");
    menuItemRegistrarOferta
          .addActionListener(new ActionListener() {
            public void
                  actionPerformed(ActionEvent evento) {
              // Muestro el InternalFrame para registrar una
              // oferta laboral
              crearOfertaLaboralInternalFrame
                    .cargarEmpresas();
              // crearOfertaLaboralInternalFrame.cargarTipoPublicaciones();
              crearOfertaLaboralInternalFrame
                    .cargarComboBoxFormaDePago();
              crearOfertaLaboralInternalFrame
                    .cargarKeywords();
              crearOfertaLaboralInternalFrame
                    .setVisible(true);
            }
          });
    menuOfertaLaboral.add(menuItemRegistrarOferta);

    JMenuItem menuItemConsultarOferta = new JMenuItem(
          "Consultar Oferta Laboral");
    menuItemConsultarOferta
          .addActionListener(new ActionListener() {
            public void
                  actionPerformed(ActionEvent evento) {
              // Muestro el InternalFrame para consultar una
              // oferta laboral
              consultarOfertaInternalFrame.cargarEmpresas();
              consultarOfertaInternalFrame.setVisible(true);
            }
          });
    menuOfertaLaboral.add(menuItemConsultarOferta);

    JMenuItem menuItemAltaTipoPublicDeOferta =
          new JMenuItem(
                "Registro Tipo de Publicacion de Oferta Laboral");
    menuItemAltaTipoPublicDeOferta
          .addActionListener(new ActionListener() {
            public void
                  actionPerformed(ActionEvent evento) {
              // Muestro el InternalFrame para crear un tipo
              // de
              // publicacion de oferta laboral
              crearTipoPublicDeOfertaLabInternalFrame
                    .setVisible(true);
            }
          });
    menuOfertaLaboral.add(menuItemAltaTipoPublicDeOferta);

    JMenuItem menuItemPostulacionOfertaLab = new JMenuItem(
          "Postulacion a Oferta Laboral");
    menuItemPostulacionOfertaLab
          .addActionListener(new ActionListener() {
            public void
                  actionPerformed(ActionEvent evento) {
              // Muestro el InternalFrame para postularse a
              // una
              // oferta laboral
              postulacionOfertaLabInternalFrame
                    .cargarEmpresasPostulacion();
              postulacionOfertaLabInternalFrame
                    .setVisible(true);
            }
          });
    menuOfertaLaboral.add(menuItemPostulacionOfertaLab);

    JMenuItem menuItemaceptarRechazarOferta = new JMenuItem(
          "Aceptar/Rechazar Oferta Laboral");
    menuItemaceptarRechazarOferta
          .addActionListener(new ActionListener() {
            public void
                  actionPerformed(ActionEvent evento) {
              // Muestro el InternalFrame para postularse a
              // una
              // oferta laboral
              aceptarRechazarOferta.cargarEmpresas();
              aceptarRechazarOferta.setVisible(true);
            }
          });
    menuOfertaLaboral.add(menuItemaceptarRechazarOferta);

    JMenuItem menuItemofertasLaboralesMasVisitadas =
          new JMenuItem(
                "Oferta Laborales Mas Visitadas");
    menuItemofertasLaboralesMasVisitadas
          .addActionListener(new ActionListener() {
            public void
                  actionPerformed(ActionEvent evento) {
              // Muestro el InternalFrame para postularse a
              // una
              // oferta laboral
              ofertasLaboralesMasVisitadas
                    .cargarOfertasMasVisitadas();
              ofertasLaboralesMasVisitadas.setVisible(true);
            }
          });
    menuOfertaLaboral
          .add(menuItemofertasLaboralesMasVisitadas);

    JMenu menuPaquete = new JMenu("Paquetes");
    menuBar.add(menuPaquete);

    JMenuItem menuItemAgregarTipoPublicacionAlPaquete =
          new JMenuItem(
                "Agregar tipo de publicacion al paquete");
    menuItemAgregarTipoPublicacionAlPaquete
          .addActionListener(new ActionListener() {
            public void
                  actionPerformed(ActionEvent evento) {
              // Muestro el InternalFrame para postularse a
              // una
              // oferta laboral
              agregarTipoPublicacionAlPaquete
                    .cargarPaquetes();
              agregarTipoPublicacionAlPaquete
                    .setVisible(true);
            }
          });
    menuPaquete
          .add(menuItemAgregarTipoPublicacionAlPaquete);

    JMenuItem menuItemRegistarPaquete = new JMenuItem(
          "Registrar paquete");
    menuItemRegistarPaquete
          .addActionListener(new ActionListener() {
            public void
                  actionPerformed(ActionEvent evento) {
              registrarPaquete.setVisible(true);
            }
          });
    menuPaquete.add(menuItemRegistarPaquete);

    JMenuItem menuItemConsultaPaquete = new JMenuItem(
          "Consultar paquete");
    menuItemConsultaPaquete
          .addActionListener(new ActionListener() {
            public void
                  actionPerformed(ActionEvent evento) {
              // Muestro el InternalFrame para postularse a
              // una
              // oferta laboral
              consultaPaquete.cargarPaquetes();
              consultaPaquete.setVisible(true);
            }
          });
    menuPaquete.add(menuItemConsultaPaquete);
  }

  protected void cargarDatosDePrueba(ActionEvent arg0)
        throws ParseException, KeywordNoExisteException,
        PaquetePublicacionYaExisteException,
        UsuarioEmailRepetidoException,
        UsuarioYaExistePostulacion,
        PaquetePublicacionNoExisteException,
        UsuarioYaEstaSeguidoException,
        PostulanteYaEsOfertaFavoritaException,
        OfertaLaboralNoSePuedeFinalizar {
    try {

      Loader loader = new Loader();
      loader.cargarDatos();
      loader.confirmarOfertas();

      JOptionPane.showMessageDialog(this,
            "Los Datos de prueba se ha creado con éxito",
            "Trabajo.uy", JOptionPane.INFORMATION_MESSAGE);

    } catch (TipoPublicacionYaExisteException e) {
      JOptionPane.showMessageDialog(this, e.getMessage(),
            "Trabajo.uy", JOptionPane.ERROR_MESSAGE);
    } catch (KeywordYaExisteException e) {
      JOptionPane.showMessageDialog(this, e.getMessage(),
            "Trabajo.uy", JOptionPane.ERROR_MESSAGE);
    } catch (java.lang.NullPointerException e) {
      JOptionPane.showMessageDialog(this, e.getMessage(),
            "Trabajo.uy", JOptionPane.ERROR_MESSAGE);
    } catch (UsuarioYaExisteException e) {
      JOptionPane.showMessageDialog(this, e.getMessage(),
            "Trabajo.uy", JOptionPane.ERROR_MESSAGE);
    } catch (OfertaLaboralYaExisteException e) {
      JOptionPane.showMessageDialog(this, e.getMessage(),
            "Trabajo.uy", JOptionPane.ERROR_MESSAGE);
    } catch (TipoPublicacionNoExisteException e) {
      JOptionPane.showMessageDialog(this, e.getMessage(),
            "Trabajo.uy", JOptionPane.ERROR_MESSAGE);
    } catch (OfertaLaboralNoExisteException e) {
      JOptionPane.showMessageDialog(this, e.getMessage(),
            "Trabajo.uy", JOptionPane.ERROR_MESSAGE);
    } catch (UsuarioNoExisteException e) {
      JOptionPane.showMessageDialog(this, e.getMessage(),
            "Trabajo.uy", JOptionPane.ERROR_MESSAGE);
    } catch (KeywordNoExisteException e) {
      JOptionPane.showMessageDialog(this, e.getMessage(),
            "Trabajo.uy", JOptionPane.ERROR_MESSAGE);
    } catch (UsuarioEmailRepetidoException e) {
      JOptionPane.showMessageDialog(this, e.getMessage(),
            "Trabajo.uy", JOptionPane.ERROR_MESSAGE);
    } catch (IOException evento) {
      JOptionPane.showMessageDialog(this,
            evento.getMessage(), "Trabajo.uy",
            JOptionPane.ERROR_MESSAGE);
    }
  }

}
