package presentacion;

import javax.swing.JInternalFrame;



import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import logica.DataTypes.DTOfertaLaboral;
import logica.interfaces.IControladorOferta;
import logica.interfaces.IControladorUsuario;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JList;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import excepciones.ColeccionEmpresaEsVaciaException;
import excepciones.UsuarioNoExisteException;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
//import net.miginfocom.swing.MigLayout;
import javax.swing.BoxLayout;
import java.awt.TextArea;
import java.awt.Panel;
import java.awt.Label;
import java.awt.TextField;

@SuppressWarnings("serial")
public class ConsultaOfertaLaboral extends JInternalFrame {

    // Controlador de usuarios que se utilizará para las acciones del JFrame
    private IControladorOferta controlOfertaLab;
    private IControladorUsuario controlUsuarioLab;
    private JComboBox<String> comboBoxListaEmpresas;
    private JComboBox<String> comboBoxListaOfertasLaborales;

    /**
     * Create the frame.
     */
    @SuppressWarnings("null")
	public ConsultaOfertaLaboral(IControladorOferta icontOfeLab) {
        // Se inicializa con el controlador de oferta
        controlOfertaLab = icontOfeLab;
        
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
    
    
    public void cargarEmpresas()
    {
    	DefaultComboBoxModel<String> model;
    	String[] empresas;
		try {
			empresas = (controlUsuarioLab.listarEmpresas()).toArray(new String[0]);
			model = new DefaultComboBoxModel<String>(empresas);
			comboBoxListaEmpresas.setModel(model); 
		} catch (ColeccionEmpresaEsVaciaException e) {
			e.printStackTrace();
		}
    }
    
    public void cargarOfertaEmpresa(String[] ofertasEmpresa)
    {
    	DefaultComboBoxModel<String> model;
		model = new DefaultComboBoxModel<String>(ofertasEmpresa);
		comboBoxListaOfertasLaborales.setModel(model);
    }
    
    public void mostrarInformacionOfertaLaboral(DTOfertaLaboral dtOfertaLaboral)
    {
    	
    }
}
