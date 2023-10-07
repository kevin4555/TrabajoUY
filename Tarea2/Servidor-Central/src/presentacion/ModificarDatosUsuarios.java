package presentacion;

import com.toedter.calendar.JDateChooser;
import excepciones.UsuarioNoExisteException;
import java.awt.BorderLayout;
import java.awt.CardLayout;
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
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import logica.datatypes.Dtempresa;
import logica.datatypes.Dtpostulante;
import logica.datatypes.Dtusuario;
import logica.interfaces.IcontroladorUsuario;

/**
 * Clase modificar datos de usuarios .
 */

@SuppressWarnings("serial")
public class ModificarDatosUsuarios extends JInternalFrame {
  private JTextField textFieldNickName;
  private JTextField textFieldNombre;
  private JTextField textFieldApellido;
  private JTextField textFieldEmail;
  private JTextField textFieldSitioWeb;
  private JTextField textFieldNacionalidad;
  private JDateChooser fechaNacimientoChooser;
  private IcontroladorUsuario controladorUsuario;
  private JComboBox<String> comboBoxSeleccionUsuario;
  private JPanel panelEmpresa;
  private JPanel panelPostulante;
  private JLayeredPane layeredPane;
  private String tipoUsuario = "";
  private JScrollPane scrollPane;
  private JTextArea textAreaDescripcion;
  private String usuarioSeleccionado;
  private JButton btnConfirmar;
  private JButton btnCancelar;
  private JPanel panelDatos;
  private JLabel lblNewLabel;
  private JPasswordField textFieldContrasenia;
  private JLabel lblNewLabel1;
  private JTextPane textPane;
  private JButton selectImageButton;
  private JButton quitarImageButton;
  private BufferedImage fotoPerfilUsuario = null;
  
  /**
   * Metodo modificar datos usuarios .
   */
  
  public ModificarDatosUsuarios(IcontroladorUsuario contrUsuario) {
    setTitle("Modificar Datos Usuarios");
    this.usuarioSeleccionado = "";
    
    setIconifiable(true);
    setResizable(true);
    setMaximizable(true);
    this.controladorUsuario = contrUsuario;
    getContentPane().setLayout(new BorderLayout(0, 0));
    setBounds(100, 100, 594, 502);
    
    this.panelDatos = new JPanel();
    getContentPane().add(panelDatos, BorderLayout.CENTER);
    GridBagLayout gblPanelDatos = new GridBagLayout();
    gblPanelDatos.columnWidths = new int[] { 113, 739, 0 };
    gblPanelDatos.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0 };
    gblPanelDatos.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
    gblPanelDatos.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0,
        0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
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
    
    GridBagConstraints gbcLblNickname = new GridBagConstraints();
    gbcLblNickname.anchor = GridBagConstraints.EAST;
    gbcLblNickname.insets = new Insets(0, 0, 5, 5);
    gbcLblNickname.gridx = 0;
    gbcLblNickname.gridy = 2;
    JLabel lblNickname = new JLabel("Nickname:");
    panelDatos.add(lblNickname, gbcLblNickname);
    
    this.textFieldNickName = new JTextField();
    this.textFieldNickName.setEditable(false);
    GridBagConstraints gbcTextFieldNickName = new GridBagConstraints();
    gbcTextFieldNickName.insets = new Insets(0, 0, 5, 0);
    gbcTextFieldNickName.fill = GridBagConstraints.HORIZONTAL;
    gbcTextFieldNickName.gridx = 1;
    gbcTextFieldNickName.gridy = 2;
    panelDatos.add(textFieldNickName, gbcTextFieldNickName);
    this.textFieldNickName.setColumns(10);
    
    GridBagConstraints gbcLblNombre = new GridBagConstraints();
    gbcLblNombre.anchor = GridBagConstraints.EAST;
    gbcLblNombre.insets = new Insets(0, 0, 5, 5);
    gbcLblNombre.gridx = 0;
    gbcLblNombre.gridy = 3;
    JLabel lblNombre = new JLabel("Nombre:");
    panelDatos.add(lblNombre, gbcLblNombre);
    
    this.textFieldNombre = new JTextField();
    this.textFieldNombre.setEditable(true);
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
    this.textFieldApellido.setEditable(true);
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
    
    lblNewLabel = new JLabel("Contraseña:");
    GridBagConstraints gbcLblNewLabel = new GridBagConstraints();
    gbcLblNewLabel.anchor = GridBagConstraints.EAST;
    gbcLblNewLabel.insets = new Insets(0, 0, 5, 5);
    gbcLblNewLabel.gridx = 0;
    gbcLblNewLabel.gridy = 6;
    panelDatos.add(lblNewLabel, gbcLblNewLabel);
    
    textFieldContrasenia = new JPasswordField();
    GridBagConstraints gbcTextField = new GridBagConstraints();
    gbcTextField.insets = new Insets(0, 0, 5, 0);
    gbcTextField.fill = GridBagConstraints.HORIZONTAL;
    gbcTextField.gridx = 1;
    gbcTextField.gridy = 6;
    panelDatos.add(textFieldContrasenia, gbcTextField);
    textFieldContrasenia.setColumns(10);
    
