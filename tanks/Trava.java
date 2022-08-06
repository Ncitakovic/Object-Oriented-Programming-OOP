package igra;

import java.awt.*;

@SuppressWarnings("serial")
public class Trava extends Polje {

	public Trava(Mreza mreza) {
		super(mreza);
		setBackground(Color.GREEN);
	}
	
	
	//MOZE SE NA TRAVI NACI
	//BILO KOJA FIGURA
		
	@Override
	public boolean dozvFigura() {
		return true;

	}
	

}
