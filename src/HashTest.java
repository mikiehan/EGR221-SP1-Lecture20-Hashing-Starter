// Program that compares several structures for a large number of
// calls on these three operations: add, remove, contains.  It expects
// the name of a file as a command-line argument, as in:
//
//     java HashTest moby.txt
//
// It has several tests that it runs:
//
//     test1: using a sorted ArrayList
//     test2: using Java's TreeSet (binary search tree)
//     test3: using Java's HashSet
//     test4: using our HashSet

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class HashTest {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner console = new Scanner(System.in);
        System.out.print("input file? ");
        Scanner input = new Scanner(new File(console.nextLine()));
        System.out.println("Which test? (1=ArrayList,  2=TreeSet,");
        System.out.println("             3=Java's HashSet, 4=our HashSet DOUBLE, ");
        System.out.print("             5=our HashSet QUAD, 6=our Chaining HashSet)? ");
        int which = console.nextInt();

        List<String> data = new ArrayList<String>();
        while (input.hasNext()) {
            data.add(input.next());
        }
        int max = data.size() / 10;  // somewhat arbitrary formula for max
        System.out.println("Total words = " + data.size());
        System.out.println("max         = " + max);
        if (which == 1) {
            test1(data, max);
        } else if (which == 2) {
            test2(data, max);
        } else if (which == 3) {
            test3(data, max);
        } else if (which == 4) {
            test4(data, max);
        } else if (which == 5) {
            test5(data, max);
        } else if (which == 6) {
            test6(data, max);
        } else {
            System.out.println("illegal test number");
        }
    }

    // run a test for an unsorted ArrayList
    public static void test1(List<String> data, int max) {
        long start = System.currentTimeMillis();
        List<String> lst = new ArrayList<String>(max);
        for (int i = 0; i < data.size(); i++) {
            if (i % 5 == 0) {
                lst.remove(data.get(i));
            } else if (i % 5 == 1) {
                lst.contains(data.get(i));
            } else if (!lst.contains(data.get(i))) {
                lst.add(data.get(i));
            }
        }
        System.out.println("list size after = " + lst.size());
        double elapsed = (System.currentTimeMillis() - start) / 1000.0;
        System.out.println("ArrayList time = " + elapsed);
    }

    // run a test for a TreeSet (binary search tree)
    public static void test2(List<String> data, int max) {
        long start = System.currentTimeMillis();
        // no TreeSet constructor that takes a size, so max not used
        TreeSet<String> s = new TreeSet<String>();
        for (int i = 0; i < data.size(); i++) {
            if (i % 5 == 0) {
                s.remove(data.get(i));
            } else if (i % 5 == 1) {
                s.contains(data.get(i));
            } else {
                s.add(data.get(i));
            }
        }
        System.out.println("list size after = " + s.size());
        double elapsed = (System.currentTimeMillis() - start) / 1000.0;
        System.out.println("TreeSet time = " + elapsed);
    }

    // run a test for Java's implementation of HashSet
    public static void test3(List<String> data, int max) {
        long start = System.currentTimeMillis();
        HashSet<String> s = new HashSet<String>(max);
        for (int i = 0; i < data.size(); i++) {
            if (i % 5 == 0) {
                s.remove(data.get(i));
            } else if (i % 5 == 1) {
                s.contains(data.get(i));
            } else {
                s.add(data.get(i));
            }
        }
        System.out.println("list size after = " + s.size());
        double elapsed = (System.currentTimeMillis() - start) / 1000.0;
        System.out.println("Java's HashSet time = " + elapsed);
    }

    // run a test for our implementation of HashSet
    public static void test4(List<String> data, int max) {
        long start = System.currentTimeMillis();
        ProbingHashSet<String> s = new ProbingHashSet<>(max, ProbeMode.DOUBLE);
        for (int i = 0; i < data.size(); i++) {
            if (i % 5 == 0) {
                s.remove(data.get(i));
            } else if (i % 5 == 1) {
                s.contains(data.get(i));
            } else {
                s.insert(data.get(i));
            }
        }
        System.out.println("list size after = " + s.size());
        double elapsed = (System.currentTimeMillis() - start) / 1000.0;
        System.out.println("our DOUBLE ProbingHashSet time = " + elapsed);
    }

    // run a test for our implementation of HashSet
    public static void test5(List<String> data, int max) {
        long start = System.currentTimeMillis();
        ProbingHashSet<String> s = new ProbingHashSet<>(max, ProbeMode.QUADRATIC);
        for (int i = 0; i < data.size(); i++) {
            if (i % 5 == 0) {
                s.remove(data.get(i));
            } else if (i % 5 == 1) {
                s.contains(data.get(i));
            } else {
                s.insert(data.get(i));
            }
        }
        System.out.println("list size after = " + s.size());
        double elapsed = (System.currentTimeMillis() - start) / 1000.0;
        System.out.println("our QUAD ProbingHashSet time = " + elapsed);
    }

    // run a test for our implementation of HashSet
    public static void test6(List<String> data, int max) {
        long start = System.currentTimeMillis();
        SeparateChainingHashSet<String> s = new SeparateChainingHashSet<>(max);
        for (int i = 0; i < data.size(); i++) {
            if (i % 5 == 0) {
                s.remove(data.get(i));
            } else if (i % 5 == 1) {
                s.contains(data.get(i));
            } else {
                s.insert(data.get(i));
            }
        }
        System.out.println("list size after = " + s.size());
        double elapsed = (System.currentTimeMillis() - start) / 1000.0;
        System.out.println("our SeparateChainingHashSet time = " + elapsed);
    }
}

