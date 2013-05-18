package by.bsuir.iit.abramov.ppvis.findinthetable.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

public class DialogCancelButtonActionListener implements ActionListener {
	private final JDialog	dialog;

	public DialogCancelButtonActionListener(final JDialog dialog) {

		this.dialog = dialog;
	}

	@Override
	public void actionPerformed(final ActionEvent e) {

		dialog.setVisible(false);
		dialog.dispose();
	}
}
