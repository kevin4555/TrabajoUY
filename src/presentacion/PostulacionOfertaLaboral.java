package presentacion;

import javax.swing.JInternalFrame;



import javax.swing.JFrame;
import javax.swing.JLabel;
import logica.interfaces.IControladorOferta;
import logica.interfaces.IControladorUsuario;
import javax.swing.DefaultComboBoxModel;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;

import excepciones.ColeccionEmpresaEsVaciaException;
import javax.swing.JTextField;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class PostulacionOfertaLaboral extends JInternalFrame {

    // Controlador de usuarios que se utilizará para las acciones del JFrame
    private IControladorOferta controlOfertaLab;
    private IControladorUsuario controlUsuarioLab;
    private JTextField textFieldNombre;
    private JTextField textFieldDescripcion;
    private JTextField textFieldHoraInicio;
    private JTextField textFieldHoraFin;
    private JTextField textFieldRemuneracion;
    private JTextField textFieldCiudad;
    private JTextField textFieldDepartamento;
    private JTextField textFieldFechaAlta;
    private JComboBox comboBoxEmpresasRegistradas;
    private JComboBox comboBoxOfertasLaborales;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;

    /**
     * Create the frame.
     */
    public PostulacionOfertaLaboral(IControladorOferta icontOfeLab, IControladorUsuario icontUsuLab) {
        // Se inicializa con el controlador de usuarios
        controlOfertaLab = icontOfeLab;
        controlUsuarioLab = icontUsuLab;
        
        // Propiedades del JInternalFrame como dimensión, posición dentro del frame, etc.
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Postulación a Oferta Laboral");
        setBounds(30, 30, 508, 380);
        
        
        JPanel ubicacionNorte = new JPanel();
        getContentPane().add(ubicacionNorte, BorderLayout.NORTH);
        ubicacionNorte.setLayout(new GridLayout(1, 2, 10, 1));
        
        JPanel ubicacionEtiquetas = new JPanel();
        ubicacionNorte.add(ubicacionEtiquetas);
        ubicacionEtiquetas.setLayout(new GridLayout(2, 1, 0, 2));
        
        JLabel lblEmpresas = new JLabel("Empresas");
        lblEmpresas.setHorizontalAlignment(SwingConstants.CENTER);
        ubicacionEtiquetas.add(lblEmpresas);
        
        JLabel lblOfertasLaborales = new JLabel("Ofertas Laborales");
        lblOfertasLaborales.setHorizontalAlignment(SwingConstants.CENTER);
        ubicacionEtiquetas.add(lblOfertasLaborales);
        lblOfertasLaborales.setVisible(false);
        
        JPanel ubicacionComboBox = new JPanel();
        ubicacionNorte.add(ubicacionComboBox);
        ubicacionComboBox.setLayout(new GridLayout(2, 1, 0, 2));
        
        JComboBox comboBoxEmpresasRegistradas = new JComboBox();
        ubicacionComboBox.add(comboBoxEmpresasRegistradas);
        
        JComboBox comboBoxOfertasLaborales = new JComboBox();
        ubicacionComboBox.add(comboBoxOfertasLaborales);
        comboBoxOfertasLaborales.setVisible(false);
        
        
        
        JPanel ubicacionCentro = new JPanel();
        getContentPane().add(ubicacionCentro, BorderLayout.CENTER);
        ubicacionCentro.setLayout(new GridLayout(0, 2, 5, 1));
        
        JPanel ubicacionDatosOferta = new JPanel();
        ubicacionCentro.add(ubicacionDatosOferta);
        ubicacionDatosOferta.setLayout(new GridLayout(0, 2, 0, 0));
        ubicacionDatosOferta.setVisible(false);
        
        JPanel ubicacionEtiquetasDTOF = new JPanel();
        ubicacionDatosOferta.add(ubicacionEtiquetasDTOF);
        ubicacionEtiquetasDTOF.setLayout(new GridLayout(8, 1, 0, 0));
        
        JLabel lblNombre = new JLabel("  Nombre");
        lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
        ubicacionEtiquetasDTOF.add(lblNombre);
        
        JLabel lblDescripcion = new JLabel("  Descripción");
        lblDescripcion.setHorizontalAlignment(SwingConstants.CENTER);
        ubicacionEtiquetasDTOF.add(lblDescripcion);
        
        JLabel lblHoraInicio = new JLabel("  Hora Inicio");
        lblHoraInicio.setHorizontalAlignment(SwingConstants.CENTER);
        ubicacionEtiquetasDTOF.add(lblHoraInicio);
        
        JLabel lblHoraFin = new JLabel("  Hora Fin");
        lblHoraFin.setHorizontalAlignment(SwingConstants.CENTER);
        ubicacionEtiquetasDTOF.add(lblHoraFin);
        
        JLabel lblRemuneracin = new JLabel("  Remuneración");
        lblRemuneracin.setHorizontalAlignment(SwingConstants.CENTER);
        ubicacionEtiquetasDTOF.add(lblRemuneracin);
        
        JLabel lblCiudad = new JLabel("  Ciudad");
        lblCiudad.setHorizontalAlignment(SwingConstants.CENTER);
        ubicacionEtiquetasDTOF.add(lblCiudad);
        
        JLabel lblDepartamento = new JLabel("  Departamento");
        lblDepartamento.setHorizontalAlignment(SwingConstants.CENTER);
        ubicacionEtiquetasDTOF.add(lblDepartamento);
        
        JLabel lblFechaDeAlta = new JLabel("  Fecha de Alta");
        lblFechaDeAlta.setHorizontalAlignment(SwingConstants.CENTER);
        ubicacionEtiquetasDTOF.add(lblFechaDeAlta);
        
        JPanel ubicacionTextos = new JPanel();
        ubicacionDatosOferta.add(ubicacionTextos);
        ubicacionTextos.setLayout(new GridLayout(8, 1, 0, 0));
        
        textFieldNombre = new JTextField();
        textFieldNombre.setEnabled(false);
        textFieldNombre.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldNombre.setEditable(false);
        ubicacionTextos.add(textFieldNombre);
        
        textFieldDescripcion = new JTextField();
        textFieldDescripcion.setEnabled(false);
        textFieldDescripcion.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldDescripcion.setEditable(false);
        ubicacionTextos.add(textFieldDescripcion);
        
        textFieldHoraInicio = new JTextField();
        textFieldHoraInicio.setEnabled(false);
        textFieldHoraInicio.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldHoraInicio.setEditable(false);
        ubicacionTextos.add(textFieldHoraInicio);
        
        textFieldHoraFin = new JTextField();
        textFieldHoraFin.setEnabled(false);
        textFieldHoraFin.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldHoraFin.setEditable(false);
        ubicacionTextos.add(textFieldHoraFin);
        
        textFieldRemuneracion = new JTextField();
        textFieldRemuneracion.setEnabled(false);
        textFieldRemuneracion.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldRemuneracion.setEditable(false);
        ubicacionTextos.add(textFieldRemuneracion);
        
        textFieldCiudad = new JTextField();
        textFieldCiudad.setEnabled(false);
        textFieldCiudad.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldCiudad.setEditable(false);
        ubicacionTextos.add(textFieldCiudad);
        
        textFieldDepartamento = new JTextField();
        textFieldDepartamento.setEnabled(false);
        textFieldDepartamento.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldDepartamento.setEditable(false);
        ubicacionTextos.add(textFieldDepartamento);
        
        textFieldFechaAlta = new JTextField();
        textFieldFechaAlta.setEnabled(false);
        textFieldFechaAlta.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldFechaAlta.setEditable(false);
        ubicacionTextos.add(textFieldFechaAlta);
        
        JPanel PostulanteEIngreso = new JPanel();
        ubicacionCentro.add(PostulanteEIngreso);
        PostulanteEIngreso.setLayout(new GridLayout(0, 2, 0, 0));
        PostulanteEIngreso.setVisible(false);
        
        JPanel ubicacionEtiqueta = new JPanel();
        PostulanteEIngreso.add(ubicacionEtiqueta);
        ubicacionEtiqueta.setLayout(new GridLayout(2, 1, 10, 1));
        
        JLabel lblPostulantesRegistrados = new JLabel("Postulantes");
        lblPostulantesRegistrados.setHorizontalAlignment(SwingConstants.CENTER);
        ubicacionEtiqueta.add(lblPostulantesRegistrados);
        
        JPanel ubicacionEspacioLibre = new JPanel();
        ubicacionEtiqueta.add(ubicacionEspacioLibre);
        
        JPanel ubicacionComboTextField = new JPanel();
        PostulanteEIngreso.add(ubicacionComboTextField);
        ubicacionComboTextField.setLayout(new GridLayout(2, 0, 5,0));
        
        JComboBox comboBoxPostulantesRegistrados = new JComboBox();
        ubicacionComboTextField.add(comboBoxPostulantesRegistrados);
        
        JPanel ubicacionEtiquetasText = new JPanel();
        PostulanteEIngreso.add(ubicacionEtiquetasText);
        ubicacionEtiquetasText.setLayout(new GridLayout(3, 1, 0, 0));
        ubicacionEtiquetasText.setVisible(false);
        
        JLabel lblIngresarCV = new JLabel("CV Reducido");
        lblIngresarCV.setHorizontalAlignment(SwingConstants.CENTER);
        ubicacionEtiquetasText.add(lblIngresarCV);
        
        JLabel lblMotivacion = new JLabel("Motivacion");
        lblMotivacion.setHorizontalAlignment(SwingConstants.CENTER);
        ubicacionEtiquetasText.add(lblMotivacion);
        
        JLabel lblFechaPostulacion = new JLabel("Fecha Postulacion");
        lblFechaPostulacion.setHorizontalAlignment(SwingConstants.CENTER);
        ubicacionEtiquetasText.add(lblFechaPostulacion);
        
        JPanel ubicacionTextFields = new JPanel();
        PostulanteEIngreso.add(ubicacionTextFields);
        ubicacionTextFields.setLayout(new GridLayout(3, 1, 0, 0));
        ubicacionTextFields.setVisible(false);
        
        textField = new JTextField();
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        textField.setEditable(false);
        ubicacionTextFields.add(textField);
        
        textField_1 = new JTextField();
        textField_1.setHorizontalAlignment(SwingConstants.CENTER);
        textField_1.setEditable(false);
        ubicacionTextFields.add(textField_1);
        
        textField_2 = new JTextField();
        textField_2.setHorizontalAlignment(SwingConstants.CENTER);
        textField_2.setEditable(false);
        ubicacionTextFields.add(textField_2);
        
        JPanel ubicacionSur = new JPanel();
        getContentPane().add(ubicacionSur, BorderLayout.SOUTH);
        
        JButton btnBotonAceptar = new JButton("Aceptar");
        ubicacionSur.add(btnBotonAceptar);
        btnBotonAceptar.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e)
        	{
        		
        	}
        });
        
        JButton btnBotonCancelar = new JButton("Cancelar");
        ubicacionSur.add(btnBotonCancelar);
        btnBotonCancelar.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e)
        	{
        		dispose();
        	}
        });
        
    }
    
//    public void cargarEmpresas()
//    {
//    		String[] empresas;
//			try {
//				empresas = (controlUsuarioLab.listarEmpresas()).toArray(new String[0]);
//				DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(empresas);
//				comboBoxEmpresasRegistradas.setModel(model);
//			} catch (ColeccionEmpresaEsVaciaException e) {
//			}
//			 
//    }
}
