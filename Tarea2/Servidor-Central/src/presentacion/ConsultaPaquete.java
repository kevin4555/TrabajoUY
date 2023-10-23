package presentacion;

import excepciones.PaquetePublicacionNoExisteException;
import excepciones.TipoPublicacionNoExisteException;
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
import java.util.Arrays;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import logica.datatypes.DtCantidadTipoPublicacion;
import logica.datatypes.DtPaquetePublicacion;
import logica.datatypes.DtTipoPublicacion;
import logica.interfaces.IcontroladorOferta;

/**
 * Clase Consulta de paquete .
 */

@SuppressWarnings("serial")
public class ConsultaPaquete extends JInternalFrame {
  
  // Controlador de usuarios que se utilizará para
  // las acciones del JFrame
  private IcontroladorOferta controladorOfertaLaboral;
  private JTextField textFieldCostoPaquete;
  private JTextField textFieldExposicion;
  private JTextField textFieldCosto;
  private JTextField textFieldDuracion;
  private JTextField textFieldFechaAlta;
  private JComboBox<String> comboBoxSeleccionPaquete;
  private JComboBox<String> comboBoxSeleccionTiposPublicaciones;
  private String tipoPublicacionSeleccionada;
  private String paqueteSeleccionado;
  private JButton btnCerrar;
  private JPanel panelDatos;
  private JTextField textFieldCantidadIncluida;
  private JTextField textFieldValidez;
  private JTextField textFieldDescuento;
  private JTextField textFieldFechaAltaPaquete;
  private JTextPane textPane;
  private JTextPane textPanePaquete;
  private JTextPane textPaneTipoPublicacion;
  
