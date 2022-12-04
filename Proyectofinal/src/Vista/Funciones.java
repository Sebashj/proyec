package Vista;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Funciones {
	
	  public ImageIcon cambiar(ImageIcon img, int ancho, int alto) {
	        Image imgEscalada = img.getImage().getScaledInstance(ancho,
	                alto, Image.SCALE_SMOOTH);
	        ImageIcon image = new ImageIcon(imgEscalada);
	        return image;
	    }

}
