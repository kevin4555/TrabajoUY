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
        
        JButton btnCerrar = new JButton("Cerrar");
        panel.add(btnCerrar);
        btnCerrar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
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
		/*textFieldHoraInicio.setText(dateToString(dtOfertaLaboral.getHoraInicio()));
		textFieldHoraFin.setText(dateToString(dtOfertaLaboral.getHoraFin()));*/
		textFieldRemuneracion.setText((dtOfertaLaboral.getRemuneracion()).toString());
		textFieldCiudad.setText(dtOfertaLaboral.getCiudad());
		textFieldDepartamento.setText(dtOfertaLaboral.getDepartamento());
		textFieldFechaAlta.setText(dateToString(dtOfertaLaboral.getFechaAlta()));
    }
    
    public void mostrarInformacionOfertaLaboral(DTOfertaLaboral dtOfertaLaboral)
    {
    	
    }
}
