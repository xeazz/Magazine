package org.example;
import java.util.*;

public class PriceList {
    private Map<String, List<Products>> priceList = new HashMap<>();

    public void addCategory(String newCategory) {
        if (priceList.containsKey(newCategory)) {
            System.out.println("Категория с таким именем уже существует!");
        } else {
            priceList.put(newCategory, new ArrayList<>());
            System.out.println("Категория успешно создана!!!");
        }
    }

    public void addProducts(Products products, String groupNames) {
        if (!priceList.containsKey(groupNames)) {
            System.out.println("Такой катагории не существует!");
        } else {
            List<Products> groupProductsList = priceList.get(groupNames);
            groupProductsList.add(products);
            System.out.println("Товар в категорию \"" + groupNames + "\" успешно добавлен!");
        }
    }

    public String getDescriptionProduct(String inputDescription) {
        for (Map.Entry<String, List<Products>> a : priceList.entrySet()) {
            List<Products> groupOfProducts = a.getValue();
            for (Products c : groupOfProducts) {
                if (c.getName().equals(inputDescription)) {
                    return "Описание товара: " + c.getProductsDescription();
                }
            }
        }
        return "Нам не удалось найти описание товара по Вашему запросу :(";
    }

    public Map<String, List<Products>> getPriceList() {
        return priceList;
    }

    public void setPriceList(Map<String, List<Products>> priceList) {
        this.priceList = priceList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, List<Products>> a : priceList.entrySet()) {
            sb.append(a.getKey());
            sb.append("\n");
            for (Products products : a.getValue()) {
                sb.append("\t");
                sb.append(products.getName());
                sb.append(", ");
                sb.append(products.getCost());
                sb.append(" руб./шт");
                sb.append("\t");
                sb.append("\n");

            }
        }
        return sb.toString();
    }
}

