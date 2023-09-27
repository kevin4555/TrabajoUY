package presentacion;

import com.toedter.calendar.JDateChooser;
import excepciones.OfertaLaboralNoExisteException;
import excepciones.UsuarioNoExisteException;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import logica.DataTypes.DTOfertaLaboral;
import logica.interfaces.IControladorOferta;
import logica.interfaces.IControladorUsuario;

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
    private String postulante;
    private String seleccionEmpresa;
    private JDateChooser dateChooser;
    private String cvReducido;
	private String motivacion;
	private Date fechaPostulacion;
	private JLabel lblOfertasLaborales;
	private JComponent ubicacionDatosOferta;
	private JComponent ubicacionEtiqueta;
	private JComponent ubicacionComboTextField;
	private JComponent ubicacionEtiquetasText;
	private JComponent ubicacionTextFields;
	private JComponent ubicacionEtiquetasDTOF;
	private JComponent ubicacionTextos;
	private JComponent ubicacionCentro;
	private JComponent postulanteEIngreso;
	private String nomOfertaLaboral;
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
        
        this.lblOfertasLaborales = new JLabel("Ofertas Laborales");
        this.lblOfertasLaborales.setHorizontalAlignment(SwingConstants.CENTER);
        ubicacionEtiquetas.add(this.lblOfertasLaborales);
        this.lblOfertasLaborales.setVisible(false);
        
        JPanel ubicacionComboBox = new JPanel();
        ubicacionNorte.add(ubicacionComboBox);
        ubicacionComboBox.setLayout(new GridLayout(2, 1, 0, 2));
        ubicacionComboBox.setVisible(true);
        
        this.comboBoxEmpresasRegistradasPostulacion = new JComboBox<String>();
        ubicacionComboBox.add(this.comboBoxEmpresasRegistradasPostulacion);
        
        this.comboBoxOfertasLaboralesPostulacion = new JComboBox<String>();
        ubicacionComboBox.add(this.comboBoxOfertasLaboralesPostulacion);
        this.comboBoxOfertasLaboralesPostulacion.setVisible(false);
        
        this.ubicacionCentro = new JPanel();
        getContentPane().add(this.ubicacionCentro, BorderLayout.CENTER);
        this.ubicacionCentro.setLayout(new GridLayout(0, 2, 5, 1));
        this.ubicacionCentro.setVisible(false);
        
        this.ubicacionDatosOferta = new JPanel();
        this.ubicacionCentro.add(this.ubicacionDatosOferta);
        this.ubicacionDatosOferta.setLayout(new GridLayout(0, 2, 0, 0));
        this.ubicacionDatosOferta.setVisible(false);
        
        this.ubicacionEtiquetasDTOF = new JPanel();
        ubicacionDatosOferta.add(this.ubicacionEtiquetasDTOF);
        this.ubicacionEtiquetasDTOF.setLayout(new GridLayout(8, 1, 0, 0));
        this.ubicacionEtiquetasDTOF.setVisible(false);
        
        JLabel lblNombre = new JLabel("  Nombre");
        lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
        this.ubicacionEtiquetasDTOF.add(lblNombre);
        
        JLabel lblDescripcion = new JLabel("  Descripción");
        lblDescripcion.setHorizontalAlignment(SwingConstants.CENTER);
        this.ubicacionEtiquetasDTOF.add(lblDescripcion);
        
        JLabel lblHoraInicio = new JLabel("  Hora Inicio");
        lblHoraInicio.setHorizontalAlignment(SwingConstants.CENTER);
        this.ubicacionEtiquetasDTOF.add(lblHoraInicio);
        
        JLabel lblHoraFin = new JLabel("  Hora Fin");
        lblHoraFin.setHorizontalAlignment(SwingConstants.CENTER);
        this.ubicacionEtiquetasDTOF.add(lblHoraFin);
        
        JLabel lblRemuneracin = new JLabel("  Remuneración");
        lblRemuneracin.setHorizontalAlignment(SwingConstants.CENTER);
        this.ubicacionEtiquetasDTOF.add(lblRemuneracin);
        
        JLabel lblCiudad = new JLabel("  Ciudad");
        lblCiudad.setHorizontalAlignment(SwingConstants.CENTER);
        this.ubicacionEtiquetasDTOF.add(lblCiudad);
        
        JLabel lblDepartamento = new JLabel("  Departamento");
        lblDepartamento.setHorizontalAlignment(SwingConstants.CENTER);
        this.ubicacionEtiquetasDTOF.add(lblDepartamento);
        
        JLabel lblFechaDeAlta = new JLabel("  Fecha de Alta");
        lblFechaDeAlta.setHorizontalAlignment(SwingConstants.CENTER);
        this.ubicacionEtiquetasDTOF.add(lblFechaDeAlta);
        
        this.ubicacionTextos = new JPanel();
        this.ubicacionDatosOferta.add(this.ubicacionTextos);
        this.ubicacionTextos.setLayout(new GridLayout(8, 1, 0, 0));
        this.ubicacionTextos.setVisible(false);
        
        this.textFieldNombre = new JTextField();
        this.textFieldNombre.setHorizontalAlignment(SwingConstants.CENTER);
        this.textFieldNombre.setEditable(false);
        this.ubicacionTextos.add(this.textFieldNombre);
        
        JScrollPane scrollPaneDescripcion = new JScrollPane();
        this.ubicacionTextos.add(scrollPaneDescripcion);
        
        this.textAreaDescripcion = new JTextArea("");
        this.textAreaDescripcion.setWrapStyleWord(true);
        this.textAreaDescripcion.setLineWrap(true);
        this.textAreaDescripcion.setEditable(false);
        scrollPaneDescripcion.setViewportView(this.textAreaDescripcion);
        
        this.textFieldHoraInicio = new JTextField();
        this.textFieldHoraInicio.setHorizontalAlignment(SwingConstants.CENTER);
        this.textFieldHoraInicio.setEditable(false);
        this.ubicacionTextos.add(this.textFieldHoraInicio);
        
        this.textFieldHoraFin = new JTextField();
        this.textFieldHoraFin.setHorizontalAlignment(SwingConstants.CENTER);
        this.textFieldHoraFin.setEditable(false);
        this.ubicacionTextos.add(this.textFieldHoraFin);
        
        this.textFieldRemuneracion = new JTextField();
        this.textFieldRemuneracion.setHorizontalAlignment(SwingConstants.CENTER);
        this.textFieldRemuneracion.setEditable(false);
        this.ubicacionTextos.add(this.textFieldRemuneracion);
        
        this.textFieldCiudad = new JTextField();
        this.textFieldCiudad.setHorizontalAlignment(SwingConstants.CENTER);
        this.textFieldCiudad.setEditable(false);
        this.ubicacionTextos.add(this.textFieldCiudad);
        
        this.textFieldDepartamento = new JTextField();
        this.textFieldDepartamento.setHorizontalAlignment(SwingConstants.CENTER);
        this.textFieldDepartamento.setEditable(false);
        this.ubicacionTextos.add(this.textFieldDepartamento);
        
        this.textFieldFechaAlta = new JTextField();
        this.textFieldFechaAlta.setHorizontalAlignment(SwingConstants.CENTER);
        this.textFieldFechaAlta.setEditable(false);
        this.ubicacionTextos.add(this.textFieldFechaAlta);
        
        this.postulanteEIngreso = new JPanel();
        this.ubicacionCentro.add(this.postulanteEIngreso);
        this.postulanteEIngreso.setLayout(new GridLayout(0, 2, 0, 0));
        this.postulanteEIngreso.setVisible(false);
        
        this.ubicacionEtiqueta = new JPanel();
        this.postulanteEIngreso.add(this.ubicacionEtiqueta);
        this.ubicacionEtiqueta.setLayout(new GridLayout(5, 1, 10, 1));
        this.ubicacionEtiqueta.setVisible(false);
        
        JLabel lblPostulantesRegistrados = new JLabel("Postulantes");
        lblPostulantesRegistrados.setHorizontalAlignment(SwingConstants.CENTER);
        this.ubicacionEtiqueta.add(lblPostulantesRegistrados);
        
        this.ubicacionComboTextField = new JPanel();
        this.postulanteEIngreso.add(this.ubicacionComboTextField);
        this.ubicacionComboTextField.setLayout(new GridLayout(5, 1, 5, 0));
        this.ubicacionComboTextField.setVisible(true);
        
        this.comboBoxPostulantesRegistrados = new JComboBox<String>();
        this.ubicacionComboTextField.add(this.comboBoxPostulantesRegistrados);
        
        this.ubicacionEtiquetasText = new JPanel();
        this.postulanteEIngreso.add(this.ubicacionEtiquetasText);
        this.ubicacionEtiquetasText.setLayout(new GridLayout(3, 1, 0, 0));
        this.ubicacionEtiquetasText.setVisible(false);
        
        JLabel lblIngresarCV = new JLabel("CV Reducido");
        lblIngresarCV.setHorizontalAlignment(SwingConstants.CENTER);
        this.ubicacionEtiquetasText.add(lblIngresarCV);
        
        JLabel lblMotivacion = new JLabel("Motivacion");
        lblMotivacion.setHorizontalAlignment(SwingConstants.CENTER);
        this.ubicacionEtiquetasText.add(lblMotivacion);
        
        JLabel lblFechaPostulacion = new JLabel("Fecha Postulacion");
        lblFechaPostulacion.setHorizontalAlignment(SwingConstants.CENTER);
        this.ubicacionEtiquetasText.add(lblFechaPostulacion);
        
        this.ubicacionTextFields = new JPanel();
        this.postulanteEIngreso.add(this.ubicacionTextFields);
        this.ubicacionTextFields.setLayout(new GridLayout(3, 1, 0, 0));
        this.ubicacionTextFields.setVisible(false);
        
        this.textFieldCVReducido = new JTextField();
        this.textFieldCVReducido.setHorizontalAlignment(SwingConstants.CENTER);
        this.ubicacionTextFields.add(this.textFieldCVReducido);
        
        this.textFieldMotivacion = new JTextField();
        this.textFieldMotivacion.setHorizontalAlignment(SwingConstants.CENTER);
        this.ubicacionTextFields.add(this.textFieldMotivacion);
        
        this.dateChooser = new JDateChooser();
        this.ubicacionTextFields.add(this.dateChooser);
        
        
        JPanel ubicacionSur = new JPanel();
        getContentPane().add(ubicacionSur, BorderLayout.SOUTH);
        ubicacionSur.setVisible(true);
        
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
        
        JButton btnBotonAceptar = new JButton("Confirmar");
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
    
    public String dateToString(Date date)
    {
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    	return formatter.format(date);
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
    	this.nomOfertaLaboral = (String) (this.comboBoxOfertasLaboralesPostulacion).getSelectedItem();
    	DTOfertaLaboral dtOfertaLaboral;
	    try {
			dtOfertaLaboral = controlOfertaLab.obtenerDtOfertaLaboral(this.nomOfertaLaboral);
			
			(this.textFieldNombre).setText(dtOfertaLaboral.getNombre());
			(this.textAreaDescripcion).setText(dtOfertaLaboral.getDescripcion());	   
			(this.textFieldHoraInicio).setText(dtOfertaLaboral.getHorarioInicio());
			(this.textFieldHoraFin).setText(dtOfertaLaboral.getHorarioFinal());
			(this.textFieldRemuneracion).setText(String.valueOf((dtOfertaLaboral.getRemuneracion())));
			(this.textFieldCiudad).setText(dtOfertaLaboral.getCiudad());
			(this.textFieldDepartamento).setText(dtOfertaLaboral.getDepartamento());
		    (this.textFieldFechaAlta).setText(dtOfertaLaboral.getFechaAlta().toString());
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
    	if(chequearDatos())
    	{	
	    	try {
				String nombrePostulante;
				if(!controlOfertaLab.estaPostulado(this.postulante, this.nomOfertaLaboral))
				{
					nombrePostulante = (controlUsuarioLab.obtenerPostulante(this.postulante)).getNickname();
					LocalDate fechaPos = this.dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					controlUsuarioLab.registrarPostulacion(this.cvReducido, this.motivacion, fechaPos, nombrePostulante , this.nomOfertaLaboral);
					JOptionPane.showMessageDialog(this, "La postulacion fue hecha con exito", "Registrar Usuario", JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					JOptionPane.showMessageDialog(this, "El usuario ya esta postulado a esta oferta", "Registrar Usuario", JOptionPane.ERROR_MESSAGE);
				}
			} catch (UsuarioNoExisteException e1) {
			} catch (OfertaLaboralNoExisteException e1) {
			}
		}
    		comboBoxOfertasLaboralesPostulacion.setVisible(false);
    		comboBoxOfertasLaboralesPostulacion.setVisible(false);
			this.lblOfertasLaborales.setVisible(false);
			this.ubicacionDatosOferta.setVisible(false);
			this.ubicacionEtiqueta.setVisible(false);
			this.ubicacionComboTextField.setVisible(false);
			this.ubicacionEtiquetasText.setVisible(false);
			this.ubicacionTextFields.setVisible(false);
			this.ubicacionCentro.setVisible(false);
			this.ubicacionEtiquetasDTOF.setVisible(false);
			this.ubicacionTextos.setVisible(false);
			this.postulanteEIngreso.setVisible(false);
	    	limpiarInformacion();
    }
    
    public boolean chequearDatos()
    {
    	this.cvReducido = this.textFieldCVReducido.getText();
    	this.motivacion = this.textFieldMotivacion.getText();
    	this.fechaPostulacion = this.dateChooser.getDate();
		if(cvReducido.isEmpty() || motivacion.isEmpty())
		    {
		    	JOptionPane.showMessageDialog(this, "Es necesario rellenar todos los campos.", "Registrar Usuario", JOptionPane.ERROR_MESSAGE);
		        return false;
		    }
		    else if(this.fechaPostulacion == null)
		    {
		    	JOptionPane.showMessageDialog(this, "Debe seleccionar una fecha válida", "Trabajo.uy", JOptionPane.ERROR_MESSAGE);
				return false;
		    }
		    else
		    {
		    	return true;
		    }
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
		this.textFieldMotivacion.setText("");
		this.textFieldCVReducido.setText("");
		this.dateChooser.setDate(null);
    }
}
	