package org.example;
public class Products implements Comparable<Products> {
    private final String name;
    private final String productsDescription;
    private final int cost;
    private int amount = 0;

    public Products(String name, String productsDescription, int cost) {
        this.name = name;
        this.productsDescription = productsDescription;
        this.cost = cost;
    }

    public String getName() {
        return this.name;
    }

    public String getProductsDescription() {
        return productsDescription;
    }

    public int getCost() {
        return cost;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean isEmpty() {
        return name.isBlank() || cost == 0;
    }

    @Override
    public int compareTo(Products products) {
        return this.name.compareTo(products.name);
    }

    public String toString() {
        return "Товар: " + getName()
                + ", " + getCost() +
                " руб/шт" +
                "\nОписание товара: "
                + getProductsDescription() +
                "\n Кол-во товара: " + getAmount();
    }
}
