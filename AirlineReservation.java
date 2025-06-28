import java.util.ArrayList;
import java.util.Scanner;
class Flight {
    private String flightNumber;
    private String destination;
    private int capacity;
    private int seatsBooked;
    public Flight(String flightNumber, String destination, int capacity) {
        this.flightNumber = flightNumber;
        this.destination = destination;
        this.capacity = capacity;
        this.seatsBooked = 0;
    }
    public String getFlightNumber() {
        return flightNumber;
    }
    public String getDestination() {
        return destination;
    }
    public int getAvailableSeats() {
        return capacity - seatsBooked;
    }
    public boolean bookSeat() {
        if (seatsBooked < capacity) {
            seatsBooked++;
            return true;
        }
        return false;
    }
    public boolean cancelSeat() {
        if (seatsBooked > 0) {
            seatsBooked--;
            return true;
        }
        return false;
    }
    @Override
    public String toString() {
        return "Flight Number: " + flightNumber + ", Destination: " + destination + ", Available Seats: " + getAvailableSeats();
    }
}
public class AirlineReservationSystem {
    private ArrayList<Flight> flights;
    private Scanner scanner;
    public AirlineReservationSystem() {
        flights = new ArrayList<>();
        scanner = new Scanner(System.in);
    }
    public void displayMenu() {
        int choice;
        do {
            System.out.println("\nAirline Reservation System");
            System.out.println("1. View Flights");
            System.out.println("2. Add a Flight");
            System.out.println("3. Book a Flight");
            System.out.println("4. Cancel a Booking");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    viewFlights();
                    break;
                case 2:
                    addFlight();
                    break;
                case 3:
                    bookFlight();
                    break;
                case 4:
                    cancelBooking();
                    break;
                case 5:
                    System.out.println("Thank you for using the Airline Reservation System.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }
    private void viewFlights() {
        if (flights.isEmpty()) {
            System.out.println("No flights available.");
        } else {
            System.out.println("Available Flights:");
            for (Flight flight : flights) {
                System.out.println(flight);
            }
        }
    }
    private void addFlight() {
        System.out.print("Enter flight number: ");
        String flightNumber = scanner.nextLine();
        System.out.print("Enter destination: ");
        String destination = scanner.nextLine();
        System.out.print("Enter capacity: ");
        int capacity = scanner.nextInt();
        scanner.nextLine(); // consume newline

        flights.add(new Flight(flightNumber, destination, capacity));
        System.out.println("Flight added successfully!");
    }
    private void bookFlight() {
        System.out.print("Enter flight number to book: ");
        String flightNumber = scanner.nextLine();
        Flight flight = findFlightByNumber(flightNumber);

        if (flight != null) {
            if (flight.bookSeat()) {
                System.out.println("Seat booked successfully on flight " + flightNumber + " to " + flight.getDestination());
            } else {
                System.out.println("No available seats on flight " + flightNumber);
            }
        } else {
            System.out.println("Flight not found.");
        }
    }
    private void cancelBooking() {
        System.out.print("Enter flight number to cancel booking: ");
        String flightNumber = scanner.nextLine();
        Flight flight = findFlightByNumber(flightNumber);

        if (flight != null) {
            if (flight.cancelSeat()) {
                System.out.println("Booking cancelled successfully on flight " + flightNumber + " to " + flight.getDestination());
            } else {
                System.out.println("No seats booked on flight " + flightNumber);
            }
        } else {
            System.out.println("Flight not found.");
        }
    }
    private Flight findFlightByNumber(String flightNumber) {
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equals(flightNumber)) {
                return flight;
            }
        }
        return null;
    }
    public static void main(String[] args) {
        AirlineReservationSystem system = new AirlineReservationSystem(); 
        system.displayMenu();
    }
}
