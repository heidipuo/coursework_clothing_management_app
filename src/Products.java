import java.util.ArrayList;

public class Products {

    private static ArrayList<Product> products = new ArrayList<>();

    public Products(){
        initialize();
    }

    private void initialize() {

    Product product1 = new Product("ulkovaatteet", "kurahousut", "104", "t. sin");
    Product product2 = new Product("ulkovaatteet", "toppapuku", "98", "musta", "Reima");
    Product product3 = new Product("sisävaatteet", "mekko", "38", "hopea", "glitter");
    Product product4 = new Product("muut", "bolero", "M", "limenvihreä", "nahka");
    Product product5 = new Product("muut", "kumpparit", "35", "musta");
    Product product6 = new Product("ulkovaatteet", "sadetakki", "XS", "keltainen");

    products.add(product1);
    products.add(product2);
    products.add(product3);
    products.add(product4);
    products.add(product5);
    products.add(product6);

    }
    
    public static  ArrayList<Product> getProducts(){
        return products;
    }

    public static void setProducts(ArrayList<Product> products) {
        Products.products = products;
    }

    public static ArrayList<String> getTypes(){

        ArrayList<String> typesOfProducts = new ArrayList<>();

        for (int i = 0; i < products.size(); i++){
            String type = products.get(i).getType();
            int count = 0;
            for (int j = 0; j < typesOfProducts.size(); j++) {
                if (!typesOfProducts.get(j).equals(type)) {
                    count++;
                }
            }
            if (count == typesOfProducts.size()) {
                typesOfProducts.add(type);
            }
        }

        return typesOfProducts;
    }

    public static void addNewClothe(String type, String name, String size, String color, String additionalInfo) {
        Product newProduct = new Product(type, name, size, color, additionalInfo);
        products.add(newProduct);
    }
    

}