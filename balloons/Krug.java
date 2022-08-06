package baloni;

import java.awt.*;

public abstract class Krug {
	protected Vektor centar;
	protected Color boja;
	protected double d;
	
	//PROVERITI DA LI TREBA DODATI SCENU U KONSTURKTORU I KAO ATRIBUT
	
	public Krug(Vektor centar, Color boja, double d ) {
		this.centar = centar;
		this.boja = boja;
		this.d = d;	
	}



	public boolean preklapaSe(Krug k) {
		double x1 = centar.getX(), y1 = centar.getY(), r1 = this.d/2;
		double x2 = k.centar.getX(), y2 = k.centar.getY(), r2 = k.d/2;
		
		 double distSq = (x1 - x2) * (x1 - x2) +  (y1 - y2) * (y1 - y2); 
		 double radSumSq = (r1 + r2) * (r1 + r2); 
		
		 if(distSq<=radSumSq) {
			 return true;
		 }else {
			 return false;
		 }
	}
	
	//DODATI METODU: KRUG JE MOGUCE ISCRTATI
	//NA ZADATOJ SCENI
	public abstract void crtaj(Scena s);
		//Graphics g = s.getGraphics();
		//g.setColor(boja);
		//g.fillOval((int)(centar.getX()-d/2),(int)( centar.getY()-d/2), (int)d,(int) d);
		//crtajFig();
	//}
	//public abstract void crtajFig();
	
	
}
