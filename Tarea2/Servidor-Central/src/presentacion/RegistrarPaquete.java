package presentacion;

import com.toedter.calendar.JDateChooser;
import excepciones.PaquetePublicacionYaExisteException;
import excepciones.TipoPublicacionNoExisteException;
import excepciones.TipoPublicacionYaExisteException;
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
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import logica.interfaces.IcontroladorOferta;

/**
 * Clase registrar paquete .
 */

@SuppressWarnings("serial")
public class RegistrarPaquete extends JInternalFrame {
  
  private JTextField textFieldNombre;
  private JTextField textFieldValidez;
  private JTextField textFieldDescuento;
  
  private JDateChooser fechaAltaChooser;
  private IcontroladorOferta controladorOferta;
  private JScrollPane scrollPane;
  private JTextPane textAreaDescripcion;
  private JButton btnCancelar;
  private JButton btnConfirmar;
  private JPanel panelDatos;
  private JLabel lblNewLabel2;
  private JButton selectImageButton;
  private BufferedImage fotoPaquete = null;
  private GridBagConstraints gbcTextField;
  private JTextPane textPane;
  
  /**
   * Constructor .
   */
  
  public RegistrarPaquete(IcontroladorOferta contralodorOferta) {
    this.controladorOferta = contralodorOferta;
    
    setIconifiable(true);
    setResizable(true);
    setMaximizable(true);
    
    setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    setTitle("Registrar Paquete");
    
    setBounds(100, 100, 594, 502);
    JPanel panelBotones = new JPanel();
    getContentPane().add(panelBotones, BorderLayout.SOUTH);
    panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 120, 20));
    
    this.btnConfirmar = new JButton("Confirmar");
    btnConfirmar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (registrarPaquete()) {
          limpiarTodosLosDatos();
        }
      }
    });
    panelBotones.add(btnConfirmar);
    
    this.btnCancelar = new JButton("Cancelar");
    btnCancelar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        limpiarTodosLosDatos();
        dispose();
      }
    });
    panelBotones.add(btnCancelar);
    
    this.panelDatos = new JPanel();
    getContentPane().add(panelDatos, BorderLayout.CENTER);
    GridBagLayout gblPanelDatos = new GridBagLayout();
    gblPanelDatos.columnWidths = new int[] { 113, 739, 0 };
    gblPanelDatos.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0 };
    gblPanelDatos.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
    gblPanelDatos.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0,
        1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
    panelDatos.setLayout(gblPanelDatos);
    
    GridBagConstraints gbcLblNombre = new GridBagConstraints();
    gbcLblNombre.anchor = GridBagConstraints.EAST;
    gbcLblNombre.insets = new Insets(0, 0, 5, 5);
    gbcLblNombre.gridx = 0;
    gbcLblNombre.gridy = 1;
    JLabel lblNombre = new JLabel("Nombre");
    panelDatos.add(lblNombre, gbcLblNombre);
    
    this.textFieldNombre = new JTextField();
    this.textFieldNombre.setEditable(true);
    GridBagConstraints gbcTextFieldNombre = new GridBagConstraints();
    gbcTextFieldNombre.insets = new Insets(0, 0, 5, 0);
    gbcTextFieldNombre.fill = GridBagConstraints.HORIZONTAL;
    gbcTextFieldNombre.gridx = 1;
    gbcTextFieldNombre.gridy = 1;
    panelDatos.add(textFieldNombre, gbcTextFieldNombre);
    textFieldNombre.setColumns(10);
    
    GridBagConstraints gbcLblValidez = new GridBagConstraints();
    gbcLblValidez.anchor = GridBagConstraints.EAST;
    gbcLblValidez.insets = new Insets(0, 0, 5, 5);
    gbcLblValidez.gridx = 0;
    gbcLblValidez.gridy = 2;
    JLabel lblValidez = new JLabel("Período de validez");
    panelDatos.add(lblValidez, gbcLblValidez);
    
    this.textFieldValidez = new JTextField();
    this.textFieldValidez.setEditable(true);
    GridBagConstraints textFieldValidez = new GridBagConstraints();
    textFieldValidez.insets = new Insets(0, 0, 5, 0);
    textFieldValidez.fill = GridBagConstraints.HORIZONTAL;
    textFieldValidez.gridx = 1;
    textFieldValidez.gridy = 2;
    panelDatos.add(this.textFieldValidez, textFieldValidez);
    this.textFieldValidez.setColumns(10);
    
    GridBagConstraints gbcLblDescuento = new GridBagConstraints();
    gbcLblDescuento.anchor = GridBagConstraints.EAST;
    gbcLblDescuento.insets = new Insets(0, 0, 5, 5);
    gbcLblDescuento.gridx = 0;
    gbcLblDescuento.gridy = 3;
    JLabel lblDescuento = new JLabel("Descuento");
    panelDatos.add(lblDescuento, gbcLblDescuento);
    
    this.textFieldDescuento = new JTextField();
    this.textFieldDescuento.setEditable(true);
    GridBagConstraints gbcTextFieldDescuento = new GridBagConstraints();
    gbcTextFieldDescuento.insets = new Insets(0, 0, 5, 0);
    gbcTextFieldDescuento.fill = GridBagConstraints.HORIZONTAL;
    gbcTextFieldDescuento.gridx = 1;
    gbcTextFieldDescuento.gridy = 3;
    panelDatos.add(this.textFieldDescuento, gbcTextFieldDescuento);
    this.textFieldDescuento.setColumns(10);
    
    GridBagConstraints gbcLblDescripcion = new GridBagConstraints();
    gbcLblDescripcion.insets = new Insets(0, 0, 5, 5);
    gbcLblDescripcion.anchor = GridBagConstraints.EAST;
    gbcLblDescripcion.gridx = 0;
    gbcLblDescripcion.gridy = 8;
    JLabel lblDescripcion = new JLabel("Descripcion");
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
    
    textAreaDescripcion = new JTextPane();
    textAreaDescripcion.setEditable(true);
    scrollPane.setViewportView(textAreaDescripcion);
    
    lblNewLabel2 = new JLabel("Imagen paquete:");
    GridBagConstraints gbcLblNewLabel2 = new GridBagConstraints();
    gbcLblNewLabel2.anchor = GridBagConstraints.EAST;
    gbcLblNewLabel2.insets = new Insets(0, 0, 5, 5);
    gbcLblNewLabel2.gridx = 0;
    gbcLblNewLabel2.gridy = 4;
    panelDatos.add(lblNewLabel2, gbcLblNewLabel2);
    
    selectImageButton = new JButton("Seleccionar Imagen");
    
    gbcTextField = new GridBagConstraints();
    gbcTextField.anchor = GridBagConstraints.WEST;
    gbcTextField.insets = new Insets(0, 0, 5, 0);
    gbcTextField.gridx = 1;
    gbcTextField.gridy = 4;
    panelDatos.add(selectImageButton, gbcTextField);
    
    selectImageButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
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
    gbcTextPane.gridy = 5;
    panelDatos.add(textPane, gbcTextPane);
    textPane.setEditable(false);
    
    GridBagConstraints gbcLblFechaAlta = new GridBagConstraints();
    gbcLblFechaAlta.insets = new Insets(0, 0, 5, 5);
    gbcLblFechaAlta.anchor = GridBagConstraints.EAST;
    gbcLblFechaAlta.gridx = 0;
    gbcLblFechaAlta.gridy = 6;
    JLabel lblFechaAlta = new JLabel("Fecha de alta:");
    panelDatos.add(lblFechaAlta, gbcLblFechaAlta);
    
    this.fechaAltaChooser = new JDateChooser();
    GridBagConstraints gbcFechaAlta = new GridBagConstraints();
    gbcFechaAlta.insets = new Insets(0, 0, 5, 0);
    gbcFechaAlta.fill = GridBagConstraints.HORIZONTAL;
    gbcFechaAlta.gridx = 1;
    gbcFechaAlta.gridy = 6;
    panelDatos.add(fechaAltaChooser, gbcFechaAlta);
    
  }
  
  protected boolean registrarPaquete() {
    String nombre = this.textFieldNombre.getText();
    String descripcion = this.textAreaDescripcion.getText();
    String descuento = this.textFieldDescuento.getText();
    String validez = this.textFieldValidez.getText();
    
    if (checkFormulario()) {
      try {
        if (this.textPane.getText() == "") {
          fotoPaquete = null;
        }
        LocalDate fechaAltaPaquete = this.fechaAltaChooser.getDate().toInstant()
            .atZone(ZoneId.systemDefault()).toLocalDate();
        this.controladorOferta.registrarPaquete(nombre, descripcion, Integer.parseInt(validez),
            Float.parseFloat(descuento), fotoPaquete, fechaAltaPaquete, null);
        JOptionPane.showMessageDialog(this, "El paquete se ha creado con éxito",
            "Registrar Paquete", JOptionPane.INFORMATION_MESSAGE);
        return true;
      } catch (NumberFormatException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (PaquetePublicacionYaExisteException e) {
        JOptionPane.showMessageDialog(this,
            "Ya existe registrado un paquete con el nombre " + nombre, "Registrar Paquete",
            JOptionPane.ERROR_MESSAGE);
      } catch (TipoPublicacionYaExisteException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (TipoPublicacionNoExisteException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      
    }
    ;
    
    return false;
  }
  
  private boolean checkFormulario() {
    if (this.textAreaDescripcion.getText().isEmpty()
        || this.textFieldNombre.getText().isEmpty()
        || this.textFieldDescuento.getText().isEmpty()
        || this.textFieldValidez.getText().isEmpty()) {
      JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Registrar Paquete",
          JOptionPane.ERROR_MESSAGE);
      return false;
    }
    
    if (this.fechaAltaChooser.getDate() == null) {
      JOptionPane.showMessageDialog(this, "Debe ingresar una Fecha de Alta",
          "Registrar Paquete", JOptionPane.ERROR_MESSAGE);
      return false;
    }
    
    try {
      if (Float.parseFloat(this.textFieldDescuento.getText()) <= 0
          || Float.parseFloat(this.textFieldDescuento.getText()) > 100) {
        JOptionPane.showMessageDialog(this,
            "El descuento(en porcentaje) debe ser un número entre 1 y 100",
            "Registrar Paquete", JOptionPane.ERROR_MESSAGE);
        return false;
      }
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(this,
          "El descuento(en porcentaje) debe ser un número entre 1 y 100", "Registrar Paquete",
          JOptionPane.ERROR_MESSAGE);
      return false;
    }
    
    try {
      if (Integer.parseInt(this.textFieldValidez.getText()) <= 0) {
        JOptionPane.showMessageDialog(this,
            "El período de validez(en días) debe ser un número entero mayor a cero",
            "Registrar Paquete", JOptionPane.ERROR_MESSAGE);
        return false;
      }
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(this,
          "El período de validez(en días) debe ser un número entero mayor a cero",
          "Registrar Paquete", JOptionPane.ERROR_MESSAGE);
      return false;
    }
    return true;
    
  }
  
  /**
   * Metodo obtener imagen .
   */
  
  public void obtenerImagen(File imagenPerfil) {
    try {
      BufferedImage originalImage = ImageIO.read(imagenPerfil);
      fotoPaquete = originalImage;
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
   * Metodo limpiar todos los datos .
   */
  
  public void limpiarTodosLosDatos() {
    this.textFieldValidez.setText("");
    this.textFieldNombre.setText("");
    this.textFieldDescuento.setText("");
    this.fechaAltaChooser.setDate(null);
    this.textAreaDescripcion.setText("");
    this.textPane.setText("");
    
  }
}
