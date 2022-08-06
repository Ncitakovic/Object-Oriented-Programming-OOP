package baloni;

import java.awt.Color;

public class Balon extends KruznaFigura {

	public Balon(Vektor centar,  double d, Scena scena, Vektor brzina) {
		super(centar, Color.RED, d, scena, brzina);
		scena.dodajFigura(this);
	}

	@Override
	protected void crtajFig() {}

	

}
