package com.gmail.kkleidal.runningcoach.data;

final class VO2_Utils {
	static final double C1 = 0.2989558;
	static final double C2 = -0.1932605;
	static final double C3 = 0.1894393;
	static final double C4 = -0.012778;
	static final double C5 = 0.8;
	/**
	 * Calculates the intensity of an all-out interval of the given length of time
	 * @param time the time of the interval
	 * @return the intensity of the interval as a percentage of the VO2 max
	 */
	public static double intensity( RunTime time ) {
		// t is in minutes
		// I left (t right ) s=0.2989558 {e} ^ {-0.1932605t} +0.1894393 {e} ^ {-0.012778t} +0.8

		double t = time.inUnit( RunTime.U_MINUTES );
		return C1 * Math.exp( C2 * t) + C3 * Math.exp( C4 * t ) + C5; 
	}
	static final double C6 = 0.182258;
	static final double C7 = 0.000104;
	/**
	 * Returns
	 * @author kkleidal
	 *
	 */
	static final double C8 = -4.60;
	
	/**
	 * Calculates the oxygen cost of running at a given speed
	 * @param speed the speed
	 * @return the oxygen cost of the interval
	 */
	public static double oxygenCost( RunSpeed speed ) {
		// t is in minutes; d is in meters
		// C left (t,d right ) =0.182258× {d} over {t} +0.000104× {{d} ^ {2}} over {{t} ^ {2}} -4.60
		double t = 1.0; //time.inUnit( RunTime.U_MINUTES );
		double d = speed.inUnits( RunDistance.U_METERS,  RunTime.U_MINUTES ); //dist.inUnit( RunDistance.U_METERS );
		double dt = d / t;
		return C6 * dt + C7 * dt * dt + C8;
	}

	/**
	 * Calculates the VDOT from a given race interval
	 * @param interval the RunInterval representing the race
	 * @return 
	 */
	public static double vdot( RunInterval interval ) {
		return oxygenCost( interval.getSpeed() ) / intensity( interval.getTime() );
	}
	
	static long BIN_SEARCH_MAX = 2419200000L; // 3 weeks in milliseconds
	static double BIN_SEARCH_EPSILON = 0.000001;
	
	/**
	 * Calculates an expected race result based on one's vdot.
	 * @param currentVdot the individual's vdot (as a double)
	 * @param eventDist the event distance (as a RunDistance)
	 * @return the expected time (as a RunTime) for running the event
	 */
	public static RunTime expectedPerformance( double currentVdot, RunDistance eventDist ) {
		// Implement Binary search:
		long low = 1;
		long high = BIN_SEARCH_MAX;
		long mid = 0L;
		double calcVdot = 0.0;
		do {
			mid = ( low + high ) / 2L;
			calcVdot = vdot( new RunInterval( new RunTime( mid ), eventDist ) );
			if ( calcVdot > currentVdot ) {
				low = mid;
			} else {
				high = mid;
			}
		} while ( Math.abs( calcVdot - currentVdot ) > BIN_SEARCH_EPSILON );
		return new RunTime( mid );
	}
	/**
	 * Calculates an expected time-based performance based on one's vdot.
	 * @param currentVdot the individual's vdot (as a double)
	 * @param eventTime the event time (as a RunTime)
	 * @return the expected distance (as a RunDistance) for running the event
	 */
	public static RunDistance expectedPerformance( double currentVdot, RunTime eventTime ) {
		// d = ( -C7 +/- Math.sqrt( C7*C7 - 4.0 * C8 * C6 ) ) / ( 2.0 * C8 )
		return new RunDistance( ( -C7 - Math.sqrt( C7 * C7 - 4.0 * C8 * C6 ) ) / ( 2.0 * C8 ) );
	}
	
	static final double C9 = 0.172;
	static final double C10 = 10.424;
	/**
	 * Calculates VO2 max from the speed (in m/min) of a 15 minute (all-out) run
	 * @param speed the speed of the 15 minute run in m/min
	 * @return the VO2 max
	 */
	public static double vo2max( RunSpeed speed ) {
		double sp = speed.inUnits( RunDistance.U_METERS, RunTime.U_MINUTES );
		return C9 * sp + C10;
	}
}
