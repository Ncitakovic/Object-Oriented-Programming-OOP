package baloni;

public class Vektor implements Cloneable {

	private double x;
	private double y;
	
	public Vektor(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	@Override
	public Vektor clone()  {
	
		try {
			return (Vektor)super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
	/*
	public Vektor mnozenjeVrednoscu(double m){
		this.x *= m;
		this.y *= m;
		return this;
	}
	
	public Vektor saberi(Vektor v) {
		this.x += v.x;
		this.y += v.y;
		return this;
	}
	*/
	
	public Vektor mnozenjeVrednoscu(double m){
		return new Vektor(x*m,y*m);
	}
	
	public Vektor saberi(Vektor v) {
		return new Vektor(x+v.x,y+v.y);
	}
	
	
	
	
}
