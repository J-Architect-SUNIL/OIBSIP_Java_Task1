import java.util.Scanner;
import java.util.Vector;
class User
{
    String username;
    String password;
}
class Reservation
{
    String name;
    int trainNo;
    String classType;
    String date;
    String source;
    String destination;
    int pnrNo;
    String status;
    int seatNo;
}
class Cancellation
{
    int pnrNo;
    String status;
}
public class OnlineReservationSystem 
{
    public static void main(String[] args) 
    {
        System.out.println("Welcome to the Online Reservation System");
        Scanner sc = new Scanner(System.in);
        Vector<User> users = new Vector<>();
        Vector<Reservation> reserve = new Vector<>();
        int seatBooked[] = new int[100];
        OnlineReservationSystem ors = new OnlineReservationSystem();
        boolean exit = false;
        User user = null;
        while (!exit) 
        {
            System.out.println("Do you have an account? (yes/no)");
            String response = sc.nextLine();
            user = ors.start(sc, response, users);
            if (user != null) 
            {
                System.out.println("You are logged in successfully!");
                boolean innerExit = false;
                while (!innerExit) 
                {
                    System.out.println("Do you want to make a reservation? (yes/no)");
                    String reservationResponse = sc.nextLine();
                    if (reservationResponse.equalsIgnoreCase("yes")) 
                    {
                        ors.reservationProcess(sc, user, reserve, seatBooked, reservationResponse);
                    } 
                    else 
                    {
                        System.out.println("No reservation made.");
                    }
                    System.out.println("Do you want to cancel a reservation? (yes/no)");
                    String cancelResponse = sc.nextLine();
                    if (cancelResponse.equalsIgnoreCase("yes")) 
                    {
                        ors.cancellationProcess(sc, reserve, seatBooked, cancelResponse);
                    } 
                    else 
                    {
                        System.out.println("No cancellation made.");
                    }
                    System.out.println("Do you want to continue booking/cancelling? (yes/no)");
                    String continueResponse = sc.nextLine();
                    if (!continueResponse.equalsIgnoreCase("yes")) 
                    {
                        innerExit = true;
                    }
                }
            }
            System.out.println("Do you want to exit the system? (yes/no)");
            String exitResponse = sc.nextLine();
            if (exitResponse.equalsIgnoreCase("yes")) 
            {
                exit = true;
                System.out.println("Exiting the system...");
            }
        }
        System.out.println("Thank you for using the Online Reservation System!");
        sc.close();
    }
    User start(Scanner sc, String response, Vector<User> users)
    {
        boolean authenticated = false;
        User user = null;
        if (response.equalsIgnoreCase("yes")) 
        {
            while (!authenticated)
            {
                System.out.println("Please log in.");
                System.out.print("Username: ");
                String username = sc.nextLine();
                System.out.print("Password: ");
                String password = sc.nextLine();
                for (User u : users) 
                {
                    if (u.username.equals(username) && u.password.equals(password)) 
                    {
                        user = u;
                        authenticated = true;
                        break;
                    }
                }
                if (!authenticated) 
                {
                    System.out.println("Invalid credentials. Try again!");
                }
            }
        } 
        else 
        {
            System.out.println("Please create an account.");
            user = new User();
            System.out.print("Enter username: ");
            user.username = sc.nextLine();
            System.out.print("Enter password: ");
            user.password = sc.nextLine();
            System.out.println("Account created successfully!");
            users.add(user);
            authenticated = true;
        }
        return user;
    }
    void reservationProcess(Scanner sc, User user, Vector<Reservation> reserve, int[] seatBooked, String reservationResponse)
    {
        System.out.println("Welcome, " + (user != null ? user.username : "") + "!");
        if (reservationResponse.equalsIgnoreCase("yes")) 
        {
            Reservation reservation = new Reservation();
            System.out.print("Enter train number: ");
            reservation.trainNo = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter class type: ");
            reservation.classType = sc.nextLine();
            System.out.print("Enter date of travel (dd/mm/yyyy): ");
            reservation.date = sc.nextLine();
            System.out.print("Enter source station: ");
            reservation.source = sc.nextLine();
            System.out.print("Enter destination station: ");
            reservation.destination = sc.nextLine();
            reservation.pnrNo = (int)(Math.random() * 1000000);
            reservation.status = "Confirmed";
            reservation.seatNo = (int)(Math.random() * 100) + 1;
            seatBooked[reservation.seatNo] = 1;
            reserve.add(reservation);
            System.out.println("Reservation successful! ");
            System.out.println("Reservation Details:");
            System.out.println("Name: " + reservation.name);
            System.out.println("Train No: " + reservation.trainNo);
            System.out.println("Class Type: " + reservation.classType);
            System.out.println("Date: " + reservation.date);
            System.out.println("Source: " + reservation.source);
            System.out.println("Destination: " + reservation.destination);
            System.out.println("PNR No: " + reservation.pnrNo);
            System.out.println("Status: " + reservation.status);
            System.out.println("Seat No: " + reservation.seatNo);
        }
        else 
        {
            System.out.println("No reservation made.");
            System.out.println("Thank you for using the Online Reservation System!");
        }
    }
    void cancellationProcess(Scanner sc, Vector<Reservation> reserve, int[] seatBooked, String cancelResponse)
    {
        if (cancelResponse.equalsIgnoreCase("yes")) 
        {
            Cancellation cancellation = new Cancellation();
            System.out.print("Enter PNR No to cancel: ");
            cancellation.pnrNo = sc.nextInt();
            sc.nextLine();
            boolean found = false;
            for (Reservation res : reserve) 
            {
                if (res.pnrNo == cancellation.pnrNo) 
                {
                    res.status = "Cancelled";
                    seatBooked[res.seatNo] = 0;
                    found = true;
                    System.out.println("Reservation cancelled successfully!");
                }
            }
            if (!found) 
            {
                System.out.println("PNR No not found.");
            }
        }
    }
}
