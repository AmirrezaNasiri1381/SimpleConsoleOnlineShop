import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Product {
    private String name;
    private int price;
    private static Map<Categories,Product> categoryToProductMap = new HashMap<>();

    public static Map<Categories, Product> getCategoryToProductMap() {
        return categoryToProductMap;
    }

    public static void setCategoryToProductMap(Map<Categories, Product> categoryToProductMap) {
        Product.categoryToProductMap = categoryToProductMap;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws InvalidProductException {
        if (name == null || name.isEmpty()) {
            throw new InvalidProductException("Product name cannot be null or empty.");
        }
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) throws NegativePriceException {
        if (price < 0) {
            throw new NegativePriceException("Product price cannot be negative.");
        }
        this.price = price;
    }
}