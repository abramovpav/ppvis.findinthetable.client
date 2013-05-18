package by.bsuir.iit.abramov.ppvis.findinthetable.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import by.bsuir.iit.abramov.ppvis.findinthetable.client.server.ModelInterface;
import by.bsuir.iit.abramov.ppvis.findinthetable.client.view.Desktop;
import by.bsuir.iit.abramov.ppvis.findinthetable.client.view.Window;
import by.bsuir.iit.abramov.ppvis.findinthetable.model.Model;
import by.bsuir.iit.abramov.ppvis.findinthetable.model.Student;

public class DesktopNavigationButtonActionListener implements ActionListener {
	private final ModelInterface	client;
	private final Desktop			desktop;

	public DesktopNavigationButtonActionListener(final ModelInterface client,
			final Desktop desktop) {

		this.client = client;
		this.desktop = desktop;
	}

	@Override
	public void actionPerformed(final ActionEvent e) {

		final JButton button = (JButton) e.getSource();
		List<Student> pageOfStudents = new Vector<Student>();
		if (client.isConnect()) {
			if (Window.geti18nString(Desktop.BUTTON_NEXT).equalsIgnoreCase(button.getText())) {
				pageOfStudents = client.getNextPageOfStudents();
			} else if (Window.geti18nString(Desktop.BUTTON_PREV).equalsIgnoreCase(
					button.getText())) {
				pageOfStudents = client.getPrevPageOfStudents();
			}
		}
		else {
			JOptionPane.showMessageDialog(null, Window.geti18nString("no_connection"));
		}
		desktop.setStudents(desktop.getTableModel(), pageOfStudents);

	}

}
