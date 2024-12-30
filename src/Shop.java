import java.util.*;

public class Shop {

    private static List<Product> products = new ArrayList<>();
    public static List<Categories> categories = new ArrayList<>();
    public static List<CategoryToProduct> categoryToProducts = new ArrayList<>();
    private static List<Customer> customers = new ArrayList<>();
    private static List<Admin> admins = new ArrayList<>();
    private static HashSet<String> shoppingList = new HashSet<>();
    private static HashSet<String> finalList = new HashSet<>();

    public static List<Product> getProducts() {
        return products;
    }

    public static List<Customer> getCustomers() {
        return customers;
    }

    public static void setCustomers(List<Customer> customers) {
        Shop.customers = customers;
    }

    public static List<Admin> getAdmins() {
        return admins;
    }

    public static void setAdmins(List<Admin> admins) {
        Shop.admins = admins;
    }

    public static List<Categories> getCategories() {
        return categories;
    }

    public static void setCategories(List<Categories> categories) {
        Shop.categories = categories;
    }

    public static List<CategoryToProduct> getCategoryToProducts() {
        return categoryToProducts;
    }

    public static void setCategoryToProducts(List<CategoryToProduct> categoryToProducts) {
        Shop.categoryToProducts = categoryToProducts;
    }

    public static void setShoppingList(HashSet<String> shoppingList) {
        Shop.shoppingList = shoppingList;
    }

    public static void initializeSeedData() {
        // First, initialize products
//        products = initialProductList();
//
        // Then initialize categories
        categories = initialCategories();
//
//        // Now initialize categoryToProducts after products and categories are ready
//        categoryToProducts = initialCategoryToProduct();

        // Add sample admins and customers
        admins.add(new Admin("admin1", "1234"));
        admins.add(new Admin("admin2", "5678"));
        admins.add(new Admin("admin3", "91011"));
        customers.add(new Customer("user1", "pass1"));
        customers.add(new Customer("user2", "pass2"));
        customers.add(new Customer("user3", "pass3"));
    }

    public static Product addProduct(String name, int price, CategoryToProduct categoryToProduct) {
        Product product = new Product();
        try {
            product.setName(name);
            product.setPrice(price);
            List<CategoryToProduct> tempList = Product.getCategoryToProducts();
            if (categoryToProduct != null) {
                tempList.add(categoryToProduct);
            }
            Product.setCategoryToProducts(tempList);
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


    public static ArrayList<CategoryToProduct> initialCategoryToProduct() {
        ArrayList<Categories> categories1 =
                (ArrayList<Categories>) getCategories();
        categoryToProducts.add(new CategoryToProduct(categories1.get(0), null));  // apple -> Fruits
        categoryToProducts.add(new CategoryToProduct(categories1.get(1), null));  // rug -> Furniture
        categoryToProducts.add(new CategoryToProduct(categories1.get(2), null));  // laptop -> Electronics
        categoryToProducts.add(new CategoryToProduct(categories1.get(2), null));  // TV -> Electronics
        categoryToProducts.add(new CategoryToProduct(categories1.get(2), null));  // Phone -> Electronics

        return (ArrayList<CategoryToProduct>) categoryToProducts;
    }


    public static ArrayList<Product> initialProductList() {
        ArrayList<CategoryToProduct> categoryToProducts1 = initialCategoryToProduct();

        products.add(addProduct("apple", 100, categoryToProducts1.get(0)));
        products.add(addProduct("rug", 500, categoryToProducts1.get(1)));
        products.add(addProduct("laptop", 1500, categoryToProducts1.get(2)));
        products.add(addProduct("TV", 2000, categoryToProducts1.get(3)));
        products.add(addProduct("Phone", 1200, categoryToProducts1.get(4)));
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

    public static void setProducts(List<Product> products) {
        Shop.products = products;
    }


    public void ShowProduct() {


        // پیمایش لیست محصولات
        for (int i = 0; i < products.size(); i++) {

            // چاپ نام محصول و قیمت
            System.out.print(i + 1 + " : " + products.get(i).getName() + " price is : " + products.get(i).getPrice() + " Category is : ");

            // دسترسی به دسته‌بندی‌های هر محصول و چاپ آنها
            List<CategoryToProduct> categoryToProducts = Product.getCategoryToProducts();

            for (CategoryToProduct categoryToProduct : categoryToProducts) {
                // چک کردن اینکه آیا categories مقداردهی شده است
                if (categoryToProduct.getCategories() != null) {
                    System.out.print(categoryToProduct.getCategories().getName() + " ");
                } else {
                    System.out.print("No category available ");
                }
            }

            // چاپ یک خط جدید بعد از هر محصول
            System.out.println();
        }
    }

    public void ShowProductsByCategory(String categoryName) {
        ArrayList<Product> products = initialProductList();
        boolean found = false;

        // Ensure categoryToProducts is correctly initialized before accessing it
        List<CategoryToProduct> categoryToProducts = Product.getCategoryToProducts();

        for (int i = 0; i < products.size(); i++) {
            boolean isInCategory = false;

            for (CategoryToProduct categoryToProduct : categoryToProducts) {
                if (categoryToProduct.getCategories() != null &&
                        categoryToProduct.getCategories().getName().equals(categoryName)) {
                    isInCategory = true;
                    break;
                }
            }

            if (isInCategory) {
                System.out.println(products.get(i).getName() + " Price: " + products.get(i).getPrice());
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
        for (String item : shoppingList) {
            for (Product product : products) {
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


//    public static ArrayList<Categories> removeRepeatedMember(List<Categories> list) {
//        for (int i = 0; i < list.size() - 1; i++) {
//            for (int j = i + 1; j < list.size(); j++) {
//                if (list.get(i).equalsIgnoreCase(list.get(j))) {
//                    System.out.println("Element " + list.get(i) + " was repeated");
//                    list.remove(j);
//                    j--; // جبران حذف برای جلوگیری از پرش عناصر
//                }
//            }
//        }
//        return list;
//    }


    public static void printShoppingList(Set<String> productList) {
        System.out.println("Your Final List Is: " + productList);
    }
}
