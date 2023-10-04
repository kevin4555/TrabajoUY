package presentacion;

import com.toedter.calendar.JDateChooser;
import excepciones.KeywordNoExisteException;
import excepciones.OfertaLaboralYaExisteException;
import excepciones.PaquetePublicacionNoExisteException;
import excepciones.TipoPublicacionNoExisteException;
import excepciones.UsuarioNoExisteException;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import logica.datatypes.DtpaquetePublicacion;
import logica.interfaces.IcontroladorOferta;
import logica.interfaces.IcontroladorUsuario;

/**
 * Clase AltaOfertaLaboral .
 */

@SuppressWarnings("serial")
public class AltaOfertaLaboral extends JInternalFrame {
  
  // Controlador de usuarios que se utilizará para
  // las acciones del JFrame
  private IcontroladorOferta controladorOfertaLaboral;
  private IcontroladorUsuario controladorUsuario;
  
  // Los componentes gráficos se agregan como
  // atributos de la clase
  // para facilitar su acceso desde diferentes
  // métodos de la misma.
  private JPanel panelDatos;
  private JPanel ubicacionEtiquetas;
  private JPanel ubicacionTexto;
  private JLabel lblEmpresa;
  private JComboBox comboBoxEmpresa;
  private JLabel lblTipoPublicacion;
  private JComboBox comboBoxTpoPublicacion;
  private JLabel lblNombre;
  private JTextField textFieldNombre;
  private JLabel lblDescripcion;
  private JTextArea textFieldDescripcion;
  private JLabel lblHoraInicio;
  private JTextField textFieldHoraInicio;
  private JLabel lblHoraFin;
  private JTextField textFieldHoraFin;
  private JLabel lblRemuneracin;
  private JTextField textFieldRemuneracion;
  private JLabel lblCiudad;
  private JTextField textFieldCiudad;
  private JLabel lblDepartamento;
  private JTextField textFieldDepartamento;
  private JLabel lblFechaDeAlta;
  // private JCalendar textFieldFechaAlta;
  private JLabel lblKeyword;
  private JList<String> listaKeyword = new JList<>();
  private JButton btnSeleccionarKeyword;
  private JButton btnConfirmar;
  private JButton btnCancelar;
  private JDateChooser dateChooser;
  private JTextField textField;
  private JTextField textField1;
  private JTextField textField2;
  private JTextField textField3;
  private JTextField textField4;
  private JTextField textField5;
  private JTextField textField6;
  private JTextPane textPane;
  private JButton selectImageButton;
  private JLabel imageLabel;
  private JScrollPane scrollPane;
  private JLabel lblNewLabel;
  private JList list;
  private JLabel lblNewLabel1;
  private JComboBox comboBoxFormaDePago;
  private JComboBox<String> comboBoxSeleccionTiposPublicaciones;
  private JLabel lblTiposPublicaciones;
  private JLabel lblSeleccionPaquete;
  private JComboBox<String> comboBoxSeleccionPaquete;
  private String paqueteSeleccionado;
  private BufferedImage fotoOferta = null;
  private GridBagConstraints gbcTextField;
  
