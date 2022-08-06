package igra;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

@SuppressWarnings("serial")
public class Mreza extends Panel implements Runnable {
	private Thread t;
	private Igra igra;
	private Polje[][] mat;
	private LinkedList<Figura> tenkovi;
	private LinkedList<Figura> novcici;
	private Igrac igrac;
	
	// KONSTRUKTOR
	public Mreza(Igra igra) {
		this(17, igra);
	}

	public Mreza(int dim, Igra igra) {
		this.igra = igra;
		novcici = new LinkedList<Figura>();
		tenkovi = new LinkedList<Figura>();
		mat = new Polje[dim][dim];
		setLayout(new GridLayout(dim, dim));
		dodajPolja();
		addKeyListener(new KeyBoardAdapter());
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(igra.getRezimIgre()==Igra.IZMENA) {
					Polje p= ((Polje)e.getSource());

					int i=0,j=0;
				loop:	for ( i = 0; i < mat.length; i++) 
							for ( j = 0; j < mat[0].length; j++) 
								if(p==(mat[i][j]))
									break loop;
						
				if(i==mat.length && j==mat[0].length) return;	
				if(Igra.grupaPolja.getSelectedCheckbox().getLabel().toString().equals("Trava"))
					mat[i][j] = new Trava(Mreza.this);
				else mat[i][j] = new Zid(Mreza.this);
					
					
				}
			}
		});
		requestFocus();
		setFocusable(true);
		
	}
	//INICIJALIZACIJA
	public void inicMreze(int brNovcica) {
		if (igra.getRezimIgre() == Igra.IGRANJE) {
			// KREIRANJE IGRACA
			int x,y;
			while(true) {
				x = (int) (Math.random() * mat.length);
			 	y = (int) (Math.random() * mat.length);
				if (mat[x][y]!=null && mat[x][y].dozvFigura()) {
					igrac = new Igrac(mat[x][y]);	break;
				}
			}
			
			// KREIRANJE NOVCICA
			
			for (int i = 0; i < brNovcica; i++) {
				x = (int) (Math.random() * mat.length);
				y = (int) (Math.random() * mat[0].length);

				Novcic n = new Novcic(mat[x][y]);
				if (mat[x][y]!=null && mat[x][y].dozvFigura()  && !postoji(n)) {
					novcici.add(n);
				} else {
					i--;
					continue;
				}

			}
			// KREIRANJE TENKOVA
			final int brTenkova = brNovcica / 3;
			for (int i = 0; i < brTenkova; i++) {
				x = (int) (Math.random() * mat.length);
				y = (int) (Math.random() * mat.length);

				Tenk t = new Tenk(mat[x][y]);
				if (mat[x][y]!=null && mat[x][y].dozvFigura() && !postoji(t)) {
					tenkovi.add(t);
				} else {
					i--;
					continue;
				}

			}

		}

	}

	private boolean postoji(Figura f) {
		if(novcici!=null) {
		for (int i = 0; i < novcici.size(); i++) {
			if (novcici.get(i).jednak(f))
				return true;
		}}
		if(tenkovi!=null) {
		for (int i = 0; i < tenkovi.size(); i++) {
			if (tenkovi.get(i).jednak(f))
				return true;
		}
		}
		return false;
	}

	// DODAVANJE TRAVE I ZIDOVA U MATRICU POLJA
	private void dodajPolja() {
		for (int i = 0; i < mat.length; i++)
			for (int j = 0; j < mat.length; j++) {
				if (Math.random() < 0.8) {
					mat[i][j] = new Trava(this);
				} else {
					mat[i][j] = new Zid(this);
				}
				add(mat[i][j]);
			}
	}

	public Polje[][] getMat() {
		return mat;
	}

	@Override
	public void run() {
		inicMreze(Integer.parseInt(igra.getNovcica().getText().toString()));
		try {
			while (!Thread.interrupted()) {
				Thread.sleep(40);
				azuriranjeStanja();
				repaint();
			}
		} catch (InterruptedException e) {}
	}

	@Override
	public void paint(Graphics g) {
		//super.paint(g);
		
		
		if(igrac!=null) igrac.crtaj();
		for (int i = 0; i < novcici.size(); i++) {
			novcici.get(i).crtaj();
		}
		for (int i = 0; i < tenkovi.size(); i++) {
			tenkovi.get(i).crtaj();
		}
		
	}
	
	private void azuriranjeStanja() {
		//JOB WITH COINS
		Figura f = null;
		for(Figura n:novcici) {
			if(igrac.jednak(n)) {igra.uvecajPoene();
				f = n; 
				break;
			
			} 
		}
		novcici.remove(f);
		if(novcici.size()==0) {this.zaustavi();return;}
		//JOB WITH TANKS
		for(Figura t:tenkovi) {
			if(igrac.jednak(t)) {
				igrac=null;zaustavi();break;
			}
		}
		
	}

	public LinkedList<Figura> getTenkovi() {
		return tenkovi;
	}

	public LinkedList<Figura> getNovcici() {
		return novcici;
		
	}

	public void pokreni() {
		t = new Thread(this);
		t.start();
	}

	public void zaustavi() {
		for (Figura tenk: tenkovi) {
			((Tenk)tenk).zaustavi();
		}
		t.interrupt();
	}
	
	class KeyBoardAdapter extends KeyAdapter{

		
			
			
			@Override
			public void keyPressed(KeyEvent e) {
				
				char key = Character.toUpperCase(e.getKeyChar());
				//int key = e.getKeyCode();
				//int x = igrac.getPolje().odrediPoziciju()[0];
				//int y = igrac.getPolje().odrediPoziciju()[1];
				switch (key) {
				case KeyEvent.VK_W: {
					System.out.println("W");
					//int x = igrac.getPolje().odrediPoziciju()[0];
					//int y = igrac.getPolje().odrediPoziciju()[1]; 
					//igrac.pomeriFiguru(igrac.getPolje().getMreza().getMat()[x][y-1]);
					//igrac.pomeriFiguru(igrac.getPolje().getMreza().getMat()[x][y - 1]);
					igrac.pomeriFiguru(igrac.getPolje().dohvPomerenoPolje(-1, 0));
					break;
				}
				case KeyEvent.VK_S: {
					System.out.println("S");
					//igrac.pomeriFiguru(igrac.getPolje().getMreza().getMat()[x][y + 1]);
					igrac.pomeriFiguru(igrac.getPolje().dohvPomerenoPolje(1, 0));
					break;
				}
				case KeyEvent.VK_A: {
					//igrac.pomeriFiguru(igrac.getPolje().getMreza().getMat()[x-1][y]);

					igrac.pomeriFiguru(igrac.getPolje().dohvPomerenoPolje(0, -1));
					break;
				}
				case KeyEvent.VK_D: {
					//igrac.pomeriFiguru(igrac.getPolje().getMreza().getMat()[x+1][y]);
					igrac.pomeriFiguru(igrac.getPolje().dohvPomerenoPolje(0, 1));
					break;
				}
				}
				repaint();
				//igrac.crtaj();
			}
			
	
			
	}
	
	
	
}
