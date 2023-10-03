package presentacion;

import excepciones.PaquetePublicacionNoExisteException;
import excepciones.TipoPublicacionNoExisteException;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
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
import javax.swing.ScrollPaneConstants;
import logica.datatypes.DttipoPublicacion;
import logica.interfaces.IcontroladorOferta;

/**
 * Clase AgregarTipoPublicacionAlPaquete .
 */

@SuppressWarnings("serial")
public class AgregarTipoPublicacionAlPaquete extends JInternalFrame {
  
  // Controlador de usuarios que se utilizará para
  // las acciones del JFrame
  private IcontroladorOferta controladorOfertaLaboral;
  private JTextField textFieldExposicion;
  private JTextField textFieldCosto;
  private JTextField textFieldDuracion;
  private JTextField textFieldFechaAlta;
  private JComboBox<String> comboBoxSeleccionPaquete;
  private JComboBox<String> comboBoxSeleccionTiposPublicaciones;
  private String tipoPublicacionSeleccionada;
  private JButton btnConfirmar;
  private JButton btnCerrar;
  private JPanel panelDatos;
  private JScrollPane scrollPane;
  private JTextArea textAreaDescripcion;
  private JTextField textFieldCantidadIncluida;
  
  /**
   * Create the frame.
   */
  public AgregarTipoPublicacionAlPaquete(IcontroladorOferta icontOfeLab) {
    // Se inicializa con el controlador de oferta
    controladorOfertaLaboral = icontOfeLab;
    this.tipoPublicacionSeleccionada = "";
    
    // Propiedades del JInternalFrame como dimensión,
    // posición dentro del frame,
    // etc.
    setResizable(true);
    setIconifiable(true);
    setMaximizable(true);
    setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    setTitle("Agregar tipo de publicación al paquete");
    setBounds(30, 30, 713, 380);
    
    JPanel panelBotones = new JPanel();
    getContentPane().add(panelBotones, BorderLayout.SOUTH);
    panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 120, 20));
    
    this.btnConfirmar = new JButton("Confirmar");
    btnConfirmar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evento) {
        agregarTipoPublicacionAlPaquete();
        
      }
    });
    panelBotones.add(btnConfirmar);
    
    this.btnCerrar = new JButton("Cancelar");
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
    gblPanelDatos.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0,
        0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0 };
    panelDatos.setLayout(gblPanelDatos);
    
    
    GridBagConstraints gbcLblSeleccion = new GridBagConstraints();
    gbcLblSeleccion.insets = new Insets(0, 0, 5, 5);
    gbcLblSeleccion.anchor = GridBagConstraints.EAST;
    gbcLblSeleccion.gridx = 0;
    gbcLblSeleccion.gridy = 1;
    JLabel lblSeleccion = new JLabel("Seleccionar Paquete:");
    panelDatos.add(lblSeleccion, gbcLblSeleccion);
    
    this.comboBoxSeleccionPaquete = new JComboBox<String>();
    this.comboBoxSeleccionPaquete.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evento) {
        cargarTipoPublicacionNoIncluidasEnPaquete(evento);
        
      }
    });
    
    GridBagConstraints gbcComboBoxSeleccionUsuario = new GridBagConstraints();
    gbcComboBoxSeleccionUsuario.insets = new Insets(0, 0, 5, 0);
    gbcComboBoxSeleccionUsuario.fill = GridBagConstraints.HORIZONTAL;
    gbcComboBoxSeleccionUsuario.gridx = 1;
    gbcComboBoxSeleccionUsuario.gridy = 1;
    panelDatos.add(this.comboBoxSeleccionPaquete, gbcComboBoxSeleccionUsuario);
    
    
    GridBagConstraints gbcLblOfertas = new GridBagConstraints();
    gbcLblOfertas.anchor = GridBagConstraints.EAST;
    gbcLblOfertas.insets = new Insets(0, 0, 5, 5);
    gbcLblOfertas.gridx = 0;
    gbcLblOfertas.gridy = 12;
    JLabel lblTiposPublicaciones = new JLabel("Tipos de publicaciones:");
    panelDatos.add(lblTiposPublicaciones, gbcLblOfertas);
    
    this.comboBoxSeleccionTiposPublicaciones = new JComboBox<String>();
    this.comboBoxSeleccionTiposPublicaciones.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evento) {
        cargarDatosTipoPublicacion(evento);
      }
    });
    GridBagConstraints gbcComboBoxSeleccionOferta = new GridBagConstraints();
    gbcComboBoxSeleccionOferta.insets = new Insets(0, 0, 5, 0);
    gbcComboBoxSeleccionOferta.fill = GridBagConstraints.HORIZONTAL;
    gbcComboBoxSeleccionOferta.gridx = 1;
    gbcComboBoxSeleccionOferta.gridy = 12;
    panelDatos.add(this.comboBoxSeleccionTiposPublicaciones, gbcComboBoxSeleccionOferta);
    
    GridBagConstraints gbcLblHorarioOferta = new GridBagConstraints();
    gbcLblHorarioOferta.anchor = GridBagConstraints.EAST;
    gbcLblHorarioOferta.insets = new Insets(0, 0, 5, 5);
    gbcLblHorarioOferta.gridx = 0;
    gbcLblHorarioOferta.gridy = 13;
    JLabel lblHorarioOferta = new JLabel("Exposicion");
    panelDatos.add(lblHorarioOferta, gbcLblHorarioOferta);
    
    this.textFieldExposicion = new JTextField();
    this.textFieldExposicion.setEditable(false);
    GridBagConstraints gbcTextFieldNombreOferta = new GridBagConstraints();
    gbcTextFieldNombreOferta.insets = new Insets(0, 0, 5, 0);
    gbcTextFieldNombreOferta.fill = GridBagConstraints.HORIZONTAL;
    gbcTextFieldNombreOferta.gridx = 1;
    gbcTextFieldNombreOferta.gridy = 13;
    panelDatos.add(this.textFieldExposicion, gbcTextFieldNombreOferta);
    this.textFieldExposicion.setColumns(10);
    
    
    GridBagConstraints gbcLblRemuneracion = new GridBagConstraints();
    gbcLblRemuneracion.anchor = GridBagConstraints.EAST;
    gbcLblRemuneracion.insets = new Insets(0, 0, 5, 5);
    gbcLblRemuneracion.gridx = 0;
    gbcLblRemuneracion.gridy = 14;
    JLabel lblRemuneracion = new JLabel("Costo");
    panelDatos.add(lblRemuneracion, gbcLblRemuneracion);
    
    this.textFieldCosto = new JTextField();
    this.textFieldCosto.setEditable(false);
    GridBagConstraints gbcTextFieldRemuneracion = new GridBagConstraints();
    gbcTextFieldRemuneracion.insets = new Insets(0, 0, 5, 0);
    gbcTextFieldRemuneracion.fill = GridBagConstraints.HORIZONTAL;
    gbcTextFieldRemuneracion.gridx = 1;
    gbcTextFieldRemuneracion.gridy = 14;
    panelDatos.add(this.textFieldCosto, gbcTextFieldRemuneracion);
    this.textFieldCosto.setColumns(10);
    
    
    GridBagConstraints gbcLblCiudad = new GridBagConstraints();
    gbcLblCiudad.anchor = GridBagConstraints.EAST;
    gbcLblCiudad.insets = new Insets(0, 0, 5, 5);
    gbcLblCiudad.gridx = 0;
    gbcLblCiudad.gridy = 15;
    JLabel lblCiudad = new JLabel("Duración");
    panelDatos.add(lblCiudad, gbcLblCiudad);
    
    this.textFieldDuracion = new JTextField();
    this.textFieldDuracion.setEditable(false);
    GridBagConstraints gbcTextFieldCiudad = new GridBagConstraints();
    gbcTextFieldCiudad.insets = new Insets(0, 0, 5, 0);
    gbcTextFieldCiudad.fill = GridBagConstraints.HORIZONTAL;
    gbcTextFieldCiudad.gridx = 1;
    gbcTextFieldCiudad.gridy = 15;
    panelDatos.add(this.textFieldDuracion, gbcTextFieldCiudad);
    this.textFieldDuracion.setColumns(10);
    
    
    GridBagConstraints gbcLblFechaAlta = new GridBagConstraints();
    gbcLblFechaAlta.anchor = GridBagConstraints.EAST;
    gbcLblFechaAlta.insets = new Insets(0, 0, 5, 5);
    gbcLblFechaAlta.gridx = 0;
    gbcLblFechaAlta.gridy = 16;
    JLabel lblFechaAlta = new JLabel("Fecha de alta");
    panelDatos.add(lblFechaAlta, gbcLblFechaAlta);
    
    this.textFieldFechaAlta = new JTextField();
    this.textFieldFechaAlta.setEditable(false);
    GridBagConstraints gbcTextFieldFechaAlta = new GridBagConstraints();
    gbcTextFieldFechaAlta.insets = new Insets(0, 0, 5, 0);
    gbcTextFieldFechaAlta.fill = GridBagConstraints.HORIZONTAL;
    gbcTextFieldFechaAlta.gridx = 1;
    gbcTextFieldFechaAlta.gridy = 16;
    panelDatos.add(this.textFieldFechaAlta, gbcTextFieldFechaAlta);
    this.textFieldFechaAlta.setColumns(10);
    
    
    GridBagConstraints gbcLblDescripcionEmpresa = new GridBagConstraints();
    gbcLblDescripcionEmpresa.insets = new Insets(0, 0, 5, 5);
    gbcLblDescripcionEmpresa.anchor = GridBagConstraints.EAST;
    gbcLblDescripcionEmpresa.gridx = 0;
    gbcLblDescripcionEmpresa.gridy = 17;
    JLabel lblDescripcionEmpresa = new JLabel("Descripcion");
    panelDatos.add(lblDescripcionEmpresa, gbcLblDescripcionEmpresa);
    
    scrollPane = new JScrollPane();
    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    scrollPane.setEnabled(false);
    scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    GridBagConstraints gbcScrollPane = new GridBagConstraints();
    gbcScrollPane.gridheight = 2;
    gbcScrollPane.insets = new Insets(0, 0, 5, 0);
    gbcScrollPane.fill = GridBagConstraints.BOTH;
    gbcScrollPane.gridx = 1;
    gbcScrollPane.gridy = 17;
    panelDatos.add(scrollPane, gbcScrollPane);
    
    textAreaDescripcion = new JTextArea();
    textAreaDescripcion.setLineWrap(true);
    textAreaDescripcion.setWrapStyleWord(true);
    textAreaDescripcion.setEditable(false);
    scrollPane.setViewportView(textAreaDescripcion);
    
    
    GridBagConstraints gbcLblCantidadInlcuidaa = new GridBagConstraints();
    gbcLblCantidadInlcuidaa.anchor = GridBagConstraints.EAST;
    gbcLblCantidadInlcuidaa.insets = new Insets(0, 0, 5, 5);
    gbcLblCantidadInlcuidaa.gridx = 0;
    gbcLblCantidadInlcuidaa.gridy = 20;
    JLabel lblCantidadInlcuida = new JLabel("Ingresa Cantidad");
    panelDatos.add(lblCantidadInlcuida, gbcLblCantidadInlcuidaa);
    
    this.textFieldCantidadIncluida = new JTextField();
    GridBagConstraints gbcTextFieldCantidadInlcuida = new GridBagConstraints();
    gbcTextFieldCantidadInlcuida.insets = new Insets(0, 0, 5, 0);
    gbcTextFieldCantidadInlcuida.fill = GridBagConstraints.HORIZONTAL;
    gbcTextFieldCantidadInlcuida.gridx = 1;
    gbcTextFieldCantidadInlcuida.gridy = 20;
    panelDatos.add(this.textFieldCantidadIncluida, gbcTextFieldCantidadInlcuida);
    this.textFieldCantidadIncluida.setColumns(10);
    
  }
  
  /**
   * Metodo cargar paquetes .
   */
  
  public void cargarPaquetes() {
    try {
      List<String> listaPaquetes = this.controladorOfertaLaboral.listarPaquetes();
      String[] arrayEmpresas;
      arrayEmpresas = listaPaquetes.toArray(new String[0]);
      Arrays.sort(arrayEmpresas);
      DefaultComboBoxModel<String> model;
      model = new DefaultComboBoxModel<String>(arrayEmpresas);
      this.comboBoxSeleccionPaquete.setModel(model);
      
    } catch (Exception e) {
      // TODO: handle exception
    }
  }
  
  /**
   * Metodo cargar tipo de publicacion no incluidas en paquete .
   */
  
  @SuppressWarnings({ })
  public void cargarTipoPublicacionNoIncluidasEnPaquete(ActionEvent evento) {
    
    try {
      String nombrePaquete = comboBoxSeleccionPaquete.getSelectedItem().toString();
      
      /*
       * if (nombrePaquete != paqueteSeleccionado) {
       * paqueteSeleccionado = nombrePaquete;
       * limpiarDatosPublicaciones();
       * DTPaquetePublicacion dtPaquetePublicaciones =
       * this.controladorOfertaLaboral.obtenerDTPaquete(
       * nombrePaquete);
       * this.textFieldDescuento.setText(String.valueOf(
       * dtPaquetePublicaciones. getDescuento()));
       * this.textFieldValidez.setText(String.valueOf(
       * dtPaquetePublicaciones. getPeriodoValidez()));
       * //this.textFieldFechaAltaPaquete.setText(
       * dtPaquetePublicaciones.getFecha);
       * this.textAreaDescripcionPaquete.setText(
       * dtPaquetePublicaciones.getDescripcion ());
       * 
       * }
       */
      
      List<String> listaTipoDePublicaciones = this.controladorOfertaLaboral
          .listarTipoDePublicaciones();
      List<String> listaTipoDePublicacionesDePaquete;
      listaTipoDePublicacionesDePaquete = this.controladorOfertaLaboral
          .listarTipoPublicacionDePaquete(nombrePaquete);
      List<String> listaTipoDePublicacionesNoIncluidas = new ArrayList<String>();
      for (String nombreTipoPublicacion : listaTipoDePublicaciones) {
        if (!listaTipoDePublicacionesDePaquete.contains(nombreTipoPublicacion)) {
          listaTipoDePublicacionesNoIncluidas.add(nombreTipoPublicacion);
        }
      }
      
      String[] arrayTiposPublicacionesNoIncluidas = listaTipoDePublicacionesNoIncluidas
          .toArray(new String[0]);
      Arrays.sort(arrayTiposPublicacionesNoIncluidas);
      DefaultComboBoxModel<String> model;
      model = new DefaultComboBoxModel<String>(arrayTiposPublicacionesNoIncluidas);
      this.comboBoxSeleccionTiposPublicaciones.setModel(model);
    } catch (PaquetePublicacionNoExisteException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    
  }
  
  protected void cargarDatosTipoPublicacion(ActionEvent evento) {
    String tipoPublicacion = comboBoxSeleccionTiposPublicaciones.getSelectedItem().toString();
    if (tipoPublicacionSeleccionada != tipoPublicacion) {
      
      try {
        DttipoPublicacion dtTipoPublicacion;
        dtTipoPublicacion = controladorOfertaLaboral.obtenerDttipoPublicacion(tipoPublicacion);
        this.textFieldExposicion.setText(dtTipoPublicacion.getExposicion());
        this.textFieldCosto.setText(String.valueOf(dtTipoPublicacion.getCosto()));
        this.textFieldDuracion
            .setText(String.valueOf(dtTipoPublicacion.getDuracionDia()) + " días");
        this.textFieldFechaAlta.setText(dtTipoPublicacion.getFechaAlta().toString());
        this.textAreaDescripcion.setText(dtTipoPublicacion.getDescripcion());
        
      } catch (TipoPublicacionNoExisteException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
      
    }
  }
  
  /**
   * Metodo agregar tipo de publicacion al paquete .
   */
  
  public void agregarTipoPublicacionAlPaquete() {
    
    try {
      if (comboBoxSeleccionTiposPublicaciones.getSelectedIndex() == -1
          || comboBoxSeleccionPaquete.getSelectedIndex() == -1) {
        JOptionPane.showMessageDialog(this,
            "Debe seleccionar un paquete y un tipo de publicacion",
            "Agregar tipo de publicacion a paquete", JOptionPane.ERROR_MESSAGE);
      } else {
        String tipoPublicacion = comboBoxSeleccionTiposPublicaciones.getSelectedItem()
            .toString();
        String nombrePaquete = comboBoxSeleccionPaquete.getSelectedItem().toString();
        int cantidadIncluida = Integer.parseInt(this.textFieldCantidadIncluida.getText());
        if (cantidadIncluida <= 0) {
          JOptionPane.showMessageDialog(this,
              "La cantidad incluida debe ser un número mayor a cero",
              "Agregar tipo de publicacion a paquete", JOptionPane.ERROR_MESSAGE);
        } else {
          this.controladorOfertaLaboral.agregarTipoPublicacionAlPaquete(cantidadIncluida,
              tipoPublicacion, nombrePaquete);
          JOptionPane.showMessageDialog(this, "El tipo de publicación se agregó con con éxito",
              "Agregar tipo de publicacion a paquete", JOptionPane.INFORMATION_MESSAGE);
          limpiarTodosLosDatos();
        }
        
      }
      
    } catch (TipoPublicacionNoExisteException | PaquetePublicacionNoExisteException evento) {
      // no imprime nada
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(this,
          "La cantidad incluida debe ser un número mayor a cero",
          "Agregar tipo de publicacion a paquete", JOptionPane.ERROR_MESSAGE);
      
    }
  }
  
  /**
   * Metodo limpiar datos de publicaciones .
   */
  
  public void limpiarDatosPublicaciones() {
    this.textFieldCosto.setText("");
    this.textFieldExposicion.setText("");
    this.textFieldDuracion.setText("");
    this.textFieldFechaAlta.setText("");
    this.textAreaDescripcion.setText("");
    this.textFieldCantidadIncluida.setText("");
  }
  
  /**
   * Metodo limpiar todos los datos .
   */
  
  public void limpiarTodosLosDatos() {
    
    this.tipoPublicacionSeleccionada = "";
    this.textFieldCosto.setText("");
    this.textFieldExposicion.setText("");
    this.textFieldDuracion.setText("");
    this.textFieldFechaAlta.setText("");
    this.textAreaDescripcion.setText("");
    this.textFieldCantidadIncluida.setText("");
  }
}
