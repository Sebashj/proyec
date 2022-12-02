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
import javax.swing.JLabel;

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
		menuBar.setBackground(new Color(0, 128, 255));
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu_1 = new JMenu("Sistema");
		mnNewMenu_1.setIcon(new ImageIcon(vPrincial.class.getResource("/Img/sistema.png")));
		menuBar.add(mnNewMenu_1);
		
		JButton btnNewButton = new JButton("Salir");
		btnNewButton.setIcon(new ImageIcon(vPrincial.class.getResource("/Img/salirr.jpg")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "ADIOS");
				System.exit(0);
			}
		});
		mnNewMenu_1.add(btnNewButton);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Producto");
		mntmNewMenuItem_5.setIcon(new ImageIcon(vPrincial.class.getResource("/Img/producto.png")));
		mnNewMenu_1.add(mntmNewMenuItem_5);
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vProducto.setVisible(true);
			}
		});
		
		JMenu mnNewMenu = new JMenu("Trabajadores");
		mnNewMenu.setIcon(new ImageIcon(vPrincial.class.getResource("/Img/trabajadores.png")));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Empleados");
		mntmNewMenuItem.setIcon(new ImageIcon(vPrincial.class.getResource("/Img/empleado.png")));
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vEmpleado.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Inventario");
		mntmNewMenuItem_1.setIcon(new ImageIcon(vPrincial.class.getResource("/Img/inventario.png")));
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vInventario.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Proveedor");
		mntmNewMenuItem_2.setIcon(new ImageIcon(vPrincial.class.getResource("/Img/Provedor.jpg")));
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vProveedor.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenu mnNewMenu_2 = new JMenu("Ventas");
		mnNewMenu_2.setIcon(new ImageIcon(vPrincial.class.getResource("/Img/ven.png")));
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Venta");
		mntmNewMenuItem_3.setIcon(new ImageIcon(vPrincial.class.getResource("/Img/vent.png")));
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vVenta.setVisible(true);
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Detalles Venta");
		mntmNewMenuItem_4.setIcon(new ImageIcon(vPrincial.class.getResource("/Img/Detallesv.png")));
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vDetalleV.setVisible(true);
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_4);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Cliente");
		mntmNewMenuItem_6.setIcon(new ImageIcon(vPrincial.class.getResource("/Img/Cliente.png")));
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vCliente.setVisible(true);
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_6);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 255));
		contentPane.setForeground(new Color(0, 128, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(new Color(92, 132, 160));
		desktopPane.setBounds(124, 86, 1015, 568);
		contentPane.add(desktopPane);
		desktopPane.add(vProducto);
		desktopPane.add(vEmpleado);
		desktopPane.add(vProveedor);
		desktopPane.add(vInventario);
		desktopPane.add(vVenta);
		desktopPane.add(vDetalleV);
		desktopPane.add(vCliente);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(10, 11, 104, 75);
		contentPane.add(lblNewLabel);
		Image a = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Img/empresalogo.png"));
		int ancho = lblNewLabel.getWidth();
		int alto = lblNewLabel.getHeight();
		lblNewLabel.setIcon(new ImageIcon(a.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH)));
	}
}
