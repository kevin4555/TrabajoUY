package presentacion;

import javax.swing.JInternalFrame;



import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import logica.DataTypes.DTEmpresa;
import logica.DataTypes.DTOfertaLaboral;
import logica.DataTypes.DTPostulacion;
import logica.DataTypes.DTPostulante;
import logica.DataTypes.DTUsuario;
import logica.classes.OfertaLaboral;
import logica.interfaces.IControladorOferta;
import logica.interfaces.IControladorUsuario;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.swing.JComboBox;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.SwingConstants;

import java.text.SimpleDateFormat;

import excepciones.OfertaLaboralNoExisteException;
import excepciones.UsuarioNoExisteException;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;




@SuppressWarnings("serial")
public class Aceptar_Rechazar_Oferta extends JInternalFrame {

    // Controlador de usuarios que se utilizará para las acciones del JFrame
    private IControladorOferta controladorOfertaLaboral;
    private IControladorUsuario controladorUsuario;
	private JTextField textFieldHorarioOferta;
	private JTextField textFieldRemuneracion;
	private JTextField textFieldCiudad;
	private JTextField textFieldDepartamento;
	private JComboBox<String> comboBoxSeleccionUsuario;
	private JComboBox<String> comboBoxSeleccionOferta;
	private String ofertaSeleccionada;
	private JButton btnConfirmar;
	private JButton btnRechazar;
	private JButton btnCerrar;
	private JPanel panelDatos;

