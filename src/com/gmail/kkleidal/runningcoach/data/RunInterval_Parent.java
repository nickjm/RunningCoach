package com.gmail.kkleidal.runningcoach.data;

/**
 * A class representing an interval with an associated distance and time.
 * Only contains data.  No methods for manipulation.  This is to allow subclasses
 * to extend interfaces associated with that particular subclass.
 * @author kkleidal
 *
 */
abstract class RunInterval_Parent {
	/**
	 * The time for the interval
	 */
	RunTime _time;
	/**
	 * The distance for the interval
	 */
	RunDistance _distance;
	
	/**
	 * Creates a new RunInterval_Parent by copying another
	 * @param other the RunInterval_Parent to be copied
	 */
	public RunInterval_Parent( RunInterval_Parent other ) {
		_time = other._time;
		_distance = other._distance;
	}
	/**
	 * Creates a new RunInterval_Parent with the provided RunTime and RunDistance
	 * @param time the RunTime of the new RunInterval_Parent
	 * @param distance the RunDistance of the new RunInterval_Parent
	 */
	public RunInterval_Parent( RunTime time, RunDistance distance ) {
		_time = time;
		_distance = distance;
	}
}
