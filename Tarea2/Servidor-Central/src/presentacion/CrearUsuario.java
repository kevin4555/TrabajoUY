package presentacion;

import com.toedter.calendar.JDateChooser;
import excepciones.UsuarioEmailRepetidoException;
import excepciones.UsuarioYaExisteException;
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
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
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
import logica.interfaces.IcontroladorUsuario;

/**
 * Clase crear usuario .
 */

@SuppressWarnings("serial")
public class CrearUsuario extends JInternalFrame {
  
  private JTextField textFieldNickName;
  private JTextField textFieldNombre;
  private JTextField textFieldApellido;
  private JTextField textFieldEmail;
  private JTextField textFieldSitioWeb;
  
  private JDateChooser fechaNacimientoChooser;
  private JTextField textFieldNacionalidad;
  private IcontroladorUsuario controladorUsuario;
  private JComboBox<String> comboBoxSeleccionTipoUsuario;
  private JPanel panelEmpresa;
  private JPanel panelPostulante;
  private JLayeredPane layeredPane;
  private JScrollPane scrollPane;
  private String tipoUsuarioSeleccionado;
  private JTextArea textAreaDescripcion;
  private JButton btnCancelar;
  private JButton btnConfirmar;
  private JPanel panelDatos;
  private JLabel lblNewLabel;
  private JLabel lblNewLabel1;
  private JPasswordField passwordField;
  private JPasswordField passwordField1;
  private JLabel lblNewLabel2;
  private JButton selectImageButton;
  private JButton quitarImageButton;
  private BufferedImage fotoPerfilUsuario = null;
  private GridBagConstraints gbcTextField;
  private JTextPane textPane;
  
  /**
   * Clase crear usuario .
   */
  
  public CrearUsuario(IcontroladorUsuario contrUsuario) {
    this.controladorUsuario = contrUsuario;
    
    setIconifiable(true);
    setResizable(true);
    setMaximizable(true);
    
    setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    setTitle("Registrar Usuario");
    
    setBounds(100, 100, 594, 502);
    JPanel panelBotones = new JPanel();
    getContentPane().add(panelBotones, BorderLayout.SOUTH);
    panelBotones.setLayout(
        new FlowLayout(FlowLayout.CENTER, 120, 20));
    
    this.btnConfirmar = new JButton("Confirmar");
    btnConfirmar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evento) {
        if (registrarUsuario()) {
          limpiarTodosLosDatos();
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
    
    this.panelDatos = new JPanel();
    getContentPane().add(panelDatos, BorderLayout.CENTER);
    GridBagLayout gblPanelDatos = new GridBagLayout();
    gblPanelDatos.columnWidths = new int[] { 113, 739, 0 };
    gblPanelDatos.rowHeights = new int[] { 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    gblPanelDatos.columnWeights = new double[] { 0.0, 1.0,
        Double.MIN_VALUE };
    gblPanelDatos.rowWeights = new double[] { 0.0, 0.0, 0.0,
        0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0,
        0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
        Double.MIN_VALUE };
    panelDatos.setLayout(gblPanelDatos);
    
    GridBagConstraints gbcLblSeleccion = new GridBagConstraints();
    gbcLblSeleccion.insets = new Insets(0, 0, 5, 5);
    gbcLblSeleccion.anchor = GridBagConstraints.EAST;
    gbcLblSeleccion.gridx = 0;
    gbcLblSeleccion.gridy = 1;
    JLabel lblSeleccion = new JLabel("Tipo Usuario:");
    panelDatos.add(lblSeleccion, gbcLblSeleccion);
    
    this.comboBoxSeleccionTipoUsuario = new JComboBox<String>();
    DefaultComboBoxModel<String> model;
    String[] tipoUsuarios = { "Postulante", "Empresa" };
    model = new DefaultComboBoxModel<String>(tipoUsuarios);
    this.tipoUsuarioSeleccionado = "Postulante";
    this.comboBoxSeleccionTipoUsuario.setModel(model);
    this.comboBoxSeleccionTipoUsuario
        .addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent evento) {
            cambiarTipoUsuario(evento);
          }
        });
    
