package Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
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
import java.awt.Toolkit;
import javax.swing.JComboBox;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class vProducto extends JFrame {

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
	private JButton btnPdf;
	

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
	public void cargarComboProducto() {
		DaoProveedor daoPro=new DaoProveedor();		
		listaProveedor=daoPro.fetchProveedor();
		DefaultComboBoxModel model=new DefaultComboBoxModel();
		for (Proveedor Producto : listaProveedor) {
		 	model.addElement(Producto.getDescripcion());
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
		setIconImage(Toolkit.getDefaultToolkit().getImage(vProducto.class.getResource("/Img/icono.jpg")));
		setLocationRelativeTo(null);
		setTitle("Producto");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 432, 502);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("id");
		lblNewLabel.setBounds(20, 26, 33, 23);
		contentPane.add(lblNewLabel);
		
		lblid = new JLabel("1");
		lblid.setHorizontalAlignment(SwingConstants.LEFT);
		lblid.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblid.setBounds(73, 26, 86, 23);
		contentPane.add(lblid);
		
		JLabel lblNewLabel_1 = new JLabel("Codigo");
		lblNewLabel_1.setBounds(10, 92, 133, 23);
		contentPane.add(lblNewLabel_1);
		
		txtprecio = new JTextField();
		txtprecio.setBounds(163, 124, 86, 20);
		contentPane.add(txtprecio);
		txtprecio.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Precio");
		lblNewLabel_1_1.setBounds(10, 123, 133, 23);
		contentPane.add(lblNewLabel_1_1);
		
		txtcodigo = new JTextField();
		txtcodigo.setColumns(10);
		txtcodigo.setBounds(163, 93, 86, 20);
		contentPane.add(txtcodigo);
		
		JLabel lblNewLabel_1_2 = new JLabel("idProovedor");
		lblNewLabel_1_2.setBounds(10, 58, 133, 23);
		contentPane.add(lblNewLabel_1_2);
		
		btnAgregar = new JButton("Agregar");
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
		btnAgregar.setBounds(294, 26, 89, 23);
		contentPane.add(btnAgregar);
		
		btnEliminar = new JButton("Eliminar");
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
		btnEliminar.setBounds(294, 58, 89, 23);
		contentPane.add(btnEliminar);
		
		btnEditar = new JButton("editar");
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
		btnEditar.setBounds(294, 92, 89, 23);
		contentPane.add(btnEditar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 218, 398, 238);
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
		lblNewLabel_1_1_1.setBounds(10, 150, 133, 23);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Descripcion");
		lblNewLabel_1_1_2.setBounds(10, 184, 133, 23);
		contentPane.add(lblNewLabel_1_1_2);
		
		txtprecioventa = new JTextField();
		txtprecioventa.setColumns(10);
		txtprecioventa.setBounds(163, 155, 86, 20);
		contentPane.add(txtprecioventa);
		
		cbodescripcion = new JComboBox();
		cbodescripcion.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				cargarComboProducto();
			}
		});
		cbodescripcion.setBounds(163, 185, 86, 22);
		contentPane.add(cbodescripcion);
		
		cboprovedor = new JComboBox();
		cboprovedor.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				cargarComboProducto();
			}
		});
		cboprovedor.setBounds(163, 58, 86, 22);
		contentPane.add(cboprovedor);
		
		btnPdf = new JButton("pdf");
		btnPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FileOutputStream archivo;
					File file = new File("C:\\Users\\Alumno.SALA2-PC35\\git\\Proyectofilan\\Proyectofinal\\src\\PDF\\producto.pdf");
					archivo = new FileOutputStream(file);
					Document doc = new Document();
					PdfWriter.getInstance(doc, archivo);
					doc.open();
					Image img = Image.getInstance("C:\\Users\\Alumno.SALA2-PC35\\git\\Proyectofilan\\Proyectofinal\\src\\Img\\icono.jpg");
					img.setAlignment(Element.ALIGN_CENTER);
		            img.scaleToFit(200, 200);
					doc.add(img);
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
				}
			}
		});
		btnPdf.setBounds(294, 123, 89, 23);
		contentPane.add(btnPdf);
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
