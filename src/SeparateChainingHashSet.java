import java.util.LinkedList;
import java.util.List;

// SeparateChaining Hash table class
//
// CONSTRUCTION: an approximate initial size or default of 101
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// boolean contains( x )  --> Return true if x is present
// void makeEmpty( )      --> Remove all items

/**
 * Separate chaining table implementation of hash tables.
 * Note that all "matching" is based on the equals method.
 * @author Mark Allen Weiss
 */
public class SeparateChainingHashSet<E>
{
    private static final int DEFAULT_TABLE_SIZE = 101;
    private static final double LOAD_FACTOR = .5;

    /** The array of Lists. */
    private List<E> [ ] lists;
    private int size;

    /**
     * Construct the hash table.
     */
    public SeparateChainingHashSet( )
    {
        this( DEFAULT_TABLE_SIZE );
    }

    /**
     * Construct the hash table.
     * @param size approximate table size.
     */
    public SeparateChainingHashSet(int size )
    {
        lists = new LinkedList[ Utils.nextPrime( size ) ];
        for(int i = 0; i < lists.length; i++ )
            lists[ i ] = new LinkedList<>( );
    }

    /**
     * Insert into the hash table. If the item is
     * already present, then do nothing.
     * @param x the item to insert.
     */
    public void insert( E x )
    {
        List<E> whichList = lists[ myhash( x ) ];
        if( !whichList.contains( x ) )
        {
            whichList.add( x );

                // Rehash; see Section 5.5
            if( ++size > lists.length * LOAD_FACTOR )
                rehash( );
        }
    }

    /**
     * Remove from the hash table.
     * @param x the item to remove.
     */
    public void remove( E x )
    {
        List<E> whichList = lists[ myhash( x ) ];
        if( whichList.contains( x ) ){
            whichList.remove( x );
                size--;
        }
    }

    /**
     * Find an item in the hash table.
     * @param x the item to search for.
     * @return true if x isnot found.
     */
    public boolean contains( E x )
    {
        List<E> whichList = lists[ myhash( x ) ];
        return whichList.contains( x );
    }

    /**
     * Make the hash table logically empty.
     */
    public void makeEmpty( )
    {
        for(int i = 0; i < lists.length; i++ )
            lists[ i ].clear( );
        size = 0;
    }

    /**
     * A hash routine for String objects.
     * @param key the String to hash.
     * @param tableSize the size of the hash table.
     * @return the hash value.
     */
    public static int hash( String key, int tableSize )
    {
        int hashVal = 0;

        for( int i = 0; i < key.length( ); i++ )
            hashVal = 37 * hashVal + key.charAt( i );

        hashVal %= tableSize;
        if( hashVal < 0 )
            hashVal += tableSize;

        return hashVal;
    }

    private void rehash( )
    {
        List<E> [ ]  oldLists = lists;

            // Create new double-sized, empty table
        lists = new List[ Utils.nextPrime( 2 * lists.length ) ];
        for(int j = 0; j < lists.length; j++ )
            lists[ j ] = new LinkedList<>( );

            // Copy table over
        size = 0;
        for( List<E> list : oldLists )
            for( E item : list )
                insert( item );
    }

    private int myhash( E x )
    {
        int hashVal = x.hashCode( );

        hashVal %= lists.length;
        if( hashVal < 0 )
            hashVal += lists.length;

        return hashVal;
    }

    public int size(){
        return size;
    }

    // Simple main
    public static void main( String [ ] args ) {
        SeparateChainingHashSet<Integer> H = new SeparateChainingHashSet<>( );

        long startTime = System.currentTimeMillis( );
        
        final int NUMS = 2000000;
        final int GAP  =   37;

        System.out.println("Checking Separate Chaining Mode ... (no \"OOPS\" or \"fails\" message means success)");

        for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS )
            H.insert( i );
        for( int i = 1; i < NUMS; i+= 2 )
            H.remove( i );

        for( int i = 2; i < NUMS; i+=2 )
            if( !H.contains( i ) )
                System.out.println( "Find fails " + i );

        for( int i = 1; i < NUMS; i+=2 )
        {
            if( H.contains( i ) )
                System.out.println( "OOPS!!! " +  i  );
        }
        
        long endTime = System.currentTimeMillis( );
        
        System.out.println( "Elapsed time: " + (endTime - startTime) );
    }
}

