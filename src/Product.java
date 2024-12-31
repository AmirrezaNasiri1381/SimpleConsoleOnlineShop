import java.util.ArrayList;
import java.util.List;

public class Product {
    private String name;
    private int price;
    public static List<CategoryToProduct> categoryToProducts = new ArrayList<>();

    public static List<CategoryToProduct> getCategoryToProducts() {
        return categoryToProducts;
    }

    public static void setCategoryToProducts(List<CategoryToProduct> categoryToProducts) {
        Product.categoryToProducts = categoryToProducts;
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