package by.bsuir.iit.abramov.ppvis.findinthetable.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import by.bsuir.iit.abramov.ppvis.findinthetable.client.server.ModelInterface;
import by.bsuir.iit.abramov.ppvis.findinthetable.client.view.Desktop;
import by.bsuir.iit.abramov.ppvis.findinthetable.client.view.FindDialog;
import by.bsuir.iit.abramov.ppvis.findinthetable.model.Model;

public class ViewSizeButtonListener implements ActionListener {

	private final ActionListener	listener;

	public ViewSizeButtonListener(final Model model, final FindDialog findDialog,
			final String caption) {

		listener = new DialogViewSizeButtonListener(model, findDialog, caption);
	}

	public ViewSizeButtonListener(final ModelInterface client, final Desktop desktop,
			final String caption) {

		listener = new DesktopViewSizeButtonListener(client, desktop, caption);
	}

	@Override
	public void actionPerformed(final ActionEvent e) {

		listener.actionPerformed(e);
	}
}
