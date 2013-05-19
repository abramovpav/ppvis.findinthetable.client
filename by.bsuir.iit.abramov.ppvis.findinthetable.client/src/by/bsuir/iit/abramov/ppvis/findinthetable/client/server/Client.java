package by.bsuir.iit.abramov.ppvis.findinthetable.client.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;

import by.bsuir.iit.abramov.ppvis.findinthetable.client.view.Window;
import by.bsuir.iit.abramov.ppvis.findinthetable.model.Student;
import by.bsuir.iit.abramov.ppvis.findinthetable.utiilNetClasses.Mode;
import by.bsuir.iit.abramov.ppvis.findinthetable.utiilNetClasses.Package;

public class Client implements ModelInterface {

	private SocketChannel		client;
	private ObjectOutputStream	oos;
	private ObjectInputStream	ois;

	public Client() {

		client = null;
	}

	@Override
	public void addStudent(final Student student) {

		final List<Object> list = new ArrayList<Object>();
		list.add(student);
		if (isConnect()) {
			sendPackage(new Package(Mode.ADD_STUDENT, list));
		}
	}

	public boolean connect() {

		if (isConnect()) {
			return true;
		}

		try {
			client = SocketChannel.open();
			client.configureBlocking(true);
			if (client.connect(new InetSocketAddress("localhost", 12345))) {
				System.out.println("connection is established");
				oos = new ObjectOutputStream(client.socket().getOutputStream());
				ois = new ObjectInputStream(client.socket().getInputStream());
				return true;
			} else {
				System.out.println(Window.geti18nString("connection_failed"));
				client = null;
				return false;
			}
		} catch (final IOException e) {
			JOptionPane
					.showMessageDialog(null, Window.geti18nString("connection_failed"));
			client = null;
			return false;
		}
	}

	@Override
	public void deleteStudents(final List<Student> delStudents) {

		final List<Object> list = new ArrayList<Object>();
		list.addAll(delStudents);
		if (isConnect()) {
			sendPackage(new Package(Mode.DELETE_STUDENTS, list));
		}
	}

	public void disconnect() {

		if (!isConnect()) {
			client = null;
			return;
		}
		try {
			if (client != null) {
				client.close();
			}
			client = null;
		} catch (final IOException e) {
			System.out.println("Unable to close socket");
			client = null;
		}
	}

	@Override
	public List<Student> getCurrPageOfStudent() {

		return getStudents(Mode.GET_CURR_PAGE, new ArrayList<Object>());
	}

	@Override
	public List<String> getFilesList() {

		final List<String> files = new ArrayList<String>();

		if (isConnect()) {
			sendPackage(new Package(Mode.GET_FILES_LIST, new ArrayList<Object>()));
			Object obj;
			while (true) {
				try {
					obj = ois.readObject();
				} catch (final IOException e) {
					System.out.println("can't read");
					break;
				} catch (final ClassNotFoundException e) {
					System.out.println("can't read");
					break;
				}
				if (obj != null) {
					if (isPackage(obj)) {
						final Package pack = (Package) obj;
						final Mode mode = pack.getMode();
						switch (mode) {
							case GET_FILES_LIST:
								receiveFiles(files, pack);
								return files;
							default:
								System.out.println("default");
							break;
						}
						System.out.println();
						break;
					}
				}
			}
		}
		return files;
	}

	private Integer getIntegerValue(final Mode inputMode) {

		Integer intValue = null;
		if (isConnect()) {
			sendPackage(new Package(inputMode, new ArrayList<Object>()));
			Object obj, object;
			while (true) {
				try {
					obj = ois.readObject();
				} catch (final IOException e) {
					System.out.println("can't read");
					break;
				} catch (final ClassNotFoundException e) {
					System.out.println("can't read");
					break;
				}
				if (obj != null) {
					if (isPackage(obj)) {
						final Package pack = (Package) obj;
						final Mode mode = pack.getMode();
						switch (mode) {
							case GET_VIEWSIZE:
								intValue = null;
								object = pack.getObjects().get(0);
								if (isInteger(object)) {
									intValue = (Integer) object;
								}
								return intValue;
							case GET_STUDENTS_COUNT:
								intValue = null;
								object = pack.getObjects().get(0);
								if (isInteger(object)) {
									intValue = (Integer) object;
								}
								return intValue;
							default:
								System.out.println("default");
							break;
						}
						System.out.println();
						break;
					}
				}
			}
		}
		return intValue;
	}

	@Override
	public List<Student> getNextPageOfStudents() {

		return getStudents(Mode.GET_NEXT_PAGE, new ArrayList<Object>());
	}

	@Override
	public List<Student> getPrevPageOfStudents() {

		return getStudents(Mode.GET_PREV_PAGE, new ArrayList<Object>());
	}

