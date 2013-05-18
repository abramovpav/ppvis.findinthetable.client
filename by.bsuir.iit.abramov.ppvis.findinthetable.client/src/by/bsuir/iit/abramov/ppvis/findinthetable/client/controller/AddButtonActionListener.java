package by.bsuir.iit.abramov.ppvis.findinthetable.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import by.bsuir.iit.abramov.ppvis.findinthetable.client.util.ActionButton;
import by.bsuir.iit.abramov.ppvis.findinthetable.client.view.ADialog;
import by.bsuir.iit.abramov.ppvis.findinthetable.client.view.ContentPane;
import by.bsuir.iit.abramov.ppvis.findinthetable.client.view.Window;
import by.bsuir.iit.abramov.ppvis.findinthetable.model.Student;

public class AddButtonActionListener implements ActionListener, ButtonActionListener {

	@Override
	public void action(final ActionEvent e) {

		actionPerformed(e);

	}

	@Override
	public void actionPerformed(final ActionEvent e) {

		final ActionButton button = (ActionButton) e.getSource();
		final ContentPane contentPane = (ContentPane) button.getContainer();
		if (!contentPane.isConnect()) {
			JOptionPane.showMessageDialog(null, Window.geti18nString("no_connection"));
			return;
		}
		final ADialog dialog = new ADialog();
		dialog.setModal(true);
		dialog.setVisible(true);
		final Student student = dialog.getStudent();
		contentPane.addStudent(student);

	}

}
