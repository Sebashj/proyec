package Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultTreeCellEditor.DefaultTextField;

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

import Dao.DaoProducto;
import Dao.DaoProveedor;
import Modelo.DetalleV;
import Modelo.Producto;
import Modelo.Proveedor;
import Modelo.Venta;

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
import javax.swing.JComboBox;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class vProducto extends JInternalFrame {

	private JPanel contentPane;
	private JLabel lblid;
	private JTextField txtprecio;
	private JTextField txtcodigo;
	private JButton btnAgregar;
	private JButton btnEliminar;
	private JButton btnEditar;
	DaoProducto dao=new DaoProducto();
	DefaultTableModel modelo=new DefaultTableModel();
	private JScrollPane scrollPane;
	private JTable tblProducto;
	ArrayList<Producto> lista = new ArrayList<Producto>();
	int fila=-1;
	Producto Producto;
	private JTextField txtprecioventa;
	ArrayList<Proveedor> listaProveedor=new ArrayList<Proveedor>();
	private JComboBox cbodescripcion;
	private JComboBox cboprovedor;
	private JButton btnpdf;
	private JLabel lblNewLabel_2;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vProducto frame = new vProducto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void limpiar() {
		lblid.setText("");
		txtprecio.setText("");
		txtcodigo.setText("");
		txtprecioventa.setText("");
	}
	public void pdf() {
		try {
			FileOutputStream archivo;
			URI uri = new URI(getClass().getResource("/PDF/producto.pdf").toString());
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
			p.add("Producto");
			p.add(Chunk.NEWLINE);
			p.add(Chunk.NEWLINE);
			p.setAlignment(Element.ALIGN_CENTER);
			doc.add(p);
			PdfPTable tabla = new PdfPTable(6);
			tabla.setWidthPercentage(100);
			PdfPCell c1 = new PdfPCell(new Phrase(" Idproducto", negrita));
			PdfPCell c2 = new PdfPCell(new Phrase(" Idproveedor", negrita));
			PdfPCell c3 = new PdfPCell(new Phrase(" Codigo", negrita));
			PdfPCell c4 = new PdfPCell(new Phrase(" Precio", negrita));
			PdfPCell c5 = new PdfPCell(new Phrase(" Precioventa", negrita));
			PdfPCell c6 = new PdfPCell(new Phrase(" Descripcion", negrita));		
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
			c3.setHorizontalAlignment(Element.ALIGN_CENTER);
			c4.setHorizontalAlignment(Element.ALIGN_CENTER);
			c5.setHorizontalAlignment(Element.ALIGN_CENTER);
			c6.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
			c2.setBackgroundColor(BaseColor.LIGHT_GRAY);
			c3.setBackgroundColor(BaseColor.LIGHT_GRAY);
			c4.setBackgroundColor(BaseColor.LIGHT_GRAY);
			c5.setBackgroundColor(BaseColor.LIGHT_GRAY);
			c6.setBackgroundColor(BaseColor.LIGHT_GRAY);
			tabla.addCell(c1);
			tabla.addCell(c2);
			tabla.addCell(c3);
			tabla.addCell(c4);
			tabla.addCell(c5);
			tabla.addCell(c6);

			for (Producto u : lista) {
				tabla.addCell("" + u.getIdproducto());
				tabla.addCell("" + u.getIdproveedor());
				tabla.addCell("" + u.getCodigo());
				tabla.addCell("" + u.getPrecio());
				tabla.addCell("" + u.getPrecioventa());
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

	public void cargarComboProducto() {
		DaoProveedor daoPro=new DaoProveedor();		
		listaProveedor=daoPro.fetchProveedor();
		DefaultComboBoxModel model=new DefaultComboBoxModel();
		for (Proveedor Producto : listaProveedor) {
			model.addElement(Producto.getIdproveedor());
		}
		cbodescripcion.setModel(model);	
		cboprovedor.setModel(model);
	}

	public String Proveedor(int id) {
		for (Proveedor Proveedor : listaProveedor) {
			if(Producto.getIdproveedor()==id) {
				return Producto.getDescripcion();
			}
		}
		return null;
	}


	public vProducto() {
		//setIconImage(Toolkit.getDefaultToolkit().getImage(vProducto.class.getResource("/Img/icono.jpg")));
		//setLocationRelativeTo(null);
		setTitle("Producto");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 920, 530);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		Funciones fx = new Funciones();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("id");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new java.awt.Font("Verdana", java.awt.Font.PLAIN, 11));
		lblNewLabel.setBounds(20, 26, 33, 23);
		contentPane.add(lblNewLabel);
		
		lblid = new JLabel("1");
		lblid.setForeground(new Color(255, 255, 255));
		lblid.setFont(new java.awt.Font("Verdana", java.awt.Font.PLAIN, 11));
		lblid.setHorizontalAlignment(SwingConstants.LEFT);
		lblid.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblid.setBounds(163, 26, 86, 23);
		contentPane.add(lblid);
		
		JLabel lblNewLabel_1 = new JLabel("Codigo");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new java.awt.Font("Verdana", java.awt.Font.PLAIN, 11));
		lblNewLabel_1.setBounds(10, 92, 133, 23);
		contentPane.add(lblNewLabel_1);
		
		txtprecio = new JTextField();
		txtprecio.setForeground(new Color(255, 255, 255));
		txtprecio.setBorder(new LineBorder(new Color(255, 255, 255)));
		txtprecio.setBackground(new Color(0, 0, 0));
		txtprecio.setFont(new java.awt.Font("Verdana", java.awt.Font.PLAIN, 11));
		txtprecio.setBounds(163, 124, 86, 20);
		contentPane.add(txtprecio);
		txtprecio.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Precio");
		lblNewLabel_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1.setFont(new java.awt.Font("Verdana", java.awt.Font.PLAIN, 11));
		lblNewLabel_1_1.setBounds(10, 123, 133, 23);
		contentPane.add(lblNewLabel_1_1);
		
		txtcodigo = new JTextField();
		txtcodigo.setForeground(new Color(255, 255, 255));
		txtcodigo.setBorder(new LineBorder(new Color(255, 255, 255)));
		txtcodigo.setBackground(new Color(0, 0, 0));
		txtcodigo.setFont(new java.awt.Font("Verdana", java.awt.Font.PLAIN, 11));
		txtcodigo.setColumns(10);
		txtcodigo.setBounds(163, 93, 86, 20);
		contentPane.add(txtcodigo);
		
		JLabel lblNewLabel_1_2 = new JLabel("idProovedor");
		lblNewLabel_1_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_2.setFont(new java.awt.Font("Verdana", java.awt.Font.PLAIN, 11));
		lblNewLabel_1_2.setBounds(10, 58, 133, 23);
		contentPane.add(lblNewLabel_1_2);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setForeground(new Color(255, 255, 255));
		btnAgregar.setFont(new java.awt.Font("Verdana", java.awt.Font.PLAIN, 11));
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(cboprovedor.getSelectedItem().equals("")||txtcodigo.getText().equals("")||txtprecio.getText().equals("")||txtprecioventa.getText().equals("")||cbodescripcion.getSelectedItem().equals("")) {
						JOptionPane.showMessageDialog(null, "campos vacios");
						return;
					}
					Producto user=new Producto();
					user.setIdproveedor(Integer.parseInt(cboprovedor.getSelectedItem().toString()));
					user.setCodigo(Integer.parseInt(txtcodigo.getText()));
					user.setPrecio(Double.parseDouble(txtprecio.getText()));
					user.setPrecioventa(Double.parseDouble(txtprecioventa.getText()));
					user.setDescripcion(cbodescripcion.getSelectedItem().toString());
					if (dao.insertarProducto(user)) {
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
		
	btnAgregar.setBounds(398, 32, 89, 50);
	contentPane.add(btnAgregar);
	
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setForeground(new Color(255, 255, 255));
		btnEliminar.setFont(new java.awt.Font("Verdana", java.awt.Font.PLAIN, 11));
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int opcion =JOptionPane.showConfirmDialog(null , "Estas seguro de eliminar");
					if(opcion==0) {
					if (dao.eliminarProducto(lista.get(fila).getIdproducto())) {
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
	    btnEliminar.setBounds(398, 160, 89, 47);
	    contentPane.add(btnEliminar);
		
		btnEditar = new JButton("editar");
		btnEditar.setForeground(new Color(255, 255, 255));
		btnEditar.setFont(new java.awt.Font("Verdana", java.awt.Font.PLAIN, 11));
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(cboprovedor.getSelectedItem().equals("")||txtcodigo.getText().equals("")||txtprecio.getText().equals("")||txtprecioventa.getText().equals("")||cbodescripcion.getSelectedItem().equals("")) {
						JOptionPane.showMessageDialog(null, "campos vacios");
						return;
					}
					Producto.setIdproveedor(Integer.parseInt(cboprovedor.getSelectedItem().toString()));
					Producto.setCodigo(Integer.parseInt(txtcodigo.getText()));
					Producto.setPrecio(Double.parseDouble(txtprecio.getText()));
					Producto.setPrecioventa(Double.parseDouble(txtprecioventa.getText()));
					Producto.setDescripcion(cbodescripcion.getSelectedItem().toString());
					if (dao.editarProducto(Producto)) {
						refrescarTabla();
						limpiar();
						JOptionPane.showMessageDialog(null, "Se edito correctamente");
					}else {
						JOptionPane.showMessageDialog(null, "Error");
					}
				}catch (Exception e2) {
					
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
		btnEditar.setBounds(675, 34, 89, 47);
		
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 235, 848, 238);
		contentPane.add(scrollPane);
		
		tblProducto = new JTable();
		tblProducto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fila=tblProducto.getSelectedRow();
				Producto=lista.get(fila);
				lblid.setText(""+lista.get(fila).getIdproducto());
				cboprovedor.setSelectedItem(""+Producto.getIdproveedor());
				txtcodigo.setText(""+Producto.getCodigo());
				txtprecio.setText(""+Producto.getPrecio());
				txtprecioventa.setText(""+Producto.getPrecioventa());
				cbodescripcion.setSelectedItem(""+Producto.getDescripcion());
				
			}
		});
		tblProducto.setModel(new DefaultTableModel(
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
		scrollPane.setViewportView(tblProducto);
		
		modelo.addColumn("ID");
		modelo.addColumn("Proveedor");
		modelo.addColumn("codigo");
		modelo.addColumn("precio");
		modelo.addColumn("precioventa");
		modelo.addColumn("descripcion");
		tblProducto.setModel(modelo);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Precio Venta");
		lblNewLabel_1_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1_1.setFont(new java.awt.Font("Verdana", java.awt.Font.PLAIN, 11));
		lblNewLabel_1_1_1.setBounds(10, 150, 133, 23);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Descripcion");
		lblNewLabel_1_1_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1_2.setFont(new java.awt.Font("Verdana", java.awt.Font.PLAIN, 11));
		lblNewLabel_1_1_2.setBounds(10, 184, 133, 23);
		contentPane.add(lblNewLabel_1_1_2);
		
		txtprecioventa = new JTextField();
		txtprecioventa.setForeground(new Color(255, 255, 255));
		txtprecioventa.setBorder(new LineBorder(new Color(255, 255, 255)));
		txtprecioventa.setBackground(new Color(0, 0, 0));
		txtprecioventa.setFont(new java.awt.Font("Verdana", java.awt.Font.PLAIN, 11));
		txtprecioventa.setColumns(10);
		txtprecioventa.setBounds(163, 155, 86, 20);
		contentPane.add(txtprecioventa);
		
		cbodescripcion = new JComboBox();
		cbodescripcion.setForeground(new Color(255, 255, 255));
		cbodescripcion.setBorder(new LineBorder(new Color(255, 255, 255)));
		cbodescripcion.setBackground(new Color(0, 0, 0));
		cbodescripcion.setFont(new java.awt.Font("Verdana", java.awt.Font.PLAIN, 11));
		cbodescripcion.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				cargarComboProducto();
			}
		});
		cbodescripcion.setBounds(163, 185, 86, 22);
		contentPane.add(cbodescripcion);
		
		cboprovedor = new JComboBox();
		cboprovedor.setForeground(new Color(255, 255, 255));
		cboprovedor.setBorder(new LineBorder(new Color(255, 255, 255)));
		cboprovedor.setBackground(new Color(0, 0, 0));
		cboprovedor.setFont(new java.awt.Font("Verdana", java.awt.Font.PLAIN, 11));
		cboprovedor.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				cargarComboProducto();
			}
		});
		cboprovedor.setBounds(163, 58, 86, 22);
		contentPane.add(cboprovedor);
		
		btnpdf = new JButton("");
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
		
		btnpdf.setBounds(675, 157, 89, 50);
		contentPane.add(btnpdf);
		
		lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setOpaque(true);
		lblNewLabel_2.setBackground(new Color(0, 0, 0));
		lblNewLabel_2.setBounds(0, 0, 915, 518);
		contentPane.add(lblNewLabel_2);
		cargarComboProducto();
		refrescarTabla();
	}
	
	public void refrescarTabla() {
		while(modelo.getRowCount()>0) {
		modelo.removeRow(0);
		}
		lista=dao.fetchProductos();
		for(Producto u: lista) {
			Object o[]=new Object [6];
			o[0]=u.getIdproducto();
			o[1]=u.getIdproveedor();
			o[2]=u.getCodigo();
			o[3]=u.getPrecio();
			o[4]=u.getPrecioventa();
			o[5]=u.getDescripcion();
			modelo.addRow(o);
		}
		tblProducto.setModel(modelo);
	}
}