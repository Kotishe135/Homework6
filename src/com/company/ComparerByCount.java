package com.company;

import java.io.Serializable;
import java.util.Comparator;

/**
 *  Class comparing products by quantity.
 *  @version 1.1 17.03.2019
 *  @author Sergey Kotov
 */

public class ComparerByCount implements Comparator<Product>, Serializable {

    /**The method comparing products by quantity.*/
    @Override
    public int compare(final Product o1, final Product o2) {
        return o1.getCount() - o2.getCount();
    }
}
