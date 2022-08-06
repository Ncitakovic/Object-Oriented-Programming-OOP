package igra;

import java.awt.Color;

public abstract class Figura{

	protected Polje p;
	
	public Figura(Polje p) {
		this.p = p;
	}
	
	public Polje getPolje() {
		return p;
	}
	
	public abstract void crtaj();

	public Figura pomeriFiguru(Polje novoP) {
		if(novoP.dozvFigura()) {
			
			p.setBackground(Color.GREEN);
			if(this instanceof Tenk) ((Tenk)this).brisiCrtez();
			if(this instanceof Igrac) ((Igrac)this).brisiCrtez();
			//int x = p.odrediPoziciju()[0];
			//int y = p.odrediPoziciju()[1];
			//p.getMreza().getMat()[x][y] = null;
			//x = novoP.odrediPoziciju()[0];
			//y = novoP.odrediPoziciju()[1];
			//p.getMreza().getMat()[x][y] = novoP;
			
			
			p = novoP;
			
			return this;
		}
		return this;
	}
	
	//1 NACIN ZA JEDNAKOST FIGURA
	public boolean jednak(Figura f) {
		return p.odrediPoziciju()[0] == f.getPolje().odrediPoziciju()[0] 
				&& p.odrediPoziciju()[1]==f.getPolje().odrediPoziciju()[1];
	}
	
	
	
	//2 NACIN ZA JEDNAKOST FIGURA
	/*
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Figura other = (Figura) obj;
		if (p == null) {
			if (other.p != null)
				return false;
		} else if (!p.equals(other.p))
			return false;
		return true;
	}
	*/
	
}
