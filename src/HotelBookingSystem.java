import java.util.*;

// ===================== Room =====================
class Room {
    private int roomNumber;
    private boolean isBooked;

    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
        this.isBooked = false;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }
}

// ===================== Guest =====================
class Guest {
    private String name;

    public Guest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

// ===================== Reservation =====================
class Reservation {
    private Room room;
    private Guest guest;

    public Reservation(Room room, Guest guest) {
        this.room = room;
        this.guest = guest;
    }

    public Room getRoom() {
        return room;
    }

    public Guest getGuest() {
        return guest;
    }
}

// ===================== Hotel =====================
class Hotel {
    private List<Room> rooms = new ArrayList<>();
    private List<Reservation> reservations = new ArrayList<>();

    public Hotel(int numberOfRooms) {
        for (int i = 1; i <= numberOfRooms; i++) {
            rooms.add(new Room(i));
        }
    }

    public void showAvailableRooms() {
        System.out.println("\nAvailable Rooms:");
        boolean found = false;

        for (Room room : rooms) {
            if (!room.isBooked()) {
                System.out.println("Room " + room.getRoomNumber());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No available rooms.");
        }
    }

    public void bookRoom(int roomNumber, String guestName) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                if (!room.isBooked()) {
                    Guest guest = new Guest(guestName);
                    Reservation reservation = new Reservation(room, guest);

                    room.setBooked(true);
                    reservations.add(reservation);

                    System.out.println("Room booked successfully.");
                } else {
                    System.out.println("Room already booked.");
                }
                return;
            }
        }
        System.out.println("Room not found.");
    }

    public void cancelBooking(int roomNumber) {
        Iterator<Reservation> it = reservations.iterator();

        while (it.hasNext()) {
            Reservation res = it.next();

            if (res.getRoom().getRoomNumber() == roomNumber) {
                res.getRoom().setBooked(false);
                it.remove();

                System.out.println("Booking canceled.");
                return;
            }
        }

        System.out.println("Reservation not found.");
    }

    public void showReservations() {
        System.out.println("\nReservations:");

        if (reservations.isEmpty()) {
            System.out.println("No reservations.");
            return;
        }

        for (Reservation res : reservations) {
            System.out.println(
                "Room " + res.getRoom().getRoomNumber() +
                " -> " + res.getGuest().getName()
            );
        }
    }
}

// ===================== Main =====================
public class HotelBookingSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Hotel hotel = new Hotel(5);

        while (true) {
            System.out.println("\n=== Hotel Booking System ===");
            System.out.println("1. Show available rooms");
            System.out.println("2. Book room");
            System.out.println("3. Cancel booking");
            System.out.println("4. Show reservations");
            System.out.println("5. Exit");
            System.out.print("Choose: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    hotel.showAvailableRooms();
                    break;

                case 2:
                    System.out.print("Enter room number: ");
                    int roomNum = sc.nextInt();
                    sc.nextLine(); // consume newline

                    System.out.print("Enter guest name: ");
                    String name = sc.nextLine();

                    hotel.bookRoom(roomNum, name);
                    break;

                case 3:
                    System.out.print("Enter room number: ");
                    int cancelRoom = sc.nextInt();

                    hotel.cancelBooking(cancelRoom);
                    break;

                case 4:
                    hotel.showReservations();
                    break;

                case 5:
                    System.out.println("Goodbye.");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}