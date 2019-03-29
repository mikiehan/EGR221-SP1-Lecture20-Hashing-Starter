//ChainingHashSet where each element in the hash table is a linked list
//This does NOT use any Java Collection
public class ChainingHashSet<E> {
    private static final double LOAD_FACTOR_THRESHOLD = 0.75;
    private HashNode<E>[] array;
    private int size;

    public ChainingHashSet(int capacity) {
        //Implement me
    }

    public void add(E value) {
        //Implement me
    }

    private void rehash(){
        HashNode<E>[ ] oldArray = array;

        // Create a new double-sized, empty table
        array = new HashNode[Utils.nextPrime(2 * oldArray.length)];
        size = 0;

        // Copy table over
        //Implement me
    }

    public boolean contains(E value) {
        //Implement me
        return false; //overwrite this code
    }

    public void remove(E value) {
        //Implement me
    }

    public int size() {
        return size;
    }

    private int findPos(E value) {
        //Implement me
        return -1; //Overwrite this code
    }

    /**
     * Make the hash table logically empty.
     */
    public void makeEmpty() {
        //Implement this method
    }

    private static class HashNode<E> {
        //Implement me
    }
}
