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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import logica.DataTypes.DTCantidadTipoPublicacion;
import logica.DataTypes.DTPaquetePublicacion;
import logica.DataTypes.DTTipoPublicacion;
import logica.interfaces.IControladorOferta;

@SuppressWarnings("serial")
public class ConsultaPaquete extends JInternalFrame {

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
	private JTextField textFieldValidez;
	private JTextField textFieldDescuento;
	private JTextField textFieldFechaAltaPaquete;
	private JTextPane textPanePaquete;
	private JTextPane textPaneTipoPublicacion;

	/**
	 * Create the frame.
	 */
	public ConsultaPaquete(IControladorOferta icontOfeLab) {
		// Se inicializa con el controlador de oferta
		controladorOfertaLaboral = icontOfeLab;
		this.paqueteSeleccionado = "";
		this.tipoPublicacionSeleccionada = "";

		// Propiedades del JInternalFrame como dimensión, posición dentro del frame,
		// etc.
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setTitle("Agregar tipo de publicación al paquete");
		setBounds(30, 30, 613, 580);

		JPanel panelBotones = new JPanel();
		getContentPane().add(panelBotones, BorderLayout.SOUTH);
		panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 120, 20));

		this.btnCerrar = new JButton("Cerrar");
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
		gbl_panelDatos.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 1.0, 1.0 };
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
				cargarTipoPublicacionEnPaquete(e);

			}
		});

		GridBagConstraints gbc_comboBoxSeleccionUsuario = new GridBagConstraints();
		gbc_comboBoxSeleccionUsuario.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxSeleccionUsuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxSeleccionUsuario.gridx = 1;
		gbc_comboBoxSeleccionUsuario.gridy = 1;
		panelDatos.add(this.comboBoxSeleccionPaquete, gbc_comboBoxSeleccionUsuario);

		JLabel lblDescuento = new JLabel("Descuento");
		GridBagConstraints gbc_lblDescuento = new GridBagConstraints();
		gbc_lblDescuento.anchor = GridBagConstraints.EAST;
		gbc_lblDescuento.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescuento.gridx = 0;
		gbc_lblDescuento.gridy = 2;
		panelDatos.add(lblDescuento, gbc_lblDescuento);

		this.textFieldDescuento = new JTextField();
		this.textFieldDescuento.setEditable(false);
		GridBagConstraints gbc_textFieldDescuento = new GridBagConstraints();
		gbc_textFieldDescuento.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldDescuento.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDescuento.gridx = 1;
		gbc_textFieldDescuento.gridy = 2;
		panelDatos.add(textFieldDescuento, gbc_textFieldDescuento);
		textFieldDescuento.setColumns(10);

		JLabel lblValidez = new JLabel("Período de validez");
		GridBagConstraints gbc_lblValidez = new GridBagConstraints();
		gbc_lblValidez.anchor = GridBagConstraints.EAST;
		gbc_lblValidez.insets = new Insets(0, 0, 5, 5);
		gbc_lblValidez.gridx = 0;
		gbc_lblValidez.gridy = 3;
		panelDatos.add(lblValidez, gbc_lblValidez);

		this.textFieldValidez = new JTextField();
		this.textFieldValidez.setEditable(false);
		GridBagConstraints gbc_textFieldValidez = new GridBagConstraints();
		gbc_textFieldValidez.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldValidez.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldValidez.gridx = 1;
		gbc_textFieldValidez.gridy = 3;
		panelDatos.add(textFieldValidez, gbc_textFieldValidez);
		textFieldValidez.setColumns(10);

		JLabel lblDescripcionPaquete = new JLabel("Descripcion");
		GridBagConstraints gbc_lblDescripcionPaquete = new GridBagConstraints();
		gbc_lblDescripcionPaquete.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescripcionPaquete.anchor = GridBagConstraints.EAST;
		gbc_lblDescripcionPaquete.gridx = 0;
		gbc_lblDescripcionPaquete.gridy = 4;
		panelDatos.add(lblDescripcionPaquete, gbc_lblDescripcionPaquete);

		textPanePaquete = new JTextPane();
		GridBagConstraints gbc_textPane = new GridBagConstraints();
		gbc_textPane.insets = new Insets(0, 0, 5, 0);
		gbc_textPane.fill = GridBagConstraints.BOTH;
		gbc_textPane.gridx = 1;
		gbc_textPane.gridy = 4;
		panelDatos.add(textPanePaquete, gbc_textPane);

		JLabel lblFechaAltaPaquete = new JLabel("Fecha de alta");
		GridBagConstraints gbc_lblFechaAltaPaquete = new GridBagConstraints();
		gbc_lblFechaAltaPaquete.anchor = GridBagConstraints.EAST;
		gbc_lblFechaAltaPaquete.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaAltaPaquete.gridx = 0;
		gbc_lblFechaAltaPaquete.gridy = 7;
		panelDatos.add(lblFechaAltaPaquete, gbc_lblFechaAltaPaquete);

		this.textFieldFechaAltaPaquete = new JTextField();
		this.textFieldFechaAltaPaquete.setEditable(false);
		GridBagConstraints gbc_textFieldFechaAltaPaquete = new GridBagConstraints();
		gbc_textFieldFechaAltaPaquete.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldFechaAltaPaquete.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldFechaAltaPaquete.gridx = 1;
		gbc_textFieldFechaAltaPaquete.gridy = 7;
		panelDatos.add(this.textFieldFechaAltaPaquete, gbc_textFieldFechaAltaPaquete);
		this.textFieldFechaAltaPaquete.setColumns(10);

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

		/*scrollPane = new JScrollPane();
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
		scrollPane.setViewportView(textAreaDescripcion); */
		textPaneTipoPublicacion = new JTextPane();
		GridBagConstraints gbc_textPaneTipoPublicacion = new GridBagConstraints();
		gbc_textPaneTipoPublicacion.insets = new Insets(0, 0, 5, 0);
		gbc_textPaneTipoPublicacion.fill = GridBagConstraints.BOTH;
		gbc_textPaneTipoPublicacion.gridx = 1;
		gbc_textPaneTipoPublicacion.gridy = 17;
		panelDatos.add(textPaneTipoPublicacion, gbc_textPaneTipoPublicacion);

		JLabel lblCantidadInlcuida = new JLabel("Cantidad Restante");
		GridBagConstraints gbc_lblCantidadInlcuidaa = new GridBagConstraints();
		gbc_lblCantidadInlcuidaa.anchor = GridBagConstraints.EAST;
		gbc_lblCantidadInlcuidaa.insets = new Insets(0, 0, 0, 5);
		gbc_lblCantidadInlcuidaa.gridx = 0;
		gbc_lblCantidadInlcuidaa.gridy = 20;
		panelDatos.add(lblCantidadInlcuida, gbc_lblCantidadInlcuidaa);

		this.textFieldCantidadIncluida = new JTextField();
		GridBagConstraints gbc_textFieldCantidadInlcuida = new GridBagConstraints();
		gbc_textFieldCantidadInlcuida.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCantidadInlcuida.gridx = 1;
		gbc_textFieldCantidadInlcuida.gridy = 20;
		panelDatos.add(this.textFieldCantidadIncluida, gbc_textFieldCantidadInlcuida);
		this.textFieldCantidadIncluida.setColumns(10);
		this.textFieldCantidadIncluida.setEditable(false);

	}

	public void cargarPaquetes() {
		try {
			ArrayList<String> listaPaquetes = this.controladorOfertaLaboral.listarPaquetes();
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

	@SuppressWarnings({ "deprecation", "exports" })
	public void cargarTipoPublicacionEnPaquete(ActionEvent e) {

		try {
			String nombrePaquete = comboBoxSeleccionPaquete.getSelectedItem().toString();

			if (nombrePaquete != paqueteSeleccionado) {
				paqueteSeleccionado = nombrePaquete;
				limpiarDatosPublicaciones();
				DTPaquetePublicacion dtPaquetePublicaciones = this.controladorOfertaLaboral
						.obtenerDTPaquete(nombrePaquete);
				this.textFieldDescuento.setText(String.valueOf(dtPaquetePublicaciones.getDescuento()) + "%");
				this.textFieldValidez.setText(String.valueOf(dtPaquetePublicaciones.getPeriodoValidez()));
				// this.textFieldFechaAltaPaquete.setText(dtPaquetePublicaciones.getFecha);
				this.textPanePaquete.setText(dtPaquetePublicaciones.getDescripcion());

			}

			ArrayList<String> listaTipoDePublicacionesDePaquete = this.controladorOfertaLaboral
					.listarTipoPublicacionDePaquete(nombrePaquete);

			String[] arrayTiposPublicacionesPaquete = listaTipoDePublicacionesDePaquete.toArray(new String[0]);
			Arrays.sort(arrayTiposPublicacionesPaquete);
			DefaultComboBoxModel<String> model;
			model = new DefaultComboBoxModel<String>(arrayTiposPublicacionesPaquete);
			this.comboBoxSeleccionTiposPublicaciones.setModel(model);
		} catch (PaquetePublicacionNoExisteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	protected void cargarDatosTipoPublicacion(ActionEvent e) {
		try {
			String tipoPublicacion = comboBoxSeleccionTiposPublicaciones.getSelectedItem().toString();
			String nombrePaquete = comboBoxSeleccionPaquete.getSelectedItem().toString();
			DTPaquetePublicacion dtPaquetePublicaciones = this.controladorOfertaLaboral.obtenerDTPaquete(nombrePaquete);
			ArrayList<DTCantidadTipoPublicacion> dtCantidadPublicaciones = dtPaquetePublicaciones.getCantidadPublicacionesColeccion();
			int cantidadRestante = 0;
			for (DTCantidadTipoPublicacion dtCantidadTipoPublicacion : dtCantidadPublicaciones) {
				if (dtCantidadTipoPublicacion.getNombreTipoPublicacion().equals(tipoPublicacion)) {
					cantidadRestante = dtCantidadTipoPublicacion.getCantidad();
				}
			}

			if (tipoPublicacionSeleccionada != tipoPublicacion) {

				DTTipoPublicacion dtTipoPublicacion;
				dtTipoPublicacion = controladorOfertaLaboral.obtenerDTTipoPublicacion(tipoPublicacion);
				this.textFieldExposicion.setText(dtTipoPublicacion.getExposicion());
				this.textFieldCosto.setText(String.valueOf(dtTipoPublicacion.getCosto()));
				this.textFieldDuracion.setText(String.valueOf(dtTipoPublicacion.getDuracionDia()) + " días");
				this.textFieldFechaAlta.setText(dtTipoPublicacion.getFechaAlta().toString());
				this.textPaneTipoPublicacion.setText(dtTipoPublicacion.getDescripcion());
				this.textFieldCantidadIncluida.setText(String.valueOf(cantidadRestante));
			}
		} catch (TipoPublicacionNoExisteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (PaquetePublicacionNoExisteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public void limpiarDatosPublicaciones() {
		this.textFieldCosto.setText("");
		this.textFieldExposicion.setText("");
		this.textFieldDuracion.setText("");
		this.textFieldFechaAlta.setText("");
		this.textPaneTipoPublicacion.setText("");
		this.textFieldCantidadIncluida.setText("");
	}

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
		/*
		 * this.textFieldDescuento.setText(""); this.textFieldValidez.setText("");
		 * this.textAreaDescripcionPaquete.setText("");
		 */

		/*
		 * ArrayList<String> listaOfertas = new ArrayList<String>(); String []
		 * arrayOfertas = listaOfertas.toArray(new String[0]);
		 * Arrays.sort(arrayOfertas); DefaultComboBoxModel<String> model; model = new
		 * DefaultComboBoxModel<String>(arrayOfertas);
		 * this.comboBoxSeleccionOferta.setModel(model);
		 * 
		 * this.comboBoxSeleccionUsuario.setModel(model);
		 */

	}
}