    /**
     * Create the frame.
     */
    public Aceptar_Rechazar_Oferta(IControladorOferta icontOfeLab, IControladorUsuario icontUsuLab) {
        // Se inicializa con el controlador de oferta
    	controladorOfertaLaboral = icontOfeLab;
    	controladorUsuario = icontUsuLab;
        
        // Propiedades del JInternalFrame como dimensión, posición dentro del frame, etc.
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setTitle("Aceptar/Rechazar Oferta laboral");
        setBounds(30, 30, 713, 380);
        
        JPanel panelBotones = new JPanel();
		getContentPane().add(panelBotones, BorderLayout.SOUTH);
		panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 120, 20));

		this.btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmarOfertaLaboral();
				
			}
		});
		panelBotones.add(btnConfirmar);
		
		this.btnRechazar = new JButton("Rechazar");
		btnRechazar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rechazarOfertaLaboral();
				
			}
		});
		panelBotones.add(btnRechazar);
		
		this.btnCerrar = new JButton("Cancelar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarTodosLosDatos();
				dispose();
				
			}
		});
		panelBotones.add(btnCerrar);

		this.panelDatos = new JPanel();
		getContentPane().add(panelDatos, BorderLayout.CENTER);
		GridBagLayout gbl_panelDatos = new GridBagLayout();
		gbl_panelDatos.columnWidths = new int[] { 113, 739, 0 };
		gbl_panelDatos.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelDatos.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_panelDatos.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 1.0, 1.0 };
		panelDatos.setLayout(gbl_panelDatos);

		JLabel lblSeleccion = new JLabel("Seleccionar Empresa:");
		GridBagConstraints gbc_lblSeleccion = new GridBagConstraints();
		gbc_lblSeleccion.insets = new Insets(0, 0, 5, 5);
		gbc_lblSeleccion.anchor = GridBagConstraints.EAST;
		gbc_lblSeleccion.gridx = 0;
		gbc_lblSeleccion.gridy = 1;
		panelDatos.add(lblSeleccion, gbc_lblSeleccion);

		this.comboBoxSeleccionUsuario = new JComboBox<String>();
		this.comboBoxSeleccionUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cargarDatosUsuarios(e);
				} catch (UsuarioNoExisteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});

		GridBagConstraints gbc_comboBoxSeleccionUsuario = new GridBagConstraints();
		gbc_comboBoxSeleccionUsuario.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxSeleccionUsuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxSeleccionUsuario.gridx = 1;
		gbc_comboBoxSeleccionUsuario.gridy = 1;
		panelDatos.add(this.comboBoxSeleccionUsuario, gbc_comboBoxSeleccionUsuario);
		
		JLabel lblOfertas = new JLabel("Ofertas Laborales:");
		GridBagConstraints gbc_lblOfertas = new GridBagConstraints();
		gbc_lblOfertas.anchor = GridBagConstraints.EAST;
		gbc_lblOfertas.insets = new Insets(0, 0, 5, 5);
		gbc_lblOfertas.gridx = 0;
		gbc_lblOfertas.gridy = 12;
		panelDatos.add(lblOfertas, gbc_lblOfertas);

		this.comboBoxSeleccionOferta = new JComboBox<String>();
		this.comboBoxSeleccionOferta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cargarDatosOferta(e);
				} catch (OfertaLaboralNoExisteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					
				}
			}
		});
		GridBagConstraints gbc_comboBoxSeleccionOferta = new GridBagConstraints();
		gbc_comboBoxSeleccionOferta.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxSeleccionOferta.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxSeleccionOferta.gridx = 1;
		gbc_comboBoxSeleccionOferta.gridy = 12;
		panelDatos.add(this.comboBoxSeleccionOferta, gbc_comboBoxSeleccionOferta);

		JLabel lblHorarioOferta = new JLabel("Horario");
		GridBagConstraints gbc_lblHorarioOferta = new GridBagConstraints();
		gbc_lblHorarioOferta.anchor = GridBagConstraints.EAST;
		gbc_lblHorarioOferta.insets = new Insets(0, 0, 5, 5);
		gbc_lblHorarioOferta.gridx = 0;
		gbc_lblHorarioOferta.gridy = 13;
		panelDatos.add(lblHorarioOferta, gbc_lblHorarioOferta);

		this.textFieldHorarioOferta = new JTextField();
		this.textFieldHorarioOferta.setEditable(false);
		GridBagConstraints gbc_textFieldNombreOferta = new GridBagConstraints();
		gbc_textFieldNombreOferta.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldNombreOferta.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNombreOferta.gridx = 1;
		gbc_textFieldNombreOferta.gridy = 13;
		panelDatos.add(this.textFieldHorarioOferta, gbc_textFieldNombreOferta);
		this.textFieldHorarioOferta.setColumns(10);

		JLabel lblRemuneracion = new JLabel("Remuneracion");
		GridBagConstraints gbc_lblRemuneracion = new GridBagConstraints();
		gbc_lblRemuneracion.anchor = GridBagConstraints.EAST;
		gbc_lblRemuneracion.insets = new Insets(0, 0, 5, 5);
		gbc_lblRemuneracion.gridx = 0;
		gbc_lblRemuneracion.gridy = 14;
		panelDatos.add(lblRemuneracion, gbc_lblRemuneracion);

		this.textFieldRemuneracion = new JTextField();
		this.textFieldRemuneracion.setEditable(false);
		GridBagConstraints gbc_textFieldRemuneracion = new GridBagConstraints();
		gbc_textFieldRemuneracion.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldRemuneracion.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldRemuneracion.gridx = 1;
		gbc_textFieldRemuneracion.gridy = 14;
		panelDatos.add(this.textFieldRemuneracion, gbc_textFieldRemuneracion);
		this.textFieldRemuneracion.setColumns(10);

		JLabel lblCiudad = new JLabel("Ciudad");
		GridBagConstraints gbc_lblCiudad = new GridBagConstraints();
		gbc_lblCiudad.anchor = GridBagConstraints.EAST;
		gbc_lblCiudad.insets = new Insets(0, 0, 5, 5);
		gbc_lblCiudad.gridx = 0;
		gbc_lblCiudad.gridy = 15;
		panelDatos.add(lblCiudad, gbc_lblCiudad);

		this.textFieldCiudad = new JTextField();
		this.textFieldCiudad.setEditable(false);
		GridBagConstraints gbc_textFieldCiudad = new GridBagConstraints();
		gbc_textFieldCiudad.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldCiudad.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCiudad.gridx = 1;
		gbc_textFieldCiudad.gridy = 15;
		panelDatos.add(this.textFieldCiudad, gbc_textFieldCiudad);
		this.textFieldCiudad.setColumns(10);

		JLabel lblDepartamento = new JLabel("Departamento");
		GridBagConstraints gbc_lblDepartamento = new GridBagConstraints();
		gbc_lblDepartamento.anchor = GridBagConstraints.EAST;
		gbc_lblDepartamento.insets = new Insets(0, 0, 5, 5);
		gbc_lblDepartamento.gridx = 0;
		gbc_lblDepartamento.gridy = 16;
		panelDatos.add(lblDepartamento, gbc_lblDepartamento);

		this.textFieldDepartamento = new JTextField();
		this.textFieldDepartamento.setEditable(false);
		GridBagConstraints gbc_textFieldDepartamento = new GridBagConstraints();
		gbc_textFieldDepartamento.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldDepartamento.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDepartamento.gridx = 1;
		gbc_textFieldDepartamento.gridy = 16;
		panelDatos.add(this.textFieldDepartamento, gbc_textFieldDepartamento);
		this.textFieldDepartamento.setColumns(10);
        
    }
    
    public void cargarEmpresas() {
		try {
			ArrayList<String> listaEmpresas = this.controladorUsuario.listarEmpresas();
			String [] arrayEmpresas;
			arrayEmpresas = listaEmpresas.toArray(new String [0]);
			Arrays.sort(arrayEmpresas);
			DefaultComboBoxModel<String> model;
			model = new DefaultComboBoxModel<String>(arrayEmpresas);
			this.comboBoxSeleccionUsuario.setModel(model);
		
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
    
    
    public void confirmarOfertaLaboral() {
    	String oferta = comboBoxSeleccionOferta.getSelectedItem().toString();
    	if (ofertaSeleccionada != oferta) {
			try {
				DTOfertaLaboral dtOferta = controladorOfertaLaboral.obtenerDtOfertaLaboral(oferta);
				
				//CAMBIAR ESTADO OFERTA
				
				JOptionPane.showMessageDialog(this, "Falta agregar el cambio de estado",
						"Agregar/Rechazar Oferta Laboral", JOptionPane.ERROR_MESSAGE);
				limpiarTodosLosDatos();
			} catch (OfertaLaboralNoExisteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    }
    
    public void rechazarOfertaLaboral() {
    	String oferta = comboBoxSeleccionOferta.getSelectedItem().toString();
    	if (ofertaSeleccionada != oferta) {
			try {
				DTOfertaLaboral dtOferta = controladorOfertaLaboral.obtenerDtOfertaLaboral(oferta);
				
				//CAMBIAR ESTADO OFERTA
				
				JOptionPane.showMessageDialog(this, "Falta agregar el cambio de estado",
						"Agregar/Rechazar Oferta Laboral", JOptionPane.ERROR_MESSAGE);
				limpiarTodosLosDatos();
			} catch (OfertaLaboralNoExisteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    }
    
    @SuppressWarnings({ "deprecation", "exports" })
	public void cargarDatosUsuarios(ActionEvent e) throws UsuarioNoExisteException {
		
		String nicknameUsuario = comboBoxSeleccionUsuario.getSelectedItem().toString();
		
			ArrayList<String> listaOfertas = this.controladorUsuario.listaOfertasUsuario(nicknameUsuario);
			if(listaOfertas.isEmpty()) {
			}
			String [] arrayOfertas = listaOfertas.toArray(new String[0]);
			Arrays.sort(arrayOfertas);
			DefaultComboBoxModel<String> model;
			model = new DefaultComboBoxModel<String>(arrayOfertas);
			this.comboBoxSeleccionOferta.setModel(model);
		}
    
    protected void cargarDatosOferta(ActionEvent e) throws OfertaLaboralNoExisteException {
		String oferta = comboBoxSeleccionOferta.getSelectedItem().toString();
		if (ofertaSeleccionada != oferta) {
			DTOfertaLaboral dtOferta = controladorOfertaLaboral.obtenerDtOfertaLaboral(oferta);
			this.textFieldHorarioOferta.setText(dtOferta.getHorarioInicio() + " - " + dtOferta.getHorarioFinal());
			this.textFieldRemuneracion.setText(dtOferta.getRemuneracion().toString());
			this.textFieldCiudad.setText(dtOferta.getCiudad());
			this.textFieldDepartamento.setText(dtOferta.getDepartamento());
		}
	}
    
    
public void limpiarTodosLosDatos() {
		
		this.ofertaSeleccionada = "";
		this.textFieldDepartamento.setText("");
		this.textFieldCiudad.setText("");
		this.textFieldHorarioOferta.setText("");
		this.textFieldRemuneracion.setText("");
		
		/*ArrayList<String> listaOfertas = new ArrayList<String>();
		String [] arrayOfertas = listaOfertas.toArray(new String[0]);
		Arrays.sort(arrayOfertas);
		DefaultComboBoxModel<String> model;
		model = new DefaultComboBoxModel<String>(arrayOfertas);
		this.comboBoxSeleccionOferta.setModel(model);
		
		this.comboBoxSeleccionUsuario.setModel(model);*/
		
	}
}
