import java.util.Arrays;

public class Shop {

    private static Product[] products = new Product[5];
    public static Categories[] categories = new Categories[3];
    public static CategoryToProduct[] categoryToProducts = new CategoryToProduct[5];
    private static Customer[] customers = new Customer[3];
    private static Admin[] admins = new Admin[3];
    private static String[] shoppingList;
    private static String[] finalList;

    public static void setShoppingList(String[] shoppingList) {
        Shop.shoppingList = shoppingList;
    }

    public static void initializeSeedData() {
        // Seed data for Admins
        admins[0] = new Admin("admin1", "1234");
        admins[1] = new Admin("admin2", "5678");
        admins[2] = new Admin("admin3", "91011");

        // Seed data for Customers
        customers[0] = new Customer("user1", "pass1");
        customers[1] = new Customer("user2", "pass2");
        customers[2] = new Customer("user3", "pass3");
    }

    private static Product addProduct(String name, int price, CategoryToProduct[] categoryToProducts) {
        Product product = new Product();
        try {
            product.setName(name);
        } catch (InvalidProductException e) {
            throw new RuntimeException(e);
        }
        try {
            product.setPrice(price);
        } catch (NegativePriceException e) {
            throw new RuntimeException(e);
        }
        product.setCategoryToProducts(categoryToProducts);
        return product;
    }

    public static Categories[] initialCategories() {
        categories[0] = new Categories(1, "Fruits");
        categories[1] = new Categories(2, "Furniture");
        categories[2] = new Categories(3, "Electronics");
        return categories;
    }

    public static CategoryToProduct[] initialCategoryToProduct() {
        Categories[] categories1 = initialCategories();
        categoryToProducts[0] = new CategoryToProduct(categories1[0], products[0]);  // apple -> Fruits
        categoryToProducts[1] = new CategoryToProduct(categories1[1], products[1]);  // rug -> Furniture
        categoryToProducts[2] = new CategoryToProduct(categories1[2], products[2]);  // laptop -> Electronics
        categoryToProducts[3] = new CategoryToProduct(categories1[2], products[3]);  // TV -> Electronics
        categoryToProducts[4] = new CategoryToProduct(categories1[2], products[4]);  // Phone -> Electronics

        return categoryToProducts;
    }

    public static Product[] initialProductList() {
        initialCategoryToProduct();
        products[0] = addProduct("apple", 100, new CategoryToProduct[]{categoryToProducts[0]});
        products[1] = addProduct("rug", 500, new CategoryToProduct[]{categoryToProducts[1]});
        products[2] = addProduct("laptop", 1500, new CategoryToProduct[]{categoryToProducts[2]});
        products[3] = addProduct("TV", 2000, new CategoryToProduct[]{categoryToProducts[2]});
        products[4] = addProduct("Phone", 1200, new CategoryToProduct[]{categoryToProducts[2]});
        return products;
    }

    public void showCategories() {
        Categories[] categories1 = initialCategories();
        for (int i = 1; i < categories1.length; i++) {
            System.out.println( "Category Number "+i + " is : " + categories1[i].getName());
        }
    }

    public static Product[] getProducts() {
        return products;
    }

    public static void setProducts(Product[] products) {
        Shop.products = products;
    }

    public void ShowProduct() {
        products = initialProductList();
        for (int i = 0; i < products.length; i++) {

            System.out.print(i + 1 + " : " + products[i].getName() + " price is : " + products[i].getPrice() + " Category is : ");

            Arrays.stream(products[i].getCategoryToProducts())
                    .map(categoryToProduct -> categoryToProduct.getCategories().getName())
                    .distinct()
                    .forEach(category -> System.out.print(category + " "));

            System.out.println();
        }
    }
    public void ShowProductsByCategory(String categoryName) {
        products = initialProductList();
        boolean found = false;

        for (int i = 0; i < products.length; i++) {

            boolean isInCategory = Arrays.stream(products[i].getCategoryToProducts())
                    .map(categoryToProduct -> categoryToProduct.getCategories().getName())
                    .anyMatch(category -> category.equals(categoryName));

            if (isInCategory) {
                System.out.print(i + 1 + " : " + products[i].getName() + " price is : " + products[i].getPrice() + " Category is : ");

                Arrays.stream(products[i].getCategoryToProducts())
                        .map(categoryToProduct -> categoryToProduct.getCategories().getName())
                        .distinct()
                        .forEach(category -> System.out.print(category + " "));

                System.out.println();
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

    public static Admin[] getAdmins() {
        return admins;
    }

    public static Customer[] getCustomers() {
        return customers;
    }
    public void finalShoppingList() {
        finalList = new String[shoppingList.length];
        String[] removeRepeatedMembers = removeRepeatedMember();
        boolean found = false;
        for (int i = 0; i < removeRepeatedMembers.length; i++) {
            for (Product product : products) {
                if (removeRepeatedMembers[i] == null) {
                    break;
                }
                if (removeRepeatedMembers[i].equalsIgnoreCase(product.getName())) {
                    finalList[i] = product.getName();
                    found = true;
                }
            }
            if (!found) {
                System.out.println("product " + shoppingList[i] + " is not in list");
            }
        }
        printShoppingList(finalList);
    }

    public void calculateTotalPrice() {
        int totalPrice = 0;
        for (String finalList : finalList) {
            for (Product product : products) {
                if (product.getName().equalsIgnoreCase(finalList)) {
                    totalPrice += product.getPrice();
                }
            }
        }
        System.out.println("TotalPrice Is :" + totalPrice);
    }


    public static String[] removeRepeatedMember() {

        for (int i = 0; i < shoppingList.length - 1; i++) {
            if (shoppingList[i] == null) {
                break;
            }
            for (int j = i + 1; j < shoppingList.length; j++) {
                if (shoppingList[i].equalsIgnoreCase(shoppingList[j])) {
                    //move element for removing
                    System.out.println("element " + shoppingList[i] + " was repeated");
                    for (int k = j; k < shoppingList.length - 1; k++) {
                        shoppingList[k] = shoppingList[k + 1];
                    }
                    shoppingList[shoppingList.length - 1] = "";
                    j--;
                }
            }
        }
        return shoppingList;
    }




    public static void printShoppingList(String[] productList) {
        String shopList = Arrays.toString(productList);
        System.out.println("Your Final List Is :" + shopList);

    }
}
