package presentacion;

import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JLayeredPane;
import java.awt.CardLayout;
import javax.swing.JTextField;

import excepciones.OfertaLaboralNoExisteException;
import excepciones.UsuarioNoExisteException;
import logica.DataTypes.DTEmpresa;
import logica.DataTypes.DTOfertaLaboral;
import logica.DataTypes.DTPostulante;
import logica.DataTypes.DTUsuario;
import logica.interfaces.IControladorOferta;
import logica.interfaces.IControladorUsuario;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

@SuppressWarnings("serial")
public class ConsultarUsuario extends JInternalFrame {
	private JTextField textFieldNickName;
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private JTextField textFieldEmail;
	private JTextField textFieldNombreOferta;
	private JTextField textFieldRemuneracion;
	private JTextField textFieldCiudad;
	private JTextField textFieldDepartamento;
	private JTextField textFieldSitioWeb;
	private JTextField textFieldFechaNacimiento;
	private JTextField textFieldNacionalidad;
	private IControladorUsuario controladorUsuario;
	private IControladorOferta controladorOferta;
	private JComboBox<String> comboBoxSeleccionUsuario;
	private JPanel panelEmpresa;
	private JPanel panelPostulante;
	private JLayeredPane layeredPane;
	private JComboBox<String> comboBoxSeleccionOferta;
	private JScrollPane scrollPane;
	private JTextArea textAreaDescripcion;
	private String usuarioSeleccionado;
	private String ofertaSeleccionada;
	private JButton btnCerrar;
	private JPanel panelDatos;

	public ConsultarUsuario(IControladorUsuario contrUsuario, IControladorOferta contrOferta) {
		setClosable(true);
		this.usuarioSeleccionado = "";
		this.ofertaSeleccionada = "";
		
		setIconifiable(true);
		setResizable(true);
		setMaximizable(true);
		this.controladorUsuario = contrUsuario;
		controladorOferta = contrOferta;
		getContentPane().setLayout(new BorderLayout(0, 0));
		setBounds(100, 100, 594, 502);

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
		gbl_panelDatos.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelDatos.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_panelDatos.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, Double.MIN_VALUE };
		panelDatos.setLayout(gbl_panelDatos);

		JLabel lblSeleccion = new JLabel("Seleccionar Usuario:");
		GridBagConstraints gbc_lblSeleccion = new GridBagConstraints();
		gbc_lblSeleccion.insets = new Insets(0, 0, 5, 5);
		gbc_lblSeleccion.anchor = GridBagConstraints.EAST;
		gbc_lblSeleccion.gridx = 0;
		gbc_lblSeleccion.gridy = 1;
		panelDatos.add(lblSeleccion, gbc_lblSeleccion);

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

		GridBagConstraints gbc_comboBoxSeleccionUsuario = new GridBagConstraints();
		gbc_comboBoxSeleccionUsuario.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxSeleccionUsuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxSeleccionUsuario.gridx = 1;
		gbc_comboBoxSeleccionUsuario.gridy = 1;
		panelDatos.add(this.comboBoxSeleccionUsuario, gbc_comboBoxSeleccionUsuario);

		JLabel lblNickname = new JLabel("Nickname:");
		GridBagConstraints gbc_lblNickname = new GridBagConstraints();
		gbc_lblNickname.anchor = GridBagConstraints.EAST;
		gbc_lblNickname.insets = new Insets(0, 0, 5, 5);
		gbc_lblNickname.gridx = 0;
		gbc_lblNickname.gridy = 2;
		panelDatos.add(lblNickname, gbc_lblNickname);

		this.textFieldNickName = new JTextField();
		this.textFieldNickName.setEditable(false);
		GridBagConstraints gbc_textFieldNickName = new GridBagConstraints();
		gbc_textFieldNickName.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldNickName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNickName.gridx = 1;
		gbc_textFieldNickName.gridy = 2;
		panelDatos.add(textFieldNickName, gbc_textFieldNickName);
		this.textFieldNickName.setColumns(10);

		JLabel lblNombre = new JLabel("Nombre:");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.EAST;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 0;
		gbc_lblNombre.gridy = 3;
		panelDatos.add(lblNombre, gbc_lblNombre);

