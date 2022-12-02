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

import Dao.DaoInventario;
import Dao.DaoProducto;
import Modelo.Inventario;
import Modelo.Producto;
import Modelo.Venta;

import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;

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

public class vInventario extends JInternalFrame {

	private JPanel contentPane;
	private JLabel lblid;
	private JTextField txtcantidad;
	private JTextField txtFecha;
	private JButton btnAgregar;
	private JButton btnEliminar;
	private JButton btnEditar;
	DaoInventario dao=new DaoInventario();
	DefaultTableModel modelo=new DefaultTableModel();
	private JScrollPane scrollPane;
	private JTable tblInventario;
	ArrayList<Inventario> lista = new ArrayList<Inventario>();
	int fila=-1;
	Inventario Inventario;
	ArrayList<Producto> listaProducto=new ArrayList<Producto>();
	private JComboBox cboproducto;
	private JComboBox cboTipodemovimiento;
	private JButton btnPdf;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vInventario frame = new vInventario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void limpiar() {
		lblid.setText("");
		txtcantidad.setText("");
		txtFecha.setText("");
	}
	public void cargarComboInventario() {
		DaoProducto daoPro=new DaoProducto();		
		listaProducto=daoPro.fetchProductos();
		DefaultComboBoxModel model=new DefaultComboBoxModel();
		for (Producto Inventario : listaProducto) {
			model.addElement(Inventario.getDescripcion());
		}
		cboproducto.setModel(model);
	}
	

	
	public int Producto(int id) {
		for (Producto Proveedor : listaProducto) {
			if(Inventario.getIdproducto()==id) {
				return Inventario.getIdproducto();
			}
		}
		return (Integer) null;
	}


	public vInventario() {
		//Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
		//(d);
		setClosable(true);
		//setIconImage(Toolkit.getDefaultToolkit().getImage(vInventario.class.getResource("/Img/icono.jpg")));
		//setLocationRelativeTo(null);
		setTitle("Inventario");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 504, 502);
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
		
		JLabel lblNewLabel_1 = new JLabel("Fecha");
		lblNewLabel_1.setBounds(10, 92, 133, 23);
		contentPane.add(lblNewLabel_1);
		
		txtcantidad = new JTextField();
		txtcantidad.setBounds(163, 124, 86, 20);
		contentPane.add(txtcantidad);
		txtcantidad.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Cantidad");
		lblNewLabel_1_1.setBounds(10, 123, 133, 23);
		contentPane.add(lblNewLabel_1_1);
		
		txtFecha = new JTextField();
		txtFecha.setColumns(10);
		txtFecha.setBounds(163, 93, 86, 20);
		contentPane.add(txtFecha);
		
