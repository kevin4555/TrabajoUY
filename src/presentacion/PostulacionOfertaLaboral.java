package presentacion;

import javax.swing.JInternalFrame;



import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import logica.DataTypes.DTOfertaLaboral;
import logica.classes.Empresa;
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
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.SwingConstants;

import excepciones.ColeccionEmpresaEsVaciaException;
import excepciones.OfertaLaboralNoExisteException;
import excepciones.UsuarioNoExisteException;

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
    private JComboBox<String> comboBoxEmpresasRegistradasPostulacion;
    private JComboBox<String> comboBoxOfertasLaboralesPostulacion;
    private JComboBox<String> comboBoxPostulantesRegistrados;
    private JTextField textFieldCVReducido;
    private JTextField textFieldMotivacion;
    private JTextField textFieldFechaPostulacion;
    private Empresa empresa;
    private OfertaLaboral oferta;
    private Postulante postulante;
    /**
     * Create the frame.
     */
    @SuppressWarnings("null")
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
        
        JPanel postulanteEIngreso = new JPanel();
        ubicacionCentro.add(postulanteEIngreso);
        postulanteEIngreso.setLayout(new GridLayout(0, 2, 0, 0));
        postulanteEIngreso.setVisible(false);
        
        JPanel ubicacionEtiqueta = new JPanel();
        postulanteEIngreso.add(ubicacionEtiqueta);
        ubicacionEtiqueta.setLayout(new GridLayout(2, 1, 10, 1));
        ubicacionEtiqueta.setVisible(false);
        
        JLabel lblPostulantesRegistrados = new JLabel("Postulantes");
        lblPostulantesRegistrados.setHorizontalAlignment(SwingConstants.CENTER);
        ubicacionEtiqueta.add(lblPostulantesRegistrados);
        
        JPanel ubicacionEspacioLibre = new JPanel();
        ubicacionEtiqueta.add(ubicacionEspacioLibre);
        
        JPanel ubicacionComboTextField = new JPanel();
        postulanteEIngreso.add(ubicacionComboTextField);
        ubicacionComboTextField.setLayout(new GridLayout(2, 0, 5,0));
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
        
        textFieldFechaPostulacion = new JTextField();
        textFieldFechaPostulacion.setHorizontalAlignment(SwingConstants.CENTER);
        ubicacionTextFields.add(textFieldFechaPostulacion);
        
        JPanel ubicacionSur = new JPanel();
        getContentPane().add(ubicacionSur, BorderLayout.SOUTH);
        
        comboBoxEmpresasRegistradasPostulacion.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e)
    		{
    			cargarOfertaEmpresaPostulacion(e);
    			comboBoxOfertasLaboralesPostulacion.setVisible(true);
    			lblOfertasLaborales.setVisible(true);
    		}
        });
        
        comboBoxOfertasLaboralesPostulacion.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e)
    		{
    			cargarDatosOfertaLaboralPostulacion(e);
    			cargarPostulantes(e);
    			ubicacionEtiquetasDTOF.setVisible(true);
    			ubicacionTextos.setVisible(true);
    			postulanteEIngreso.setVisible(true);
    			ubicacionEtiqueta.setVisible(true);
    			ubicacionComboTextField.setVisible(true);
    			
    		}
        });       
        
        comboBoxPostulantesRegistrados.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e)
    		{
    			ubicacionEtiquetasText.setVisible(true);
    			ubicacionTextFields.setVisible(true);
    		}
        });
        
        JButton btnBotonAceptar = new JButton("Aceptar");
        ubicacionSur.add(btnBotonAceptar);
        btnBotonAceptar.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e)
        	{
        		//registrarPostulacion(e);
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
    
    public String dateToString(Date fecha)
    {
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    	return formatter.format(fecha);
    }
    
    public void cargarEmpresasPostulacion()
    {
    	String[] empresas;
		try {
			empresas = (controlUsuarioLab.listarEmpresas()).toArray(new String[0]);
			
			DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(empresas);
			comboBoxEmpresasRegistradasPostulacion.setModel(model);
		} catch (ColeccionEmpresaEsVaciaException e) {
		}
    }
    
    public void cargarOfertaEmpresaPostulacion(ActionEvent e)
    {
    	try {
			String empresa = (String) comboBoxEmpresasRegistradasPostulacion.getSelectedItem();
			//this.Empresa = controlUsuarioLab.obtenerEmpresa(empresa);
			
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
    	String ofertaLaboral = (String) comboBoxEmpresasRegistradasPostulacion.getSelectedItem();
    	try {
			this.oferta = controlOfertaLab.obtenerOfertaLaboral(ofertaLaboral);
		} catch (OfertaLaboralNoExisteException e1) {
		}
	    DTOfertaLaboral dtOfertaLaboral = controlOfertaLab.obtenerDtOfertaLaboral(ofertaLaboral);
		textFieldNombre.setText(dtOfertaLaboral.getNombre());
		textFieldDescripcion.setText(dtOfertaLaboral.getDescripcion());	   
		textFieldHoraInicio.setText(dtOfertaLaboral.getHorarioInicio());
		textFieldHoraFin.setText(dtOfertaLaboral.getHorarioFinal());
		textFieldRemuneracion.setText((dtOfertaLaboral.getRemuneracion()).toString());
		textFieldCiudad.setText(dtOfertaLaboral.getCiudad());
		textFieldDepartamento.setText(dtOfertaLaboral.getDepartamento());
		textFieldFechaAlta.setText(dateToString(dtOfertaLaboral.getFechaAlta()));
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
    	String fechaPostulacion = textFieldFechaPostulacion.getText();
    	Date fechaPostulacionD = stringToDate(fechaPostulacion);
    	
    	if(chequearDatos())
    		try {
                controlUsuarioLab.registrarPostulacion(cvReducido, motivacion, fechaPostulacionD, this.postulante.getNickname() , this.oferta.getNombre());
            } catch (UsuarioNoExisteException e1) {

            } catch (OfertaLaboralNoExisteException e1) {

            }
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
    
    public boolean chequearDatos()
    {
    	String cvReducido = textFieldCVReducido.getText();
    	String motivacion = textFieldMotivacion.getText();
    	String fechaPostulacion = textFieldFechaPostulacion.getText();
    	
    	if(cvReducido.isEmpty() || motivacion.isEmpty() || fechaPostulacion.isEmpty())
    	{
    		JOptionPane.showMessageDialog(this, "La CI debe ser un numero", "Registrar Usuario",
                    JOptionPane.ERROR_MESSAGE);
            return false;
    	}
    	
    	//Falta el chequeo de la fecha
    	return true;
    }
}
