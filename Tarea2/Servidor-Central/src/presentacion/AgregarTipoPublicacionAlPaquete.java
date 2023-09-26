package presentacion;

import javax.swing.JInternalFrame;



import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import logica.DataTypes.DTEmpresa;
import logica.DataTypes.DTOfertaLaboral;
import logica.DataTypes.DTPaquetePublicacion;
import logica.DataTypes.DTPostulacion;
import logica.DataTypes.DTPostulante;
import logica.DataTypes.DTTipoPublicacion;
import logica.DataTypes.DTUsuario;
import logica.classes.OfertaLaboral;
import logica.interfaces.IControladorOferta;
import logica.interfaces.IControladorUsuario;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.swing.JComboBox;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.SwingConstants;

import java.text.SimpleDateFormat;

import excepciones.OfertaLaboralNoExisteException;
import excepciones.PaquetePublicacionNoExisteException;
import excepciones.TipoPublicacionNoExisteException;
import excepciones.TipoPublicacionYaExisteException;
import excepciones.UsuarioNoExisteException;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;




@SuppressWarnings("serial")
public class AgregarTipoPublicacionAlPaquete extends JInternalFrame {

    // Controlador de usuarios que se utilizará para las acciones del JFrame
    private IControladorOferta controladorOfertaLaboral;
	private JTextField textFieldExposicion;
	private JTextField textFieldCosto;
	private JTextField textFieldDuracion;
	private JTextField textFieldFechaAlta;
	private JComboBox<String> comboBoxSeleccionPaquete;
	private JComboBox<String> comboBoxSeleccionTiposPublicaciones;
	private String tipoPublicacionSeleccionada;
	private String paqueteSeleccionado;
	private JButton btnConfirmar;
	private JButton btnCerrar;
	private JPanel panelDatos;
	private JScrollPane scrollPane;
	private JTextArea textAreaDescripcion;
	private JTextField textFieldCantidadIncluida;

