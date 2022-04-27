package com.example.asmjava;

public class Product {
    protected int id;
    protected String name;
    protected float weight;
    protected float pricePerKg;

    public Product() {
    }

    public Product(int id, String name, float weight, float pricePerKg) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        if(weight < 0 || weight > 100)
        {
            throw new NumberFormatException();
        }
        this.pricePerKg = pricePerKg;
        if(pricePerKg < 5 || pricePerKg > 100)
        {
            throw new NumberFormatException();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getPricePerKg() {
        return pricePerKg;
    }

    public void setPricePerKg(float pricePerKg) {
        this.pricePerKg = pricePerKg;
    }

    @Override
    public String toString() {
        return id + ", " + name + ", " + weight + ", " + pricePerKg;
    }
}
