public class Categories {
    private  int id;
    private String Name;
    private CategoryToProduct[] categoryToProducts;

    public Categories(int id, String name) {
        this.id = id;
        Name = name;
    }

    public CategoryToProduct[] getCategoryToProducts() {
        return categoryToProducts;
    }

    public void setCategoryToProducts(CategoryToProduct[] categoryToProducts) {
        this.categoryToProducts = categoryToProducts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
