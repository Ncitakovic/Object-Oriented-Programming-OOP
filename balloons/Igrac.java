package baloni;

import java.awt.*;

public class Igrac extends KruznaFigura {

	public Igrac(Vektor centar, double d, Scena scena, Vektor v) {
		super(centar, Color.GREEN, d, scena, v);
		//scena.dodajFigura(this);
		//scena.postaviIgraca(this);
		//scena.igrac = this;
	}
	
	public void pomeri(double p) {
		if(centar.getX()-d/2+p<0) {
			centar=centar.saberi(new Vektor(-centar.getX()+15,0));
		}else
		if(centar.getX()+d/2+p>= scena.getWidth()) {
			centar=centar.saberi(new Vektor((double)(scena.getWidth()-centar.getX()-d/2),0));
		}else
			centar=centar.saberi(new Vektor(p,0));
		
	}
	
	public void crtajFig() {
		
		Graphics g = scena.getGraphics();
		int dd = (int) (this.d/2);
		g.setColor(Color.BLUE);
		g.fillOval((int)(centar.getX()-dd/2),(int) (centar.getY()-dd/2), dd, dd);
	}
	
	public void sudar(Balon b) {
		if(this.preklapaSe(b)) scena.zaustavi();
	}
	
	

}
