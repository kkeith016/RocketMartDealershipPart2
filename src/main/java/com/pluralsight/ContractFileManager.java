package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ContractFileManager {
    private static final String CONTRACTS_FILE = "src/main/resources/contracts.csv";

    public void saveContract(Contract contract) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CONTRACTS_FILE, true))) {
            Vehicle v = contract.getVehicleSold();
            String line = "";

            if (contract instanceof SalesContract sale) {
                line = String.format("SALE|%s|%s|%s|%d|%d|%s|%s|%s|%s|%d|%.2f|%.2f|%.2f|%.2f|%.2f|%s|%.2f",
                        sale.getDate(), sale.getCustomerName(), sale.getCustomerEmail(),
                        v.getVin(), v.getYear(), v.getMake(), v.getModel(), v.getVehicleType(), v.getColor(),
                        v.getOdometer(), v.getPrice(),
                        v.getPrice() * 0.05, // sales tax
                        100.00,
                        (v.getPrice() < 10000 ? 295.00 : 495.00),
                        sale.getTotalPrice(),
                        sale.getMonthlyPayment(),
                        (sale.getMonthlyPayment() > 0 ? "YES" : "NO"),
                        sale.getMonthlyPayment()
                );
            } else if (contract instanceof LeaseContract lease) {
                double base = v.getPrice();
                double ending = base * 0.50;
                double leaseFee = base * 0.07;

                line = String.format("LEASE|%s|%s|%s|%d|%d|%s|%s|%s|%s|%d|%.2f|%.2f|%.2f|%.2f|%.2f",
                        lease.getDate(), lease.getCustomerName(), lease.getCustomerEmail(),
                        v.getVin(), v.getYear(), v.getMake(), v.getModel(), v.getVehicleType(), v.getColor(),
                        v.getOdometer(), v.getPrice(),
                        ending, leaseFee,
                        lease.getTotalPrice(), lease.getMonthlyPayment());
            }

            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
