import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class TicketBookingSystem {
    private final boolean[] seats;
    
    // Constructor to initialize seat availability
    public TicketBookingSystem(int totalSeats) {
        seats = new boolean[totalSeats]; // false means available, true means booked
    }

    // Synchronized method to book a ticket
    public synchronized boolean bookSeat(int seatNumber, String customerName) {
        if (seatNumber < 0 || seatNumber >= seats.length) {
            System.out.println(customerName + " tried to book an invalid seat.");
            return false;
        }

        if (!seats[seatNumber]) {
            seats[seatNumber] = true;
            System.out.println(customerName + " successfully booked Seat #" + seatNumber);
            return true;
        } else {
            System.out.println(customerName + " attempted to book Seat #" + seatNumber + ", but it's already taken.");
            return false;
        }
    }
}

// Thread class representing a customer
class Customer extends Thread {
    private final TicketBookingSystem system;
    private final String customerName;
    private final boolean isVIP;
    private final Random random = new Random();

    public Customer(TicketBookingSystem system, String customerName, boolean isVIP) {
        this.system = system;
        this.customerName = customerName;
        this.isVIP = isVIP;
        if (isVIP) {
            this.setPriority(Thread.MAX_PRIORITY); // VIP customers get higher priority
        } else {
            this.setPriority(Thread.NORM_PRIORITY);
        }
    }

    @Override
    public void run() {
        int seatNumber = random.nextInt(10); // Randomly choose a seat from 0 to 9
        system.bookSeat(seatNumber, customerName);
    }
}

// Main Class
public class TicketBookingSimulation {
    public static void main(String[] args) {
        int totalSeats = 10; // Number of seats
        TicketBookingSystem system = new TicketBookingSystem(totalSeats);
        List<Customer> customers = new ArrayList<>();

        // Creating customers (some VIP and some regular)
        customers.add(new Customer(system, "Alice (VIP)", true));
        customers.add(new Customer(system, "Bob", false));
        customers.add(new Customer(system, "Charlie (VIP)", true));
        customers.add(new Customer(system, "David", false));
        customers.add(new Customer(system, "Eve (VIP)", true));
        customers.add(new Customer(system, "Frank", false));
        customers.add(new Customer(system, "Grace", false));

        // Start all threads (VIP customers should execute first due to priority)
        for (Customer customer : customers) {
            customer.start();
        }

        // Wait for all threads to finish
        for (Customer customer : customers) {
            try {
                customer.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("All bookings completed!");
    }
}

