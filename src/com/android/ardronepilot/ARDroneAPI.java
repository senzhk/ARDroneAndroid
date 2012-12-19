package com.android.ardronepilot;

public class ARDroneAPI {

	private ARDrone ardrone;
	
	public static String DRONE_IP  = "192.168.1.1";
	
	public ARDroneAPI() throws Exception {		
		ardrone = new ARDrone(DRONE_IP);
	}
	
	public String getStatus() {
		return "";
	}
	
	public void landing() {
		try {
			ardrone.send_at_cmd("AT*REF=" 
								+ ardrone.get_seq() 
								+ ",290717696");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void hovering() {
		 try {
			ardrone.send_pcmd(0, 0, 0, 0, 0);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public void takeoff() {
		try {
			ardrone.send_at_cmd("AT*REF=" 
								+ ardrone.get_seq() 
								+ ",290718208");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void disbleEmergency() {
		try {
			ardrone.send_at_cmd("AT*REF=" 
								+ ardrone.get_seq() 
								+ ",290717952");
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public void trim() {
		try {
			ardrone.send_at_cmd("AT*FTRIM=" + ardrone.get_seq()); //flat trim
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public void up() {
		try {
			ardrone.send_pcmd(1, 0, 0, ardrone.getSpeed(), 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void down() {
		try {			
			ardrone.send_pcmd(1, 0, 0, -ardrone.getSpeed(), 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void rotatel() {
		try {			
			ardrone.send_pcmd(1, 0, 0, 0, -ardrone.getSpeed());
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public void rotater() {
		try {			
			ardrone.send_pcmd(1, 0, 0, 0, ardrone.getSpeed());
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public void goForward() {
		try {			
			ardrone.send_pcmd(1, ardrone.getSpeed(),0,0,0);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public void goBackward() {
		try {			
			ardrone.send_pcmd(1, -ardrone.getSpeed(),0,0,0);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	
	 
}
