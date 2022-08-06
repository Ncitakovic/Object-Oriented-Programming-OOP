package baloni;

import java.awt.*;

public abstract class KruznaFigura extends Krug {

	protected Vektor brzina;
	protected Scena scena;

	public KruznaFigura(Vektor centar, Color boja, double d, Scena scena, Vektor brzina) {
		super(centar, boja, d);
		this.brzina = brzina;
		this.scena = scena;
	}

	public void protekaoPeriod(double t) {
		if(this instanceof Igrac) return;
		Vektor pomeraj = brzina.mnozenjeVrednoscu(t);
		centar = centar.saberi(pomeraj);
		if (centar.getX() < 0 || centar.getY() < 0 || centar.getX() >= scena.getWidth()
				|| centar.getY() >= scena.getHeight()) {
			
			//scena.izbaciFigura(this);
			//return;
		}
		//super.crtaj(scena);

	}
	
	//public void postScena(Scena s) {scena=s;}
	//DODATI METODU: KRUG JE MOGUCE ISCRTATI
	//NA ZADATOJ SCENI
	public void crtaj(Scena scena) {
		Graphics g = scena.getGraphics();
		g.setColor(boja);
		g.fillOval((int)(centar.getX()-d/2),(int)( centar.getY()-d/2), (int)d,(int) d);
		crtajFig();
	}
	protected abstract void crtajFig();

	public void sudarilaSe(KruznaFigura f) {
		synchronized (f) {
			if (this.preklapaSe(f)) {
				f.notify();

			}
		}
	}

}