		this.textFieldNombre = new JTextField();
		this.textFieldNombre.setEditable(false);
		GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
		gbc_textFieldNombre.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNombre.gridx = 1;
		gbc_textFieldNombre.gridy = 3;
		panelDatos.add(textFieldNombre, gbc_textFieldNombre);
		textFieldNombre.setColumns(10);

		JLabel lblApellido = new JLabel("Apellido:");
		GridBagConstraints gbc_lblApellido = new GridBagConstraints();
		gbc_lblApellido.anchor = GridBagConstraints.EAST;
		gbc_lblApellido.insets = new Insets(0, 0, 5, 5);
		gbc_lblApellido.gridx = 0;
		gbc_lblApellido.gridy = 4;
		panelDatos.add(lblApellido, gbc_lblApellido);

		this.textFieldApellido = new JTextField();
		this.textFieldApellido.setEditable(false);
		GridBagConstraints gbc_textFieldApellido = new GridBagConstraints();
		gbc_textFieldApellido.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldApellido.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldApellido.gridx = 1;
		gbc_textFieldApellido.gridy = 4;
		panelDatos.add(this.textFieldApellido, gbc_textFieldApellido);
		this.textFieldApellido.setColumns(10);

		JLabel lblEmail = new JLabel("Email:");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.EAST;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 0;
		gbc_lblEmail.gridy = 5;
		panelDatos.add(lblEmail, gbc_lblEmail);

		this.textFieldEmail = new JTextField();
		this.textFieldEmail.setEditable(false);
		GridBagConstraints gbc_textFieldEmail = new GridBagConstraints();
		gbc_textFieldEmail.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldEmail.gridx = 1;
		gbc_textFieldEmail.gridy = 5;
		panelDatos.add(this.textFieldEmail, gbc_textFieldEmail);
		this.textFieldEmail.setColumns(10);

