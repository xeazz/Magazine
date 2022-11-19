package org.example;
import java.util.*;

import java.util.stream.Collectors;

public class SortPriceList implements SortValue, SortKey {
    private Map<String, List<Products>> result;

    @Override
    public void sortCategory(PriceList priceList) {
        result = priceList.getCategory().entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        priceList.setCategory(result);
    }

    @Override
    public void sortProducts(PriceList priceList) {
        result = priceList.getCategory();
        for (List<Products> products : result.values()) {
            Collections.sort(products);
        }
        priceList.setCategory(result);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, List<Products>> a : result.entrySet()) {
            sb.append(a.getKey());
            sb.append("\n");
            for (Products products : a.getValue()) {
                sb.append("\t");
                sb.append(products.getName());
                sb.append(", ");
                sb.append(products.getCost());
                sb.append(" руб/шт");
                sb.append("\t");
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
