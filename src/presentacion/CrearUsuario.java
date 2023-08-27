package presentacion;

import javax.swing.JInternalFrame;



import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import logica.interfaces.IControladorUsuario;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * JInternalFrame que permite registrar un nuevo usuario al sistema.
 * 
 * @author TProg2017
 *
 */
@SuppressWarnings("serial")
public class CrearUsuario extends JInternalFrame {

    // Controlador de usuarios que se utilizará para las acciones del JFrame
    private IControladorUsuario controlUsr;
    
    

    /**
     * Create the frame.
     */
    public CrearUsuario(IControladorUsuario icu) {
        // Se inicializa con el controlador de usuarios
        controlUsr = icu;

        // Propiedades del JInternalFrame como dimensión, posición dentro del frame,
        // etc.
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Registrar un Usuario");
        setBounds(10, 40, 360, 150);
    }

    
}
