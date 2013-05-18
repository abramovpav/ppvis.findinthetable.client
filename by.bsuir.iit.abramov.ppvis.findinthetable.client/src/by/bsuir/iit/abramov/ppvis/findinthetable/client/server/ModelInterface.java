package by.bsuir.iit.abramov.ppvis.findinthetable.client.server;

import java.io.File;
import java.util.List;

import by.bsuir.iit.abramov.ppvis.findinthetable.model.Student;

public interface ModelInterface {
	public void addStudent(final Student student);

	public void deleteStudents(final List<Student> delStudents);

	public List<Student> getCurrPageOfStudent();

	public List<String> getFilesList();

	public List<Student> getNextPageOfStudents();

	public List<Student> getPrevPageOfStudents();

	public Integer getStudentsCount();

	public Integer getViewSize();

	public boolean isConnect();

	public void leafNext();

	public void leafPrev();

	public void loadFile(Object obj);

	public void saveFile(final Object obj);

	public List<Student> search(final String name, final Integer group);

	public List<Student> search(final String name, final String botStr,
			final String topStr);

	public List<Student> search(final String name, final String examStr,
			final String botStr, final String topStr);

	public void setStudents(final List<Student> students);

	public void setViewSize(final Integer viewSize);
}
