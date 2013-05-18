package by.bsuir.iit.abramov.ppvis.findinthetable.client.util;

public enum MenuItem {
	OPEN("open"), SAVE("save"), CLOSE("close"), EXIT("exit"), ADD("add"), DELETE("delete"), FIND(
			"find"), LANGUAGE_ENGLISH("english"), LANGUAGE_RUSSIAN("russian"), AUTHOR(
			"author"), CONNECT("connect");
	private final String	name;

	MenuItem(final String name) {

		this.name = name;
	}

	public final String getName() {

		return name;
	}
}
