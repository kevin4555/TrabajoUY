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

import logica.interfaces.IControladorUsuario;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ConsultarUsuario extends JInternalFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textFieldNombreOferta;
	private JTextField textFieldDescripcionOferta;
	private JTextField textFieldRemuneracion;
	private JTextField textFieldCiudad;
	private JTextField textFieldDepartamento;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private IControladorUsuario controladorUsuario;
	public ConsultarUsuario(IControladorUsuario contrUsuario) {
		controladorUsuario = contrUsuario;
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panelBotones = new JPanel();
		getContentPane().add(panelBotones, BorderLayout.SOUTH);
		panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 120, 20));
		
		JButton btnCerrar = new JButton("Cerrar");
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
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nicknameUsuario = comboBox.getSelectedItem().toString();
				
			}
		});
		try {
			ArrayList<String> listaUsuarios = contrUsuario.listaDeUsuarios();
			for(String nickUsuario : listaUsuarios) {
				comboBox.addItem(nickUsuario);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 1;
		panelDatos.add(comboBox, gbc_comboBox);
		
		JLabel lblNickname = new JLabel("Nickname:");
		GridBagConstraints gbc_lblNickname = new GridBagConstraints();
		gbc_lblNickname.anchor = GridBagConstraints.EAST;
		gbc_lblNickname.insets = new Insets(0, 0, 5, 5);
		gbc_lblNickname.gridx = 0;
		gbc_lblNickname.gridy = 2;
		panelDatos.add(lblNickname, gbc_lblNickname);
		
		textField = new JTextField();
		textField.setEditable(false);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 2;
		panelDatos.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre:");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.EAST;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 0;
		gbc_lblNombre.gridy = 3;
		panelDatos.add(lblNombre, gbc_lblNombre);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 3;
		panelDatos.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		JLabel lblApellido = new JLabel("Apellido:");
		GridBagConstraints gbc_lblApellido = new GridBagConstraints();
		gbc_lblApellido.anchor = GridBagConstraints.EAST;
		gbc_lblApellido.insets = new Insets(0, 0, 5, 5);
		gbc_lblApellido.gridx = 0;
		gbc_lblApellido.gridy = 4;
		panelDatos.add(lblApellido, gbc_lblApellido);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 0);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 1;
		gbc_textField_2.gridy = 4;
		panelDatos.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email:");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.EAST;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 0;
		gbc_lblEmail.gridy = 5;
		panelDatos.add(lblEmail, gbc_lblEmail);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.insets = new Insets(0, 0, 5, 0);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 1;
		gbc_textField_3.gridy = 5;
		panelDatos.add(textField_3, gbc_textField_3);
		textField_3.setColumns(10);
		
		JLayeredPane layeredPane = new JLayeredPane();
		GridBagConstraints gbc_layeredPane = new GridBagConstraints();
		gbc_layeredPane.gridheight = 2;
		gbc_layeredPane.gridwidth = 2;
		gbc_layeredPane.insets = new Insets(0, 0, 5, 5);
		gbc_layeredPane.fill = GridBagConstraints.BOTH;
		gbc_layeredPane.gridx = 0;
		gbc_layeredPane.gridy = 6;
		panelDatos.add(layeredPane, gbc_layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		JPanel panelEmpresa = new JPanel();
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
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.insets = new Insets(0, 0, 5, 0);
		gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_4.gridx = 1;
		gbc_textField_4.gridy = 0;
		panelEmpresa.add(textField_4, gbc_textField_4);
		textField_4.setColumns(10);
		
		JLabel lblCitioWeb = new JLabel("Citio Web:");
		GridBagConstraints gbc_lblCitioWeb = new GridBagConstraints();
		gbc_lblCitioWeb.anchor = GridBagConstraints.EAST;
		gbc_lblCitioWeb.insets = new Insets(0, 0, 0, 5);
		gbc_lblCitioWeb.gridx = 0;
		gbc_lblCitioWeb.gridy = 1;
		panelEmpresa.add(lblCitioWeb, gbc_lblCitioWeb);
		
		textField_5 = new JTextField();
		textField_5.setEditable(false);
		GridBagConstraints gbc_textField_5 = new GridBagConstraints();
		gbc_textField_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_5.gridx = 1;
		gbc_textField_5.gridy = 1;
		panelEmpresa.add(textField_5, gbc_textField_5);
		textField_5.setColumns(10);
		
		JPanel panelPostulante = new JPanel();
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
		
		textField_6 = new JTextField();
		textField_6.setEditable(false);
		GridBagConstraints gbc_textField_6 = new GridBagConstraints();
		gbc_textField_6.insets = new Insets(0, 0, 5, 0);
		gbc_textField_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_6.gridx = 1;
		gbc_textField_6.gridy = 0;
		panelPostulante.add(textField_6, gbc_textField_6);
		textField_6.setColumns(10);
		
		JLabel lblNacionalidad = new JLabel("Nacionalidad:");
		GridBagConstraints gbc_lblNacionalidad = new GridBagConstraints();
		gbc_lblNacionalidad.anchor = GridBagConstraints.EAST;
		gbc_lblNacionalidad.insets = new Insets(0, 0, 0, 5);
		gbc_lblNacionalidad.gridx = 0;
		gbc_lblNacionalidad.gridy = 1;
		panelPostulante.add(lblNacionalidad, gbc_lblNacionalidad);
		
		textField_7 = new JTextField();
		textField_7.setEditable(false);
		GridBagConstraints gbc_textField_7 = new GridBagConstraints();
		gbc_textField_7.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_7.gridx = 1;
		gbc_textField_7.gridy = 1;
		panelPostulante.add(textField_7, gbc_textField_7);
		textField_7.setColumns(10);
		
		JLabel lblOfertas = new JLabel("Ofertas Laborales:");
		GridBagConstraints gbc_lblOfertas = new GridBagConstraints();
		gbc_lblOfertas.anchor = GridBagConstraints.EAST;
		gbc_lblOfertas.insets = new Insets(0, 0, 5, 5);
		gbc_lblOfertas.gridx = 0;
		gbc_lblOfertas.gridy = 10;
		panelDatos.add(lblOfertas, gbc_lblOfertas);
		
		JComboBox<String> comboBox_1 = new JComboBox<String>();
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_1.gridx = 1;
		gbc_comboBox_1.gridy = 10;
		panelDatos.add(comboBox_1, gbc_comboBox_1);
		
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
		
		JLabel lblDescripcionOderta = new JLabel("Descripcion de Oferta:");
		GridBagConstraints gbc_lblDescripcionOderta = new GridBagConstraints();
		gbc_lblDescripcionOderta.anchor = GridBagConstraints.EAST;
		gbc_lblDescripcionOderta.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescripcionOderta.gridx = 0;
		gbc_lblDescripcionOderta.gridy = 12;
		panelDatos.add(lblDescripcionOderta, gbc_lblDescripcionOderta);
		
		textFieldDescripcionOferta = new JTextField();
		textFieldDescripcionOferta.setEditable(false);
		GridBagConstraints gbc_textFieldDescripcionOferta = new GridBagConstraints();
		gbc_textFieldDescripcionOferta.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldDescripcionOferta.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDescripcionOferta.gridx = 1;
		gbc_textFieldDescripcionOferta.gridy = 12;
		panelDatos.add(textFieldDescripcionOferta, gbc_textFieldDescripcionOferta);
		textFieldDescripcionOferta.setColumns(10);
		
		JLabel lblRemuneracion = new JLabel("Remuneracion");
		GridBagConstraints gbc_lblRemuneracion = new GridBagConstraints();
		gbc_lblRemuneracion.anchor = GridBagConstraints.EAST;
		gbc_lblRemuneracion.insets = new Insets(0, 0, 5, 5);
		gbc_lblRemuneracion.gridx = 0;
		gbc_lblRemuneracion.gridy = 13;
		panelDatos.add(lblRemuneracion, gbc_lblRemuneracion);
		
		textFieldRemuneracion = new JTextField();
		textFieldRemuneracion.setEditable(false);
		GridBagConstraints gbc_textFieldRemuneracion = new GridBagConstraints();
		gbc_textFieldRemuneracion.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldRemuneracion.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldRemuneracion.gridx = 1;
		gbc_textFieldRemuneracion.gridy = 13;
		panelDatos.add(textFieldRemuneracion, gbc_textFieldRemuneracion);
		textFieldRemuneracion.setColumns(10);
		
		JLabel lblCiudad = new JLabel("Ciudad");
		GridBagConstraints gbc_lblCiudad = new GridBagConstraints();
		gbc_lblCiudad.anchor = GridBagConstraints.EAST;
		gbc_lblCiudad.insets = new Insets(0, 0, 5, 5);
		gbc_lblCiudad.gridx = 0;
		gbc_lblCiudad.gridy = 14;
		panelDatos.add(lblCiudad, gbc_lblCiudad);
		
		textFieldCiudad = new JTextField();
		textFieldCiudad.setEditable(false);
		GridBagConstraints gbc_textFieldCiudad = new GridBagConstraints();
		gbc_textFieldCiudad.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldCiudad.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCiudad.gridx = 1;
		gbc_textFieldCiudad.gridy = 14;
		panelDatos.add(textFieldCiudad, gbc_textFieldCiudad);
		textFieldCiudad.setColumns(10);
		
		JLabel lblDepartamento = new JLabel("Departamento");
		GridBagConstraints gbc_lblDepartamento = new GridBagConstraints();
		gbc_lblDepartamento.anchor = GridBagConstraints.EAST;
		gbc_lblDepartamento.insets = new Insets(0, 0, 0, 5);
		gbc_lblDepartamento.gridx = 0;
		gbc_lblDepartamento.gridy = 15;
		panelDatos.add(lblDepartamento, gbc_lblDepartamento);
		
		textFieldDepartamento = new JTextField();
		textFieldDepartamento.setEditable(false);
		GridBagConstraints gbc_textFieldDepartamento = new GridBagConstraints();
		gbc_textFieldDepartamento.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDepartamento.gridx = 1;
		gbc_textFieldDepartamento.gridy = 15;
		panelDatos.add(textFieldDepartamento, gbc_textFieldDepartamento);
		textFieldDepartamento.setColumns(10);
		
		
	}

}
