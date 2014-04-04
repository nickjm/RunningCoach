package com.gmail.kkleidal.runningcoach.data;

/**
 * A class to represent Speed.  Subclass of RunInterval.  Handles the associated distance and time of the RunInterval behind the scenes.
 * @author kkleidal
 *
 */
public class RunSpeed extends RunInterval_Parent implements MultAdd<RunSpeed> {
	
	/**
	 * Copy RunSpeed object
	 * @param other object to be copied
	 */
	public RunSpeed( RunSpeed other ) {
		super( other );
	}
	/**
	 * Creates a new RunSpeed based on a speed in the given units
	 * @param speed the value of the speed in distanceUnits per timeUnits as a double
	 * @param distanceUnits a double representing the distance units in meters per distance unit
	 * @param timeUnits a long representing the time units in milliseconds per time unit
	 */
	public RunSpeed( double speed, double distanceUnits, long timeUnits ) {
		super( new RunTime( 1.0, timeUnits ), 
				new RunDistance( speed, distanceUnits ) );
	}
	
	/**
	 * Creates a new RunSpeed from a value representing speed in meters per minute
	 * @param speedMMin a double representing the speed in meters per minute
	 */
	public RunSpeed( double speedMMin ) {
		this( speedMMin, RunDistance.U_METERS, RunTime.U_MINUTES );
	}
	
	/**
	 * Returns the value of the speed in the given units
	 * @param distanceUnits the distance units in meters per distance unit
	 * @param timeUnits the time units in milliseconds per time unit
	 * @return the value of the speed in the given units
	 */
	public double inUnits( double distanceUnits, long timeUnits ) {
		return this._distance.inUnit( distanceUnits ) / this._time.inUnit( timeUnits );
	}
	
	public RunPace getPace() {
		return (RunPace)( (RunInterval_Parent) this );
	}
	
	// Operations:
	
	/**
	 * Multiplies this speed by a scalar and returns a new RunSpeed representing the product
	 * @param scalar the scalar by which to multiply the speed
	 * @return the product as a new RunSpeed
	 */
	@Override
	public RunSpeed multiply( double scalar ) {
		// return (RunSpeed)( new RunInterval( rs._time, RunDistance.multiply( rs._distance, scalar ) ) );
		return new RunSpeed( this.inUnits( RunDistance.U_METERS, RunTime.U_MINUTES ) * scalar );
	}
	/**
	 * adds this RunSpeed with another and returns a new RunSpeed representing the sum
	 * @param rs1 the other addend
	 * @return the sum of this RunSpeed and rs1 as a new RunSpeed
	 */
	public RunSpeed add( RunSpeed rs1 ) {
		double mmin1 = this.inUnits( RunDistance.U_METERS, RunTime.U_MINUTES );
		double mmin2 = rs1.inUnits( RunDistance.U_METERS, RunTime.U_MINUTES );
		return new RunSpeed( mmin1 + mmin2 );
	}
}