package by.milosh.java8.comparator;

import by.milosh.entity.Product;

import java.util.Arrays;
import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.Comparator.nullsLast;
import static java.util.stream.Collectors.toList;

/**
 * Contains examples of some features of java 8 Comparator class.
 * <p/>
 * Created on 2021-03-15
 * <p/>
 *
 * @author Alexander Milosh
 */
public class ComparatorExecutor {

    public void execute() {
        List<Product> products = composeList();

        // This line sorts list with case sensitive order and throw exception if code == null
        // List<Product> sortedProducts = products.stream().sorted(comparing(Product::getCode)).collect(toList());

        List<Product> sortedProducts = products.stream()
                .sorted(comparing(Product::getCode, nullsLast(String.CASE_INSENSITIVE_ORDER)))
                .collect(toList());

        System.out.println("For debug breakpoint.");
    }

    private List<Product> composeList() {
        Product product1 = new Product(1, "bbb");
        Product product2 = new Product(2, "AAA");
        Product product3 = new Product(3, "BBB");
        Product product4 = new Product(4, "aaa");
        Product product5 = new Product(4, null);

        return Arrays.asList(product1, product2, product3, product4, product5);
    }
}
