import java.util.Arrays;


public class Shop {

    private static Product[] products = new Product[5];
    private static Customer[] customers = new Customer[1];
    private static String[] shoppingList;
    private static String[] finalList;




    public static void setShoppingList(String[] shoppingList) {
        Shop.shoppingList = shoppingList;
    }

    private static Product addProduct(String name, int price) {
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
        return product;
    }

    public static Product[] initialProductList() {

        products[0] = addProduct("apple", 100);
        products[1] = addProduct("rug", 500);
        products[2] = addProduct("laptop", 1500);
        products[3] = addProduct("TV", 2000);
        products[4] = addProduct("Phone", 1200);
        return products;
    }

    public static Customer adduser(String name, int phoneNumber) throws InvalidProductException, InvalidPhoneNumberException {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setPhoneNumber(phoneNumber);
        return customer;
    }

    public static Customer[] initialUser() {

        try {
            customers[0] = adduser("user", 2345);
        } catch (InvalidProductException e) {
            throw new RuntimeException(e);
        } catch (InvalidPhoneNumberException e) {
            throw new RuntimeException(e);
        }
        return customers;
    }

    public void ShowProduct() {
        products = initialProductList();
        for (int i = 0; i < products.length; i++) {
            System.out.println(i + 1 + " :" + products[i].getName() + " price is :" + products[i].getPrice());
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
