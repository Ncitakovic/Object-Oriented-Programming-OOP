package igra;
import java.awt.*;
public class Novcic extends Figura{

	public Novcic(Polje p) {
		super(p);
	}

	@Override
	public void crtaj() {
		Graphics g = p.getGraphics();
		int x0 = p.getWidth()/4;
		int y0 = p.getHeight()/4;
		g.setColor(Color.YELLOW);
		g.fillOval(x0, y0,p.getWidth()/2, p.getHeight()/2);
	}

}
