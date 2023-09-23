package presentacion;

import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JLabel;
import java.awt.FlowLayout;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;

import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import java.awt.CardLayout;
import javax.swing.JTextField;
import excepciones.UsuarioNoExisteException;
import logica.DataTypes.DTEmpresa;
import logica.DataTypes.DTPostulante;
import logica.DataTypes.DTUsuario;
import logica.interfaces.IControladorUsuario;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTextPane;

@SuppressWarnings("serial")
public class ModificarDatosUsuarios extends JInternalFrame {
	private JTextField textFieldNickName;
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private JTextField textFieldEmail;
	private JTextField textFieldSitioWeb;
	private JTextField textFieldNacionalidad;
	private JDateChooser fechaNacimientoChooser;
	private IControladorUsuario controladorUsuario;
	private JComboBox<String> comboBoxSeleccionUsuario;
	private JPanel panelEmpresa;
	private JPanel panelPostulante;
	private JLayeredPane layeredPane;
	private String tipoUsuario = "";
	private JScrollPane scrollPane;
	private JTextArea textAreaDescripcion;
	private String usuarioSeleccionado;
	private JButton btnConfirmar;
	private JButton btnCancelar;
	private JPanel panelDatos;
	private JLabel lblNewLabel;
	private JTextField textFieldContrasenia;
	private JLabel lblNewLabel_1;
	private JTextPane textPane;
	private JButton selectImageButton;
    private JLabel imageLabel;
    private BufferedImage fotoPerfilUsuario = null;