	private List<Student> getStudents(final Mode inputMode, final List<Object> params) {

		final List<Student> students = new Vector<Student>();
		if (isConnect()) {
			sendPackage(new Package(inputMode, params));

			Object obj;

			while (true) {
				try {
					obj = ois.readObject();
				} catch (final IOException e) {
					System.out.println("can't read");
					break;
				} catch (final ClassNotFoundException e) {
					System.out.println("can't read");
					break;
				}
				if (obj != null) {
					if (isPackage(obj)) {
						final Package pack = (Package) obj;
						final Mode mode = pack.getMode();
						switch (mode) {
							case SEARCH1:
								receiveStudents(students, pack);
								return students;
							case SEARCH2:
								receiveStudents(students, pack);
								return students;
							case SEARCH3:
								receiveStudents(students, pack);
								return students;
							case GET_CURR_PAGE:
								receiveStudents(students, pack);
								return students;
							case GET_NEXT_PAGE:
								receiveStudents(students, pack);
								return students;
							case GET_PREV_PAGE:
								receiveStudents(students, pack);
								return students;
							default:
								System.out.println("default");
							break;
						}
						System.out.println();
						break;
					}
				}
			}
		}
		return students;
	}

	@Override
	public Integer getStudentsCount() {

		return getIntegerValue(Mode.GET_STUDENTS_COUNT);
	}

	@Override
	public Integer getViewSize() {

		return getIntegerValue(Mode.GET_VIEWSIZE);
	}

	@Override
	public boolean isConnect() {

		if (client == null) {
			return false;
		}
		return client.isConnected();
	}

	private boolean isInteger(final Object object) {

		return object.getClass() == Integer.class;
	}

	private boolean isPackage(final Object obj) {

		return obj.getClass() == Package.class;
	}

	@Override
	public void leafNext() {

		final List<Object> list = new ArrayList<Object>();
		if (isConnect()) {
			sendPackage(new Package(Mode.LEAF_NEXT_PAGE, list));
		}

	}

	@Override
	public void leafPrev() {

		final List<Object> list = new ArrayList<Object>();
		if (isConnect()) {
			sendPackage(new Package(Mode.LEAF_PREV_PAGE, list));
		}

	}

	@Override
	public void loadFile(final Object obj) {

		if (isConnect()) {
			if (obj != null && obj.getClass() == String.class) {
				final List<Object> objects = new ArrayList<Object>();
				objects.add(obj);
				sendPackage(new Package(Mode.OPEN_FILE, objects));
			}
		}
	}

	private void receiveFiles(final List<String> students, final Package pack) {

		for (final Object object : pack.getObjects()) {
			if (object.getClass() == String.class) {
				final String string = (String) object;
				students.add(string);
			}
		}
	}

	private void receiveStudents(final List<Student> students, final Package pack) {

		for (final Object object : pack.getObjects()) {
			if (object.getClass() == Student.class) {
				final Student student = (Student) object;
				students.add(student);
			}
		}
	}

	@Override
	public void saveFile(final Object obj) {

		if (isConnect()) {
			if (obj != null && obj.getClass() == String.class) {
				System.out.println("string" + (String) obj);
				final List<Object> objects = new ArrayList<Object>();
				objects.add(obj);
				sendPackage(new Package(Mode.SAVE_FILE, objects));
			}
		}

	}

	@Override
	public List<Student> search(final String name, final Integer group) {

		final List<Object> params = new ArrayList<Object>();
		params.add(name);
		params.add(group);
		final List<Student> students = getStudents(Mode.SEARCH2, params);

		return students;
	}

	@Override
	public List<Student> search(final String name, final String botStr,
			final String topStr) {

		final List<Object> params = new ArrayList<Object>();
		params.add(name);
		params.add(botStr);
		params.add(topStr);

		final List<Student> students = getStudents(Mode.SEARCH1, params);
		return students;
	}

	@Override
	public List<Student> search(final String name, final String examStr,
			final String botStr, final String topStr) {

		final List<Object> params = new ArrayList<Object>();
		params.add(name);
		params.add(examStr);
		params.add(botStr);
		params.add(topStr);

		final List<Student> students = getStudents(Mode.SEARCH3, params);
		return students;
	}

	public void sendPackage(final Package pack) {

		if (!isConnect()) {
			return;
		}
		try {
			oos.writeObject(pack);
		} catch (final IOException e) {
			System.out.println("can't send package");
		}
	}

	@Override
	public void setViewSize(final Integer viewSize) {

		final List<Object> list = new ArrayList<Object>();
		list.add(viewSize);
		if (isConnect()) {
			sendPackage(new Package(Mode.SET_VIEWSIZE, list));
		}

	}
}
