package com.gmail.kkleidal.runningcoach.data;

import java.util.Comparator;

public class RunDistance implements MultAdd<RunDistance> {
	/**
	 * Only data member stored:  number of meters as a double
	 */
	private double _m;
	
	// Pre-defined units (in meters per _____):
	/**
	 * Meters per meter
	 */
	public static double U_METERS = 1.0;
	/**
	 * Meters per kilometer
	 */
	public static double U_KILOMETERS = 1000.0;
	/**
	 * Meters per mile
	 */
	public static double U_MILES = 1609.34;
	/**
	 * Meters per yard
	 */
	public static double U_YARDS = U_MILES / 1760.0;
	/**
	 * Meters in a marathon
	 */
	public static double U_MARATHON = 42194.988;
	
	// Constructors:
	/**
	 * Creates a new RunDistance given an input distance in meters
	 * @param meters the input distance in meters as a double 
	 */
	public RunDistance( double meters ) {
		_m = meters;
	}
	/**
	 * Creates a new RunDistance by copying another RunDistance object
	 * @param rd the RunDistance object to copy
	 */
	public RunDistance( RunDistance rd ) {
		_m = rd._m;
	}
	/**
	 * Creates a new RunDistance object given an input distance in the given units
	 * @param distance the input distance
	 * @param units the units of the input distance (in meters per unit)
	 */
	public RunDistance( double distance, double units ) {
		_m = distance * units;
	}
	
	// Get
	/**
	 * Gets the value of the RunDistance object (the distance in meters)
	 * @return the distance in meters
	 */
	public double getValue() {
		return _m;
	}
	/**
	 * Returns the length of the RunDistance object in the given units
	 * @param units the units of the returned distance (in meters per unit)
	 * @return the distance of the RunDistance in the given units
	 */
	public double inUnit( double units ) {
		return _m / units;
	}
	
	// Operations:
	/**
	 * Adds this RunDistance object with another and returns a new RunDistance as their sum
	 * @param r1 the other addend
	 * @return the sum of the two objects as a new RunDistance object
	 */
	public RunDistance add( RunDistance r1) {
		return new RunDistance( this._m + r1._m );
	}
	/**
	 * Multiplies this RunDistance object by a scalar and returns a new RunDistance representing the product
	 * @param scalar the scalar as a double
	 * @return the product of the RunDistance object and the scalar as a new RunDistance object
	 */
	public RunDistance multiply( double scalar) {
		return new RunDistance( this._m * scalar );
	}
	/**
	 * Subtracts one RunDistance object from another and returns the difference
	 * @param orig the original RunDistance
	 * @param minus the RunDistance to be subtracted from the original
	 * @return the difference as a new RunDistance object. **NOTE:  will be negative if minus > orig
	 */
	public static RunDistance subtract( RunDistance orig, RunDistance minus ) {
		return new RunDistance( orig._m - minus._m );
	}
	
	// Comparators:
	public static class Comparator_GreaterDistance implements Comparator<RunDistance> {
		@Override
		public int compare( RunDistance arg0, RunDistance arg1 ) {
			// TODO Auto-generated method stub
			if ( arg0._m > arg1._m ) {
				return 1;
			} else if ( arg0._m < arg1._m ) {
				return -1;
			}
			return 0;
		}	
	}
}