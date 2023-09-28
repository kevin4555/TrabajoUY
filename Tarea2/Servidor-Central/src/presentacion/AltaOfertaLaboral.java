package presentacion;

import javax.swing.JInternalFrame;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import logica.DataTypes.DTPaquetePublicacion;
import logica.interfaces.IControladorOferta;
import logica.interfaces.IControladorUsuario;

import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Image;
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
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;

import excepciones.KeywordNoExisteException;
import excepciones.OfertaLaboralNoExisteException;
import excepciones.OfertaLaboralYaExisteException;
import excepciones.PaquetePublicacionNoExisteException;
import excepciones.TipoPublicacionNoExisteException;
import excepciones.TipoPublicacionYaExisteException;
import excepciones.UsuarioNoExisteException;
import javax.swing.JRadioButton;

@SuppressWarnings("serial")
public class AltaOfertaLaboral extends JInternalFrame {

	// Controlador de usuarios que se utilizará para las acciones del JFrame
	private IControladorOferta controladorOfertaLaboral;
	private IControladorUsuario controladorUsuario;

	// Los componentes gráficos se agregan como atributos de la clase
	// para facilitar su acceso desde diferentes métodos de la misma.
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
	private JList<String> listaKeyword = new JList<>();;
	private JButton btnSeleccionarKeyword;
	private JButton btnConfirmar;
	private JButton btnCancelar;
	private JDateChooser dateChooser;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextPane textPane;
	private JButton selectImageButton;
	private JLabel imageLabel;
	private JScrollPane scrollPane;
	private JLabel lblNewLabel;
	private JList list;
	private JLabel lblNewLabel_1;
	private JComboBox comboBoxFormaDePago;
	private JComboBox<String> comboBoxSeleccionTiposPublicaciones;
	private JLabel lblTiposPublicaciones;
	private JLabel lblSeleccionPaquete;
	private JComboBox<String> comboBoxSeleccionPaquete;
	private String paqueteSeleccionado;
	private BufferedImage fotoOferta = null;
	private GridBagConstraints gbc_textField;

