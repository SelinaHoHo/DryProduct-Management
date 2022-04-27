package com.example.asmjava;

public class DriedFruit extends Product{
    protected float driedFruitTemp;

    public DriedFruit() {
    }

    public DriedFruit(int id, String name, float weight, float pricePerKg, float driedFruitTemp) {
        super(id, name, weight, pricePerKg);
        this.driedFruitTemp = driedFruitTemp;
    }

    public float getDriedFruitTemp() {
        return driedFruitTemp;
    }

    public void setDriedFruitTemp(Float driedFruitTemp) {
        this.driedFruitTemp = driedFruitTemp;
    }

    @Override
    public String toString() {
        return id + ", " + name + ", " + weight + ", " + pricePerKg + ", " + driedFruitTemp;
    }
}
