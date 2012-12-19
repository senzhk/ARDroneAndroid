package com.android.ardronepilot;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.TextView;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;

public class PilotActivity extends Activity implements LocationListener, SensorEventListener {

	private ARDroneAPI drone;
	private LocationManager mLocationManager;
	private SensorManager mSensorManager;
	private Location mCurrentLocation;
	private Location mTargetLocation;
	private boolean mStarted = false;
	private double mOrientation;
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {    	
    	super.onCreate(savedInstanceState);    	
    	setContentView(R.layout.main);
    
    	//dummy date, Auto Pilot is not completed.
    	mTargetLocation = new Location("target");
    	
    	mTargetLocation.setLatitude(1.0);
    	mTargetLocation.setLongitude(1.0);
    	
    	mCurrentLocation = new Location("cr");
  
    	mCurrentLocation.setLatitude(1.0);
    	mCurrentLocation.setLongitude(1.0);		
	}
	
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.connect:
			new Thread() {
			public void run() {
				try {
					drone = new ARDroneAPI();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			}.start();
			break;
			
		case R.id.disconnect:
			drone.disbleEmergency();
			break;
		
		case R.id.takeoff:
			mStarted = true;
			drone.takeoff();
			break;
		case R.id.landing:
			drone.landing();
			break;
		case R.id.up:
			drone.up();
			break;
		case R.id.down:
			drone.down();
			break;
		case R.id.ror:
			drone.rotater();
			break;
		case R.id.rol:
			drone.rotatel();
			break;
		case R.id.forward:
			drone.goForward();
			break;
		case R.id.backward:
			drone.goBackward();
			break;
		case R.id.hover:
			drone.hovering();
			break;
		}
	}
	
    @Override
    protected void onResume() {
        super.onResume();
        startSensors();
    }

    @Override
    protected void onPause() {
        stopSensors();
        super.onPause();
    }

    public void stopSensors() {
    	if (mLocationManager != null) {
    		mLocationManager.removeUpdates(this);
    		mLocationManager = null;
    	}
    	
    	if (mSensorManager != null) {
    		mSensorManager.unregisterListener(this);
    		mSensorManager = null;
    	}
    }
    
	public void startSensors() {
			if (mLocationManager == null) {
                mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                if (mLocationManager != null) {
                    // mGpsLocListener = new GpsLocationListener();
                	mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                         1000 /* minTime ms */,
                         1 /* minDistance in meters */,
                         this);
                }
			}
            

            if (mSensorManager == null) {
            	mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);	
            	if (mSensorManager != null) {
            		mSensorManager.registerListener(this, 
            				mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION)
            				, SensorManager.SENSOR_DELAY_NORMAL);

            	}
            }
         
	}

	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		mCurrentLocation = location;
		
		Log.d("Drone","Height="+mCurrentLocation.getAltitude());
	}

	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}

	public void onSensorChanged(SensorEvent event) {
			mOrientation = event.values[0];
	}
	
	
}
