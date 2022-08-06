package igra;

import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class Igra extends Frame {
	public static final int IGRANJE = 0;
	public static final int IZMENA = 1;
	private Button pocni = new Button("Pocni");
	private int brPoena;
	private Label poeni;
	private int rezimIgre=Igra.IZMENA;
//	static int TravaZid;
	TextField novcica;
	static CheckboxGroup grupaPolja;
	public TextField getNovcica() {
		return novcica;
	}
	public int getRezimIgre() {
		return rezimIgre;
	}

	public void uvecajPoene() {
		brPoena++; 
		poeni.setText("Poena:"+brPoena);
		int b = Integer.parseInt(novcica.getText())-1;
		novcica.setText(Integer.toString(b));
	}
	public Igra(){
		super("Igra");
		setVisible(true);
		popuniProzor();
	}
	

	private void popuniProzor() {
											// GORNJI MENI
		MenuBar bar = new MenuBar();
		MenuItem izmena = new MenuItem("Rezim izmena");
		MenuItem igranje = new MenuItem("Rezim igranje");
		Menu rezim = new Menu("Rezim");
		rezim.add(izmena);
		rezim.add(igranje);
		bar.add(rezim);
		setMenuBar(bar);
		
										//CENTRALNI DEO (MREZA)

		Mreza mreza = new Mreza(this);
		add(mreza,BorderLayout.CENTER);
		
											//DESNI DEO 
		Panel east = new Panel(new GridLayout(1,2));
		Panel radioDugme = new Panel(new GridLayout(2,1));
		
		CheckboxGroup grupa = new CheckboxGroup();
		grupaPolja = grupa;
		Checkbox c1 = new Checkbox("Trava",true, grupa);
		c1.setBackground(Color.GREEN);
		Checkbox c2 = new Checkbox("Zid",false,grupa);
		c2.setBackground(Color.LIGHT_GRAY);
		radioDugme.add(c1);
		radioDugme.add(c2);
		
		
		Label podloga = new Label("Podloga:");
		east.add(podloga);
		east.add(radioDugme);
		add(east,BorderLayout.EAST);
		
		
											// DONJI PANEL
		Panel bottomPanel = new Panel();
		Label l1 = new Label("Novcica: ");
		novcica = new TextField();
		novcica.setEnabled(true);
		poeni = new Label("Poena:" + brPoena);
		bottomPanel.add(l1);
		bottomPanel.add(novcica);
		bottomPanel.add(poeni);
		bottomPanel.add(pocni);
		add(bottomPanel,BorderLayout.SOUTH);
		
											//OSLUSKIVACI
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		
		
		igranje.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				rezimIgre = IGRANJE;
			}
		});
		izmena.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				rezimIgre = IZMENA;
				mreza.zaustavi();

			}
		});
		
		pocni.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(rezimIgre == Igra.IGRANJE) mreza.pokreni();
			}
		});
		
	}

	public static void main(String[] args) {
		new Igra();
	}	
}
