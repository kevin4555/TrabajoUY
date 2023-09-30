package presentacion;

import excepciones.OfertaLaboralNoExisteException;
import excepciones.UsuarioNoExisteException;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import logica.datatypes.DtOfertaLaboral;
import logica.interfaces.IcontroladorOferta;
import logica.interfaces.IcontroladorUsuario;

/**
 * Clase AceptarRechazarOferta .
 */

@SuppressWarnings("serial")
public class AceptarRechazarOferta extends JInternalFrame {
  
  // Controlador de usuarios que se utilizará para
  // las acciones del JFrame
  private IcontroladorOferta controladorOfertaLaboral;
  private IcontroladorUsuario controladorUsuario;
  private JTextField textFieldHorarioOferta;
  private JTextField textFieldRemuneracion;
  private JTextField textFieldCiudad;
  private JTextField textFieldDepartamento;
  private JComboBox<String> comboBoxSeleccionUsuario;
  private JComboBox<String> comboBoxSeleccionOferta;
  private String ofertaSeleccionada;
  private JButton btnConfirmar;
  private JButton btnRechazar;
  private JButton btnCerrar;
  private JPanel panelDatos;
  
  /**
   * Create the frame.
   */
  public AceptarRechazarOferta(IcontroladorOferta icontOfeLab,
      IcontroladorUsuario icontUsuLab) {
    // Se inicializa con el controlador de oferta
    controladorOfertaLaboral = icontOfeLab;
    controladorUsuario = icontUsuLab;
    
    // Propiedades del JInternalFrame como dimensión,
    // posición dentro del frame,
    // etc.
    setResizable(true);
    setIconifiable(true);
    setMaximizable(true);
    setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    setTitle("Aceptar/Rechazar Oferta laboral");
    setBounds(30, 30, 713, 380);
    
    JPanel panelBotones = new JPanel();
    getContentPane().add(panelBotones, BorderLayout.SOUTH);
    panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 120, 20));
    
    this.btnConfirmar = new JButton("Confirmar");
    btnConfirmar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        confirmarOfertaLaboral();
        
      }
    });
    panelBotones.add(btnConfirmar);
    
    this.btnRechazar = new JButton("Rechazar");
    btnRechazar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        rechazarOfertaLaboral();
        
      }
    });
    panelBotones.add(btnRechazar);
    
    this.btnCerrar = new JButton("Cancelar");
    btnCerrar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
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
    JLabel lblSeleccion = new JLabel("Seleccionar Empresa:");
    panelDatos.add(lblSeleccion, gbcLblSeleccion);
    
    this.comboBoxSeleccionUsuario = new JComboBox<String>();
    this.comboBoxSeleccionUsuario.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          cargarDatosUsuarios(e);
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
    
    GridBagConstraints gbcLblOfertas = new GridBagConstraints();
    gbcLblOfertas.anchor = GridBagConstraints.EAST;
    gbcLblOfertas.insets = new Insets(0, 0, 5, 5);
    gbcLblOfertas.gridx = 0;
    gbcLblOfertas.gridy = 12;
    JLabel lblOfertas = new JLabel("Ofertas Laborales:");
    panelDatos.add(lblOfertas, gbcLblOfertas);
    
    this.comboBoxSeleccionOferta = new JComboBox<String>();
    this.comboBoxSeleccionOferta.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          cargarDatosOferta(e);
        } catch (OfertaLaboralNoExisteException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
          
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
    
  }
  
  /**
   * Metodo cargar empresas .
   */
  
  public void cargarEmpresas() {
    try {
      ArrayList<String> listaEmpresas = this.controladorUsuario.listarEmpresas();
      String[] arrayEmpresas;
      arrayEmpresas = listaEmpresas.toArray(new String[0]);
      Arrays.sort(arrayEmpresas);
      DefaultComboBoxModel<String> model;
      model = new DefaultComboBoxModel<String>(arrayEmpresas);
      this.comboBoxSeleccionUsuario.setModel(model);
      
    } catch (Exception e) {
      // TODO: handle exception
    }
  }
  
  /**
   * Metodo confirmar oferta laboral .
   */
  
  public void confirmarOfertaLaboral() {
    String oferta = comboBoxSeleccionOferta.getSelectedItem().toString();
    if (ofertaSeleccionada != oferta) {
      try {
        DtOfertaLaboral dtOferta = controladorOfertaLaboral.obtenerDtOfertaLaboral(oferta);
        
        // CAMBIAR ESTADO OFERTA
        
        JOptionPane.showMessageDialog(this, "Falta agregar el cambio de estado",
            "Agregar/Rechazar Oferta Laboral", JOptionPane.ERROR_MESSAGE);
        limpiarTodosLosDatos();
      } catch (OfertaLaboralNoExisteException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }
  
  /**
   * Metodo rechazar oferta laboral .
   */
  
  public void rechazarOfertaLaboral() {
    String oferta = comboBoxSeleccionOferta.getSelectedItem().toString();
    if (ofertaSeleccionada != oferta) {
      try {
        DtOfertaLaboral dtOferta = controladorOfertaLaboral.obtenerDtOfertaLaboral(oferta);
        
        // CAMBIAR ESTADO OFERTA
        
        JOptionPane.showMessageDialog(this, "Falta agregar el cambio de estado",
            "Agregar/Rechazar Oferta Laboral", JOptionPane.ERROR_MESSAGE);
        limpiarTodosLosDatos();
      } catch (OfertaLaboralNoExisteException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }
  
  /**
   * Metodo cargar datos usuario .
   */
  
  public void cargarDatosUsuarios(ActionEvent e) throws UsuarioNoExisteException {
    
    String nicknameUsuario = comboBoxSeleccionUsuario.getSelectedItem().toString();
    
    ArrayList<String> listaOfertas = this.controladorUsuario
        .listaOfertasUsuario(nicknameUsuario);
    String[] arrayOfertas = listaOfertas.toArray(new String[0]);
    Arrays.sort(arrayOfertas);
    DefaultComboBoxModel<String> model;
    model = new DefaultComboBoxModel<String>(arrayOfertas);
    this.comboBoxSeleccionOferta.setModel(model);
  }
  
  protected void cargarDatosOferta(ActionEvent e) throws OfertaLaboralNoExisteException {
    String oferta = comboBoxSeleccionOferta.getSelectedItem().toString();
    if (ofertaSeleccionada != oferta) {
      DtOfertaLaboral dtOferta = controladorOfertaLaboral.obtenerDtOfertaLaboral(oferta);
      this.textFieldHorarioOferta
          .setText(dtOferta.getHorarioInicio() + " - " + dtOferta.getHorarioFinal());
      this.textFieldRemuneracion.setText(dtOferta.getRemuneracion().toString());
      this.textFieldCiudad.setText(dtOferta.getCiudad());
      this.textFieldDepartamento.setText(dtOferta.getDepartamento());
    }
  }
  
  /**
   * Metodo limpiar datos .
   */
  
  public void limpiarTodosLosDatos() {
    
    this.ofertaSeleccionada = "";
    this.textFieldDepartamento.setText("");
    this.textFieldCiudad.setText("");
    this.textFieldHorarioOferta.setText("");
    this.textFieldRemuneracion.setText("");
    
    /*
     * ArrayList<String> listaOfertas = new
     * ArrayList<String>(); String [] arrayOfertas =
     * listaOfertas.toArray(new String[0]);
     * Arrays.sort(arrayOfertas);
     * DefaultComboBoxModel<String> model; model = new
     * DefaultComboBoxModel<String>(arrayOfertas);
     * this.comboBoxSeleccionOferta.setModel(model);
     * 
     * this.comboBoxSeleccionUsuario.setModel(model);
     */
    
  }
}
