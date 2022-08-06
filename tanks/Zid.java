package igra;

import java.awt.Color;

@SuppressWarnings("serial")
public class Zid extends Polje {

	public Zid(Mreza mreza) {
		super(mreza);
		setBackground(Color.LIGHT_GRAY);
	}
	
	//NA ZIDU SE NE MOZE NACI NI JEDNA FIGURA
	@Override
	public boolean dozvFigura() {
		return false;
	}

}
