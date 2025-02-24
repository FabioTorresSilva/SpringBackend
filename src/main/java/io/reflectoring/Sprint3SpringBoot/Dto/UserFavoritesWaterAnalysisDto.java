package io.reflectoring.Sprint3SpringBoot.Dto;

public class UserFavoritesWaterAnalysisDto {
    private int totalTests;
    private double lowestRadonValue;
    private String lowestRadonFountain;
    private double highestRadonValue;
    private String highestRadonFountain;
    private double drinkablePercentage;

    // Getters and setters...
    public int getTotalTests() {
        return totalTests;
    }

    public void setTotalTests(int totalTests) {
        this.totalTests = totalTests;
    }

    public double getLowestRadonValue() {
        return lowestRadonValue;
    }

    public void setLowestRadonValue(double lowestRadonValue) {
        this.lowestRadonValue = lowestRadonValue;
    }

    public String getLowestRadonFountain() {
        return lowestRadonFountain;
    }

    public void setLowestRadonFountain(String lowestRadonFountain) {
        this.lowestRadonFountain = lowestRadonFountain;
    }

    public double getHighestRadonValue() {
        return highestRadonValue;
    }

    public void setHighestRadonValue(double highestRadonValue) {
        this.highestRadonValue = highestRadonValue;
    }

    public String getHighestRadonFountain() {
        return highestRadonFountain;
    }

    public void setHighestRadonFountain(String highestRadonFountain) {
        this.highestRadonFountain = highestRadonFountain;
    }

    public double getDrinkablePercentage() {
        return drinkablePercentage;
    }

    public void setDrinkablePercentage(double drinkablePercentage) {
        this.drinkablePercentage = drinkablePercentage;
    }
}
