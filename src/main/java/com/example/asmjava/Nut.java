package com.example.asmjava;

public class Nut extends Product{
    protected Float nutTemp;

    public Nut() {
    }

    public Nut(int id, String name, float weight, float pricePerKg, Float nutTemp) {
        super(id, name, weight, pricePerKg);
        this.nutTemp = nutTemp;
    }

    public Float getNutTemp() {
        return nutTemp;
    }

    public void setNutTemp(Float nutTemp) {
        this.nutTemp = nutTemp;
    }

    @Override
    public String toString() {
        return id + ", " + name + ", " + weight + ", " + pricePerKg + ", " + nutTemp ;
    }
}
