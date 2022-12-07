package Vista;

import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultTreeCellEditor.DefaultTextField;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import Dao.DaoAutos;
import Decoder.BASE64Decoder;
import Modelo.DetalleV;
import Modelo.Autos;
import Modelo.Cliente;
import Modelo.Proveedor;
import Modelo.Venta;

import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Color;
import java.awt.Desktop;

import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.awt.Toolkit;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Incar extends JInternalFrame {

	private JPanel contentPane;
    String imagenActual = "";
	DaoAutos dao=new DaoAutos();
	DefaultTableModel modelo=new DefaultTableModel();
	ArrayList<Autos> lista = new ArrayList<Autos>();
	int fila=-1;
	Autos Autos;
	ImageIcon imgOri = null;
	Funciones fx = new Funciones();
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Incar frame = new Incar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Incar() {
		setBackground(new Color(128, 0, 0));
		setClosable(true);
		//setIconImage(Toolkit.getDefaultToolkit().getImage(Incar.class.getResource("/Img/icono.jpg")));
		//setLocationRelativeTo(null);
		setTitle("Incar");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 921, 533);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("Incar");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new java.awt.Font("Rockwell Extra Bold", java.awt.Font.BOLD, 20));
		lblNewLabel.setBounds(95, 92, 296, 90);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(439, 27, 335, 229);
		lblNewLabel_1.setIcon(fx.cambiar(new ImageIcon(getClass().getResource("/Img/logodesot.png")), 335, 229 ));
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("<html> Incar es un sotware creado y diceñado por la compañia QUETWARE, este sotware fue creado para llevar el gestionamiento de una agencia de autos  ");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 19));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(80, 283, 683, 170);
		contentPane.add(lblNewLabel_2);
		
	}
}
