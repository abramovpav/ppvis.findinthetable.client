package by.bsuir.iit.abramov.ppvis.findinthetable.utiilNetClasses;

public enum Mode {
	EMPTY(0), ADD_STUDENT(6), DELETE_STUDENTS(7), GET_CURR_PAGE(8), GET_NEXT_PAGE(9), GET_PREV_PAGE(
			10), GET_STUDENTS_COUNT(11), GET_VIEWSIZE(12), GET_FILES_LIST(13), LEAF_NEXT_PAGE(
			14), LEAF_PREV_PAGE(15), OPEN_FILE(16), SAVE_FILE(17), SEARCH1(18), SEARCH2(
			19), SEARCH3(20), SET_VIEWSIZE(21);
	private final int	mode;

	Mode(final int mode) {

		this.mode = mode;
	}

	public final int getMode() {

		return mode;
	}
}
