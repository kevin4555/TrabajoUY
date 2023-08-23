package presentacion;

import javax.swing.JInternalFrame;



import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;



import excepciones.ColeccionEmpresaEsVaciaException;
import excepciones.ColeccionTipoPublicacionEsVaciaException;
import logica.interfaces.IControladorOferta;
import logica.interfaces.IControladorUsuario;

import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;


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
	//private JCalendar textFieldFechaAlta;
	private JLabel lblKeyword;
	private JButton btnSeleccionarKeyword;
	private JButton btnConfirmar;
	private JButton btnCancelar;

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
		
		/*textFieldFechaAlta = new JCalendar();
		ubicacionTexto.add(textFieldFechaAlta);*/
		
		JLabel lblKeyword = new JLabel("  Keyword");
		ubicacionEtiquetas.add(lblKeyword);
		
		btnSeleccionarKeyword = new JButton("Seleccionar Keywords");
		ubicacionTexto.add(btnSeleccionarKeyword);

		
		btnConfirmar = new JButton("Confirmar");
		ubicacionBotones.add(btnConfirmar);
		
		btnCancelar = new JButton("Cancelar");
		ubicacionBotones.add(btnCancelar);

        btnConfirmar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                cmdRegistroOfertaLaboralActionPerformed(arg0);
            }
        });

       /* GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
        gbc_btnAceptar.fill = GridBagConstraints.BOTH;
        gbc_btnAceptar.insets = new Insets(0, 0, 0, 5);
        gbc_btnAceptar.gridx = 1;
        gbc_btnAceptar.gridy = 3;
        getContentPane().add(btnAceptar, gbc_btnAceptar); */

        // Un botón (JButton) con un evento asociado que permite cerrar el formulario (solo ocultarlo).
        // Dado que antes de cerrar se limpia el formulario, se invoca un método reutilizable para ello. 
        //btnCancelar = new JButton("Cancelar");
       /* btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limpiarFormulario();
                setVisible(false);
            }
        });*/
        /*GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
        gbc_btnCancelar.fill = GridBagConstraints.BOTH;
        gbc_btnCancelar.gridx = 2;
        gbc_btnCancelar.gridy = 3;
        getContentPane().add(btnCancelar, gbc_btnCancelar); */
    }
    
    public void cargarEmpresas()
    {
    	DefaultComboBoxModel<String> model;
    	String[] empresas;
		try {
			empresas = (controlUsu.listarEmpresas()).toArray(new String[0]);
			model = new DefaultComboBoxModel<String>(empresas);
			comboBoxEmpresa.setModel(model); 
		} catch (ColeccionEmpresaEsVaciaException e) {
			e.printStackTrace();
		}
    }
    
    public void cargarTipoPublicaciones()
    {
    	DefaultComboBoxModel<String> model;
    	String[] tiposPublicaciones;
		try {
			tiposPublicaciones = (controlOferta.listarTipoDePublicaciones()).toArray(new String[0]);
			model = new DefaultComboBoxModel<String>(tiposPublicaciones);
			comboBoxTpoPublicacion.setModel(model); 
		} catch (ColeccionTipoPublicacionEsVaciaException e) {
			e.printStackTrace();
		}
    }
    

    // Este método es invocado al querer registrar un usuario, funcionalidad
    // provista por la operación del sistem registrarUsuario().
    // Previamente se hace una verificación de los campos, particularmente que no sean vacíos
    // y que la cédula sea un número. 
    // Tanto en caso de que haya un error (de verificación o de registro) o no, se despliega
    // un mensaje utilizando un panel de mensaje (JOptionPane).
    protected void cmdRegistroOfertaLaboralActionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub

        // Obtengo datos de los controles Swing
        String nombreOfertaLab = this.textFieldNombre.getText();
        String descripOfertaLab = this.textFieldDescripcion.getText();
        String remuneracionOfertaLab = this.textFieldRemuneracion.getText();
        String ciudadOfertaLab =  this.textFieldCiudad.getText();
        String departOfertaLab = this.textFieldDepartamento.getText();
        String nomTipoPublic = this.comboBoxTpoPublicacion.getSelectedItem().toString();
        String nicknameEmpresa = this.comboBoxEmpresa.getSelectedItem().toString();
        //String fechaAlta = this.textFieldFechaAlta.toString();
        String horaIniOfertaLab = this.textFieldHoraInicio.getText();
        String horaFinOfertaLab = this.textFieldHoraFin.getText();
        

        /*if (checkFormulario(nombreOfertaLab, descripOfertaLab, remuneracionOfertaLab, ciudadOfertaLab, departOfertaLab, fechaAlta, horaIniOfertaLab, horaFinOfertaLab)) {
            try {
                controlOferta.altaOfertaLaboral(nombreOfertaLab, descripOfertaLab, null, null, ABORT, ciudadOfertaLab, horaFinOfertaLab, null, null, nomTipoPublic, nicknameEmpresa);

                // Muestro éxito de la operación
                JOptionPane.showMessageDialog(this, "El Usuario se ha creado con éxito", "Registrar Usuario",
                        JOptionPane.INFORMATION_MESSAGE);

            } catch (UsuarioRepetidoException e) {
                // Muestro error de registro
                JOptionPane.showMessageDialog(this, e.getMessage(), "Registrar Usuario", JOptionPane.ERROR_MESSAGE);
            }

            // Limpio el internal frame antes de cerrar la ventana
            limpiarFormulario();
            setVisible(false);*/
        }
    

    // Permite validar la información introducida en los campos e indicar
    // a través de un mensaje de error (JOptionPane) cuando algo sucede.
    // Este tipo de chequeos se puede realizar de otras formas y con otras librerías de Java, 
    // por ejemplo impidiendo que se escriban caracteres no numéricos al momento de escribir en
    // en el campo de la cédula, o mostrando un mensaje de error apenas el foco pasa a otro campo.
    private boolean checkFormulario(String nombreOfertaLab, String descripOfertaLab, String remuneracionOfertaLab, String ciudadOfertaLab, String departOfertaLab, String fechaAlta, 
    		String horaIniOfertaLab, String horaFinOfertaLab) {

        if (nombreOfertaLab.isEmpty() || descripOfertaLab.isEmpty() || remuneracionOfertaLab.isEmpty() || ciudadOfertaLab.isEmpty() 
        		|| departOfertaLab.isEmpty() || fechaAlta.isEmpty() || horaIniOfertaLab.isEmpty() || horaFinOfertaLab.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Registrar Oferta Laboral",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try {
            Double.parseDouble(remuneracionOfertaLab);
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
    	//textFieldFechaAlta.setDate("");
    	textFieldHoraInicio.setText("");
    	textFieldHoraFin.setText("");
    }
}
