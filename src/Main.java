import java.util.ArrayList;
import java.util.Scanner;

class Bike{
private String bikeId;
private String model;
private String brand;
private double basePricePerDay;
private boolean isAvalable;

public Bike(String bikeId,String model,String brand,double basePricePerDay){
    this.bikeId = bikeId;
    this.model = model;
    this.brand = brand;
    this.basePricePerDay = basePricePerDay;
    this.isAvalable = true;
}
public String getBikeId(){
    return bikeId;
}

    public String getModel() {
        return model;
    }

    public double calculatePrice(int rentalDays) {
        return basePricePerDay * rentalDays;
    }
    public String getBrand(){
    return brand;
    }

    public boolean isAvalable() {
        return isAvalable;
    }

    public void rentBike(){
    isAvalable = false;
    }
    public void returnBike(){
    isAvalable = true;
    }
}
class Customer{

    private String customerId;
    private String customerName;

Customer(String customerId,String customerName){
this.customerName = customerName;
this.customerId = customerId;
}

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerId() {
        return customerId;
    }
}
class Rental{
    private Bike bike;
    private Customer customer;
    private int rentalDays;

    public Rental(Bike bike, Customer customer, int rentalDays){
        this.bike = bike;
        this.customer = customer;
        this.rentalDays = rentalDays;
    }

    public Bike getBike(){
        return bike;
    }

    public Customer getCustomer() {
        return customer;
    }
    public int getRentalDays(){
        return  rentalDays;
    }
}
class bikeRentalSystem{
    private ArrayList<Bike> bikes;
    private ArrayList<Customer> customers;
    private ArrayList<Rental> rentals;

    public bikeRentalSystem(){
        bikes = new ArrayList<Bike>();
        customers = new ArrayList<Customer>();
        rentals = new ArrayList<Rental>();
    }
    public void addBike(Bike bike){
        bikes.add(bike);
    }
    public void addCustomer(Customer customer){
        customers.add(customer);
    }

    public void rentBike(Bike bike, Customer customer, int rentDays){
        if(bike.isAvalable()){
            bike.rentBike();
            rentals.add(new Rental(bike,customer,rentDays));
        } else{
            System.out.println("Bike is not available for rent now!!!");
        }
    }

    public void returnBike(Bike bike){
        Rental rentalToRemove = null;
        for(Rental rental : rentals){
            if(rental.getBike()==bike){
                rentalToRemove = rental;
                break;
            }
        }
        if(rentalToRemove !=null){
            rentals.remove(rentalToRemove);
            bike.returnBike();
            System.out.println("Bike Returned, Ready for new Customer!!");
        }else{
            System.out.println("Bike was not rented");
        }
    }

    public void menu(){
        Scanner scan = new Scanner(System.in);
        while(true){
            System.out.println("RentoBike(🏍️) - Easy Rental Service in India");
            System.out.println("1. Rent a Bike");
            System.out.println("2. Return a Bike");
            System.out.println("3. Exit");
            int choice = scan.nextInt();
            scan.nextLine();

            if(choice == 1){
                System.out.println("\n== Rent a Bike ==\n");
                System.out.println("Enter your Name");
                String CustomerName = scan.nextLine();

                System.out.println("Available Bikes");
                for(Bike bike: bikes){
                    if(bike.isAvalable()){
                        System.out.println(bike.getBikeId() +" - " + bike.getBrand() + " " + bike.getModel());
                    }
                }
                System.out.println("Enter the Id of Bike You want to Rent");
                String bikeId = scan.nextLine();

                System.out.println("Enter the number of Days You want rent Bike");
                int rentDays = scan.nextInt();
                scan.nextLine();

                Customer newCustomer = new Customer("CUS" + (customers.size()+1),CustomerName);
                addCustomer(newCustomer);


                Bike selectedBike = null;
                for(Bike bike:bikes){
                    if(bike.getBikeId().equals(bikeId) && bike.isAvalable()){
                        selectedBike = bike;
                        break;
                    }
                }

                if(selectedBike !=null){
                    double totalPrice = selectedBike.calculatePrice(rentDays);
                    System.out.println("\n ==Rental Information==\n");
                    System.out.println("Customer Id 🪪: " + newCustomer.getCustomerId());
                    System.out.println("Customer Name 👦: " + newCustomer.getCustomerName());
                    System.out.println("Bike 🏍️: " + selectedBike.getBrand() + " " + selectedBike.getModel());
                    System.out.println("Rental Days ⌚:"+ rentDays);
                    System.out.println("Total Amount 💵: " + totalPrice);

                    System.out.println("\n Confirm Rental Service (Y/N) \n");
                    String validation = scan.nextLine();

                    if(validation.equalsIgnoreCase("Y")){
                        rentBike(selectedBike,newCustomer,rentDays);
                        System.out.println("Bike Rented Successfully 😍");
                    }else{
                        System.out.println("Service Cancelled!! See you Soon 🙌");
                    }

                } else{
                    System.out.println("Invallid Bike Selection, Bike not found...🥲");
                }

            } else if (choice==2) {
                System.out.println("\n== Return a Bike ==\n");
                System.out.println("Enter Bike ID you want to return 🪪: " );
                String bikeId = scan.nextLine();

                Bike bikeToReturn = null;
                for(Bike bike:bikes){
                    if(bike.getBikeId().equals(bikeId) && !bike.isAvalable()){
                        bikeToReturn = bike;
                        break;
                    }

                }
                if( bikeToReturn != null){
                    Customer customer = null;
                    for(Rental rental: rentals){
                        if(rental.getBike()==bikeToReturn){
                            customer = rental.getCustomer();
                            break;
                        }
                    }
                    if(customer !=null){
                        returnBike(bikeToReturn);
                        System.out.println("Bike Returned Successfully 🤞");
                    }else{
                        System.out.println("Bike was not rented or rental information is missing. 😥");
                    }
                }else{
                    System.out.println("Invalid Bike Id 😥| Details Not Found [404]");
                }
            } else if (choice==3) {
                break;
            } else{
                System.out.println("Invalid Value Entered | Choose a valid Value");
            }

        }
        System.out.println("\n Thank You for Using Bike Rental System 😍 | See You Soon 🙋‍♂️");
    }

}

public class Main {
    public static void main(String[] args) {
        System.out.println("Created with ❤️ by Bhavesh, For Learning Java Oops Concepts");
        bikeRentalSystem rentalSystem = new bikeRentalSystem();

        Bike bike1 = new Bike("B001","RX100", "Yamaha",300);
        Bike bike2 = new Bike("B002","Classic", "Royal Enfield",400);
        Bike bike3 = new Bike("B003","Perak", "Jawa",450);
        Bike bike4 = new Bike("B004","Bullet", "Royal Enfield",450);
        Bike bike5 = new Bike("B005","42 FJ", "Jawa",400);
        Bike bike6 = new Bike("B006","Hunter", "Royal Enfield",300);

        rentalSystem.addBike(bike1);
        rentalSystem.addBike(bike2);
        rentalSystem.addBike(bike3);
        rentalSystem.addBike(bike4);
        rentalSystem.addBike(bike5);
        rentalSystem.addBike(bike6);

        rentalSystem.menu();
    }
}
