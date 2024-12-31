public class Main {
    public static void main(String[] args) {

        Main main = new Main();
        main.start();

    }

    public void start() {

        GetInput inputUser = new GetInput();
        Shop.initializeSeedData();
        System.out.println("Select user type: \n1. Admin \n2. Customer");
        int choice = 0;
        try {
            choice = inputUser.GetNumberInput();
        } catch (InvalidInputException e) {
            throw new RuntimeException(e);
        }
        Main main = new Main();
        if (choice == 1) {
            main.handelAdmin();
        } else if (choice == 2) {
            main.handelCustomer();
        } else {
            System.out.println("Invalid selection!");
        }
    }

    public void handelAdmin() {
        GetInput inputUser = new GetInput();
        System.out.print("Enter Admin username: ");
        String username = inputUser.GetStringInput();
        System.out.print("Enter Admin password: ");
        String password = inputUser.GetStringInput();
        boolean adminFound = false;
        for (Admin admin : Shop.getAdmins()) {
            try {
                if (admin != null && admin.login(username, password)) {
                    System.out.println("Welcome Admin!");
                    adminFound = true;
                    break;
                }
            } catch (InvalidLoginException e) {
                System.out.println(e.getMessage());
            }
        }
        if (!adminFound) {
            System.out.println("Admin not found. Please register.");
            registerAdmin();
        }
    }

    public void handelCustomer() {
        GetInput inputUser = new GetInput();
        Shop shop = new Shop();
//        shop.initializeSeedData();
        System.out.print("Enter Customer username: ");
        String username = inputUser.GetStringInput();
        System.out.print("Enter Customer password: ");
        String password = inputUser.GetStringInput();
        boolean userFound = false;

        for (Customer customer : Shop.getCustomers()) {
            try {
                if (customer != null && customer.login(username, password)) {
                    System.out.println("Welcome Customer!");
                    shop.showCategories();
                    Categories categoryByInput = customer.getCategoryByInput();
                    String name = categoryByInput.getName();
                    shop.ShowProductsByCategory(name);
                    customer.selectProduct();
                    shop.finalShoppingList();
                    shop.calculateTotalPrice();
                    userFound = true;
                    break;
                }
            } catch (InvalidLoginException e) {

                System.out.println(e.getMessage());
            }
        }
        if (!userFound) {
            System.out.println("User not found. Please register.");
            registerCustomer();
        }
    }

    public static void registerAdmin() {
        GetInput inputUser = new GetInput();
        System.out.print("Enter a new Admin username: ");
        String newUsername = inputUser.GetStringInput();
        System.out.print("Enter a new Admin password: ");
        String newPassword = inputUser.GetStringInput();
        Admin newAdmin = new Admin(newUsername, newPassword);
        newAdmin.register(newUsername, newPassword);
        System.out.println("Admin registration successful! Welcome, " + newUsername);
    }

    public static void registerCustomer() {
        GetInput inputUser = new GetInput();
        System.out.print("Enter a new username: ");
        String newUsername = inputUser.GetStringInput();
        System.out.print("Enter a new password: ");
        String newPassword = inputUser.GetStringInput();
        Customer newCustomer = new Customer();
        try {
            newCustomer.setName(newUsername);

        } catch (InvalidProductException e) {
            throw new RuntimeException(e);
        }
        newCustomer.setPassword(newPassword);
        newCustomer.register(newUsername, newPassword);
        System.out.println("Customer registration successful! Welcome, " + newUsername);
        Main main = new Main();
        main.start();
    }
}
