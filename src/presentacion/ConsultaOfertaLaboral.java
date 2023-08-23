package presentacion;

import javax.swing.JInternalFrame;



import javax.swing.JFrame;
import javax.swing.JLabel;

import logica.DataTypes.DTOfertaLaboral;
import logica.interfaces.IControladorOferta;
import logica.interfaces.IControladorUsuario;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.format.DateTimeFormatter;

import javax.swing.JComboBox;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.SwingConstants;

import java.text.SimpleDateFormat;
import excepciones.ColeccionEmpresaEsVaciaException;
import excepciones.UsuarioNoExisteException;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class ConsultaOfertaLaboral extends JInternalFrame {

    // Controlador de usuarios que se utilizará para las acciones del JFrame
    private IControladorOferta controlOfertaLab;
    private IControladorUsuario controlUsuarioLab;
    private JComboBox<String> comboBoxEmpresasRegistradas;
    private JComboBox<String> comboBoxOfertasLaborales;
    private JTextField textFieldNombre;
    private JTextField textFieldDescripcion;
    private JTextField textFieldHoraInicio;
    private JTextField textFieldHoraFin;
    private JTextField textFieldRemuneracion;
    private JTextField textFieldCiudad;
    private JTextField textFieldDepartamento;
    private JTextField textFieldFechaAlta;

    /**
     * Create the frame.
     */
    @SuppressWarnings("null")
	public ConsultaOfertaLaboral(IControladorOferta icontOfeLab, IControladorUsuario icontUsuLab) {
        // Se inicializa con el controlador de oferta
        controlOfertaLab = icontOfeLab;
        controlUsuarioLab = icontUsuLab;
        
        // Propiedades del JInternalFrame como dimensión, posición dentro del frame, etc.
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Consultar Oferta Laboral");
        setBounds(30, 30, 508, 380);
        
        JPanel ubicacionSur = new JPanel();
        getContentPane().add(ubicacionSur, BorderLayout.SOUTH);
        
        JButton btnBotonCerrar = new JButton("Cerrar");
        ubicacionSur.add(btnBotonCerrar);
        
        btnBotonCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
        JPanel ubicacionNorte = new JPanel();
        getContentPane().add(ubicacionNorte, BorderLayout.NORTH);
        ubicacionNorte.setLayout(new GridLayout(1, 2, 10, 10));
        
        JPanel ubicacionIzq = new JPanel();
        ubicacionNorte.add(ubicacionIzq);
        ubicacionIzq.setLayout(new GridLayout(2, 1, 100, 5));
        
        JLabel lblEmpresas = new JLabel("Empresas");
        lblEmpresas.setHorizontalAlignment(SwingConstants.CENTER);
        ubicacionIzq.add(lblEmpresas);
        
        
        JLabel lblOfertasLaborales = new JLabel("Ofertas Laborales");
        lblOfertasLaborales.setHorizontalAlignment(SwingConstants.CENTER);
        ubicacionIzq.add(lblOfertasLaborales);
        lblOfertasLaborales.setVisible(false);
        
        JPanel ubicacionDer = new JPanel();
        ubicacionNorte.add(ubicacionDer);
        ubicacionDer.setLayout(new GridLayout(2, 1, 1, 10));
        
        comboBoxEmpresasRegistradas = new JComboBox();
        ubicacionDer.add(comboBoxEmpresasRegistradas);
        comboBoxEmpresasRegistradas.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e)
        	{
        		cargarOfertaEmpresa(e);
        	}
        });
        
        comboBoxOfertasLaborales = new JComboBox();
        ubicacionDer.add(comboBoxOfertasLaborales);
        comboBoxOfertasLaborales.setVisible(false);
        
        comboBoxOfertasLaborales.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e)
        	{
        		cargarDatosOferta(e);
        	}
        });
        
        JPanel ubicacionCentro = new JPanel();
        getContentPane().add(ubicacionCentro, BorderLayout.CENTER);
        ubicacionCentro.setLayout(new GridLayout(1, 2, 0, 0));
        ubicacionCentro.setVisible(false);
        
        JPanel ubicacionEtiquetas = new JPanel();
        ubicacionCentro.add(ubicacionEtiquetas);
        ubicacionEtiquetas.setLayout(new GridLayout(8, 1, 0, 0));
        
        JLabel lblNombre = new JLabel("  Nombre");
        lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
        ubicacionEtiquetas.add(lblNombre);
        
        JLabel lblDescripcion = new JLabel("  Descripción");
        lblDescripcion.setHorizontalAlignment(SwingConstants.CENTER);
        ubicacionEtiquetas.add(lblDescripcion);
        
        JLabel lblHoraInicio = new JLabel("  Hora Inicio");
        lblHoraInicio.setHorizontalAlignment(SwingConstants.CENTER);
        ubicacionEtiquetas.add(lblHoraInicio);
        
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
        
        textFieldDescripcion = new JTextField();
        textFieldDescripcion.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldDescripcion.setEditable(false);
        ubicacionTextos.add(textFieldDescripcion);
        
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
    }
    
    public String dateToString(Date fecha)
    {
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    	return formatter.format(fecha);
    }
    
    public void cargarEmpresas()
    {
    		String[] empresas;
			try {
				empresas = (controlUsuarioLab.listarEmpresas()).toArray(new String[0]);
				DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(empresas);
				comboBoxEmpresasRegistradas.setModel(model);
			} catch (ColeccionEmpresaEsVaciaException e) {
			}
			 
    }
    
    public void cargarOfertaEmpresa(ActionEvent e)
    { 	
			try {
				String empresa = (String) comboBoxEmpresasRegistradas.getSelectedItem();
				String[] ofertasLaborales = (controlUsuarioLab.obtenerOfertasEmpresa(empresa)).toArray(new String[0]);
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
	    DTOfertaLaboral dtOfertaLaboral = controlOfertaLab.obtenerDtOfertaLaboral(ofertaLaboral);
		textFieldNombre.setText(dtOfertaLaboral.getNombre());
		textFieldDescripcion.setText(dtOfertaLaboral.getDescripcion());	   
		textFieldHoraInicio.setText(dateToString(dtOfertaLaboral.getHoraInicio()));
		textFieldHoraFin.setText(dateToString(dtOfertaLaboral.getHoraFin()));
		textFieldRemuneracion.setText((dtOfertaLaboral.getRemuneracion()).toString());
		textFieldCiudad.setText(dtOfertaLaboral.getCiudad());
		textFieldDepartamento.setText(dtOfertaLaboral.getDepartamento());
		textFieldFechaAlta.setText(dateToString(dtOfertaLaboral.getFechaAlta()));
    }
    
}
