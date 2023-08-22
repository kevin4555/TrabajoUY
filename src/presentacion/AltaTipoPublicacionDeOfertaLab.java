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
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Button;
import com.toedter.calendar.JCalendar;
import java.awt.CardLayout;

@SuppressWarnings("serial")
public class AltaTipoPublicacionDeOfertaLab extends JInternalFrame {

    // Controlador de usuarios que se utilizará para las acciones del JFrame
    private IControladorOferta controlOfertaLab;
    private JTextField textFieldNombre;
    private JTextField textFieldDescripcion;
    private JTextField textFieldExposicion;
    private JTextField textFieldDuracion;
    private JTextField textFieldCosto;
    
   

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
        setBounds(30, 30, 933, 470);
        getContentPane().setLayout(new BorderLayout(0, 0));
        
        JPanel ubicacionEtiquetas = new JPanel();
        ubicacionEtiquetas.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(ubicacionEtiquetas, BorderLayout.WEST);
        ubicacionEtiquetas.setLayout(new GridLayout(6, 1, 10, 10));
        
        JLabel lblNombre = new JLabel("Nombre: ");
        ubicacionEtiquetas.add(lblNombre);
        
        JLabel lblDescripcion = new JLabel("Descripcion: ");
        ubicacionEtiquetas.add(lblDescripcion);
        
        JLabel lblExposición = new JLabel("Exposición: ");
        ubicacionEtiquetas.add(lblExposición);
        
        JLabel lblDuración = new JLabel("Duración: ");
        ubicacionEtiquetas.add(lblDuración);
        
        JLabel lblCosto = new JLabel("Costo: ");
        ubicacionEtiquetas.add(lblCosto);
        
        JLabel lblFechaPublicacion = new JLabel("Fecha de publicación: ");
        ubicacionEtiquetas.add(lblFechaPublicacion);
        
        JPanel ubicacionBotones = new JPanel();
        ubicacionBotones.setBorder(new EmptyBorder(5, 5, 5, 5));
        FlowLayout flowLayout = (FlowLayout) ubicacionBotones.getLayout();
        flowLayout.setVgap(20);
        flowLayout.setHgap(120);
        getContentPane().add(ubicacionBotones, BorderLayout.SOUTH);
        
        JButton btnConfirmar = new JButton("Confirmar");
        ubicacionBotones.add(btnConfirmar);
        
        JButton btnCancelar = new JButton("Cancelar");
        ubicacionBotones.add(btnCancelar);
        
        JPanel ubicacionTexto = new JPanel();
        ubicacionTexto.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(ubicacionTexto, BorderLayout.CENTER);
        ubicacionTexto.setLayout(new GridLayout(6, 1, 10, 10));
        
        textFieldNombre = new JTextField();
        ubicacionTexto.add(textFieldNombre);
        
        textFieldDescripcion = new JTextField();
        ubicacionTexto.add(textFieldDescripcion);
        textFieldDescripcion.setColumns(10);
        
        textFieldExposicion = new JTextField();
        ubicacionTexto.add(textFieldExposicion);
        textFieldExposicion.setColumns(10);
        
        textFieldDuracion = new JTextField();
        ubicacionTexto.add(textFieldDuracion);
        textFieldDuracion.setColumns(10);
        
        textFieldCosto = new JTextField();
        ubicacionTexto.add(textFieldCosto);
        textFieldCosto.setColumns(10);
        
        JPanel panel = new JPanel();
        ubicacionTexto.add(panel);
        panel.setLayout(new GridLayout(1, 0, 0, 0));

      
    }
}