    /**
     * Create the frame.
     */
    public AgregarTipoPublicacionAlPaquete(IControladorOferta icontOfeLab) {
        // Se inicializa con el controlador de oferta
    	controladorOfertaLaboral = icontOfeLab;
    	this.paqueteSeleccionado = "";
		this.tipoPublicacionSeleccionada = "";
        
        // Propiedades del JInternalFrame como dimensión, posición dentro del frame, etc.
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
			public void actionPerformed(ActionEvent e) {
				agregarTipoPublicacionAlPaquete();
				
			}
		});
		panelBotones.add(btnConfirmar);
		
		
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
		GridBagLayout gbl_panelDatos = new GridBagLayout();
		gbl_panelDatos.columnWidths = new int[] { 113, 739, 0 };
		gbl_panelDatos.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelDatos.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_panelDatos.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 1.0, 1.0 };
		panelDatos.setLayout(gbl_panelDatos);

		JLabel lblSeleccion = new JLabel("Seleccionar Paquete:");
		GridBagConstraints gbc_lblSeleccion = new GridBagConstraints();
		gbc_lblSeleccion.insets = new Insets(0, 0, 5, 5);
		gbc_lblSeleccion.anchor = GridBagConstraints.EAST;
		gbc_lblSeleccion.gridx = 0;
		gbc_lblSeleccion.gridy = 1;
		panelDatos.add(lblSeleccion, gbc_lblSeleccion);

		this.comboBoxSeleccionPaquete = new JComboBox<String>();
		this.comboBoxSeleccionPaquete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarTipoPublicacionNoIncluidasEnPaquete(e);
				
			}
		});

		GridBagConstraints gbc_comboBoxSeleccionUsuario = new GridBagConstraints();
		gbc_comboBoxSeleccionUsuario.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxSeleccionUsuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxSeleccionUsuario.gridx = 1;
		gbc_comboBoxSeleccionUsuario.gridy = 1;
		panelDatos.add(this.comboBoxSeleccionPaquete, gbc_comboBoxSeleccionUsuario);
		
		JLabel lblTiposPublicaciones = new JLabel("Tipos de publicaciones:");
		GridBagConstraints gbc_lblOfertas = new GridBagConstraints();
		gbc_lblOfertas.anchor = GridBagConstraints.EAST;
		gbc_lblOfertas.insets = new Insets(0, 0, 5, 5);
		gbc_lblOfertas.gridx = 0;
		gbc_lblOfertas.gridy = 12;
		panelDatos.add(lblTiposPublicaciones, gbc_lblOfertas);

		this.comboBoxSeleccionTiposPublicaciones = new JComboBox<String>();
		this.comboBoxSeleccionTiposPublicaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					cargarDatosTipoPublicacion(e);
			}
		});
		GridBagConstraints gbc_comboBoxSeleccionOferta = new GridBagConstraints();
		gbc_comboBoxSeleccionOferta.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxSeleccionOferta.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxSeleccionOferta.gridx = 1;
		gbc_comboBoxSeleccionOferta.gridy = 12;
		panelDatos.add(this.comboBoxSeleccionTiposPublicaciones, gbc_comboBoxSeleccionOferta);

		JLabel lblHorarioOferta = new JLabel("Exposicion");
		GridBagConstraints gbc_lblHorarioOferta = new GridBagConstraints();
		gbc_lblHorarioOferta.anchor = GridBagConstraints.EAST;
		gbc_lblHorarioOferta.insets = new Insets(0, 0, 5, 5);
		gbc_lblHorarioOferta.gridx = 0;
		gbc_lblHorarioOferta.gridy = 13;
		panelDatos.add(lblHorarioOferta, gbc_lblHorarioOferta);

		this.textFieldExposicion = new JTextField();
		this.textFieldExposicion.setEditable(false);
		GridBagConstraints gbc_textFieldNombreOferta = new GridBagConstraints();
		gbc_textFieldNombreOferta.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldNombreOferta.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNombreOferta.gridx = 1;
		gbc_textFieldNombreOferta.gridy = 13;
		panelDatos.add(this.textFieldExposicion, gbc_textFieldNombreOferta);
		this.textFieldExposicion.setColumns(10);

		JLabel lblRemuneracion = new JLabel("Costo");
		GridBagConstraints gbc_lblRemuneracion = new GridBagConstraints();
		gbc_lblRemuneracion.anchor = GridBagConstraints.EAST;
		gbc_lblRemuneracion.insets = new Insets(0, 0, 5, 5);
		gbc_lblRemuneracion.gridx = 0;
		gbc_lblRemuneracion.gridy = 14;
		panelDatos.add(lblRemuneracion, gbc_lblRemuneracion);

		this.textFieldCosto = new JTextField();
		this.textFieldCosto.setEditable(false);
		GridBagConstraints gbc_textFieldRemuneracion = new GridBagConstraints();
		gbc_textFieldRemuneracion.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldRemuneracion.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldRemuneracion.gridx = 1;
		gbc_textFieldRemuneracion.gridy = 14;
		panelDatos.add(this.textFieldCosto, gbc_textFieldRemuneracion);
		this.textFieldCosto.setColumns(10);

		JLabel lblCiudad = new JLabel("Duración");
		GridBagConstraints gbc_lblCiudad = new GridBagConstraints();
		gbc_lblCiudad.anchor = GridBagConstraints.EAST;
		gbc_lblCiudad.insets = new Insets(0, 0, 5, 5);
		gbc_lblCiudad.gridx = 0;
		gbc_lblCiudad.gridy = 15;
		panelDatos.add(lblCiudad, gbc_lblCiudad);

		this.textFieldDuracion = new JTextField();
		this.textFieldDuracion.setEditable(false);
		GridBagConstraints gbc_textFieldCiudad = new GridBagConstraints();
		gbc_textFieldCiudad.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldCiudad.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCiudad.gridx = 1;
		gbc_textFieldCiudad.gridy = 15;
		panelDatos.add(this.textFieldDuracion, gbc_textFieldCiudad);
		this.textFieldDuracion.setColumns(10);
		
		JLabel lblFechaAlta = new JLabel("Fecha de alta");
		GridBagConstraints gbc_lblFechaAlta = new GridBagConstraints();
		gbc_lblFechaAlta.anchor = GridBagConstraints.EAST;
		gbc_lblFechaAlta.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaAlta.gridx = 0;
		gbc_lblFechaAlta.gridy = 16;
		panelDatos.add(lblFechaAlta, gbc_lblFechaAlta);

		this.textFieldFechaAlta = new JTextField();
		this.textFieldFechaAlta.setEditable(false);
		GridBagConstraints gbc_textFieldFechaAlta = new GridBagConstraints();
		gbc_textFieldFechaAlta.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldFechaAlta.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldFechaAlta.gridx = 1;
		gbc_textFieldFechaAlta.gridy = 16;
		panelDatos.add(this.textFieldFechaAlta, gbc_textFieldFechaAlta);
		this.textFieldFechaAlta.setColumns(10);
		
		JLabel lblDescripcionEmpresa = new JLabel("Descripcion");
		GridBagConstraints gbc_lblDescripcionEmpresa = new GridBagConstraints();
		gbc_lblDescripcionEmpresa.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescripcionEmpresa.anchor = GridBagConstraints.EAST;
		gbc_lblDescripcionEmpresa.gridx = 0;
		gbc_lblDescripcionEmpresa.gridy = 17;
		panelDatos.add(lblDescripcionEmpresa, gbc_lblDescripcionEmpresa);

		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setEnabled(false);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 17;
		panelDatos.add(scrollPane, gbc_scrollPane);
		
	
		textAreaDescripcion = new JTextArea();
		textAreaDescripcion.setLineWrap(true);
		textAreaDescripcion.setWrapStyleWord(true);
		textAreaDescripcion.setEditable(false);
		scrollPane.setViewportView(textAreaDescripcion);
		
		JLabel lblCantidadInlcuida = new JLabel("Ingresa Cantidad");
		GridBagConstraints gbc_lblCantidadInlcuidaa = new GridBagConstraints();
		gbc_lblCantidadInlcuidaa.anchor = GridBagConstraints.EAST;
		gbc_lblCantidadInlcuidaa.insets = new Insets(0, 0, 5, 5);
		gbc_lblCantidadInlcuidaa.gridx = 0;
		gbc_lblCantidadInlcuidaa.gridy = 20;
		panelDatos.add(lblCantidadInlcuida, gbc_lblCantidadInlcuidaa);

		this.textFieldCantidadIncluida = new JTextField();
		GridBagConstraints gbc_textFieldCantidadInlcuida = new GridBagConstraints();
		gbc_textFieldCantidadInlcuida.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldCantidadInlcuida.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCantidadInlcuida.gridx = 1;
		gbc_textFieldCantidadInlcuida.gridy = 20;
		panelDatos.add(this.textFieldCantidadIncluida, gbc_textFieldCantidadInlcuida);
		this.textFieldCantidadIncluida.setColumns(10);


        
    }
    
    public void cargarPaquetes() {
		try {
			ArrayList<String> listaPaquetes = this.controladorOfertaLaboral.listarPaquetes();
			String [] arrayEmpresas;
			arrayEmpresas = listaPaquetes.toArray(new String [0]);
			Arrays.sort(arrayEmpresas);
			DefaultComboBoxModel<String> model;
			model = new DefaultComboBoxModel<String>(arrayEmpresas);
			this.comboBoxSeleccionPaquete.setModel(model);
		
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
    
    
    
    
    
    
    @SuppressWarnings({ "deprecation", "exports" })
	public void cargarTipoPublicacionNoIncluidasEnPaquete(ActionEvent e)  {
		
		
			try {
				String nombrePaquete = comboBoxSeleccionPaquete.getSelectedItem().toString();
				
				if (nombrePaquete != paqueteSeleccionado) {
					paqueteSeleccionado = nombrePaquete;
					limpiarDatosPublicaciones();
					//DTPaquetePublicacion dtPaquetePublicaciones = this.controladorOfertaLaboral.obtenerDtPaquetePublicacion(nombrePaquete);
					

				}
				
				ArrayList<String> listaTipoDePublicaciones = this.controladorOfertaLaboral.listarTipoDePublicaciones();
				ArrayList<String> listaTipoDePublicacionesDePaquete;
				listaTipoDePublicacionesDePaquete = this.controladorOfertaLaboral.listarTipoPublicacionDePaquete(nombrePaquete);
				ArrayList<String> listaTipoDePublicacionesNoIncluidas = new ArrayList<String>();
				for (String nombreTipoPublicacion : listaTipoDePublicaciones) {
					if (!listaTipoDePublicacionesDePaquete.contains(nombreTipoPublicacion)) {
						listaTipoDePublicacionesNoIncluidas.add(nombreTipoPublicacion);
					}
				}
				
				
				String [] arrayTiposPublicacionesNoIncluidas = listaTipoDePublicacionesNoIncluidas.toArray(new String[0]);
				Arrays.sort(arrayTiposPublicacionesNoIncluidas);
				DefaultComboBoxModel<String> model;
				model = new DefaultComboBoxModel<String>(arrayTiposPublicacionesNoIncluidas);
				this.comboBoxSeleccionTiposPublicaciones.setModel(model);
			} catch (PaquetePublicacionNoExisteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
    
    protected void cargarDatosTipoPublicacion(ActionEvent e)  {
		String tipoPublicacion = comboBoxSeleccionTiposPublicaciones.getSelectedItem().toString();
		if (tipoPublicacionSeleccionada != tipoPublicacion) {
			
			try {
				DTTipoPublicacion dtTipoPublicacion;
				dtTipoPublicacion = controladorOfertaLaboral.obtenerTipoPublicacion(tipoPublicacion).obtenerDTTipoPublicacion();
				this.textFieldExposicion.setText(dtTipoPublicacion.getExposicion());
				this.textFieldCosto.setText(String.valueOf(dtTipoPublicacion.getCosto()));
				this.textFieldDuracion.setText(String.valueOf(dtTipoPublicacion.getDuracionDia()) + " días");
				this.textFieldFechaAlta.setText(dtTipoPublicacion.getFechaAlta().toString());
				this.textAreaDescripcion.setText(dtTipoPublicacion.getDescripcion());
				
			} catch (TipoPublicacionNoExisteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (TipoPublicacionYaExisteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	}
    
   public void agregarTipoPublicacionAlPaquete() {
	   
	
	   try {
		   if (comboBoxSeleccionTiposPublicaciones.getSelectedIndex() == -1 || comboBoxSeleccionPaquete.getSelectedIndex() == -1 ) {
			   JOptionPane.showMessageDialog(this, "Debe seleccionar un paquete y un tipo de publicacion", "Agregar tipo de publicacion a paquete",
					   JOptionPane.ERROR_MESSAGE);
		   }
		   else {
			   String tipoPublicacion = comboBoxSeleccionTiposPublicaciones.getSelectedItem().toString();
			   String nombrePaquete = comboBoxSeleccionPaquete.getSelectedItem().toString();
			   int cantidadIncluida = Integer.parseInt(this.textFieldCantidadIncluida.getText());
			   if (cantidadIncluida <= 0) {
				   JOptionPane.showMessageDialog(this, "La cantidad incluida debe ser un número mayor a cero", "Agregar tipo de publicacion a paquete",
						   JOptionPane.ERROR_MESSAGE);
			   }
			   else {
				   this.controladorOfertaLaboral.agregarTipoPublicacionAlPaquete(cantidadIncluida, tipoPublicacion, nombrePaquete);
					JOptionPane.showMessageDialog(this, "El tipo de publicación se agregó con con éxito", "Agregar tipo de publicacion a paquete",
							JOptionPane.INFORMATION_MESSAGE);
					 limpiarTodosLosDatos();
			   }
			
		   }
		   
		
	} catch (TipoPublicacionNoExisteException | PaquetePublicacionNoExisteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (NumberFormatException e) {
		JOptionPane.showMessageDialog(this, "La cantidad incluida debe ser un número mayor a cero", "Agregar tipo de publicacion a paquete",
				JOptionPane.ERROR_MESSAGE);
		
	}
    }
    
    public void limpiarDatosPublicaciones() {
    	this.textFieldCosto.setText("");
		this.textFieldExposicion.setText("");
		this.textFieldDuracion.setText("");
		this.textFieldFechaAlta.setText("");
		this.textAreaDescripcion.setText("");
		this.textFieldCantidadIncluida.setText("");
	}
    
public void limpiarTodosLosDatos() {
		
		this.tipoPublicacionSeleccionada = "";
		this.paqueteSeleccionado ="";
		this.textFieldCosto.setText("");
		this.textFieldExposicion.setText("");
		this.textFieldDuracion.setText("");
		this.textFieldFechaAlta.setText("");
		this.textAreaDescripcion.setText("");
		this.textFieldCantidadIncluida.setText("");

		/*ArrayList<String> listaOfertas = new ArrayList<String>();
		String [] arrayOfertas = listaOfertas.toArray(new String[0]);
		Arrays.sort(arrayOfertas);
		DefaultComboBoxModel<String> model;
		model = new DefaultComboBoxModel<String>(arrayOfertas);
		this.comboBoxSeleccionOferta.setModel(model);
		
		this.comboBoxSeleccionUsuario.setModel(model);*/
		
	}
}
