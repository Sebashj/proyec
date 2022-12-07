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

import Dao.DaoDetalleV;
import Dao.DaoProducto;
import Dao.DaoProveedor;
import Dao.DaoVenta;
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

public class vDetalleV extends JInternalFrame {

	private JPanel contentPane;
	private JLabel lblid;
	private JTextField txtcosto;
	private JTextField txtimporte;
	private JButton btnAgregar;
	private JButton btnEliminar;
	private JButton btnEditar;
	DaoDetalleV dao=new DaoDetalleV();
	DefaultTableModel modelo=new DefaultTableModel();
	private JScrollPane scrollPane;
	private JTable tblDetalleV;
	ArrayList<DetalleV> lista = new ArrayList<DetalleV>();
	int fila=-1;
	DetalleV DetalleV;
	private JTextField txtcantidad;
	ArrayList<Producto> listaProducto=new ArrayList<Producto>();
	ArrayList<Proveedor> listaProveedor=new ArrayList<Proveedor>();
	ArrayList<Venta> listaVenta=new ArrayList<Venta>();
	private JComboBox cboventa;
	private JComboBox cboproducto;
	private JButton btnpdf;
	private JLabel lblNewLabel_2;
	public void pdf() {
		try {
			FileOutputStream archivo;
			URI uri = new URI(getClass().getResource("/PDF/detalleV.pdf").toString());
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
			p.add("Detalle Venta");
			p.add(Chunk.NEWLINE);
			p.add(Chunk.NEWLINE);
			p.setAlignment(Element.ALIGN_CENTER);
			doc.add(p);
			PdfPTable tabla = new PdfPTable(6);
			tabla.setWidthPercentage(100);
			PdfPCell c1 = new PdfPCell(new Phrase(" Iddetalle", negrita));
			PdfPCell c2 = new PdfPCell(new Phrase(" Idproducto", negrita));
			PdfPCell c3 = new PdfPCell(new Phrase(" Idventa", negrita));
			PdfPCell c4 = new PdfPCell(new Phrase(" Costo", negrita));
			PdfPCell c5 = new PdfPCell(new Phrase(" Cantidad", negrita));
			PdfPCell c6 = new PdfPCell(new Phrase(" Importe", negrita));		
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

			for (DetalleV u : lista) {
				tabla.addCell("" + u.getIddetalle());
				tabla.addCell("" + u.getIdproducto());
				tabla.addCell("" + u.getIdventa());
				tabla.addCell("" + u.getCosto());
				tabla.addCell("" + u.getCantidad());
				tabla.addCell("" + u.getImporte());

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

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vDetalleV frame = new vDetalleV();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void limpiar() {
		lblid.setText("");
		txtcosto.setText("");
		txtimporte.setText("");
		txtcantidad.setText("");
	}
	
	
	public void cargarComboProducto() {
		DaoProveedor daoPro=new DaoProveedor();		
		listaProveedor=daoPro.fetchProveedor();
		DefaultComboBoxModel model=new DefaultComboBoxModel();
		for (Proveedor Producto : listaProveedor) {
		 	model.addElement(Producto.getIdproveedor());
		}
		cboproducto.setModel(model);
	}

	public int Producto(int id) {
		for (Producto Producto : listaProducto) {
			if(DetalleV.getIdproducto()==id) {
				return DetalleV.getIdproducto();
			}
		}
		return (Integer) null;
	}
	
	public void cargarComboVenta() {
		DaoVenta daoPro=new DaoVenta();		
		listaVenta=daoPro.fetchVentas();
		DefaultComboBoxModel model=new DefaultComboBoxModel();
		for (Venta DetalleV : listaVenta) {
		 	model.addElement(DetalleV.getNopedido());
		}
		cboventa.setModel(model);
	}
	
	public int Venta(int id) {
		for (Venta DetalleV : listaVenta) {
			if(DetalleV.getIdventa()==id) {
				return DetalleV.getIdventa();
			}
		}
		return (Integer) null;
	}


	public vDetalleV() {
		//setIconImage(Toolkit.getDefaultToolkit().getImage(vDetalleV.class.getResource("/Img/icono.jpg")));
		//setLocationRelativeTo(null);
		setTitle("DetalleV");
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
		lblid.setFont(new java.awt.Font("Verdana", java.awt.Font.ITALIC, 12));
		lblid.setHorizontalAlignment(SwingConstants.LEFT);
		lblid.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblid.setBounds(163, 26, 86, 23);
		contentPane.add(lblid);
		
		JLabel lblNewLabel_1 = new JLabel("Venta");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new java.awt.Font("Verdana", java.awt.Font.ITALIC, 11));
		lblNewLabel_1.setBounds(10, 92, 133, 23);
		contentPane.add(lblNewLabel_1);
		
		txtcosto = new JTextField();
		txtcosto.setForeground(new Color(255, 255, 255));
		txtcosto.setBackground(new Color(0, 0, 0));
		txtcosto.setBorder(new LineBorder(new Color(255, 255, 255)));
		txtcosto.setFont(new java.awt.Font("Verdana", java.awt.Font.ITALIC, 11));
		txtcosto.setBounds(163, 124, 86, 20);
		contentPane.add(txtcosto);
		txtcosto.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Costo");
		lblNewLabel_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1.setFont(new java.awt.Font("Verdana", java.awt.Font.ITALIC, 11));
		lblNewLabel_1_1.setBounds(10, 123, 133, 23);
		contentPane.add(lblNewLabel_1_1);
		
		txtimporte = new JTextField();
		txtimporte.setForeground(new Color(255, 255, 255));
		txtimporte.setBackground(new Color(0, 0, 0));
		txtimporte.setBorder(new LineBorder(new Color(255, 255, 255)));
		txtimporte.setFont(new java.awt.Font("Verdana", java.awt.Font.ITALIC, 11));
		txtimporte.setColumns(10);
		txtimporte.setBounds(163, 185, 86, 20);
		contentPane.add(txtimporte);
		
		JLabel lblNewLabel_1_2 = new JLabel("Producto");
		lblNewLabel_1_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_2.setFont(new java.awt.Font("Verdana", java.awt.Font.ITALIC, 11));
		lblNewLabel_1_2.setBounds(10, 58, 133, 23);
		contentPane.add(lblNewLabel_1_2);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setForeground(new Color(255, 255, 255));
		btnAgregar.setFont(new java.awt.Font("Verdana", java.awt.Font.ITALIC, 12));
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(cboproducto.getSelectedItem().equals("")||cboventa.getSelectedItem().equals("")||txtcosto.getText().equals("")||txtcantidad.getText().equals("")||txtimporte.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "campos vacios");
						return;
					}
					DetalleV user=new DetalleV();
					user.setIdproducto(Integer.parseInt(cboproducto.getSelectedItem().toString()));
					user.setIdventa(Integer.parseInt(cboventa.getSelectedItem().toString()));
					user.setCosto(Integer.parseInt(txtcosto.getText()));
					user.setCantidad(Integer.parseInt(txtcantidad.getText()));
					user.setImporte(Integer.parseInt(txtimporte.getText()));
					if (dao.insertarDetalleV(user)) {
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
		
	btnAgregar.setBounds(363, 139, 89, 50);
	contentPane.add(btnAgregar);
	
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setForeground(new Color(255, 255, 255));
		btnEliminar.setFont(new java.awt.Font("Verdana", java.awt.Font.ITALIC, 12));
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int opcion =JOptionPane.showConfirmDialog(null , "Estas seguro de eliminar");
					if(opcion==0) {
					if (dao.eliminarDetalleV(lista.get(fila).getIddetalle())) {
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
	    btnEliminar.setBounds(363, 34, 89, 47);
	    contentPane.add(btnEliminar);
		
		btnEditar = new JButton("editar");
		btnEditar.setForeground(new Color(255, 255, 255));
		btnEditar.setFont(new java.awt.Font("Verdana", java.awt.Font.ITALIC, 12));
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(cboproducto.getSelectedItem().equals("")||cboventa.getSelectedItem().equals("")||txtcosto.getText().equals("")||txtcantidad.getText().equals("")||txtimporte.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "campos vacios");
						return;
					}
					DetalleV.setIdproducto(Integer.parseInt(cboproducto.getSelectedItem().toString()));
					DetalleV.setIdventa(Integer.parseInt(cboventa.getSelectedItem().toString()));
					DetalleV.setCosto(Integer.parseInt(txtcosto.getText()));
					DetalleV.setCantidad(Integer.parseInt(txtcantidad.getText()));
					DetalleV.setImporte(Integer.parseInt(txtimporte.getText()));
					if (dao.editarDetalleV(DetalleV)) {
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
		btnEditar.setBounds(625, 34, 89, 47);
		
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 232, 846, 238);
		contentPane.add(scrollPane);
		
		tblDetalleV = new JTable();
		tblDetalleV.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fila=tblDetalleV.getSelectedRow();
				DetalleV=lista.get(fila);
				lblid.setText(""+lista.get(fila).getIddetalle());
				cboproducto.setSelectedItem(""+DetalleV.getIdproducto());
				cboventa.setSelectedItem(""+DetalleV.getIdventa());
				txtcosto.setText(""+DetalleV.getCosto());
				txtcantidad.setText(""+DetalleV.getCantidad());
				txtimporte.setText(""+DetalleV.getImporte());
				
			}
		});
		tblDetalleV.setModel(new DefaultTableModel(
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
		scrollPane.setViewportView(tblDetalleV);
		
		modelo.addColumn("ID");
		modelo.addColumn("Idproducto");
		modelo.addColumn("Idventa");
		modelo.addColumn("Costo");
		modelo.addColumn("Cantidad");
		modelo.addColumn("Importe");
		tblDetalleV.setModel(modelo);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Cantidad");
		lblNewLabel_1_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1_1.setFont(new java.awt.Font("Verdana", java.awt.Font.ITALIC, 11));
		lblNewLabel_1_1_1.setBounds(10, 150, 133, 23);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Importe");
		lblNewLabel_1_1_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1_2.setFont(new java.awt.Font("Verdana", java.awt.Font.ITALIC, 11));
		lblNewLabel_1_1_2.setBounds(10, 184, 133, 23);
		contentPane.add(lblNewLabel_1_1_2);
		
		txtcantidad = new JTextField();
		txtcantidad.setForeground(new Color(255, 255, 255));
		txtcantidad.setBackground(new Color(0, 0, 0));
		txtcantidad.setBorder(new LineBorder(new Color(255, 255, 255)));
		txtcantidad.setFont(new java.awt.Font("Verdana", java.awt.Font.ITALIC, 11));
		txtcantidad.setColumns(10);
		txtcantidad.setBounds(163, 155, 86, 20);
		contentPane.add(txtcantidad);
		
		cboventa = new JComboBox();
		cboventa.setForeground(new Color(255, 255, 255));
		cboventa.setBorder(new LineBorder(new Color(255, 255, 255)));
		cboventa.setBackground(new Color(0, 0, 0));
		cboventa.setFont(new java.awt.Font("Verdana", java.awt.Font.ITALIC, 11));
		cboventa.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				cargarComboVenta();
			}
		});
		cboventa.setBounds(163, 92, 86, 22);
		contentPane.add(cboventa);
		
