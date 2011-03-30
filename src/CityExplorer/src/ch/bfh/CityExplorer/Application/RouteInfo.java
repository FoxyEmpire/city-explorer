package ch.bfh.CityExplorer.Application;

public class RouteInfo {
	private String duration;
	private String distance;
	
	public RouteInfo(String duration, String distance){
		this.setDuration(duration);
		this.setDistance(distance);
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getDuration() {
		return duration;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getDistance() {
		return distance;
	}
}
