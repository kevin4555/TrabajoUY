package presentacion;

import com.toedter.calendar.JDateChooser;
import excepciones.PaquetePublicacionYaExisteException;
import excepciones.TipoPublicacionNoExisteException;
import excepciones.TipoPublicacionYaExisteException;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import logica.interfaces.IControladorOferta;

@SuppressWarnings("serial")
public class RegistrarPaquete extends JInternalFrame {

	private JTextField textFieldNombre;
	private JTextField textFieldValidez;
	private JTextField textFieldDescuento;

	private JDateChooser fechaAltaChooser;
	private IControladorOferta controladorOferta;
	private JScrollPane scrollPane;
	private JTextPane textAreaDescripcion;
	private JButton btnCancelar;
	private JButton btnConfirmar;
	private JPanel panelDatos;
	private JLabel lblNewLabel_2;
	private JButton selectImageButton;
	private BufferedImage fotoPaquete = null;
	private GridBagConstraints gbc_textField;
	private JTextPane textPane;

	public RegistrarPaquete(IControladorOferta contralodorOferta) {
		this.controladorOferta = contralodorOferta;

		setIconifiable(true);
		setResizable(true);
		setMaximizable(true);

		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setTitle("Registrar Paquete");

		setBounds(100, 100, 594, 502);
		JPanel panelBotones = new JPanel();
		getContentPane().add(panelBotones, BorderLayout.SOUTH);
		panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 120, 20));

		this.btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (RegistrarPaquete()) {
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
		gbl_panelDatos.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelDatos.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_panelDatos.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panelDatos.setLayout(gbl_panelDatos);

		JLabel lblNombre = new JLabel("Nombre");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.EAST;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 0;
		gbc_lblNombre.gridy = 1;
		panelDatos.add(lblNombre, gbc_lblNombre);

		this.textFieldNombre = new JTextField();
		this.textFieldNombre.setEditable(true);
		GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
		gbc_textFieldNombre.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNombre.gridx = 1;
		gbc_textFieldNombre.gridy = 1;
		panelDatos.add(textFieldNombre, gbc_textFieldNombre);
		textFieldNombre.setColumns(10);

		JLabel lblValidez = new JLabel("Período de validez");
		GridBagConstraints gbc_lblValidez = new GridBagConstraints();
		gbc_lblValidez.anchor = GridBagConstraints.EAST;
		gbc_lblValidez.insets = new Insets(0, 0, 5, 5);
		gbc_lblValidez.gridx = 0;
		gbc_lblValidez.gridy = 2;
		panelDatos.add(lblValidez, gbc_lblValidez);

		this.textFieldValidez = new JTextField();
		this.textFieldValidez.setEditable(true);
		GridBagConstraints textFieldValidez = new GridBagConstraints();
		textFieldValidez.insets = new Insets(0, 0, 5, 0);
		textFieldValidez.fill = GridBagConstraints.HORIZONTAL;
		textFieldValidez.gridx = 1;
		textFieldValidez.gridy = 2;
		panelDatos.add(this.textFieldValidez, textFieldValidez);
		this.textFieldValidez.setColumns(10);

		JLabel lblDescuento = new JLabel("Descuento");
		GridBagConstraints gbc_lblDescuento = new GridBagConstraints();
		gbc_lblDescuento.anchor = GridBagConstraints.EAST;
		gbc_lblDescuento.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescuento.gridx = 0;
		gbc_lblDescuento.gridy = 3;
		panelDatos.add(lblDescuento, gbc_lblDescuento);

		this.textFieldDescuento = new JTextField();
		this.textFieldDescuento.setEditable(true);
		GridBagConstraints gbc_textFieldDescuento = new GridBagConstraints();
		gbc_textFieldDescuento.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldDescuento.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDescuento.gridx = 1;
		gbc_textFieldDescuento.gridy = 3;
		panelDatos.add(this.textFieldDescuento, gbc_textFieldDescuento);
		this.textFieldDescuento.setColumns(10);

		JLabel lblDescripcion = new JLabel("Descripcion");
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

		textAreaDescripcion = new JTextPane();
		textAreaDescripcion.setEditable(true);
		scrollPane.setViewportView(textAreaDescripcion);

		lblNewLabel_2 = new JLabel("Imagen paquete:");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 4;
		panelDatos.add(lblNewLabel_2, gbc_lblNewLabel_2);

		selectImageButton = new JButton("Seleccionar Imagen");
		JLabel imageLabel = new JLabel();

		gbc_textField = new GridBagConstraints();
		gbc_textField.anchor = GridBagConstraints.WEST;
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 4;
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
		gbc_textPane.gridy = 5;
		panelDatos.add(textPane, gbc_textPane);
		textPane.setEditable(false);

		JLabel lblFechaAlta = new JLabel("Fecha de alta:");
		GridBagConstraints gbc_lblFechaAlta = new GridBagConstraints();
		gbc_lblFechaAlta.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaAlta.anchor = GridBagConstraints.EAST;
		gbc_lblFechaAlta.gridx = 0;
		gbc_lblFechaAlta.gridy = 6;
		panelDatos.add(lblFechaAlta, gbc_lblFechaAlta);

		this.fechaAltaChooser = new JDateChooser();
		GridBagConstraints gbc_FechaAlta = new GridBagConstraints();
		gbc_FechaAlta.insets = new Insets(0, 0, 5, 0);
		gbc_FechaAlta.fill = GridBagConstraints.HORIZONTAL;
		gbc_FechaAlta.gridx = 1;
		gbc_FechaAlta.gridy = 6;
		panelDatos.add(fechaAltaChooser, gbc_FechaAlta);

	}

	protected boolean RegistrarPaquete() {
		String nombre = this.textFieldNombre.getText();
		String descripcion = this.textAreaDescripcion.getText();
		String descuento = this.textFieldDescuento.getText();
		String validez = this.textFieldValidez.getText();

		if (checkFormulario()) {
			try {
				if (this.textPane.getText() == "") {
					fotoPaquete = null;
				}
				LocalDate fechaAltaPaquete = this.fechaAltaChooser.getDate().toInstant().atZone(ZoneId.systemDefault())
						.toLocalDate();
				this.controladorOferta.registrarPaquete(nombre, descripcion, Integer.parseInt(validez),
						Float.parseFloat(descuento), fotoPaquete, fechaAltaPaquete, null);
				JOptionPane.showMessageDialog(this, "El paquete se ha creado con éxito", "Registrar Paquete",
						JOptionPane.INFORMATION_MESSAGE);
				return true;
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (PaquetePublicacionYaExisteException e) {
				JOptionPane.showMessageDialog(this, "Ya existe registrado un paquete con el nombre " + nombre,
						"Registrar Paquete", JOptionPane.ERROR_MESSAGE);
			} catch (TipoPublicacionYaExisteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TipoPublicacionNoExisteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		};

		return false;
	}

	private boolean checkFormulario() {
		if (this.textAreaDescripcion.getText().isEmpty() || this.textFieldNombre.getText().isEmpty()
				|| this.textFieldDescuento.getText().isEmpty() || this.textFieldValidez.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Registrar Paquete",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (this.fechaAltaChooser.getDate() == null) {
			JOptionPane.showMessageDialog(this, "Debe ingresar una Fecha de Alta", "Registrar Paquete",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		try {
			if (Float.parseFloat(this.textFieldDescuento.getText()) <= 0 || Float.parseFloat(this.textFieldDescuento.getText()) > 100) {
				JOptionPane.showMessageDialog(this, "El descuento(en porcentaje) debe ser un número entre 1 y 100", "Registrar Paquete",
						JOptionPane.ERROR_MESSAGE);
				return false;
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "El descuento(en porcentaje) debe ser un número entre 1 y 100", "Registrar Paquete",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		try {
			if (Integer.parseInt(this.textFieldValidez.getText()) <= 0) {
				JOptionPane.showMessageDialog(this, "El período de validez(en días) debe ser un número entero mayor a cero", "Registrar Paquete",
						JOptionPane.ERROR_MESSAGE);
				return false;
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "El período de validez(en días) debe ser un número entero mayor a cero", "Registrar Paquete",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;

	}

	public void obtenerImagen(File imagenPerfil) {
		try {
			BufferedImage originalImage = ImageIO.read(imagenPerfil);
			int newWidth = 100; // Ancho deseado
			int newHeight = 100; // Alto deseado
			Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
			fotoPaquete = originalImage;
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

	

	public void limpiarTodosLosDatos() {
		this.textFieldValidez.setText("");
		this.textFieldNombre.setText("");
		this.textFieldDescuento.setText("");
		this.fechaAltaChooser.setDate(null);
		this.textAreaDescripcion.setText("");
		this.textPane.setText("");

	}
}
