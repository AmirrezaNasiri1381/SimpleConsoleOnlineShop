import java.util.*;

public class Shop {

    private static List<Product> products = new ArrayList<>();
    public static List<Categories> categories = new ArrayList<>();
    private static Map<Product,Categories> categoryToProductMap = new HashMap<>();

    private static List<Customer> customers = new ArrayList<>();
    private static List<Admin> admins = new ArrayList<>();
    private static HashSet<String> shoppingList = new HashSet<>();
    private static HashSet<String> finalList = new HashSet<>();

    public static Map<Product, Categories> getCategoryToProductMap() {
        return categoryToProductMap;
    }

    public static void setCategoryToProductMap(Map<Product, Categories> categoryToProductMap) {
        Shop.categoryToProductMap = categoryToProductMap;
    }

    public static List<Product> getProducts() {
        return products;
    }

    public static void setProducts(List<Product> products) {
        Shop.products = products;
    }

    public static List<Customer> getCustomers() {
        return customers;
    }

    public static List<Admin> getAdmins() {
        return admins;
    }

    public static List<Categories> getCategories() {
        return categories;
    }

    public static void setShoppingList(HashSet<String> shoppingList) {
        Shop.shoppingList = shoppingList;
    }

    public static void initializeSeedData() {
        // First, initialize products
        products = initialProductList();
//
        // Then initialize categories
        categories = initialCategories();
//
//        // Now initialize categoryToProducts after products and categories are ready
        categoryToProductMap = initialCategoryToProduct();

        // Add sample admins and customers
        admins.add(new Admin("admin1", "1234"));
        admins.add(new Admin("admin2", "5678"));
        admins.add(new Admin("admin3", "91011"));
        customers.add(new Customer("user1", "pass1"));
        customers.add(new Customer("user2", "pass2"));
        customers.add(new Customer("user3", "pass3"));
    }

    public static Product addProduct(String name, int price) {
        Product product = new Product();
        try {
            product.setName(name);
            product.setPrice(price);
        } catch (InvalidProductException | NegativePriceException e) {
            throw new RuntimeException(e);
        }
        return product;
    }


    public static ArrayList<Categories> initialCategories() {

        categories.add(new Categories(1, "Fruits"));
        categories.add(new Categories(2, "Furniture"));
        categories.add(new Categories(3, "Electronics"));

        return (ArrayList<Categories>) categories;
    }


    public static Map<Product,Categories>  initialCategoryToProduct() {

        Map< Product,Categories> categoryToProductMap = getCategoryToProductMap();
        ArrayList<Categories>categories = (ArrayList<Categories>) getCategories();
        ArrayList<Product> products  = (ArrayList<Product>) getProducts();
        categoryToProductMap.put(products.get(0),categories.get(0));
        categoryToProductMap.put(products.get(1),categories.get(1));
        categoryToProductMap.put(products.get(2),categories.get(2));
        categoryToProductMap.put(products.get(3),categories.get(2));
        categoryToProductMap.put(products.get(4),categories.get(2));
        return categoryToProductMap ;
    }

    public static ArrayList<Product> initialProductList() {
        products.add(addProduct("apple", 100));
        products.add(addProduct("rug", 500));
        products.add(addProduct("laptop", 1500));
        products.add(addProduct("TV", 2000));
        products.add(addProduct("Phone", 1200));
        return (ArrayList<Product>) products;
    }

    public void showCategories() {
        List<Categories> categories1 = getCategories();

        int index = 1;
        for (Categories category : categories1) {
            System.out.println("Category Number " + index + " is : " + category.getName());
            index++;
        }
    }

//    public void ShowProduct() {
//
//
//        // پیمایش لیست محصولات
//        for (int i = 0; i < products.size(); i++) {
//
//            // چاپ نام محصول و قیمت
//            System.out.print(i + 1 + " : " + products.get(i).getName() + " price is : " + products.get(i).getPrice() + " Category is : ");
//
//            // دسترسی به دسته‌بندی‌های هر محصول و چاپ آنها
//            Map<Categories, Product> categoryToProducts = Product.getCategoryToProductMap();
//
//            for (CategoryToProduct categoryToProduct : categoryToProducts) {
//                // چک کردن اینکه آیا categories مقداردهی شده است
//                if (categoryToProduct.getCategories() != null) {
//                    System.out.print(categoryToProduct.getCategories().getName() + " ");
//                } else {
//                    System.out.print("No category available ");
//                }
//            }
//
//            // چاپ یک خط جدید بعد از هر محصول
//            System.out.println();
//        }
//    }

    public void ShowProductsByCategory(String categoryName) {
        ArrayList<Product> products = initialProductList();
        boolean found = false;
        Map< Product,Categories> categoryToProducts = getCategoryToProductMap();

        for (Map.Entry< Product,Categories> entry : categoryToProducts.entrySet()) {
            Product product = entry.getKey();
            Categories category = entry.getValue();


            if (category != null && category.getName().equals(categoryName)) {
                System.out.println(product.getName() + " Price: " + product.getPrice());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No products found for category: " + categoryName);
        }
    }



    public static int getNumberOfProduct() {
        GetInput userInput = new GetInput();
        while (true) {
            try {
                System.out.println("Please Enter Number Of Product that You want buy ");
                return userInput.GetNumberInput();
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void finalShoppingList() {
        finalList.clear();
        Map<Product, Categories> categoryToProductMap = getCategoryToProductMap();
        for (String item : shoppingList) {
            for (Map.Entry<Product, Categories> entry : categoryToProductMap.entrySet()) {
                Product product = entry.getKey();
                if (item.equalsIgnoreCase(product.getName())) {
                    finalList.add(product.getName());
                    break;
                }
            }
        }
        printShoppingList(finalList);
    }

    public void calculateTotalPrice() {
        int totalPrice = 0;
        for (String item : finalList) {
            for (Product product : products) {
                if (product.getName().equalsIgnoreCase(item)) {
                    totalPrice += product.getPrice();
                }
            }
        }
        System.out.println("Total Price Is: " + totalPrice);
    }
    public static void printShoppingList(Set<String> productList) {
        System.out.println("Your Final List Is: " + productList);
    }
}