  /**
   * Create the frame.
   */
  public ConsultaPaquete(IcontroladorOferta icontOfeLab) {
    // Se inicializa con el controlador de oferta
    controladorOfertaLaboral = icontOfeLab;
    this.paqueteSeleccionado = "";
    this.tipoPublicacionSeleccionada = "";
    
    // Propiedades del JInternalFrame como dimensión,
    // posición dentro del frame,
    // etc.
    setResizable(true);
    setIconifiable(true);
    setMaximizable(true);
    setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    setTitle("Agregar tipo de publicación al paquete");
    setBounds(30, 30, 613, 580);
    
    JPanel panelBotones = new JPanel();
    getContentPane().add(panelBotones, BorderLayout.SOUTH);
    panelBotones.setLayout(
        new FlowLayout(FlowLayout.CENTER, 120, 20));
    
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
    gblPanelDatos.rowHeights = new int[] { 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    gblPanelDatos.columnWeights = new double[] { 0.0, 1.0,
        Double.MIN_VALUE };
    gblPanelDatos.rowWeights = new double[] { 0.0, 0.0, 0.0,
        0.0, 1.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0,
        0.0, 0.0, 0.0, 0.0, 1.0, 1.0 };
    panelDatos.setLayout(gblPanelDatos);
    
    GridBagConstraints gbcLblSeleccion = new GridBagConstraints();
    gbcLblSeleccion.insets = new Insets(0, 0, 5, 5);
    gbcLblSeleccion.anchor = GridBagConstraints.EAST;
    gbcLblSeleccion.gridx = 0;
    gbcLblSeleccion.gridy = 1;
    JLabel lblSeleccion = new JLabel(
        "Seleccionar Paquete:");
    panelDatos.add(lblSeleccion, gbcLblSeleccion);
    
    this.comboBoxSeleccionPaquete = new JComboBox<String>();
    this.comboBoxSeleccionPaquete
        .addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent evento) {
            cargarTipoPublicacionEnPaquete(evento);
            
          }
        });
    
    GridBagConstraints gbcComboBoxSeleccionUsuario = new GridBagConstraints();
    gbcComboBoxSeleccionUsuario.insets = new Insets(0, 0, 5,
        0);
    gbcComboBoxSeleccionUsuario.fill = GridBagConstraints.HORIZONTAL;
    gbcComboBoxSeleccionUsuario.gridx = 1;
    gbcComboBoxSeleccionUsuario.gridy = 1;
    panelDatos.add(this.comboBoxSeleccionPaquete,
        gbcComboBoxSeleccionUsuario);
    
    GridBagConstraints gbcLblDescuento = new GridBagConstraints();
    gbcLblDescuento.anchor = GridBagConstraints.EAST;
    gbcLblDescuento.insets = new Insets(0, 0, 5, 5);
    gbcLblDescuento.gridx = 0;
    gbcLblDescuento.gridy = 2;
    JLabel lblDescuento = new JLabel("Descuento");
    panelDatos.add(lblDescuento, gbcLblDescuento);
    
    this.textFieldDescuento = new JTextField();
    this.textFieldDescuento.setEditable(false);
    GridBagConstraints gbcTextFieldDescuento = new GridBagConstraints();
    gbcTextFieldDescuento.insets = new Insets(0, 0, 5, 0);
    gbcTextFieldDescuento.fill = GridBagConstraints.HORIZONTAL;
    gbcTextFieldDescuento.gridx = 1;
    gbcTextFieldDescuento.gridy = 2;
    panelDatos.add(textFieldDescuento,
        gbcTextFieldDescuento);
    textFieldDescuento.setColumns(10);
    
    GridBagConstraints gbcLblValidez = new GridBagConstraints();
    gbcLblValidez.anchor = GridBagConstraints.EAST;
    gbcLblValidez.insets = new Insets(0, 0, 5, 5);
    gbcLblValidez.gridx = 0;
    gbcLblValidez.gridy = 3;
    JLabel lblValidez = new JLabel("Período de validez");
    panelDatos.add(lblValidez, gbcLblValidez);
    
    this.textFieldValidez = new JTextField();
    this.textFieldValidez.setEditable(false);
    GridBagConstraints gbcTextFieldValidez = new GridBagConstraints();
    gbcTextFieldValidez.insets = new Insets(0, 0, 5, 0);
    gbcTextFieldValidez.fill = GridBagConstraints.HORIZONTAL;
    gbcTextFieldValidez.gridx = 1;
    gbcTextFieldValidez.gridy = 3;
    panelDatos.add(textFieldValidez, gbcTextFieldValidez);
    textFieldValidez.setColumns(10);
    
    GridBagConstraints gbcLblDescripcionPaquete = new GridBagConstraints();
    gbcLblDescripcionPaquete.insets = new Insets(0, 0, 5,
        5);
    gbcLblDescripcionPaquete.anchor = GridBagConstraints.EAST;
    gbcLblDescripcionPaquete.gridx = 0;
    gbcLblDescripcionPaquete.gridy = 4;
    JLabel lblDescripcionPaquete = new JLabel(
        "Descripcion");
    panelDatos.add(lblDescripcionPaquete,
        gbcLblDescripcionPaquete);
    
    textPanePaquete = new JTextPane();
    GridBagConstraints gbcTextPane = new GridBagConstraints();
    gbcTextPane.insets = new Insets(0, 0, 5, 0);
    gbcTextPane.fill = GridBagConstraints.BOTH;
    gbcTextPane.gridx = 1;
    gbcTextPane.gridy = 4;
    panelDatos.add(textPanePaquete, gbcTextPane);
    
    GridBagConstraints gbcLblFechaAltaPaquete = new GridBagConstraints();
    gbcLblFechaAltaPaquete.anchor = GridBagConstraints.EAST;
    gbcLblFechaAltaPaquete.insets = new Insets(0, 0, 5, 5);
    gbcLblFechaAltaPaquete.gridx = 0;
    gbcLblFechaAltaPaquete.gridy = 7;
    JLabel lblFechaAltaPaquete = new JLabel(
        "Fecha de alta");
    panelDatos.add(lblFechaAltaPaquete,
        gbcLblFechaAltaPaquete);
    
    this.textFieldFechaAltaPaquete = new JTextField();
    this.textFieldFechaAltaPaquete.setEditable(false);
    GridBagConstraints gbcTextFieldFechaAltaPaquete = new GridBagConstraints();
    gbcTextFieldFechaAltaPaquete.insets = new Insets(0, 0,
        5, 0);
    gbcTextFieldFechaAltaPaquete.fill = GridBagConstraints.HORIZONTAL;
    gbcTextFieldFechaAltaPaquete.gridx = 1;
    gbcTextFieldFechaAltaPaquete.gridy = 7;
    panelDatos.add(this.textFieldFechaAltaPaquete,
        gbcTextFieldFechaAltaPaquete);
    this.textFieldFechaAltaPaquete.setColumns(10);
    
    GridBagConstraints gbcLblCostoPaquete = new GridBagConstraints();
    gbcLblCostoPaquete.anchor = GridBagConstraints.EAST;
    gbcLblCostoPaquete.insets = new Insets(0, 0, 5, 5);
    gbcLblCostoPaquete.gridx = 0;
    gbcLblCostoPaquete.gridy = 8;
    JLabel lblCostoPaquete = new JLabel("Costo paquete");
    panelDatos.add(lblCostoPaquete, gbcLblCostoPaquete);
    
    this.textFieldCostoPaquete = new JTextField();
    this.textFieldCostoPaquete.setEditable(false);
    GridBagConstraints gbcTextFieldCostoPaquete = new GridBagConstraints();
    gbcTextFieldCostoPaquete.insets = new Insets(0, 0, 5,
        0);
    gbcTextFieldCostoPaquete.fill = GridBagConstraints.HORIZONTAL;
    gbcTextFieldCostoPaquete.gridx = 1;
    gbcTextFieldCostoPaquete.gridy = 8;
    panelDatos.add(this.textFieldCostoPaquete,
        gbcTextFieldCostoPaquete);
    this.textFieldCostoPaquete.setColumns(10);
    
    GridBagConstraints gbcLblFotoOferta = new GridBagConstraints();
    gbcLblFotoOferta.insets = new Insets(0, 0, 5, 5);
    gbcLblFotoOferta.gridx = 0;
    gbcLblFotoOferta.gridy = 9;
    JLabel labelFoto = new JLabel("Foto Paquete:");
    panelDatos.add(labelFoto, gbcLblFotoOferta);
    
    this.textPane = new JTextPane();
    GridBagConstraints gbcTextPaneFoto = new GridBagConstraints();
    gbcTextPaneFoto.insets = new Insets(0, 0, 5, 0);
    gbcTextPaneFoto.fill = GridBagConstraints.BOTH;
    gbcTextPaneFoto.gridx = 1;
    gbcTextPaneFoto.gridy = 9;
    this.textPane.setEditable(false);
    panelDatos.add(textPane, gbcTextPaneFoto);
    
    GridBagConstraints gbcLblOfertas = new GridBagConstraints();
    gbcLblOfertas.anchor = GridBagConstraints.EAST;
    gbcLblOfertas.insets = new Insets(0, 0, 5, 5);
    gbcLblOfertas.gridx = 0;
    gbcLblOfertas.gridy = 12;
    JLabel lblTiposPublicaciones = new JLabel(
        "Tipos de publicaciones:");
    panelDatos.add(lblTiposPublicaciones, gbcLblOfertas);
    
    this.comboBoxSeleccionTiposPublicaciones = new JComboBox<String>();
    this.comboBoxSeleccionTiposPublicaciones
        .addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent evento) {
            cargarDatosTipoPublicacion(evento);
          }
        });
    GridBagConstraints gbcComboBoxSeleccionOferta = new GridBagConstraints();
    gbcComboBoxSeleccionOferta.insets = new Insets(0, 0, 5,
        0);
    gbcComboBoxSeleccionOferta.fill = GridBagConstraints.HORIZONTAL;
    gbcComboBoxSeleccionOferta.gridx = 1;
    gbcComboBoxSeleccionOferta.gridy = 12;
    panelDatos.add(this.comboBoxSeleccionTiposPublicaciones,
        gbcComboBoxSeleccionOferta);
    
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
    gbcTextFieldNombreOferta.insets = new Insets(0, 0, 5,
        0);
    gbcTextFieldNombreOferta.fill = GridBagConstraints.HORIZONTAL;
    gbcTextFieldNombreOferta.gridx = 1;
    gbcTextFieldNombreOferta.gridy = 13;
    panelDatos.add(this.textFieldExposicion,
        gbcTextFieldNombreOferta);
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
    gbcTextFieldRemuneracion.insets = new Insets(0, 0, 5,
        0);
    gbcTextFieldRemuneracion.fill = GridBagConstraints.HORIZONTAL;
    gbcTextFieldRemuneracion.gridx = 1;
    gbcTextFieldRemuneracion.gridy = 14;
    panelDatos.add(this.textFieldCosto,
        gbcTextFieldRemuneracion);
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
    panelDatos.add(this.textFieldDuracion,
        gbcTextFieldCiudad);
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
    panelDatos.add(this.textFieldFechaAlta,
        gbcTextFieldFechaAlta);
    this.textFieldFechaAlta.setColumns(10);
    
    GridBagConstraints gbcLblDescripcionEmpresa = new GridBagConstraints();
    gbcLblDescripcionEmpresa.insets = new Insets(0, 0, 5,
        5);
    gbcLblDescripcionEmpresa.anchor = GridBagConstraints.EAST;
    gbcLblDescripcionEmpresa.gridx = 0;
    gbcLblDescripcionEmpresa.gridy = 17;
    JLabel lblDescripcionEmpresa = new JLabel(
        "Descripcion");
    panelDatos.add(lblDescripcionEmpresa,
        gbcLblDescripcionEmpresa);
    
    textPaneTipoPublicacion = new JTextPane();
    GridBagConstraints gbcTextPaneTipoPublicacion = new GridBagConstraints();
    gbcTextPaneTipoPublicacion.insets = new Insets(0, 0, 5,
        0);
    gbcTextPaneTipoPublicacion.fill = GridBagConstraints.BOTH;
    gbcTextPaneTipoPublicacion.gridx = 1;
    gbcTextPaneTipoPublicacion.gridy = 17;
    panelDatos.add(textPaneTipoPublicacion,
        gbcTextPaneTipoPublicacion);
    
    GridBagConstraints gbcLblCantidadInlcuidaa = new GridBagConstraints();
    gbcLblCantidadInlcuidaa.anchor = GridBagConstraints.EAST;
    gbcLblCantidadInlcuidaa.insets = new Insets(0, 0, 0, 5);
    gbcLblCantidadInlcuidaa.gridx = 0;
    gbcLblCantidadInlcuidaa.gridy = 20;
    JLabel lblCantidadInlcuida = new JLabel(
        "Cantidad Incluida");
    panelDatos.add(lblCantidadInlcuida,
        gbcLblCantidadInlcuidaa);
    
    this.textFieldCantidadIncluida = new JTextField();
    GridBagConstraints gbcTextFieldCantidadInlcuida = new GridBagConstraints();
    gbcTextFieldCantidadInlcuida.fill = GridBagConstraints.HORIZONTAL;
    gbcTextFieldCantidadInlcuida.gridx = 1;
    gbcTextFieldCantidadInlcuida.gridy = 20;
    panelDatos.add(this.textFieldCantidadIncluida,
        gbcTextFieldCantidadInlcuida);
    this.textFieldCantidadIncluida.setColumns(10);
    this.textFieldCantidadIncluida.setEditable(false);
    
  }
  
  /**
   * Metodo cargar paquetes .
   */
  
  public void cargarPaquetes() {
    try {
      List<String> listaPaquetes = this.controladorOfertaLaboral
          .listarPaquetes();
      String[] arrayEmpresas;
      arrayEmpresas = listaPaquetes.toArray(new String[0]);
      Arrays.sort(arrayEmpresas);
      DefaultComboBoxModel<String> model;
      model = new DefaultComboBoxModel<String>(
          arrayEmpresas);
      this.comboBoxSeleccionPaquete.setModel(model);
      
    } catch (Exception e) {
      // TODO: handle exception
    }
  }
  
  /**
   * Metodo cargar tipo de publicacion en paquete .
   */
  
  public void cargarTipoPublicacionEnPaquete(
      ActionEvent evento) {
    
    try {
      if (comboBoxSeleccionPaquete
          .getSelectedIndex() == -1) {
        JOptionPane.showMessageDialog(this,
            "Debe seleccionar un paquete",
            "Consultar paquete", JOptionPane.ERROR_MESSAGE);
      } else {
        String nombrePaquete = comboBoxSeleccionPaquete
            .getSelectedItem().toString();
        
        if (nombrePaquete != paqueteSeleccionado) {
          paqueteSeleccionado = nombrePaquete;
          limpiarDatosPublicaciones();
          DtPaquetePublicacion dtPaquetePublicaciones = this.controladorOfertaLaboral
              .obtenerDtPaquete(nombrePaquete);
          this.textFieldDescuento.setText(String.valueOf(
              dtPaquetePublicaciones.getDescuento()) + "%");
          this.textFieldValidez.setText(String.valueOf(
              dtPaquetePublicaciones.getPeriodoValidez()));
          this.textFieldCostoPaquete.setText(String
              .valueOf(dtPaquetePublicaciones.getCosto()));
          this.textPanePaquete.setText(
              dtPaquetePublicaciones.getDescripcion());
          this.textFieldFechaAltaPaquete
              .setText(dtPaquetePublicaciones.getFechaAlta()
                  .toString());
          BufferedImage originalImage = dtPaquetePublicaciones
              .getImagen();
          if (originalImage != null) {
            int newWidth = 100; // Ancho deseado
            int newHeight = 100; // Alto deseado
            Image scaledImage = originalImage
                .getScaledInstance(newWidth, newHeight,
                    Image.SCALE_SMOOTH);
            this.textPane.setCaretPosition(
                textPane.getStyledDocument().getLength());
            ImageIcon icono = new ImageIcon(scaledImage);
            this.textPane.insertIcon(icono);
          }
          
        }
        
        List<String> listaTipoDePublicacionesDePaquete = this.controladorOfertaLaboral
            .listarTipoPublicacionDePaquete(nombrePaquete);
        
        String[] arrayTiposPublicacionesPaquete = listaTipoDePublicacionesDePaquete
            .toArray(new String[0]);
        Arrays.sort(arrayTiposPublicacionesPaquete);
        DefaultComboBoxModel<String> model;
        model = new DefaultComboBoxModel<String>(
            arrayTiposPublicacionesPaquete);
        this.comboBoxSeleccionTiposPublicaciones
            .setModel(model);
      }
      
    } catch (PaquetePublicacionNoExisteException
        | IOException evento1) {
      // no imprime nada
    }
    
  }
  
  protected void cargarDatosTipoPublicacion(
      ActionEvent evento) {
    try {
      if (comboBoxSeleccionTiposPublicaciones
          .getSelectedIndex() == -1) {
        JOptionPane.showMessageDialog(this,
            "Debe seleccionar un tipo de publicacion",
            "Consultar paquete", JOptionPane.ERROR_MESSAGE);
      } else {
        String tipoPublicacion = comboBoxSeleccionTiposPublicaciones
            .getSelectedItem().toString();
        String nombrePaquete = comboBoxSeleccionPaquete
            .getSelectedItem().toString();
        DtPaquetePublicacion dtPaquetePublicaciones = this.controladorOfertaLaboral
            .obtenerDtPaquete(nombrePaquete);
        List<DtCantidadTipoPublicacion> dtCantidadPublicaciones = dtPaquetePublicaciones
            .getCantidadTipoPublicaciones();
        int cantidadRestante = 0;
        for (DtCantidadTipoPublicacion dtCantidadTipoPublicacion : dtCantidadPublicaciones) {
          if (dtCantidadTipoPublicacion
              .getNombreTipoPublicacion()
              .equals(tipoPublicacion)) {
            cantidadRestante = dtCantidadTipoPublicacion
                .getCantidad();
          }
        }
        
        if (tipoPublicacionSeleccionada != tipoPublicacion) {
          
          DtTipoPublicacion dtTipoPublicacion;
          dtTipoPublicacion = controladorOfertaLaboral
              .obtenerDtTipoPublicacion(tipoPublicacion);
          this.textFieldExposicion
              .setText(dtTipoPublicacion.getExposicion());
          this.textFieldCosto.setText(
              String.valueOf(dtTipoPublicacion.getCosto()));
          this.textFieldDuracion.setText(String
              .valueOf(dtTipoPublicacion.getDuracionDia())
              + " días");
          this.textFieldFechaAlta.setText(
              dtTipoPublicacion.getFechaAlta().toString());
          this.textPaneTipoPublicacion
              .setText(dtTipoPublicacion.getDescripcion());
          this.textFieldCantidadIncluida
              .setText(String.valueOf(cantidadRestante));
        }
      }
      
    } catch (TipoPublicacionNoExisteException evento1) {
      // no imprime nada
    } catch (PaquetePublicacionNoExisteException evento1) {
      // no imprime nada
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
  }
  
  /**
   * Metodo limpiar datos publicaciones .
   */
  
  public void limpiarDatosPublicaciones() {
    this.textFieldCosto.setText("");
    this.textFieldExposicion.setText("");
    this.textFieldDuracion.setText("");
    this.textFieldFechaAlta.setText("");
    this.textPaneTipoPublicacion.setText("");
    this.textFieldCantidadIncluida.setText("");
    this.textPane.setText("");
  }
  
  /**
   * Metodo limpiar todos los datos .
   */
  
  public void limpiarTodosLosDatos() {
    
    this.tipoPublicacionSeleccionada = "";
    this.paqueteSeleccionado = "";
    this.textFieldCosto.setText("");
    this.textFieldExposicion.setText("");
    this.textFieldDuracion.setText("");
    this.textFieldFechaAlta.setText("");
    this.textPaneTipoPublicacion.setText("");
    this.textFieldCantidadIncluida.setText("");
    this.textPanePaquete.setText("");
    this.textFieldFechaAltaPaquete.setText("");
    this.textPane.setText("");
    this.textFieldCostoPaquete.setText("");
    
  }
}
