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
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JDesktopPane;

public class vPrincial extends JFrame {

	private JPanel contentPane;
	vCliente vCliente=new vCliente();
	vDetalleV vDetalleV=new vDetalleV();
	vVenta vVenta=new vVenta();
	vProveedor vProveedor=new vProveedor();
	vInventario vInventario=new vInventario();
	vEmpleado vEmpleado=new vEmpleado();
	vProducto vProducto=new vProducto();
	double ancho=Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	double alto=Toolkit.getDefaultToolkit().getScreenSize().getWidth();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vPrincial frame = new vPrincial();
					frame.setExtendedState(MAXIMIZED_BOTH);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public vPrincial() {
		setTitle("Angencia Quetware");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu_1 = new JMenu("Sistema");
		menuBar.add(mnNewMenu_1);
		
		JButton btnNewButton = new JButton("Salir");
		mnNewMenu_1.add(btnNewButton);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Producto");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vProducto.setVisible(true);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_5);
		
		JMenu mnNewMenu = new JMenu("Trabajadores");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Empleados");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vEmpleado.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Inventario");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vInventario.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Proveedor");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vProveedor.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenu mnNewMenu_2 = new JMenu("Ventas");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Venta");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vVenta.setVisible(true);
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Detalles Venta");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vDetalleV.setVisible(true);
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_4);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Cliente");
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vCliente.setVisible(true);
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_6);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(0, 0, 434, 239);
		desktopPane.setSize((int)ancho,(int)alto);
		contentPane.add(desktopPane);
		desktopPane.add(vProducto);
		desktopPane.add(vEmpleado);
		desktopPane.add(vProveedor);
		desktopPane.add(vInventario);
		desktopPane.add(vVenta);
		desktopPane.add(vDetalleV);
		desktopPane.add(vCliente);
	}
}
