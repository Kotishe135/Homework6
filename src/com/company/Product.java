package com.company;

/**
 *  Class containing information about product.
 *  @version 1.1 17.03.2019
 *  @author Sergey Kotov
 */

public class Product {
    private String name;
    private int count;

    /**Constructor.*/
    public Product(final String name, final int count) {
        this.name = name;
        this.count = count;
    }

    /**The method returns name and count of product.*/
    public String toString() {
        return name + " " + count + "шт.";
    }

    /**The method returns name of product.*/
    public String getName() {
        return name;
    }

    /**The method sets count of product.*/
    public void setCount(final int count) {
        this.count = count;
    }

    /**The method returns count of product.*/
    public int getCount() {
        return count;
    }
}
