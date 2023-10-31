package presentacion;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import logica.datatypes.DtOfertaLaboral;
import logica.interfaces.IcontroladorOferta;

/**
 * Clase Consulta de oferta laboral .
 */

@SuppressWarnings("serial")
public class OfertasLaboralesMasVisitadas extends JInternalFrame {
  
  // Controlador de usuarios que se utilizará para
  // las acciones del JFrame
  private IcontroladorOferta controlOfertaLab;
  private JButton btnCerrar;
  private JPanel panelDatos;
  private String [] nombreColumnas = {"#", "Oferta Laboral", "Empresa", "Tipo de publicación?", "Cantidad de visitas"};
  private Object [][] datosFilas = new Object[5][5];
  private JTable ofertaMasVisitadas;
  
  /**
   * Create the frame.
   */
  public OfertasLaboralesMasVisitadas(
      IcontroladorOferta icontOfeLab) {
    // Se inicializa con el controlador de oferta
    controlOfertaLab = icontOfeLab;
    
    // Propiedades del JInternalFrame como dimensión,
    // posición dentro del frame,
    // etc.
    setResizable(true);
    setIconifiable(true);
    setMaximizable(true);
    setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    setTitle("Ver ofertas más visitadas");
    setBounds(30, 30, 686, 227);
    
    JPanel panelBotones = new JPanel();
    getContentPane().add(panelBotones, BorderLayout.SOUTH);
    panelBotones.setLayout(
        new FlowLayout(FlowLayout.CENTER, 120, 20));
    
    btnCerrar = new JButton("Cerrar");
    panelBotones.add(btnCerrar);
    
    btnCerrar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evento) {
        datosFilas = new Object[5][5];
        dispose();
      }
    });
    
    this.panelDatos = new JPanel(new BorderLayout());
    getContentPane().add(panelDatos, BorderLayout.CENTER);

    ofertaMasVisitadas = new JTable(datosFilas, nombreColumnas);
    panelDatos.add(new JScrollPane(ofertaMasVisitadas), BorderLayout.CENTER);

    
    ofertaMasVisitadas = new JTable(datosFilas, nombreColumnas);
    panelDatos.add(new JScrollPane(ofertaMasVisitadas), BorderLayout.CENTER);

    
  }
  
  
  
  
  /**
   * Metodo cargar empresas .
   */
  
  public void cargarOfertasMasVisitadas() {
    try {
      List<DtOfertaLaboral> ofertasMasVisitadas =  this.controlOfertaLab.obtenerOfertasMasVisitadas();
      int fila = 0;
      int columna = 0;
      for (DtOfertaLaboral oferta: ofertasMasVisitadas) {
        datosFilas[fila][columna] = fila + 1;
        columna += 1;
        datosFilas[fila][columna] = oferta.getNombre(); 
        columna += 1;
        datosFilas[fila][columna] = oferta.getEmpresa(); 
        columna += 1;
        datosFilas[fila][columna] = oferta.getNombreTipoPublicacion();
        columna += 1;
        datosFilas[fila][columna] = oferta.getVisitas();
        columna = 0;
        fila += 1;
        if (fila == 5) {
          break;
        }
      }
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  

  
}

