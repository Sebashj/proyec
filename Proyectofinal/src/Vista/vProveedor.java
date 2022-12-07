package Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.netbeans.lib.awtextra.AbsoluteConstraints;

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

import Dao.DaoProveedor;
import Modelo.Producto;
import Modelo.Proveedor;

import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.awt.Toolkit;

public class vProveedor extends JInternalFrame {

	private JPanel contentPane;
	private JLabel lblid;
	private JTextField txtdescripcion;
	private JTextField txtcontacto;
	private JTextField txtnombre;
	private JButton btnAgregar;
	private JButton btnEliminar;
	private JButton btnEditar;
	DaoProveedor dao=new DaoProveedor();
	DefaultTableModel modelo=new DefaultTableModel();
	private JScrollPane scrollPane;
	private JTable tblProveedor;
	ArrayList<Proveedor> lista = new ArrayList<Proveedor>();
	int fila=-1;
	Proveedor Proveedor;
	private JButton btnpdf;
	private JLabel lblNewLabel_2;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vProveedor frame = new vProveedor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void limpiar() {
		lblid.setText("");
		txtdescripcion.setText("");
		txtcontacto.setText("");
		txtnombre.setText("");
	}
	public void pdf() {
		try {
			FileOutputStream archivo;
			URI uri = new URI(getClass().getResource("/PDF/Proveedor.pdf").toString());
			File file = new File(uri);
			archivo = new FileOutputStream(file);
			Document doc = new Document();
			PdfWriter.getInstance(doc, archivo);
			doc.open();
			java.awt.Image Img2 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Img/logodesot.jpg"));
			Image Img = Image.getInstance(getClass().getResource("/Img/logodesot.jpg"));
			Img.setAlignment(Element.ALIGN_CENTER);
	        Img.scaleToFit(200, 200);
			doc.add(Img);
			Paragraph p = new Paragraph(10);
			com.itextpdf.text.Font negrita = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK);
			p.add(Chunk.NEWLINE);
			p.add("Proveedor");
			p.add(Chunk.NEWLINE);
			p.add(Chunk.NEWLINE);
			p.setAlignment(Element.ALIGN_CENTER);
			doc.add(p);
			PdfPTable tabla = new PdfPTable(4);
			tabla.setWidthPercentage(100);
			PdfPCell c1 = new PdfPCell(new Phrase(" Idproveedor", negrita));
			PdfPCell c2 = new PdfPCell(new Phrase(" Nombreproveedor", negrita));
			PdfPCell c3 = new PdfPCell(new Phrase(" Contacto", negrita));
			PdfPCell c4 = new PdfPCell(new Phrase(" Descripcion", negrita));		
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
			c3.setHorizontalAlignment(Element.ALIGN_CENTER);
			c4.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
			c2.setBackgroundColor(BaseColor.LIGHT_GRAY);
			c3.setBackgroundColor(BaseColor.LIGHT_GRAY);
			c4.setBackgroundColor(BaseColor.LIGHT_GRAY);
			tabla.addCell(c1);
			tabla.addCell(c2);
			tabla.addCell(c3);
			tabla.addCell(c4);

			for (Proveedor u : lista) {
				tabla.addCell("" + u.getIdproveedor());
				tabla.addCell("" + u.getNombreproveedor());
				tabla.addCell("" + u.getContacto());
				tabla.addCell("" + u.getDescripcion());

			}

			doc.add(tabla);
			Paragraph p1 = new Paragraph(10);
			p1.add(Chunk.NEWLINE);
			p1.add("NÚMERO DE REGISTRO " + lista.size());
			p1.add(Chunk.NEWLINE);
			p1.add(Chunk.NEWLINE);
			p1.setAlignment(Element.ALIGN_RIGHT);
			doc.add(p1);
			doc.close();
			archivo.close();
			Desktop.getDesktop().open(file);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "ERROR AL CREAR ARCHIVO");
		} catch (DocumentException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "ERROR AL CREAR DOCUMENTO PDF");
		} catch (IOException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "ERROR AL CREAR IO");
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public vProveedor() {
		//setIconImage(Toolkit.getDefaultToolkit().getImage(vProveedor.class.getResource("/Img/icono.jpg")));
		//setLocationRelativeTo(null);
		setTitle("Proveedor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 920, 530);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		Funciones fx = new Funciones();

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("id");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new java.awt.Font("Verdana", java.awt.Font.ITALIC, 11));
		lblNewLabel.setBounds(20, 26, 33, 23);
		contentPane.add(lblNewLabel);
		
		lblid = new JLabel("1");
		lblid.setForeground(new Color(255, 255, 255));
		lblid.setFont(new java.awt.Font("Verdana", java.awt.Font.ITALIC, 11));
		lblid.setHorizontalAlignment(SwingConstants.LEFT);
		lblid.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblid.setBounds(73, 26, 86, 23);
		contentPane.add(lblid);
		
		JLabel lblNewLabel_1 = new JLabel("Contacto");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new java.awt.Font("Verdana", java.awt.Font.ITALIC, 11));
		lblNewLabel_1.setBounds(10, 92, 53, 23);
		contentPane.add(lblNewLabel_1);
		
		txtdescripcion = new JTextField();
		txtdescripcion.setForeground(new Color(255, 255, 255));
		txtdescripcion.setBorder(new LineBorder(new Color(255, 255, 255)));
		txtdescripcion.setBackground(new Color(0, 0, 0));
		txtdescripcion.setFont(new java.awt.Font("Verdana", java.awt.Font.ITALIC, 11));
		txtdescripcion.setBounds(73, 127, 86, 20);
		contentPane.add(txtdescripcion);
		txtdescripcion.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Descripcion");
		lblNewLabel_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1.setFont(new java.awt.Font("Verdana", java.awt.Font.ITALIC, 11));
		lblNewLabel_1_1.setBounds(10, 126, 86, 23);
		contentPane.add(lblNewLabel_1_1);
		
		txtcontacto = new JTextField();
		txtcontacto.setForeground(new Color(255, 255, 255));
		txtcontacto.setBorder(new LineBorder(new Color(255, 255, 255)));
		txtcontacto.setBackground(new Color(0, 0, 0));
		txtcontacto.setFont(new java.awt.Font("Verdana", java.awt.Font.ITALIC, 11));
		txtcontacto.setColumns(10);
		txtcontacto.setBounds(73, 94, 86, 20);
		contentPane.add(txtcontacto);
		
		JLabel lblNewLabel_1_2 = new JLabel("Nombre");
		lblNewLabel_1_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_2.setFont(new java.awt.Font("Verdana", java.awt.Font.ITALIC, 11));
		lblNewLabel_1_2.setBounds(10, 60, 53, 23);
		contentPane.add(lblNewLabel_1_2);
		
		txtnombre = new JTextField();
		txtnombre.setForeground(new Color(255, 255, 255));
		txtnombre.setBorder(new LineBorder(new Color(255, 255, 255)));
		txtnombre.setBackground(new Color(0, 0, 0));
		txtnombre.setFont(new java.awt.Font("Verdana", java.awt.Font.ITALIC, 11));
		txtnombre.setColumns(10);
		txtnombre.setBounds(73, 60, 86, 20);
		contentPane.add(txtnombre);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setForeground(new Color(255, 255, 255));
		btnAgregar.setFont(new java.awt.Font("Verdana", java.awt.Font.ITALIC, 11));
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(txtnombre.getText().equals("")||txtcontacto.getText().equals("")||txtdescripcion.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "campos vacios");
						return;
					}
					Proveedor user=new Proveedor();
					user.setNombreproveedor(txtnombre.getText());
					user.setContacto(Integer.parseInt(txtcontacto.getText()));
					user.setDescripcion(txtdescripcion.getText());
					if (dao.insertarProveedor(user)) {
						refrescarTabla();
						limpiar();
						JOptionPane.showMessageDialog(null, "Se agrego correctamente");
					}else {
						JOptionPane.showMessageDialog(null, "Error");
					}
				}catch(Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error");
				}
				
			}
		});

	    btnAgregar.setIcon(fx.cambiar(new ImageIcon(getClass().getResource("/img/agregar.png")), 20,20 ));
        btnAgregar.setBackground(new Color(0, 0, 0)); 
        btnAgregar.setHorizontalTextPosition(SwingConstants.CENTER);
        btnAgregar.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnAgregar.setHorizontalAlignment(SwingConstants.CENTER);
        this.getContentPane().add(btnAgregar, new AbsoluteConstraints(10, 240, 80, 80));
		contentPane.add(btnAgregar);
		
	btnAgregar.setBounds(411, 141, 89, 50);
	contentPane.add(btnAgregar);
	
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setForeground(new Color(255, 255, 255));
		btnEliminar.setFont(new java.awt.Font("Verdana", java.awt.Font.ITALIC, 11));
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int opcion =JOptionPane.showConfirmDialog(null , "Estas seguro de eliminar");
					if(opcion==0) {
					if (dao.eliminarProveedor(lista.get(fila).getIdproveedor())) {
						refrescarTabla();
						limpiar();
						JOptionPane.showMessageDialog(null, "Se elimino correctamente");
					}else {
						JOptionPane.showMessageDialog(null, "Error");
					}
					}
				}catch(Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error");
				}
				
			}
		});

	    btnEliminar.setIcon(fx.cambiar(new ImageIcon(getClass().getResource("/img/Eliminar.png")), 20,20 ));
        btnEliminar.setBackground(new Color(0, 0, 0));
        btnEliminar.setHorizontalTextPosition(SwingConstants.CENTER);
        btnEliminar.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnEliminar.setHorizontalAlignment(SwingConstants.CENTER);
        this.getContentPane().add(btnEliminar, new AbsoluteConstraints(10, 240, 80, 80));
		contentPane.add(btnEliminar);
	    btnEliminar.setBounds(411, 36, 89, 47);
	    contentPane.add(btnEliminar);
		btnEditar = new JButton("editar");
		btnEditar.setForeground(new Color(255, 255, 255));
		btnEditar.setFont(new java.awt.Font("Verdana", java.awt.Font.ITALIC, 11));
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(txtnombre.getText().equals("")||txtcontacto.getText().equals("")||txtdescripcion.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "campos vacios");
						return;
					}
					Proveedor.setNombreproveedor(txtnombre.getText());
					Proveedor.setContacto(Integer.parseInt(txtcontacto.getText()));
					Proveedor.setDescripcion(txtdescripcion.getText());
					if (dao.editarProveedor(Proveedor)) {
						refrescarTabla();
						limpiar();
						JOptionPane.showMessageDialog(null, "Se edito correctamente");
					}else {
						JOptionPane.showMessageDialog(null, "Error");
					}
				}catch (Exception e2) {
					e2.printStackTrace();
				}
				
			}
		});
		btnEditar.setIcon(fx.cambiar(new ImageIcon(getClass().getResource("/img/guardar.png")), 20,20 ));
        btnEditar.setBackground(new Color(0, 0, 0));
        btnEditar.setHorizontalTextPosition(SwingConstants.CENTER);
        btnEditar.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnEditar.setHorizontalAlignment(SwingConstants.CENTER);
        this.getContentPane().add(btnEditar, new AbsoluteConstraints(10, 240, 80, 80));
		contentPane.add(btnEditar);
		btnEditar.setBounds(640, 36, 89, 47);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 236, 837, 238);
		contentPane.add(scrollPane);
		
		tblProveedor = new JTable();
		tblProveedor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fila=tblProveedor.getSelectedRow();
				Proveedor=lista.get(fila);
				lblid.setText(""+lista.get(fila).getIdproveedor());
				txtnombre.setText(""+Proveedor.getNombreproveedor());
				txtcontacto.setText(""+Proveedor.getContacto());
				txtdescripcion.setText(""+Proveedor.getDescripcion());
				
				
			}
		});
		tblProveedor.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column"
			}
		));
		scrollPane.setViewportView(tblProveedor);
		
		modelo.addColumn("ID");
		modelo.addColumn("Nombre");
		modelo.addColumn("Contacto");
		modelo.addColumn("Descripcion");
		tblProveedor.setModel(modelo);
		
		btnpdf = new JButton("");
		btnpdf.setFont(new java.awt.Font("Verdana", java.awt.Font.ITALIC, 11));
		btnpdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			pdf();
			}
		});
		btnpdf.setIcon(fx.cambiar(new ImageIcon(getClass().getResource("/img/pdf.png")), 40,40 ));
		btnpdf.setBackground(new Color(0, 0, 0));
		btnpdf.setHorizontalTextPosition(SwingConstants.CENTER);
		btnpdf.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnpdf.setHorizontalAlignment(SwingConstants.CENTER);
	    this.getContentPane().add(btnpdf, new AbsoluteConstraints(10, 240, 80, 80));
		contentPane.add(btnpdf);
		contentPane.add(btnpdf);
		
		btnpdf.setBounds(640, 141, 89, 50);
		contentPane.add(btnpdf);
		
		lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setOpaque(true);
		lblNewLabel_2.setBackground(new Color(0, 0, 0));
		lblNewLabel_2.setBounds(0, 0, 916, 531);
		contentPane.add(lblNewLabel_2);
		refrescarTabla();
	}
	public void refrescarTabla() {
		while(modelo.getRowCount()>0) {
		modelo.removeRow(0);
		}
		lista=dao.fetchProveedor();
		for(Proveedor u: lista) {
			Object o[]=new Object [4];
			o[0]=u.getIdproveedor();
			o[1]=u.getNombreproveedor();
			o[2]=u.getContacto();
			o[3]=u.getDescripcion();
			modelo.addRow(o);
		}
		tblProveedor.setModel(modelo);
	}
}