		layeredPane = new JLayeredPane();
		GridBagConstraints gbc_layeredPane = new GridBagConstraints();
		gbc_layeredPane.gridheight = 3;
		gbc_layeredPane.gridwidth = 2;
		gbc_layeredPane.insets = new Insets(0, 0, 5, 0);
		gbc_layeredPane.fill = GridBagConstraints.BOTH;
		gbc_layeredPane.gridx = 0;
		gbc_layeredPane.gridy = 6;
		panelDatos.add(layeredPane, gbc_layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));

		panelEmpresa = new JPanel();
		layeredPane.add(panelEmpresa, "name_918030925291900");
		GridBagLayout gbl_panelEmpresa = new GridBagLayout();
		gbl_panelEmpresa.columnWidths = new int[] { 114, 0, 0 };
		gbl_panelEmpresa.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_panelEmpresa.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_panelEmpresa.rowWeights = new double[] { 1.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		panelEmpresa.setLayout(gbl_panelEmpresa);

		JLabel lblSitioWeb = new JLabel("Sitio Web:");
		GridBagConstraints gbc_lblSitioWeb = new GridBagConstraints();
		gbc_lblSitioWeb.anchor = GridBagConstraints.EAST;
		gbc_lblSitioWeb.insets = new Insets(0, 0, 5, 5);
		gbc_lblSitioWeb.gridx = 0;
		gbc_lblSitioWeb.gridy = 1;
		panelEmpresa.add(lblSitioWeb, gbc_lblSitioWeb);

		this.textFieldSitioWeb = new JTextField();
		this.textFieldSitioWeb.setEditable(false);
		GridBagConstraints gbc_textFieldSitioWeb = new GridBagConstraints();
		gbc_textFieldSitioWeb.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldSitioWeb.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldSitioWeb.gridx = 1;
		gbc_textFieldSitioWeb.gridy = 1;
		panelEmpresa.add(this.textFieldSitioWeb, gbc_textFieldSitioWeb);
		this.textFieldSitioWeb.setColumns(10);

		JLabel lblDescripcionEmpresa = new JLabel("Descripcion:");
		GridBagConstraints gbc_lblDescripcionEmpresa = new GridBagConstraints();
		gbc_lblDescripcionEmpresa.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescripcionEmpresa.anchor = GridBagConstraints.EAST;
		gbc_lblDescripcionEmpresa.gridx = 0;
		gbc_lblDescripcionEmpresa.gridy = 2;
		panelEmpresa.add(lblDescripcionEmpresa, gbc_lblDescripcionEmpresa);

		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setEnabled(false);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 2;
		panelEmpresa.add(scrollPane, gbc_scrollPane);

		textAreaDescripcion = new JTextArea();
		textAreaDescripcion.setLineWrap(true);
		textAreaDescripcion.setWrapStyleWord(true);
		textAreaDescripcion.setEditable(false);
		scrollPane.setViewportView(textAreaDescripcion);

		panelPostulante = new JPanel();
		layeredPane.add(panelPostulante, "name_919472867094100");
		GridBagLayout gbl_panelPostulante = new GridBagLayout();
		gbl_panelPostulante.columnWidths = new int[] { 113, 0, 0 };
		gbl_panelPostulante.rowHeights = new int[] { 0, 0, 0 };
		gbl_panelPostulante.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_panelPostulante.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		panelPostulante.setLayout(gbl_panelPostulante);

		JLabel lblFechaNacimiento = new JLabel("Fecha de Nacimiento:");
		GridBagConstraints gbc_lblFechaNacimiento = new GridBagConstraints();
		gbc_lblFechaNacimiento.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaNacimiento.anchor = GridBagConstraints.EAST;
		gbc_lblFechaNacimiento.gridx = 0;
		gbc_lblFechaNacimiento.gridy = 0;
		panelPostulante.add(lblFechaNacimiento, gbc_lblFechaNacimiento);

		this.textFieldFechaNacimiento = new JTextField();
		this.textFieldFechaNacimiento.setEditable(false);
		GridBagConstraints gbc_textFieldFechaNacimiento = new GridBagConstraints();
		gbc_textFieldFechaNacimiento.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldFechaNacimiento.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldFechaNacimiento.gridx = 1;
		gbc_textFieldFechaNacimiento.gridy = 0;
		panelPostulante.add(this.textFieldFechaNacimiento, gbc_textFieldFechaNacimiento);
		this.textFieldFechaNacimiento.setColumns(10);

		JLabel lblNacionalidad = new JLabel("Nacionalidad:");
		GridBagConstraints gbc_lblNacionalidad = new GridBagConstraints();
		gbc_lblNacionalidad.anchor = GridBagConstraints.EAST;
		gbc_lblNacionalidad.insets = new Insets(0, 0, 0, 5);
		gbc_lblNacionalidad.gridx = 0;
		gbc_lblNacionalidad.gridy = 1;
		panelPostulante.add(lblNacionalidad, gbc_lblNacionalidad);

		this.textFieldNacionalidad = new JTextField();
		this.textFieldNacionalidad.setEditable(false);
		GridBagConstraints gbc_textFieldNacionalidad = new GridBagConstraints();
		gbc_textFieldNacionalidad.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNacionalidad.gridx = 1;
		gbc_textFieldNacionalidad.gridy = 1;
		panelPostulante.add(this.textFieldNacionalidad, gbc_textFieldNacionalidad);
		this.textFieldNacionalidad.setColumns(10);

		JLabel lblOfertas = new JLabel("Ofertas Laborales:");
		GridBagConstraints gbc_lblOfertas = new GridBagConstraints();
		gbc_lblOfertas.anchor = GridBagConstraints.EAST;
		gbc_lblOfertas.insets = new Insets(0, 0, 5, 5);
		gbc_lblOfertas.gridx = 0;
		gbc_lblOfertas.gridy = 10;
		panelDatos.add(lblOfertas, gbc_lblOfertas);

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
		GridBagConstraints gbc_comboBoxSeleccionOferta = new GridBagConstraints();
		gbc_comboBoxSeleccionOferta.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxSeleccionOferta.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxSeleccionOferta.gridx = 1;
		gbc_comboBoxSeleccionOferta.gridy = 10;
		panelDatos.add(this.comboBoxSeleccionOferta, gbc_comboBoxSeleccionOferta);

		JLabel lblNombreOferta = new JLabel("Nombre Oferta:");
		GridBagConstraints gbc_lblNombreOferta = new GridBagConstraints();
		gbc_lblNombreOferta.anchor = GridBagConstraints.EAST;
		gbc_lblNombreOferta.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombreOferta.gridx = 0;
		gbc_lblNombreOferta.gridy = 11;
		panelDatos.add(lblNombreOferta, gbc_lblNombreOferta);

		this.textFieldNombreOferta = new JTextField();
		this.textFieldNombreOferta.setEditable(false);
		GridBagConstraints gbc_textFieldNombreOferta = new GridBagConstraints();
		gbc_textFieldNombreOferta.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldNombreOferta.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNombreOferta.gridx = 1;
		gbc_textFieldNombreOferta.gridy = 11;
		panelDatos.add(this.textFieldNombreOferta, gbc_textFieldNombreOferta);
		this.textFieldNombreOferta.setColumns(10);

		JLabel lblRemuneracion = new JLabel("Remuneracion");
		GridBagConstraints gbc_lblRemuneracion = new GridBagConstraints();
		gbc_lblRemuneracion.anchor = GridBagConstraints.EAST;
		gbc_lblRemuneracion.insets = new Insets(0, 0, 5, 5);
		gbc_lblRemuneracion.gridx = 0;
		gbc_lblRemuneracion.gridy = 12;
		panelDatos.add(lblRemuneracion, gbc_lblRemuneracion);

		this.textFieldRemuneracion = new JTextField();
		this.textFieldRemuneracion.setEditable(false);
		GridBagConstraints gbc_textFieldRemuneracion = new GridBagConstraints();
		gbc_textFieldRemuneracion.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldRemuneracion.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldRemuneracion.gridx = 1;
		gbc_textFieldRemuneracion.gridy = 12;
		panelDatos.add(this.textFieldRemuneracion, gbc_textFieldRemuneracion);
		this.textFieldRemuneracion.setColumns(10);

		JLabel lblCiudad = new JLabel("Ciudad");
		GridBagConstraints gbc_lblCiudad = new GridBagConstraints();
		gbc_lblCiudad.anchor = GridBagConstraints.EAST;
		gbc_lblCiudad.insets = new Insets(0, 0, 5, 5);
		gbc_lblCiudad.gridx = 0;
		gbc_lblCiudad.gridy = 13;
		panelDatos.add(lblCiudad, gbc_lblCiudad);

		this.textFieldCiudad = new JTextField();
		this.textFieldCiudad.setEditable(false);
		GridBagConstraints gbc_textFieldCiudad = new GridBagConstraints();
		gbc_textFieldCiudad.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldCiudad.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCiudad.gridx = 1;
		gbc_textFieldCiudad.gridy = 13;
		panelDatos.add(this.textFieldCiudad, gbc_textFieldCiudad);
		this.textFieldCiudad.setColumns(10);

		JLabel lblDepartamento = new JLabel("Departamento");
		GridBagConstraints gbc_lblDepartamento = new GridBagConstraints();
		gbc_lblDepartamento.anchor = GridBagConstraints.EAST;
		gbc_lblDepartamento.insets = new Insets(0, 0, 5, 5);
		gbc_lblDepartamento.gridx = 0;
		gbc_lblDepartamento.gridy = 14;
		panelDatos.add(lblDepartamento, gbc_lblDepartamento);

		this.textFieldDepartamento = new JTextField();
		this.textFieldDepartamento.setEditable(false);
		GridBagConstraints gbc_textFieldDepartamento = new GridBagConstraints();
		gbc_textFieldDepartamento.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldDepartamento.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDepartamento.gridx = 1;
		gbc_textFieldDepartamento.gridy = 14;
		panelDatos.add(this.textFieldDepartamento, gbc_textFieldDepartamento);
		this.textFieldDepartamento.setColumns(10);

	}

	protected void cargarDatosOferta(ActionEvent e) throws OfertaLaboralNoExisteException {
		String oferta = comboBoxSeleccionOferta.getSelectedItem().toString();
		if (ofertaSeleccionada != oferta) {
			DTOfertaLaboral dtOferta = controladorOferta.obtenerDtOfertaLaboral(oferta);
			this.textFieldNombreOferta.setText(oferta);
			this.textFieldRemuneracion.setText(dtOferta.getRemuneracion().toString());
			this.textFieldCiudad.setText(dtOferta.getCiudad());
			this.textFieldDepartamento.setText(dtOferta.getDepartamento());
		}
	}

	public void cargarUsuarios() {
		try {
			ArrayList<String> listaUsuarios = this.controladorUsuario.listaDeUsuarios();
			String [] arrayUsuarios;
			arrayUsuarios = listaUsuarios.toArray(new String [0]);
			Arrays.sort(arrayUsuarios);
			DefaultComboBoxModel<String> model;
			model = new DefaultComboBoxModel<String>(arrayUsuarios);
			this.comboBoxSeleccionUsuario.setModel(model);
		
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@SuppressWarnings({ "deprecation", "exports" })
	public void cargarDatosUsuarios(ActionEvent e) throws UsuarioNoExisteException {
		
		String nicknameUsuario = comboBoxSeleccionUsuario.getSelectedItem().toString();
		if (nicknameUsuario != usuarioSeleccionado) {
			usuarioSeleccionado = nicknameUsuario;
			limpiarDatosOfertas();
			DTUsuario dtUsuario = this.controladorUsuario.obtenerDTUsuario(nicknameUsuario);
			
		
			this.textFieldNickName.setText(nicknameUsuario);
			this.textFieldNombre.setText(dtUsuario.getNombre());
			this.textFieldApellido.setText(dtUsuario.getApellido());
			this.textFieldEmail.setText(dtUsuario.getEmail());
			if (dtUsuario instanceof DTEmpresa) {
				DTEmpresa dtEmpresa = (DTEmpresa) dtUsuario;
				this.textAreaDescripcion.setText(dtEmpresa.getDescripcion());
				this.textFieldSitioWeb.setText(dtEmpresa.getSitioWeb());
				cambiarPanel(panelEmpresa);
			}
			if (dtUsuario instanceof DTPostulante) {
				DTPostulante dtPostulante = (DTPostulante) dtUsuario;
				this.textFieldNacionalidad.setText(dtPostulante.getNacionalidad());
				this.textFieldFechaNacimiento.setText(dtPostulante.getFechaNacimiento().toGMTString());
				cambiarPanel(panelPostulante);
			}
			ArrayList<String> listaOfertas = this.controladorUsuario.listaOfertasUsuario(nicknameUsuario);
			String [] arrayOfertas = listaOfertas.toArray(new String[0]);
			Arrays.sort(arrayOfertas);
			DefaultComboBoxModel<String> model;
			model = new DefaultComboBoxModel<String>(arrayOfertas);
			this.comboBoxSeleccionOferta.setModel(model);
		}
		
	}
	
	@SuppressWarnings("exports")
	public void cambiarPanel(JPanel panel) {
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
	}
	
	public void limpiarDatosOfertas() {
		this.textFieldDepartamento.setText("");
		this.textFieldCiudad.setText("");
		this.textFieldNombreOferta.setText("");
		this.textFieldRemuneracion.setText("");
		//this.comboBoxSeleccionOferta = new JComboBox<String>();
	}
	
	public void limpiarTodosLosDatos() {
		
		this.ofertaSeleccionada = "";
		this.usuarioSeleccionado ="";
		this.textFieldNickName.setText("");
		this.textFieldNombre.setText("");
		this.textFieldApellido.setText("");
		this.textFieldEmail.setText("");
		
		this.textFieldFechaNacimiento.setText("");
		this.textFieldNacionalidad.setText("");
		
		this.textAreaDescripcion.setText("");
		this.textFieldSitioWeb.setText("");
		this.textFieldDepartamento.setText("");
		this.textFieldCiudad.setText("");
		this.textFieldNombreOferta.setText("");
		this.textFieldRemuneracion.setText("");
		
		ArrayList<String> listaOfertas = new ArrayList<String>();
		String [] arrayOfertas = listaOfertas.toArray(new String[0]);
		Arrays.sort(arrayOfertas);
		DefaultComboBoxModel<String> model;
		model = new DefaultComboBoxModel<String>(arrayOfertas);
		this.comboBoxSeleccionOferta.setModel(model);
		
		this.comboBoxSeleccionUsuario.setModel(model);
		
	}
	
}
