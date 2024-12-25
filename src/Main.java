import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        Shop shop = new Shop();
        shop.ShowProduct();
        Customer[] customer = Shop.initialUser();
        customer[0].selectProduct();
        shop.finalShoppingList();
        shop.calculateTotalPrice();
    }
}