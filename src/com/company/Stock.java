package com.company;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *  Class contains a list of all products.
 *  @version 1.1 17.03.2019
 *  @author Sergey Kotov
 */

public class Stock implements Iterable {
    private ArrayList<Product> productList = new ArrayList<>();

    /**Iterator constructor.*/
    @Override
    public Iterator iterator() {
        return new ProductIterator(productList);
    }

    /**The method returns product by his name.*/
    public Product findProduct(final String nameProduct) {
        for (Product product : productList) {
            if (product.getName().equals(nameProduct)) {
                return product;
            }
        }
        return null;
    }

    /**The method adds product in product list.*/
    public void addProduct(final Product product) {
        Product find = findProduct(product.getName());
        if (find != null) {
            find.setCount(find.getCount() + product.getCount());
        } else {
            productList.add(product);
        }
    }

    /**The method removes product in product list by his number.*/
    public void removeProduct(final int i) {
        productList.remove(i);
    }

    /**The method removes product in product list.*/
    public void removeProduct(final Product product) {
        productList.remove(product);
    }

    /**The method removes product in product list by his name.*/
    public void removeProduct(final String nameProduct, final int count) {
        for (Product product : productList) {
            if (product.getName().equals(nameProduct)) {
                if (count > product.getCount()) {
                    JOptionPane.showMessageDialog(null,
                            "Нельзя удалить больше продукта,"
                                    + " чем есть на складе");
                    return;
                }
                product.setCount(product.getCount() - count);
                if (product.getCount() <= 0) {
                    productList.remove(product);
                }
                return;
            }
        }
    }

    /**The method returns product in product list by his number.*/
    public Product getProduct(final int i) {
        return productList.get(i);
    }

    /**The method writs product list in the console.*/
    public void writeProductList() {
        for (Product s : productList) {
            System.out.println(s.toString());
        }
    }

    /**The method sorts product list by name.*/
    public void sortByName() {
        productList.sort(new ComparerByName());
    }

    /**The method sorts product list by count.*/
    public void sortByCount() {
        productList.sort(new ComparerByCount());
    }

    private static final class ProductIterator implements Iterator {
        private int iterator = 0;
        private ArrayList<Product> arr;

        private ProductIterator(final ArrayList<Product> productList) {
            arr = productList;
        }

        @Override
        public boolean hasNext() {
            return iterator < arr.size();
        }

        @Override
        public Object next() {
            return arr.get(iterator++);
        }
    }

}
