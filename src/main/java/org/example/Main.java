package org.example;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        PriceList priceList = new PriceList();
        SortPriceList sortPriceList = new SortPriceList();
        Basket basket = new Basket();
        JsonFileWriter jsonFileWriter = new JsonFileWriter();
        ParserToJson parserToJson = new ParserToJson();
        List<String> categoryList = Arrays.asList("Смартфоны", "Ноутбуки", "Телевизоры", "Игры");
        categoryList.forEach(priceList::addCategory);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Список товаров по категориям");
        createListProducts(priceList);
        while (true) {
            printMenu();
            String input = scanner.nextLine();
            if ("end".equals(input)) {
                System.out.println("Программа завершена");
                break;
            }
            menuSelection(scanner, input, priceList, sortPriceList, basket, parserToJson, jsonFileWriter);
        }
    }

    public static void menuSelection(Scanner scanner, String input, PriceList priceList,
                                     SortPriceList sortPriceList, Basket basket,
                                     ParserToJson parserToJson, JsonFileWriter jsonFileWriter) {
        switch (input) {
            case "0" -> {
                printMenu();
                System.out.println("Введите название товара, на которое хотите получить описание");
                String inputDescription = scanner.nextLine();
                priceList.getDescriptionProduct(inputDescription);
            }
            case "1" -> {
                printMenu();
                System.out.print("Выберите категорию товара: ");
                String selectCategory = scanner.nextLine();
                System.out.print("Выберите товар из категории: ");
                String selectProduct = scanner.nextLine();
                if (addProductsToTheCart(selectCategory, selectProduct, priceList, basket)) {
                    System.out.println("товар успешно добавлен в корзину ");
                    System.out.println(basket.getTotalCost());
                } else {
                    System.out.println("К сожалению, мы не смогли найти товар по Вашему запросу :(");
                }
            }
            case "2" -> {
                if (basket.getBasket().isEmpty()) {
                    System.out.print("Ваша корзина пуста! Из нее нечего удалять...");
                } else {
                    System.out.print("Выберите категорию товара: ");
                    String remove = scanner.nextLine();
                    for (Map.Entry<String, Products> a : basket.getBasket().entrySet()) {
                        if (a.getKey().equals(remove)) {
                            Products b = a.getValue();
                            basket.remove(b);
                            System.out.println("Товар успешно удален!");
                        }
                    }
                }
            }
            case "3" -> {
                sortPriceList.sortCategory(priceList);
                System.out.println(sortPriceList);
            }
            case "4" -> {
                sortPriceList.sortProducts(priceList);
                System.out.println(sortPriceList);
            }
            case "5" -> {
                if (basket.getBasket().isEmpty()) {
                    System.out.println("Ваша корзина пуста :(");
                } else {
                    System.out.println(basket);
                    System.out.println("Общая сумма покупок составила: " + basket.getTotalCost() + " руб.");
                }
            }
            case "6" -> {
                if (basket.getBasket().isEmpty()) {
                    System.out.println("Ваша корзина пуста :(");
                } else {
                    jsonFileWriter.outputJson(parserToJson.parseJson(basket));
                    System.out.println("JSON-файл успешно создан !!!");
                }
            }
            case "What is JSON" -> parserToJson.description();
            case "7" -> {
                if (basket.getBasket().isEmpty()) {
                    System.out.println("Ваша корзина пуста :(");
                } else {
                    basket.clear();
                    System.out.println("Корзина успешно очищена!!!");
                }
            }
        }

    }

    public static boolean addProductsToTheCart(String selectCategory, String selectProduct, PriceList priceList, Basket basket) {
        for (Map.Entry<String, List<Products>> a : priceList.getCategory().entrySet()) {
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

    public static void printMenu() {
        System.out.println("Выберите действие. Для выхода из программы напишите \"end\"\n");
        System.out.println("0. Хочу получить описание товара");
        System.out.println("1. Добавить товар в корзину");
        System.out.println("2. Удалить товар из корзины");
        System.out.println("3. Отсортировать категории по алфавиту");
        System.out.println("4. Отсортировать товар по алфавиту");
        System.out.println("5. Показать содержимое корзины");
        System.out.println("6. Создать файл списка покупок (.json) \n Для справки \"Что такое JSON\" " +
                "наберите \"What is JSON?\"");
        System.out.println("7. Очистить корзину");
    }

    public static void createListProducts(PriceList priceList) {

        List<Products> productsPhone = Arrays.asList(new Products("Xiaomi", "Модель китайского производства", 10000), new Products("Apple iPhone 14", "Модель 2022 год выпуска. Уникальная модель", 35000), new Products("One Plus", "Конкурент Apple iPhone 14", 21000));

        List<Products> productsNotebook = Arrays.asList(new Products("ASUS ROG", "Игровой ноутбук", 100000), new Products("DELL", "Ноутбук для учебы", 90000), new Products("HP", "Ноутбук для офиса", 40000), new Products("Acer", "Игровой ноутбук с мощнейшей видеокартой", 80000),
                new Products("MSI", "Игровой (супермощный) нотебук", 87000));
        List<Products> productsTelevision = Arrays.asList(new Products("Philips", "Качество пушка", 200000),

                new Products("Hiesense", "FULL-HD", 94000), new Products("LG", "QLED дисплей", 102000), new Products("Samsung", "OLED дисплей", 253000));

        List<Products> productsGames = Arrays.asList(new Products("DOTA-2", "РПГ", 1), new Products("CS:GO", "шутер", 8500), new Products("C.Т.А.Л.К.Е.Р.", "ТОП ИГРА", 3600));
        for (Products products : productsPhone) {
            priceList.addProducts(products, "Смартфоны");
        }
        for (Products products : productsNotebook) {
            priceList.addProducts(products, "Ноутбуки");
        }
        for (Products products : productsTelevision) {
            priceList.addProducts(products, "Телевизоры");
        }
        for (Products products : productsGames) {
            priceList.addProducts(products, "Игры");
        }
        System.out.println(priceList);
    }
}