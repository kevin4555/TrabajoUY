package presentacion;

import excepciones.OfertaLaboralNoExisteException;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioNoExistePostulacion;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import logica.datatypes.DtOfertaLaboral;
import logica.datatypes.Dtempresa;
import logica.datatypes.Dtpostulacion;
import logica.datatypes.Dtpostulante;
import logica.datatypes.Dtusuario;
import logica.interfaces.IcontroladorOferta;
import logica.interfaces.IcontroladorUsuario;

/**
 * Clase consultar usuario .
 */

@SuppressWarnings("serial")
public class ConsultarUsuario extends JInternalFrame {
  // private JTextField textFieldNickName;
  private JTextField textFieldNombre;
  private JTextField textFieldApellido;
  private JTextField textFieldEmail;
  private JTextField textFieldHorarioOferta;
  private JTextField textFieldRemuneracion;
  private JTextField textFieldCiudad;
  private JTextField textFieldDepartamento;
  private JTextField textFieldSitioWeb;
  private JTextField textFieldFechaNacimiento;
  private JTextField textFieldNacionalidad;
  private IcontroladorUsuario controladorUsuario;
  private IcontroladorOferta controladorOferta;
  private JComboBox<String> comboBoxSeleccionUsuario;
  private JPanel panelEmpresa;
  private JPanel panelPostulante;
  private JLayeredPane layeredPane;
  private JComboBox<String> comboBoxSeleccionOferta;
  private JScrollPane scrollPane;
  private JTextArea textAreaDescripcion;
  private String usuarioSeleccionado;
  private String ofertaSeleccionada;
  private JButton btnCerrar;
  private String tipoUsuario = "";
  private JPanel panelDatos;
  private JLabel lblMotivacion = new JLabel("Motivacion");
  private JLabel labelCvReducido = new JLabel("CV Reducido");
  private JLabel labelFechaPostulacion = new JLabel("Fecha Postulacion");
  private JTextField textFieldFechaPostulacion;
  private JLabel lblNewLabel1;
  private JTextPane textPane;
  private JTextPane textPaneMotivacion;
  private JTextPane textPaneCvReducido;
  
  /**
   * Constructor .
   */
  