		cboproducto = new JComboBox();
		cboproducto.setForeground(new Color(255, 255, 255));
		cboproducto.setBackground(new Color(0, 0, 0));
		cboproducto.setBorder(new LineBorder(new Color(255, 255, 255)));
		cboproducto.setFont(new java.awt.Font("Verdana", java.awt.Font.ITALIC, 11));
		cboproducto.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				cargarComboProducto();
			}
		});
		
		cboproducto.setBounds(163, 58, 86, 22);
		contentPane.add(cboproducto);
		
		btnpdf = new JButton("");
		btnpdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pdf();
			}});
		
		btnpdf.setIcon(fx.cambiar(new ImageIcon(getClass().getResource("/img/pdf.png")), 40,40 ));
		btnpdf.setBackground(new Color(0, 0, 0));
		btnpdf.setHorizontalTextPosition(SwingConstants.CENTER);
		btnpdf.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnpdf.setHorizontalAlignment(SwingConstants.CENTER);
	    this.getContentPane().add(btnpdf, new AbsoluteConstraints(10, 240, 80, 80));
		contentPane.add(btnpdf);
		contentPane.add(btnpdf);
		
		btnpdf.setBounds(625, 139, 89, 50);
		contentPane.add(btnpdf);
		
		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setOpaque(true);
		lblNewLabel_2.setBackground(new Color(0, 0, 0));
		lblNewLabel_2.setBounds(0, 0, 915, 500);
		contentPane.add(lblNewLabel_2);
		refrescarTabla();
	}
	public void refrescarTabla() {
		while(modelo.getRowCount()>0) {
		modelo.removeRow(0);
		}
		lista=dao.fetchDetalleVs();
		for(DetalleV u: lista) {
			Object o[]=new Object [6];
			o[0]=u.getIddetalle();
			o[1]=u.getIdproducto();
			o[2]=u.getIdventa();
			o[3]=u.getCosto();
			o[4]=u.getCantidad();
			o[5]=u.getImporte();
			
			modelo.addRow(o);
		}
		tblDetalleV.setModel(modelo);
	}
}