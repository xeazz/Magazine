package org.example;
import java.util.*;


public class Basket {
    private final Map<String, Products> basketList = new HashMap<>();
    private int totalCost;

    private transient Products old;

    public void add(Products products) {
        if (products.isEmpty()) {
            System.out.println("Данные введены некорректно!");
        }
        if (basketList.containsKey(products.getName())) {
            old = basketList.get(products.getName());
            old.setAmount(old.getAmount() + 1);
            basketList.replace(products.getName(), old);
            System.out.println("Вы ещё раз добавили " + products.getName() + " в Вашу корзину");
        } else {
            products.setAmount(1);
            basketList.put(products.getName(), products);
            System.out.println("Товар успешно добавлен в корзину!");
        }
        totalCost += products.getCost();
    }

    public void remove(Products products) {
        if (products != null) {
            if (products.isEmpty()) {
                System.out.println("Данные введены некорректно!");
            }
            if (basketList.containsKey(products.getName())) {
                old = basketList.get(products.getName());
                old.setAmount(products.getAmount() - 1);
                if (old.getAmount() != 0) {
                    basketList.replace(products.getName(), old);
                    totalCost -= products.getCost();
                } else {
                    basketList.remove(products.getName(), products);
                    totalCost -= products.getCost();
                    System.out.println("Товар успешно удален из корзины!");
                }
            }
        } else {
            System.out.println("К сожалению, выбранный Вами товар отсутствует в корзине :(");
        }

    }

    public int getTotalCost() {

        return totalCost;
    }

    public void clear() {
        basketList.clear();
        totalCost = 0;
    }

    public Map<String, Products> getBasket() {
        return basketList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Map.Entry<String, Products> a : basketList.entrySet()) {
            Products value = a.getValue();
            sb.append(i)
                    .append(". ")
                    .append(value.getName())
                    .append(", кол-во ")
                    .append(value.getAmount())
                    .append(", цена: ")
                    .append(value.getCost())
                    .append(" руб/шт")
                    .append("\n");
            i++;
        }
        return sb.toString();
    }
}
