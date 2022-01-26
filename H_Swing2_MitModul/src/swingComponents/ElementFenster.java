package swingComponents;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class ElementFenster extends JFrame {

	private static final long serialVersionUID = 1;
	final JButton btnOk, btnVerwerfen;
	final JLabel lblMeldung;
	final JTextField txtZuname, txtVorname, txtPLZ, txtOrt, txtStrasse;
	final JCheckBox cbWindows, cbUnix, cbProgrammierung;
	final ButtonGroup cbgGeschlecht;
	final JRadioButton rbMann, rbFrau;
	final JTextArea taVorkenntnisse;
	final JList<String> lbWindowsVersionen, lbUnixVersionen;

	public ElementFenster() {
		super("Lehrgangsteilnehmer (Swing ohne Layout)");
		this.setSize(670, 400);
		this.getContentPane().setBackground(Color.CYAN);

		this.setLayout(null);

		// TODO: Handler erzeugen mit Referenz auf unser Fenster
		Handler meinHandler = new Handler(this);
		// Alle Komponenten werden in die ContentPane hinzugefügt
		// Überschrift
		getContentPane()
				.add(createLabel(40, 30, 200, 30, "Eingabe-Dialog", new Font("Serif", Font.BOLD | Font.ITALIC, 20)));

		// Textfelder mit JLabels
		getContentPane().add(createLabel(30, 80, 60, 20, "Zuname"));
		getContentPane().add(txtZuname = createTextField(90, 80, 200, 20));
		getContentPane().add(txtZuname);
		// TODO: Handler
		txtZuname.getDocument().addDocumentListener(meinHandler);

		getContentPane().add(createLabel(30, 105, 60, 20, "Vorname"));
		getContentPane().add(txtVorname = createTextField(90, 105, 200, 20));
		// TODO: Handler
		txtVorname.getDocument().addDocumentListener(meinHandler);
		
		getContentPane().add(createLabel(30, 130, 60, 20, "PLZ / Ort"));
		getContentPane().add(txtPLZ = createTextField(90, 130, 50, 20));
		// TODO: Filter und Handler
		txtPLZ.getDocument().addDocumentListener(meinHandler);
		
		getContentPane().add(txtOrt = createTextField(140, 130, 150, 20));
		// TODO: Handler
		txtOrt.getDocument().addDocumentListener(meinHandler);
		
		getContentPane().add(createLabel(30, 155, 60, 20, "Strasse"));
		getContentPane().add(txtStrasse = createTextField(90, 155, 200, 20));
		// TODO: Handler
		txtStrasse.getDocument().addDocumentListener(meinHandler);
		
		// Optionsfeld
		cbgGeschlecht = new ButtonGroup();
		getContentPane().add(rbMann = createRadioButton(90, 185, 100, 20, "männlich", cbgGeschlecht, true));
		getContentPane().add(rbFrau = createRadioButton(90, 205, 100, 20, "weiblich", cbgGeschlecht, false));

		getContentPane().add(createLabel(340, 40, 120, 20, "Vorkenntnisse", new Font("Serif", Font.BOLD, 14)));

		// CheckBoxen und Listen
		getContentPane().add(cbWindows = createCheckBox(335, 75, 80, 20, "Windows"));
		// TODO: Handler
		cbWindows.addItemListener(meinHandler);
		getContentPane()
				.add(lbWindowsVersionen = createList(340, 96, 75, 53, false, true, "Windows 10", "Win2019", "Win2016"));

		getContentPane().add(cbUnix = createCheckBox(425, 75, 80, 20, "Unix"));
		// TODO: Handler
		cbUnix.addItemListener(meinHandler);
		getContentPane().add(lbUnixVersionen = createList(430, 96, 75, 53, false, false, "Linux", "Solaris"));

		getContentPane().add(cbProgrammierung = createCheckBox(520, 75, 150, 20, "Programmierung"));

		// Textarea für Spezialkenntnisse
		getContentPane().add(taVorkenntnisse = createTextArea(340, 155, 300, 80, "Spezialkenntnisse"));

		// Schaltflächen
		getContentPane().add(btnOk = createButton(200, 270, 100, 23, "OK", "OK", false));
		// TODO: Handler
		btnOk.addActionListener(meinHandler);

		getContentPane().add(btnVerwerfen = createButton(320, 270, 100, 23, "Verwerfen", "CANCEL", true));
		// TODO: Handler
		btnVerwerfen.addActionListener(meinHandler);

		// Infomeldung
		getContentPane().add(lblMeldung = createLabel(180, 320, 270, 23, "Bitte nehmen Sie Ihre Eingaben vor",
				new Font("Serif", Font.BOLD, 15)));

		lblMeldung.setForeground(new Color(128, 0, 128));
		lblMeldung.setHorizontalAlignment(JLabel.CENTER);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	private JLabel createLabel(int x, int y, int w, int h, String text) {
		JLabel lbl = new JLabel(text);
		lbl.setBounds(x, y, w, h);

		return lbl;
	}

	private JLabel createLabel(int x, int y, int w, int h, String text, Font font) {
		JLabel lbl = createLabel(x, y, w, h, text);
		lbl.setFont(font);
		return lbl;

	}

	private JTextField createTextField(int x, int y, int w, int h) {
		JTextField tf = new JTextField();
		tf.setBounds(x, y, w, h);

		return tf;
	}

	private JTextArea createTextArea(int x, int y, int w, int h, String title) {
		JTextArea ta = new JTextArea();
		ta.setBounds(x, y, w, h);
		if (title != null) {
			ta.setBorder(BorderFactory.createTitledBorder(title));

		}
		return ta;
	}

	private JButton createButton(int x, int y, int w, int h, String text, String action, boolean enabled) {
		JButton btn = new JButton(text);
		btn.setActionCommand(action);
		btn.setBounds(x, y, w, h);
		btn.setEnabled(enabled);

		return btn;
	}

	private JCheckBox createCheckBox(int x, int y, int w, int h, String text) {
		JCheckBox cb = new JCheckBox(text);
		cb.setBounds(x, y, w, h);
		// Hintergrund setzen (null bewirkt , das der Hintergrund vom Parent verwendet
		// wird)
		cb.setBackground(null);
		return cb;
	}

	private JRadioButton createRadioButton(int x, int y, int w, int h, String text, ButtonGroup group,
			boolean checked) {
		JRadioButton rb = new JRadioButton(text, checked);
		rb.setBounds(x, y, w, h);
		// bei der Button group hinzufügen
		group.add(rb);

		// Hintergrund setzen (null bewirkt , das der Hintergrund vom Parent verwendet
		// wird)
		rb.setBackground(null);
		return rb;
	}

	private JList<String> createList(int x, int y, int w, int h, boolean enabled, boolean multiSel, String... items) {
		// Listbox die die angegebenen items enthält erzeugen
		JList<String> lst = new JList<>(items);
		lst.setBounds(x, y, w, h);
		if (multiSel) {
			lst.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		} else {
			lst.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}

		// TODO: enable anwenden
		lst.setEnabled(enabled);
		return lst;
	}

	// main method
	public static void main(String[] args) {
		new ElementFenster().setVisible(true);

	}

}
