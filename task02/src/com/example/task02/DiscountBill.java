package com.example.task02;

public class DiscountBill extends Bill{
     private final double discount;

    public DiscountBill(double discount) {
        this.discount = discount / 100.0;
    }

    @Override
    public double getPrice() {
       return ( super.getPrice() - super.getPrice() * discount);
    }

    public double getDiscount() {
        return discount;
    }

    public String getSizeDiscount() {
        double size = discount * 100;
        return String.format("%.2f%%", size);

    }
    public String getAbsoluteDiscount() {
        double size = discount * super.getPrice();



        return String.format("%.2f", size);

    }
}
