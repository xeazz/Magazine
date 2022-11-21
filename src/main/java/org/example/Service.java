package org.example;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Service {
    public void getDescription(Scanner scanner, PriceList priceList) {
        System.out.println("Введите название товара, на которое хотите получить описание");
        String inputDescription = scanner.nextLine();
        System.out.println(priceList.getDescriptionProduct(inputDescription));
    }

    public boolean addProductsToTheCart(Scanner scanner, PriceList priceList, Basket basket) {
        System.out.print("Выберите категорию товара: ");
        String selectCategory = scanner.nextLine();
        System.out.print("Выберите товар из категории: ");
        String selectProduct = scanner.nextLine();
        for (Map.Entry<String, List<Products>> a : priceList.getPriceList().entrySet()) {
            if (a.getKey().equals(selectCategory)) {
                List<Products> groupOfProducts = a.getValue();
                for (Products c : groupOfProducts) {
                    if (c.getName().equals(selectProduct)) {
                        basket.add(c);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void removeItem(Scanner scanner, Basket basket) {
        if (basket.getBasket().isEmpty()) {
            System.out.println("Ваша корзина пуста! Из нее нечего удалять...");
        } else {
            System.out.println("Выберите товар: ");
            String remove = scanner.nextLine();
            Products b = basket.getBasket().get(remove);
            basket.remove(b);
        }
    }

    public void sortingCategory(SortPriceList sortPriceList, PriceList priceList) {
        sortPriceList.sortCategory(priceList);
        System.out.println(sortPriceList);
    }

    public void sortingProducts(SortPriceList sortPriceList, PriceList priceList) {
        sortPriceList.sortProducts(priceList);
        System.out.println(sortPriceList);
    }

    public void viewShoppingCart(Basket basket) {
        if (basket.getBasket().isEmpty()) {
            System.out.println("Ваша корзина пуста :(");
        } else {
            System.out.println(basket);
            System.out.println("Общая сумма покупок: " + basket.getTotalCost() + " руб.");
        }
    }

    public void createJsonFile(Basket basket, JsonFileWriter jsonFileWriter, ParserToJson parserToJson) {
        if (basket.getBasket().isEmpty()) {
            System.out.println("Ваша корзина пуста :(");
        } else {
            jsonFileWriter.outputJson(parserToJson.parseJson(basket));
            System.out.println("JSON-файл успешно создан !!!");
        }
    }

    public void clearCart(Basket basket) {
        if (basket.getBasket().isEmpty()) {
            System.out.println("Ваша корзина пуста :(");
        } else {
            basket.clear();
            System.out.println("Корзина успешно очищена!!!");
        }
    }

    public void jsonDescription(ParserToJson parserToJson) {
        parserToJson.description();
    }
}
