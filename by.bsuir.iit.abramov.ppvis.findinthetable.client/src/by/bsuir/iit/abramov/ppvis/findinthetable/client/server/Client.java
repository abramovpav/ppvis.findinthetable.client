package by.bsuir.iit.abramov.ppvis.findinthetable.client.server;

import java.io.File;
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

		return getModePageOfStudent(Mode.GET_CURR_PAGE);
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
					e.printStackTrace();
					break;
				} catch (final ClassNotFoundException e) {
					e.printStackTrace();
					System.out.println("can't read");
					break;
				}
				if (obj != null) {
					if (obj.getClass() == Package.class) {
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

		Integer integerValue = null;
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
					if (obj.getClass() == Package.class) {
						final Package pack = (Package) obj;
						final Mode mode = pack.getMode();
						switch (mode) {
							case GET_VIEWSIZE:
								integerValue = null;
								object = pack.getObjects().get(0);
								if (object.getClass() == Integer.class) {
									integerValue = (Integer) object;
								}
								return integerValue;
							case GET_STUDENTS_COUNT:
								integerValue = null;
								object = pack.getObjects().get(0);
								if (object.getClass() == Integer.class) {
									integerValue = (Integer) object;
								}
								return integerValue;
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
		return integerValue;
	}

	private List<Student> getModePageOfStudent(final Mode inputMode) {

		final List<Student> students = new Vector();

		// if (isConnect()) {
		sendPackage(new Package(inputMode, new ArrayList<Object>()));

		Object obj;

		while (true) {
			try {
				obj = ois.readObject();
			} catch (final IOException e) {
				System.out.println("can't read");
				e.printStackTrace();
				break;
			} catch (final ClassNotFoundException e) {
				e.printStackTrace();
				System.out.println("can't read");
				break;
			}
			if (obj != null) {
				if (obj.getClass() == Package.class) {
					final Package pack = (Package) obj;
					final Mode mode = pack.getMode();
					switch (mode) {
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
		// }
		return students;
	}

	@Override
	public List<Student> getNextPageOfStudents() {

		return getModePageOfStudent(Mode.GET_NEXT_PAGE);
	}

	@Override
	public List<Student> getPrevPageOfStudents() {

		return getModePageOfStudent(Mode.GET_PREV_PAGE);
	}

	@Override
	public Integer getStudentsCount() {

		return getIntegerValue(Mode.GET_STUDENTS_COUNT);
	}

	/*
	 * case ADD_STUDENT: break; case DELETE_STUDENTS: break; case GET_CURR_PAGE:
	 * break; case GET_NEXT_PAGE: break; case GET_PREV_PAGE: break; case
	 * GET_STUDENTS_COUNT: break; case GET_VIEWSIZE: break; case LEAF_NEXT_PAGE:
	 * break; case LEAF_PREV_PAGE: break; case OPEN_FILE: break; case SAVE_FILE:
	 * break; case SEARCH1: break; case SEARCH2: break; case SEARCH3: break;
	 * case SET_VIEWSIZE: break;
	 */

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
				System.out.println("string" + (String)obj);
				final List<Object> objects = new ArrayList<Object>();
				objects.add(obj);
				sendPackage(new Package(Mode.SAVE_FILE, objects));
			}
		}

	}

	private List<Student> search(final Mode inputMode, final List<Object> params) {

		final List<Student> students = new Vector();
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
				if (obj.getClass() == Package.class) {
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
						default:
							System.out.println("default");
						break;
					}
					System.out.println();
					break;
				}
			}
		}
		return students;
	}

	@Override
	public List<Student> search(final String name, final Integer group) {

		final List<Object> params = new ArrayList<Object>();
		params.add(name);
		params.add(group);
		final List<Student> students = search(Mode.SEARCH2, params);

		return students;
	}

	@Override
	public List<Student> search(final String name, final String botStr,
			final String topStr) {

		final List<Object> params = new ArrayList<Object>();
		params.add(name);
		params.add(botStr);
		params.add(topStr);

		final List<Student> students = search(Mode.SEARCH1, params);
		/*
		 * sendPackage(new Package(Mode.SEARCH1, params));
		 * 
		 * Object obj;
		 * 
		 * 
		 * while (true) { try { obj = ois.readObject(); } catch (IOException e)
		 * { System.out.println("can't read"); break; } catch
		 * (ClassNotFoundException e) { System.out.println("can't read"); break;
		 * } if (obj != null) { if (obj.getClass() == Package.class) { Package
		 * pack = (Package) obj; Mode mode = pack.getMode(); switch (mode) {
		 * case SEARCH1: receiveStudents(students, pack); return students;
		 * default: System.out.println("default"); break; }
		 * System.out.println(); break; } } }
		 */
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

		final List<Student> students = search(Mode.SEARCH3, params);
		/*
		 * sendPackage(new Package(Mode.SEARCH3, params));
		 * 
		 * Object obj;
		 * 
		 * 
		 * while (true) { try { obj = ois.readObject(); } catch (IOException e)
		 * { System.out.println("can't read"); break; } catch
		 * (ClassNotFoundException e) { System.out.println("can't read"); break;
		 * } if (obj != null) { if (obj.getClass() == Package.class) { Package
		 * pack = (Package) obj; Mode mode = pack.getMode(); switch (mode) {
		 * case SEARCH3: receiveStudents(students, pack); return students;
		 * default: System.out.println("default"); break; }
		 * System.out.println(); break; } } }
		 */
		return students;
	}

	public void sendMessage(final Mode text) throws IOException {

		if (client == null) {
			return;
		}

		oos.writeObject(text);
	}

	public boolean sendMessage(final String text) throws IOException {

		if (client == null) {
			return false;
		}

		oos.writeObject(text);
		return true;
	}

	public void sendPackage(final Package pack) {

		if (client == null) {
			return;
		}
		try {
			oos.writeObject(pack);
		} catch (final IOException e) {
			System.out.println("can't send package");
		}
	}

	@Override
	public void setStudents(final List<Student> students) {

		// TODO Auto-generated method stub
		// реализация не нужна

	}

	@Override
	public void setViewSize(final Integer viewSize) {

		final List<Object> list = new ArrayList<Object>();
		list.add(viewSize);
		sendPackage(new Package(Mode.SET_VIEWSIZE, list));

	}
}
