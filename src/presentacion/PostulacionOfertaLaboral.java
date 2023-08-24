package presentacion;

import javax.swing.JInternalFrame;



import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import logica.DataTypes.DTOfertaLaboral;
import logica.classes.OfertaLaboral;
import logica.classes.Postulante;
import logica.interfaces.IControladorOferta;
import logica.interfaces.IControladorUsuario;
import javax.swing.DefaultComboBoxModel;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.SwingConstants;

import excepciones.OfertaLaboralNoExisteException;
import excepciones.UsuarioNoExisteException;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import com.toedter.calendar.JDateChooser;

@SuppressWarnings("serial")
public class PostulacionOfertaLaboral extends JInternalFrame {

    // Controlador de usuarios que se utilizará para las acciones del JFrame
    private IControladorOferta controlOfertaLab;
    private IControladorUsuario controlUsuarioLab;
    private JTextField textFieldNombre;
    private JTextField textFieldHoraInicio;
    private JTextField textFieldHoraFin;
    private JTextField textFieldRemuneracion;
    private JTextField textFieldCiudad;
    private JTextField textFieldDepartamento;
    private JTextField textFieldFechaAlta;
    private JComboBox<String> comboBoxEmpresasRegistradasPostulacion;
    private JComboBox<String> comboBoxOfertasLaboralesPostulacion;
    private JComboBox<String> comboBoxPostulantesRegistrados;
    private JTextField textFieldCVReducido;
    private JTextField textFieldMotivacion;
    private JTextArea textAreaDescripcion;
    private OfertaLaboral oferta;
    private Postulante postulante;
    private String seleccionEmpresa;
    private JDateChooser dateChoose;
    /**
     * Create the frame.
     */
    @SuppressWarnings("null")
    public PostulacionOfertaLaboral(IControladorOferta icontOfeLab, IControladorUsuario icontUsuLab) {
        // Se inicializa con el controlador de usuarios
        controlOfertaLab = icontOfeLab;
        controlUsuarioLab = icontUsuLab;
        this.seleccionEmpresa = "";
        
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
        
        comboBoxEmpresasRegistradasPostulacion = new JComboBox<String>();
        ubicacionComboBox.add(comboBoxEmpresasRegistradasPostulacion);
        
        comboBoxOfertasLaboralesPostulacion = new JComboBox<String>();
        ubicacionComboBox.add(comboBoxOfertasLaboralesPostulacion);
        comboBoxOfertasLaboralesPostulacion.setVisible(false);
        
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
        textFieldNombre.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldNombre.setEditable(false);
        ubicacionTextos.add(textFieldNombre);
        
        JScrollPane scrollPaneDescripcion = new JScrollPane();
        ubicacionTextos.add(scrollPaneDescripcion);
        
        JTextArea textAreaDescripcion = new JTextArea();
        textAreaDescripcion.setWrapStyleWord(true);
        textAreaDescripcion.setLineWrap(true);
        textAreaDescripcion.setEnabled(false);
        textAreaDescripcion.setEditable(false);
        scrollPaneDescripcion.setViewportView(textAreaDescripcion);
        
        textFieldHoraInicio = new JTextField();
        textFieldHoraInicio.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldHoraInicio.setEditable(false);
        ubicacionTextos.add(textFieldHoraInicio);
        
        textFieldHoraFin = new JTextField();
        textFieldHoraFin.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldHoraFin.setEditable(false);
        ubicacionTextos.add(textFieldHoraFin);
        
        textFieldRemuneracion = new JTextField();
        textFieldRemuneracion.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldRemuneracion.setEditable(false);
        ubicacionTextos.add(textFieldRemuneracion);
        
        textFieldCiudad = new JTextField();
        textFieldCiudad.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldCiudad.setEditable(false);
        ubicacionTextos.add(textFieldCiudad);
        
        textFieldDepartamento = new JTextField();
        textFieldDepartamento.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldDepartamento.setEditable(false);
        ubicacionTextos.add(textFieldDepartamento);
        
        textFieldFechaAlta = new JTextField();
        textFieldFechaAlta.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldFechaAlta.setEditable(false);
        ubicacionTextos.add(textFieldFechaAlta);
        
        JPanel postulanteEIngreso = new JPanel();
        ubicacionCentro.add(postulanteEIngreso);
        postulanteEIngreso.setLayout(new GridLayout(0, 2, 0, 0));
        
        JPanel ubicacionEtiqueta = new JPanel();
        postulanteEIngreso.add(ubicacionEtiqueta);
        ubicacionEtiqueta.setLayout(new GridLayout(5, 1, 10, 1));
        ubicacionEtiqueta.setVisible(false);
        
        JLabel lblPostulantesRegistrados = new JLabel("Postulantes");
        lblPostulantesRegistrados.setHorizontalAlignment(SwingConstants.CENTER);
        ubicacionEtiqueta.add(lblPostulantesRegistrados);
        
        JPanel ubicacionComboTextField = new JPanel();
        postulanteEIngreso.add(ubicacionComboTextField);
        ubicacionComboTextField.setLayout(new GridLayout(5, 1, 5, 0));
        ubicacionComboTextField.setVisible(false);
        
        comboBoxPostulantesRegistrados = new JComboBox<String>();
        ubicacionComboTextField.add(comboBoxPostulantesRegistrados);
        
        JPanel ubicacionEtiquetasText = new JPanel();
        postulanteEIngreso.add(ubicacionEtiquetasText);
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
        postulanteEIngreso.add(ubicacionTextFields);
        ubicacionTextFields.setLayout(new GridLayout(3, 1, 0, 0));
        ubicacionTextFields.setVisible(false);
        
        textFieldCVReducido = new JTextField();
        textFieldCVReducido.setHorizontalAlignment(SwingConstants.CENTER);
        ubicacionTextFields.add(textFieldCVReducido);
        
        textFieldMotivacion = new JTextField();
        textFieldMotivacion.setHorizontalAlignment(SwingConstants.CENTER);
        ubicacionTextFields.add(textFieldMotivacion);
        
        JDateChooser dateChooser = new JDateChooser();
        ubicacionTextFields.add(dateChooser);
        
        JPanel ubicacionSur = new JPanel();
        getContentPane().add(ubicacionSur, BorderLayout.SOUTH);
        
        comboBoxEmpresasRegistradasPostulacion.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e)
    		{
    			String empresa = (String) comboBoxEmpresasRegistradasPostulacion.getSelectedItem();	
    			if(empresa != seleccionEmpresa)
    			{
    				limpiarInformacion();
    				ubicacionDatosOferta.setVisible(false);
    				postulanteEIngreso.setVisible(false);
    			}
    			cargarOfertaEmpresaPostulacion(e, empresa);
    			comboBoxOfertasLaboralesPostulacion.setVisible(true);
    			lblOfertasLaborales.setVisible(true);
    		}
        });
        
        comboBoxOfertasLaboralesPostulacion.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e)
    		{
    			cargarDatosOfertaLaboralPostulacion(e);
    			cargarPostulantes(e);
    			ubicacionDatosOferta.setVisible(true);
    			ubicacionEtiqueta.setVisible(true);
    			ubicacionComboTextField.setVisible(true);
    		}
        });       
        
        comboBoxPostulantesRegistrados.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e)
    		{
    			ubicacionEtiquetasText.setVisible(true);
    			ubicacionTextFields.setVisible(true);
    			guardarPostulante();
    		}
        });
        
        JButton btnBotonAceptar = new JButton("Aceptar");
        ubicacionSur.add(btnBotonAceptar);
        btnBotonAceptar.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e)
        	{
        		registrarPostulacion(e);
        	}
        });
        
        JButton btnBotonCancelar = new JButton("Cancelar");
        ubicacionSur.add(btnBotonCancelar);
        btnBotonCancelar.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e)
        	{
        		dispose();
        		comboBoxOfertasLaboralesPostulacion.setVisible(false);
    			lblOfertasLaborales.setVisible(false);
    			ubicacionDatosOferta.setVisible(false);
    			ubicacionEtiqueta.setVisible(false);
    			ubicacionComboTextField.setVisible(false);
    			ubicacionEtiquetasText.setVisible(false);
    			ubicacionTextFields.setVisible(false);
    			limpiarInformacion();
        	}
        });
    }
    
    public String dateToString(Date fecha)
    {
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    	return formatter.format(fecha);
    }
    
    public Date stringToDate(String Fecha)
    {
    	SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
    	try {
    		Date fechaParseada;
    		fechaParseada = (Date) inputFormat.parse(Fecha);
    		return fechaParseada;
    	}catch(ParseException e)
    	{
    		return null;
    	}
    }
    
    public void cargarEmpresasPostulacion()
    {
    	String[] empresas;
			empresas = (controlUsuarioLab.listarEmpresas()).toArray(new String[0]);
			
			DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(empresas);
			comboBoxEmpresasRegistradasPostulacion.setModel(model);

    }
    
    public void cargarOfertaEmpresaPostulacion(ActionEvent e, String empresa)
    {
    	try {
			
			String[] ofertasLaborales = (controlUsuarioLab.obtenerOfertasEmpresa(empresa)).toArray(new String[0]);
			DefaultComboBoxModel<String> model;
    		model = new DefaultComboBoxModel<String>(ofertasLaborales);
    		comboBoxOfertasLaboralesPostulacion.setModel(model);
		} catch (UsuarioNoExisteException e1) {
			e1.printStackTrace();
		}
    }
    
    public void cargarDatosOfertaLaboralPostulacion(ActionEvent e)
    {
    	try {
    		String ofertaLaboral = (String) comboBoxEmpresasRegistradasPostulacion.getSelectedItem();
			this.oferta = controlOfertaLab.obtenerOfertaLaboral(ofertaLaboral); 	
			DTOfertaLaboral dtOfertaLaboral;
			dtOfertaLaboral = controlOfertaLab.obtenerDtOfertaLaboral(ofertaLaboral);
			textFieldNombre.setText(dtOfertaLaboral.getNombre());
			textAreaDescripcion.setText(dtOfertaLaboral.getDescripcion());	   
			textFieldHoraInicio.setText(dtOfertaLaboral.getHorarioInicio());
			textFieldHoraFin.setText(dtOfertaLaboral.getHorarioFinal());
			textFieldRemuneracion.setText((dtOfertaLaboral.getRemuneracion()).toString());
			textFieldCiudad.setText(dtOfertaLaboral.getCiudad());
			textFieldDepartamento.setText(dtOfertaLaboral.getDepartamento());
			textFieldFechaAlta.setText(dateToString(dtOfertaLaboral.getFechaAlta()));
		} catch (OfertaLaboralNoExisteException e1) {
			
		}
    }
    
    public void cargarPostulantes(ActionEvent e)
    {
    	String[] postulantes = (controlUsuarioLab.listarPostulantes()).toArray(new String[0]);
    	DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(postulantes);
    	comboBoxPostulantesRegistrados.setModel(model);
    }
    
    public void registrarPostulacion(ActionEvent e)
    {
    	String cvReducido = textFieldCVReducido.getText();
    	String motivacion = textFieldMotivacion.getText();
    	Date fechaPostulacion = (Date)dateChoose.getDate();
    	
    	if(chequearDatos())
    	{
    		try {
				controlUsuarioLab.registrarPostulacion(cvReducido, motivacion, fechaPostulacion, this.postulante.getNickname() , this.oferta.getNombre());
			} catch (UsuarioNoExisteException e1) {

			} catch (OfertaLaboralNoExisteException e1) {

			}
    	}
    	limpiarInformacion();
    }
    
    public boolean chequearDatos()
    {
    	String cvReducido = textFieldCVReducido.getText();
    	String motivacion = textFieldMotivacion.getText();
    	
    	if(cvReducido.isEmpty() || motivacion.isEmpty())
    	{
    		JOptionPane.showMessageDialog(this, "Es necesario rellenar todos los campos.", "Registrar Usuario",
                    JOptionPane.ERROR_MESSAGE);
            return false;
    	}
    	
    	//Falta el chequeo de la fecha
    	return true;
    }
    
    public void guardarPostulante()
    {
    	try {
			this.postulante = controlUsuarioLab.obtenerPostulante((comboBoxPostulantesRegistrados.getSelectedItem().toString()));
		} catch (UsuarioNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void limpiarInformacion()
    {
    	textFieldNombre.setText("");
		textAreaDescripcion.setText("");	   
		textFieldHoraInicio.setText("");
		textFieldHoraFin.setText("");
		textFieldRemuneracion.setText("");
		textFieldCiudad.setText("");
		textFieldDepartamento.setText("");
		textFieldFechaAlta.setText("");
    }
}
