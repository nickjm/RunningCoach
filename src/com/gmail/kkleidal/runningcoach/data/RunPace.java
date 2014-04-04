package com.gmail.kkleidal.runningcoach.data;

public class RunPace extends RunInterval_Parent {
	
	/**
	 * Copies RunPace object
	 * @param other object to be copied
	 */
	public RunPace( RunPace other ) {
		super( other );
	}
	
	public RunSpeed getSpeed() {
		return (RunSpeed)( (RunInterval_Parent) this );
	}
	
	/**
	 * Returns a RunTime representing the pace per distUnits where distUnits is in meters per unit
	 * @param distUnits the distance unit (ie "mile" in "pace per mile") in meters per unit
	 * @return a RunTime representing the pace
	 */
	public RunTime perUnits( double distUnits ) {
		// Example:  350 meters per minute is the speed.  350m is the distance, 1 minute is the time
		// if we want pace per mile, distUnits will be 1609m.
		// To get the pace we take 1 minute * 1609m / 350m which is about 4:36
		return this._time.multiply( distUnits / this._distance.inUnit( RunDistance.U_METERS ) );
	}

}
