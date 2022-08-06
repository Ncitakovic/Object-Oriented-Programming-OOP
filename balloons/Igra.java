package baloni;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class Igra extends Frame{

	private Scena s= new Scena(this);
	public Igra() {
		super("Baloni");
		setVisible(true);
		setBounds(200, 200, 500, 500);
		addWindowListener(new WinAdapter());
		add(s,BorderLayout.CENTER);
	}
	
	private class WinAdapter extends WindowAdapter {
		@Override
		public void windowClosing(WindowEvent e) {
			s.zaustavi();
			dispose();
		}
	}	
	
	
	
	
	public static void main(String[] args) throws InterruptedException {
		new Igra();
	
	}
	
	
}
