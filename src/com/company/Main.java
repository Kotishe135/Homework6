package com.company;

import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

/**
 *  Main class.
 *  @version 1.1 17.03.2019
 *  @author Sergey Kotov
 */

public final class Main {
    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT = 200;
    private static final int STANDART_HEIGHT = 25;
    private static final int TEXTAREA_HEIGHT = 125;
    private static final int TEXTFIELD_WIDTH = 50;
    private static final int COUNT_BUTTON = 5;
    private Main() {
    }

    public static void window(final Stock stock) {
        JFrame frame = new JFrame("Склад");
        JTextArea productList = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(productList);
        JButton addButton = new JButton("Добавить");
        JButton remButton = new JButton("Удалить");
        JButton writeInCon = new JButton("Вывод списка");
        JButton sortByName = new JButton("Сорт. по имени");
        JButton sortByCount = new JButton("Сорт. по кол-ву");
        JTextField nameProduct = new JTextField();
        JTextField countProduct = new JTextField("0");

        frame.setLayout(null);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - frame.getWidth()) / 2,
                (Toolkit.getDefaultToolkit().getScreenSize().height - frame.getHeight()) / 2);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        productList.setEnabled(false);
        productList.setDisabledTextColor(Color.BLACK);
        productList.setSize(frame.getWidth(), TEXTAREA_HEIGHT);
        productList.setLocation(0, 0);

        scrollPane.setBounds(productList.getBounds());

        addButton.setSize(frame.getWidth() / COUNT_BUTTON, STANDART_HEIGHT);
        addButton.setLocation(frame.getWidth() / COUNT_BUTTON,
                frame.getHeight() - addButton.getHeight() - STANDART_HEIGHT);

        remButton.setSize(frame.getWidth() / COUNT_BUTTON, STANDART_HEIGHT);
        remButton.setLocation(frame.getWidth() * 2 / COUNT_BUTTON,
                frame.getHeight() - remButton.getHeight() - STANDART_HEIGHT);

        writeInCon.setMargin(new Insets(1, 1, 1, 1));
        writeInCon.setSize(frame.getWidth() / COUNT_BUTTON, STANDART_HEIGHT);
        writeInCon.setLocation(0, frame.getHeight() - remButton.getHeight() - STANDART_HEIGHT);

        sortByName.setMargin(new Insets(1, 1, 1, 1));
        sortByName.setSize(frame.getWidth() / COUNT_BUTTON, STANDART_HEIGHT);
        sortByName.setLocation(frame.getWidth() * (2 + 1) / COUNT_BUTTON,
                frame.getHeight() - remButton.getHeight() - STANDART_HEIGHT);

        sortByCount.setMargin(new Insets(1, 1, 1, 1));
        sortByCount.setSize(frame.getWidth() / COUNT_BUTTON, STANDART_HEIGHT);
        sortByCount.setLocation(frame.getWidth() * (2 + 2) / COUNT_BUTTON,
                frame.getHeight() - remButton.getHeight() - STANDART_HEIGHT);

        countProduct.setSize(TEXTFIELD_WIDTH, STANDART_HEIGHT);
        countProduct.setLocation(frame.getWidth() - countProduct.getWidth(),
                frame.getHeight() - (nameProduct.getHeight() + addButton.getHeight() + STANDART_HEIGHT * 2));

        nameProduct.setSize(frame.getWidth() - countProduct.getWidth(), STANDART_HEIGHT);
        nameProduct.setLocation(0,
                frame.getHeight() - (nameProduct.getHeight() + addButton.getHeight() + STANDART_HEIGHT));

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                int count;
                try {
                    count = Integer.parseInt(countProduct.getText());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Введите количество числом");
                    return;
                }
                stock.addProduct(new Product(nameProduct.getText(), count));
                nameProduct.setText("");
                productList.setText("");
                Iterator<Product> iterator = stock.iterator();
                while (iterator.hasNext()) {
                    productList.setText(productList.getText() + iterator.next().toString() + "\n");
                }
            }
        });

        remButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                int count;
                try {
                    count = Integer.parseInt(countProduct.getText());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Введите количество числом");
                    return;
                }
                stock.removeProduct(nameProduct.getText(), count);
                nameProduct.setText("");
                productList.setText("");
                Iterator<Product> iterator = stock.iterator();
                while (iterator.hasNext()) {
                    productList.setText(productList.getText() + iterator.next().toString() + "\n");
                }
            }
        });

        writeInCon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                stock.writeProductList();
            }
        });

        sortByName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                stock.sortByName();
                productList.setText("");
                Iterator<Product> iterator = stock.iterator();
                while (iterator.hasNext()) {
                    productList.setText(productList.getText() + iterator.next().toString() + "\n");
                }
            }
        });

        sortByCount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                stock.sortByCount();
                productList.setText("");
                Iterator<Product> iterator = stock.iterator();
                while (iterator.hasNext()) {
                    productList.setText(productList.getText() + iterator.next().toString() + "\n");
                }
            }
        });

        frame.getContentPane().add(scrollPane);
        frame.getContentPane().add(writeInCon);
        frame.getContentPane().add(nameProduct);
        frame.getContentPane().add(countProduct);
        frame.getContentPane().add(addButton);
        frame.getContentPane().add(remButton);
        frame.getContentPane().add(sortByName);
        frame.getContentPane().add(sortByCount);
        frame.setVisible(true);
    }

    public static void main(final String[] args) {
        Stock stock = new Stock();
        window(stock);
    }
}