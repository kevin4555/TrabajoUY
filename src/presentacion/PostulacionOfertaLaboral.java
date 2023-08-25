package presentacion;

import javax.swing.JInternalFrame;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import logica.classes.OfertaLaboral;
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
    private String oferta;
    private String postulante;
    private String seleccionEmpresa;
    private JDateChooser dateChoose;
    /**
     * Create the frame.
     */
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
        ubicacionNorte.setVisible(true);
        
        JPanel ubicacionEtiquetas = new JPanel();
        ubicacionNorte.add(ubicacionEtiquetas);
        ubicacionEtiquetas.setLayout(new GridLayout(2, 1, 0, 2));
        ubicacionEtiquetas.setVisible(true);
        
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
        ubicacionComboBox.setVisible(true);
        
        this.comboBoxEmpresasRegistradasPostulacion = new JComboBox<String>();
        ubicacionComboBox.add(this.comboBoxEmpresasRegistradasPostulacion);
        
        this.comboBoxOfertasLaboralesPostulacion = new JComboBox<String>();
        ubicacionComboBox.add(this.comboBoxOfertasLaboralesPostulacion);
        this.comboBoxOfertasLaboralesPostulacion.setVisible(false);
        
        JPanel ubicacionCentro = new JPanel();
        getContentPane().add(ubicacionCentro, BorderLayout.CENTER);
        ubicacionCentro.setLayout(new GridLayout(0, 2, 5, 1));
        ubicacionCentro.setVisible(false);
        
        JPanel ubicacionDatosOferta = new JPanel();
        ubicacionCentro.add(ubicacionDatosOferta);
        ubicacionDatosOferta.setLayout(new GridLayout(0, 2, 0, 0));
        ubicacionDatosOferta.setVisible(false);
        
        JPanel ubicacionEtiquetasDTOF = new JPanel();
        ubicacionDatosOferta.add(ubicacionEtiquetasDTOF);
        ubicacionEtiquetasDTOF.setLayout(new GridLayout(8, 1, 0, 0));
        ubicacionEtiquetasDTOF.setVisible(false);
        
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
        ubicacionTextos.setVisible(false);
        
        this.textFieldNombre = new JTextField();
        this.textFieldNombre.setHorizontalAlignment(SwingConstants.CENTER);
        this.textFieldNombre.setEditable(false);
        ubicacionTextos.add(this.textFieldNombre);
        
        JScrollPane scrollPaneDescripcion = new JScrollPane();
        ubicacionTextos.add(scrollPaneDescripcion);
        
        this.textAreaDescripcion = new JTextArea("");
        this.textAreaDescripcion.setWrapStyleWord(true);
        this.textAreaDescripcion.setLineWrap(true);
        this.textAreaDescripcion.setEditable(false);
        scrollPaneDescripcion.setViewportView(this.textAreaDescripcion);
        
        this.textFieldHoraInicio = new JTextField();
        this.textFieldHoraInicio.setHorizontalAlignment(SwingConstants.CENTER);
        this.textFieldHoraInicio.setEditable(false);
        ubicacionTextos.add(this.textFieldHoraInicio);
        
        this.textFieldHoraFin = new JTextField();
        this.textFieldHoraFin.setHorizontalAlignment(SwingConstants.CENTER);
        this.textFieldHoraFin.setEditable(false);
        ubicacionTextos.add(this.textFieldHoraFin);
        
        this.textFieldRemuneracion = new JTextField();
        this.textFieldRemuneracion.setHorizontalAlignment(SwingConstants.CENTER);
        this.textFieldRemuneracion.setEditable(false);
        ubicacionTextos.add(this.textFieldRemuneracion);
        
        this.textFieldCiudad = new JTextField();
        this.textFieldCiudad.setHorizontalAlignment(SwingConstants.CENTER);
        this.textFieldCiudad.setEditable(false);
        ubicacionTextos.add(this.textFieldCiudad);
        
        this.textFieldDepartamento = new JTextField();
        this.textFieldDepartamento.setHorizontalAlignment(SwingConstants.CENTER);
        this.textFieldDepartamento.setEditable(false);
        ubicacionTextos.add(this.textFieldDepartamento);
        
        this.textFieldFechaAlta = new JTextField();
        this.textFieldFechaAlta.setHorizontalAlignment(SwingConstants.CENTER);
        this.textFieldFechaAlta.setEditable(false);
        ubicacionTextos.add(this.textFieldFechaAlta);
        
        JPanel postulanteEIngreso = new JPanel();
        ubicacionCentro.add(postulanteEIngreso);
        postulanteEIngreso.setLayout(new GridLayout(0, 2, 0, 0));
        postulanteEIngreso.setVisible(false);
        
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
        ubicacionComboTextField.setVisible(true);
        
        this.comboBoxPostulantesRegistrados = new JComboBox<String>();
        ubicacionComboTextField.add(this.comboBoxPostulantesRegistrados);
        
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
        
        this.textFieldCVReducido = new JTextField();
        this.textFieldCVReducido.setHorizontalAlignment(SwingConstants.CENTER);
        ubicacionTextFields.add(this.textFieldCVReducido);
        
        this.textFieldMotivacion = new JTextField();
        this.textFieldMotivacion.setHorizontalAlignment(SwingConstants.CENTER);
        ubicacionTextFields.add(this.textFieldMotivacion);
        
        JDateChooser dateChooser = new JDateChooser();
        ubicacionTextFields.add(dateChooser);
        
        JPanel ubicacionSur = new JPanel();
        getContentPane().add(ubicacionSur, BorderLayout.SOUTH);
        ubicacionSur.setVisible(false);
        
        this.comboBoxEmpresasRegistradasPostulacion.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e)
    		{
    			//empresa1 = (String) (comboBoxEmpresasRegistradasPostulacion).getSelectedItem();
        		//if(empresa1 != empresa2)
        		{
        			ubicacionCentro.setVisible(false);
        			limpiarInformacion();
        		}
    			cargarOfertaEmpresaPostulacion(e);
    			comboBoxOfertasLaboralesPostulacion.setVisible(true);
    			lblOfertasLaborales.setVisible(true);
    		}
        });
        
        this.comboBoxOfertasLaboralesPostulacion.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e)
    		{
    			cargarDatosOfertaLaboralPostulacion(e);
    			cargarPostulantes(e);
    			ubicacionCentro.setVisible(true);
    			ubicacionDatosOferta.setVisible(true);
    			ubicacionEtiquetasDTOF.setVisible(true);
    			ubicacionTextos.setVisible(true);
    			postulanteEIngreso.setVisible(true);
    			ubicacionEtiqueta.setVisible(true);
    			ubicacionComboTextField.setVisible(true);
    		}
        });       
        
        this.comboBoxPostulantesRegistrados.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e)
    		{
    			ubicacionEtiquetasText.setVisible(true);
    			ubicacionTextFields.setVisible(true);
    			ubicacionSur.setVisible(true);
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
    			ubicacionCentro.setVisible(false);
    			ubicacionEtiquetasDTOF.setVisible(false);
    			ubicacionTextos.setVisible(false);
    			postulanteEIngreso.setVisible(false);

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
			this.comboBoxEmpresasRegistradasPostulacion.setModel(model);

    }
    
    public void cargarOfertaEmpresaPostulacion(ActionEvent e)
    {
    	try {
    		this.seleccionEmpresa = this.comboBoxEmpresasRegistradasPostulacion.getSelectedItem().toString();
			String[] ofertasLaborales = (controlUsuarioLab.obtenerOfertasEmpresa(this.seleccionEmpresa)).toArray(new String[0]);
			DefaultComboBoxModel<String> model;
    		model = new DefaultComboBoxModel<String>(ofertasLaborales);
    		this.comboBoxOfertasLaboralesPostulacion.setModel(model);
		} catch (UsuarioNoExisteException e1) {
			e1.printStackTrace();
		}
    }
    
    public void cargarDatosOfertaLaboralPostulacion(ActionEvent e)
    {
    	String ofertaLaboral = (String) (this.comboBoxOfertasLaboralesPostulacion).getSelectedItem();
    	OfertaLaboral dtOfertaLaboral;
	    try {
			dtOfertaLaboral = controlOfertaLab.obtenerOfertaLaboral(ofertaLaboral);
			
			(this.textFieldNombre).setText(dtOfertaLaboral.getNombre());
			(this.textAreaDescripcion).setText(dtOfertaLaboral.getDescripcion());	   
			(this.textFieldHoraInicio).setText(dtOfertaLaboral.getHorarioInicial());
			(this.textFieldHoraFin).setText(dtOfertaLaboral.getHorarioFinal());
			(this.textFieldRemuneracion).setText(String.valueOf((dtOfertaLaboral.getRemunaracion())));
			(this.textFieldCiudad).setText(dtOfertaLaboral.getCiudad());
			(this.textFieldDepartamento).setText(dtOfertaLaboral.getDepartamento());
			(this.textFieldFechaAlta).setText(dateToString(dtOfertaLaboral.getFechaAlta()));
		} catch (OfertaLaboralNoExisteException e1) {

    	}
    }
    
    public void cargarPostulantes(ActionEvent e)
    {
    	String[] postulantes = (controlUsuarioLab.listarPostulantes()).toArray(new String[0]);
    	DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(postulantes);
    	this.comboBoxPostulantesRegistrados.setModel(model);
    }
    
    public void registrarPostulacion(ActionEvent e)
    {
    	String cvReducido = this.textFieldCVReducido.getText();
    	String motivacion = this.textFieldMotivacion.getText();
    	Date fechaPostulacion = (Date)this.dateChoose.getDate();
    	String nombreOferta;
		try
		{
			nombreOferta = (controlOfertaLab.obtenerDtOfertaLaboral(this.oferta)).getNombre();
			String nombrePostulante;
			nombrePostulante = (controlUsuarioLab.obtenerPostulante(this.postulante)).getNickname();
			
	    	if(chequearDatos())
	    	{
	    		try {
					controlUsuarioLab.registrarPostulacion(cvReducido, motivacion, fechaPostulacion, nombrePostulante , nombreOferta);
				} catch (UsuarioNoExisteException e1) {

				} catch (OfertaLaboralNoExisteException e1) {

				}
	    	}
	    	limpiarInformacion();
		}
		catch (OfertaLaboralNoExisteException e1)
		{
		}
		catch (UsuarioNoExisteException e1)
		{
		}
    }
    
    public boolean chequearDatos()
    {
    	String cvReducido = this.textFieldCVReducido.getText();
    	String motivacion = this.textFieldMotivacion.getText();
    	
    	if(cvReducido.isEmpty() || motivacion.isEmpty())
    	{
    		JOptionPane.showMessageDialog(this, "Es necesario rellenar todos los campos.", "Registrar Usuario",
                    JOptionPane.ERROR_MESSAGE);
            return false;
    	}
    	return true;
    }
    
    public void guardarPostulante()
    {
		this.postulante = this.comboBoxPostulantesRegistrados.getSelectedItem().toString();
    }
    
    public void limpiarInformacion()
    {
    	this.textFieldNombre.setText("");
    	this.textAreaDescripcion.setText("");	   
    	this.textFieldHoraInicio.setText("");
    	this.textFieldHoraFin.setText("");
    	this.textFieldRemuneracion.setText("");
    	this.textFieldCiudad.setText("");
    	this.textFieldDepartamento.setText("");
		this.textFieldFechaAlta.setText("");
    }
}
