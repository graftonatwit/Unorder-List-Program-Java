/* @formatter:off
 *
 * Dave Rosenberg
 * Comp 2000 - Data Structures
 * Lab: Bag ADT
 * Spring, 2024
 * 
 * Usage restrictions:
 * 
 * You may use this code for exploration, experimentation, and furthering your
 * learning for this course. You may not use this code for any other
 * assignments, in my course or elsewhere, without explicit permission, in
 * advance, from myself (and the instructor of any other course).
 * 
 * Further, you may not post (including in a public repository such as on github)
 * nor otherwise share this code with anyone other than current students in my 
 * sections of this course. Violation of these usage restrictions will be considered 
 * a violation of the Wentworth Institute of Technology Academic Honesty Policy.
 *
 * Do not remove this notice.
 *
 * @formatter:on
 */


/* @formatter:off
 *
 * You must:
 *  - replace "Your Name" with your actual name in the class Javadoc comment block
 *  - once you complete each task, delete the entire "// TODO..." comment
 *  - follow my coding style
 *      - use my configuration settings (see Resources in Brightspace)
 *          - use my provided code as a guide/template - maintain this style
 *      - see Appendix A in the textbook for the basics
 *      - qualify all instance variable references with 'this.'
 *          - for example, this.numberOfEntries rather than numberOfEntries
 *          - I don't typically qualify instance method invocations with 'this.'
 *              - exception: I'm manipulating two or more instances in a method
 *      - braces are required for single-line statements
 *          - for example:
 *              if ( condition )
 *                  {
 *                  do something ;
 *                  }
 *          - use next-line, indented brace position (as above)
 *      - fully spell out your variable, parameter, method, class names
 *
 * You may not use any Java Class Library (JCL) classes or methods other than:
 *      StringBuilder - in toString() to construct the output String
 *          - you may construct and use Strings for output but you aren't
 *              permitted to use any String methods
 *      Arrays - you need to instantiate and manipulate arrays in toArray() and
 *               add(T[]) but you aren't permitted to use any Array/Arrays methods
 *      print(), etc. - for debugging only
 *
 * You should:
 *  - find the appropriate code in the textbook or in the code I provided for
 *      lectures (links in the Weekly Plans) and copy it (do not attribute)
 *  - the array constructor, cloning constructor, difference(), intersection(),
 *      union(), initializeState(), and add(T[]) are not in the book - they are
 *      described in the assignment and the Javadoc comments
 *
 * @formatter:on
 */

package edu.wit.scds.ds.lists.adt.unordered.linked ;

import edu.wit.scds.dmr.testing.framework.StubMethodException ;
import edu.wit.scds.dmr.tests.ArrayBag ;
import edu.wit.scds.ds.common.enhanced.Node ;
import edu.wit.scds.ds.lists.UnorderedListInterface ;

import java.util.Arrays ;

/**
 * A class of bags whose entries are stored in a chain of linked nodes. The bag is never full.
 * <p>
 * Note: This implementation does not permit {@code null} entries.
 *
 * @author Frank M. Carrano
 * @author Timothy M. Henry
 *
 * @version 4.1
 *
 * @author David M Rosenberg
 *
 * @version 4.2 2016-01-10 Reformat and revise
 * @version 4.3 2019-05-12 Add 'cloning' constructor, {@code difference()},
 *     {@code intersection()}, {@code union()}
 * @version 4.4 2019-05-24 standardize on {@code resultBag} for returned construct variable name
 *     for {@code difference()}, {@code intersection()}, {@code union()}
 * @version 4.5 2020-01-28
 *     <ul>
 *     <li>add constructor taking array of entries
 *     <li>move {@code Node} to a separate class
 *     </ul>
 * @version 4.6 2020-05-16 add check for null argument to difference()
 * @version 4.7 2020-08-08 track changes to BagInterface - now extend EnhancedBagInterface instead
 * @version 4.8 2020-09-13
 *     <ul>
 *     <li>track changes to BagInterface.java - switch back to it
 *     <li>copy all Javadoc comments from BagInterface.java to here instead of referencing them
 *     <li>restructured some methods to return as soon as possible
 *     </ul>
 * @version 4.9 2022-10-11 full solution
 * @version 4.10 2022-10-07 stub various methods for assignment
 * @version 4.10.1 2023-10-19
 *     <ul>
 *     <li>remove Javadoc comment blocks for methods declared (and documented) in
 *     {@code BagInterface} since they're redundant
 *     <li>add implementations for some methods
 *     </ul>
 * @version 4.11 2024-02-13 implementation per assignment
 * @version 4.12 2024-10-19
 *     <ul>
 *     <li>add implementations per assignment of {@code areDisjointSets()},
 *     {@code isProperSubsetOf()}, and {@code isSubsetOf()}
 *     <li>revise {@code toString()} to include {@code this.numberOfEntries} as a debugging aid
 *     <ul>
 * @version 4.13 2022-10-21 stub various methods for assignment
 * 
 * @author Trevor J Grafton // TODO replace with your actual name (not username)
 *
 * @version 4.14 2024-10-21 implementation per assignment
 *
 * @param <T>
 *     The class of entry the {@code LinkedBag} will hold.
 */
