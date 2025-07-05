import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Product cheese = new Cheese("Cheese", 100, 5, LocalDate.of(2025, 7, 10), 0.2);
        Product TV = new TV("TV", 150, 2, null); // no expiry
        Product card = new ScratchCard("Scratch Card", 50, 10);

        Cart cart = new Cart();
        cart.add(cheese, 2);
        cart.add(TV, 1);

        Customer customer = new Customer("John", 1000);


        try {
            Checkout.process(customer, cart);
        } catch (Exception e) {
            System.out.println("Checkout failed: " + e.getMessage());
        }



    }
}
