import java.util.*;

class Vehicle {
    
    String model;
    int year;
    double rentFee;

    public Vehicle(String model, int year,double rentFee) {
        this.model = model;
        this.year = year;
        this.rentFee=rentFee;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public double getRentFee(){
        return rentFee;
    }
}


class RentVehicle {
    ArrayList<Vehicle> availableVehicle;
    ArrayList<Vehicle> rentedVehicles;

    public RentVehicle() {
        this.availableVehicle = new ArrayList<>();
        this.rentedVehicles = new ArrayList<>();
    }

    public void addVehicle(Vehicle vehicle) {
        availableVehicle.add(vehicle);
    }

    public List<Vehicle> getAvailableVehicle() {
        return availableVehicle;
    }

    public List<Vehicle> getRentedVehicles() {
        return rentedVehicles;
    }

    public void displayAvailableVehicle() {
        System.out.println("Available Vehicles:"+"\n");
        for (int i = 0; i < availableVehicle.size(); i++) {
            Vehicle vehicle = availableVehicle.get(i);
            System.out.println((i + 1) + ". " + vehicle.getModel() + " " + vehicle.getYear());
        }
    }

    public void displayRentedVehicles() {
        System.out.println("Rented Vehicles:"+"\n");
        for (int i = 0; i < rentedVehicles.size(); i++) {
            Vehicle vehicle = rentedVehicles.get(i);
            System.out.println((i + 1) + ". " + vehicle.getModel() + " " + vehicle.getYear());
        }
    }

    public void rentVehicle(int vehicleNumber) {
        Vehicle selectedVehicle = availableVehicle.get(vehicleNumber - 1);
        System.out.println("\nYou selected: " + selectedVehicle.getModel() + " " + selectedVehicle.getYear() +
                " | Rent Fee per Day: Rs." + selectedVehicle.getRentFee());

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the day to rent :");
        int day = sc.nextInt();
        double rentfee = (day * selectedVehicle.getRentFee());
        System.out.println("Your total rent fee : Rs." + rentfee + "\n");

        rentedVehicles.add(selectedVehicle);
        availableVehicle.remove(selectedVehicle);

        System.out.println("Vehicle rented successfully!");
    }

    public void returnVehicle(int vehicleNumber) {
        Vehicle returnedVehicle = rentedVehicles.get(vehicleNumber - 1);
        System.out.println("\nYou are returning: " + returnedVehicle.getModel() + " " + returnedVehicle.getYear() +
                " | Rent Fee per Day: Rs." + returnedVehicle.getRentFee());

        rentedVehicles.remove(returnedVehicle);
        availableVehicle.add(returnedVehicle);

        System.out.println("Vehicle returned successfully!");
    }
}

public class hh {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Vehicle vehicle1 = new Vehicle("BMW", 2022, 3000.00);
        Vehicle vehicle2 = new Vehicle("AUDI", 2020, 4000.00);
        Vehicle vehicle3 = new Vehicle("RANGE-ROVER", 2023, 5000.00);

        RentVehicle rentSystem = new RentVehicle();
        rentSystem.addVehicle(vehicle1);
        rentSystem.addVehicle(vehicle2);
        rentSystem.addVehicle(vehicle3);

        while (true) {
            System.out.println("\nSelect an option using a number \n"+
                                "1 --> Rent a Vehicle :\n"+
                                "2 --> Return a Vehicle :\n"+
                                "3 --> Exit\n");
            int choose = sc.nextInt();

            switch(choose){
                case 1:
                    rentSystem.displayAvailableVehicle();
                    System.out.print("\nSelect a Vehicle by entering the number (0 to cancel): ");
                    int rentVehicleNumber = sc.nextInt();
                    if (rentVehicleNumber == 0) {
                        break;
                    }
                    if (rentVehicleNumber < 1 || rentVehicleNumber > rentSystem.getAvailableVehicle().size()) {
                        System.out.println("Invalid vehicle selection.");
                        break;
                    }
                    rentSystem.rentVehicle(rentVehicleNumber);
                    break;
                case 2:
                    rentSystem.displayRentedVehicles();
                    System.out.print("\nSelect a Vehicle to return by entering the number (0 to cancel): ");
                    int returnVehicleNumber = sc.nextInt();
                    if (returnVehicleNumber == 0) {
                        break;
                    }
                    if (returnVehicleNumber < 1 || returnVehicleNumber > rentSystem.getRentedVehicles().size()) {
                        System.out.println("Invalid vehicle selection.");
                        break;
                    }
                    rentSystem.returnVehicle(returnVehicleNumber);
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please select again.");
            }
        }
    }
}
