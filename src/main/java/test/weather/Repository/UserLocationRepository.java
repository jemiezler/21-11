package test.weather.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import test.weather.Model.Location;

@Repository
public interface UserLocationRepository extends JpaRepository<Location, String> {
    // JpaRepository provides basic CRUD operations
    Optional<Location> findTopByUserIdOrderByTimestampDesc(String userId);
}


