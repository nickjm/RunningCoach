package com.example.runningcoach;

import android.content.Context;
import android.location.*;
import android.os.Bundle;

public class LocationHandler {
	private double _lat = -1.0;
	private double _lon = -1.0;
	private Context _con;
	
	public LocationHandler( Context activityContext ) {
		_con = activityContext;
	}
	
	private void _getLocation() {
		// Get the location manager
		LocationManager locationManager = (LocationManager) _con.getSystemService(Context.LOCATION_SERVICE);
		Criteria criteria = new Criteria();
		String bestProvider = locationManager.getBestProvider(criteria, false);
		Location location = locationManager.getLastKnownLocation(bestProvider);
		LocationListener loc_listener = new LocationListener() {

			public void onLocationChanged(Location l) {}

			public void onProviderEnabled(String p) {}

			public void onProviderDisabled(String p) {}

			@Override
			public void onStatusChanged(String p, int status, Bundle extras) {}
		};
		locationManager
		.requestLocationUpdates(bestProvider, 0, 0, loc_listener);
		location = locationManager.getLastKnownLocation(bestProvider);
		try {
			_lat = location.getLatitude();
			_lon = location.getLongitude();
		} catch (NullPointerException e) {
			_lat = -1.0;
			_lon = -1.0;
		}
	}
	
	public void updateLocation() {
		_getLocation();
	}
	public double getLat() {
		return _lat;
	}
	public double getLon() {
		return _lon;
	}
}
