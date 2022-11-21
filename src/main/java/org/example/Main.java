package org.example;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Service service = new Service();
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
            menuSelection(scanner, input, priceList, sortPriceList, basket, parserToJson, jsonFileWriter, service);
        }
    }

    public static void menuSelection(Scanner scanner, String input, PriceList priceList,
                                     SortPriceList sortPriceList, Basket basket,
                                     ParserToJson parserToJson, JsonFileWriter jsonFileWriter, Service service) {
        switch (input) {
            case "0" -> service.getDescription(scanner, priceList);

            case "1" -> {
                if (!service.addProductsToTheCart(scanner, priceList, basket))
                    System.out.println("Выбранный Вами товар отсутствует в каталоге товаров!");
            }
            case "2" -> service.removeItem(scanner, basket);
            case "3" -> service.sortingCategory(sortPriceList, priceList);
            case "4" -> service.sortingProducts(sortPriceList, priceList);
            case "5" -> service.viewShoppingCart(basket);
            case "6" -> service.createJsonFile(basket, jsonFileWriter, parserToJson);
            case "7" -> service.clearCart(basket);
            case "What is JSON" -> service.jsonDescription(parserToJson);
        }
    }

    public static void printMenu() {
        System.out.println("Выберите действие. Для выхода из программы напишите \"end\"\n");
        System.out.println("0. Хочу получить описание товара");
        System.out.println("1. Добавить товар в корзину");
        System.out.println("2. Удалить товар из корзины");
        System.out.println("3. Отсортировать категории по алфавиту");
        System.out.println("4. Отсортировать товар по алфавиту");
        System.out.println("5. Показать содержимое корзины");
        System.out.println("6. Создать файл списка покупок (.json). Для справки \"Что такое JSON\" " +
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