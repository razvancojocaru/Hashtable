import java.util.ArrayList;
import java.util.List;


/**
 * Implementantion for MyHashMap
 * @author Razvan Cojocaru
 *
 * @param <K>	Type of the key for the HashMap
 * @param <V>	Type of the value for the HashMap
 */
public class MyHashMapImpl<K, V> implements MyHashMap<K, V> {
	
	private ArrayList<MyBucket> buckets;
	private int numBuckets;
	private int size;
	
	/**
	 * Defaul constructor, initializes the HashMap and sets the maximum 
	 * number of buckets.
	 * @param numBuckets	Maximum number of buckets for the HashMap
	 */
	public MyHashMapImpl(int numBuckets) {
		buckets = new ArrayList<MyBucket>(numBuckets);
		
		// We need to add empty buckets in the <code>buckets<code> array to be
		//able to use the <code>get<code> method of ArrayList.
		for ( int i=0; i<numBuckets ; i++ )
			buckets.add( new MyBucket() );
		
		// Set maximum number of buckets and initial size 0.
		this.numBuckets = numBuckets;
		this.size = 0;
	}

	@Override
	public V get(K key) {
		// Calculate bucketIndex using the given formula.
		int bucketIndex = Math.abs(key.hashCode()) % numBuckets;
		
		// Return the value of the corresponding entry if found, or null
		//otherwise.
		for (MyEntry e : buckets.get(bucketIndex).bucket )
			if ( e.key.equals(key) )
				return e.getValue();
		return null;
	}

	@Override
	public V put(K key, V value) {
		// Calculate bucketIndex using the given formula.
		int bucketIndex = Math.abs(key.hashCode()) % numBuckets;
		
		// Check if there is already an entry with the given key inside the
		//HashMap. If true, insert the new value in the entry and return the
		//old value. If no entry with the given key is found, insert a new 
		//entry and return null.
		MyEntry entry = new MyEntry(key, value);
		for (MyEntry e : buckets.get(bucketIndex).bucket )
			if ( e.key.equals(key) ) {
				V oldValue = e.value;
				e.value = value;
				return oldValue;
			}
		buckets.get(bucketIndex).bucket.add(entry);
		size++;
		return null;
	}

	@Override
	public V remove(K key) {
		// Calculate bucketIndex using the given formula.
		int bucketIndex = Math.abs(key.hashCode()) % numBuckets;

		// Check if there is an entry with the given key. If true, remove the
		//entry, reduce the HashMap's size, and return the value. If not,
		//return null.
		for (MyEntry e : buckets.get(bucketIndex).bucket )
			if ( e.key.equals(key) ) {
				V oldValue = e.value;
				buckets.get(bucketIndex).bucket.remove(e);
				size--;
				return oldValue;
			}
		return null;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public List<? extends MyHashMap.Bucket<K, V>> getBuckets() {
		return this.buckets;
	}

	/**
	 * Entry implementation.
	 * This is the key-value pair used for the HashMap.
	 * @author Razvan Cojocaru
	 */
	public class MyEntry implements MyHashMap.Entry<K, V> {

		private K key;
		private V value;
		
		/**
		 * Constructor that sets the key and value of the entry.
		 * @param key		K type key
		 * @param value		V type value
		 */
		public MyEntry(K key, V value) {
			this.key = key;
			this.value = value;
		}
		
		@Override
		public K getKey() {
			return this.key;
		}

		@Override
		public V getValue() {
			return this.value;
		}
	}
	
	/**
	 * Bucket implementation.
	 * This is an array used to store entries for the HashMap.
	 * Each bucket contains entries with the same bucketIndex.
	 * @author Razvan Cojocaru
	 *
	 */
	public class MyBucket implements MyHashMap.Bucket<K, V> {
		
		/**
		 * ArrayList that stores the entries in the bucket. 
		 */
		private ArrayList< MyEntry > bucket;
		
		/**
		 * Constructor that initializes the bucket array.
		 */
		public MyBucket() {
			this.bucket = new ArrayList< MyEntry >();
		}

		@Override
		public List<? extends MyHashMap.Entry<K, V>> getEntries() {
			return this.bucket;
		}
		
		
	}
	
}
