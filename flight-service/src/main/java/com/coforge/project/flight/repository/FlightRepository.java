//package com.coforge.project.flight.repository;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import com.coforge.project.flight.model.Flight;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Repository
//public interface FlightRepository extends JpaRepository<Flight, Long> {
//    List<Flight> findBySourceAndDestination(String source, String destination);
//
//    // Custom query method to find flights by source, destination, departure time, and arrival time
//    List<Flight> findBySourceAndDestinationAndDepartureTimeAndArrivalTime(
//        String source, 
//        String destination, 
//        LocalDateTime departureTime, 
//        LocalDateTime arrivalTime
//    );
//}
package com.coforge.project.flight.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
 
import com.coforge.project.flight.model.Flight;
 
import java.util.List;
 
@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
	List<Flight> findBySourceIgnoreCaseAndDestinationIgnoreCase(String source, String destination);
    @Query("SELECT DISTINCT f.source FROM Flight f WHERE LOWER(f.source) LIKE LOWER(CONCAT('%', :query, '%')) "
    + "UNION " + "SELECT DISTINCT f.destination FROM Flight f WHERE LOWER(f.destination) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<String> findDistinctCityNames(String query);   }