	public ModificarDatosUsuarios(IControladorUsuario contrUsuario) {
		setTitle("Modificar Datos Usuarios");
		this.usuarioSeleccionado = "";
		
		setIconifiable(true);
		setResizable(true);
		setMaximizable(true);
		this.controladorUsuario = contrUsuario;
		getContentPane().setLayout(new BorderLayout(0, 0));
		setBounds(100, 100, 594, 502);

		

		this.panelDatos = new JPanel();
		getContentPane().add(panelDatos, BorderLayout.CENTER);
		GridBagLayout gbl_panelDatos = new GridBagLayout();
		gbl_panelDatos.columnWidths = new int[] { 113, 739, 0 };
		gbl_panelDatos.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelDatos.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_panelDatos.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
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
		this.textFieldEmail.setEditable(false);
		GridBagConstraints gbc_textFieldEmail = new GridBagConstraints();
		gbc_textFieldEmail.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldEmail.gridx = 1;
		gbc_textFieldEmail.gridy = 5;
		panelDatos.add(this.textFieldEmail, gbc_textFieldEmail);
		this.textFieldEmail.setColumns(10);
		
		lblNewLabel = new JLabel("Contraseña:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 6;
		panelDatos.add(lblNewLabel, gbc_lblNewLabel);
		
		textFieldContrasenia = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 6;
		panelDatos.add(textFieldContrasenia, gbc_textField);
		textFieldContrasenia.setColumns(10);
		
		lblNewLabel_1 = new JLabel("Foto de perfil:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 7;
		panelDatos.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		selectImageButton = new JButton("Seleccionar Imagen");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.WEST;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 7;
		panelDatos.add(selectImageButton, gbc_btnNewButton);
		
        imageLabel = new JLabel();
        
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
		gbc_textPane.gridy = 8;
		panelDatos.add(textPane, gbc_textPane);

		layeredPane = new JLayeredPane();
		GridBagConstraints gbc_layeredPane = new GridBagConstraints();
		gbc_layeredPane.gridheight = 3;
		gbc_layeredPane.gridwidth = 2;
		gbc_layeredPane.insets = new Insets(0, 0, 5, 0);
		gbc_layeredPane.fill = GridBagConstraints.BOTH;
		gbc_layeredPane.gridx = 0;
		gbc_layeredPane.gridy = 9;
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
		
		JPanel panelBotones = new JPanel();
		getContentPane().add(panelBotones, BorderLayout.SOUTH);
		panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 120, 20));

		this.btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarDatosUsuarios();
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
			DTUsuario dtUsuario = this.controladorUsuario.obtenerDTUsuario(nicknameUsuario);
			
		
			this.textFieldNickName.setText(nicknameUsuario);
			this.textFieldNombre.setText(dtUsuario.getNombre());
			this.textFieldApellido.setText(dtUsuario.getApellido());
			this.textFieldEmail.setText(dtUsuario.getEmail());
			this.textFieldContrasenia.setText(dtUsuario.getContrasenia());
			BufferedImage originalImage  = dtUsuario.getImagen();
			if (originalImage != null) {
				int newWidth = 100; // Ancho deseado
				int newHeight = 100; // Alto deseado
				Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
				this.textPane.setCaretPosition(textPane.getStyledDocument().getLength());
		        ImageIcon icono = new ImageIcon(scaledImage);
		        this.textPane.insertIcon(icono);
			}
			if (dtUsuario instanceof DTEmpresa) {
				tipoUsuario = "Empresa";
				DTEmpresa dtEmpresa = (DTEmpresa) dtUsuario;
				this.textAreaDescripcion.setText(dtEmpresa.getDescripcion());
				this.textFieldSitioWeb.setText(dtEmpresa.getSitioWeb());
				cambiarPanel(panelEmpresa);
			}
			if (dtUsuario instanceof DTPostulante) {
				tipoUsuario = "Postulante";
				DTPostulante dtPostulante = (DTPostulante) dtUsuario;
				this.textFieldNacionalidad.setText(dtPostulante.getNacionalidad());
				this.fechaNacimientoChooser.setDate(Date.from(dtPostulante.getFechaNacimiento().atStartOfDay(ZoneId.systemDefault()).toInstant()));
				cambiarPanel(panelPostulante);
			}

		}
		
	}
	
	public void modificarDatosUsuarios() {
		String nombre =  this.textFieldNombre.getText();
		String apelliido =  this.textFieldApellido.getText();
		String sitioWeb = this.textFieldSitioWeb.getText();
		Date fechaNac = this.fechaNacimientoChooser.getDate();
		String nacionalidadPos = this.textFieldNacionalidad.getText();
		String descripcionEmpresa = this.textAreaDescripcion.getText();
		String contrasenia = this.textFieldContrasenia.getText();
		if (checkFormulario(nombre, apelliido, fechaNac, nacionalidadPos, descripcionEmpresa, contrasenia)) {
		try {
			if (this.textPane.getText() == "") {
				fotoPerfilUsuario = null;
			}
			if (tipoUsuario.equals("Postulante")) {
				LocalDate fechaNacimiento = this.fechaNacimientoChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				controladorUsuario.editarPostulante(usuarioSeleccionado, nombre,  apelliido, fechaNacimiento, nacionalidadPos, fotoPerfilUsuario, contrasenia);
			} else if (tipoUsuario.equals("Empresa")) {
				controladorUsuario.editarEmpresa(usuarioSeleccionado, nombre, apelliido, sitioWeb, descripcionEmpresa, fotoPerfilUsuario, contrasenia);
			}
			
			JOptionPane.showMessageDialog(this, "Los datos se actualizaron con éxito",
					"Modificar datos de usuario", JOptionPane.INFORMATION_MESSAGE);
			limpiarTodosLosDatos();
		} catch (UsuarioNoExisteException e) {
			
		}
			
		}
		
	}
	
	
	@SuppressWarnings("exports")
	public void cambiarPanel(JPanel panel) {
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
	}
	
	public void obtenerImagen(File imagenPerfil) {
		try {
			BufferedImage originalImage  = ImageIO.read(imagenPerfil);
			int newWidth = 100; // Ancho deseado
			int newHeight = 100; // Alto deseado
			Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
			fotoPerfilUsuario = originalImage;
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
	
	public boolean checkFormulario(String nombre, String apellido, Date fechaNacPostulante, String nacionalidadPostulante, String descripEmpresa, String contrasenia) {
		if (nombre.isEmpty() || apellido.isEmpty() || contrasenia.isEmpty()) {
			JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Modificar Datos Usuario",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (tipoUsuario.equals("Postulante")) {
			if (nacionalidadPostulante.isEmpty()) {
				JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Modificar Datos Usuario",
						JOptionPane.ERROR_MESSAGE);
				return false;
			}
			if (fechaNacPostulante == null) {
				JOptionPane.showMessageDialog(this, "Debe ingresar una fecha valida",
						 "Modificar Datos Usuario", JOptionPane.ERROR_MESSAGE);
				return false;
			
		} else if (tipoUsuario.equals("Empresa")) {
			if (descripEmpresa.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Debe ingresar una fecha valida",
						 "Modificar Datos Usuario", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
	}
		return true;
	}

	
	public void limpiarTodosLosDatos() {
		
		this.usuarioSeleccionado = "";
		this.textFieldNickName.setText("");
		this.textFieldNombre.setText("");
		this.textFieldApellido.setText("");
		this.textFieldEmail.setText("");
		this.textFieldNacionalidad.setText("");
		this.textAreaDescripcion.setText("");
		this.textFieldSitioWeb.setText("");
		this.textFieldContrasenia.setText("");
		this.fechaNacimientoChooser.setDate(null);
		this.textPane.setText("");
		
	}
	
	 
	
}
