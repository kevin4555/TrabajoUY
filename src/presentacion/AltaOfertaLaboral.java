package presentacion;

import javax.swing.JInternalFrame;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import logica.interfaces.IControladorOferta;
import logica.interfaces.IControladorUsuario;

import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;

import excepciones.KeywordNoExisteException;
import excepciones.OfertaLaboralNoExisteException;
import excepciones.OfertaLaboralYaExisteException;
import excepciones.TipoPublicacionNoExisteException;
import excepciones.TipoPublicacionYaExisteException;
import excepciones.UsuarioNoExisteException;

@SuppressWarnings("serial")
public class AltaOfertaLaboral extends JInternalFrame {

	// Controlador de usuarios que se utilizará para las acciones del JFrame
	private IControladorOferta controlOferta;
	private IControladorUsuario controlUsu;

	// Los componentes gráficos se agregan como atributos de la clase
	// para facilitar su acceso desde diferentes métodos de la misma.
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

	/**
	 * Create the frame.
	 */
	public AltaOfertaLaboral(IControladorOferta icontOfer, IControladorUsuario icontUsu) {
		// Se inicializa con el controlador de usuarios
		controlOferta = icontOfer;
		controlUsu = icontUsu;

		// Propiedades del JInternalFrame como dimensión, posición dentro del frame,
		// etc.
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setTitle("Registrar Oferta Laboral");
		setBounds(30, 30, 600, 502);

		ubicacionBotones = new JPanel();
		ubicacionBotones.setBorder(new EmptyBorder(5, 5, 5, 5));

		ubicacionEtiquetas = new JPanel();
		ubicacionEtiquetas.setBorder(new EmptyBorder(5, 5, 5, 5));

		ubicacionTexto = new JPanel();
		ubicacionTexto.setBorder(new EmptyBorder(5, 5, 5, 5));

		getContentPane().add(ubicacionBotones, BorderLayout.SOUTH); // Establezco ubicacion de los botones al sur del
																	// Panle
		ubicacionBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 120, 20));

		getContentPane().add(ubicacionEtiquetas, BorderLayout.WEST); // Establezco ubicacion de los botones al oeste del
																		// Panel
		ubicacionEtiquetas.setLayout(new GridLayout(11, 1, 10, 10));

		getContentPane().add(ubicacionTexto, BorderLayout.CENTER); // Establezco ubicacion de los botones al centro del
																	// Panel
		ubicacionTexto.setLayout(new GridLayout(11, 1, 0, 5));

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

		dateChooser = new JDateChooser();
		ubicacionTexto.add(dateChooser);

		JLabel lblKeyword = new JLabel("  Keyword");
		ubicacionEtiquetas.add(lblKeyword);

		// JList<String> listaKeyword = new JList<>();
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(listaKeyword);
		ubicacionTexto.add(scrollPane);
		listaKeyword.setVisibleRowCount(4);

		btnConfirmar = new JButton("Confirmar");
		ubicacionBotones.add(btnConfirmar);

		btnCancelar = new JButton("Cancelar");
		ubicacionBotones.add(btnCancelar);

		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarFormulario();
				setVisible(false);
			}
		});

		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cmdRegistroOfertaLaboralActionPerformed(arg0);
			}
		});

	}

	@SuppressWarnings("unchecked")
	public void cargarEmpresas() {
		DefaultComboBoxModel<String> model;
		String[] empresas = (controlUsu.listarEmpresas()).toArray(new String[0]);
		model = new DefaultComboBoxModel<String>(empresas);
		comboBoxEmpresa.setModel(model);
	}

	@SuppressWarnings("unchecked")
	public void cargarTipoPublicaciones() {
		DefaultComboBoxModel<String> model;
		String[] tiposPublicaciones = (controlOferta.listarTipoDePublicaciones()).toArray(new String[0]);
		model = new DefaultComboBoxModel<String>(tiposPublicaciones);
		comboBoxTpoPublicacion.setModel(model);
	}

	public void cargarKeywords() {
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		listaKeyword.setModel(listModel);

		// Recorrer el contenido del ArrayList
		for (int i = 0; i < controlOferta.listarKeywords().size(); i++) {
			// Añadir cada elemento del ArrayList en el modelo de la lista
			listModel.addElement(controlOferta.listarKeywords().get(i));
		}
	}

	// }

	// Este método es invocado al querer registrar un usuario, funcionalidad
	// provista por la operación del sistem registrarUsuario().
	// Previamente se hace una verificación de los campos, particularmente que no
	// sean vacíos
	// y que la cédula sea un número.
	// Tanto en caso de que haya un error (de verificación o de registro) o no, se
	// despliega
	// un mensaje utilizando un panel de mensaje (JOptionPane).
	protected void cmdRegistroOfertaLaboralActionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

		// Obtengo datos de los controles Swing
		String nombreOfertaLab = this.textFieldNombre.getText();
		String descripOfertaLab = this.textFieldDescripcion.getText();
		String remuneracionOfertaLab = this.textFieldRemuneracion.getText();
		String ciudadOfertaLab = this.textFieldCiudad.getText();
		String departOfertaLab = this.textFieldDepartamento.getText();
		String nomTipoPublic = this.comboBoxTpoPublicacion.getSelectedItem().toString();
		String nicknameEmpresa = this.comboBoxEmpresa.getSelectedItem().toString();
		Date fechaAlta = this.dateChooser.getDate();

		String horaIniOfertaLab = this.textFieldHoraInicio.getText();
		String horaFinOfertaLab = this.textFieldHoraFin.getText();
		ArrayList<String> keywordSeleccionadas = new ArrayList<String>();
		if (!this.listaKeyword.getSelectedValuesList().isEmpty()) {
			keywordSeleccionadas = (ArrayList<String>) this.listaKeyword.getSelectedValuesList();
		}
		
		

		if (checkFormulario(nombreOfertaLab, descripOfertaLab, remuneracionOfertaLab, ciudadOfertaLab, departOfertaLab,
				horaIniOfertaLab, horaFinOfertaLab, fechaAlta)) {
			try {
				controlOferta.altaOfertaLaboral(nombreOfertaLab, descripOfertaLab, horaIniOfertaLab, horaFinOfertaLab,
						Float.parseFloat(remuneracionOfertaLab), ciudadOfertaLab, departOfertaLab, fechaAlta,
						controlOferta.obtenerTipoPublicacion(nomTipoPublic));
				controlUsu.obtenerEmpresa(nicknameEmpresa)
						.agregarOferta(controlOferta.obtenerOfertaLaboral(nombreOfertaLab));
				controlOferta.agregarKeywordEnOfertaLaboral(keywordSeleccionadas, nombreOfertaLab);
				// falta asociar keywords.
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
			} catch (OfertaLaboralNoExisteException e) {
				// no imprime nada
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Trabajo.uy", JOptionPane.ERROR_MESSAGE);
			} catch (TipoPublicacionYaExisteException e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Trabajo.uy", JOptionPane.ERROR_MESSAGE);
			} catch (KeywordNoExisteException e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Trabajo.uy", JOptionPane.ERROR_MESSAGE);
			} catch (java.lang.ClassCastException e) {
				JOptionPane.showMessageDialog(this, "Debe ingresar una fecha valida", "Trabajo.uy", JOptionPane.ERROR_MESSAGE);

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
			Date fechaAlta) {

		if (nombreOfertaLab.isEmpty() || descripOfertaLab.isEmpty() || remuneracionOfertaLab.isEmpty()
				|| ciudadOfertaLab.isEmpty() || departOfertaLab.isEmpty() || horaIniOfertaLab.isEmpty()
				|| horaFinOfertaLab.isEmpty()) {
			JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Registrar Oferta Laboral",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (fechaAlta == null) {
			JOptionPane.showMessageDialog(this, "Debe ingresar una fecha valida",
					"Registrar tipo de publicación de oferta laboral", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		try {
			Float.parseFloat(remuneracionOfertaLab);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "La remunaracion debe ser un numero", "Registrar Oferta Laboral",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

	// Permite borrar el contenido de un formulario antes de cerrarlo.
	// Recordar que las ventanas no se destruyen, sino que simplemente
	// se ocultan, por lo que conviene borrar la información para que
	// no aparezca al mostrarlas nuevamente.
	private void limpiarFormulario() {
		textFieldNombre.setText("");
		textFieldDescripcion.setText("");
		textFieldRemuneracion.setText("");
		textFieldCiudad.setText("");
		textFieldDepartamento.setText("");
		dateChooser.setDate(null);
		textFieldHoraInicio.setText("");
		textFieldHoraFin.setText("");
	}
}
