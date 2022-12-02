package Vista;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JDesktopPane;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;

public class vPrincial extends JFrame {

	private JPanel contentPane;
	double ancho=Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	double alto=Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	vProducto vProducto=new vProducto();
	vEmpleado vEmpleado=new vEmpleado();
	vProveedor vProveedor=new vProveedor();
	vInventario vInventario=new vInventario();
	vVenta vVenta=new vVenta();
	vDetalleV vDetalleV=new vDetalleV();
	vCliente vCliente=new vCliente();
	private JDesktopPane desktopPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vPrincial frame = new vPrincial();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public vPrincial() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(vPrincial.class.getResource("/Img/empresalogo.png")));
		setTitle("Angencia Quetware");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1155, 715);
		this.setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("sistema");
		menuBar.add(mnNewMenu);
		
		JButton btnNewButton = new JButton("salir ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "ADIOS");
				System.exit(0);
			}
		});
		mnNewMenu.add(btnNewButton);
		
		JMenu mnNewMenu_1 = new JMenu("Empleado");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Empleados");
		mnNewMenu_1.add(mntmNewMenuItem);
		mntmNewMenuItem.setBackground(new Color(255, 255, 255));
		mntmNewMenuItem.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mntmNewMenuItem.setIcon(new ImageIcon(vPrincial.class.getResource("/Img/empleado.png")));
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Inventario");
		mnNewMenu_1.add(mntmNewMenuItem_1);
		mntmNewMenuItem_1.setBackground(new Color(255, 255, 255));
		mntmNewMenuItem_1.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mntmNewMenuItem_1.setIcon(new ImageIcon(vPrincial.class.getResource("/Img/inventario.png")));
		
		JMenu mnNewMenu_2 = new JMenu("Pdf");
		menuBar.add(mnNewMenu_2);
		
		JMenu mnNewMenu_3 = new JMenu("Acerca de");
		menuBar.add(mnNewMenu_3);
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
				//System.out.println("Ancho: "+d.getWidth()+" Alto: "+d.getHeight());
				//Dimension d=JDesktopPane.
				//vInventario.setSize(JDesktopPane.WIDTH,JDesktopPane.HEIGHT);
				vInventario.setVisible(true);
			}
		});
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vEmpleado.setVisible(true);
			}
		});
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		desktopPane = new JDesktopPane();
		desktopPane.setBackground(new Color(255, 255, 255));
		desktopPane.setBounds(208, 110, 1779, 905);
		contentPane.add(desktopPane);
		vProducto.setLocation(0, 0);
		vProducto.setResizable(true);
		desktopPane.add(vProducto);
		vInventario.setBounds(0, 0, 504, 502);
		vEmpleado.setLocation(0, 0);
		desktopPane.add(vEmpleado);
		vProveedor.setLocation(0, 0);
		desktopPane.add(vProveedor);
		vVenta.setLocation(0, 0);
		desktopPane.add(vVenta);
		desktopPane.add(vDetalleV);
		vCliente.setLocation(0, 0);
		desktopPane.add(vCliente);
		desktopPane.add(vInventario);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(vPrincial.class.getResource("/Img/empresalogo.png")));
		lblNewLabel.setBounds(47, 24, 75, 73);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Incar");
		lblNewLabel_1.setForeground(new Color(128, 255, 255));
		lblNewLabel_1.setFont(new Font("Baskerville Old Face", Font.BOLD, 25));
		lblNewLabel_1.setBounds(220, 15, 192, 36);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("La inovacion distinge a los lideres de los seguidores");
		lblNewLabel_2.setFont(new Font("NSimSun", Font.BOLD, 15));
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(259, 60, 454, 26);
		contentPane.add(lblNewLabel_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Venta");
		mntmNewMenuItem_3.setBackground(new Color(255, 255, 255));
		mntmNewMenuItem_3.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mntmNewMenuItem_3.setBounds(10, 175, 188, 25);
		contentPane.add(mntmNewMenuItem_3);
		mntmNewMenuItem_3.setIcon(new ImageIcon(vPrincial.class.getResource("/Img/vent.png")));
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Cliente");
		mntmNewMenuItem_6.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mntmNewMenuItem_6.setBackground(new Color(255, 255, 255));
		mntmNewMenuItem_6.setBounds(10, 138, 188, 26);
		contentPane.add(mntmNewMenuItem_6);
		mntmNewMenuItem_6.setIcon(new ImageIcon(vPrincial.class.getResource("/Img/Cliente.png")));
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Producto");
		mntmNewMenuItem_5.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mntmNewMenuItem_5.setBackground(new Color(255, 255, 255));
		mntmNewMenuItem_5.setBounds(10, 248, 188, 26);
		contentPane.add(mntmNewMenuItem_5);
		mntmNewMenuItem_5.setIcon(new ImageIcon(vPrincial.class.getResource("/Img/producto.png")));
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Detalles Venta");
		mntmNewMenuItem_4.setBackground(new Color(255, 255, 255));
		mntmNewMenuItem_4.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mntmNewMenuItem_4.setBounds(10, 211, 188, 26);
		contentPane.add(mntmNewMenuItem_4);
		mntmNewMenuItem_4.setIcon(new ImageIcon(vPrincial.class.getResource("/Img/Detallesv.png")));
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Proveedor");
		mntmNewMenuItem_2.setBounds(21, 612, 153, 31);
		contentPane.add(mntmNewMenuItem_2);
		mntmNewMenuItem_2.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mntmNewMenuItem_2.setBackground(new Color(255, 255, 255));
		mntmNewMenuItem_2.setIcon(new ImageIcon(vPrincial.class.getResource("/Img/Provedor.jpg")));
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vProveedor.setVisible(true);
			}
		});
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vDetalleV.setVisible(true);
			}
		});
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vProducto.setVisible(true);
			}
		});
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vCliente.setVisible(true);
			}
		});
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vVenta.setVisible(true);
			}
		});
	}
}