		JLabel lblNewLabel_1_2 = new JLabel("Producto");
		lblNewLabel_1_2.setBounds(10, 58, 133, 23);
		contentPane.add(lblNewLabel_1_2);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(cboproducto.getSelectedItem().equals("")||txtFecha.getText().equals("")||txtcantidad.getText().equals("")||cboTipodemovimiento.getSelectedItem().equals("")) {
						JOptionPane.showMessageDialog(null, "campos vacios");
						return;
					}
					Inventario user=new Inventario();
					user.setIdproducto(Integer.parseInt(cboproducto.getSelectedItem().toString()));
					user.setFecha(Integer.parseInt(txtFecha.getText()));
					user.setCantidad(Integer.parseInt(txtcantidad.getText()));
					user.setTipodemovimiento(cboTipodemovimiento.getSelectedItem().toString());
					if (dao.insertarInventario(user)) {
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
					if (dao.eliminarInventario(lista.get(fila).getIdinventario())) {
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
					if(cboproducto.getSelectedItem().equals("")||txtFecha.getText().equals("")||txtcantidad.getText().equals("")||cboTipodemovimiento.getSelectedItem().equals("")) {
						JOptionPane.showMessageDialog(null, "campos vacios");
						return;
					}
					Inventario.setIdproducto(Integer.parseInt(cboproducto.getSelectedItem().toString()));
					Inventario.setFecha(Integer.parseInt(txtFecha.getText()));
					Inventario.setCantidad(Integer.parseInt(txtcantidad.getText()));
					Inventario.setTipodemovimiento(cboTipodemovimiento.getSelectedItem().toString());
					if (dao.editarInventario(Inventario)) {
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
		scrollPane.setBounds(10, 218, 468, 238);
		contentPane.add(scrollPane);
		
		tblInventario = new JTable();
		tblInventario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fila=tblInventario.getSelectedRow();
				Inventario=lista.get(fila);
				lblid.setText(""+lista.get(fila).getIdinventario());
				cboproducto.setSelectedItem(""+Inventario.getIdproducto());
				txtFecha.setText(""+Inventario.getFecha());
				txtcantidad.setText(""+Inventario.getCantidad());
				cboTipodemovimiento.setSelectedItem(""+Inventario.getTipodemovimiento());
				
			}
		});
		tblInventario.setModel(new DefaultTableModel(
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
		scrollPane.setViewportView(tblInventario);
		
		modelo.addColumn("ID");
		modelo.addColumn("producto");
		modelo.addColumn("Fecha");
		modelo.addColumn("Cantidad");
		modelo.addColumn("Tipo De Movimiento");
		tblInventario.setModel(modelo);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Tipo de movimiento");
		lblNewLabel_1_1_1.setBounds(10, 150, 133, 23);
		contentPane.add(lblNewLabel_1_1_1);
		
		cboproducto = new JComboBox();
		cboproducto.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				cargarComboInventario();
			}
		});
		cboproducto.setBounds(163, 58, 86, 22);
		contentPane.add(cboproducto);
		
		cboTipodemovimiento = new JComboBox();
		cboTipodemovimiento.setModel(new DefaultComboBoxModel(new String[] {"salida", "entrada"}));
		cboTipodemovimiento.setBounds(163, 155, 86, 22);
		contentPane.add(cboTipodemovimiento);
		
		btnPdf = new JButton("PDF");
		btnPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FileOutputStream archivo;
					URI uri = new URI(getClass().getResource("/PDF/Inventario.pdf").toString());
					File file = new File(uri);
					archivo = new FileOutputStream(file);
					Document doc = new Document();
					PdfWriter.getInstance(doc, archivo);
					doc.open();
					java.awt.Image img2 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Img/icono.jpg"));
					Image img = Image.getInstance(getClass().getResource("/Img/icono.jpg"));
					img.setAlignment(Element.ALIGN_CENTER);
		            img.scaleToFit(200, 200);
					doc.add(img);
					Paragraph p = new Paragraph(10);
					com.itextpdf.text.Font negrita = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK);
					p.add(Chunk.NEWLINE);
					p.add("Invetario");
					p.add(Chunk.NEWLINE);
					p.add(Chunk.NEWLINE);
					p.setAlignment(Element.ALIGN_CENTER);
					doc.add(p);
					PdfPTable tabla = new PdfPTable(5);
					tabla.setWidthPercentage(100);
					PdfPCell c1 = new PdfPCell(new Phrase(" Idinventario", negrita));
					PdfPCell c2 = new PdfPCell(new Phrase(" Idproducto", negrita));
					PdfPCell c3 = new PdfPCell(new Phrase(" Fecha", negrita));
					PdfPCell c4 = new PdfPCell(new Phrase(" Cantidad", negrita));
					PdfPCell c5 = new PdfPCell(new Phrase(" Tipodemovimiento", negrita));		
					c1.setHorizontalAlignment(Element.ALIGN_CENTER);
					c2.setHorizontalAlignment(Element.ALIGN_CENTER);
					c3.setHorizontalAlignment(Element.ALIGN_CENTER);
					c4.setHorizontalAlignment(Element.ALIGN_CENTER);
					c5.setHorizontalAlignment(Element.ALIGN_CENTER);
					c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
					c2.setBackgroundColor(BaseColor.LIGHT_GRAY);
					c3.setBackgroundColor(BaseColor.LIGHT_GRAY);
					c4.setBackgroundColor(BaseColor.LIGHT_GRAY);
					c5.setBackgroundColor(BaseColor.LIGHT_GRAY);
					tabla.addCell(c1);
					tabla.addCell(c2);
					tabla.addCell(c3);
					tabla.addCell(c4);
					tabla.addCell(c5);

					for (Inventario u : lista) {
						tabla.addCell("" + u.getIdinventario());
						tabla.addCell("" + u.getIdproducto());
						tabla.addCell("" + u.getFecha());
						tabla.addCell("" + u.getCantidad());
						tabla.addCell("" + u.getTipodemovimiento());

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
		});
		btnPdf.setBounds(294, 123, 89, 23);
		contentPane.add(btnPdf);
		cargarComboInventario();
		refrescarTabla();
	}
	public void refrescarTabla() {
		while(modelo.getRowCount()>0) {
		modelo.removeRow(0);
		}
		lista=dao.fetchInventarios();
		for(Inventario u: lista) {
			Object o[]=new Object [6];
			o[0]=u.getIdinventario();
			o[1]=u.getIdproducto();
			o[2]=u.getFecha();
			o[3]=u.getCantidad();
			o[4]=u.getTipodemovimiento();
			modelo.addRow(o);
		}
		tblInventario.setModel(modelo);
	}
}

