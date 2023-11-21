package test.weather.Model;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import test.weather.Security.User;

@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // This will auto-generate unique IDs for each location
    private String id; // Unique identifier for each location

    @ManyToOne
    @JoinColumn(name = "user_id") // This column in the Location table references the ID in the User table
    private User user; // The user to whom this location belongs

    private double latitude; // Latitude coordinate

    private double longitude; // Longitude coordinate

    private LocalDateTime timestamp; // The date and time when the location was recorded

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
