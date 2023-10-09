package presentacion;

import excepciones.OfertaLaboralNoExisteException;
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
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import logica.datatypes.DtOfertaLaboral;
import logica.interfaces.IcontroladorOferta;
import logica.interfaces.IcontroladorUsuario;

/**
 * Clase Consulta de oferta laboral .
 */

@SuppressWarnings("serial")
public class ConsultaOfertaLaboral extends JInternalFrame {
  
  // Controlador de usuarios que se utilizará para
  // las acciones del JFrame
  private IcontroladorOferta controlOfertaLab;
  private IcontroladorUsuario controlUsuarioLab;
  private JComboBox<String> comboBoxEmpresasRegistradas;
  private JComboBox<String> comboBoxOfertasLaborales;
  private JTextField textFieldHorarioOferta;
  private JTextField textFieldRemuneracion;
  private JTextField textFieldCiudad;
  private JTextField textFieldDepartamento;
  private JTextField textFieldFechaAlta;
  private JTextField textFieldFechaResolucion;
  private JTextField textFieldEstado;
  private JTextField textFieldTipoPublicacion;
  private JTextArea textAreaDescripcion;
  private JTextPane textPane;
  private JButton btnCerrar;
  private JPanel panelDatos;
  private JPanel ubicacionCentro;
  private JScrollPane scrollPane;
  
