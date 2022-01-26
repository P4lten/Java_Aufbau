package swingComponents;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Handler implements ActionListener, ItemListener, DocumentListener {

	private ElementFenster meinFenster;

	public Handler(ElementFenster fenster) {
		this.meinFenster = fenster;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// mit switch verzweigen (Achtung: case-sensitive)
		switch (e.getActionCommand()) {
		case "OK" -> onOK();
		case "CANCEL" -> onCancel();
		default -> throw new IllegalArgumentException("Unexpected value: " + e.getActionCommand());
		}

	}

	// abstracte Methode aus Itemlistener
	@Override
	public void itemStateChanged(ItemEvent e) {
		// herausfinden, ob ein StateChange auf "Selected" stattgefunden hat
		boolean isSelectet = e.getStateChange() == ItemEvent.SELECTED;
		// wenn es die Checkbox für Windows ist -> die Windows-Liste enablen
		if (e.getSource() == meinFenster.cbWindows) {
			meinFenster.lbWindowsVersionen.setEnabled(isSelectet);
		}
		// Wenn es die Checkbox für Unix ist -> die Unix Liste enablen
		if (e.getSource() == meinFenster.cbUnix) {
			meinFenster.lbUnixVersionen.setEnabled(isSelectet);
		}
	}

	// Überschreibungen für die DocumentListener-Methoden
	@Override
	public void insertUpdate(DocumentEvent e) {
		checkValid();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		checkValid();
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		checkValid();
	}

	private void onOK() {
		System.out.println("OK Button ausgelöst");
		// ein Teilnehmer-Objekt erzeugen und mit den eingegebenen Werten intialisieren
		Teilnehmer tnNeu = new Teilnehmer();
		tnNeu.setZuname(meinFenster.txtZuname.getText());
		tnNeu.setVorname(meinFenster.txtVorname.getText());
		tnNeu.setStrasse(meinFenster.txtStrasse.getText());
		tnNeu.setPlz(meinFenster.txtPLZ.getText());
		tnNeu.setOrt(meinFenster.txtOrt.getText());

		// Radiobuttons für Geschlecht
		Teilnehmer.Geschlecht geschlecht;
//		if(meinFenster.rbMann.isSelected()) {
//			geschlecht = Teilnehmer.Geschlecht.MAENNLICH;
//		}else {
//			geschlecht = Teilnehmer.Geschlecht.WEIBLICH;
//		}
//		kürzer
		geschlecht = meinFenster.rbMann.isSelected() ? Teilnehmer.Geschlecht.MAENNLICH : Teilnehmer.Geschlecht.WEIBLICH;
		tnNeu.setGeschlecht(geschlecht);

		// Checkboxen und Listboxen
		if (meinFenster.cbWindows.isSelected()) {
			List<String> werte = meinFenster.lbWindowsVersionen.getSelectedValuesList();
			tnNeu.setWindowsKenntnisse(werte.toString());
		}

		if (meinFenster.cbUnix.isSelected()) {
			List<String> werte = meinFenster.lbUnixVersionen.getSelectedValuesList();
			tnNeu.setUnixKenntnisse(werte.toString());
		}

		tnNeu.setProgrammierKenntnisse(meinFenster.cbProgrammierung.isSelected());

		// Textarea analog Textfield
		tnNeu.setSpezialKenntnisse(meinFenster.taVorkenntnisse.getText());

		String info = tnNeu.toString();
		System.out.println("Daten erfasst: ");
		System.out.println(info);

		JOptionPane.showConfirmDialog(meinFenster, info, "Daten erfasst", JOptionPane.DEFAULT_OPTION,
				JOptionPane.INFORMATION_MESSAGE);

	}

	private void onCancel() {
		System.out.println("Cancel Button ausgelöst");
		// alle eingaben löschen
		meinFenster.txtZuname.setText("");
		meinFenster.txtOrt.setText("");
		meinFenster.txtPLZ.setText("");
		meinFenster.txtStrasse.setText("");
		meinFenster.txtVorname.setText("");
		
		// Default-radio-Button selektieren, der andere wird automatisch de-selektiert
		meinFenster.rbMann.setSelected(true);
		
		meinFenster.cbWindows.setSelected(false);
		meinFenster.cbProgrammierung.setSelected(false);
		meinFenster.cbUnix.setSelected(false);
		
		meinFenster.taVorkenntnisse.setText("");
		
		meinFenster.lbUnixVersionen.clearSelection();
		meinFenster.lbWindowsVersionen.clearSelection();
		
		
	}

	private void checkValid() {
		boolean valid = !meinFenster.txtVorname.getText().isEmpty() && !meinFenster.txtZuname.getText().isEmpty()
				&& !meinFenster.txtOrt.getText().isEmpty() && !meinFenster.txtPLZ.getText().isEmpty()
				&& !meinFenster.txtStrasse.getText().isEmpty();

		System.out.println("checkValid: gültig= " + valid);

		// je nach gültigkeit enablen oder disablen
		meinFenster.btnOk.setEnabled(valid);
	}

}
