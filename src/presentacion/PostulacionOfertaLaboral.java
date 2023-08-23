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
import javax.swing.SwingConstants;

import excepciones.ColeccionEmpresaEsVaciaException;

@SuppressWarnings("serial")
public class PostulacionOfertaLaboral extends JInternalFrame {

    // Controlador de usuarios que se utilizará para las acciones del JFrame
    private IControladorOferta controlOfertaLab;
    private IControladorUsuario controlUsuarioLab;
    private JComboBox<String> comboBoxEmpresasRegistradas;

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
        setBounds(30, 30, 400, 280);
        getContentPane().setLayout(new BorderLayout(0, 0));
        
        JPanel ubicacionNorte = new JPanel();
        getContentPane().add(ubicacionNorte, BorderLayout.NORTH);
        ubicacionNorte.setLayout(new GridLayout(1, 1, 5, 1));
        
        JLabel lblEmpresas = new JLabel("Empresas");
        lblEmpresas.setHorizontalAlignment(SwingConstants.CENTER);
        ubicacionNorte.add(lblEmpresas);
        
        comboBoxEmpresasRegistradas = new JComboBox();
        ubicacionNorte.add(comboBoxEmpresasRegistradas);
        
        JPanel ubicacionCentro = new JPanel();
        getContentPane().add(ubicacionCentro, BorderLayout.CENTER);
        
        JPanel ubicacionSur = new JPanel();
        getContentPane().add(ubicacionSur, BorderLayout.SOUTH);
        
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
}
