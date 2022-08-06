package igra;

import java.awt.*;

@SuppressWarnings("serial")
public abstract class Polje extends Canvas {

	private Mreza mreza;
	//private int[] pozicija= new int [2];
	
	public Polje(Mreza mreza) {
		this.mreza = mreza;
		//pozicija[0]=x;
		//pozicija[1]=y;
		
	}

	public int[] odrediPoziciju(){
		//return pozicija;
	
		//2 NACIN
		
		
				int[] position = new int[2];
				for (int i = 0; i < mreza.getMat().length; i++)
					for (int j = 0; j < mreza.getMat()[0].length; j++) 
						if(this==mreza.getMat()[i][j]) { position[0]=i;position[1]=j;return position;}
				return null;		
						
			
	}
	
	public Mreza getMreza() {
		return mreza;
	}
	
	public Polje dohvPomerenoPolje(int rowOffset,int colOffset) {
		int x = odrediPoziciju()[0] + rowOffset;
		int y = odrediPoziciju()[1] + colOffset;
		if(x<0 || y<0 || x>= mreza.getMat().length || y>= mreza.getMat()[0].length) return null;
		return mreza.getMat()[x][y];
		
	}
	
	public abstract boolean dozvFigura();
	
	
	
	
	
	
	
	
}
