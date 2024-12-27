public class CategoryToProduct {


    private Categories categories;
    private Product product;

    public CategoryToProduct(Categories categories, Product product) {
        this.categories = categories;
        this.product = product;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


}
