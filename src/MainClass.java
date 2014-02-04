import java.util.LinkedList;
import java.util.List;
import java.util.Random;


/**
 * Class used to test the average GET time for a HashMap of Student(class with
 * a proper hashCode) versus a HashMap of LazyStudent(class with a constant
 * hashCode).
 * 
 * @author Razvan Cojocaru
 */
public class MainClass {
	
	/**
	 * Returns a list of random strings
	 * @param count		number of strings in the list
	 * @return			a list of strings
	 */
	private static List<String> getRandomStrings(int count) {
		Random r = new Random();
		StringBuffer buf = new StringBuffer();
		List<String> strings = new LinkedList<String>();

		for (int s = 0; s < count; s++) {
			buf.delete(0, buf.length());
			int length = r.nextInt(10) + 1; // avoid 0 length strings

			for (int i = 0; i < length; i++) {
				char c = (char)(r.nextInt(93) + 33);
				buf.append(c);
			}

			strings.add(buf.toString());
		}

		return strings;
	}
	
	public static void main(String[] args) {
		List<String> nume = getRandomStrings(10000);
		int varsta;
		int nota;

		
		System.out.println("---------- MyHashMap<Student> ----------");
		
		// Creare lista Student
		Student s;		
		LinkedList<Student> lista = new LinkedList<Student>();
		for ( int i=0; i<10000; i++ ) {
			varsta = (int) (Math.random() * 100);
			s = new Student(nume.get(i), varsta);
			lista.add(s);
		}
		
		// Initializare Map si inserarea Student
		MyHashMapImpl<Student, Integer> map = 
				new MyHashMapImpl<Student, Integer>(1000);
				
		for ( int i=0; i<10000; i++ ) {
			nota = (int) (Math.random() * 10);
			map.put(lista.get(i), nota);
		}
		
		// Creare lista pentru interogari
		lista = new LinkedList<Student>();
		for ( int i=0; i<10000; i++ ) {
			varsta = (int) (Math.random() * 100);
			s = new Student(nume.get(i), varsta);
			lista.add(s);
		}
		
		// Accesarile de Map pentru Student (operatiile get)
		long begin = System.currentTimeMillis();
		for ( int i=0; i<10000; i++ )
			map.get(lista.get(i));
		long end = System.currentTimeMillis();
		
		System.out.println("Time for 10.000 get operations: " + (end - begin));
		System.out.println();
		
		
		
		System.out.println("-------- MyHashMap<LazyStudent> --------");
		
		// Creare lista LazyStudent
		LazyStudent s2;	
		LinkedList<LazyStudent> lazyLista = new LinkedList<LazyStudent>();
		for ( int i=0; i<10000; i++ ) {
			varsta = (int) (Math.random() * 100);
			s2 = new LazyStudent(nume.get(i), varsta);
			lazyLista.add(s2);
		}
		
		// Initializare Map si inserarea LazyStudent
		MyHashMapImpl<LazyStudent, Integer> lazyMap = 
				new MyHashMapImpl<LazyStudent, Integer>(1000);
		
		for ( int i=0; i<10000; i++ ) {
			nota = (int) (Math.random() * 10);
			lazyMap.put(lazyLista.get(i), nota);
		}

		// Creare lista pentru interogari
		lazyLista = new LinkedList<LazyStudent>();
		for ( int i=0; i<10000; i++ ) {
			varsta = (int) (Math.random() * 100);
			s2 = new LazyStudent(nume.get(i), varsta);
			lazyLista.add(s2);
		}
		
		// Accesarile de Map pentru LazyStudent (operatiile get)
		begin = System.currentTimeMillis();
		for ( int i=0; i<10000; i++ )
			lazyMap.get(lazyLista.get(i));
		end = System.currentTimeMillis();
		
		System.out.println("Time for 10.000 get operations: " + (end - begin));
		
	}
}
