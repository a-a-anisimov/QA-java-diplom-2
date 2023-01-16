package order;

import java.util.ArrayList;

public class Order {
    private static final String fluorescentBunR2D3 = "61c0c5a71d1f82001bdaaa6d";
    private static final String meatOfImmortalClamsProtostomia = "61c0c5a71d1f82001bdaaa6f";
    private final ArrayList<Object> ingredients;

    public Order(ArrayList<Object> ingredients) {
        this.ingredients = ingredients;
    }

    public static Order getDefaultOrder() {
        ArrayList<Object> order = new ArrayList<>();
        order.add(fluorescentBunR2D3);
        order.add(meatOfImmortalClamsProtostomia);
        return new Order(order);
    }

    public static Order getIncorrectOrder() {
        ArrayList<Object> order = new ArrayList<>();
        order.add("aaaaaaaaaaaa1");
        order.add("asdfghjkl1");
        return new Order(order);
    }
}
