package com.company;

import java.util.ArrayList;
import java.util.Iterator;

public class Stock implements Iterable {
    private ArrayList<Product> productList = new ArrayList<>();

    @Override
    public Iterator iterator() {
        return new ProductIterator(productList);
    }

    public void addProduct(Product product){
        productList.add(product);
    }

    public void removeProduct(int i){
        productList.remove(i);
    }

    public void removeProduct(Product product){
        productList.remove(product);
    }

    public void removeProduct(String nameProduct){
        for(Product product : productList){
            if (product.getName().equals(nameProduct)){
                productList.remove(product);
                return;
            }
        }
    }

    public Product getProduct(int i){
        return productList.get(i);
    }

    public void writeProductList(){
        for (Product s : productList){
            System.out.println(s.getName());
        }
    }

    private class ProductIterator implements Iterator{
        private int iterator = 0;
        private ArrayList<Product> arr;

        public ProductIterator(ArrayList<Product> productList) {
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
