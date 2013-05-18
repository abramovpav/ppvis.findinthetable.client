package by.bsuir.iit.abramov.ppvis.findinthetable.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import by.bsuir.iit.abramov.ppvis.findinthetable.client.server.ModelInterface;
import by.bsuir.iit.abramov.ppvis.findinthetable.client.view.Desktop;
import by.bsuir.iit.abramov.ppvis.findinthetable.client.view.FindDialog;
import by.bsuir.iit.abramov.ppvis.findinthetable.model.Model;

public class NavigationButtonActionListener implements ActionListener {

	private final ActionListener	listener;

	public NavigationButtonActionListener(final ModelInterface model, final Desktop desktop) {

		listener = new DesktopNavigationButtonActionListener(model, desktop);
	}

	public NavigationButtonActionListener(final Model model, final FindDialog findDialog) {

		listener = new DialogNavigationButtonActionListener(model, findDialog);
	}

	@Override
	public void actionPerformed(final ActionEvent e) {

		listener.actionPerformed(e);
	}
}