    lblNewLabel1 = new JLabel("Foto de perfil:");
    GridBagConstraints gbcLblNewLabel1 = new GridBagConstraints();
    gbcLblNewLabel1.insets = new Insets(0, 0, 5, 5);
    gbcLblNewLabel1.gridx = 0;
    gbcLblNewLabel1.gridy = 7;
    panelDatos.add(lblNewLabel1, gbcLblNewLabel1);
    
    selectImageButton = new JButton("Seleccionar Imagen");
    GridBagConstraints gbcBtnNewButton = new GridBagConstraints();
    gbcBtnNewButton.anchor = GridBagConstraints.WEST;
    gbcBtnNewButton.insets = new Insets(0, 0, 5, 0);
    gbcBtnNewButton.gridx = 1;
    gbcBtnNewButton.gridy = 7;
    panelDatos.add(selectImageButton, gbcBtnNewButton);
    
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
    gbcTextPane.gridy = 8;
    textPane.setEditable(false);
    panelDatos.add(textPane, gbcTextPane);
    
    quitarImageButton = new JButton("Borrar Imagen");
    GridBagConstraints gbcBtnButtonBorrar = new GridBagConstraints();
    gbcBtnButtonBorrar.anchor = GridBagConstraints.WEST;
    gbcBtnButtonBorrar.insets = new Insets(0, 0, 5, 0);
    gbcBtnButtonBorrar.gridx = 0;
    gbcBtnButtonBorrar.gridy = 8;
    panelDatos.add(quitarImageButton, gbcBtnButtonBorrar);
    quitarImageButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent evento) {
        fotoPerfilUsuario = null;
        textPane.setText("");
      }
    });
    
    layeredPane = new JLayeredPane();
    GridBagConstraints gbcLayeredPane = new GridBagConstraints();
    gbcLayeredPane.gridheight = 3;
    gbcLayeredPane.gridwidth = 2;
    gbcLayeredPane.insets = new Insets(0, 0, 5, 0);
    gbcLayeredPane.fill = GridBagConstraints.BOTH;
    gbcLayeredPane.gridx = 0;
    gbcLayeredPane.gridy = 9;
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
    
    GridBagConstraints gbcLblSitioWeb = new GridBagConstraints();
    gbcLblSitioWeb.anchor = GridBagConstraints.EAST;
    gbcLblSitioWeb.insets = new Insets(0, 0, 5, 5);
    gbcLblSitioWeb.gridx = 0;
    gbcLblSitioWeb.gridy = 1;
    JLabel lblSitioWeb = new JLabel("Sitio Web:");
    panelEmpresa.add(lblSitioWeb, gbcLblSitioWeb);
    
    this.textFieldSitioWeb = new JTextField();
    this.textFieldSitioWeb.setEditable(true);
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
    textAreaDescripcion.setEditable(true);
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
    
    this.fechaNacimientoChooser = new JDateChooser();
    GridBagConstraints gbcFechaNacimiento = new GridBagConstraints();
    gbcFechaNacimiento.insets = new Insets(0, 0, 5, 0);
    gbcFechaNacimiento.fill = GridBagConstraints.HORIZONTAL;
    gbcFechaNacimiento.gridx = 1;
    gbcFechaNacimiento.gridy = 0;
    panelPostulante.add(fechaNacimientoChooser, gbcFechaNacimiento);
    
    GridBagConstraints gbcLblNacionalidad = new GridBagConstraints();
    gbcLblNacionalidad.anchor = GridBagConstraints.EAST;
    gbcLblNacionalidad.insets = new Insets(0, 0, 0, 5);
    gbcLblNacionalidad.gridx = 0;
    gbcLblNacionalidad.gridy = 1;
    JLabel lblNacionalidad = new JLabel("Nacionalidad:");
    panelPostulante.add(lblNacionalidad, gbcLblNacionalidad);
    
    this.textFieldNacionalidad = new JTextField();
    this.textFieldNacionalidad.setEditable(true);
    GridBagConstraints gbcTextFieldNacionalidad = new GridBagConstraints();
    gbcTextFieldNacionalidad.fill = GridBagConstraints.HORIZONTAL;
    gbcTextFieldNacionalidad.gridx = 1;
    gbcTextFieldNacionalidad.gridy = 1;
    panelPostulante.add(this.textFieldNacionalidad, gbcTextFieldNacionalidad);
    this.textFieldNacionalidad.setColumns(10);
    
    JPanel panelBotones = new JPanel();
    getContentPane().add(panelBotones, BorderLayout.SOUTH);
    panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 120, 20));
    
    this.btnConfirmar = new JButton("Confirmar");
    btnConfirmar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evento) {
        try {
          modificarDatosUsuarios();
        } catch (UsuarioNoExisteException evento1) {
          // no imprime nada
        }
      }
    });
    panelBotones.add(btnConfirmar);
    
    this.btnCancelar = new JButton("Cancelar");
    btnCancelar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evento) {
        limpiarTodosLosDatos();
        dispose();
      }
    });
    panelBotones.add(btnCancelar);
    
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
      this.textPane.setText("");
      Dtusuario dtUsuario;
      try {
        dtUsuario = this.controladorUsuario.obtenerDtusuario(nicknameUsuario);
      } catch (UsuarioNoExisteException | IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        return;
      }
      
      this.textFieldNickName.setText(nicknameUsuario);
      this.textFieldNombre.setText(dtUsuario.getNombre());
      this.textFieldApellido.setText(dtUsuario.getApellido());
      this.textFieldEmail.setText(dtUsuario.getEmail());
      this.textFieldContrasenia.setText(dtUsuario.getContrasenia());
      BufferedImage originalImage = dtUsuario.getImagen();
      if (originalImage != null) {
        fotoPerfilUsuario = originalImage;
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
        this.fechaNacimientoChooser.setDate(Date.from(dtPostulante.getFechaNacimiento()
            .atStartOfDay(ZoneId.systemDefault()).toInstant()));
        cambiarPanel(panelPostulante);
      }
      
    }
    
  }
  
  /**
   * Metodo modificar datos usuarios .
   */
  
  public void modificarDatosUsuarios() throws UsuarioNoExisteException {
    String nombre = this.textFieldNombre.getText();
    String apelliido = this.textFieldApellido.getText();
    String sitioWeb = this.textFieldSitioWeb.getText();
    Date fechaNac = this.fechaNacimientoChooser.getDate();
    String nacionalidadPos = this.textFieldNacionalidad.getText();
    String descripcionEmpresa = this.textAreaDescripcion.getText();
    String contrasenia = this.textFieldContrasenia.getPassword().toString();
    if (checkFormulario(nombre, apelliido, fechaNac, nacionalidadPos, descripcionEmpresa,
        contrasenia)) {
      
      if (this.textPane.getText() == "") {
        fotoPerfilUsuario = null;
      }
      if (tipoUsuario.equals("Postulante")) {
        LocalDate fechaNacimiento = this.fechaNacimientoChooser.getDate().toInstant()
            .atZone(ZoneId.systemDefault()).toLocalDate();
        controladorUsuario.editarPostulante(usuarioSeleccionado, nombre, apelliido,
            fechaNacimiento, nacionalidadPos, fotoPerfilUsuario, contrasenia);
      } else if (tipoUsuario.equals("Empresa")) {
        controladorUsuario.editarEmpresa(usuarioSeleccionado, nombre, apelliido, sitioWeb,
            descripcionEmpresa, fotoPerfilUsuario, contrasenia);
      }
      
      JOptionPane.showMessageDialog(this, "Los datos se actualizaron con éxito",
          "Modificar datos de usuario", JOptionPane.INFORMATION_MESSAGE);
      limpiarTodosLosDatos();
      
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
   * Metodo obtener imagen .
   */
  
  public void obtenerImagen(File imagenPerfil) {
    try {
      BufferedImage originalImage = ImageIO.read(imagenPerfil);
      fotoPerfilUsuario = originalImage;
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
   * Metodo checkFormulario .
   */
  
  public boolean checkFormulario(String nombre, String apellido, Date fechaNacPostulante,
      String nacionalidadPostulante, String descripEmpresa, String contrasenia) {
    if (nombre.isEmpty() || apellido.isEmpty() || this.textFieldContrasenia.getPassword().length == 0) {
      JOptionPane.showMessageDialog(this, "No puede haber campos vacíos",
          "Modificar Datos Usuario", JOptionPane.ERROR_MESSAGE);
      return false;
    }
    if (tipoUsuario.equals("Postulante")) {
      if (nacionalidadPostulante.isEmpty()) {
        JOptionPane.showMessageDialog(this, "No puede haber campos vacíos",
            "Modificar Datos Usuario", JOptionPane.ERROR_MESSAGE);
        return false;
      }
      if (fechaNacPostulante == null) {
        JOptionPane.showMessageDialog(this, "Debe ingresar una fecha valida",
            "Modificar Datos Usuario", JOptionPane.ERROR_MESSAGE);
        return false;
        
      } else if (tipoUsuario.equals("Empresa")) {
        if (descripEmpresa.isEmpty()) {
          JOptionPane.showMessageDialog(this, "Debe ingresar una fecha valida",
              "Modificar Datos Usuario", JOptionPane.ERROR_MESSAGE);
          return false;
        }
      }
    }
    return true;
  }
  
  /**
   * Metodo limpiar todos los datos .
   */
  
  public void limpiarTodosLosDatos() {
    
    this.usuarioSeleccionado = "";
    this.textFieldNickName.setText("");
    this.textFieldNombre.setText("");
    this.textFieldApellido.setText("");
    this.textFieldEmail.setText("");
    this.textFieldNacionalidad.setText("");
    this.textAreaDescripcion.setText("");
    this.textFieldSitioWeb.setText("");
    this.textFieldContrasenia.setText("");
    this.fechaNacimientoChooser.setDate(null);
    this.textPane.setText("");
    
  }
  
}
