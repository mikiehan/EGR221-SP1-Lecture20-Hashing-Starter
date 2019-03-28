/**
 * A class for Utility methods
 */
public class Utils {
    /**
     * A utility method to find a prime number at least as large as n.
     * @param n the starting number (must be positive).
     * @return a prime number larger than or equal to n.
     */
    public static int nextPrime( int n )
    {
        if( n % 2 == 0 )
            n++;

        for( ; !isPrime( n ); n += 2 )
            ;

        return n;
    }

    /**
     * A utility method to find a prime number that is LESS than n.
     * @param n the starting number (must be positive).
     * @return a prime number larger than or equal to n.
     */
    public static int prevPrime( int n ) {
        if( n % 2 == 0 )
            n--;
        else
            n -= 2;
        for ( ; !isPrime(n); n -=2 )
            ;
        return n;
    }

    /**
     * A utility method to test if a number is prime.
     * Not an efficient algorithm.
     * @param n the number to test.
     * @return the result of the test.
     */
    public static boolean isPrime( int n )
    {
        if( n == 2 || n == 3 )
            return true;

        if( n == 1 || n % 2 == 0 )
            return false;

        for( int i = 3; i * i <= n; i += 2 )
            if( n % i == 0 )
                return false;

        return true;
    }
}
