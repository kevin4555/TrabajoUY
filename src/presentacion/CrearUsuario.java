package presentacion;

import javax.swing.JInternalFrame;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import logica.interfaces.IControladorUsuario;

import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import com.toedter.calendar.JDateChooser;

import excepciones.UsuarioEmailRepetidoException;
import excepciones.UsuarioYaExisteException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class CrearUsuario extends JInternalFrame {

	private JTextField textFieldNickName;
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private JTextField textFieldEmail;
	private JTextField textFieldSitioWeb;

	private JDateChooser fechaNacimientoChooser;
	private JTextField textFieldNacionalidad;
	private IControladorUsuario controladorUsuario;
	private JComboBox<String> comboBoxSeleccionTipoUsuario;
	private JPanel panelEmpresa;
	private JPanel panelPostulante;
	private JLayeredPane layeredPane;
	private JScrollPane scrollPane;
	private String tipoUsuarioSeleccionado;
	private JTextArea textAreaDescripcion;
	private JButton btnCancelar;
	private JButton btnConfirmar;
	private JPanel panelDatos;

	public CrearUsuario(IControladorUsuario contrUsuario) {
		this.controladorUsuario = contrUsuario;

		setIconifiable(true);
		setResizable(true);
		setMaximizable(true);

		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setTitle("Registrar Usuario");

		setBounds(100, 100, 594, 502);
		JPanel panelBotones = new JPanel();
		getContentPane().add(panelBotones, BorderLayout.SOUTH);
		panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 120, 20));

		this.btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (RegistrarUsuario()) {
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
		GridBagLayout gbl_panelDatos = new GridBagLayout();
		gbl_panelDatos.columnWidths = new int[] { 113, 739, 0 };
		gbl_panelDatos.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelDatos.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_panelDatos.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, Double.MIN_VALUE };
		panelDatos.setLayout(gbl_panelDatos);

		JLabel lblSeleccion = new JLabel("Tipo Usuario:");
		GridBagConstraints gbc_lblSeleccion = new GridBagConstraints();
		gbc_lblSeleccion.insets = new Insets(0, 0, 5, 5);
		gbc_lblSeleccion.anchor = GridBagConstraints.EAST;
		gbc_lblSeleccion.gridx = 0;
		gbc_lblSeleccion.gridy = 1;
		panelDatos.add(lblSeleccion, gbc_lblSeleccion);

		this.comboBoxSeleccionTipoUsuario = new JComboBox<String>();
		DefaultComboBoxModel<String> model;
		String[] tipoUsuarios = { "Postulante", "Empresa" };
		model = new DefaultComboBoxModel<String>(tipoUsuarios);
		this.tipoUsuarioSeleccionado = "Postulante";
		this.comboBoxSeleccionTipoUsuario.setModel(model);
		this.comboBoxSeleccionTipoUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiarTipoUsuario(e);
			}
		});

		GridBagConstraints gbc_comboBoxSeleccionTipoUsuario = new GridBagConstraints();
		gbc_comboBoxSeleccionTipoUsuario.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxSeleccionTipoUsuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxSeleccionTipoUsuario.gridx = 1;
		gbc_comboBoxSeleccionTipoUsuario.gridy = 1;
		panelDatos.add(this.comboBoxSeleccionTipoUsuario, gbc_comboBoxSeleccionTipoUsuario);

		JLabel lblNickname = new JLabel("Nickname:");
		GridBagConstraints gbc_lblNickname = new GridBagConstraints();
		gbc_lblNickname.anchor = GridBagConstraints.EAST;
		gbc_lblNickname.insets = new Insets(0, 0, 5, 5);
		gbc_lblNickname.gridx = 0;
		gbc_lblNickname.gridy = 2;
		panelDatos.add(lblNickname, gbc_lblNickname);

		this.textFieldNickName = new JTextField();
		this.textFieldNickName.setEditable(true);
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
		this.textFieldNombre.setEditable(true);
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
		this.textFieldApellido.setEditable(true);
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
		this.textFieldEmail.setEditable(true);
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
		this.textFieldSitioWeb.setEditable(true);
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
		textAreaDescripcion.setEditable(true);
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

		this.fechaNacimientoChooser = new JDateChooser();
		GridBagConstraints gbc_FechaNacimiento = new GridBagConstraints();
		gbc_FechaNacimiento.insets = new Insets(0, 0, 5, 0);
		gbc_FechaNacimiento.fill = GridBagConstraints.HORIZONTAL;
		gbc_FechaNacimiento.gridx = 1;
		gbc_FechaNacimiento.gridy = 0;
		panelPostulante.add(fechaNacimientoChooser, gbc_FechaNacimiento);

		JLabel lblNacionalidad = new JLabel("Nacionalidad:");
		GridBagConstraints gbc_lblNacionalidad = new GridBagConstraints();
		gbc_lblNacionalidad.anchor = GridBagConstraints.EAST;
		gbc_lblNacionalidad.insets = new Insets(0, 0, 0, 5);
		gbc_lblNacionalidad.gridx = 0;
		gbc_lblNacionalidad.gridy = 1;
		panelPostulante.add(lblNacionalidad, gbc_lblNacionalidad);

		this.textFieldNacionalidad = new JTextField();
		this.textFieldNacionalidad.setEditable(true);
		GridBagConstraints gbc_textFieldNacionalidad = new GridBagConstraints();
		gbc_textFieldNacionalidad.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNacionalidad.gridx = 1;
		gbc_textFieldNacionalidad.gridy = 1;
		panelPostulante.add(this.textFieldNacionalidad, gbc_textFieldNacionalidad);
		this.textFieldNacionalidad.setColumns(10);
		cambiarPanel(panelPostulante);
	}

	protected boolean RegistrarUsuario() {
		String nickname = this.textFieldNickName.getText();
		String nombre = this.textFieldNombre.getText();
		String apellido = this.textFieldApellido.getText();
		String email = this.textFieldEmail.getText();

		String descripcion = this.textAreaDescripcion.getText();
		String sitioWeb = this.textFieldSitioWeb.getText();

		String nacionalidad = this.textFieldNacionalidad.getText();
		Date fechaNacimienito = this.fechaNacimientoChooser.getDate();

		if (checkFormulario()) {
			try {
				if (tipoUsuarioSeleccionado.equals("Postulante")) {
					this.controladorUsuario.altaPostulante(nickname, nombre, apellido, email, fechaNacimienito,
							nacionalidad);
					JOptionPane.showMessageDialog(this, "El Postulante se ha creado con éxito", "Registrar Usuario",
							JOptionPane.INFORMATION_MESSAGE);
					return true;
				} else {
					this.controladorUsuario.altaEmpresa(nickname, nombre, apellido, email, descripcion, sitioWeb);
					JOptionPane.showMessageDialog(this, "La Empresa se ha creado con éxito", "Registrar Usuario",
							JOptionPane.INFORMATION_MESSAGE);
					return true;
				}
			} catch (UsuarioYaExisteException e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Trabajo.uy", JOptionPane.ERROR_MESSAGE);
				return false;
			} catch (UsuarioEmailRepetidoException e2) {
				JOptionPane.showMessageDialog(this, e2.getMessage(), "Trabajo.uy", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		return false;
	}

	@SuppressWarnings("exports")
	public void cambiarTipoUsuario(ActionEvent e) {

		String tipoSeleccionado = comboBoxSeleccionTipoUsuario.getSelectedItem().toString();
		if (!tipoSeleccionado.equals(tipoUsuarioSeleccionado)) {
			tipoUsuarioSeleccionado = tipoSeleccionado;

			if (tipoUsuarioSeleccionado.equals("Empresa")) {
				cambiarPanel(panelEmpresa);
			}
			if (tipoUsuarioSeleccionado.equals("Postulante")) {
				cambiarPanel(panelPostulante);
			}
		}

	}

	private boolean checkFormulario() {
		if (this.textFieldNickName.getText().isEmpty() || this.textFieldNombre.getText().isEmpty()
				|| this.textFieldApellido.getText().isEmpty() || this.textFieldEmail.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Registrar Usuario",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
	
		if(!this.textFieldEmail.getText().contains("@")) {
			JOptionPane.showMessageDialog(this, "Debe ingresar un mail con el valor @", "Registrar Usuario",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (this.tipoUsuarioSeleccionado.equals("Postulante")) {
			if (this.textFieldNacionalidad.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Debe ingresar una Nacionalidad", "Registrar Usuario",
						JOptionPane.ERROR_MESSAGE);
				return false;
			}
			Date fechaNacimiento = fechaNacimientoChooser.getDate();
			if (fechaNacimiento == null) {
				JOptionPane.showMessageDialog(this, "Debe ingresar una Fecha de Nacimiento", "Registrar Usuario",
						JOptionPane.ERROR_MESSAGE);
				return false;
			}
		} else {
			if (this.textAreaDescripcion.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Debe ingresar una Descripcion", "Registrar Usuario",
						JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		return true;
	}

	@SuppressWarnings("exports")
	public void cambiarPanel(JPanel panel) {
		limpiarTodosLosDatos();
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
	}

	public void limpiarTodosLosDatos() {
		this.textFieldNickName.setText("");
		this.textFieldNombre.setText("");
		this.textFieldApellido.setText("");
		this.textFieldEmail.setText("");

		this.textFieldNacionalidad.setText("");
		this.fechaNacimientoChooser.setDate(null);
		this.textAreaDescripcion.setText("");
		this.textFieldSitioWeb.setText("");

	}
}
