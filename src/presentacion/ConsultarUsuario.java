package presentacion;

import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.lang.reflect.Array;
import java.util.ArrayList;

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
	private JTextField textFieldDescripcion;
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
	
	public ConsultarUsuario(IControladorUsuario contrUsuario, IControladorOferta contrOferta) {
		setIconifiable(true);
		setResizable(true);
		setClosable(true);
		setMaximizable(true);
		controladorUsuario = contrUsuario;
		controladorOferta = contrOferta;
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panelBotones = new JPanel();
		getContentPane().add(panelBotones, BorderLayout.SOUTH);
		panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 120, 20));
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panelBotones.add(btnCerrar);
		
		JPanel panelDatos = new JPanel();
		getContentPane().add(panelDatos, BorderLayout.CENTER);
		GridBagLayout gbl_panelDatos = new GridBagLayout();
		gbl_panelDatos.columnWidths = new int[]{113, 739, 0};
		gbl_panelDatos.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelDatos.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panelDatos.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelDatos.setLayout(gbl_panelDatos);
		
		JLabel lblSeleccion = new JLabel("Seleccionar Usuario:");
		GridBagConstraints gbc_lblSeleccion = new GridBagConstraints();
		gbc_lblSeleccion.insets = new Insets(0, 0, 5, 5);
		gbc_lblSeleccion.anchor = GridBagConstraints.EAST;
		gbc_lblSeleccion.gridx = 0;
		gbc_lblSeleccion.gridy = 1;
		panelDatos.add(lblSeleccion, gbc_lblSeleccion);
		
		comboBoxSeleccionUsuario = new JComboBox<String>();
		cargarUsuarios();
		comboBoxSeleccionUsuario.addActionListener(new ActionListener() {
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
		panelDatos.add(comboBoxSeleccionUsuario, gbc_comboBoxSeleccionUsuario);
		
		JLabel lblNickname = new JLabel("Nickname:");
		GridBagConstraints gbc_lblNickname = new GridBagConstraints();
		gbc_lblNickname.anchor = GridBagConstraints.EAST;
		gbc_lblNickname.insets = new Insets(0, 0, 5, 5);
		gbc_lblNickname.gridx = 0;
		gbc_lblNickname.gridy = 2;
		panelDatos.add(lblNickname, gbc_lblNickname);
		
		textFieldNickName = new JTextField();
		textFieldNickName.setEditable(false);
		GridBagConstraints gbc_textFieldNickName = new GridBagConstraints();
		gbc_textFieldNickName.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldNickName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNickName.gridx = 1;
		gbc_textFieldNickName.gridy = 2;
		panelDatos.add(textFieldNickName, gbc_textFieldNickName);
		textFieldNickName.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre:");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.EAST;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 0;
		gbc_lblNombre.gridy = 3;
		panelDatos.add(lblNombre, gbc_lblNombre);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setEditable(false);
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
		
		textFieldApellido = new JTextField();
		textFieldApellido.setEditable(false);
		GridBagConstraints gbc_textFieldApellido = new GridBagConstraints();
		gbc_textFieldApellido.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldApellido.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldApellido.gridx = 1;
		gbc_textFieldApellido.gridy = 4;
		panelDatos.add(textFieldApellido, gbc_textFieldApellido);
		textFieldApellido.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email:");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.EAST;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 0;
		gbc_lblEmail.gridy = 5;
		panelDatos.add(lblEmail, gbc_lblEmail);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setEditable(false);
		GridBagConstraints gbc_textFieldEmail = new GridBagConstraints();
		gbc_textFieldEmail.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldEmail.gridx = 1;
		gbc_textFieldEmail.gridy = 5;
		panelDatos.add(textFieldEmail, gbc_textFieldEmail);
		textFieldEmail.setColumns(10);
		
		layeredPane = new JLayeredPane();
		GridBagConstraints gbc_layeredPane = new GridBagConstraints();
		gbc_layeredPane.gridheight = 2;
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
		gbl_panelEmpresa.columnWidths = new int[]{114, 0, 0};
		gbl_panelEmpresa.rowHeights = new int[]{0, 0, 0};
		gbl_panelEmpresa.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panelEmpresa.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panelEmpresa.setLayout(gbl_panelEmpresa);
		
		JLabel lblDescripcionEmpresa = new JLabel("Descripcion:");
		GridBagConstraints gbc_lblDescripcionEmpresa = new GridBagConstraints();
		gbc_lblDescripcionEmpresa.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescripcionEmpresa.anchor = GridBagConstraints.EAST;
		gbc_lblDescripcionEmpresa.gridx = 0;
		gbc_lblDescripcionEmpresa.gridy = 0;
		panelEmpresa.add(lblDescripcionEmpresa, gbc_lblDescripcionEmpresa);
		
		textFieldDescripcion = new JTextField();
		textFieldDescripcion.setEditable(false);
		GridBagConstraints gbc_textFieldDescripcion = new GridBagConstraints();
		gbc_textFieldDescripcion.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldDescripcion.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDescripcion.gridx = 1;
		gbc_textFieldDescripcion.gridy = 0;
		panelEmpresa.add(textFieldDescripcion, gbc_textFieldDescripcion);
		textFieldDescripcion.setColumns(10);
		
		JLabel lblSitioWeb = new JLabel("Sitio Web:");
		GridBagConstraints gbc_lblSitioWeb = new GridBagConstraints();
		gbc_lblSitioWeb.anchor = GridBagConstraints.EAST;
		gbc_lblSitioWeb.insets = new Insets(0, 0, 0, 5);
		gbc_lblSitioWeb.gridx = 0;
		gbc_lblSitioWeb.gridy = 1;
		panelEmpresa.add(lblSitioWeb, gbc_lblSitioWeb);
		
		textFieldSitioWeb = new JTextField();
		textFieldSitioWeb.setEditable(false);
		GridBagConstraints gbc_textFieldSitioWeb = new GridBagConstraints();
		gbc_textFieldSitioWeb.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldSitioWeb.gridx = 1;
		gbc_textFieldSitioWeb.gridy = 1;
		panelEmpresa.add(textFieldSitioWeb, gbc_textFieldSitioWeb);
		textFieldSitioWeb.setColumns(10);
		
		panelPostulante = new JPanel();
		layeredPane.add(panelPostulante, "name_919472867094100");
		GridBagLayout gbl_panelPostulante = new GridBagLayout();
		gbl_panelPostulante.columnWidths = new int[]{113, 0, 0};
		gbl_panelPostulante.rowHeights = new int[]{0, 0, 0};
		gbl_panelPostulante.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panelPostulante.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panelPostulante.setLayout(gbl_panelPostulante);
		
		JLabel lblFechaNacimiento = new JLabel("Fecha de Nacimiento:");
		GridBagConstraints gbc_lblFechaNacimiento = new GridBagConstraints();
		gbc_lblFechaNacimiento.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaNacimiento.anchor = GridBagConstraints.EAST;
		gbc_lblFechaNacimiento.gridx = 0;
		gbc_lblFechaNacimiento.gridy = 0;
		panelPostulante.add(lblFechaNacimiento, gbc_lblFechaNacimiento);
		
		textFieldFechaNacimiento = new JTextField();
		textFieldFechaNacimiento.setEditable(false);
		GridBagConstraints gbc_textFieldFechaNacimiento = new GridBagConstraints();
		gbc_textFieldFechaNacimiento.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldFechaNacimiento.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldFechaNacimiento.gridx = 1;
		gbc_textFieldFechaNacimiento.gridy = 0;
		panelPostulante.add(textFieldFechaNacimiento, gbc_textFieldFechaNacimiento);
		textFieldFechaNacimiento.setColumns(10);
		
		JLabel lblNacionalidad = new JLabel("Nacionalidad:");
		GridBagConstraints gbc_lblNacionalidad = new GridBagConstraints();
		gbc_lblNacionalidad.anchor = GridBagConstraints.EAST;
		gbc_lblNacionalidad.insets = new Insets(0, 0, 0, 5);
		gbc_lblNacionalidad.gridx = 0;
		gbc_lblNacionalidad.gridy = 1;
		panelPostulante.add(lblNacionalidad, gbc_lblNacionalidad);
		
		textFieldNacionalidad = new JTextField();
		textFieldNacionalidad.setEditable(false);
		GridBagConstraints gbc_textFieldNacionalidad = new GridBagConstraints();
		gbc_textFieldNacionalidad.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNacionalidad.gridx = 1;
		gbc_textFieldNacionalidad.gridy = 1;
		panelPostulante.add(textFieldNacionalidad, gbc_textFieldNacionalidad);
		textFieldNacionalidad.setColumns(10);
		
		JLabel lblOfertas = new JLabel("Ofertas Laborales:");
		GridBagConstraints gbc_lblOfertas = new GridBagConstraints();
		gbc_lblOfertas.anchor = GridBagConstraints.EAST;
		gbc_lblOfertas.insets = new Insets(0, 0, 5, 5);
		gbc_lblOfertas.gridx = 0;
		gbc_lblOfertas.gridy = 10;
		panelDatos.add(lblOfertas, gbc_lblOfertas);
		
		comboBoxSeleccionOferta = new JComboBox<String>();
		comboBoxSeleccionOferta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarDatosOferta(e);
			}
		});
		GridBagConstraints gbc_comboBoxSeleccionOferta = new GridBagConstraints();
		gbc_comboBoxSeleccionOferta.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxSeleccionOferta.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxSeleccionOferta.gridx = 1;
		gbc_comboBoxSeleccionOferta.gridy = 10;
		panelDatos.add(comboBoxSeleccionOferta, gbc_comboBoxSeleccionOferta);
		
		JLabel lblNombreOferta = new JLabel("Nombre Oferta:");
		GridBagConstraints gbc_lblNombreOferta = new GridBagConstraints();
		gbc_lblNombreOferta.anchor = GridBagConstraints.EAST;
		gbc_lblNombreOferta.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombreOferta.gridx = 0;
		gbc_lblNombreOferta.gridy = 11;
		panelDatos.add(lblNombreOferta, gbc_lblNombreOferta);
		
		textFieldNombreOferta = new JTextField();
		textFieldNombreOferta.setEditable(false);
		GridBagConstraints gbc_textFieldNombreOferta = new GridBagConstraints();
		gbc_textFieldNombreOferta.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldNombreOferta.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNombreOferta.gridx = 1;
		gbc_textFieldNombreOferta.gridy = 11;
		panelDatos.add(textFieldNombreOferta, gbc_textFieldNombreOferta);
		textFieldNombreOferta.setColumns(10);
		
		JLabel lblRemuneracion = new JLabel("Remuneracion");
		GridBagConstraints gbc_lblRemuneracion = new GridBagConstraints();
		gbc_lblRemuneracion.anchor = GridBagConstraints.EAST;
		gbc_lblRemuneracion.insets = new Insets(0, 0, 5, 5);
		gbc_lblRemuneracion.gridx = 0;
		gbc_lblRemuneracion.gridy = 12;
		panelDatos.add(lblRemuneracion, gbc_lblRemuneracion);
		
		textFieldRemuneracion = new JTextField();
		textFieldRemuneracion.setEditable(false);
		GridBagConstraints gbc_textFieldRemuneracion = new GridBagConstraints();
		gbc_textFieldRemuneracion.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldRemuneracion.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldRemuneracion.gridx = 1;
		gbc_textFieldRemuneracion.gridy = 12;
		panelDatos.add(textFieldRemuneracion, gbc_textFieldRemuneracion);
		textFieldRemuneracion.setColumns(10);
		
		JLabel lblCiudad = new JLabel("Ciudad");
		GridBagConstraints gbc_lblCiudad = new GridBagConstraints();
		gbc_lblCiudad.anchor = GridBagConstraints.EAST;
		gbc_lblCiudad.insets = new Insets(0, 0, 5, 5);
		gbc_lblCiudad.gridx = 0;
		gbc_lblCiudad.gridy = 13;
		panelDatos.add(lblCiudad, gbc_lblCiudad);
		
		textFieldCiudad = new JTextField();
		textFieldCiudad.setEditable(false);
		GridBagConstraints gbc_textFieldCiudad = new GridBagConstraints();
		gbc_textFieldCiudad.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldCiudad.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCiudad.gridx = 1;
		gbc_textFieldCiudad.gridy = 13;
		panelDatos.add(textFieldCiudad, gbc_textFieldCiudad);
		textFieldCiudad.setColumns(10);
		
		JLabel lblDepartamento = new JLabel("Departamento");
		GridBagConstraints gbc_lblDepartamento = new GridBagConstraints();
		gbc_lblDepartamento.anchor = GridBagConstraints.EAST;
		gbc_lblDepartamento.insets = new Insets(0, 0, 5, 5);
		gbc_lblDepartamento.gridx = 0;
		gbc_lblDepartamento.gridy = 14;
		panelDatos.add(lblDepartamento, gbc_lblDepartamento);
		
		textFieldDepartamento = new JTextField();
		textFieldDepartamento.setEditable(false);
		GridBagConstraints gbc_textFieldDepartamento = new GridBagConstraints();
		gbc_textFieldDepartamento.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldDepartamento.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDepartamento.gridx = 1;
		gbc_textFieldDepartamento.gridy = 14;
		panelDatos.add(textFieldDepartamento, gbc_textFieldDepartamento);
		textFieldDepartamento.setColumns(10);
		
		
	}


	protected void cargarDatosOferta(ActionEvent e) throws OfertaLaboralNoExisteException {
		String oferta = comboBoxSeleccionOferta.getSelectedItem().toString();
		DTOfertaLaboral  dtOferta = controladorOferta.obtenerDtOfertaLaboral(oferta);
		this.textFieldNombreOferta.setText(oferta);
		this.textFieldRemuneracion.setText(dtOferta.getRemuneracion().toString());
		this.textFieldCiudad.setText(dtOferta.getCiudad());
		this.textFieldDepartamento.setText(dtOferta.getDepartamento());
		
	}


	public void cargarUsuarios() {
		try {
			ArrayList<String> listaUsuarios = this.controladorUsuario.listaDeUsuarios();
			for(String nickUsuario : listaUsuarios) {
				comboBoxSeleccionUsuario.addItem(nickUsuario);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@SuppressWarnings("deprecation")
	public void cargarDatosUsuarios(ActionEvent e) throws UsuarioNoExisteException {
		String nicknameUsuario = comboBoxSeleccionUsuario.getSelectedItem().toString();
		DTUsuario dtUsuario = controladorUsuario.obtenerDTUsuario(nicknameUsuario);
		ArrayList<String> listaOfertas = controladorUsuario.listaOfertasUsuario(nicknameUsuario);
		this.textFieldNickName.setText(nicknameUsuario);
		this.textFieldNombre.setText(dtUsuario.getNombre());
		this.textFieldApellido.setText(dtUsuario.getApellido());
		this.textFieldEmail.setText(dtUsuario.getEmail());
		if(dtUsuario instanceof DTEmpresa) {
			DTEmpresa dtEmpresa = (DTEmpresa) dtUsuario;
			this.textFieldDescripcion.setText(dtEmpresa.getDescripcion());
			this.textFieldSitioWeb.setText(dtEmpresa.getSitioWeb());
			cambiarPanel(panelEmpresa);			
		}
		if(dtUsuario instanceof DTPostulante) {
			DTPostulante dtPostulante = (DTPostulante) dtUsuario;
			this.textFieldNacionalidad.setText(dtPostulante.getNacionalidad());
			this.textFieldFechaNacimiento.setText(dtPostulante.getFechaNacimiento().toGMTString());
			cambiarPanel(panelPostulante);
		}
		for(String oferta : listaOfertas) {
			comboBoxSeleccionOferta.addItem(oferta);
		}
		
	}
	
	public void cambiarPanel(JPanel panel) {
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
	}


}
