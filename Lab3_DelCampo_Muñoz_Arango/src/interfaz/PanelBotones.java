package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

public class PanelBotones extends JPanel implements ActionListener {

	public final static String CARGAR = "cargar";
	public final static String CONSULTAR = "CONSULTAR";
	public final static String NUEVA_CONSULTA = "consulta nueva";
	public final static String DEFINIR_CAMPOS_RAPIDOS = "campos rapidos jajajajjajajajjajaxdxdxdxdxdxdxdxlolololololo";

	private JButton butonCrgar;
	private FrameBase frame;
	private JComboBox opc1;
	private JComboBox opc2;
	private JComboBox opc3;
	private JPanel aux;

	private JPanel panelBusqueda;
	private JComboBox txtCampo;
	private JTextField txtLlave;

	private JButton btbBuscar;

	public PanelBotones(FrameBase frame) {
		this.frame = frame;
		setLayout(new BorderLayout());
		panelCargar();
		// panelBuscar();
	}

	
	public void panelBuscar() {
		this.removeAll();
		JButton cargarnew = new JButton("Cargar Nuevo Archivo");
		cargarnew.addActionListener(this);
		cargarnew.setActionCommand(NUEVA_CONSULTA);

		panelBusqueda = new JPanel();
		txtCampo = new JComboBox(frame.getTitulos());
		txtLlave = new JTextField("Ingrese un valor a buscar");
		

		
		
		
		btbBuscar = new JButton("Buscar");
		btbBuscar.addActionListener(this);
		btbBuscar.setActionCommand(CONSULTAR);

		panelBusqueda.setLayout(new GridLayout(1, 4));
		panelBusqueda.add(btbBuscar);
		panelBusqueda.add(txtCampo);
		panelBusqueda.add(txtLlave);

		panelBusqueda.add(cargarnew);
		add(panelBusqueda, BorderLayout.CENTER);
		repaint();
		revalidate();

	}
	public void PanelDefinicCamposRapidos() {
		this.removeAll();
		aux = new JPanel();
		aux.setLayout(new GridLayout(1, 5));
		butonCrgar = new JButton("definir llamados");
		butonCrgar.addActionListener(this);
		butonCrgar.setActionCommand(DEFINIR_CAMPOS_RAPIDOS);
		opc1 = new JComboBox(frame.getTitulos());
		opc2 = new JComboBox(frame.getTitulos());
		opc3 = new JComboBox(frame.getTitulos());

		JLabel aux1 = new JLabel("seleccione los llmados rapidos");
		aux.add(aux1);
		aux.add(opc1);
		aux.add(opc2);
		aux.add(opc3);
		aux.add(butonCrgar);
		add(aux, BorderLayout.CENTER);
		repaint();
		revalidate();
		
	}

	public void panelCargar() {
		JLabel saludo=new JLabel("hola vienvenido al sistema, selecciona tu arvhivo para continuar");
		
		this.removeAll();
		aux = new JPanel();
		aux.setLayout(new GridLayout(2, 3));
		butonCrgar = new JButton("Cargar");
		butonCrgar.addActionListener(this);
		butonCrgar.setActionCommand(CARGAR);

		setLayout(new GridLayout(1, 3));
		add(butonCrgar, BorderLayout.CENTER);
	    add(saludo,BorderLayout.EAST);
		repaint();
		revalidate();

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getActionCommand().equals(CARGAR)) {
			
			try {
				
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new File("./DatosEnCSV"));
				FileNameExtensionFilter filter = new FileNameExtensionFilter("XLS or cvs only", "XLSX", "XLS", "csv");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showOpenDialog(getParent());
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					JOptionPane.showMessageDialog(this,
							"You chose to open this file: " + chooser.getSelectedFile().getName());
					

					try {
						frame.lectorArchivo(chooser.getSelectedFile());
						PanelDefinicCamposRapidos();
					} catch (Exception e10) {
						e10.printStackTrace();
						JOptionPane.showMessageDialog(null, "Un error fatal ha ocurrido" + "\n"
								+ " tenemos un grupo de simios trabajando para solucionaro" + "\n"
								+ "si los ves muestrales esto Ð²Ñ£Ð´Ð¸Ð³Ð»Ð°Ð³Ð¾Ð»ÑŒÐ¶Ð¸Ð²Ñ£Ñ‚ÐµÐºÑ€Ð°Ñ‚ÐºÐ¾Ð¹ÑŽÑ�ÑŠ Ð±Ð¾Ð»ÑŒÑˆÐ¾Ð¹ Ñ–Ð¾Ñ‚Ð¸Ñ€Ð¾Ð²Ð°Ð½Ð½Ñ‹Ð¹ÑŽÑ�ÑŠ Ð¼Ð°Ð»Ñ‹Ð¹ Ñ–Ð¾Ñ‚Ð¸Ñ€Ð¾Ð²Ð°Ð½Ð½Ñ‹Ð¹",
								"Error al digitar los datos", JOptionPane.ERROR_MESSAGE, null);
						panelCargar();
					}
				}
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, "Debe indicar el NUMERO de una columna valida ",
						"Error al digitar los datos", JOptionPane.ERROR_MESSAGE, null);
				panelCargar();
			}
		} else if (e.getActionCommand().equals(CONSULTAR)) {
			try {
				String letras = "";
				ArrayList<String[]> arreglo = frame.buscar(txtCampo.getSelectedIndex(), txtLlave.getText());
				for (int i = 0; i < arreglo.size(); i++) {
					letras += Arrays.toString(arreglo.get(i)) + "\n";
				}
				JOptionPane.showMessageDialog(this, letras);

			} catch (NumberFormatException e2) {
				JOptionPane.showMessageDialog(null, "Debe indicar el NUMERO de una columna valida ",
						"Error al digitar los datos", JOptionPane.ERROR_MESSAGE, null);
			} catch (Exception e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(this, "algo extranio acaba de suceder o eres manoso o habia un error"
						+ "\n por favor no bajes la nota :'(");
			}

		} else if (e.getActionCommand().equals(NUEVA_CONSULTA)) {
			panelCargar();
		}
		else if(e.getActionCommand().equals(DEFINIR_CAMPOS_RAPIDOS)) {
			
			int entrada1 = 0;
			int entrada2 = 1;
			int entrada3 = 2;
			if(opc1.getSelectedIndex()!=opc2.getSelectedIndex()&&opc2.getSelectedIndex()!=opc3.getSelectedIndex()) {
				entrada1=opc1.getSelectedIndex();
				entrada2=opc2.getSelectedIndex();
				entrada3=opc3.getSelectedIndex();
				try {
					frame.definirArboles(entrada1, entrada2, entrada3);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(this, "uy uy hubo errorer");
				}
				panelBuscar();
				
				
			}else {
				JOptionPane.showMessageDialog(this, "debes eleguir tres campos diferentes pillin");
			}
			
		}

	}

}