    GridBagConstraints gbcComboBoxSeleccionTipoUsuario = new GridBagConstraints();
    gbcComboBoxSeleccionTipoUsuario.insets = new Insets(0,
        0, 5, 0);
    gbcComboBoxSeleccionTipoUsuario.fill = GridBagConstraints.HORIZONTAL;
    gbcComboBoxSeleccionTipoUsuario.gridx = 1;
    gbcComboBoxSeleccionTipoUsuario.gridy = 1;
    panelDatos.add(this.comboBoxSeleccionTipoUsuario,
        gbcComboBoxSeleccionTipoUsuario);
    
    GridBagConstraints gbcLblNickname = new GridBagConstraints();
    gbcLblNickname.anchor = GridBagConstraints.EAST;
    gbcLblNickname.insets = new Insets(0, 0, 5, 5);
    gbcLblNickname.gridx = 0;
    gbcLblNickname.gridy = 2;
    JLabel lblNickname = new JLabel("Nickname:");
    panelDatos.add(lblNickname, gbcLblNickname);
    
    this.textFieldNickName = new JTextField();
    this.textFieldNickName.setEditable(true);
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
    panelDatos.add(this.textFieldApellido,
        gbcTextFieldApellido);
    this.textFieldApellido.setColumns(10);
    
    GridBagConstraints gbcLblEmail = new GridBagConstraints();
    gbcLblEmail.anchor = GridBagConstraints.EAST;
    gbcLblEmail.insets = new Insets(0, 0, 5, 5);
    gbcLblEmail.gridx = 0;
    gbcLblEmail.gridy = 5;
    JLabel lblEmail = new JLabel("Email:");
    panelDatos.add(lblEmail, gbcLblEmail);
    
    this.textFieldEmail = new JTextField();
    this.textFieldEmail.setEditable(true);
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
    
    passwordField = new JPasswordField();
    GridBagConstraints gbcPasswordField = new GridBagConstraints();
    gbcPasswordField.insets = new Insets(0, 0, 5, 0);
    gbcPasswordField.fill = GridBagConstraints.HORIZONTAL;
    gbcPasswordField.gridx = 1;
    gbcPasswordField.gridy = 6;
    panelDatos.add(passwordField, gbcPasswordField);
    
    lblNewLabel1 = new JLabel("Confirmar contraseña:");
    GridBagConstraints gbcLblNewLabel1 = new GridBagConstraints();
    gbcLblNewLabel1.anchor = GridBagConstraints.EAST;
    gbcLblNewLabel1.insets = new Insets(0, 0, 5, 5);
    gbcLblNewLabel1.gridx = 0;
    gbcLblNewLabel1.gridy = 7;
    panelDatos.add(lblNewLabel1, gbcLblNewLabel1);
    
    passwordField1 = new JPasswordField();
    GridBagConstraints gbcPasswordField1 = new GridBagConstraints();
    gbcPasswordField1.insets = new Insets(0, 0, 5, 0);
    gbcPasswordField1.fill = GridBagConstraints.HORIZONTAL;
    gbcPasswordField1.gridx = 1;
    gbcPasswordField1.gridy = 7;
    panelDatos.add(passwordField1, gbcPasswordField1);
    
    lblNewLabel2 = new JLabel("Foto de perfil:");
    GridBagConstraints gbcLblNewLabel2 = new GridBagConstraints();
    gbcLblNewLabel2.anchor = GridBagConstraints.EAST;
    gbcLblNewLabel2.insets = new Insets(0, 0, 5, 5);
    gbcLblNewLabel2.gridx = 0;
    gbcLblNewLabel2.gridy = 8;
    panelDatos.add(lblNewLabel2, gbcLblNewLabel2);
    
    selectImageButton = new JButton("Seleccionar Imagen");
    gbcTextField = new GridBagConstraints();
    gbcTextField.anchor = GridBagConstraints.WEST;
    gbcTextField.insets = new Insets(0, 0, 5, 0);
    gbcTextField.gridx = 1;
    gbcTextField.gridy = 8;
    panelDatos.add(selectImageButton, gbcTextField);
    
