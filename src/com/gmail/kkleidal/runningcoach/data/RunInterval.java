package com.gmail.kkleidal.runningcoach.data;

/**
 * A class representing an interval with an associated distance and time
 * @author kkleidal
 *
 */
public class RunInterval extends RunInterval_Parent implements MultAdd<RunInterval> {
	
	/**
	 * Creates a new RunInterval by copying another
	 * @param other the RunInterval to be copied
	 */
	public RunInterval( RunInterval other ) {
		super( other );
	}
	/**
	 * Creates a new RunInterval with the provided RunTime and RunDistance
	 * @param time the RunTime of the new RunInterval
	 * @param distance the RunDistance of the new RunInterval
	 */
	public RunInterval( RunTime time, RunDistance distance ) {
		super( time, distance );
	}
	
	/**
	 * Creates a new RunInterval at the given speed for the given distance
	 * @param speed the speed
	 * @param distance the distance
	 */
	public RunInterval( RunSpeed speed, RunDistance distance ) {
		super( new RunTime( 1.0 / ( speed.inUnits( RunDistance.U_METERS, RunTime.U_MINUTES ) ) * distance.inUnit( RunDistance.U_METERS ), RunTime.U_MINUTES ), 
				distance);
	}
	
	/**
	 * Creates a new RunInterval at the given speed for the given time
	 * @param speed the speed
	 * @param time the time
	 */
	public RunInterval( RunSpeed speed, RunTime time ) {
		super( time,
				new RunDistance( speed.inUnits( RunDistance.U_METERS, RunTime.U_MINUTES ) * time.inUnit( RunTime.U_MINUTES ), RunDistance.U_METERS ));
	}
	
	/**
	 * Returns the time of the RunInterval
	 * @return the time of the RunInterval as a RunTime
	 */
	public RunTime getTime() {
		return _time;
	}
	/**
	 * Returns the distance of the RunInterval
	 * @return the distance of the RunInterval as a RunDistance
	 */
	public RunDistance getDistance() {
		return _distance;
	}
	
	public RunSpeed getSpeed() {
		return (RunSpeed)( (RunInterval_Parent) this );
	}
	public RunPace getPace() {
		return (RunPace)( (RunInterval_Parent) this );
	}
	
	// Operations:
	
	/**
	 * Adds this RunInterval with another (by adding their times and distances) and returns a new RunInterval
	 * @param r1 the other addend RunInterval
	 * @return a new RunInterval representing the sum of the two RunIntervals
	 */
	public RunInterval add( RunInterval r1 ) {
		return new RunInterval( this._time.add( r1._time), 
				this._distance.add( r1._distance ) );
	}
	/**
	 * Multiplies the time and distance of this RunInterval by a scalar.
	 *   (for example, if you had an interval, fiveMile, representing a 5 minute mile
	 *   and you wanted to get a new interval representing a 10 minute 2 mile, you could
	 *   call fiveMile.multiply( 2.0 );     )
	 * @param scalar the scalar to multiply the RunInterval by
	 * @return a new RunInterval representing the product
	 */
	public RunInterval multiply( double scalar ) {
		return new RunInterval( this._time.multiply( scalar ), 
				this._distance.multiply( scalar ) );
	}

}
