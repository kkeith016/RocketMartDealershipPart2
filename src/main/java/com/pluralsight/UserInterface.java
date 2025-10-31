package com.pluralsight;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private Dealership dealership;
    private Scanner scanner = new Scanner(System.in);

    public UserInterface() {
    }

    private void init() {
        dealership = FileManager.getDealership();
    }

    public void display() {
        init();

        do {
            homeMenuScreen();

            System.out.print("Type in an number: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> processGetByPriceRequest();
                case 2 -> processGetByMakeModelRequest();
                case 3 -> processGetByYearRequest();
                case 4 -> processGetByColorRequest();
                case 5 -> processGetByMileageRequest();
                case 6 -> processGetByVehicleTypeRequest();
                case 7 -> processGetAllVehiclesRequest();
                case 8 -> processAddVehicleRequest();
                case 9 -> processRemoveVehicleRequest();
                case 99 -> System.exit(0);
                default -> {
                    System.out.println("Invalid option entered, please try again...");
                }
            }
        } while(true);
    }

    private void homeMenuScreen() {
        System.out.println("""
                    1 - Find vehicles within a price range
                    2 - Find vehicles by make / model
                    3 - Find vehicles by year range
                    4 - Find vehicles by color
                    5 - Find vehicles by mileage range
                    6 - Find vehicles by type (car, truck, SUV, van)
                    7 - List ALL vehicles
                    8 - Add a vehicle
                    9 - Remove a vehicle
                    99 - Quit
                    """);
    }

    private void displayVehicles(List<Vehicle> vehicles) {
        for (Vehicle v: vehicles) {
            System.out.println(v);
        }
        System.out.println();
    }

    public void processGetByPriceRequest() {
        System.out.print("Please enter the max price: ");
        int max = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Please enter the min price: ");
        int min = scanner.nextInt();
        scanner.nextLine();

        displayVehicles(dealership.getVehiclesByPrice(min, max));
    }

    public void processGetByMakeModelRequest() {
        System.out.print("Please enter the make: ");
        String make = scanner.nextLine();

        System.out.print("Please enter the model: ");
        String model = scanner.nextLine();

        displayVehicles(dealership.getVehiclesByMakeModel(make, model));
    }

    public void processGetByYearRequest() {
        System.out.print("Please enter the max year: ");
        int max = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Please enter the min year: ");
        int min = scanner.nextInt();
        scanner.nextLine();

        displayVehicles(dealership.getVehiclesByYear(min, max));
    }

    public void processGetByColorRequest() {
        System.out.print("Please enter the color: ");
        String color = scanner.nextLine();

        displayVehicles(dealership.getVehiclesByColor(color));
    }

    public void processGetByMileageRequest() {
        System.out.print("Please enter the max mileage: ");
        int max = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Please enter the min mileage: ");
        int min = scanner.nextInt();
        scanner.nextLine();

        displayVehicles(dealership.getVehiclesByMileage(min, max));
    }

    public void processGetByVehicleTypeRequest() {
        System.out.print("Please enter the type: ");
        String type = scanner.nextLine();

        displayVehicles(dealership.getVehiclesByType(type));
    }

    public void processGetAllVehiclesRequest() {
        displayVehicles(dealership.getAllVehicles());
    }

    public void processAddVehicleRequest() {
        System.out.print("Please enter the vin: ");
        int vin = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Please enter the year: ");
        int year = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Please enter the odometer: ");
        int odometer = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Please enter the make: ");
        String make = scanner.nextLine();

        System.out.print("Please enter the model: ");
        String model = scanner.nextLine();

        System.out.print("Please enter the type: ");
        String vehicleType = scanner.nextLine();

        System.out.print("Please enter the color: ");
        String color = scanner.nextLine();

        System.out.print("Please enter the price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        dealership.addVehicle(new Vehicle(vin, year, odometer, make, model, vehicleType, color, price));
        FileManager.saveDealership(dealership);
    }

    public void processRemoveVehicleRequest() {
        System.out.print("Please enter the vin of the car you want to remove: ");
        int vin = scanner.nextInt();
        scanner.nextLine();

        dealership.removeVehicle(dealership.getVehicleByVin(vin));
        FileManager.saveDealership(dealership);
    }
}