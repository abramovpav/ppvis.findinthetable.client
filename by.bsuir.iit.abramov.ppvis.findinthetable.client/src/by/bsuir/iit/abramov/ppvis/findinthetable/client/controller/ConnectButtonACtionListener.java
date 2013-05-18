package by.bsuir.iit.abramov.ppvis.findinthetable.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import by.bsuir.iit.abramov.ppvis.findinthetable.client.server.Client;
import by.bsuir.iit.abramov.ppvis.findinthetable.client.util.ActionButton;
import by.bsuir.iit.abramov.ppvis.findinthetable.client.view.ContentPane;
import by.bsuir.iit.abramov.ppvis.findinthetable.utiilNetClasses.Mode;

public class ConnectButtonACtionListener implements ButtonActionListener {
	


	@Override
	public void action(ActionEvent e) {
		final ActionButton button = (ActionButton) e.getSource();
		((ContentPane) button.getContainer()).connect();
	}

}
