
// Probing Hash Set class
// Depending on the mode, it implements LINEAR, DOUBLE, and QUADRATIC Probing resolution
//
// CONSTRUCTION: an approximate initial size or default of 101
//
// ******************PUBLIC OPERATIONS*********************
// bool insert( x )       --> Insert x
// bool remove( x )       --> Remove x
// bool contains( x )     --> Return true if x is present
// void makeEmpty( )      --> Remove all items


/**
 * Probing table implementation of hash set
 * Note that all "matching" is based on the equals method.
 * @author Mark Allen Weiss, Mi Kyung Han
 */
public class ProbingHashSet<E>
{
    private static final int DEFAULT_TABLE_SIZE = 101;
    private static final double LOAD_FACTOR = .5;
    private HashEntry<E> [ ] array; // The array of elements
    private int size;                  // Current size (how many elements are occupying the array)
    private ProbeMode mode;            // Probing mode
    private int prevPrime;             // Used for double probing. Stores the prime smaller than current length of array

    /**
     * Construct the hash table.
     */
    public ProbingHashSet(){
        this(ProbeMode.QUADRATIC);
    }

    public ProbingHashSet(ProbeMode mode){
        this(DEFAULT_TABLE_SIZE, mode);
    }

    public ProbingHashSet(int size)
    {
        this( DEFAULT_TABLE_SIZE, ProbeMode.QUADRATIC);
    }

    /**
     * Construct the hash table.
     * @param size the approximate initial size.
     */
    public ProbingHashSet(int size , ProbeMode mode) {
        allocateArray( size );
        makeEmpty();
        this.mode = mode;
    }

    /**
     * Internal method to allocate array.
     * @param arraySize the size of the array.
     */
    private void allocateArray( int arraySize ) {
        //Implement your code here
        //Initialize allocate array and initialize prevPrime
    }

    /**
     * Make the hash table logically empty.
     */
    public void makeEmpty() {
        //Implement this method
    }

    /**
     * Find an item in the hash table.
     * @param x the item to search for.
     * @return the matching item.
     */
    public boolean contains( E x ) {
        //Implement this method
        return false; //Overwrite this method
    }

    /**
     * Return true if currentPos exists and is active.
     * @param currentPos the result of a call to findPos.
     * @return true if currentPos is active.
     */
    private boolean isActive( int currentPos ) {
        //Implement this method
        return false;
    }

    /**
     * Method that performs probing resolution based on current probe mode.
     * @param x the item to search for.
     * @return the position where the search terminates.
     */
    private int findPos( E x ) {
        //Implement this method
        return -1; //overwrite this method
    }

    //a hash function that returns the "preferred" index given x
    private int myhash( E x ) {
        //Implement this method
        return -1;
    }

    //Used only for Linear Probing
    //Another hash function that returns the "preferred" index given x
    private int myhash2(E x) {
        //Implement this method
        return -1;
    }

    /**
     * Insert into the hash table. If the item is
     * already present, do nothing.
     * @param x the item to insert.
     * @return false if x already exists in the set, true if we inserted x successfully
     */
    public boolean insert( E x ) {
        //Implement your method
        return false; //Overwrite this code
    }

    /**
     * Expand the hash table.
     */
    private void rehash( ) {
        //Implement this method
    }

    /**
     * Remove from the hash table.
     * @param x the item to remove.
     * @return true if x is removed, false if x was not found
     */
    public boolean remove( E x ) {
        //Implement this method
        return false; //Overwrite this code
    }

    /**
     * Get current size.
     * @return the size.
     */
    public int size( )
    {
        return size;
    }
    
    /**
     * Get length of internal table.
     * @return the size.
     */
    public int capacity( )
    {
        return array.length;
    }

    private static class HashEntry<E> {
        //Implement this class
    }

    // Simple main
    public static void main( String [ ] args ) {
        for(ProbeMode m : ProbeMode.values()) {
            ProbingHashSet<String> H = new ProbingHashSet<>(m);

            long startTime = System.currentTimeMillis();

            final int NUMS = 2000000;
            final int GAP = 37;

            System.out.println("Checking " + m + " Mode ... (no \"OOPS\" or \"fails\" message means success)");


            for (int i = GAP; i != 0; i = (i + GAP) % NUMS)
                H.insert("" + i);
            for (int i = GAP; i != 0; i = (i + GAP) % NUMS)
                if (H.insert("" + i))
                    System.out.println("OOPS!!! " + i);
            for (int i = 1; i < NUMS; i += 2)
                H.remove("" + i);

            for (int i = 2; i < NUMS; i += 2)
                if (!H.contains("" + i))
                    System.out.println("Find fails " + i);

            for (int i = 1; i < NUMS; i += 2) {
                if (H.contains("" + i))
                    System.out.println("OOPS!!! " + i);
            }

            long endTime = System.currentTimeMillis();

            System.out.println("Elapsed time: " + (endTime - startTime));
            System.out.println("H size is: " + H.size());
            System.out.println("Array size is: " + H.capacity());
        }
    }
}
