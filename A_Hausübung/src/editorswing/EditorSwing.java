package editorswing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class EditorSwing extends JFrame {

	
	private static final long serialVersionUID = 1L;

	final JButton btnOpen, btnSaveAs, btnExit, btnSaveAt, btnNew;

	final static String ACTION_NEW = "NEW", ACTION_OPEN = " OPEN", ACTION_SAVEAS = "SAVEAS", ACTION_EXIT = "EXIT", ACTION_SAVEAT = "SAVEAT";
	final JList<String> lbEreignisse;
	final DefaultListModel<String> modelEreignisse;

	final JTextArea textArea;

	private EditorSwing() {
		super("Unbenannt");
		setSize(600, 500);
		getContentPane().setBackground(Color.LIGHT_GRAY);
		
		EditorHandler meinHandler = new EditorHandler(this);

		JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));

		pnlButtons.add(btnOpen = new JButton("Öffnen"));
		btnOpen.setActionCommand(ACTION_OPEN);
		btnOpen.addActionListener(meinHandler::onOpen);

		pnlButtons.add(btnSaveAs = new JButton("Speichern"));
		btnSaveAs.setActionCommand(ACTION_SAVEAS);
		btnSaveAs.addActionListener(meinHandler::onSaveAs);

		pnlButtons.add(btnSaveAt = new JButton("Speichern unter"));
		btnSaveAt.setActionCommand(ACTION_SAVEAT);
		btnSaveAt.addActionListener(meinHandler::onSaveAt);

		pnlButtons.add(btnNew = new JButton("Neu"));
		btnNew.setActionCommand(ACTION_NEW);
		btnNew.addActionListener(meinHandler::onNew);

		pnlButtons.add(btnExit = new JButton("Beenden"));
		btnExit.setActionCommand(ACTION_EXIT);
		btnExit.addActionListener(meinHandler::onExit);

		add(pnlButtons, BorderLayout.SOUTH);


		textArea = new JTextArea();
		Font font = new Font("Arial", 0, 15);
		textArea.setFont(font);
		textArea.setBackground(Color.white);
	

		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
		getContentPane().add(scroll);
		
		
		modelEreignisse = new DefaultListModel<>();
		lbEreignisse = new JList<>(modelEreignisse);

		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// beim Exit-Button einen Klick auslösen
				btnExit.doClick();
			}
		});
	}
	
	

	public static void main(String[] args) {
		new EditorSwing().setVisible(true);

	}
}
