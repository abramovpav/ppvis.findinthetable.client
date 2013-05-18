package by.bsuir.iit.abramov.ppvis.findinthetable.client.controller;

import by.bsuir.iit.abramov.ppvis.findinthetable.client.server.ModelInterface;
import by.bsuir.iit.abramov.ppvis.findinthetable.client.view.Desktop;

public class Controller {
	private final ModelInterface	model;
	private final Desktop			desktop;

	public Controller(final ModelInterface model, final Desktop desktop) {

		this.model = model;
		this.desktop = desktop;
	}

	public void update() {

		desktop.refresh();
	}
}
