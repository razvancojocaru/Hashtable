
/**
 * Class containing a String and an int, with an overridden hashCode() method.
 * @author Razvan Cojocaru
 */
public class Student {
	
	private String nume;
	private int varsta;
	
	/**
	 * Constructor which sets the two members of the class
	 * @param nume			String 
	 * @param varsta		int
	 */
	public Student(String nume, int varsta) {
		this.nume = nume;
		this.varsta = varsta;
	}
	
	/**
	 * @return 	a String with the <code>nume<code> member 
	 */
	public String getNume() {
		return this.nume;
	}
	
	/**
	 * @return	an int with the <code>varsta<code> member
	 */
	public int getVarsta() {
		return this.varsta;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 * 
	 * unique hashCode dependent of the <code>varsta<code> member and the 
	 * hashCode() of the <code>nume<code> member
	 */
	@Override
	public int hashCode() {
		int h = 17;
		h = 37 * h + varsta;
		h = 37 * h + nume.hashCode();
		return h;
	}
	
	@Override
	public boolean equals(Object o) {
		if ( ( this.nume == ((Student)o).getNume() ) &&
			( this.varsta == ((Student)o).getVarsta() ) )
			return true;
		return false;
	}
}
