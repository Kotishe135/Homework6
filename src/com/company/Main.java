package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

public class Main {

    public static void window(Stock stock){
        JFrame frame = new JFrame("Склад");
        frame.setLayout(null);
        frame.setSize(400, 200);
        frame.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - frame.getWidth())/2, (Toolkit.getDefaultToolkit().getScreenSize().height - frame.getHeight())/2);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JTextArea productList = new JTextArea();
        productList.setEnabled(false);
        productList.setDisabledTextColor(Color.BLACK);
        productList.setSize(400, 125);
        productList.setLocation(0, 0);

        JScrollPane scrollPane = new JScrollPane(productList);
        scrollPane.setBounds(productList.getBounds());

        JButton addButton = new JButton("Добавить");
        addButton.setSize(100, 25);
        addButton.setLocation(frame.getWidth()/2 - addButton.getWidth(), frame.getHeight() - addButton.getHeight() - 25);
        
        JButton remButton = new JButton("Удалить");
        remButton.setSize(100, 25);
        remButton.setLocation(frame.getWidth()/2, frame.getHeight() - remButton.getHeight() - 25);

        JButton writeInCon = new JButton("Вывод списка");
        writeInCon.setMargin(new Insets(1, 1, 1, 1));
        writeInCon.setSize(100, 25);
        writeInCon.setLocation(0, frame.getHeight() - remButton.getHeight() - 25);
        
        JTextField nameProduct = new JTextField();
        nameProduct.setSize(frame.getWidth(), 25);
        nameProduct.setLocation(0, frame.getHeight() - (nameProduct.getHeight() + addButton.getHeight() + 25));

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stock.addProduct(new Product(nameProduct.getText()));
                nameProduct.setText("");
                productList.setText("");
                Iterator<Product> iterator = stock.iterator();
                while (iterator.hasNext()) {
                    productList.setText(productList.getText() + iterator.next().getName() + "\n");
                }
            }
        });

        remButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stock.removeProduct(nameProduct.getText());
                nameProduct.setText("");
                productList.setText("");
                Iterator<Product> iterator = stock.iterator();
                while (iterator.hasNext()) {
                    productList.setText(productList.getText() + iterator.next().getName() + "\n");
                }
            }
        });

        writeInCon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stock.writeProductList();
            }
        });

        frame.getContentPane().add(scrollPane);
        frame.getContentPane().add(writeInCon);
        frame.getContentPane().add(nameProduct);
        frame.getContentPane().add(addButton);
        frame.getContentPane().add(remButton);
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
	    Stock stock = new Stock();
	    window(stock);
    }
}