package baloni;

import java.awt.*;
import java.util.*;
 
import java.awt.event.*;

@SuppressWarnings("serial")
public class Scena extends Canvas implements Runnable {
	//Semaphore s = new Semaphore(1);
	private Thread t = new Thread(this);
	private static final int PLAYER_OFFSET = 50;
	private Igra igra;
	private  Igrac igrac;
	LinkedList<KruznaFigura> figure;
	private final long beginning;

	public Scena(Igra igra) {
		
		this.igra = igra;
		figure = new LinkedList<KruznaFigura>();
		addKeyListener(new KeyBoardAdapter());
	
		beginning = System.currentTimeMillis();
		t.start();
	}



	public void dodajFigura(KruznaFigura f) {
			figure.add(f);
	}

	public void izbaciFigura(KruznaFigura f) {
		figure.remove(f);
	}

	@Override
	public void run() {
		
		
		
		
		
		try {
			
				Thread.sleep(2000);
				double xx = this.getWidth() / 2;
				double yy =	this.getHeight()-1 - Scena.PLAYER_OFFSET;
				igrac = new Igrac(new Vektor(xx, yy), 30, this,new Vektor(0.0,0.0));
				//Thread.sleep(2000);
				
				
			while (!Thread.interrupted()) {
				
				
				// CREATING A BALOON
				if (Math.random() < 0.1) {
					
					double x =   igra.getX()+10 + Math.random() * (igra.getWidth() - 20);
					Vektor centar = new Vektor(x, igra.getY() + 20);
					new Balon(centar,  20, this, new Vektor(0, 1));
					// ADDING BALOON TO LIST OF FIGURES

				}
				igrac.crtaj(this);

				//Remove figures that are out of bounds 
				for (int i = 0; i < figure.size(); i++) {
					KruznaFigura figura = figure.get(i);
					if (figura.centar.getX() < 0 || figura.centar.getY() < 0 || figura.centar.getX() >= igra.getWidth()
							|| figura.centar.getY() >= igra.getHeight()) {
						
						this.izbaciFigura(figura);
						
					}
				}
				
				// INFORM FIGURES ABOUT TIME
				igrac.crtaj(this);

				for (KruznaFigura f : figure) {
					//synchronized(this) {
						//this.wait();
						f.protekaoPeriod((System.currentTimeMillis() - beginning)/1000);
					//}
				}
				// zaustavljanje figura ako se neki od balona preklapa sa igracem
				for(KruznaFigura f:figure) {
					igrac.sudar((Balon)f);
				}
					
				
				igrac.crtaj(this);
				// PAINT AGAIN

				Thread.sleep(60);
				//igrac.crtaj();  OVDE JE PROBLEM NESTO!
				repaint();
				igrac.crtaj(this);
				//figure.remove(igrac);
			}
		} catch (InterruptedException e) {}
		
	}

	@Override
	public void paint(Graphics g) {

		for (KruznaFigura f : figure)
			f.crtaj(this);
		//igrac.crtaj(this);
	}

	class KeyBoardAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();
			if (keyCode == KeyEvent.VK_LEFT) {
				igrac.pomeri(-3);
			} else if (keyCode == KeyEvent.VK_RIGHT) {
				igrac.pomeri(3);
			}
			repaint();
		}

		@Override
		public void keyReleased(KeyEvent e) {
			int keyCode = e.getKeyCode();
			if (e.getKeyCode() == keyCode || e.getKeyCode() == keyCode) {}
		}

	}

	// ZAUSTAVLJANJE SCENE
	public void zaustavi() {
		t.interrupt();
	}

}