    selectImageButton
        .addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent evento) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(null);
            
            if (result == JFileChooser.APPROVE_OPTION) {
              
              File fotoPerfil = new File(fileChooser
                  .getSelectedFile().getAbsolutePath());
              obtenerImagen(fotoPerfil);
              
            }
          }
        });
    
    textPane = new JTextPane();
    GridBagConstraints gbcTextPane = new GridBagConstraints();
    gbcTextPane.insets = new Insets(0, 0, 5, 0);
    gbcTextPane.fill = GridBagConstraints.BOTH;
    gbcTextPane.gridx = 1;
    gbcTextPane.gridy = 9;
    panelDatos.add(textPane, gbcTextPane);
    textPane.setEditable(false);
    
    quitarImageButton = new JButton("Borrar Imagen");
    GridBagConstraints gbcBtnButtonBorrar = new GridBagConstraints();
    gbcBtnButtonBorrar.anchor = GridBagConstraints.WEST;
    gbcBtnButtonBorrar.insets = new Insets(0, 0, 5, 0);
    gbcBtnButtonBorrar.gridx = 0;
    gbcBtnButtonBorrar.gridy = 9;
    panelDatos.add(quitarImageButton, gbcBtnButtonBorrar);
    quitarImageButton
        .addActionListener(new ActionListener() {
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
    gbcLayeredPane.gridy = 10;
    panelDatos.add(layeredPane, gbcLayeredPane);
    layeredPane.setLayout(new CardLayout(0, 0));
    
    panelEmpresa = new JPanel();
    layeredPane.add(panelEmpresa, "name_918030925291900");
    GridBagLayout gblPanelEmpresa = new GridBagLayout();
    gblPanelEmpresa.columnWidths = new int[] { 114, 0, 0 };
    gblPanelEmpresa.rowHeights = new int[] { 0, 0, 0, 0, 0,
        0 };
    gblPanelEmpresa.columnWeights = new double[] { 0.0, 1.0,
        Double.MIN_VALUE };
    gblPanelEmpresa.rowWeights = new double[] { 1.0, 0.0,
        0.0, 1.0, 0.0, Double.MIN_VALUE };
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
    panelEmpresa.add(this.textFieldSitioWeb,
        gbcTextFieldSitioWeb);
    this.textFieldSitioWeb.setColumns(10);
    
    GridBagConstraints gbcLblDescripcionEmpresa = new GridBagConstraints();
    gbcLblDescripcionEmpresa.insets = new Insets(0, 0, 5,
        5);
    gbcLblDescripcionEmpresa.anchor = GridBagConstraints.EAST;
    gbcLblDescripcionEmpresa.gridx = 0;
    gbcLblDescripcionEmpresa.gridy = 2;
    JLabel lblDescripcionEmpresa = new JLabel(
        "Descripcion:");
    panelEmpresa.add(lblDescripcionEmpresa,
        gbcLblDescripcionEmpresa);
    
    scrollPane = new JScrollPane();
    scrollPane.setVerticalScrollBarPolicy(
        ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    scrollPane.setEnabled(false);
    scrollPane.setHorizontalScrollBarPolicy(
        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
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
    layeredPane.add(panelPostulante,
        "name_919472867094100");
    GridBagLayout gblPanelPostulante = new GridBagLayout();
    gblPanelPostulante.columnWidths = new int[] { 113, 0,
        0 };
    gblPanelPostulante.rowHeights = new int[] { 0, 0, 0 };
    gblPanelPostulante.columnWeights = new double[] { 0.0,
        1.0, Double.MIN_VALUE };
    gblPanelPostulante.rowWeights = new double[] { 0.0, 0.0,
        Double.MIN_VALUE };
    panelPostulante.setLayout(gblPanelPostulante);
    
    GridBagConstraints gbcLblFechaNacimiento = new GridBagConstraints();
    gbcLblFechaNacimiento.insets = new Insets(0, 0, 5, 5);
    gbcLblFechaNacimiento.anchor = GridBagConstraints.EAST;
    gbcLblFechaNacimiento.gridx = 0;
    gbcLblFechaNacimiento.gridy = 0;
    JLabel lblFechaNacimiento = new JLabel(
        "Fecha de Nacimiento:");
    panelPostulante.add(lblFechaNacimiento,
        gbcLblFechaNacimiento);
    
    this.fechaNacimientoChooser = new JDateChooser();
    GridBagConstraints gbcFechaNacimiento = new GridBagConstraints();
    gbcFechaNacimiento.insets = new Insets(0, 0, 5, 0);
    gbcFechaNacimiento.fill = GridBagConstraints.HORIZONTAL;
    gbcFechaNacimiento.gridx = 1;
    gbcFechaNacimiento.gridy = 0;
    panelPostulante.add(fechaNacimientoChooser,
        gbcFechaNacimiento);
    
    GridBagConstraints gbcLblNacionalidad = new GridBagConstraints();
    gbcLblNacionalidad.anchor = GridBagConstraints.EAST;
    gbcLblNacionalidad.insets = new Insets(0, 0, 0, 5);
    gbcLblNacionalidad.gridx = 0;
    gbcLblNacionalidad.gridy = 1;
    JLabel lblNacionalidad = new JLabel("Nacionalidad:");
    panelPostulante.add(lblNacionalidad,
        gbcLblNacionalidad);
    
    this.textFieldNacionalidad = new JTextField();
    this.textFieldNacionalidad.setEditable(true);
    GridBagConstraints gbcTextFieldNacionalidad = new GridBagConstraints();
    gbcTextFieldNacionalidad.fill = GridBagConstraints.HORIZONTAL;
    gbcTextFieldNacionalidad.gridx = 1;
    gbcTextFieldNacionalidad.gridy = 1;
    panelPostulante.add(this.textFieldNacionalidad,
        gbcTextFieldNacionalidad);
    this.textFieldNacionalidad.setColumns(10);
    cambiarPanel(panelPostulante);
  }
  
  protected boolean registrarUsuario() {
    String nickname = this.textFieldNickName.getText();
    String nombre = this.textFieldNombre.getText();
    String apellido = this.textFieldApellido.getText();
    String email = this.textFieldEmail.getText();
    String descripcion = this.textAreaDescripcion.getText();
    String sitioWeb = this.textFieldSitioWeb.getText();
    String nacionalidad = this.textFieldNacionalidad
        .getText();
    
    if (checkFormulario()) {
      try {
        if (this.textPane.getText() == "") {
          fotoPerfilUsuario = null;
        }
        char[] passwordChars = this.passwordField
            .getPassword();
        String contrasenia = new String(passwordChars);
        if (tipoUsuarioSeleccionado.equals("Postulante")) {
          LocalDate fechaNac = this.fechaNacimientoChooser
              .getDate().toInstant()
              .atZone(ZoneId.systemDefault()).toLocalDate();
          this.controladorUsuario.altaPostulante(nickname,
              nombre, apellido, email, fechaNac,
              nacionalidad, fotoPerfilUsuario, contrasenia);
          JOptionPane.showMessageDialog(this,
              "El Postulante se ha creado con éxito",
              "Registrar Usuario",
              JOptionPane.INFORMATION_MESSAGE);
          return true;
        } else {
          
          this.controladorUsuario.altaEmpresa(nickname,
              nombre, apellido, email, descripcion,
              sitioWeb, fotoPerfilUsuario, contrasenia);
          JOptionPane.showMessageDialog(this,
              "La Empresa se ha creado con éxito",
              "Registrar Usuario",
              JOptionPane.INFORMATION_MESSAGE);
          return true;
        }
      } catch (UsuarioYaExisteException e) {
        JOptionPane.showMessageDialog(this, e.getMessage(),
            "Trabajo.uy", JOptionPane.ERROR_MESSAGE);
        return false;
      } catch (UsuarioEmailRepetidoException e2) {
        JOptionPane.showMessageDialog(this, e2.getMessage(),
            "Trabajo.uy", JOptionPane.ERROR_MESSAGE);
        return false;
      }
      
    }
    ;
    
    return false;
  }
  
  /**
   * Metodo cambiar tipo de usuario .
   */
  
  public void cambiarTipoUsuario(ActionEvent evento) {
    
    String tipoSeleccionado = comboBoxSeleccionTipoUsuario
        .getSelectedItem().toString();
    if (!tipoSeleccionado.equals(tipoUsuarioSeleccionado)) {
      tipoUsuarioSeleccionado = tipoSeleccionado;
      
      if (tipoUsuarioSeleccionado.equals("Empresa")) {
        cambiarPanel(panelEmpresa);
      }
      if (tipoUsuarioSeleccionado.equals("Postulante")) {
        cambiarPanel(panelPostulante);
      }
    }
    
  }
  
  private boolean checkFormulario() {
    if (this.textFieldNickName.getText().isEmpty()
        || this.textFieldNombre.getText().isEmpty()
        || this.textFieldApellido.getText().isEmpty()
        || this.textFieldEmail.getText().isEmpty()
        || this.passwordField1.getPassword().toString()
            .isEmpty()
        || this.passwordField.getPassword().toString()
            .isEmpty()) {
      JOptionPane.showMessageDialog(this,
          "No puede haber campos vacíos",
          "Registrar Usuario", JOptionPane.ERROR_MESSAGE);
      return false;
    }
    
    if (!this.textFieldEmail.getText().contains("@")) {
      JOptionPane.showMessageDialog(this,
          "Debe ingresar un mail con el valor @",
          "Registrar Usuario", JOptionPane.ERROR_MESSAGE);
      return false;
    }
    
    char[] password1 = passwordField1.getPassword();
    char[] password2 = passwordField.getPassword();
    
    if (!Arrays.equals(password1, password2)) {
      JOptionPane.showMessageDialog(this,
          "Las contraseñas no son iguales",
          "Registrar Usuario", JOptionPane.ERROR_MESSAGE);
      return false;
    }
    
    if (this.tipoUsuarioSeleccionado.equals("Postulante")) {
      if (this.textFieldNacionalidad.getText().isEmpty()) {
        JOptionPane.showMessageDialog(this,
            "Debe ingresar una Nacionalidad",
            "Registrar Usuario", JOptionPane.ERROR_MESSAGE);
        return false;
      }
      try {
        Float.parseFloat(
            this.textFieldNacionalidad.getText());
        JOptionPane.showMessageDialog(this,
            "La nacionalidad debe ser un texto",
            "Registrar Oferta Laboral",
            JOptionPane.ERROR_MESSAGE);
        return false;
      } catch (NumberFormatException e) {
        // Esta bien
      }
      Date fechaNacimiento = fechaNacimientoChooser
          .getDate();
      if (fechaNacimiento == null
          || (fechaNacimiento.compareTo(Date.from(LocalDate
              .now().atStartOfDay(ZoneId.systemDefault())
              .toInstant()))) > 0) {
        JOptionPane.showMessageDialog(this,
            "Debe ingresar una Fecha de Nacimiento valida",
            "Registrar Usuario", JOptionPane.ERROR_MESSAGE);
        return false;
      }
    } else {
      if (this.textAreaDescripcion.getText().isEmpty()) {
        JOptionPane.showMessageDialog(this,
            "Debe ingresar una Descripcion",
            "Registrar Usuario", JOptionPane.ERROR_MESSAGE);
        return false;
      }
    }
    return true;
  }
  
  /**
   * Metodo obtener imagen .
   */
  
  public void obtenerImagen(File imagenPerfil) {
    try {
      BufferedImage originalImage = ImageIO
          .read(imagenPerfil);
      fotoPerfilUsuario = originalImage;
      this.textPane.setCaretPosition(
          textPane.getStyledDocument().getLength());
      this.textPane.setText("");
      int newWidth = 100; // Ancho deseado
      int newHeight = 100; // Alto deseado
      Image scaledImage = originalImage.getScaledInstance(
          newWidth, newHeight, Image.SCALE_SMOOTH);
      ImageIcon icono = new ImageIcon(scaledImage);
      this.textPane.insertIcon(icono);
      
    } catch (IOException e) {
      JOptionPane.showMessageDialog(this,
          "No se cargo la imagen", "Registrar Usuario",
          JOptionPane.ERROR_MESSAGE);
    } catch (java.lang.NullPointerException e2) {
      JOptionPane.showMessageDialog(this,
          "Debe ingresar una imagen valida",
          "Registrar Usuario", JOptionPane.ERROR_MESSAGE);
    }
  }
  
  /**
   * Metodo cambiar panel .
   */
  
  public void cambiarPanel(JPanel panel) {
    limpiarTodosLosDatos();
    layeredPane.removeAll();
    layeredPane.add(panel);
    layeredPane.repaint();
    layeredPane.revalidate();
  }
  
  /**
   * Metodo limpiar todos los datos .
   */
  
  public void limpiarTodosLosDatos() {
    this.textFieldNickName.setText("");
    this.textFieldNombre.setText("");
    this.textFieldApellido.setText("");
    this.textFieldEmail.setText("");
    this.passwordField1.setText("");
    this.passwordField.setText("");
    this.textFieldNacionalidad.setText("");
    this.fechaNacimientoChooser.setDate(null);
    this.textAreaDescripcion.setText("");
    this.textFieldSitioWeb.setText("");
    this.textPane.setText("");
    
  }
}