  public ConsultarUsuario(IcontroladorUsuario contrUsuario, IcontroladorOferta contrOferta) {
    setTitle("Consulta de Usuarios");
    this.usuarioSeleccionado = "";
    this.ofertaSeleccionada = "";
    
    setIconifiable(true);
    setResizable(true);
    setMaximizable(true);
    this.controladorUsuario = contrUsuario;
    controladorOferta = contrOferta;
    getContentPane().setLayout(new BorderLayout(0, 0));
    setBounds(80, 80, 644, 544);
    
    JPanel panelBotones = new JPanel();
    getContentPane().add(panelBotones, BorderLayout.SOUTH);
    panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 120, 20));
    
    this.btnCerrar = new JButton("Cerrar");
    btnCerrar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evento) {
        limpiarTodosLosDatos();
        dispose();
        
      }
    });
    panelBotones.add(btnCerrar);
    
    this.panelDatos = new JPanel();
    getContentPane().add(panelDatos, BorderLayout.CENTER);
    GridBagLayout gblPanelDatos = new GridBagLayout();
    gblPanelDatos.columnWidths = new int[] { 113, 739, 0 };
    gblPanelDatos.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0 };
    gblPanelDatos.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
    gblPanelDatos.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0,
        0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0 };
    panelDatos.setLayout(gblPanelDatos);
    
    GridBagConstraints gbcLblSeleccion = new GridBagConstraints();
    gbcLblSeleccion.insets = new Insets(0, 0, 5, 5);
    gbcLblSeleccion.anchor = GridBagConstraints.EAST;
    gbcLblSeleccion.gridx = 0;
    gbcLblSeleccion.gridy = 1;
    JLabel lblSeleccion = new JLabel("Seleccionar Usuario:");
    panelDatos.add(lblSeleccion, gbcLblSeleccion);
    
    this.comboBoxSeleccionUsuario = new JComboBox<String>();
    this.comboBoxSeleccionUsuario.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evento) {
        try {
          cargarDatosUsuarios(evento);
        } catch (UsuarioNoExisteException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }
        
      }
    });
    
    GridBagConstraints gbcComboBoxSeleccionUsuario = new GridBagConstraints();
    gbcComboBoxSeleccionUsuario.insets = new Insets(0, 0, 5, 0);
    gbcComboBoxSeleccionUsuario.fill = GridBagConstraints.HORIZONTAL;
    gbcComboBoxSeleccionUsuario.gridx = 1;
    gbcComboBoxSeleccionUsuario.gridy = 1;
    panelDatos.add(this.comboBoxSeleccionUsuario, gbcComboBoxSeleccionUsuario);
    
    GridBagConstraints gbcLblNombre = new GridBagConstraints();
    gbcLblNombre.anchor = GridBagConstraints.EAST;
    gbcLblNombre.insets = new Insets(0, 0, 5, 5);
    gbcLblNombre.gridx = 0;
    gbcLblNombre.gridy = 3;
    JLabel lblNombre = new JLabel("Nombre:");
    panelDatos.add(lblNombre, gbcLblNombre);
    
    this.textFieldNombre = new JTextField();
    this.textFieldNombre.setEditable(false);
    GridBagConstraints gbcTextFieldNombre = new GridBagConstraints();
    gbcTextFieldNombre.insets = new Insets(0, 0, 5, 0);
    gbcTextFieldNombre.fill = GridBagConstraints.HORIZONTAL;
    gbcTextFieldNombre.gridx = 1;
    gbcTextFieldNombre.gridy = 3;
    panelDatos.add(textFieldNombre, gbcTextFieldNombre);
    textFieldNombre.setColumns(10);
    
    GridBagConstraints gbcLblApellido = new GridBagConstraints();
    gbcLblApellido.anchor = GridBagConstraints.EAST;
    gbcLblApellido.insets = new Insets(0, 0, 5, 5);
    gbcLblApellido.gridx = 0;
    gbcLblApellido.gridy = 4;
    JLabel lblApellido = new JLabel("Apellido:");
    panelDatos.add(lblApellido, gbcLblApellido);
    
    this.textFieldApellido = new JTextField();
    this.textFieldApellido.setEditable(false);
    GridBagConstraints gbcTextFieldApellido = new GridBagConstraints();
    gbcTextFieldApellido.insets = new Insets(0, 0, 5, 0);
    gbcTextFieldApellido.fill = GridBagConstraints.HORIZONTAL;
    gbcTextFieldApellido.gridx = 1;
    gbcTextFieldApellido.gridy = 4;
    panelDatos.add(this.textFieldApellido, gbcTextFieldApellido);
    this.textFieldApellido.setColumns(10);
    
    GridBagConstraints gbcLblEmail = new GridBagConstraints();
    gbcLblEmail.anchor = GridBagConstraints.EAST;
    gbcLblEmail.insets = new Insets(0, 0, 5, 5);
    gbcLblEmail.gridx = 0;
    gbcLblEmail.gridy = 5;
    JLabel lblEmail = new JLabel("Email:");
    panelDatos.add(lblEmail, gbcLblEmail);
    
    this.textFieldEmail = new JTextField();
    this.textFieldEmail.setEditable(false);
    GridBagConstraints gbcTextFieldEmail = new GridBagConstraints();
    gbcTextFieldEmail.insets = new Insets(0, 0, 5, 0);
    gbcTextFieldEmail.fill = GridBagConstraints.HORIZONTAL;
    gbcTextFieldEmail.gridx = 1;
    gbcTextFieldEmail.gridy = 5;
    panelDatos.add(this.textFieldEmail, gbcTextFieldEmail);
    this.textFieldEmail.setColumns(10);
    
    lblNewLabel1 = new JLabel("Foto de perfil:");
    GridBagConstraints gbcLblNewLabel1 = new GridBagConstraints();
    gbcLblNewLabel1.insets = new Insets(0, 0, 5, 5);
    gbcLblNewLabel1.gridx = 0;
    gbcLblNewLabel1.gridy = 7;
    panelDatos.add(lblNewLabel1, gbcLblNewLabel1);
    
    textPane = new JTextPane();
    GridBagConstraints gbcTextPane = new GridBagConstraints();
    gbcTextPane.insets = new Insets(0, 0, 5, 0);
    gbcTextPane.fill = GridBagConstraints.BOTH;
    gbcTextPane.gridx = 1;
    gbcTextPane.gridy = 7;
    textPane.setEditable(false);
    panelDatos.add(textPane, gbcTextPane);
    
    layeredPane = new JLayeredPane();
    GridBagConstraints gbcLayeredPane = new GridBagConstraints();
    gbcLayeredPane.gridheight = 3;
    gbcLayeredPane.gridwidth = 2;
    gbcLayeredPane.insets = new Insets(0, 0, 5, 0);
    gbcLayeredPane.fill = GridBagConstraints.BOTH;
    gbcLayeredPane.gridx = 0;
    gbcLayeredPane.gridy = 8;
    panelDatos.add(layeredPane, gbcLayeredPane);
    layeredPane.setLayout(new CardLayout(0, 0));
    
    panelEmpresa = new JPanel();
    layeredPane.add(panelEmpresa, "name_918030925291900");
    GridBagLayout gblPanelEmpresa = new GridBagLayout();
    gblPanelEmpresa.columnWidths = new int[] { 114, 0, 0 };
    gblPanelEmpresa.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
    gblPanelEmpresa.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
    gblPanelEmpresa.rowWeights = new double[] { 1.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
    panelEmpresa.setLayout(gblPanelEmpresa);
    panelEmpresa.setPreferredSize(new Dimension(200, 200));
    
    GridBagConstraints gbcLblSitioWeb = new GridBagConstraints();
    gbcLblSitioWeb.anchor = GridBagConstraints.EAST;
    gbcLblSitioWeb.insets = new Insets(0, 0, 5, 5);
    gbcLblSitioWeb.gridx = 0;
    gbcLblSitioWeb.gridy = 1;
    JLabel lblSitioWeb = new JLabel("Sitio Web:");
    panelEmpresa.add(lblSitioWeb, gbcLblSitioWeb);
    
    this.textFieldSitioWeb = new JTextField();
    this.textFieldSitioWeb.setEditable(false);
    GridBagConstraints gbcTextFieldSitioWeb = new GridBagConstraints();
    gbcTextFieldSitioWeb.insets = new Insets(0, 0, 5, 0);
    gbcTextFieldSitioWeb.fill = GridBagConstraints.HORIZONTAL;
    gbcTextFieldSitioWeb.gridx = 1;
    gbcTextFieldSitioWeb.gridy = 1;
    panelEmpresa.add(this.textFieldSitioWeb, gbcTextFieldSitioWeb);
    this.textFieldSitioWeb.setColumns(10);
    
    GridBagConstraints gbcLblDescripcionEmpresa = new GridBagConstraints();
    gbcLblDescripcionEmpresa.insets = new Insets(0, 0, 5, 5);
    gbcLblDescripcionEmpresa.anchor = GridBagConstraints.EAST;
    gbcLblDescripcionEmpresa.gridx = 0;
    gbcLblDescripcionEmpresa.gridy = 2;
    JLabel lblDescripcionEmpresa = new JLabel("Descripcion:");
    panelEmpresa.add(lblDescripcionEmpresa, gbcLblDescripcionEmpresa);
    
    scrollPane = new JScrollPane();
    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    scrollPane.setEnabled(false);
    scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    GridBagConstraints gbcScrollPane = new GridBagConstraints();
    gbcScrollPane.gridheight = 2;
    gbcScrollPane.insets = new Insets(0, 0, 5, 0);
    gbcScrollPane.fill = GridBagConstraints.BOTH;
    gbcScrollPane.gridx = 1;
    gbcScrollPane.gridy = 2;
    panelEmpresa.add(scrollPane, gbcScrollPane);
    
    textAreaDescripcion = new JTextArea();
    textAreaDescripcion.setLineWrap(true);
    textAreaDescripcion.setWrapStyleWord(true);
    textAreaDescripcion.setEditable(false);
    scrollPane.setViewportView(textAreaDescripcion);
    
    panelPostulante = new JPanel();
    layeredPane.add(panelPostulante, "name_919472867094100");
    GridBagLayout gblPanelPostulante = new GridBagLayout();
    gblPanelPostulante.columnWidths = new int[] { 113, 0, 0 };
    gblPanelPostulante.rowHeights = new int[] { 0, 0, 0 };
    gblPanelPostulante.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
    gblPanelPostulante.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
    panelPostulante.setLayout(gblPanelPostulante);
    
    GridBagConstraints gbcLblFechaNacimiento = new GridBagConstraints();
    gbcLblFechaNacimiento.insets = new Insets(0, 0, 5, 5);
    gbcLblFechaNacimiento.anchor = GridBagConstraints.EAST;
    gbcLblFechaNacimiento.gridx = 0;
    gbcLblFechaNacimiento.gridy = 0;
    JLabel lblFechaNacimiento = new JLabel("Fecha de Nacimiento:");
    panelPostulante.add(lblFechaNacimiento, gbcLblFechaNacimiento);
    
    this.textFieldFechaNacimiento = new JTextField();
    this.textFieldFechaNacimiento.setEditable(false);
    GridBagConstraints gbcTextFieldFechaNacimiento = new GridBagConstraints();
    gbcTextFieldFechaNacimiento.insets = new Insets(0, 0, 5, 0);
    gbcTextFieldFechaNacimiento.fill = GridBagConstraints.HORIZONTAL;
    gbcTextFieldFechaNacimiento.gridx = 1;
    gbcTextFieldFechaNacimiento.gridy = 0;
    panelPostulante.add(this.textFieldFechaNacimiento, gbcTextFieldFechaNacimiento);
    this.textFieldFechaNacimiento.setColumns(10);
    
    GridBagConstraints gbcLblNacionalidad = new GridBagConstraints();
    gbcLblNacionalidad.anchor = GridBagConstraints.EAST;
    gbcLblNacionalidad.insets = new Insets(0, 0, 0, 5);
    gbcLblNacionalidad.gridx = 0;
    gbcLblNacionalidad.gridy = 1;
    JLabel lblNacionalidad = new JLabel("Nacionalidad:");
    panelPostulante.add(lblNacionalidad, gbcLblNacionalidad);
    
    this.textFieldNacionalidad = new JTextField();
    this.textFieldNacionalidad.setEditable(false);
    GridBagConstraints gbcTextFieldNacionalidad = new GridBagConstraints();
    gbcTextFieldNacionalidad.fill = GridBagConstraints.HORIZONTAL;
    gbcTextFieldNacionalidad.gridx = 1;
    gbcTextFieldNacionalidad.gridy = 1;
    panelPostulante.add(this.textFieldNacionalidad, gbcTextFieldNacionalidad);
    this.textFieldNacionalidad.setColumns(10);
    
    GridBagConstraints gbcLblOfertas = new GridBagConstraints();
    gbcLblOfertas.anchor = GridBagConstraints.EAST;
    gbcLblOfertas.insets = new Insets(0, 0, 5, 5);
    gbcLblOfertas.gridx = 0;
    gbcLblOfertas.gridy = 12;
    JLabel lblOfertas = new JLabel("Ofertas Laborales:");
    panelDatos.add(lblOfertas, gbcLblOfertas);
    
    this.comboBoxSeleccionOferta = new JComboBox<String>();
    this.comboBoxSeleccionOferta.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evento) {
        try {
          cargarDatosOferta(evento);
        } catch (OfertaLaboralNoExisteException | UsuarioNoExistePostulacion evento1) {
          // no imprime nada
        }
      }
    });
    GridBagConstraints gbcComboBoxSeleccionOferta = new GridBagConstraints();
    gbcComboBoxSeleccionOferta.insets = new Insets(0, 0, 5, 0);
    gbcComboBoxSeleccionOferta.fill = GridBagConstraints.HORIZONTAL;
    gbcComboBoxSeleccionOferta.gridx = 1;
    gbcComboBoxSeleccionOferta.gridy = 12;
    panelDatos.add(this.comboBoxSeleccionOferta, gbcComboBoxSeleccionOferta);
    
    GridBagConstraints gbcLblHorarioOferta = new GridBagConstraints();
    gbcLblHorarioOferta.anchor = GridBagConstraints.EAST;
    gbcLblHorarioOferta.insets = new Insets(0, 0, 5, 5);
    gbcLblHorarioOferta.gridx = 0;
    gbcLblHorarioOferta.gridy = 13;
    JLabel lblHorarioOferta = new JLabel("Horario");
    panelDatos.add(lblHorarioOferta, gbcLblHorarioOferta);
    
    this.textFieldHorarioOferta = new JTextField();
    this.textFieldHorarioOferta.setEditable(false);
    GridBagConstraints gbcTextFieldNombreOferta = new GridBagConstraints();
    gbcTextFieldNombreOferta.insets = new Insets(0, 0, 5, 0);
    gbcTextFieldNombreOferta.fill = GridBagConstraints.HORIZONTAL;
    gbcTextFieldNombreOferta.gridx = 1;
    gbcTextFieldNombreOferta.gridy = 13;
    panelDatos.add(this.textFieldHorarioOferta, gbcTextFieldNombreOferta);
    this.textFieldHorarioOferta.setColumns(10);
    
    GridBagConstraints gbcLblRemuneracion = new GridBagConstraints();
    gbcLblRemuneracion.anchor = GridBagConstraints.EAST;
    gbcLblRemuneracion.insets = new Insets(0, 0, 5, 5);
    gbcLblRemuneracion.gridx = 0;
    gbcLblRemuneracion.gridy = 14;
    JLabel lblRemuneracion = new JLabel("Remuneracion");
    panelDatos.add(lblRemuneracion, gbcLblRemuneracion);
    
    this.textFieldRemuneracion = new JTextField();
    this.textFieldRemuneracion.setEditable(false);
    GridBagConstraints gbcTextFieldRemuneracion = new GridBagConstraints();
    gbcTextFieldRemuneracion.insets = new Insets(0, 0, 5, 0);
    gbcTextFieldRemuneracion.fill = GridBagConstraints.HORIZONTAL;
    gbcTextFieldRemuneracion.gridx = 1;
    gbcTextFieldRemuneracion.gridy = 14;
    panelDatos.add(this.textFieldRemuneracion, gbcTextFieldRemuneracion);
    this.textFieldRemuneracion.setColumns(10);
    
    GridBagConstraints gbcLblCiudad = new GridBagConstraints();
    gbcLblCiudad.anchor = GridBagConstraints.EAST;
    gbcLblCiudad.insets = new Insets(0, 0, 5, 5);
    gbcLblCiudad.gridx = 0;
    gbcLblCiudad.gridy = 15;
    JLabel lblCiudad = new JLabel("Ciudad");
    panelDatos.add(lblCiudad, gbcLblCiudad);
    
    this.textFieldCiudad = new JTextField();
    this.textFieldCiudad.setEditable(false);
    GridBagConstraints gbcTextFieldCiudad = new GridBagConstraints();
    gbcTextFieldCiudad.insets = new Insets(0, 0, 5, 0);
    gbcTextFieldCiudad.fill = GridBagConstraints.HORIZONTAL;
    gbcTextFieldCiudad.gridx = 1;
    gbcTextFieldCiudad.gridy = 15;
    panelDatos.add(this.textFieldCiudad, gbcTextFieldCiudad);
    this.textFieldCiudad.setColumns(10);
    
    GridBagConstraints gbcLblDepartamento = new GridBagConstraints();
    gbcLblDepartamento.anchor = GridBagConstraints.EAST;
    gbcLblDepartamento.insets = new Insets(0, 0, 5, 5);
    gbcLblDepartamento.gridx = 0;
    gbcLblDepartamento.gridy = 16;
    JLabel lblDepartamento = new JLabel("Departamento");
    panelDatos.add(lblDepartamento, gbcLblDepartamento);
    
    this.textFieldDepartamento = new JTextField();
    this.textFieldDepartamento.setEditable(false);
    GridBagConstraints gbcTextFieldDepartamento = new GridBagConstraints();
    gbcTextFieldDepartamento.insets = new Insets(0, 0, 5, 0);
    gbcTextFieldDepartamento.fill = GridBagConstraints.HORIZONTAL;
    gbcTextFieldDepartamento.gridx = 1;
    gbcTextFieldDepartamento.gridy = 16;
    panelDatos.add(this.textFieldDepartamento, gbcTextFieldDepartamento);
    this.textFieldDepartamento.setColumns(10);
    
    GridBagConstraints gbcLblMotivacion = new GridBagConstraints();
    gbcLblMotivacion.anchor = GridBagConstraints.EAST;
    gbcLblMotivacion.insets = new Insets(0, 0, 5, 5);
    gbcLblMotivacion.gridx = 0;
    gbcLblMotivacion.gridy = 17;
    this.lblMotivacion.setVisible(false);
    panelDatos.add(this.lblMotivacion, gbcLblMotivacion);
    
    GridBagConstraints gbcLabelCvReducido = new GridBagConstraints();
    gbcLabelCvReducido.anchor = GridBagConstraints.EAST;
    gbcLabelCvReducido.insets = new Insets(0, 0, 5, 5);
    gbcLabelCvReducido.gridx = 0;
    gbcLabelCvReducido.gridy = 18;
    this.labelCvReducido.setVisible(false);
        
    textPaneMotivacion = new JTextPane();
    GridBagConstraints gbcTextPane1 = new GridBagConstraints();
    gbcTextPane1.insets = new Insets(0, 0, 5, 0);
    gbcTextPane1.fill = GridBagConstraints.BOTH;
    gbcTextPane1.gridx = 1;
    gbcTextPane1.gridy = 17;
    panelDatos.add(textPaneMotivacion, gbcTextPane1);
    this.textPaneMotivacion.setVisible(false);
    this.textPaneMotivacion.setEditable(false);
    panelDatos.add(this.labelCvReducido, gbcLabelCvReducido);
    
    GridBagConstraints gbcLabelFechaPostulaciono = new GridBagConstraints();
    gbcLabelFechaPostulaciono.anchor = GridBagConstraints.EAST;
    gbcLabelFechaPostulaciono.insets = new Insets(0, 0, 0, 5);
    gbcLabelFechaPostulaciono.gridx = 0;
    gbcLabelFechaPostulaciono.gridy = 19;
    this.labelFechaPostulacion.setVisible(false);
    
    textPaneCvReducido = new JTextPane();
    GridBagConstraints gbcTextPane2 = new GridBagConstraints();
    gbcTextPane2.insets = new Insets(0, 0, 5, 0);
    gbcTextPane2.fill = GridBagConstraints.BOTH;
    gbcTextPane2.gridx = 1;
    gbcTextPane2.gridy = 18;
    panelDatos.add(textPaneCvReducido, gbcTextPane2);
    this.textPaneCvReducido.setVisible(false);
    this.textPaneCvReducido.setEditable(false);
    panelDatos.add(this.labelFechaPostulacion, gbcLabelFechaPostulaciono);
    
    this.textFieldFechaPostulacion = new JTextField();
    this.textFieldFechaPostulacion.setEditable(false);
    GridBagConstraints gbcTextFiedFechaPostulacion = new GridBagConstraints();
    gbcTextFiedFechaPostulacion.fill = GridBagConstraints.HORIZONTAL;
    gbcTextFiedFechaPostulacion.gridx = 1;
    gbcTextFiedFechaPostulacion.gridy = 19;
    panelDatos.add(this.textFieldFechaPostulacion, gbcTextFiedFechaPostulacion);
    this.textFieldFechaPostulacion.setColumns(10);
    this.textFieldFechaPostulacion.setVisible(false);
    
  }
  
  protected void cargarDatosOferta(ActionEvent evento)
      throws OfertaLaboralNoExisteException, UsuarioNoExistePostulacion {
    String oferta = comboBoxSeleccionOferta.getSelectedItem().toString();
    String nicknameUsu = comboBoxSeleccionUsuario.getSelectedItem().toString();
    if (ofertaSeleccionada != oferta) {
      DtOfertaLaboral dtOferta = controladorOferta.obtenerDtOfertaLaboral(oferta);
      Dtpostulacion dtPostulacion = new Dtpostulacion();
      this.textFieldHorarioOferta
          .setText(dtOferta.getHorarioInicio() + " - " + dtOferta.getHorarioFinal());
      this.textFieldRemuneracion.setText(dtOferta.getRemuneracion().toString());
      this.textFieldCiudad.setText(dtOferta.getCiudad());
      this.textFieldDepartamento.setText(dtOferta.getDepartamento());
      if (tipoUsuario.equals("Postulante")) {
        try {
          dtPostulacion = controladorUsuario.obtenerDtpostulacion(nicknameUsu, oferta);
        } catch (UsuarioNoExisteException evento1) {
          // TODO Auto-generated catch block
          
        }
        this.textPaneMotivacion.setText(dtPostulacion.getDescripMotivacion());
        this.textPaneMotivacion.setVisible(true);
        this.lblMotivacion.setVisible(true);
        this.textPaneCvReducido.setText(dtPostulacion.getCvReducido());
        this.textPaneCvReducido.setVisible(true);
        this.labelCvReducido.setVisible(true);
        
        this.textFieldFechaPostulacion.setText(dtPostulacion.getFechaPostulacion().toString());
        this.textFieldFechaPostulacion.setVisible(true);
        this.labelFechaPostulacion.setVisible(true);
      }
    }
  }
  
  /**
   * Metodo cargar usuarios .
   */
  
  public void cargarUsuarios() {
    try {
      List<String> listaUsuarios = this.controladorUsuario.listaDeUsuarios();
      String[] arrayUsuarios;
      arrayUsuarios = listaUsuarios.toArray(new String[0]);
      Arrays.sort(arrayUsuarios);
      DefaultComboBoxModel<String> model;
      model = new DefaultComboBoxModel<String>(arrayUsuarios);
      this.comboBoxSeleccionUsuario.setModel(model);
      
    } catch (Exception e) {
      // TODO: handle exception
    }
  }
  
  /**
   * Metodo cargar datos usuarios .
   */

  public void cargarDatosUsuarios(ActionEvent evento) throws UsuarioNoExisteException {
    
    String nicknameUsuario = comboBoxSeleccionUsuario.getSelectedItem().toString();
    if (nicknameUsuario != usuarioSeleccionado) {
      usuarioSeleccionado = nicknameUsuario;
      limpiarDatosOfertas();
      Dtusuario dtUsuario = this.controladorUsuario.obtenerDtusuario(nicknameUsuario);
      
      // this.textFieldNickName.setText(nicknameUsuario);
      this.textFieldNombre.setText(dtUsuario.getNombre());
      this.textFieldApellido.setText(dtUsuario.getApellido());
      this.textFieldEmail.setText(dtUsuario.getEmail());
      BufferedImage originalImage = dtUsuario.getImagen();
      if (originalImage != null) {
        int newWidth = 100; // Ancho deseado
        int newHeight = 100; // Alto deseado
        Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight,
            Image.SCALE_SMOOTH);
        this.textPane.setCaretPosition(textPane.getStyledDocument().getLength());
        ImageIcon icono = new ImageIcon(scaledImage);
        this.textPane.insertIcon(icono);
      }
      
      if (dtUsuario instanceof Dtempresa) {
        tipoUsuario = "Empresa";
        Dtempresa dtEmpresa = (Dtempresa) dtUsuario;
        this.textAreaDescripcion.setText(dtEmpresa.getDescripcion());
        this.textFieldSitioWeb.setText(dtEmpresa.getSitioWeb());
        cambiarPanel(panelEmpresa);
      }
      if (dtUsuario instanceof Dtpostulante) {
        tipoUsuario = "Postulante";
        Dtpostulante dtPostulante = (Dtpostulante) dtUsuario;
        this.textFieldNacionalidad.setText(dtPostulante.getNacionalidad());
        this.textFieldFechaNacimiento.setText(dtPostulante.getFechaNacimiento().toString());
        cambiarPanel(panelPostulante);
      }
      List<String> listaOfertas = this.controladorUsuario
          .listaOfertasUsuario(nicknameUsuario);
      String[] arrayOfertas = listaOfertas.toArray(new String[0]);
      Arrays.sort(arrayOfertas);
      DefaultComboBoxModel<String> model;
      model = new DefaultComboBoxModel<String>(arrayOfertas);
      this.comboBoxSeleccionOferta.setModel(model);
    }
    
  }
  
  /**
   * Metodo cambiar panel .
   */
  
  public void cambiarPanel(JPanel panel) {
    layeredPane.removeAll();
    layeredPane.add(panel);
    layeredPane.repaint();
    layeredPane.revalidate();
  }
  
  /**
   * Metodo limpiar datos ofertas .
   */
  
  public void limpiarDatosOfertas() {
    this.textFieldDepartamento.setText("");
    this.textFieldCiudad.setText("");
    this.textFieldHorarioOferta.setText("");
    this.textFieldRemuneracion.setText("");
    this.textPaneMotivacion.setText("");
    this.textPaneMotivacion.setVisible(false);
    this.lblMotivacion.setVisible(false);
    this.textPaneCvReducido.setText("");
    this.textPaneCvReducido.setVisible(false);
    this.labelCvReducido.setVisible(false);
    this.textFieldFechaPostulacion.setText("");
    this.textFieldFechaPostulacion.setVisible(false);
    this.labelFechaPostulacion.setVisible(false);
    // this.comboBoxSeleccionOferta = new
    // JComboBox<String>();
  }
  
  /**
   * Metodo limpiar todos los datos .
   */
  
  public void limpiarTodosLosDatos() {
    
    this.ofertaSeleccionada = "";
    this.usuarioSeleccionado = "";
    // this.textFieldNickName.setText("");
    this.textFieldNombre.setText("");
    this.textFieldApellido.setText("");
    this.textFieldEmail.setText("");
    
    this.textFieldFechaNacimiento.setText("");
    this.textFieldNacionalidad.setText("");
    this.textPane.setText("");
    this.textAreaDescripcion.setText("");
    this.textFieldSitioWeb.setText("");
    this.textFieldDepartamento.setText("");
    this.textFieldCiudad.setText("");
    this.textFieldHorarioOferta.setText("");
    this.textFieldRemuneracion.setText("");
    this.textPaneMotivacion.setText("");
    this.textPaneMotivacion.setVisible(false);
    this.lblMotivacion.setVisible(false);
    this.textPaneCvReducido.setText("");
    this.textPaneCvReducido.setVisible(false);
    this.labelCvReducido.setVisible(false);
    this.textFieldFechaPostulacion.setText("");
    this.textFieldFechaPostulacion.setVisible(false);
    this.labelFechaPostulacion.setVisible(false);
    
    List<String> listaOfertas = new ArrayList<String>();
    String[] arrayOfertas = listaOfertas.toArray(new String[0]);
    Arrays.sort(arrayOfertas);
    DefaultComboBoxModel<String> model;
    model = new DefaultComboBoxModel<String>(arrayOfertas);
    this.comboBoxSeleccionOferta.setModel(model);
    
    this.comboBoxSeleccionUsuario.setModel(model);
    
  }
  
  public String dateToString(Date fecha) {
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    return formatter.format(fecha);
  }
  
}