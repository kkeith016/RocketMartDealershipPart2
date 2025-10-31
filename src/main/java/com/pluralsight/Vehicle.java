package com.pluralsight;

public class Vehicle {
    private int vin, year, odometer;
    private String make, model, vehicleType, color;
    private double price;

    public Vehicle(int vin, int year, int odometer, String make, String model, String vehicleType, String color, double price) {
        this.vin = vin;
        this.year = year;
        this.odometer = odometer;
        this.make = make;
        this.model = model;
        this.vehicleType = vehicleType;
        this.color = color;
        this.price = price;
    }

    public int getVin() {
        return vin;
    }

    public int getYear() {
        return year;
    }

    public int getOdometer() {
        return odometer;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getColor() {
        return color;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format(
                """
        VIN: %-6d  | Year: %-4d | Make: %-12s | Model: %-15s
        Type: %-8s | Color: %-10s | Mileage: %-8d | Price: $%,.2f
        ---------------------------------------------------------------------------

        """,
                vin, year, make, model, vehicleType, color, odometer, price
        );
    }
}