package presentacion;

import javax.swing.JInternalFrame;



import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

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
        
        JPanel ubicacionBoton = new JPanel();
        ubicacionBoton.setBorder(new EmptyBorder(5,5,5,5));
        getContentPane().add(ubicacionBoton, BorderLayout.SOUTH);
        ubicacionBoton.setLayout(new FlowLayout(FlowLayout.CENTER, 120, 20));
        
        JPanel ubicacionEtiquetas = new JPanel();
        ubicacionEtiquetas.setBorder(new EmptyBorder(2,10,150,5));
        getContentPane().add(ubicacionEtiquetas, BorderLayout.WEST);
        ubicacionEtiquetas.setLayout(new GridLayout(2,1,1,1));
        
        
        
        JPanel ubicacionCombo = new JPanel(new GridBagLayout());
        ubicacionCombo.setBorder(new EmptyBorder(5,5,120,5));
        getContentPane().add(ubicacionCombo, BorderLayout.CENTER);
        ubicacionCombo.setLayout(new GridLayout(3, 1, 1, 15));
        
        JButton btnCerrar = new JButton("Cerrar");
        ubicacionBoton.add(btnCerrar);
        
        btnCerrar.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{
        		dispose();
        	}
        });
        
        JLabel lblempresas = new JLabel("Empresas");
        ubicacionEtiquetas.add(lblempresas);
        
        JLabel lblofertasLaborales = new JLabel("	Ofertas Laborales");
        lblofertasLaborales.setBounds(200,200,0, 0);
        ubicacionEtiquetas.add(lblofertasLaborales);
        lblofertasLaborales.setVisible(false);
        
        JComboBox comboBoxListaEmpresas = new JComboBox<>();
        ubicacionCombo.add(comboBoxListaEmpresas);
        
        JComboBox comboBoxListaOfertasLaborales = new JComboBox();
        comboBoxListaOfertasLaborales.setVisible(false);
        ubicacionCombo.add(comboBoxListaOfertasLaborales);
        
        
        
        comboBoxListaEmpresas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String empresaSeleccionada = (String) comboBoxListaEmpresas.getSelectedItem();
                try {
                	lblofertasLaborales.setVisible(true);
                	comboBoxListaOfertasLaborales.setVisible(true);
					String[] ofertasEmpresas = (controlUsuarioLab.obtenerOfertasEmpresa(empresaSeleccionada)).toArray(new String[0]);
					cargarOfertaEmpresa(ofertasEmpresas);
                } catch (UsuarioNoExisteException e1) {
					e1.printStackTrace();
				}
                
            }
        });
        
        comboBoxListaOfertasLaborales.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ofertaSeleccionada = (String) comboBoxListaOfertasLaborales.getSelectedItem();                
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
    
   
    
}
