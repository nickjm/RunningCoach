package com.gmail.kkleidal.runningcoach.data;

import java.util.Comparator;

/**
 * Simple class for storing data on lengths of intervals
 * @author kkleidal
 *
 */
public class RunTime implements MultAdd<RunTime> {
	/**
	 * Only member data stored:  number of milliseconds as a long
	 */
	private long _ms = 0L;

	// Pre-defined units:
	/**
	 * Milliseconds per millisecond
	 */
	public static final long U_MILLISECONDS = 1;
	/**
	 * Milliseconds per hundredth second
	 */
	public static final long U_HSECONDS = 10;
	/**
	 * Milliseconds per second
	 */
	public static final long U_SECONDS = 1000;
	/**
	 * Milliseconds per minute
	 */
	public static final long U_MINUTES = U_SECONDS * 60;
	/**
	 * Milliseconds per hour
	 */
	public static final long U_HOURS  = U_MINUTES * 60;
	/**
	 * Milliseconds per day
	 */
	public static final long U_DAYS = U_HOURS * 24;

	// Constructors:
	/**
	 * Creates a new RunTime object from the fundamental data:  time in milliseconds
	 * @param ms the time in milliseconds as a long
	 */
	public RunTime( long ms ) {
		_ms = ms;
	}
	/**
	 * Creates a new RunTime object by copying another RunTime object, other
	 * @param other the object to be copied
	 */
	public RunTime( RunTime other ) {
		_ms = other._ms;
	}
	/**
	 * Creates a new RunTime object from an input value representing the length of the new RunTime
	 * @param value the value of the input time
	 * @param units the units of the input time (in milliseconds per unit)
	 */
	public RunTime( double value, long units ) {
		_ms = (long)( value * units );
	}

	// Get
	/**
	 * Gets the value of the RunTime object in milliseconds
	 * @return the value of the RunTime object in milliseconds
	 */
	public long getValue() {
		return _ms;
	}
	/**
	 * Converts the RunTime object into a time of the given units
	 * @param units the desired units of the output (in milliseconds per unit)
	 * @return a double representing the length of the RunTime in the given units
	 */
	public double inUnit( long units ) {
		return ( (double)_ms ) / ( (double)units );
	}

	// Operations
	/**
	 * Adds this RunTime object to another returns a new RunTime object whose length is the sum
	 * @param t1 the other addend
	 * @return the new RunTime object representing the sum
	 */
	public RunTime add( RunTime t1 ) {
		return new RunTime( this._ms + t1._ms );
	}
	/**
	 * Multiplies this RunTime by a scalar and returns a new RunTime object whose length is the product
	 * @param scalar the scalar as a double
	 * @return the product of this RunTime object and the scalar as a new RunTime object
	 */
	public RunTime multiply( double scalar ) {
		return new RunTime( (long)( this._ms * scalar ) );
	}
	/**
	 * Returns the time difference between two RunTime objects
	 * @param r1 one RunTime object
	 * @param r2 the other RunTime object
	 * @return the difference between the two RunTime objects as a RunTime
	 */
	public static RunTime difference( RunTime r1, RunTime r2 ) {
		return new RunTime( UtilFunctions.abs( r1._ms - r2._ms ) );
	}

	// Comparators:
	public static class Comparator_GreaterTime implements Comparator<RunTime> {
		@Override
		public int compare( RunTime arg0, RunTime arg1 ) {
			// TODO Auto-generated method stub
			if ( arg0._ms > arg1._ms ) {
				return 1;
			} else if ( arg0._ms < arg1._ms ) {
				return -1;
			}
			return 0;
		}	
	}
}
