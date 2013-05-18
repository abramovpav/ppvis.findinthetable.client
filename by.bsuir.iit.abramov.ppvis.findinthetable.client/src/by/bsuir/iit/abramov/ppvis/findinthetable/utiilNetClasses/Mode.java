package by.bsuir.iit.abramov.ppvis.findinthetable.utiilNetClasses;

public enum Mode {
	EMPTY(0), ADD_STUDENT(6), DELETE_STUDENTS(7), GET_CURR_PAGE(8), GET_NEXT_PAGE(9), GET_PREV_PAGE(10), 
	GET_STUDENTS_COUNT(11), GET_VIEWSIZE(12), LEAF_NEXT_PAGE(13), LEAF_PREV_PAGE(14), 
	OPEN_FILE(15), SAVE_FILE(16), SEARCH1(17), SEARCH2(18), SEARCH3(19), SET_VIEWSIZE(20); 
	private final int	mode;

	Mode(final int mode) {

		this.mode = mode;
	}

	public final int getMode() {

		return mode;
	}
}
