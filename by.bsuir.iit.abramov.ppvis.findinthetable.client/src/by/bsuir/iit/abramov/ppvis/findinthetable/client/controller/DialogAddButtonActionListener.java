package by.bsuir.iit.abramov.ppvis.findinthetable.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import by.bsuir.iit.abramov.ppvis.findinthetable.client.view.ADialog;

public class DialogAddButtonActionListener implements ActionListener {
	private final ADialog	dialog;

	public DialogAddButtonActionListener(final ADialog dialog) {

		this.dialog = dialog;
	}

	@Override
	public void actionPerformed(final ActionEvent e) {

		if (dialog.verifyStudentFields() && dialog.verifyExamsFields()) {
			dialog.generateStudent();
			dialog.setVisible(false);
			dialog.dispose();

		}
	}
}