public class LinkedBag<T> implements UnorderedListInterface<T>
    {

    /*
     * data fields/instance variables
     */
    private Node<T> firstNode ;     // reference to first node
    private int numberOfEntries ;   // count of entries in use

    
    /*
     * constructors
     */


    /**
     * Initialize a new, empty LinkedBag
     */
    public LinkedBag()
        {
        // initialize the bag - state is empty
        initializeState() ;

        // Do not make any changes to this constructor - initializeState() will do
        // the setup

        }   // end no-arg constructor


    /**
     * 'Cloning' constructor - initialize a new {@code LinkedBag} and populate it with the contents
     * of {@code sourceBag}
     *
     * @param sourceBag
     *     another bag containing zero or more entries to copy to the newly instantiated
     *     {@code LinkedBag} - if {@code null}, the new {@code LinkedBag} will be empty
     *     <p>
     *     Note: {@code sourceBag} is an instance of any class that implements
     *     {@code UnorderedListInterface} - it doesn't have to be another {@code LinkedBag}
     *     <p>
     *     Post-condition: the contents of {@code sourceBag} are unchanged.
     */
    public LinkedBag( final UnorderedListInterface<T> sourceBag )
        {
        this() ;    
        
        //throw new StubMethodException() ;
        if (sourceBag != null) {
        for (T value : sourceBag.toArray()) {
            add(value);}
        }
        }   // end 1-arg 'cloning' constructor


    /**
     * Initialize a new {@code LinkedBag} and populate it with the contents of
     * {@code initialContents}- if {@code null}, the new {@code LinkedBag} will be empty
     *
     * @param initialContents
     *     an array of zero or more entries to copy to the newly instantiated {@code LinkedBag}
     *     <p>
     *     Post-condition: the contents of {@code initialContents} are unchanged.
     */
    public LinkedBag( final T[] initialContents )
        {
        this() ;    // known valid starting state: empty
        if (initialContents != null) {
        for (T value : initialContents) {
            add(value);
        }
        }
        //throw new StubMethodException() ;   // TODO replace the exception with the
                                            // full implementation of this constructor

        }   // end 1-arg 'array' constructor


    /*
     * API methods
     */


    @Override
    public boolean add( final T newEntry )
        {

        // prevent null entries
        if ( newEntry == null )
            {
            throw new IllegalArgumentException( "entry cannot be null" ) ;
            }

        // create a new Node for this entry pointing it at the current first
        // entry (Node) in the chain - will be null if the bag is empty
        final Node<T> newNode = new Node<>( newEntry, this.firstNode ) ;

        // make it the first entry in the chain
        this.firstNode = newNode ;

        // count this entry
        this.numberOfEntries++ ;

        // always succeeds
        return true ;

        }   // end add()
    
    
    @Override
    public boolean areDisjointSets( final UnorderedListInterface<T> anotherList )
        {

        //throw new StubMethodException() ;   // TODO replace the exception with the
        if (anotherList == null) {
        return true; 
    }
    for (T value : this.toArray()) {
        if (anotherList.contains(value)) {
            return false; 
        }
    }
    return true; 
    }  


    @Override
    public void clear()
        {
        // re-initialize the state to empty
        initializeState() ;

        // Do not make any changes to this method - initializeState() will reset the
        // state to empty with O(1) efficiency.

        }   // end clear()


    @Override
    public boolean contains( final T anEntry )
        {
        // get a reference to the first matching entry - eliminates duplicate code
        return getReferenceTo( anEntry ) != null ;

        }   // end contains()


    @Override
    public UnorderedListInterface<T> difference( final UnorderedListInterface<T> anotherBag )
        {
        
        //throw new StubMethodException() ;   // TODO replace the exception with the
        LinkedBag<T> result = new LinkedBag<>();
        if (anotherBag != null) {
            for (T value : this.toArray()) {
                if (getFrequencyOf(value) > anotherBag.getFrequencyOf(value)) {
                    result.add(value);
                }
            }
        }
        return result;                                   

        }   


    @Override
    public int getCurrentSize()
        {
        // simply return the number of entries
        return this.numberOfEntries ;

        }   // end getCurrentSize()


    @Override
    public int getFrequencyOf( final T anEntry )
        {

        // null can't be present in the bag so simply indicate we didn't find it
        if ( anEntry == null )
            {
            return 0 ;
            }

        int foundCount = 0 ;     // number of matching elements

        // traverse the chain
        Node<T> currentNode = this.firstNode ;

        while ( currentNode != null )
            {

            // check for a match
            if ( currentNode.getData().equals( anEntry ) )
                {
                // we have a match
                foundCount++ ;
                }

            // move to the next node
            currentNode = currentNode.getNext() ;
            }   // end while

        return foundCount ;

        }   // end getFrequencyOf()


    @Override
    public UnorderedListInterface<T> intersection( final UnorderedListInterface<T> anotherBag )
        {
        
        //throw new StubMethodException() ;   // TODO replace the exception with the
        LinkedBag<T> result = new LinkedBag<>();
        if (anotherBag != null) {
            for (T value : this.toArray()) {
                if (anotherBag.contains(value) && result.getFrequencyOf(value) < getFrequencyOf(value)) {
                    result.add(value);
                }
            }
            }
        return result;                               

        }   // end intersection()


    @Override
    public boolean isEmpty()
        {
        // simply check the current number of entries
        return this.numberOfEntries == 0 ;

        }   // end isEmpty()
    
    
    @Override
    public boolean isProperSubsetOf( final UnorderedListInterface<T> anotherList )
        {
        
        //throw new StubMethodException() ;   // TODO replace the exception with the
        
        if (anotherList == null || getCurrentSize() >= anotherList.getCurrentSize()) {
        return false;
    }
    for (T value : this.toArray()) {
        if (!anotherList.contains(value)) {
            return false; 
        }
        }
    return true;                          
        }   // end isProperSubsetOf()
    
    
    @Override
    public boolean isSubsetOf( final UnorderedListInterface<T> anotherList )
        {
        
        //throw new StubMethodException() ;   // TODO replace the exception with the
        if (anotherList == null) {
        return false; 
        }
       for (T value : this.toArray()) {
        if (!anotherList.contains(value)) {
            return false; 
        }
        }
       return true;
        }   // end isSubsetOf()


    @Override
    public T remove()
        {

        // if the bag is empty, indicate that by returning null
        if ( isEmpty() )
            {
            return null ;   // nothing to remove
            }

        // assertion: the bag isn't empty

        // grab the data from the first Node in the chain
        final T anEntry = this.firstNode.getData() ;

        // drop the first node from the chain
        this.firstNode = this.firstNode.getNext() ;

        // decrement the number of entries
        this.numberOfEntries-- ;

        return anEntry ;

        }   // end no-arg remove()


    @Override
    public boolean remove( final T anEntry )
        {
        // get a reference to a node that matches the parameter
        // getReferenceTo() treats null as unfindable so returns null
        final Node<T> foundEntry = this.getReferenceTo( anEntry ) ;

        // if we didn't find a match, we're done
        if ( foundEntry == null )
            {
            return false ;      // indicate no match
            }

        // assertion: the chain can't be empty so there is a first node

        // replace the found entry's node's data with the data from the
        // first node
        foundEntry.setData( this.firstNode.getData() ) ;

        // drop the first node from the chain
        // assertion: unspecified remove() removes the first node
        remove() ;

        // let the caller know we were able to remove the requested entry
        return true ;

        }   // end 1-arg remove()


    @Override
    public T[] toArray()
        {
        // instantiate an array to contain the entries
        @SuppressWarnings( "unchecked" )
        final T[] result = (T[]) new Object[ this.numberOfEntries ] ;

        // traverse the chain copying data references into sequential elements
        // of the array
        int index = 0 ;
        Node<T> currentNode = this.firstNode ;

        while ( currentNode != null )
            {
            result[ index ] = currentNode.getData() ;
            currentNode = currentNode.getNext() ;
            index++ ;
            } // end while

        return result ;

        }   // end toArray()


    @Override
    public String toString()
        {
        // Note: we don't typically implement toString() in a collection - this one is
        // provided as a debugging aid

        /*
         * we'll return a string of comma-separated text representations of each entry and surround
         * them with square brackets
         */

        return String.format( "nOE: %,d; data: %s",
                              this.numberOfEntries,
                              Arrays.toString( toArray() ) ) ;

        }   // end toString()


    @Override
    public UnorderedListInterface<T> union( final UnorderedListInterface<T> anotherBag )
        {
        
        //throw new StubMethodException() ;   // TODO replace the exception with the
        LinkedBag<T> result = new LinkedBag<>();
        for (T value : this.toArray()) {
        result.add(value);
    }
    if (anotherBag != null) {
        for (T value : anotherBag.toArray()) {
            result.add(value);
        }
        }
    return result;                                    

        }   


    // end of LinkedBag API methods


    /*
     * private utility methods
     */


    /**
     * Utility method to load this bag with the provided items
     *
     * @param newEntries
     *     the items to add
     *     <ul>
     *     <li>array will never be {@code null}
     *     <li>{@code add( entry )} will prevent adding {@code null} entries
     *     </ul>
     *     <p>
     *     Pre-condition: newEntries is never {@code null}
     *     <p>
     *     Post-condition: the contents of {@code newEntries} are unchanged
     */
    @SuppressWarnings( "unused" )
    private void add( final T[] newEntries )
        {
        // Note: if newEntries contains any nulls, add() will throw an
        // IllegalArgumentException - do not check for null entries in this method
        
        //throw new StubMethodException() ;   // TODO replace the exception with the
        for (T entry : newEntries) {
        add(entry);
        }
        }   // end add() from array


    /**
     * Find a node in the chain with data that matches the parameter.
     *
     * @param anEntry
     *     the entry to match
     *
     * @return a reference to a node containing a matching entry or {@code null} if not found
     */
    private Node<T> getReferenceTo( final T anEntry )
        {

        // null can't be present in the bag so simply indicate we didn't find it
        if ( anEntry == null )
            {
            return null ;
            }

        // traverse the chain starting with the first node
        Node<T> currentNode = this.firstNode ;

        while ( currentNode != null )
            {

            if ( currentNode.getData().equals( anEntry ) )
                {
                // we found a match so stop looking
                return currentNode ;
                }   // end if

            // didn't find it (yet) so move to the next node
            currentNode = currentNode.getNext() ;
            }   // end while

        // return null to indicate no match
        return null ;

        }   // end getReferenceTo()


    /**
     * Initialize the state of the current {@code LinkedBag} to empty
     */
    private void initializeState()
        {
        // (re)set the state to empty
        this.firstNode = null ;
        this.numberOfEntries = 0 ;

        }   // end initializeState()


    /**
     * (optional) test driver for LinkedBag
     *
     * @param args
     *     -unused-
     */
    public static void main( final String[] args )
        {
        // OPTIONAL for debugging/testing
        UnorderedListInterface<String> list1 = new LinkedBag<>() ;
        printStuff( list1, null ) ;
        
        UnorderedListInterface<String> list2 = new ArrayBag<>() ;
        
        printStuff( list1, list2 ) ;
        
        list1.add( "a" ) ;
        
        printStuff( list1, list2 ) ;
        
        list2.add( "a" ) ;
        
        printStuff( list1, list2 ) ;
        
        list2.add( "b" ) ;
        
        printStuff( list1, list2 ) ;
        
        list1.add( "a" ) ;
        list2.add( "b" ) ;
        
        printStuff( list1, list2 ) ;
        
        list2.add( "a" ) ;
        
        printStuff( list1, list2 ) ;
        
        list1.add( "a" ) ;
        
        printStuff( list1, list2 ) ;
        
        list2.clear() ;
        list2.add( "z" ) ;
        list2.add( "y" ) ;
        list2.add( "x" ) ;
        list2.add( "w" ) ;
        
        printStuff( list1, list2 ) ;

        }   // end main()


    private static void printStuff( final UnorderedListInterface<String> list1,
                                    final UnorderedListInterface<String> list2 )
        {

        System.out.printf( "linked and array-backed lists:%nLB: %s%nAB: %s%n%n",
                           list1,
                           list2 ) ;

        System.out.printf( "1l.aDS(l2): %b%n", list1.areDisjointSets( list2 ) ) ;
        System.out.printf( "l1.iSO(l2): %b%n", list1.isSubsetOf( list2 ) ) ;
        System.out.printf( "l1.iPSO(l2): %b%n%n----------%n%n", list1.isProperSubsetOf( list2 ) ) ;

        }   // end printStuff()

    }   // end class LinkedBag