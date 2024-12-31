import java.util.ArrayList;
import java.util.List;

public class Categories {
    private  int id;
    private String Name;
    public List<CategoryToProduct> categoryToProducts = new ArrayList<>();

    public Categories(int id, String name) {
        this.id = id;
        Name = name;
    }

    public List<CategoryToProduct> getCategoryToProducts() {
        return categoryToProducts;
    }

    public void setCategoryToProducts(List<CategoryToProduct> categoryToProducts) {
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