	/**
	 * Create the frame.
	 */
	public AltaOfertaLaboral(IControladorOferta icontOfer, IControladorUsuario icontUsu) {

		controladorOfertaLaboral = icontOfer;
		controladorUsuario = icontUsu;
		this.paqueteSeleccionado = "";

		// Propiedades del JInternalFrame como dimensión, posición dentro del frame,
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
			public void actionPerformed(ActionEvent e) {
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
		GridBagLayout gbl_panelDatos = new GridBagLayout();
		gbl_panelDatos.columnWidths = new int[] { 113, 739, 0 };
		gbl_panelDatos.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelDatos.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_panelDatos.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 1.0, 1.0 };
		panelDatos.setLayout(gbl_panelDatos);

		JLabel lblSeleccion = new JLabel("Seleccionar Empresa:");
		GridBagConstraints gbc_lblSeleccion = new GridBagConstraints();
		gbc_lblSeleccion.insets = new Insets(0, 0, 5, 5);
		gbc_lblSeleccion.anchor = GridBagConstraints.EAST;
		gbc_lblSeleccion.gridx = 0;
		gbc_lblSeleccion.gridy = 1;
		panelDatos.add(lblSeleccion, gbc_lblSeleccion);

		this.comboBoxEmpresa = new JComboBox<String>();
		GridBagConstraints gbc_comboBoxSeleccionUsuario = new GridBagConstraints();
		gbc_comboBoxSeleccionUsuario.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxSeleccionUsuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxSeleccionUsuario.gridx = 1;
		gbc_comboBoxSeleccionUsuario.gridy = 1;
		panelDatos.add(this.comboBoxEmpresa, gbc_comboBoxSeleccionUsuario);

		JLabel lblNombre = new JLabel("Nombre");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.EAST;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 0;
		gbc_lblNombre.gridy = 2;
		panelDatos.add(lblNombre, gbc_lblNombre);

		this.textFieldNombre = new JTextField();
		GridBagConstraints gbc_textFieldNombreOferta = new GridBagConstraints();
		gbc_textFieldNombreOferta.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldNombreOferta.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNombreOferta.gridx = 1;
		gbc_textFieldNombreOferta.gridy = 2;
		panelDatos.add(this.textFieldNombre, gbc_textFieldNombreOferta);
		this.textFieldNombre.setColumns(10);
		
		JLabel lblRemuneracion = new JLabel("Remuneracion");
		GridBagConstraints gbc_lblRemuneracion = new GridBagConstraints();
		gbc_lblRemuneracion.anchor = GridBagConstraints.EAST;
		gbc_lblRemuneracion.insets = new Insets(0, 0, 5, 5);
		gbc_lblRemuneracion.gridx = 0;
		gbc_lblRemuneracion.gridy = 3;
		panelDatos.add(lblRemuneracion, gbc_lblRemuneracion);

		this.textFieldRemuneracion = new JTextField();
		GridBagConstraints gbc_textFieldRemuneracion = new GridBagConstraints();
		gbc_textFieldRemuneracion.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldRemuneracion.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldRemuneracion.gridx = 1;
		gbc_textFieldRemuneracion.gridy = 3;
		panelDatos.add(this.textFieldRemuneracion, gbc_textFieldRemuneracion);
		this.textFieldRemuneracion.setColumns(10);

		JLabel lblCiudad = new JLabel("Ciudad");
		GridBagConstraints gbc_lblCiudad = new GridBagConstraints();
		gbc_lblCiudad.anchor = GridBagConstraints.EAST;
		gbc_lblCiudad.insets = new Insets(0, 0, 5, 5);
		gbc_lblCiudad.gridx = 0;
		gbc_lblCiudad.gridy = 4;
		panelDatos.add(lblCiudad, gbc_lblCiudad);

		this.textFieldCiudad = new JTextField();
		GridBagConstraints gbc_textFieldCiudad = new GridBagConstraints();
		gbc_textFieldCiudad.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldCiudad.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCiudad.gridx = 1;
		gbc_textFieldCiudad.gridy = 4;
		panelDatos.add(this.textFieldCiudad, gbc_textFieldCiudad);
		this.textFieldCiudad.setColumns(10);

		JLabel lblDepartamento = new JLabel("Departamento");
		GridBagConstraints gbc_lblDepartamento = new GridBagConstraints();
		gbc_lblDepartamento.anchor = GridBagConstraints.EAST;
		gbc_lblDepartamento.insets = new Insets(0, 0, 5, 5);
		gbc_lblDepartamento.gridx = 0;
		gbc_lblDepartamento.gridy = 5;
		panelDatos.add(lblDepartamento, gbc_lblDepartamento);

		this.textFieldDepartamento = new JTextField();
		GridBagConstraints gbc_textFieldDepartamento = new GridBagConstraints();
		gbc_textFieldDepartamento.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldDepartamento.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDepartamento.gridx = 1;
		gbc_textFieldDepartamento.gridy = 5;
		panelDatos.add(this.textFieldDepartamento, gbc_textFieldDepartamento);
		this.textFieldDepartamento.setColumns(10);
		
		JLabel lblHorarioInicio = new JLabel("Horario Inicio");
		GridBagConstraints gbc_lblHorarioInicio = new GridBagConstraints();
		gbc_lblHorarioInicio.anchor = GridBagConstraints.EAST;
		gbc_lblHorarioInicio.insets = new Insets(0, 0, 5, 5);
		gbc_lblHorarioInicio.gridx = 0;
		gbc_lblHorarioInicio.gridy = 6;
		panelDatos.add(lblHorarioInicio, gbc_lblHorarioInicio);

		this.textFieldHoraInicio = new JTextField();
		GridBagConstraints gbc_textFieldHorarioInicio = new GridBagConstraints();
		gbc_textFieldHorarioInicio.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldHorarioInicio.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldHorarioInicio.gridx = 1;
		gbc_textFieldHorarioInicio.gridy = 6;
		panelDatos.add(this.textFieldHoraInicio, gbc_textFieldHorarioInicio);
		this.textFieldHoraInicio.setColumns(10);
		
		JLabel lblHorarioFin = new JLabel("Horario Fin");
		GridBagConstraints gbc_lblHorarioFin = new GridBagConstraints();
		gbc_lblHorarioFin.anchor = GridBagConstraints.EAST;
		gbc_lblHorarioFin.insets = new Insets(0, 0, 5, 5);
		gbc_lblHorarioFin.gridx = 0;
		gbc_lblHorarioFin.gridy = 7;
		panelDatos.add(lblHorarioFin, gbc_lblHorarioFin);

		this.textFieldHoraFin = new JTextField();
		GridBagConstraints gbc_textFieldHorarioFin = new GridBagConstraints();
		gbc_textFieldHorarioFin.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldHorarioFin.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldHorarioFin.gridx = 1;
		gbc_textFieldHorarioFin.gridy = 7;
		panelDatos.add(this.textFieldHoraFin, gbc_textFieldHorarioFin);
		this.textFieldHoraFin.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("Descripcion:");
		GridBagConstraints gbc_lblDescripcion = new GridBagConstraints();
		gbc_lblDescripcion.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescripcion.anchor = GridBagConstraints.EAST;
		gbc_lblDescripcion.gridx = 0;
		gbc_lblDescripcion.gridy = 8;
		panelDatos.add(lblDescripcion, gbc_lblDescripcion);

		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setEnabled(false);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 8;
		panelDatos.add(scrollPane, gbc_scrollPane);

		textFieldDescripcion = new JTextArea();
		textFieldDescripcion.setLineWrap(true);
		textFieldDescripcion.setWrapStyleWord(true);
		textFieldDescripcion.setEditable(true);
		scrollPane.setViewportView(textFieldDescripcion);
		
		JLabel lblFechaAlta = new JLabel("Fecha de Alta");
		GridBagConstraints gbc_lblFechaAlta = new GridBagConstraints();
		gbc_lblFechaAlta.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaAlta.anchor = GridBagConstraints.EAST;
		gbc_lblFechaAlta.gridx = 0;
		gbc_lblFechaAlta.gridy = 10;
		panelDatos.add(lblFechaAlta, gbc_lblFechaAlta);

		this.dateChooser = new JDateChooser();
		GridBagConstraints gbc_FechaAlta = new GridBagConstraints();
		gbc_FechaAlta.insets = new Insets(0, 0, 5, 0);
		gbc_FechaAlta.fill = GridBagConstraints.HORIZONTAL;
		gbc_FechaAlta.gridx = 1;
		gbc_FechaAlta.gridy = 10;
		panelDatos.add(dateChooser, gbc_FechaAlta);
		
		lblNewLabel = new JLabel("Keywords");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 11;
		panelDatos.add(lblNewLabel, gbc_lblNewLabel);
		
		
		listaKeyword = new JList<>();
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.insets = new Insets(0, 0, 5, 0);
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 1;
		gbc_list.gridy = 11;
		
		
		JScrollPane scrollPaneList = new JScrollPane(); 
		scrollPaneList.setViewportView(listaKeyword);
		listaKeyword.setVisibleRowCount(4);
		panelDatos.add(scrollPaneList, gbc_list);
		
		lblNewLabel_1 = new JLabel("Forma de pago");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 15;
		panelDatos.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		comboBoxFormaDePago = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 15;
		panelDatos.add(comboBoxFormaDePago, gbc_comboBox);
		
		this.comboBoxFormaDePago.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarDatosTipoPublicacion(e);

			}
		});
		
		lblTiposPublicaciones = new JLabel("Tipos de publicaciones:");
		GridBagConstraints gbc_lblOfertas = new GridBagConstraints();
		gbc_lblOfertas.anchor = GridBagConstraints.EAST;
		gbc_lblOfertas.insets = new Insets(0, 0, 5, 5);
		gbc_lblOfertas.gridx = 0;
		gbc_lblOfertas.gridy = 17;
		panelDatos.add(lblTiposPublicaciones, gbc_lblOfertas);
		lblTiposPublicaciones.setVisible(false);

		this.comboBoxSeleccionTiposPublicaciones = new JComboBox<String>();
		GridBagConstraints gbc_comboBoxSeleccionOferta = new GridBagConstraints();
		gbc_comboBoxSeleccionOferta.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxSeleccionOferta.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxSeleccionOferta.gridx = 1;
		gbc_comboBoxSeleccionOferta.gridy = 17;
		panelDatos.add(this.comboBoxSeleccionTiposPublicaciones, gbc_comboBoxSeleccionOferta);
		comboBoxSeleccionTiposPublicaciones.setVisible(false);
		
		lblSeleccionPaquete = new JLabel("Seleccionar Paquete:");
		GridBagConstraints gbc_lblSeleccionPAquete = new GridBagConstraints();
		gbc_lblSeleccionPAquete.insets = new Insets(0, 0, 5, 5);
		gbc_lblSeleccionPAquete.anchor = GridBagConstraints.EAST;
		gbc_lblSeleccionPAquete.gridx = 0;
		gbc_lblSeleccionPAquete.gridy = 16;
		panelDatos.add(lblSeleccionPaquete, gbc_lblSeleccionPAquete);
		lblSeleccionPaquete.setVisible(false);

		this.comboBoxSeleccionPaquete = new JComboBox<String>();
		this.comboBoxSeleccionPaquete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarTipoPublicacionEnPaquete(e);

			}
		});

		GridBagConstraints gbc_comboBoxSeleccionPaquete = new GridBagConstraints();
		gbc_comboBoxSeleccionPaquete.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxSeleccionPaquete.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxSeleccionPaquete.gridx = 1;
		gbc_comboBoxSeleccionPaquete.gridy = 16;
		panelDatos.add(this.comboBoxSeleccionPaquete, gbc_comboBoxSeleccionPaquete);
		this.comboBoxSeleccionPaquete.setVisible(false);
		
		JLabel imagenOferta = new JLabel("Imagen Oferta:");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 13;
		panelDatos.add(imagenOferta, gbc_lblNewLabel_2);

		selectImageButton = new JButton("Seleccionar Imagen");
		JLabel imageLabel = new JLabel();

		gbc_textField = new GridBagConstraints();
		gbc_textField.anchor = GridBagConstraints.WEST;
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 13;
		panelDatos.add(selectImageButton, gbc_textField);

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
		GridBagConstraints gbc_textPane = new GridBagConstraints();
		gbc_textPane.insets = new Insets(0, 0, 5, 0);
		gbc_textPane.fill = GridBagConstraints.BOTH;
		gbc_textPane.gridx = 1;
		gbc_textPane.gridy = 14;
		panelDatos.add(textPane, gbc_textPane);
		textPane.setEditable(false);
		
		

	}

	@SuppressWarnings("unchecked")
	public void cargarEmpresas() {

		ArrayList<String> listaEmpresas = this.controladorUsuario.listarEmpresas();
		String[] arrayEmpresas;
		arrayEmpresas = listaEmpresas.toArray(new String[0]);
		Arrays.sort(arrayEmpresas);
		DefaultComboBoxModel<String> model;
		model = new DefaultComboBoxModel<String>(arrayEmpresas);
		this.comboBoxEmpresa.setModel(model);

	}

	

	public void cargarKeywords() {
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		listaKeyword.setModel(listModel);

		// Recorrer el contenido del ArrayList
		for (int i = 0; i < controladorOfertaLaboral.listarKeywords().size(); i++) {
			// Añadir cada elemento del ArrayList en el modelo de la lista
			listModel.addElement(controladorOfertaLaboral.listarKeywords().get(i));
		}
	}
	
	public void cargarComboBoxFormaDePago() {
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		model.addElement("Seleccione:");
		model.addElement("General");
		model.addElement("Por paquete");
		comboBoxFormaDePago.setModel(model);
	}
	
	public void cargarDatosTipoPublicacion(ActionEvent e)  {
		
		
		try {
			DefaultComboBoxModel<String> modelPublicaciones;
			DefaultComboBoxModel<String> modelPaquetes;
			if (this.comboBoxFormaDePago.getSelectedItem().equals("General")) {
				lblTiposPublicaciones.setVisible(true);
				comboBoxSeleccionTiposPublicaciones.setVisible(true);
				this.comboBoxSeleccionPaquete.setVisible(false);
				lblSeleccionPaquete.setVisible(false);
				ArrayList<String> listaTipoDePublicaciones = this.controladorOfertaLaboral.listarTipoDePublicaciones();
				String [] arrayTiposPublicaciones = listaTipoDePublicaciones.toArray(new String[0]);
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
					
					
					ArrayList<DTPaquetePublicacion> dtPaquetesComprados = this.controladorUsuario.obtenerDTPaquetesDeEmpresa(nicknameEmpresa);
					ArrayList<String> nombrePaquetesComprados = new ArrayList<String>();
					for (DTPaquetePublicacion dtPaquete : dtPaquetesComprados) {
						nombrePaquetesComprados.add(dtPaquete.getNombre());
					}
					String [] arrayPaquetes = nombrePaquetesComprados.toArray(new String[0]);
					if (arrayPaquetes.length == 0) {
						this.comboBoxSeleccionTiposPublicaciones.removeAllItems();
						lblTiposPublicaciones.setVisible(false);
						comboBoxSeleccionTiposPublicaciones.setVisible(false);
						this.comboBoxSeleccionPaquete.setVisible(false);
						lblSeleccionPaquete.setVisible(false);
						JOptionPane.showMessageDialog(this, "La empresa no tiene ningún paquete comprado", "Registrar Oferta Laboral",
								JOptionPane.ERROR_MESSAGE);
					}
					Arrays.sort(arrayPaquetes);
					modelPaquetes = new DefaultComboBoxModel<String>(arrayPaquetes);
					this.comboBoxSeleccionPaquete.setModel(modelPaquetes);
				} else {
					JOptionPane.showMessageDialog(this, "Debe seleccionar una empresa", "Registrar Oferta Laboral",
							JOptionPane.ERROR_MESSAGE);
				}
				
			}			
		} catch (UsuarioNoExisteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
	}

	public void obtenerImagen(File imagenPerfil) {
		try {
			BufferedImage originalImage = ImageIO.read(imagenPerfil);
			int newWidth = 100; // Ancho deseado
			int newHeight = 100; // Alto deseado
			Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
			fotoOferta = originalImage;
			this.textPane.setCaretPosition(textPane.getStyledDocument().getLength());
			this.textPane.setText("");
			ImageIcon icono = new ImageIcon(scaledImage);
			this.textPane.insertIcon(icono);

		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "No se cargo la imagen", "Registrar Usuario",
					JOptionPane.ERROR_MESSAGE);
		} catch (java.lang.NullPointerException e2) {
			JOptionPane.showMessageDialog(this, "Debe ingresar una imagen valida", "Registrar Usuario",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void cargarTipoPublicacionEnPaquete(ActionEvent e) {

		try {
			String nombrePaquete = comboBoxSeleccionPaquete.getSelectedItem().toString();
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

	protected void cmdRegistroOfertaLaboralActionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

		// Obtengo datos de los controles Swing
		String nombreOfertaLab = this.textFieldNombre.getText();
		String descripOfertaLab = this.textFieldDescripcion.getText();
		String remuneracionOfertaLab = this.textFieldRemuneracion.getText();
		String ciudadOfertaLab = this.textFieldCiudad.getText();
		String departOfertaLab = this.textFieldDepartamento.getText();
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
		ArrayList<String> keywordSeleccionadas = new ArrayList<String>();
		if (!this.listaKeyword.getSelectedValuesList().isEmpty()) {
			keywordSeleccionadas = (ArrayList<String>) this.listaKeyword.getSelectedValuesList();
		}

		if (checkFormulario(nombreOfertaLab, descripOfertaLab, remuneracionOfertaLab, ciudadOfertaLab, departOfertaLab,
				horaIniOfertaLab, horaFinOfertaLab, fechaAlta, nicknameEmpresa
				)) {
			try {
				if (this.textPane.getText() == "") {
					fotoOferta = null;
				}
				LocalDate fechaAltaOferta = this.dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault())
						.toLocalDate();
				controladorOfertaLaboral.altaOfertaLaboral(nombreOfertaLab, descripOfertaLab, horaIniOfertaLab, horaFinOfertaLab,
						Float.parseFloat(remuneracionOfertaLab), ciudadOfertaLab, departOfertaLab, fechaAltaOferta,
						this.comboBoxSeleccionTiposPublicaciones.getSelectedItem().toString(), nicknameEmpresa, keywordSeleccionadas, fotoOferta, nomPaquete);
				// Muestro éxito de la operación
				JOptionPane.showMessageDialog(this, "La Oferta Laboral se ha creado con éxito",
						"Registrar Oferta Laboral", JOptionPane.INFORMATION_MESSAGE);

				// Limpio el internal frame antes de cerrar la ventana
				limpiarFormulario();
				// setVisible(false);
			}

			catch (TipoPublicacionNoExisteException e) {
				// No imprime nada
			} catch (UsuarioNoExisteException e) {
				// no imprime nada
			} catch (OfertaLaboralYaExisteException e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Trabajo.uy", JOptionPane.ERROR_MESSAGE);
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Trabajo.uy", JOptionPane.ERROR_MESSAGE);
			} catch (KeywordNoExisteException e) {
				// no imprime nada
			} catch (java.lang.ClassCastException e) {
				JOptionPane.showMessageDialog(this, "Debe ingresar una fecha valida", "Trabajo.uy",
						JOptionPane.ERROR_MESSAGE);

			}
		}

	}

	// Permite validar la información introducida en los campos e indicar
	// a través de un mensaje de error (JOptionPane) cuando algo sucede.
	// Este tipo de chequeos se puede realizar de otras formas y con otras librerías
	// de Java,
	// por ejemplo impidiendo que se escriban caracteres no numéricos al momento de
	// escribir en
	// en el campo de la cédula, o mostrando un mensaje de error apenas el foco pasa
	// a otro campo.
	private boolean checkFormulario(String nombreOfertaLab, String descripOfertaLab, String remuneracionOfertaLab,
			String ciudadOfertaLab, String departOfertaLab, String horaIniOfertaLab, String horaFinOfertaLab,
			Date fechaAlta, String nicknameEmpresa
			 ) {

		if (nombreOfertaLab.isEmpty() || descripOfertaLab.isEmpty() || remuneracionOfertaLab.isEmpty()
				|| ciudadOfertaLab.isEmpty() || departOfertaLab.isEmpty() || horaIniOfertaLab.isEmpty()
				|| horaFinOfertaLab.isEmpty()) {
			JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Registrar Oferta Laboral",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (this.comboBoxSeleccionTiposPublicaciones.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(this, "Debe seleccionar un tipo de publicación", "Registrar Oferta Laboral",
					JOptionPane.ERROR_MESSAGE);
		}
		if (fechaAlta == null) {
			JOptionPane.showMessageDialog(this, "Debe ingresar una fecha valida", "Registrar Oferta Laboral",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (chequeoHoraValida(horaIniOfertaLab)) {
			JOptionPane.showMessageDialog(this, "Por favor, ingrese una hora de inicio valida del tipo xx:xx",
					"Registrar Oferta Laboral", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (chequeoHoraValida(horaFinOfertaLab)) {
			JOptionPane.showMessageDialog(this, "Por favor, ingrese una hora de fin valida del tipo xx:xx",
					"Registrar Oferta Laboral", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		/*
		 * char data[] = {'a', 'b', 'c'}; String str = new String(data);
		 */

		try {
			Float.parseFloat(remuneracionOfertaLab);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "La remuneración debe ser un numero", "Registrar Oferta Laboral",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (Float.parseFloat(remuneracionOfertaLab) <= 0) {
			JOptionPane.showMessageDialog(this, "La remuneración debe ser mayor a cero", "Registrar Oferta Laboral",
					JOptionPane.ERROR_MESSAGE);
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

	// Permite borrar el contenido de un formulario antes de cerrarlo.
	// Recordar que las ventanas no se destruyen, sino que simplemente
	// se ocultan, por lo que conviene borrar la información para que
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
