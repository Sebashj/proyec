package Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Vista.vEmpleado;

import javax.swing.JProgressBar;

public class vCargando extends JFrame {

	private JPanel contentPane;
	private JProgressBar barcargando;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vCargando frame = new vCargando();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void cargar() {
		Thread hilo = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i <= 100; i++) {
					barcargando.setValue(i);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (i == 100) {
						setVisible(false);
						vEmpleado p=new vEmpleado();
						p.setVisible(true);
					}
				}
			}
		});
		hilo.start();
	}

	public vCargando() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 84);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		barcargando = new JProgressBar();
		barcargando.setStringPainted(true);
		barcargando.setBounds(0, 0, 450, 84);
		contentPane.add(barcargando);
		cargar();
	}
}
