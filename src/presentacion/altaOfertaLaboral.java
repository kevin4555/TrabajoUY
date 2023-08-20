package presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;

public class altaOfertaLaboral extends JFrame {

	private JPanel ubicacionBotones;
	private JPanel ubicacionEtiquetas;
	private JPanel ubicacionTexto;
	private JLabel lblEmpresa;
	private JComboBox comboBoxEmpresa;
	private JLabel lblTipoPublicacion;
	private JComboBox comboBoxTpoPublicacion;
	private JLabel lblNombre;
	private JTextField textFieldNombre;
	private JLabel lblDescripcion;
	private JTextField textFieldDescripcion;
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
	private JTextField textFieldFechaAlta;
	private JLabel lblKeyword;
	private JButton btnSeleccionarKeyword;
	private JButton btnConfirmar;
	private JButton btnCancelar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					altaOfertaLaboral frame = new altaOfertaLaboral();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public altaOfertaLaboral() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 594, 502);
		
		ubicacionBotones = new JPanel();
		ubicacionBotones.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		ubicacionEtiquetas = new JPanel();
		ubicacionEtiquetas.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		ubicacionTexto = new JPanel();
		ubicacionTexto.setBorder(new EmptyBorder(5, 5, 5, 5));

		add(ubicacionBotones, BorderLayout.SOUTH); // Establezco ubicacion de los botones al sur del Panle
		ubicacionBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 120, 20));
		
		add(ubicacionEtiquetas, BorderLayout.WEST); // Establezco ubicacion de los botones al oeste del Panel
		ubicacionEtiquetas.setLayout(new GridLayout(11, 1, 10, 10));
		
		add(ubicacionTexto, BorderLayout.CENTER); // Establezco ubicacion de los botones al centro del Panel
		ubicacionTexto.setLayout(new GridLayout(11, 1, 10, 10));
		
		
		lblEmpresa = new JLabel("  Empresa");
		ubicacionEtiquetas.add(lblEmpresa);
		
		comboBoxEmpresa = new JComboBox();
		ubicacionTexto.add(comboBoxEmpresa);
		
		JLabel lblTipoPublicacion = new JLabel("  Tipo de publicación");
		ubicacionEtiquetas.add(lblTipoPublicacion);
		
		comboBoxTpoPublicacion = new JComboBox();
		ubicacionTexto.add(comboBoxTpoPublicacion);
		
		lblNombre = new JLabel("  Nombre");
		ubicacionEtiquetas.add(lblNombre);
		
		textFieldNombre = new JTextField();
		ubicacionTexto.add(textFieldNombre);
		
		lblDescripcion = new JLabel("  Descripción");
		ubicacionEtiquetas.add(lblDescripcion);
		
		textFieldDescripcion = new JTextField();
		ubicacionTexto.add(textFieldDescripcion);
		
		lblHoraInicio = new JLabel("  Hora Inicio");
		ubicacionEtiquetas.add(lblHoraInicio);
		
		textFieldHoraInicio = new JTextField();
		ubicacionTexto.add(textFieldHoraInicio);
		
		lblHoraFin = new JLabel("  Hora Fin");
		ubicacionEtiquetas.add(lblHoraFin);
		
		textFieldHoraFin = new JTextField();
		ubicacionTexto.add(textFieldHoraFin);
		
		lblRemuneracin = new JLabel("  Remuneración");
		ubicacionEtiquetas.add(lblRemuneracin);
		
		textFieldRemuneracion = new JTextField();
		ubicacionTexto.add(textFieldRemuneracion);
		
		lblCiudad = new JLabel("  Ciudad");
		ubicacionEtiquetas.add(lblCiudad);
		
		textFieldCiudad = new JTextField();
		ubicacionTexto.add(textFieldCiudad);
		
		lblDepartamento = new JLabel("  Departamento");
		ubicacionEtiquetas.add(lblDepartamento);
		
		textFieldDepartamento = new JTextField();
		ubicacionTexto.add(textFieldDepartamento);
		
		lblFechaDeAlta = new JLabel("  Fecha de Alta");
		ubicacionEtiquetas.add(lblFechaDeAlta);
		
		textFieldFechaAlta = new JTextField();
		ubicacionTexto.add(textFieldFechaAlta);
		
		JLabel lblKeyword = new JLabel("  Keyword");
		ubicacionEtiquetas.add(lblKeyword);
		
		btnSeleccionarKeyword = new JButton("Seleccionar Keywords");
		ubicacionTexto.add(btnSeleccionarKeyword);

		
		btnConfirmar = new JButton("Confirmar");
		ubicacionBotones.add(btnConfirmar);
		
		btnCancelar = new JButton("Cancelar");
		ubicacionBotones.add(btnCancelar);
	}

}