  /**
   * Create the frame.
   */
  public AltaOfertaLaboral(IcontroladorOferta icontOfer, IcontroladorUsuario icontUsu) {
    
    controladorOfertaLaboral = icontOfer;
    controladorUsuario = icontUsu;
    this.paqueteSeleccionado = "";
    
    // Propiedades del JInternalFrame como dimensión,
    // posición dentro del frame,
    // etc.
    setResizable(true);
    setIconifiable(true);
    setMaximizable(true);
    setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    setTitle("Alta Oferta Laboral");
    setBounds(30, 30, 513, 580);
    
    JPanel panelBotones = new JPanel();
    getContentPane().add(panelBotones, BorderLayout.SOUTH);
    panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 120, 20));
    
    btnConfirmar = new JButton("Confirmar");
    panelBotones.add(btnConfirmar);
    
    btnCancelar = new JButton("Cancelar");
    panelBotones.add(btnCancelar);
    
    btnCancelar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evento) {
        limpiarFormulario();
        dispose();
      }
    });
    
    btnConfirmar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        cmdRegistroOfertaLaboralActionPerformed(arg0);
      }
    });
    
    this.panelDatos = new JPanel();
    getContentPane().add(panelDatos, BorderLayout.CENTER);
    GridBagLayout gblPanelDatos = new GridBagLayout();
    gblPanelDatos.columnWidths = new int[] { 113, 739, 0 };
    gblPanelDatos.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0 };
    gblPanelDatos.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
    gblPanelDatos.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0,
        0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0 };
    panelDatos.setLayout(gblPanelDatos);
    
    GridBagConstraints gbcLblSeleccion = new GridBagConstraints();
    gbcLblSeleccion.insets = new Insets(0, 0, 5, 5);
    gbcLblSeleccion.anchor = GridBagConstraints.EAST;
    gbcLblSeleccion.gridx = 0;
    gbcLblSeleccion.gridy = 1;
    JLabel lblSeleccion = new JLabel("Seleccionar Empresa:");
    panelDatos.add(lblSeleccion, gbcLblSeleccion);
    
    this.comboBoxEmpresa = new JComboBox<String>();
    GridBagConstraints gbcComboBoxSeleccionUsuario = new GridBagConstraints();
    gbcComboBoxSeleccionUsuario.insets = new Insets(0, 0, 5, 0);
    gbcComboBoxSeleccionUsuario.fill = GridBagConstraints.HORIZONTAL;
    gbcComboBoxSeleccionUsuario.gridx = 1;
    gbcComboBoxSeleccionUsuario.gridy = 1;
    panelDatos.add(this.comboBoxEmpresa, gbcComboBoxSeleccionUsuario);
    
    GridBagConstraints gbcLblNombre = new GridBagConstraints();
    gbcLblNombre.anchor = GridBagConstraints.EAST;
    gbcLblNombre.insets = new Insets(0, 0, 5, 5);
    gbcLblNombre.gridx = 0;
    gbcLblNombre.gridy = 2;
    JLabel lblNombre = new JLabel("Nombre");
    panelDatos.add(lblNombre, gbcLblNombre);
    
    this.textFieldNombre = new JTextField();
    GridBagConstraints gbcTextFieldNombreOferta = new GridBagConstraints();
    gbcTextFieldNombreOferta.insets = new Insets(0, 0, 5, 0);
    gbcTextFieldNombreOferta.fill = GridBagConstraints.HORIZONTAL;
    gbcTextFieldNombreOferta.gridx = 1;
    gbcTextFieldNombreOferta.gridy = 2;
    panelDatos.add(this.textFieldNombre, gbcTextFieldNombreOferta);
    this.textFieldNombre.setColumns(10);
    
    GridBagConstraints gbcLblRemuneracion = new GridBagConstraints();
    gbcLblRemuneracion.anchor = GridBagConstraints.EAST;
    gbcLblRemuneracion.insets = new Insets(0, 0, 5, 5);
    gbcLblRemuneracion.gridx = 0;
    gbcLblRemuneracion.gridy = 3;
    JLabel lblRemuneracion = new JLabel("Remuneracion");
    panelDatos.add(lblRemuneracion, gbcLblRemuneracion);
    
    this.textFieldRemuneracion = new JTextField();
    GridBagConstraints gbcTextFieldRemuneracion = new GridBagConstraints();
    gbcTextFieldRemuneracion.insets = new Insets(0, 0, 5, 0);
    gbcTextFieldRemuneracion.fill = GridBagConstraints.HORIZONTAL;
    gbcTextFieldRemuneracion.gridx = 1;
    gbcTextFieldRemuneracion.gridy = 3;
    panelDatos.add(this.textFieldRemuneracion, gbcTextFieldRemuneracion);
    this.textFieldRemuneracion.setColumns(10);
    
    GridBagConstraints gbcLblCiudad = new GridBagConstraints();
    gbcLblCiudad.anchor = GridBagConstraints.EAST;
    gbcLblCiudad.insets = new Insets(0, 0, 5, 5);
    gbcLblCiudad.gridx = 0;
    gbcLblCiudad.gridy = 4;
    JLabel lblCiudad = new JLabel("Ciudad");
    panelDatos.add(lblCiudad, gbcLblCiudad);
    
    this.textFieldCiudad = new JTextField();
    GridBagConstraints gbcTextFieldCiudad = new GridBagConstraints();
    gbcTextFieldCiudad.insets = new Insets(0, 0, 5, 0);
    gbcTextFieldCiudad.fill = GridBagConstraints.HORIZONTAL;
    gbcTextFieldCiudad.gridx = 1;
    gbcTextFieldCiudad.gridy = 4;
    panelDatos.add(this.textFieldCiudad, gbcTextFieldCiudad);
    this.textFieldCiudad.setColumns(10);
    
    GridBagConstraints gbcLblDepartamento = new GridBagConstraints();
    gbcLblDepartamento.anchor = GridBagConstraints.EAST;
    gbcLblDepartamento.insets = new Insets(0, 0, 5, 5);
    gbcLblDepartamento.gridx = 0;
    gbcLblDepartamento.gridy = 5;
    JLabel lblDepartamento = new JLabel("Departamento");
    panelDatos.add(lblDepartamento, gbcLblDepartamento);
    
    this.textFieldDepartamento = new JTextField();
    GridBagConstraints gbcTextFieldDepartamento = new GridBagConstraints();
    gbcTextFieldDepartamento.insets = new Insets(0, 0, 5, 0);
    gbcTextFieldDepartamento.fill = GridBagConstraints.HORIZONTAL;
    gbcTextFieldDepartamento.gridx = 1;
    gbcTextFieldDepartamento.gridy = 5;
    panelDatos.add(this.textFieldDepartamento, gbcTextFieldDepartamento);
    this.textFieldDepartamento.setColumns(10);
    
    GridBagConstraints gbcLblHorarioInicio = new GridBagConstraints();
    gbcLblHorarioInicio.anchor = GridBagConstraints.EAST;
    gbcLblHorarioInicio.insets = new Insets(0, 0, 5, 5);
    gbcLblHorarioInicio.gridx = 0;
    gbcLblHorarioInicio.gridy = 6;
    JLabel lblHorarioInicio = new JLabel("Horario Inicio");
    panelDatos.add(lblHorarioInicio, gbcLblHorarioInicio);
    
    this.textFieldHoraInicio = new JTextField();
    GridBagConstraints gbcTextFieldHorarioInicio = new GridBagConstraints();
    gbcTextFieldHorarioInicio.insets = new Insets(0, 0, 5, 0);
    gbcTextFieldHorarioInicio.fill = GridBagConstraints.HORIZONTAL;
    gbcTextFieldHorarioInicio.gridx = 1;
    gbcTextFieldHorarioInicio.gridy = 6;
    panelDatos.add(this.textFieldHoraInicio, gbcTextFieldHorarioInicio);
    this.textFieldHoraInicio.setColumns(10);
    
    GridBagConstraints gbcLblHorarioFin = new GridBagConstraints();
    gbcLblHorarioFin.anchor = GridBagConstraints.EAST;
    gbcLblHorarioFin.insets = new Insets(0, 0, 5, 5);
    gbcLblHorarioFin.gridx = 0;
    gbcLblHorarioFin.gridy = 7;
    JLabel lblHorarioFin = new JLabel("Horario Fin");
    panelDatos.add(lblHorarioFin, gbcLblHorarioFin);
    
    this.textFieldHoraFin = new JTextField();
    GridBagConstraints gbcTextFieldHorarioFin = new GridBagConstraints();
    gbcTextFieldHorarioFin.insets = new Insets(0, 0, 5, 0);
    gbcTextFieldHorarioFin.fill = GridBagConstraints.HORIZONTAL;
    gbcTextFieldHorarioFin.gridx = 1;
    gbcTextFieldHorarioFin.gridy = 7;
    panelDatos.add(this.textFieldHoraFin, gbcTextFieldHorarioFin);
    this.textFieldHoraFin.setColumns(10);
    
    GridBagConstraints gbcLblDescripcion = new GridBagConstraints();
    gbcLblDescripcion.insets = new Insets(0, 0, 5, 5);
    gbcLblDescripcion.anchor = GridBagConstraints.EAST;
    gbcLblDescripcion.gridx = 0;
    gbcLblDescripcion.gridy = 8;
    JLabel lblDescripcion = new JLabel("Descripcion:");
    panelDatos.add(lblDescripcion, gbcLblDescripcion);
    
    scrollPane = new JScrollPane();
    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    scrollPane.setEnabled(false);
    scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    GridBagConstraints gbcScrollPane = new GridBagConstraints();
    gbcScrollPane.gridheight = 2;
    gbcScrollPane.insets = new Insets(0, 0, 5, 0);
    gbcScrollPane.fill = GridBagConstraints.BOTH;
    gbcScrollPane.gridx = 1;
    gbcScrollPane.gridy = 8;
    panelDatos.add(scrollPane, gbcScrollPane);
    
    textFieldDescripcion = new JTextArea();
    textFieldDescripcion.setLineWrap(true);
    textFieldDescripcion.setWrapStyleWord(true);
    textFieldDescripcion.setEditable(true);
    scrollPane.setViewportView(textFieldDescripcion);
    
    GridBagConstraints gbcLblFechaAlta = new GridBagConstraints();
    gbcLblFechaAlta.insets = new Insets(0, 0, 5, 5);
    gbcLblFechaAlta.anchor = GridBagConstraints.EAST;
    gbcLblFechaAlta.gridx = 0;
    gbcLblFechaAlta.gridy = 10;
    JLabel lblFechaAlta = new JLabel("Fecha de Alta");
    panelDatos.add(lblFechaAlta, gbcLblFechaAlta);
    
    this.dateChooser = new JDateChooser();
    GridBagConstraints gbcFechaAlta = new GridBagConstraints();
    gbcFechaAlta.insets = new Insets(0, 0, 5, 0);
    gbcFechaAlta.fill = GridBagConstraints.HORIZONTAL;
    gbcFechaAlta.gridx = 1;
    gbcFechaAlta.gridy = 10;
    panelDatos.add(dateChooser, gbcFechaAlta);
    
    lblNewLabel = new JLabel("Keywords");
    GridBagConstraints gbcLblNewLabel = new GridBagConstraints();
    gbcLblNewLabel.insets = new Insets(0, 0, 5, 5);
    gbcLblNewLabel.anchor = GridBagConstraints.EAST;
    gbcLblNewLabel.gridx = 0;
    gbcLblNewLabel.gridy = 11;
    panelDatos.add(lblNewLabel, gbcLblNewLabel);
    
    listaKeyword = new JList<>();
    GridBagConstraints gbcList = new GridBagConstraints();
    gbcList.insets = new Insets(0, 0, 5, 0);
    gbcList.fill = GridBagConstraints.BOTH;
    gbcList.gridx = 1;
    gbcList.gridy = 11;
    
    JScrollPane scrollPaneList = new JScrollPane();
    scrollPaneList.setViewportView(listaKeyword);
    listaKeyword.setVisibleRowCount(4);
    panelDatos.add(scrollPaneList, gbcList);
    
    lblNewLabel1 = new JLabel("Forma de pago");
    GridBagConstraints gbcLblNewLabel1 = new GridBagConstraints();
    gbcLblNewLabel1.anchor = GridBagConstraints.EAST;
    gbcLblNewLabel1.insets = new Insets(0, 0, 5, 5);
    gbcLblNewLabel1.gridx = 0;
    gbcLblNewLabel1.gridy = 15;
    panelDatos.add(lblNewLabel1, gbcLblNewLabel1);
    
    comboBoxFormaDePago = new JComboBox();
    GridBagConstraints gbcComboBox = new GridBagConstraints();
    gbcComboBox.insets = new Insets(0, 0, 5, 0);
    gbcComboBox.fill = GridBagConstraints.HORIZONTAL;
    gbcComboBox.gridx = 1;
    gbcComboBox.gridy = 15;
    panelDatos.add(comboBoxFormaDePago, gbcComboBox);
    
    this.comboBoxFormaDePago.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evento) {
        cargarDatosTipoPublicacion(evento);
        
      }
    });
    
    lblTiposPublicaciones = new JLabel("Tipos de publicaciones:");
    GridBagConstraints gbcLblOfertas = new GridBagConstraints();
    gbcLblOfertas.anchor = GridBagConstraints.EAST;
    gbcLblOfertas.insets = new Insets(0, 0, 5, 5);
    gbcLblOfertas.gridx = 0;
    gbcLblOfertas.gridy = 17;
    panelDatos.add(lblTiposPublicaciones, gbcLblOfertas);
    lblTiposPublicaciones.setVisible(false);
    
    this.comboBoxSeleccionTiposPublicaciones = new JComboBox<String>();
    GridBagConstraints gbcComboBoxSeleccionOferta = new GridBagConstraints();
    gbcComboBoxSeleccionOferta.insets = new Insets(0, 0, 5, 0);
    gbcComboBoxSeleccionOferta.fill = GridBagConstraints.HORIZONTAL;
    gbcComboBoxSeleccionOferta.gridx = 1;
    gbcComboBoxSeleccionOferta.gridy = 17;
    panelDatos.add(this.comboBoxSeleccionTiposPublicaciones, gbcComboBoxSeleccionOferta);
    comboBoxSeleccionTiposPublicaciones.setVisible(false);
    
    lblSeleccionPaquete = new JLabel("Seleccionar Paquete:");
    GridBagConstraints gbcLblSeleccionPaquete = new GridBagConstraints();
    gbcLblSeleccionPaquete.insets = new Insets(0, 0, 5, 5);
    gbcLblSeleccionPaquete.anchor = GridBagConstraints.EAST;
    gbcLblSeleccionPaquete.gridx = 0;
    gbcLblSeleccionPaquete.gridy = 16;
    panelDatos.add(lblSeleccionPaquete, gbcLblSeleccionPaquete);
    lblSeleccionPaquete.setVisible(false);
    
    this.comboBoxSeleccionPaquete = new JComboBox<String>();
    this.comboBoxSeleccionPaquete.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evento) {
        cargarTipoPublicacionEnPaquete(evento);
        
      }
    });
    
    GridBagConstraints gbcComboBoxSeleccionPaquete = new GridBagConstraints();
    gbcComboBoxSeleccionPaquete.insets = new Insets(0, 0, 5, 0);
    gbcComboBoxSeleccionPaquete.fill = GridBagConstraints.HORIZONTAL;
    gbcComboBoxSeleccionPaquete.gridx = 1;
    gbcComboBoxSeleccionPaquete.gridy = 16;
    panelDatos.add(this.comboBoxSeleccionPaquete, gbcComboBoxSeleccionPaquete);
    this.comboBoxSeleccionPaquete.setVisible(false);
    
    GridBagConstraints gbcLblNewLabel2 = new GridBagConstraints();
    gbcLblNewLabel2.anchor = GridBagConstraints.EAST;
    gbcLblNewLabel2.insets = new Insets(0, 0, 5, 5);
    gbcLblNewLabel2.gridx = 0;
    gbcLblNewLabel2.gridy = 13;
    JLabel imagenOferta = new JLabel("Imagen Oferta:");
    panelDatos.add(imagenOferta, gbcLblNewLabel2);
    
    selectImageButton = new JButton("Seleccionar Imagen");
    JLabel imageLabel = new JLabel();
    
    gbcTextField = new GridBagConstraints();
    gbcTextField.anchor = GridBagConstraints.WEST;
    gbcTextField.insets = new Insets(0, 0, 5, 0);
    gbcTextField.gridx = 1;
    gbcTextField.gridy = 13;
    panelDatos.add(selectImageButton, gbcTextField);
    
    selectImageButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent evento) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);
        
        if (result == JFileChooser.APPROVE_OPTION) {
          
          File fotoPerfil = new File(fileChooser.getSelectedFile().getAbsolutePath());
          obtenerImagen(fotoPerfil);
          
        }
      }
    });
    
    textPane = new JTextPane();
    GridBagConstraints gbcTextPane = new GridBagConstraints();
    gbcTextPane.insets = new Insets(0, 0, 5, 0);
    gbcTextPane.fill = GridBagConstraints.BOTH;
    gbcTextPane.gridx = 1;
    gbcTextPane.gridy = 14;
    panelDatos.add(textPane, gbcTextPane);
    textPane.setEditable(false);
    
  }
  
  /**
   * Metodo cargar empresas .
   */
  
  @SuppressWarnings("unchecked")
  public void cargarEmpresas() {
    
    List<String> listaEmpresas = this.controladorUsuario.listarEmpresas();
    String[] arrayEmpresas;
    arrayEmpresas = listaEmpresas.toArray(new String[0]);
    Arrays.sort(arrayEmpresas);
    DefaultComboBoxModel<String> model;
    model = new DefaultComboBoxModel<String>(arrayEmpresas);
    this.comboBoxEmpresa.setModel(model);
    
  }
  
  /**
   * Metodo cargar keywords .
   */
  
  public void cargarKeywords() {
    DefaultListModel<String> listModel = new DefaultListModel<String>();
    listaKeyword.setModel(listModel);
    
    // Recorrer el contenido del ArrayList
    for (int i = 0; i < controladorOfertaLaboral.listarKeywords().size(); i++) {
      // Añadir cada elemento del ArrayList en el modelo
      // de la lista
      listModel.addElement(controladorOfertaLaboral.listarKeywords().get(i));
    }
  }
  
  /**
   * Metodo cargar combo box forma de pago .
   */
  
  public void cargarComboBoxFormaDePago() {
    DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
    model.addElement("Seleccione:");
    model.addElement("General");
    model.addElement("Por paquete");
    comboBoxFormaDePago.setModel(model);
  }
  
  /**
   * Metodo cargar datos tipo publicacion .
   */
  
  public void cargarDatosTipoPublicacion(ActionEvent evento) {
    
    try {
      DefaultComboBoxModel<String> modelPublicaciones;
      DefaultComboBoxModel<String> modelPaquetes;
      if (this.comboBoxFormaDePago.getSelectedItem().equals("General")) {
        lblTiposPublicaciones.setVisible(true);
        comboBoxSeleccionTiposPublicaciones.setVisible(true);
        this.comboBoxSeleccionPaquete.setVisible(false);
        lblSeleccionPaquete.setVisible(false);
        List<String> listaTipoDePublicaciones = this.controladorOfertaLaboral
            .listarTipoDePublicaciones();
        String[] arrayTiposPublicaciones = listaTipoDePublicaciones.toArray(new String[0]);
        Arrays.sort(arrayTiposPublicaciones);
        modelPublicaciones = new DefaultComboBoxModel<String>(arrayTiposPublicaciones);
        this.comboBoxSeleccionTiposPublicaciones.setModel(modelPublicaciones);
      }
      if (this.comboBoxFormaDePago.getSelectedItem().equals("Por paquete")) {
        String nicknameEmpresa = "";
        if (this.comboBoxEmpresa.getSelectedIndex() != -1) {
          nicknameEmpresa = this.comboBoxEmpresa.getSelectedItem().toString();
          lblTiposPublicaciones.setVisible(true);
          comboBoxSeleccionTiposPublicaciones.setVisible(true);
          this.comboBoxSeleccionPaquete.setVisible(true);
          lblSeleccionPaquete.setVisible(true);
          
          List<DtpaquetePublicacion> dtPaquetesComprados = this.controladorUsuario
              .obtenerDtpaquetesDeEmpresa(nicknameEmpresa);
          List<String> nombrePaquetesComprados = new ArrayList<String>();
          for (DtpaquetePublicacion dtPaquete : dtPaquetesComprados) {
            nombrePaquetesComprados.add(dtPaquete.getNombre());
          }
          String[] arrayPaquetes = nombrePaquetesComprados.toArray(new String[0]);
          if (arrayPaquetes.length == 0) {
            this.comboBoxSeleccionTiposPublicaciones.removeAllItems();
            lblTiposPublicaciones.setVisible(false);
            comboBoxSeleccionTiposPublicaciones.setVisible(false);
            this.comboBoxSeleccionPaquete.setVisible(false);
            lblSeleccionPaquete.setVisible(false);
            JOptionPane.showMessageDialog(this, "La empresa no tiene ningún paquete comprado",
                "Registrar Oferta Laboral", JOptionPane.ERROR_MESSAGE);
          }
          Arrays.sort(arrayPaquetes);
          modelPaquetes = new DefaultComboBoxModel<String>(arrayPaquetes);
          this.comboBoxSeleccionPaquete.setModel(modelPaquetes);
        } else {
          JOptionPane.showMessageDialog(this, "Debe seleccionar una empresa",
              "Registrar Oferta Laboral", JOptionPane.ERROR_MESSAGE);
        }
        
      }
    } catch (UsuarioNoExisteException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    
  }
  
  /**
   * Metodo obtener imagen .
   */
  
  public void obtenerImagen(File imagenPerfil) {
    try {
      BufferedImage originalImage = ImageIO.read(imagenPerfil);
      fotoOferta = originalImage;
      this.textPane.setCaretPosition(textPane.getStyledDocument().getLength());
      this.textPane.setText("");
      int newWidth = 100; // Ancho deseado
      int newHeight = 100; // Alto deseado
      Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight,
          Image.SCALE_SMOOTH);
      ImageIcon icono = new ImageIcon(scaledImage);
      this.textPane.insertIcon(icono);
      
    } catch (IOException e) {
      JOptionPane.showMessageDialog(this, "No se cargo la imagen", "Registrar Usuario",
          JOptionPane.ERROR_MESSAGE);
    } catch (java.lang.NullPointerException e2) {
      JOptionPane.showMessageDialog(this, "Debe ingresar una imagen valida",
          "Registrar Usuario", JOptionPane.ERROR_MESSAGE);
    }
  }
  
  /**
   * Metodo cargar tipo publicacion en paquete .
   */
  
  public void cargarTipoPublicacionEnPaquete(ActionEvent evento) {
    
    try {
      String nombrePaquete = comboBoxSeleccionPaquete.getSelectedItem().toString();
      List<String> listaTipoDePublicacionesDePaquete = this.controladorOfertaLaboral
          .listarTipoPublicacionDePaquete(nombrePaquete);
      
      String[] arrayTiposPublicacionesPaquete = listaTipoDePublicacionesDePaquete
          .toArray(new String[0]);
      Arrays.sort(arrayTiposPublicacionesPaquete);
      DefaultComboBoxModel<String> model;
      model = new DefaultComboBoxModel<String>(arrayTiposPublicacionesPaquete);
      this.comboBoxSeleccionTiposPublicaciones.setModel(model);
    } catch (PaquetePublicacionNoExisteException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    
  }
  
  protected void cmdRegistroOfertaLaboralActionPerformed(ActionEvent arg0) {
    // TODO Auto-generated method stub
    
    // Obtengo datos de los controles Swing
    String nicknameEmpresa = "";
    if (this.comboBoxEmpresa.getSelectedIndex() != -1) {
      nicknameEmpresa = this.comboBoxEmpresa.getSelectedItem().toString();
    }
    String nomPaquete = null;
    if (this.comboBoxSeleccionPaquete.getSelectedIndex() != -1) {
      nomPaquete = this.comboBoxSeleccionPaquete.getSelectedItem().toString();
    }
    
    Date fechaAlta = this.dateChooser.getDate();
    
    String horaIniOfertaLab = this.textFieldHoraInicio.getText();
    String horaFinOfertaLab = this.textFieldHoraFin.getText();
    List<String> keywordSeleccionadas = new ArrayList<String>();
    if (!this.listaKeyword.getSelectedValuesList().isEmpty()) {
      keywordSeleccionadas = (ArrayList<String>) this.listaKeyword.getSelectedValuesList();
    }
    
    String descripOfertaLab = this.textFieldDescripcion.getText();
    String nombreOfertaLab = this.textFieldNombre.getText();
    String remuneracionOfertaLab = this.textFieldRemuneracion.getText();
    String ciudadOfertaLab = this.textFieldCiudad.getText();
    String departOfertaLab = this.textFieldDepartamento.getText();
    if (checkFormulario(nombreOfertaLab, descripOfertaLab, remuneracionOfertaLab,
        ciudadOfertaLab, departOfertaLab, horaIniOfertaLab, horaFinOfertaLab, fechaAlta,
        nicknameEmpresa)) {
      try {
        if (this.textPane.getText() == "") {
          fotoOferta = null;
        }
        LocalDate fechaAltaOferta = this.dateChooser.getDate().toInstant()
            .atZone(ZoneId.systemDefault()).toLocalDate();
        controladorOfertaLaboral.altaOfertaLaboral(nombreOfertaLab, descripOfertaLab,
            horaIniOfertaLab, horaFinOfertaLab, Float.parseFloat(remuneracionOfertaLab),
            ciudadOfertaLab, departOfertaLab, fechaAltaOferta,
            this.comboBoxSeleccionTiposPublicaciones.getSelectedItem().toString(),
            nicknameEmpresa, keywordSeleccionadas, fotoOferta, nomPaquete);
        // Muestro éxito de la operación
        JOptionPane.showMessageDialog(this, "La Oferta Laboral se ha creado con éxito",
            "Registrar Oferta Laboral", JOptionPane.INFORMATION_MESSAGE);
        
        // Limpio el internal frame antes de cerrar la
        // ventana
        limpiarFormulario();
        // setVisible(false)
      } catch (TipoPublicacionNoExisteException e) {
        // No imprime nada
      } catch (UsuarioNoExisteException e) {
        // no imprime nada
      } catch (OfertaLaboralYaExisteException e) {
        JOptionPane.showMessageDialog(this, e.getMessage(), "Trabajo.uy",
            JOptionPane.ERROR_MESSAGE);
      } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, e.getMessage(), "Trabajo.uy",
            JOptionPane.ERROR_MESSAGE);
      } catch (KeywordNoExisteException e) {
        // no imprime nada
      } catch (java.lang.ClassCastException e) {
        JOptionPane.showMessageDialog(this, "Debe ingresar una fecha valida", "Trabajo.uy",
            JOptionPane.ERROR_MESSAGE);
        
      }
    }
    
  }
  
  // Permite validar la información introducida en
  // los campos e indicar
  // a través de un mensaje de error (JOptionPane)
  // cuando algo sucede.
  // Este tipo de chequeos se puede realizar de
  // otras formas y con otras librerías
  // de Java,
  // por ejemplo impidiendo que se escriban
  // caracteres no numéricos al momento de
  // escribir en
  // en el campo de la cédula, o mostrando un
  // mensaje de error apenas el foco pasa
  // a otro campo.
  private boolean checkFormulario(String nombreOfertaLab, String descripOfertaLab,
      String remuneracionOfertaLab, String ciudadOfertaLab, String departOfertaLab,
      String horaIniOfertaLab, String horaFinOfertaLab, Date fechaAlta,
      String nicknameEmpresa) {
    
    if (nombreOfertaLab.isEmpty() || descripOfertaLab.isEmpty()
        || remuneracionOfertaLab.isEmpty() || ciudadOfertaLab.isEmpty()
        || departOfertaLab.isEmpty() || horaIniOfertaLab.isEmpty()
        || horaFinOfertaLab.isEmpty()) {
      JOptionPane.showMessageDialog(this, "No puede haber campos vacíos",
          "Registrar Oferta Laboral", JOptionPane.ERROR_MESSAGE);
      return false;
    }
    if (this.comboBoxSeleccionTiposPublicaciones.getSelectedIndex() == -1) {
      JOptionPane.showMessageDialog(this, "Debe seleccionar un tipo de publicación",
          "Registrar Oferta Laboral", JOptionPane.ERROR_MESSAGE);
    }
    if (fechaAlta == null) {
      JOptionPane.showMessageDialog(this, "Debe ingresar una fecha valida",
          "Registrar Oferta Laboral", JOptionPane.ERROR_MESSAGE);
      return false;
    }
    if (chequeoHoraValida(horaIniOfertaLab)) {
      JOptionPane.showMessageDialog(this,
          "Por favor, ingrese una hora de inicio valida del tipo xx:xx",
          "Registrar Oferta Laboral", JOptionPane.ERROR_MESSAGE);
      return false;
    }
    if (chequeoHoraValida(horaFinOfertaLab)) {
      JOptionPane.showMessageDialog(this,
          "Por favor, ingrese una hora de fin valida del tipo xx:xx",
          "Registrar Oferta Laboral", JOptionPane.ERROR_MESSAGE);
      return false;
    }
    
    /*
     * char data[] = {'a', 'b', 'c'}; String str = new
     * String(data);
     */
    
    try {
      Float.parseFloat(remuneracionOfertaLab);
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(this, "La remuneración debe ser un numero",
          "Registrar Oferta Laboral", JOptionPane.ERROR_MESSAGE);
      return false;
    }
    
    if (Float.parseFloat(remuneracionOfertaLab) <= 0) {
      JOptionPane.showMessageDialog(this, "La remuneración debe ser mayor a cero",
          "Registrar Oferta Laboral", JOptionPane.ERROR_MESSAGE);
      return false;
    }
    
    return true;
  }
  
  private boolean chequeoHoraValida(String horario) {
    char[] horaChar = horario.toCharArray();
    if (horaChar.length != 5) {
      return true;
    }
    String hora = "";
    String minuto = "";
    try {
      for (int i = 0; i < horaChar.length; i++) {
        if (i != 2) {
          Integer.parseInt(String.valueOf(horaChar[i]));
          if (i < 2) {
            hora = hora + horaChar[i];
          } else if (i > 2) {
            minuto = minuto + horaChar[i];
          }
        } else if (horaChar[i] != ':') {
          return true;
        }
      }
      if (Integer.parseInt(hora) < 0 || Integer.parseInt(hora) > 23) {
        return true;
      }
      if (Integer.parseInt(minuto) < 0 || Integer.parseInt(minuto) > 59) {
        return true;
      }
      return false;
      
    } catch (NumberFormatException e) {
      return true;
    }
  }
  
  // Permite borrar el contenido de un formulario
  // antes de cerrarlo.
  // Recordar que las ventanas no se destruyen, sino
  // que simplemente
  // se ocultan, por lo que conviene borrar la
  // información para que
  // no aparezca al mostrarlas nuevamente.
  private void limpiarFormulario() {
    textFieldNombre.setText("");
    this.paqueteSeleccionado = "";
    textFieldDescripcion.setText("");
    textFieldRemuneracion.setText("");
    textFieldCiudad.setText("");
    textFieldDepartamento.setText("");
    dateChooser.setDate(null);
    textFieldHoraInicio.setText("");
    textFieldHoraFin.setText("");
    this.textPane.setText("");
    lblTiposPublicaciones.setVisible(false);
    comboBoxSeleccionTiposPublicaciones.setVisible(false);
    this.comboBoxSeleccionPaquete.setVisible(false);
    lblSeleccionPaquete.setVisible(false);
  }
}
