package com.pluralsight;

public class LeaseContract extends Contract {
    private static final double LEASE_FEE_RATE = 0.07;
    private static final double INTEREST_RATE = 0.04 / 12;
    private static final int MONTHS = 36;

    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicleSold) {
        super(date, customerName, customerEmail, vehicleSold);
    }

    @Override
    public double getTotalPrice() {
        double basePrice = getVehicleSold().getPrice();
        double endingValue = basePrice * 0.50;
        double leaseFee = basePrice * LEASE_FEE_RATE;
        return endingValue + leaseFee;
    }

    @Override
    public double getMonthlyPayment() {
        double total = getTotalPrice();
        return (total * INTEREST_RATE) / (1 - Math.pow(1 + INTEREST_RATE, -MONTHS));
    }
}
