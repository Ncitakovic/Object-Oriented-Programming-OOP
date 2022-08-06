package igra;

import java.awt.*;


public class Tenk extends Figura implements Runnable {
	private Thread t = new Thread(this);

	public Tenk(Polje p) {
		super(p);
		t.start();
	}

	@Override
	public void run() {

		try {
			while (!Thread.interrupted()) {
				Thread.sleep(500);

				// POMERANJE TENKA
				int smer = (int)( Math.random() * 4);
				int x = getPolje().odrediPoziciju()[0];
				int y = getPolje().odrediPoziciju()[1];
				if(x-1<0 || x+1>=getPolje().getMreza().getMat()[0].length ||
					y-1<0 || y+1>= getPolje().getMreza().getMat()[0].length) continue;
				switch (smer) {
				case 0: // LEVO
					pomeriFiguru(getPolje().getMreza().getMat()[x - 1][y]);
					break;
				case 1: // DESNO
					
					pomeriFiguru(getPolje().getMreza().getMat()[x + 1][y]);
					break;
				case 2: // GORE
					
					pomeriFiguru(getPolje().getMreza().getMat()[x][y + 1]);
					break;
				case 3: // DOLE
					
					pomeriFiguru(getPolje().getMreza().getMat()[x][y - 1]);
					break;
				}
				this.crtaj();
			}
		} catch (InterruptedException e) {}

	}

	@Override
	public void crtaj() {
		
		Graphics g = p.getGraphics();
		if(g!=null) {
		g.setColor(Color.BLACK);
		g.drawLine(0, 0, p.getWidth(), p.getHeight());
		g.drawLine(p.getWidth(), 0, 0, p.getHeight());
		}
	}
	public void brisiCrtez() {

		Graphics g = p.getGraphics();
		
		if(g!=null) {
		g.setColor(Color.GREEN);
		g.drawLine(0, 0, p.getWidth(), p.getHeight());
		g.drawLine(p.getWidth(), 0, 0, p.getHeight());
		}
		
	}

	public void zaustavi() {
		t.interrupt();
	}

}
