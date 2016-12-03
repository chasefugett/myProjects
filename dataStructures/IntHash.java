package data_structures;

import java.util.ArrayList;
import java.util.Collections;
import java.lang.IndexOutOfBoundsException;
import java.lang.IllegalArgumentException;

/**
 * @author Chase Fugett
 * 
 * A simple hash function that extends dictionary using Integers as keys 
 * and generic value_type as values
 */
public class IntHash<value_type> extends Dictionary<Integer, value_type> {
	int a;
	int b;
	// Length of the list
	int m;
	// The number of data values plus del values in list
	int q;
	// Integer that represents that a key has been deleted
	final Integer del = -1;
	// The list where the pair is stored
	ArrayList<Pair<Integer,value_type>> table;
	
	/**
	 * Hashing function
	 * @param key
	 * @return hash value
	 */
	private int hash(Integer key) {
		return (((int) key * a) + b) % m;
	}
	
	/**
	 * Default constructor
	 */
	public IntHash() {
		this(7, 1, 25014);
	}
	
	/**
	 * Constructor -- hash values specified.
	 */
	public IntHash(int a, int b, int m) {
		super();
		this.a = a;
		this.b = b;
		this.m = m;
		table = new ArrayList<Pair<Integer,value_type>>(Collections.nCopies(m, null));
		for (int i = 0; i < m; i++) {
			table.add(i, new Pair<Integer,value_type>(null,null));
		}
	}

	/**
	 * Insert a value/key pair into the dictionary.  Do not allow duplicate
	 * or null key.
	 * @param key        key to be inserted
	 * @param value      value to be inserted
	 * @exception   Throw IndexOutOfBoundsException if key already present.
	 * @exception   Throw IllegalArgumentException if value is null.
	 * @exception   Throw IllegalArgumentException if key < 0.  (Makes life easier.)
	 */
	@Override
	public void insert(Integer key, value_type value) {
		if (value == null)
			throw new IllegalArgumentException("Null values not allowed");
		if (n == m)
			throw new ArrayIndexOutOfBoundsException("Duplicate keys not allowed");
		if (key < 0)
			throw new IllegalArgumentException("Negative keys not allowed");
		if (find(key) != null) {
			throw new IndexOutOfBoundsException();
		}
		
        int i = hash(key);
        while (table.get(i).second != null && !table.get(i).first.equals(del)) {
            i = (i == (table.size()/2) - 1) ? 0 : i + 1;
        }
        if (table.get(i).second == null) {
        	q++;
        }
        n++;
        table.get(i).first = key;
        table.get(i).second = value;
	}

	/**
	 * Takes in a key, and removes it from the hash function
	 * @param Integer
	 */
	@Override
	public void remove(Integer key) {
		int i = hash(key);
		while (table.get(i).first != null) {
            Integer y = table.get(i).first;
            if (!(y.equals(del)) && key.equals(y)) {
            	table.get(i).first = del;
                n--;
            }
            i = (i == (table.size()/2) - 1) ? 0 : i + 1;
		}
	}
	
	/**
	 * Clears the hash function
	 */
	@Override
	public void clear() {
		for (int i = 0; i < m; i++) {
			table.get(i).first = null;
			table.get(i).second = null;
		}
		n = 0;
	}

	/**
	 * Given a key, return the value
	 * @param Integer
	 * @return value_type
	 */
	@Override
	public value_type find(Integer key) {
		int i = hash(key);
        while (table.get(i).first != null) {
            if (!(table.get(i).first.equals(del)) && key.equals(table.get(i).first)) {
            	return table.get(i).second;
            }
            i = (i == (table.size()/2) - 1) ? 0 : i + 1;
        }
        return null;
	}

	/*
	 * This is not useful for this class -- always pass it.
	 */
	@Override
	public boolean check_structure() {
		return true;
	}

	@Override
	public void print_structure() {
		for (int i=0; i < m; i++) {
			Pair<Integer,value_type> p = table.get(i);
			if (p != null && p.first >= 0)
				System.out.println("k, h(k), v = " + p.first + " " + i + " " + p.second);
		}
	}

}
