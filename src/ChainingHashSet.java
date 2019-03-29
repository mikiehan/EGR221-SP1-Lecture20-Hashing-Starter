// Quick and dirty implementation of ChainingHashSet.

public class ChainingHashSet<E> {
    private static final double LOAD_FACTOR_THRESHOLD = 0.75;
    private HashNode<E>[] array;
    private int size;

    public ChainingHashSet(int capacity) {
        size = 0;
        array = new HashNode[Utils.nextPrime(capacity)];
    }

    public void add(E value) {
        if (!contains(value)) {
            int i = findPos(value);
            array[i] = new HashNode(value, array[i]);
            size++;
        }
        if(size > array.length * LOAD_FACTOR_THRESHOLD){
            rehash();
        }
    }

    private void rehash(){
        HashNode<E>[ ] oldArray = array;

        // Create a new double-sized, empty table
        array = new HashNode[Utils.nextPrime(2 * oldArray.length)];
        size = 0;

        // Copy table over
        for( HashNode<E> entry : oldArray ) {
            while(entry != null){
                this.add(entry.element);
                entry = entry.next;
            }
        }
    }

    public boolean contains(E value) {
        int i = findPos(value);
        HashNode<E> current = array[i];
        while (current != null) {
            if (current.element.equals(value)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public void remove(E value) {
        if (contains(value)) {
            int i = findPos(value);
            if (array[i].element.equals(value)) {
                array[i] = array[i].next; //updating the first
            } else {
                HashNode current = array[i];
                while (!current.next.element.equals(value)) {
                    current = current.next;
                }
                current.next = current.next.next;
            }
            size--;
        }
    }

    public int size() {
        return size;
    }

    private int findPos(E value) {
        int pos = value.hashCode() % array.length;
        if(pos < 0)
            pos += array.length;
        return pos;
    }

    private static class HashNode<E> {
        public E element;
        public HashNode next;

        public HashNode(E element){
            this(element, null);
        }

        public HashNode(E element, HashNode next) {
            this.element = element;
            this.next = next;
        }
    }
}
