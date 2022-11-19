package org.example;
import java.util.*;

public class PriceList {
    private Map<String, List<Products>> category = new HashMap<>();

    public void addCategory(String newCategory) {
        if (category.containsKey(newCategory)) {
            System.out.println("Категория с таким именем уже существует!");
        } else {
            category.put(newCategory, new ArrayList<>());
            System.out.println("Категория успешно создана!!!");
        }
    }

    public void addProducts(Products products, String groupNames) {
        if (!category.containsKey(groupNames)) {
            System.out.println("Такой катагории не существует!");
        } else {
            List<Products> groupProductsList = category.get(groupNames);
            groupProductsList.add(products);
            System.out.println("Товар в категорию \"" + groupNames + "\" успешно добавлен!");
        }
    }

    public void getDescriptionProduct(String inputDescription) {
        for (Map.Entry<String, List<Products>> a : category.entrySet()) {
            List<Products> groupOfProducts = a.getValue();
            for (Products c : groupOfProducts) {
                if (c.getName().equals(inputDescription)) {
                    System.out.println("Описание товара: " + c.getProductsDescription());
                }
            }
        }
        System.out.println("Нам не удалось найти описание товара по Вашему запросу :(");
    }

    public Map<String, List<Products>> getCategory() {
        return category;
    }

    public void setCategory(Map<String, List<Products>> category) {
        this.category = category;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, List<Products>> a : category.entrySet()) {
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

