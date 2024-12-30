import java.util.*;

public class Customer implements User {
    private String name;

    private Integer phoneNumber;
    private String password;
    private static int customerCount = 0;


    public Customer() {
    }

    public Customer(String name, Integer phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public Customer(String name, String password) {
        this.name = name;
        this.password = password;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static int getCustomerCount() {
        return customerCount;
    }

    public static void setCustomerCount(int customerCount) {
        Customer.customerCount = customerCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws InvalidProductException {
        if (name == null || name.isEmpty()) {
            throw new InvalidProductException("Customer name cannot be null or empty.");
        }
        this.name = name;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) throws InvalidPhoneNumberException {
        if (phoneNumber == null || phoneNumber.toString().length() != 4) {
            throw new InvalidPhoneNumberException("Phone number must be a 4-digit number.");
        }
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean login(String username, String password) throws InvalidLoginException {
        Shop.initializeSeedData();
        List<Customer> customers = Shop.getCustomers();
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).name.equals(username) && customers.get(i).password.equals(password)) {
                System.out.println("Customer login successful.");
                return true;
            }
        }
        throw new InvalidLoginException("Invalid Customer credentials!");
    }

    @Override
    public void register(String username, String password) {
//        Shop.initializeSeedData();
        List<Customer> customers = Shop.getCustomers();
        if (customerCount < customers.size()) {
            customers.set(customerCount++, new Customer(name, password));
            System.out.println("Customer registered successfully.");
        } else {
            System.out.println("Customer list is full.");
        }
    }

    public void selectProduct() {
        GetInput userInput = new GetInput();
        HashSet<String> productSet = new HashSet<>();
        int numberOfProduct = Shop.getNumberOfProduct();
        for (int i = 0; i < numberOfProduct; i++) {
            while (true) {
                try {
                    System.out.println("Number " + (i + 1) + " : " + "Please Enter Your Product Name");
                    String input = userInput.GetStringInput();
                    if (input.matches(".*\\d.*")) {
                        throw new InvalidProductNameException("Invalid product name. Please enter only letters.");
                    }
                    if (input.equalsIgnoreCase("exit")) {
                        System.out.println("Your choosing finished.");
                        break;
                    }
                    productSet.add(input);
                    break;
                } catch (InvalidProductNameException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        Shop.setShoppingList(productSet);
    }

    public Categories getCategoryByInput() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Categories> categories1 = (ArrayList<Categories>) Shop.getCategories();

        System.out.println("Enter category number or category name: ");
        String input = scanner.nextLine();  // ورودی کاربر


        try {
            int categoryNumber = Integer.parseInt(input);  // اگر ورودی عدد باشد
            if (categoryNumber > 0 && categoryNumber <= categories1.size()) {
                return categories1.get(categoryNumber - 1);  // برگرداندن دسته‌بندی بر اساس شماره
            } else {
                System.out.println("Invalid category number.");
                return null;
            }
        } catch (NumberFormatException e) {

            for (Categories category : categories1) {
                if (category.getName().equalsIgnoreCase(input)) {
                    return category;
                }
            }
            System.out.println("Category with the name " + input + " not found.");
            return null;
        }
    }


}