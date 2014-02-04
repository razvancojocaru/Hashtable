
/**
 * Class containing a String and an int, with a constant hashCode() method,
 * which always returns 1
 * @author Razvan Cojocaru
 */
public class LazyStudent extends Student {

	/**
	 * Constructor which sets the two members of the class
	 * @param nume			String 
	 * @param varsta		int
	 */
	public LazyStudent(String nume, int varsta) {
		super(nume, varsta);
	}

	/* (non-Javadoc)
	 * @see Student#hashCode()
	 * 
	 * constant hashCode, returns a constant: 1
	 */
	@Override
	public int hashCode() {
		return 1;
	}
	
}
