public class Product {
    private String name;
    private int price;
    private CategoryToProduct[] categoryToProducts;

    public CategoryToProduct[] getCategoryToProducts() {
        return categoryToProducts;
    }

    public void setCategoryToProducts(CategoryToProduct[] categoryToProducts) {
        this.categoryToProducts = categoryToProducts;
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