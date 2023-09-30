package presentacion;

import excepciones.OfertaLaboralNoExisteException;
import excepciones.UsuarioNoExisteException;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import logica.datatypes.DtOfertaLaboral;
import logica.interfaces.IcontroladorOferta;
import logica.interfaces.IcontroladorUsuario;

/**
 * Clase Consulta de oferta laboral .
 */

@SuppressWarnings("serial")
public class ConsultaOfertaLaboral extends JInternalFrame {
  
  // Controlador de usuarios que se utilizará para
  // las acciones del JFrame
  private IcontroladorOferta controlOfertaLab;
  private IcontroladorUsuario controlUsuarioLab;
  private JComboBox<String> comboBoxEmpresasRegistradas;
  private JComboBox<String> comboBoxOfertasLaborales;
  private JTextField textFieldHorarioOferta;
  private JTextField textFieldRemuneracion;
  private JTextField textFieldCiudad;
  private JTextField textFieldDepartamento;
  private JTextField textFieldFechaAlta;
  private JTextArea textAreaDescripcion;
  
  /**
   * Create the frame.
   */
  public ConsultaOfertaLaboral(IcontroladorOferta icontOfeLab,
      IcontroladorUsuario icontUsuLab) {
    // Se inicializa con el controlador de oferta
    controlOfertaLab = icontOfeLab;
    controlUsuarioLab = icontUsuLab;
    
    // Propiedades del JInternalFrame como dimensión,
    // posición dentro del frame,
    // etc.
    setResizable(true);
    setIconifiable(true);
    setMaximizable(true);
    setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    setTitle("Consultar Oferta Laboral");
    setBounds(30, 30, 500, 547);
    
    JPanel ubicacionSur = new JPanel();
    getContentPane().add(ubicacionSur, BorderLayout.SOUTH);
    
    JButton btnBotonCerrar = new JButton("Cerrar");
    ubicacionSur.add(btnBotonCerrar);
    
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
    
    this.comboBoxEmpresasRegistradas = new JComboBox<String>();
    ubicacionDer.add(this.comboBoxEmpresasRegistradas);
    
    this.comboBoxOfertasLaborales = new JComboBox<String>();
    ubicacionDer.add(this.comboBoxOfertasLaborales);
    this.comboBoxOfertasLaborales.setVisible(false);
    
    JPanel ubicacionCentro = new JPanel();
    getContentPane().add(ubicacionCentro, BorderLayout.CENTER);
    ubicacionCentro.setLayout(new GridLayout(1, 2, 30, 30));
    ubicacionCentro.setVisible(false);
    
    JPanel ubicacionEtiquetas = new JPanel();
    ubicacionCentro.add(ubicacionEtiquetas);
    ubicacionEtiquetas.setLayout(new GridLayout(7, 1, 0, 0));
    
    JLabel labelEspacio = new JLabel("---------");
    labelEspacio.setHorizontalAlignment(SwingConstants.CENTER);
    ubicacionEtiquetas.add(labelEspacio);
    
    JLabel lblDescripcion = new JLabel("  Descripción");
    lblDescripcion.setHorizontalAlignment(SwingConstants.CENTER);
    ubicacionEtiquetas.add(lblDescripcion);
    
    JLabel lblHoraInicio = new JLabel("  Horario");
    lblHoraInicio.setHorizontalAlignment(SwingConstants.CENTER);
    ubicacionEtiquetas.add(lblHoraInicio);
    
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
    ubicacionTextos.setLayout(new GridLayout(7, 1, 0, 0));
    
    JLabel labelEspacio2 = new JLabel("---------");
    labelEspacio2.setHorizontalAlignment(SwingConstants.CENTER);
    ubicacionTextos.add(labelEspacio2);
    
    JScrollPane scrollPaneDescripcion = new JScrollPane();
    ubicacionTextos.add(scrollPaneDescripcion);
    
    this.textAreaDescripcion = new JTextArea();
    textAreaDescripcion.setWrapStyleWord(true);
    textAreaDescripcion.setLineWrap(true);
    textAreaDescripcion.setEditable(false);
    scrollPaneDescripcion.setViewportView(this.textAreaDescripcion);
    
    this.textFieldHorarioOferta = new JTextField();
    this.textFieldHorarioOferta.setEditable(false);
    this.textFieldHorarioOferta.setHorizontalAlignment(SwingConstants.CENTER);
    ubicacionTextos.add(this.textFieldHorarioOferta);
    
    this.textFieldRemuneracion = new JTextField();
    this.textFieldRemuneracion.setHorizontalAlignment(SwingConstants.CENTER);
    this.textFieldRemuneracion.setEditable(false);
    ubicacionTextos.add(this.textFieldRemuneracion);
    
    this.textFieldCiudad = new JTextField();
    this.textFieldCiudad.setHorizontalAlignment(SwingConstants.CENTER);
    this.textFieldCiudad.setEditable(false);
    ubicacionTextos.add(this.textFieldCiudad);
    
    this.textFieldDepartamento = new JTextField();
    this.textFieldDepartamento.setHorizontalAlignment(SwingConstants.CENTER);
    this.textFieldDepartamento.setEditable(false);
    ubicacionTextos.add(this.textFieldDepartamento);
    
    this.textFieldFechaAlta = new JTextField();
    this.textFieldFechaAlta.setHorizontalAlignment(SwingConstants.CENTER);
    this.textFieldFechaAlta.setEditable(false);
    ubicacionTextos.add(this.textFieldFechaAlta);
    
    btnBotonCerrar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        dispose();
        lblOfertasLaborales.setVisible(false);
        
        ubicacionCentro.setVisible(false);
        limpiarInformacion();
      }
    });
    
    this.comboBoxEmpresasRegistradas.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        cargarOfertaEmpresa(e);
        lblOfertasLaborales.setVisible(true);
        comboBoxOfertasLaborales.setVisible(true);
      }
    });
    
    this.comboBoxOfertasLaborales.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          cargarDatosOferta(e);
        } catch (OfertaLaboralNoExisteException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }
        ubicacionCentro.setVisible(true);
      }
    });
  }
  
  public String dateToString(Date fecha) {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    return formatter.format(fecha);
  }
  
  /**
   * Metodo cargar empresas .
   */
  
  public void cargarEmpresas() {
    String[] empresas;
    empresas = (controlUsuarioLab.listarEmpresas()).toArray(new String[0]);
    DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(empresas);
    this.comboBoxEmpresasRegistradas.setModel(model);
  }
  
  /**
   * Metodo cargar oferta empresa .
   */
  
  public void cargarOfertaEmpresa(ActionEvent e) {
    String empresa = (String) (this.comboBoxEmpresasRegistradas).getSelectedItem();
    String[] ofertasLaborales;
    limpiarInformacion(); // correccion
    try {
      ofertasLaborales = (controlUsuarioLab.obtenerOfertasEmpresa(empresa))
          .toArray(new String[0]);
      DefaultComboBoxModel<String> model;
      model = new DefaultComboBoxModel<String>(ofertasLaborales);
      this.comboBoxOfertasLaborales.setModel(model);
    } catch (UsuarioNoExisteException e1) {
      e1.printStackTrace();
    }
  }
  
  /**
   * Metodo cargar datos oferta .
   */
  
  public void cargarDatosOferta(ActionEvent e) throws OfertaLaboralNoExisteException {
    String ofertaLaboral = (String) (this.comboBoxOfertasLaborales).getSelectedItem();
    DtOfertaLaboral dtOfertaLaboral;
    
    dtOfertaLaboral = controlOfertaLab.obtenerDtOfertaLaboral(ofertaLaboral);
    
    (this.textAreaDescripcion).setText(dtOfertaLaboral.getDescripcion());
    (this.textFieldHorarioOferta).setText(
        dtOfertaLaboral.getHorarioInicio() + " - " + dtOfertaLaboral.getHorarioFinal());
    (this.textFieldRemuneracion).setText(dtOfertaLaboral.getRemuneracion().toString());
    (this.textFieldCiudad).setText(dtOfertaLaboral.getCiudad());
    (this.textFieldDepartamento).setText(dtOfertaLaboral.getDepartamento());
    (this.textFieldFechaAlta).setText(dtOfertaLaboral.getFechaAlta().toString());
    
  }
  
  /**
   * Metodo limpiar informacion .
   */
  
  public void limpiarInformacion() {
    this.textAreaDescripcion.setText("");
    this.textFieldHorarioOferta.setText("");
    this.textFieldRemuneracion.setText("");
    this.textFieldCiudad.setText("");
    this.textFieldDepartamento.setText("");
    this.textFieldFechaAlta.setText("");
    /*
     * ArrayList<String> listaOfertas = new
     * ArrayList<String>(); String [] arrayOfertas =
     * listaOfertas.toArray(new String[0]);
     * Arrays.sort(arrayOfertas);
     * DefaultComboBoxModel<String> model; model = new
     * DefaultComboBoxModel<String>(arrayOfertas);
     * this.comboBoxOfertasLaborales.setModel(model);
     */
    
  }
  
}
