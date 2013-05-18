package by.bsuir.iit.abramov.ppvis.findinthetable.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;

import by.bsuir.iit.abramov.ppvis.findinthetable.client.server.ModelInterface;
import by.bsuir.iit.abramov.ppvis.findinthetable.client.view.Desktop;
import by.bsuir.iit.abramov.ppvis.findinthetable.client.view.Window;
import by.bsuir.iit.abramov.ppvis.findinthetable.model.Model;
import by.bsuir.iit.abramov.ppvis.findinthetable.model.Student;

public class DesktopViewSizeButtonListener implements ActionListener {
	private final ModelInterface		client;
	private final Desktop	desktop;
	private int				modifier;

	public DesktopViewSizeButtonListener(final ModelInterface client, final Desktop desktop,
			final String caption) {

		this.client = client;
		this.desktop = desktop;
		if (Desktop.INCREMENT.equals(caption)) {
			modifier = 1;
		} else {
			modifier = -1;
		}
	}

	@Override
	public void actionPerformed(final ActionEvent e) {

		// listener.actionPerformed(e);
		final List<Student> pageOfStudents;
		if (!client.isConnect()) {
			JOptionPane.showMessageDialog(null, Window.geti18nString("no_connection"));
			pageOfStudents = new Vector<Student>();
		}
		else {
			client.setViewSize(client.getViewSize() + modifier);
			pageOfStudents = client.getCurrPageOfStudent();
		}
		desktop.setStudents(desktop.getTableModel(), pageOfStudents);
	}

}
