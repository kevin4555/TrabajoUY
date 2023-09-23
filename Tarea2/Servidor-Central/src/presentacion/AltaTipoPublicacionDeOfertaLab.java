package presentacion;

import javax.swing.JInternalFrame;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import logica.interfaces.IControladorOferta;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Button;

import java.awt.CardLayout;

import javax.swing.JTextArea;
import java.awt.TextArea;
import javax.swing.JScrollPane;
import java.awt.Choice;
import javax.swing.ScrollPaneConstants;
import javax.swing.DropMode;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import excepciones.TipoPublicacionYaExisteException;

@SuppressWarnings("serial")
public class AltaTipoPublicacionDeOfertaLab extends JInternalFrame {

	// Controlador de usuarios que se utilizará para las acciones del JFrame
	private IControladorOferta controlOfertaLab;
	private JTextField textFieldNombre;
	private JTextField textFieldExposicion;
	private JTextField textFieldDuracion;
	private JTextField textFieldCosto;
	private JDateChooser dateChooser;
	private JTextArea textAreaDescripcion;
	private JButton btnCancelar;
	private JButton btnConfirmar;
	private JTextField textFieldDescripcion;

	/**
	 * Create the frame.
	 */
	public AltaTipoPublicacionDeOfertaLab(IControladorOferta icontOfeLab) {
		// Se inicializa con el controlador de usuarios
		controlOfertaLab = icontOfeLab;

		// Propiedades del JInternalFrame como dimensión, posición dentro del frame,
		// etc.
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setTitle("Registro de Tipo Publicacion de Oferta Laboral");
		setBounds(30, 30, 639, 470);
		getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel ubicacionEtiquetas = new JPanel();
		ubicacionEtiquetas.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(ubicacionEtiquetas, BorderLayout.WEST);
		ubicacionEtiquetas.setLayout(new GridLayout(6, 1, 50, 50));

		JLabel lblNombre = new JLabel("Nombre: ");
		ubicacionEtiquetas.add(lblNombre);

		JLabel lblDescripcion = new JLabel("Descripcion: ");
		ubicacionEtiquetas.add(lblDescripcion);

		JLabel lblExposición = new JLabel("Exposición: ");
		ubicacionEtiquetas.add(lblExposición);

		JLabel lblDuración = new JLabel("Duración: ");
		ubicacionEtiquetas.add(lblDuración);

		JLabel lblCosto = new JLabel("Costo: ");
		ubicacionEtiquetas.add(lblCosto);

		JLabel lblFechaPublicacion = new JLabel("Fecha de publicación: ");
		ubicacionEtiquetas.add(lblFechaPublicacion);

		JPanel ubicacionBotones = new JPanel();
		ubicacionBotones.setBorder(new EmptyBorder(5, 5, 5, 5));
		FlowLayout flowLayout = (FlowLayout) ubicacionBotones.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(120);
		getContentPane().add(ubicacionBotones, BorderLayout.SOUTH);

		btnConfirmar = new JButton("Confirmar");
		ubicacionBotones.add(btnConfirmar);
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cmdRegistrarTipoPublicacionOfertaLaboral(arg0);
			}
		});

		btnCancelar = new JButton("Cancelar");
		ubicacionBotones.add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// limpiarInformacion();
				setVisible(false);
			}
		});

		JPanel ubicacionTexto = new JPanel();
		ubicacionTexto.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(ubicacionTexto, BorderLayout.CENTER);
		ubicacionTexto.setLayout(new GridLayout(0, 1, 0, 50));

		textFieldNombre = new JTextField();
		ubicacionTexto.add(textFieldNombre);

		textFieldDescripcion = new JTextField();
		ubicacionTexto.add(textFieldDescripcion);
		textFieldDescripcion.setColumns(10);

		textFieldExposicion = new JTextField();
		ubicacionTexto.add(textFieldExposicion);
		textFieldExposicion.setColumns(10);

		textFieldDuracion = new JTextField();
		ubicacionTexto.add(textFieldDuracion);
		textFieldDuracion.setColumns(10);

		textFieldCosto = new JTextField();
		ubicacionTexto.add(textFieldCosto);
		textFieldCosto.setColumns(10);

		JPanel panel = new JPanel();
		ubicacionTexto.add(panel);
		panel.setLayout(new GridLayout(0, 5, 0, 0));

		dateChooser = new JDateChooser();
		panel.add(dateChooser);

	}

	protected void cmdRegistrarTipoPublicacionOfertaLaboral(ActionEvent arg0) {
		String nombre = textFieldNombre.getText();
		String descipcion = textFieldDescripcion.getText();
		String exposicion = textFieldExposicion.getText();
		String duracion = this.textFieldDuracion.getText();
		String costo = this.textFieldCosto.getText();
		Date fecha = this.dateChooser.getDate();
		String valorString = "";
		/*if (checkFormulario()) {
			try {
				Integer duracionFinal = Integer.parseInt(this.textFieldDuracion.getText());
				Float costoFinal = Float.parseFloat(this.textFieldCosto.getText());
				controlOfertaLab.altaTipoPublicacion(nombre, descipcion, exposicion, duracionFinal, costoFinal, fecha);

				// Muestro éxito de la operación
				JOptionPane.showMessageDialog(this, "La publicacion se creo con exito",
						"Registrar tipo de publicación", JOptionPane.INFORMATION_MESSAGE);

			} catch (TipoPublicacionYaExisteException e) {
				// Muestro error de registro
				JOptionPane.showMessageDialog(this, e.getMessage(), "Registrar tipo de publicación",
						JOptionPane.ERROR_MESSAGE);
			}

			// Limpio el internal frame antes de cerrar la ventana
			limpiarInformacion();
			setVisible(true);
		}*/

	}

	// Permite validar la información introducida en los campos e indicar
	// a través de un mensaje de error (JOptionPane) cuando algo sucede.
	// Este tipo de chequeos se puede realizar de otras formas y con otras librerías
	// de Java,
	// por ejemplo impidiendo que se escriban caracteres no numéricos al momento de
	// escribir en
	// en el campo de la cédula, o mostrando un mensaje de error apenas el foco pasa
	// a otro campo.
	private boolean checkFormulario() {

		String nombre = this.textFieldNombre.getText();
		String descipcion = this.textFieldDescripcion.getText();
		String exposicion = this.textFieldExposicion.getText();
		String duracion = this.textFieldDuracion.getText();
		String costo = this.textFieldCosto.getText();
		Date fecha = this.dateChooser.getDate();

		if (nombre.isEmpty() || descipcion.isEmpty() || exposicion.isEmpty() || duracion.isEmpty() || costo.isEmpty()) {
			JOptionPane.showMessageDialog(this, "No puede haber campos vacíos",
					"Registrar tipo de publicación", JOptionPane.ERROR_MESSAGE);
			return false;
		} if(fecha == null) {
			JOptionPane.showMessageDialog(this, "Debe ingresar una fecha valida",
					"Registrar tipo de publicación", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		try {
			Integer.parseInt(duracion);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "La duración debe ser un valor numérico",
					"Registrar tipo de publicación", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		if (Integer.parseInt(duracion) <= 0) {
			JOptionPane.showMessageDialog(this, "La duración debe ser mayor a cero",
					"Registrar tipo de publicación", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		try {
			Float.parseFloat(costo);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "El costo debe ser un valor numérico",
					"Registrar tipo de publicación", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(Float.parseFloat(costo) < 0) {
			JOptionPane.showMessageDialog(this, "El costo debe ser mayor o igual a cero",
					"Registrar tipo de publicación", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

	public void limpiarInformacion() {
		textFieldNombre.setText("");
		textFieldExposicion.setText("");
		textFieldDuracion.setText("");
		textFieldCosto.setText("");
		dateChooser.setDate(null);
		textFieldDescripcion.setText("");
	}
}
