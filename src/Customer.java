public class Customer {
    private String name;
    private Integer phoneNumber;

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

    public void selectProduct() {
        GetInput userInput = new GetInput();
        String[] product;
        int numberOfProduct = Shop.getNumberOfProduct();
        product = new String[numberOfProduct];
        for (int i = 0; i < product.length; i++) {
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
                    product[i] = input;
                    break;
                } catch (InvalidProductNameException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        Shop.setShoppingList(product);
    }
}