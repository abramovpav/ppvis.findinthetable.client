package by.bsuir.iit.abramov.ppvis.findinthetable.utiilNetClasses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Package implements Serializable {
	private Mode				mode				= Mode.EMPTY;
	private static final long	serialVersionUID	= 1L;
	public List<Object>			objects;

	public Package() {

		objects = new ArrayList<Object>();
	}

	public Package(final List<Object> objects) {

		this.objects = new ArrayList<Object>();
		this.objects.addAll(objects);
	}

	public Package(final Mode mode) {

		setMode(mode);
		objects = new ArrayList<Object>();
	}

	public Package(final Mode mode, final List<Object> objects) {

		setMode(mode);
		this.objects = new ArrayList<Object>();
		this.objects.addAll(objects);
	}

	public final Mode getMode() {

		return mode;
	}

	public final List<Object> getObjects() {

		return objects;
	}

	public void setMode(final Mode mode) {

		if (mode != null) {
			this.mode = mode;
		} else {
			this.mode = Mode.EMPTY;
			System.out.println("class Package. setMode. input: mode == null");
		}
	}

	public void setObjects(final List<Object> objects) {

		this.objects.clear();
		this.objects.addAll(objects);
	}
}
