package by.bsuir.iit.abramov.ppvis.findinthetable.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import by.bsuir.iit.abramov.ppvis.findinthetable.client.util.IllegalParametrs;
import by.bsuir.iit.abramov.ppvis.findinthetable.client.util.MenuItem;

public class EditButtonsListener implements ActionListener {

	private final MenuItem				menuItem;
	private final ButtonActionListener	listener;

	public EditButtonsListener(final MenuItem menuItem) throws IllegalParametrs {

		this.menuItem = menuItem;

		switch (menuItem) {
			case ADD:
				listener = new AddButtonActionListener();
			break;
			case DELETE:
				listener = new DeleteButtonActionListener();
			break;
			case FIND:
				listener = new FindButtonActionListener();
			break;
			case LANGUAGE_ENGLISH:
				listener = new EnglishLanguageButtonActionListener();
			break;
			case LANGUAGE_RUSSIAN:
				listener = new RussianLanguageButtonActionListener();
			break;
			case CONNECT:
				listener = new ConnectButtonActionListener();
			break;
			case DISCONNECT:
				listener = new DisconnectButtonActionListener();
			break;
			default:
				throw new IllegalParametrs();
		}
	}

	@Override
	public void actionPerformed(final ActionEvent e) {

		listener.action(e);
	}
}
