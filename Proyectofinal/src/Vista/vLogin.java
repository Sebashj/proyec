package Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Dao.DaoEmpleado;
import Modelo.Empleado;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.border.LineBorder;

public class vLogin extends JFrame {

	private JPanel contentPane;
	private JTextField txtEmpleado;
	private JButton btnCancelar;
	private JButton btnentrada;
	private JPasswordField txtpassword;
	private JLabel btnlogo;
	DaoEmpleado dao=new DaoEmpleado();
	private JLabel lblNewLabel_2;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vLogin frame = new vLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public vLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setForeground(new Color(255, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Empleado");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(33, 71, 63, 22);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblNewLabel);

		txtEmpleado = new JTextField();
		txtEmpleado.setBounds(33, 104, 220, 19);
		txtEmpleado.setBorder(null);
		txtEmpleado.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (txtEmpleado.getText().length() >= 10) {
					e.consume();
				}
			}
		});
		contentPane.add(txtEmpleado);
		txtEmpleado.setColumns(10);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(new Color(255, 255, 255));
		lblPassword.setBounds(33, 134, 63, 22);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblPassword);

		btnentrada = new JButton("Entrada");
		btnentrada.setBorder(new LineBorder(new Color(255, 255, 255)));
		btnentrada.setBackground(new Color(0, 0, 0));
		btnentrada.setFont(new Font("Tw Cen MT", Font.BOLD, 11));
		btnentrada.setForeground(new Color(255, 255, 255));
		btnentrada.setBounds(70, 218, 134, 38);
		btnentrada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Empleado user =new Empleado();
				user.setNombre(txtEmpleado.getText());
				user.setPassword(String.valueOf(txtpassword.getPassword()));
				if(dao.loginEmpleado(user)) {
				JOptionPane.showMessageDialog(null, "Bienvenido");
				vCargando cargando=new vCargando();
				setVisible(false);
				cargando.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "Empleado y/o contraseña incorrecta");
				}
				
			}
		});
		contentPane.add(btnentrada);

		btnCancelar = new JButton("salir");
		btnCancelar.setBorder(new LineBorder(new Color(255, 255, 255)));
		btnCancelar.setBackground(new Color(0, 0, 0));
		btnCancelar.setFont(new Font("Trebuchet MS", Font.BOLD, 11));
		btnCancelar.setForeground(new Color(255, 255, 255));
		btnCancelar.setBounds(101, 279, 85, 21);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "ADIOS");
				System.exit(0);
				//Image img=Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Img/salirr.jpg"));
				//ImageIcon img2=new ImageIcon(img.getScaledInstance(30, 30, Image.SCALE_SMOOTH));
				//btnCancelar.setIcon(img2);
			}
		});
		contentPane.add(btnCancelar);

		txtpassword = new JPasswordField();
		txtpassword.setBorder(null);
		txtpassword.setBounds(33, 167, 220, 22);
		txtpassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (txtpassword.getText().length() >= 10) {
					e.consume();
				}
			}
		});
		contentPane.add(txtpassword);

		btnlogo = new JLabel("");
		btnlogo.setBounds(277, 0, 173, 300);
		contentPane.add(btnlogo);

		Image a = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Img/Esto haría con las carreras _Sprint_ de la Formula 1 _ F1.jpg"));
		int ancho = btnlogo.getWidth();
		int alto = btnlogo.getHeight();
		btnlogo.setIcon(new ImageIcon(a.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH)));
		
		JLabel lblNewLabel_1 = new JLabel("QUETWARE");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Roboto Black", Font.BOLD, 20));
		lblNewLabel_1.setBounds(47, 22, 125, 38);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(193, 11, 59, 60);
		contentPane.add(lblNewLabel_2);
		
		Image b = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Img/empresalogo.png"));
		int anch = lblNewLabel_2.getWidth();
		int alt = lblNewLabel_2.getHeight();
		lblNewLabel_2.setIcon(new ImageIcon(b.getScaledInstance(anch, alt, Image.SCALE_SMOOTH)));
		
	}
}