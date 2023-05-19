package org.example;

public class Nutritions {
    private double calories;
    private double fat;
    private double sugar;
    private double carbohydrates;
    private double protein;

    public Nutritions(double calories, double fat, double sugar, double carbohydrates, double protein) {
        this.calories = calories;
        this.fat = fat;
        this.sugar = sugar;
        this.carbohydrates = carbohydrates;
        this.protein = protein;
    }

    public double getCalories() {
        return calories;
    }

    public double getFat() {
        return fat;
    }

    public double getSugar() {
        return sugar;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public double getProtein() {
        return protein;
    }

    @Override
    public String toString() {
        return "\ncalories: " + calories + "\n" +
                "fat: " + fat + "\n" +
                "sugar: " + sugar + "\n" +
                "carbohydrates: " + carbohydrates + "\n" +
                "protein: " + protein + "\n";
    }
}
