package presentacion;

import javax.swing.JInternalFrame;
import javax.swing.JTextField;

import logica.interfaces.IControladorUsuario;
import javax.swing.JLayeredPane;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.CardLayout;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class AltaUsuario extends JInternalFrame{
	private JTextField textField;
	private JTextField textFieldNombre;
	private JTextField textFielApellido;
	private JTextField textFieldEmail;
	private JTextField textFieldFechaNacimiento;
	private JTextField textFieldNacionalidad;
	private JTextField textFieldDescripcion;
	private JTextField textFieldcitioWeb;
	private JPanel panel;
	private JLayeredPane layeredPane;
	private JPanel panelPostulante;
	private JPanel panelEmpresa;
	public AltaUsuario() {
		setTitle("Alta de Usuario");
		getContentPane().setLayout(null);
		
		JLabel labelNickname = new JLabel("Nickname:");
		labelNickname.setBounds(4, 60, 49, 14);
		getContentPane().add(labelNickname);
		
		textField = new JTextField();
		textField.setBounds(63, 57, 275, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel labelNombre = new JLabel("Nombre:");
		labelNombre.setHorizontalAlignment(SwingConstants.TRAILING);
		labelNombre.setBounds(5, 87, 46, 14);
		getContentPane().add(labelNombre);
		
		JLabel labelSeleccionTipo = new JLabel("Seleccione tipo de usuario:");
		labelSeleccionTipo.setHorizontalAlignment(SwingConstants.TRAILING);
		labelSeleccionTipo.setBounds(7, 11, 136, 14);
		getContentPane().add(labelSeleccionTipo);
		
		JComboBox<String> comboBoxSeleccion = new JComboBox<String>();
		comboBoxSeleccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String seleccion = comboBoxSeleccion.getSelectedItem().toString();
				if(seleccion == "Postulante") {
					cambiarPanel(panelPostulante);
				}
				if(seleccion == "Empresa") {
					cambiarPanel(panelEmpresa);
				}
			}
		});
		comboBoxSeleccion.setModel(new DefaultComboBoxModel<String>(new String[] {"Postulante", "Empresa"}));
		comboBoxSeleccion.setBounds(153, 7, 112, 22);
		getContentPane().add(comboBoxSeleccion);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(63, 85, 276, 20);
		getContentPane().add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JLabel labelApellido = new JLabel("Apellido:");
		labelApellido.setHorizontalAlignment(SwingConstants.TRAILING);
		labelApellido.setBounds(7, 116, 46, 14);
		getContentPane().add(labelApellido);
		
		textFielApellido = new JTextField();
		textFielApellido.setBounds(63, 113, 275, 20);
		getContentPane().add(textFielApellido);
		textFielApellido.setColumns(10);
		
		JLabel labelEmail = new JLabel("Emal:");
		labelEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		labelEmail.setBounds(21, 147, 32, 14);
		getContentPane().add(labelEmail);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(63, 144, 275, 20);
		getContentPane().add(textFieldEmail);
		textFieldEmail.setColumns(10);
		
		panel = new JPanel();
		panel.setBounds(4, 172, 420, 169);
		getContentPane().add(panel);
		panel.setLayout(new CardLayout(0, 0));
		
		layeredPane = new JLayeredPane();
		panel.add(layeredPane, "name_837242802357300");
		
		panelPostulante = new JPanel();
		panelPostulante.setBounds(0, 0, 420, 169);
		layeredPane.add(panelPostulante);
		panelPostulante.setLayout(null);
		
		JLabel labelFechaNacimiento = new JLabel("Fecha de Nacimiento:");
		labelFechaNacimiento.setHorizontalAlignment(SwingConstants.RIGHT);
		labelFechaNacimiento.setBounds(32, 32, 109, 14);
		panelPostulante.add(labelFechaNacimiento);
		
		textFieldFechaNacimiento = new JTextField();
		textFieldFechaNacimiento.setBounds(151, 29, 86, 20);
		panelPostulante.add(textFieldFechaNacimiento);
		textFieldFechaNacimiento.setColumns(10);
		
		JLabel labelNacionalidad = new JLabel("Nacionalidad:");
		labelNacionalidad.setHorizontalAlignment(SwingConstants.RIGHT);
		labelNacionalidad.setBounds(71, 63, 71, 14);
		panelPostulante.add(labelNacionalidad);
		
		textFieldNacionalidad = new JTextField();
		textFieldNacionalidad.setBounds(151, 60, 86, 20);
		panelPostulante.add(textFieldNacionalidad);
		textFieldNacionalidad.setColumns(10);
		
		panelEmpresa = new JPanel();
		panelEmpresa.setBounds(0, 0, 420, 169);
		layeredPane.add(panelEmpresa);
		panelEmpresa.setLayout(null);
		
		JLabel labelDescripcion = new JLabel("Descripcion:");
		labelDescripcion.setHorizontalAlignment(SwingConstants.RIGHT);
		labelDescripcion.setBounds(76, 32, 66, 14);
		panelEmpresa.add(labelDescripcion);
		
		textFieldDescripcion = new JTextField();
		textFieldDescripcion.setBounds(151, 29, 86, 20);
		panelEmpresa.add(textFieldDescripcion);
		textFieldDescripcion.setColumns(10);
		
		JLabel labelCitioWeb = new JLabel("CitioWeb (opcional):");
		labelCitioWeb.setHorizontalAlignment(SwingConstants.RIGHT);
		labelCitioWeb.setBounds(42, 64, 103, 14);
		panelEmpresa.add(labelCitioWeb);
		
		textFieldcitioWeb = new JTextField();
		textFieldcitioWeb.setBounds(151, 60, 86, 20);
		panelEmpresa.add(textFieldcitioWeb);
		textFieldcitioWeb.setColumns(10);		
	}
	public void cambiarPanel(JPanel panel ) {
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
	}
	public JPanel getPanel() {
		return panel;
	}
	public JLayeredPane getLayeredPane() {
		return layeredPane;
	}
	public JPanel getPanelPostulante() {
		return panelPostulante;
	}
	public JPanel getPanelEmpresa() {
		return panelEmpresa;
	}
}
