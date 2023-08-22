package presentacion;

import javax.swing.JInternalFrame;



import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import logica.interfaces.IControladorOferta;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class AltaTipoPublicacionDeOfertaLab extends JInternalFrame {

    // Controlador de usuarios que se utilizará para las acciones del JFrame
    private IControladorOferta controlOfertaLab;
    
   

    /**
     * Create the frame.
     */
    public AltaTipoPublicacionDeOfertaLab(IControladorOferta icontOfeLab) {
        // Se inicializa con el controlador de usuarios
        controlOfertaLab = icontOfeLab;
        
        // Propiedades del JInternalFrame como dimensión, posición dentro del frame, etc.
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Registro de Tipo Publicacion de Oferta Laboral");
        setBounds(30, 30, 400, 280);

       
    }
	
}
