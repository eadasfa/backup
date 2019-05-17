package spittr.entity;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table
public class Spittle {
	public static long auto=5520;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private final long id;
	private final String message;
	private final Date time;
	private Double latitude;
	private Double longitude;
	public Spittle(String message,Date time){
		this(message,time,null,null);
	}
	public Spittle(String message, Date time, Double longitude, Double latitude){
		this.id = auto++;
		this.message = message;
		this.time = time;
		this.longitude = longitude;
		this.latitude = latitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Long getId() {
		return id;
	}
	public String getMessage() {
		return message;
	}
	public Date getTime() {
		return time;
	}
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(
				this, "id", "time");
	}
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(
				this,obj, "id", "time");
	}
	@Override
	public String toString() {
		return "Spittle [id=" + id + ", message=" + message + ", time=" + time + ", latitude=" + latitude
				+ ", longitude=" + longitude + "]";
	}
	
	
	
}
