package presentacion;

import javax.swing.JInternalFrame;



import javax.swing.JFrame;
import javax.swing.JLabel;

import logica.DataTypes.DTOfertaLaboral;
import logica.classes.OfertaLaboral;
import logica.interfaces.IControladorOferta;
import logica.interfaces.IControladorUsuario;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JComboBox;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.SwingConstants;

import java.text.SimpleDateFormat;

import excepciones.OfertaLaboralNoExisteException;
import excepciones.UsuarioNoExisteException;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class ConsultaOfertaLaboral extends JInternalFrame {

    // Controlador de usuarios que se utilizará para las acciones del JFrame
    private IControladorOferta controlOfertaLab;
    private IControladorUsuario controlUsuarioLab;
    private JComboBox<String> comboBoxEmpresasRegistradas;
    private JComboBox<String> comboBoxOfertasLaborales;
    private JTextField textFieldNombre;
    private JTextField textFieldHoraInicio;
    private JTextField textFieldHoraFin;
    private JTextField textFieldRemuneracion;
    private JTextField textFieldCiudad;
    private JTextField textFieldDepartamento;
    private JTextField textFieldFechaAlta;
    private JTextArea textAreaDescripcion;
    private String seleccionEmpresa;

    /**
     * Create the frame.
     */
    @SuppressWarnings("null")
	public ConsultaOfertaLaboral(IControladorOferta icontOfeLab, IControladorUsuario icontUsuLab) {
        // Se inicializa con el controlador de oferta
        controlOfertaLab = icontOfeLab;
        controlUsuarioLab = icontUsuLab;
        this.seleccionEmpresa = "";
        // Propiedades del JInternalFrame como dimensión, posición dentro del frame, etc.
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Consultar Oferta Laboral");
        setBounds(30, 30, 483, 339);
        getContentPane().setLayout(null);
        
        JPanel ubicacionComboBox = new JPanel();
        ubicacionComboBox.setBounds(104, 36, 300, 75);
        getContentPane().add(ubicacionComboBox);
        ubicacionComboBox.setLayout(new GridLayout(2, 1, 10, 25));
        
        JComboBox comboBoxEmpresasRegistradas = new JComboBox();
        ubicacionComboBox.add(comboBoxEmpresasRegistradas);
        
        JComboBox comboBoxOfertasLaborales = new JComboBox();
        ubicacionComboBox.add(comboBoxOfertasLaborales);
        
        JPanel ubicacionEtiquetas = new JPanel();
        ubicacionEtiquetas.setBounds(10, 36, 89, 75);
        getContentPane().add(ubicacionEtiquetas);
        ubicacionEtiquetas.setLayout(new GridLayout(2, 1, 10, 15));
        
        JLabel lblOfertasLaborales = new JLabel("Ofertas Laborales");
        ubicacionEtiquetas.add(lblOfertasLaborales);
        
        JLabel lblEmpresas = new JLabel("Empresas");
        ubicacionEtiquetas.add(lblEmpresas);
        
        JPanel panel = new JPanel();
        panel.setBounds(212, 257, 113, 41);
        getContentPane().add(panel);
        
        JLabel lblHoraFin = new JLabel("  Hora Fin");
        lblHoraFin.setHorizontalAlignment(SwingConstants.CENTER);
        ubicacionEtiquetas.add(lblHoraFin);
        
        JLabel lblRemuneracin = new JLabel("  Remuneración");
        lblRemuneracin.setHorizontalAlignment(SwingConstants.CENTER);
        ubicacionEtiquetas.add(lblRemuneracin);
        
        JLabel lblCiudad = new JLabel("  Ciudad");
        lblCiudad.setHorizontalAlignment(SwingConstants.CENTER);
        ubicacionEtiquetas.add(lblCiudad);
        
        JLabel lblDepartamento = new JLabel("  Departamento");
        lblDepartamento.setHorizontalAlignment(SwingConstants.CENTER);
        ubicacionEtiquetas.add(lblDepartamento);
        
        JLabel lblFechaDeAlta = new JLabel("  Fecha de Alta");
        lblFechaDeAlta.setHorizontalAlignment(SwingConstants.CENTER);
        ubicacionEtiquetas.add(lblFechaDeAlta);
        
        JPanel ubicacionTextos = new JPanel();
        ubicacionCentro.add(ubicacionTextos);
        ubicacionTextos.setLayout(new GridLayout(8, 1, 0, 0));
        
        textFieldNombre = new JTextField();
        textFieldNombre.setEditable(false);
        textFieldNombre.setHorizontalAlignment(SwingConstants.CENTER);
        ubicacionTextos.add(textFieldNombre);
        
        JScrollPane scrollPaneDescripcion = new JScrollPane();
        ubicacionTextos.add(scrollPaneDescripcion);
        
        JTextArea textAreaDescripcion = new JTextArea();
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
        
        btnBotonCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                lblOfertasLaborales.setVisible(false);
                comboBoxOfertasLaborales.setVisible(false);
                ubicacionCentro.setVisible(false);
                limpiarInformacion();
            }
        });
        
        comboBoxEmpresasRegistradas.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e)
        	{
        		String empresa = (String) comboBoxEmpresasRegistradas.getSelectedItem();
        		if(empresa != seleccionEmpresa)
        		{
        			limpiarInformacion();
        			ubicacionCentro.setVisible(false);
        		}
        		cargarOfertaEmpresa(e, empresa);
        		lblOfertasLaborales.setVisible(true);
        		comboBoxOfertasLaborales.setVisible(true);
        	}
        });
        
        comboBoxOfertasLaborales.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e)
        	{
        		cargarDatosOferta(e);
        		ubicacionCentro.setVisible(true);
        	}
        });
      
    }
    
    public String dateToString(Date fecha)
    {
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    	return formatter.format(fecha);
    }
    
    public void cargarEmpresas()
    {
    		String[] empresas;
			empresas = (controlUsuarioLab.listarEmpresas()).toArray(new String[0]);
			DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(empresas);
			comboBoxEmpresasRegistradas.setModel(model);	 
    }
    
    public void cargarOfertaEmpresa(ActionEvent e, String empresa)
    { 	
		this.seleccionEmpresa = empresa;
		String[] ofertasLaborales;
		try {
			ofertasLaborales = (controlUsuarioLab.obtenerOfertasEmpresa(empresa)).toArray(new String[0]);
			DefaultComboBoxModel<String> model;
		    model = new DefaultComboBoxModel<String>(ofertasLaborales);
		    comboBoxOfertasLaborales.setModel(model);	
		} catch (UsuarioNoExisteException e1) {
			e1.printStackTrace();
		}
    }
    
    public void cargarDatosOferta(ActionEvent e)
    {
    	String ofertaLaboral = (String) comboBoxOfertasLaborales.getSelectedItem();
    	DTOfertaLaboral dtOfertaLaboral;
	    try {
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
    
    public void mostrarInformacionOfertaLaboral(DTOfertaLaboral dtOfertaLaboral)
    {
    	
    }
}