  /**
   * Create the frame.
   */
  public ConsultaOfertaLaboral(IcontroladorOferta icontOfeLab,
      IcontroladorUsuario icontUsuLab) {
    // Se inicializa con el controlador de oferta
    controlOfertaLab = icontOfeLab;
    controlUsuarioLab = icontUsuLab;
    
    // Propiedades del JInternalFrame como dimensión,
    // posición dentro del frame,
    // etc.
    setResizable(true);
    setIconifiable(true);
    setMaximizable(true);
    setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    setTitle("Consultar Oferta Laboral");
    setBounds(30, 30, 500, 547);
    
    JPanel panelBotones = new JPanel();
    getContentPane().add(panelBotones, BorderLayout.SOUTH);
    panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 120, 20));
    
    btnCerrar = new JButton("Cerrar");
    panelBotones.add(btnCerrar);
    
    btnCerrar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evento) {
        limpiarInformacion();
        dispose();
      }
    });
    
    this.panelDatos = new JPanel();
    getContentPane().add(panelDatos, BorderLayout.NORTH);
    GridBagLayout gblpanelDatos = new GridBagLayout();
    gblpanelDatos.columnWidths = new int[] { 113, 739, 0 };
    gblpanelDatos.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0 };
    gblpanelDatos.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
    gblpanelDatos.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0,
        0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0 };
    panelDatos.setLayout(gblpanelDatos);
    
    GridBagConstraints gbclblSeleccion = new GridBagConstraints();
    gbclblSeleccion.insets = new Insets(0, 0, 5, 5);
    gbclblSeleccion.anchor = GridBagConstraints.EAST;
    gbclblSeleccion.gridx = 0;
    gbclblSeleccion.gridy = 1;
    JLabel lblSeleccion = new JLabel("Seleccionar Empresa:");
    panelDatos.add(lblSeleccion, gbclblSeleccion);
    
    this.comboBoxEmpresasRegistradas = new JComboBox<String>();
    GridBagConstraints gbccomboBoxSeleccionUsuario = new GridBagConstraints();
    gbccomboBoxSeleccionUsuario.insets = new Insets(0, 0, 5, 0);
    gbccomboBoxSeleccionUsuario.fill = GridBagConstraints.HORIZONTAL;
    gbccomboBoxSeleccionUsuario.gridx = 1;
    gbccomboBoxSeleccionUsuario.gridy = 1;
    panelDatos.add(this.comboBoxEmpresasRegistradas, gbccomboBoxSeleccionUsuario);
    
    GridBagConstraints gbclblOfertas = new GridBagConstraints();
    gbclblOfertas.anchor = GridBagConstraints.EAST;
    gbclblOfertas.insets = new Insets(0, 0, 5, 5);
    gbclblOfertas.gridx = 0;
    gbclblOfertas.gridy = 2;
    JLabel lblOfertasLaborales = new JLabel("Ofertas Laborales:");
    panelDatos.add(lblOfertasLaborales, gbclblOfertas);
    lblOfertasLaborales.setVisible(false);
    
    this.comboBoxOfertasLaborales = new JComboBox<String>();
    GridBagConstraints gbccomboBoxSeleccionOferta = new GridBagConstraints();
    gbccomboBoxSeleccionOferta.insets = new Insets(0, 0, 5, 0);
    gbccomboBoxSeleccionOferta.fill = GridBagConstraints.HORIZONTAL;
    gbccomboBoxSeleccionOferta.gridx = 1;
    gbccomboBoxSeleccionOferta.gridy = 2;
    panelDatos.add(this.comboBoxOfertasLaborales, gbccomboBoxSeleccionOferta);
    this.comboBoxOfertasLaborales.setVisible(false);
    
    this.ubicacionCentro = new JPanel();
    getContentPane().add(ubicacionCentro, BorderLayout.CENTER);
    GridBagLayout gblpanelDatosOfertas = new GridBagLayout();
    gblpanelDatosOfertas.columnWidths = new int[] { 113, 739, 0 };
    gblpanelDatosOfertas.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0 };
    gblpanelDatosOfertas.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
    gblpanelDatosOfertas.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0,
        1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0 };
    this.ubicacionCentro.setLayout(gblpanelDatosOfertas);
    this.ubicacionCentro.setVisible(false);
    
    GridBagConstraints gbclblRemuneracion = new GridBagConstraints();
    gbclblRemuneracion.anchor = GridBagConstraints.EAST;
    gbclblRemuneracion.insets = new Insets(0, 0, 5, 5);
    gbclblRemuneracion.gridx = 0;
    gbclblRemuneracion.gridy = 4;
    JLabel lblRemuneracion = new JLabel("Remuneracion");
    ubicacionCentro.add(lblRemuneracion, gbclblRemuneracion);
    
    this.textFieldRemuneracion = new JTextField();
    GridBagConstraints gbctextFieldRemuneracion = new GridBagConstraints();
    gbctextFieldRemuneracion.insets = new Insets(0, 0, 5, 0);
    gbctextFieldRemuneracion.fill = GridBagConstraints.HORIZONTAL;
    gbctextFieldRemuneracion.gridx = 1;
    gbctextFieldRemuneracion.gridy = 4;
    ubicacionCentro.add(this.textFieldRemuneracion, gbctextFieldRemuneracion);
    this.textFieldRemuneracion.setColumns(10);
    this.textFieldRemuneracion.setEditable(false);
    
    GridBagConstraints gbclblCiudad = new GridBagConstraints();
    gbclblCiudad.anchor = GridBagConstraints.EAST;
    gbclblCiudad.insets = new Insets(0, 0, 5, 5);
    gbclblCiudad.gridx = 0;
    gbclblCiudad.gridy = 5;
    JLabel lblCiudad = new JLabel("Ciudad");
    ubicacionCentro.add(lblCiudad, gbclblCiudad);
    
    this.textFieldCiudad = new JTextField();
    GridBagConstraints gbctextFieldCiudad = new GridBagConstraints();
    gbctextFieldCiudad.insets = new Insets(0, 0, 5, 0);
    gbctextFieldCiudad.fill = GridBagConstraints.HORIZONTAL;
    gbctextFieldCiudad.gridx = 1;
    gbctextFieldCiudad.gridy = 5;
    ubicacionCentro.add(this.textFieldCiudad, gbctextFieldCiudad);
    this.textFieldCiudad.setColumns(10);
    this.textFieldCiudad.setEditable(false);
    
    GridBagConstraints gbclblDepartamento = new GridBagConstraints();
    gbclblDepartamento.anchor = GridBagConstraints.EAST;
    gbclblDepartamento.insets = new Insets(0, 0, 5, 5);
    gbclblDepartamento.gridx = 0;
    gbclblDepartamento.gridy = 6;
    JLabel lblDepartamento = new JLabel("Departamento");
    ubicacionCentro.add(lblDepartamento, gbclblDepartamento);
    
    this.textFieldDepartamento = new JTextField();
    GridBagConstraints gbctextFieldDepartamento = new GridBagConstraints();
    gbctextFieldDepartamento.insets = new Insets(0, 0, 5, 0);
    gbctextFieldDepartamento.fill = GridBagConstraints.HORIZONTAL;
    gbctextFieldDepartamento.gridx = 1;
    gbctextFieldDepartamento.gridy = 6;
    ubicacionCentro.add(this.textFieldDepartamento, gbctextFieldDepartamento);
    this.textFieldDepartamento.setColumns(10);
    this.textFieldDepartamento.setEditable(false);
    
    GridBagConstraints gbclblHorarioInicio = new GridBagConstraints();
    gbclblHorarioInicio.anchor = GridBagConstraints.EAST;
    gbclblHorarioInicio.insets = new Insets(0, 0, 5, 5);
    gbclblHorarioInicio.gridx = 0;
    gbclblHorarioInicio.gridy = 7;
    JLabel lblHorarioInicio = new JLabel("Horario");
    ubicacionCentro.add(lblHorarioInicio, gbclblHorarioInicio);
    
    this.textFieldHorarioOferta = new JTextField();
    GridBagConstraints gbctextFieldHorarioInicio = new GridBagConstraints();
    gbctextFieldHorarioInicio.insets = new Insets(0, 0, 5, 0);
    gbctextFieldHorarioInicio.fill = GridBagConstraints.HORIZONTAL;
    gbctextFieldHorarioInicio.gridx = 1;
    gbctextFieldHorarioInicio.gridy = 7;
    this.ubicacionCentro.add(this.textFieldHorarioOferta, gbctextFieldHorarioInicio);
    this.textFieldHorarioOferta.setColumns(10);
    this.textFieldHorarioOferta.setEditable(false);
    
    GridBagConstraints gbclblDescripcion = new GridBagConstraints();
    gbclblDescripcion.insets = new Insets(0, 0, 5, 5);
    gbclblDescripcion.anchor = GridBagConstraints.EAST;
    gbclblDescripcion.gridx = 0;
    gbclblDescripcion.gridy = 8;
    JLabel lblDescripcion = new JLabel("Descripcion:");
    this.ubicacionCentro.add(lblDescripcion, gbclblDescripcion);
    
    scrollPane = new JScrollPane();
    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    scrollPane.setEnabled(false);
    scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    GridBagConstraints gbcscrollPane = new GridBagConstraints();
    gbcscrollPane.gridheight = 2;
    gbcscrollPane.insets = new Insets(0, 0, 5, 0);
    gbcscrollPane.fill = GridBagConstraints.BOTH;
    gbcscrollPane.gridx = 1;
    gbcscrollPane.gridy = 8;
    this.ubicacionCentro.add(scrollPane, gbcscrollPane);
    
    textAreaDescripcion = new JTextArea();
    textAreaDescripcion.setLineWrap(true);
    textAreaDescripcion.setWrapStyleWord(true);
    textAreaDescripcion.setEditable(false);
    scrollPane.setViewportView(textAreaDescripcion);
    
    GridBagConstraints gbclblfechaAlta = new GridBagConstraints();
    gbclblfechaAlta.anchor = GridBagConstraints.EAST;
    gbclblfechaAlta.insets = new Insets(0, 0, 5, 5);
    gbclblfechaAlta.gridx = 0;
    gbclblfechaAlta.gridy = 11;
    JLabel lblFechaAlta = new JLabel("Fecha de alta");
    ubicacionCentro.add(lblFechaAlta, gbclblfechaAlta);
    
    this.textFieldFechaAlta = new JTextField();
    GridBagConstraints gbctextFieldFechaAlta = new GridBagConstraints();
    gbctextFieldFechaAlta.insets = new Insets(0, 0, 5, 0);
    gbctextFieldFechaAlta.fill = GridBagConstraints.HORIZONTAL;
    gbctextFieldFechaAlta.gridx = 1;
    gbctextFieldFechaAlta.gridy = 11;
    ubicacionCentro.add(this.textFieldFechaAlta, gbctextFieldFechaAlta);
    this.textFieldFechaAlta.setColumns(10);
    this.textFieldFechaAlta.setEditable(false);
    
    GridBagConstraints gbclblfechaResolucion = new GridBagConstraints();
    gbclblfechaResolucion.anchor = GridBagConstraints.EAST;
    gbclblfechaResolucion.insets = new Insets(0, 0, 5, 5);
    gbclblfechaResolucion.gridx = 0;
    gbclblfechaResolucion.gridy = 12;
    JLabel lblFechaResolucion = new JLabel("Fecha de resolución");
    ubicacionCentro.add(lblFechaResolucion, gbclblfechaResolucion);
    
    this.textFieldFechaResolucion = new JTextField();
    GridBagConstraints gbctextFieldFechaResolucion = new GridBagConstraints();
    gbctextFieldFechaResolucion.insets = new Insets(0, 0, 5, 0);
    gbctextFieldFechaResolucion.fill = GridBagConstraints.HORIZONTAL;
    gbctextFieldFechaResolucion.gridx = 1;
    gbctextFieldFechaResolucion.gridy = 12;
    ubicacionCentro.add(this.textFieldFechaResolucion, gbctextFieldFechaResolucion);
    this.textFieldFechaResolucion.setColumns(10);
    this.textFieldFechaResolucion.setEditable(false);
    
    GridBagConstraints gbclblEstado = new GridBagConstraints();
    gbclblEstado.anchor = GridBagConstraints.EAST;
    gbclblEstado.insets = new Insets(0, 0, 5, 5);
    gbclblEstado.gridx = 0;
    gbclblEstado.gridy = 13;
    JLabel lblEstado = new JLabel("Estado");
    ubicacionCentro.add(lblEstado, gbclblEstado);
    
    this.textFieldEstado = new JTextField();
    GridBagConstraints gbctextFieldEstado = new GridBagConstraints();
    gbctextFieldEstado.insets = new Insets(0, 0, 5, 0);
    gbctextFieldEstado.fill = GridBagConstraints.HORIZONTAL;
    gbctextFieldEstado.gridx = 1;
    gbctextFieldEstado.gridy = 13;
    ubicacionCentro.add(this.textFieldEstado, gbctextFieldEstado);
    this.textFieldEstado.setColumns(10);
    this.textFieldEstado.setEditable(false);
    
    GridBagConstraints gbclblTipoPublicacion = new GridBagConstraints();
    gbclblTipoPublicacion.anchor = GridBagConstraints.EAST;
    gbclblTipoPublicacion.insets = new Insets(0, 0, 5, 5);
    gbclblTipoPublicacion.gridx = 0;
    gbclblTipoPublicacion.gridy = 14;
    JLabel lblTipoPublicacion = new JLabel("Tipo de publicacion");
    ubicacionCentro.add(lblTipoPublicacion, gbclblTipoPublicacion);
    
    this.textFieldTipoPublicacion = new JTextField();
    GridBagConstraints gbctextFieldTipoPublicacion = new GridBagConstraints();
    gbctextFieldTipoPublicacion.insets = new Insets(0, 0, 5, 0);
    gbctextFieldTipoPublicacion.fill = GridBagConstraints.HORIZONTAL;
    gbctextFieldTipoPublicacion.gridx = 1;
    gbctextFieldTipoPublicacion.gridy = 14;
    ubicacionCentro.add(this.textFieldTipoPublicacion, gbctextFieldTipoPublicacion);
    this.textFieldTipoPublicacion.setColumns(10);
    this.textFieldTipoPublicacion.setEditable(false);
    
    GridBagConstraints gbcLblFotoOferta = new GridBagConstraints();
    gbcLblFotoOferta.insets = new Insets(0, 0, 5, 5);
    gbcLblFotoOferta.gridx = 0;
    gbcLblFotoOferta.gridy = 15;
    JLabel labelFoto = new JLabel("Foto Oferta:");
    ubicacionCentro.add(labelFoto, gbcLblFotoOferta);
    
    this.textPane = new JTextPane();
    GridBagConstraints gbcTextPane = new GridBagConstraints();
    gbcTextPane.insets = new Insets(0, 0, 5, 0);
    gbcTextPane.fill = GridBagConstraints.BOTH;
    gbcTextPane.gridx = 1;
    gbcTextPane.gridy = 15;
    this.textPane.setEditable(false);
    ubicacionCentro.add(textPane, gbcTextPane);
    
    this.comboBoxEmpresasRegistradas.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evento) {
        cargarOfertaEmpresa(evento);
        lblOfertasLaborales.setVisible(true);
        comboBoxOfertasLaborales.setVisible(true);
      }
    });
    
    this.comboBoxOfertasLaborales.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evento) {
        cargarDatosOferta(evento);
        ubicacionCentro.setVisible(true);
      }
    });
    
  }
  
  public String dateToString(Date fecha) {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    return formatter.format(fecha);
  }
  
  /**
   * Metodo cargar empresas .
   */
  
  public void cargarEmpresas() {
    String[] empresas;
    empresas = (controlUsuarioLab.listarEmpresas()).toArray(new String[0]);
    DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(empresas);
    this.comboBoxEmpresasRegistradas.setModel(model);
  }
  
  /**
   * Metodo cargar oferta empresa .
   */
  
  public void cargarOfertaEmpresa(ActionEvent evento) {
    String empresa = (String) (this.comboBoxEmpresasRegistradas).getSelectedItem();
    String[] ofertasLaborales;
    limpiarInformacion(); // correccion
    try {
      ofertasLaborales = (controlUsuarioLab.obtenerOfertasEmpresa(empresa))
          .toArray(new String[0]);
      DefaultComboBoxModel<String> model;
      model = new DefaultComboBoxModel<String>(ofertasLaborales);
      this.comboBoxOfertasLaborales.setModel(model);
    } catch (UsuarioNoExisteException evento1) {
      JOptionPane.showMessageDialog(this, "Debe seleccionar una empresa",
          "Consulta Oferta Laboral", JOptionPane.ERROR_MESSAGE);
    }
  }
  
  /**
   * Metodo cargar datos oferta .
   */
  
  public void cargarDatosOferta(ActionEvent evento) {
    String ofertaLaboral = (String) (this.comboBoxOfertasLaborales).getSelectedItem();
    this.textPane.setText("");
    DtOfertaLaboral dtOfertaLaboral;
    try {
      dtOfertaLaboral = controlOfertaLab.obtenerDtOfertaLaboral(ofertaLaboral);
      (this.textAreaDescripcion).setText(dtOfertaLaboral.getDescripcion());
      (this.textFieldHorarioOferta).setText(
          dtOfertaLaboral.getHorarioInicio() + " - " + dtOfertaLaboral.getHorarioFinal());
      (this.textFieldRemuneracion).setText(dtOfertaLaboral.getRemuneracion().toString());
      (this.textFieldCiudad).setText(dtOfertaLaboral.getCiudad());
      (this.textFieldDepartamento).setText(dtOfertaLaboral.getDepartamento());
      (this.textFieldFechaAlta).setText(dtOfertaLaboral.getFechaAlta().toString());
      (this.textFieldEstado).setText(dtOfertaLaboral.getEstadoOferta().toString());
      BufferedImage originalImage = dtOfertaLaboral.getImagen();
      if (originalImage != null) {
        int newWidth = 100; // Ancho deseado
        int newHeight = 100; // Alto deseado
        Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight,
            Image.SCALE_SMOOTH);
        this.textPane.setCaretPosition(textPane.getStyledDocument().getLength());
        ImageIcon icono = new ImageIcon(scaledImage);
        this.textPane.insertIcon(icono);
      }
      (this.textFieldTipoPublicacion).setText(dtOfertaLaboral.getNombreTipoPublicacion());
      if (dtOfertaLaboral.getFechaResolucion() == null) {
        this.textFieldFechaResolucion.setText("");
      } else {
        this.textFieldFechaResolucion.setText(dtOfertaLaboral.getFechaResolucion().toString());
      }
    } catch (OfertaLaboralNoExisteException evento1) {
      JOptionPane.showMessageDialog(this, "Debe seleccionar una oferta",
          "Consulta Oferta Laboral", JOptionPane.ERROR_MESSAGE);
    } catch (IOException e) {
      JOptionPane.showMessageDialog(this, "Error con la imagen", "Consulta Oferta Laboral",
          JOptionPane.ERROR_MESSAGE);
      e.printStackTrace();
    }
  }
  
  /**
   * Metodo limpiar informacion .
   */
  
  public void limpiarInformacion() {
    this.textAreaDescripcion.setText("");
    this.textFieldHorarioOferta.setText("");
    this.textFieldRemuneracion.setText("");
    this.textFieldCiudad.setText("");
    this.textFieldDepartamento.setText("");
    this.textFieldFechaAlta.setText("");
    this.textFieldEstado.setText("");
    this.textFieldFechaResolucion.setText("");
    this.textFieldTipoPublicacion.setText("");
    this.textPane.setText("");
    
  }
  
}
