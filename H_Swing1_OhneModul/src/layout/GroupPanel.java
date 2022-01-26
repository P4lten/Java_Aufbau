package layout;


import java.awt.Font;
import javax.swing.*;
import javax.swing.GroupLayout.*;

public class GroupPanel extends JPanel {
	private static final long serialVersionUID = 1L;


	public GroupPanel(){
		// alle Controls
		JLabel lblTitel = new JLabel("Deine Adresse");
		lblTitel.setFont(new Font(getFont().getName(), Font.BOLD, 16));
        JLabel lblStrasse = new JLabel("Straße");
        JTextField txtStrasse = new JTextField();
        JLabel lblPlzOrt = new JLabel("Plz/Ort");
        JTextField txtPlz = new JTextField();
        JTextField txtOrt = new JTextField();
        JButton btnOK = new JButton("OK");
        JButton btnAbbrechen = new JButton("Abbrechen");

        // Layout 
		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		// Zwischenräume automatisch
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		// Sequentielle Gruppen für Controls, die innerhalb eine Spalte 
		// nebeneinander angezeigt werden sollen
		SequentialGroup grpOkAbbrechen = layout.createSequentialGroup()
			.addComponent(btnOK)
			.addComponent(btnAbbrechen);

		SequentialGroup grpPlzOrt = layout.createSequentialGroup()
			.addComponent(txtPlz, 50, 60, 70)
			.addComponent(txtOrt);

		// Parallele Gruppe für alle Zeilen mit mehreren Controls
		// Straße: Label und Textbox 
		ParallelGroup zeileStrasse = layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		 	.addComponent(lblStrasse)
		 	.addComponent(txtStrasse);
		
		// Plz/ort: Label und 2 Textboxen 
		ParallelGroup zeilePlzOrt = layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		 	.addComponent(lblPlzOrt)
		 	.addComponent(txtPlz)
		 	.addComponent(txtOrt);
		
		// Ok/Abbrechen: die beiden Buttons 
		ParallelGroup zeileOkAbbrechen = layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			.addComponent(btnOK)
			.addComponent(btnAbbrechen);
		

		// ParallelGroup für alle Spalten ausrichtungen
		// Spalte 1: die Labels Straße und PlzOrt
		ParallelGroup spalte1 = layout.createParallelGroup(GroupLayout.Alignment.LEADING)
			.addComponent(lblStrasse)
			.addComponent(lblPlzOrt);
		 
		// Spalte 2: der Rest 
		ParallelGroup spalte2 = layout.createParallelGroup(GroupLayout.Alignment.LEADING)
			.addComponent(lblTitel)
			.addComponent(txtStrasse)
			.addGroup(grpPlzOrt)
			.addGroup(GroupLayout.Alignment.CENTER, grpOkAbbrechen);


		// HOrizontal: die Spalten angeben
		layout.setHorizontalGroup(layout.createSequentialGroup()
		    .addGroup(spalte1)
		    .addGroup(spalte2));
		
		// Vertikal: die Zeilen angeben
		layout.setVerticalGroup(layout.createSequentialGroup()
			.addComponent(lblTitel)
		    .addGroup(zeileStrasse)
		    .addGroup(zeilePlzOrt)
		    .addGroup(zeileOkAbbrechen));

    
		layout.linkSize(SwingConstants.HORIZONTAL, btnOK, btnAbbrechen);

	}
	
}
