package by.bsuir.iit.abramov.ppvis.findinthetable.client.controller;

import java.awt.event.ActionEvent;

import by.bsuir.iit.abramov.ppvis.findinthetable.client.util.ActionButton;
import by.bsuir.iit.abramov.ppvis.findinthetable.client.view.ContentPane;

public class ConnectButtonActionListener implements ButtonActionListener {

	@Override
	public void action(final ActionEvent e) {

		final ActionButton button = (ActionButton) e.getSource();
		((ContentPane) button.getContainer()).connect();
	}

}
