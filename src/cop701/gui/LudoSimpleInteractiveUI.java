package cop701.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import cop701.common.GameState;
import cop701.main.Ludo;

public class LudoSimpleInteractiveUI {

	private GameState gameState;
	
	public LudoSimpleInteractiveUI(GameState gameState) {
		this.gameState = gameState;
		buildUI();
	}

	public void buildUI() {
		JFrame f = new JFrame("Ludo");
		JButton b1 = new JButton("Submit");
		JButton b2 = new JButton("<next>");
		JTextArea t1 = new JTextArea("");
		JLabel l1 = new JLabel("Input to server");
		b1.setBounds(20, 20, 80, 30);
		b2.setBounds(120, 20, 80, 30);
		l1.setBounds(10, 80, 150, 30);
		t1.setBounds(150, 80, 300, 30);
		f.add(b1);
		f.add(b2);
		f.add(t1);
		f.add(l1);
		f.setSize(500, 200);
		f.setLayout(null);
		f.setVisible(true);
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = t1.getText();
				t1.setText("");
				Ludo.submitText(gameState, text);
			}
		});
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = t1.getText();
				t1.setText(text + "<next>");
			}
		});
	}

}
