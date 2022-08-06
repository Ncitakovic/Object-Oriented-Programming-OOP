package igra;
import java.awt.*;
public class Igrac extends Figura {

	public Igrac(Polje p) {
		super(p);
	}

	@Override
	public void crtaj() {
		Graphics g = p.getGraphics();
		int height = p.getHeight();
		int width  = p.getWidth();
		g.setColor(Color.RED);
		g.drawLine(0, height/2, width, height/2);
		g.drawLine(width/2, 0, width/2, height);
	}

	public void brisiCrtez() {
		Graphics g = p.getGraphics();
		int height = p.getHeight();
		int width  = p.getWidth();
		g.setColor(Color.GREEN);
		g.drawLine(0, height/2, width, height/2);
		g.drawLine(width/2, 0, width/2, height);
		 
	}

	
}
