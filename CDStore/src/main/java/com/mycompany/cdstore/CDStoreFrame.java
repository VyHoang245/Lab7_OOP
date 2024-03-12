/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cdstore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author vygir
 */
public class CDStoreFrame extends JDialog implements Serializable{
    private JDialog NewCD;
    private JTextField idField, titleField, priceField, yearOfReleaseField;
    private JButton addButton, clearButton, showAllButton;
    private JComboBox collection;
    private JRadioButton rb1, rb2;
    
    
    public CDStoreFrame(ArrayList<CD> list) {
        //init form
        NewCD = new JDialog(this, "Quach Tinh_CD Store");
        NewCD.setSize(500, 500);
        NewCD.setLocationRelativeTo(null);
        //Layout control
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel(new GridLayout(7, 2, 5, 5));
        JPanel type = new JPanel(new GridLayout(1, 3, 5, 5));
        JPanel collect = new JPanel(new GridLayout(1, 2, 5, 5));
        JPanel buttons = new JPanel(new GridLayout(1, 3, 5, 5));
        String obj[] = {"Movie", "MV", "Record"};        
        collection = new JComboBox(obj);
        idField = new JTextField();
        titleField = new JTextField();
        priceField = new JTextField();
        yearOfReleaseField = new JTextField();
        addButton = new JButton("Add");
        clearButton = new JButton("Clear");
        showAllButton = new JButton("Show All");
        rb1 = new JRadioButton("VCD");
        rb2 = new JRadioButton("CD");
        rb1.setBounds(75, 50, 100, 30);
        rb2.setBounds(75, 100, 100, 30);
        ButtonGroup bg = new ButtonGroup();
        bg.add(rb1);
        bg.add(rb2);
        //Add control to Layout(type Layout)
        type.add(new JLabel("CD Type "));
        type.add(rb1);
        type.add(rb2);
        //Add control to Layout(collection Layout)
        collect.add(new JLabel("CD Collection "));
        collect.add(collection);
        //Add control to Layout(inputPanel Layout)
        inputPanel.add(new JLabel("CD ID "));
        inputPanel.add(idField);
        inputPanel.add(new JLabel("CD Title "));
        inputPanel.add(titleField);
        inputPanel.add(collect);
        inputPanel.add(type);
        inputPanel.add(new JLabel("CD Price "));
        inputPanel.add(priceField);
        inputPanel.add(new JLabel("CD Year of Release "));
        inputPanel.add(yearOfReleaseField);
        //Add control to Layout(buttons Layout)
        buttons.add(addButton);
        buttons.add(clearButton);
        buttons.add(showAllButton);
        //Add imputPanel layout to mainpanel Layout
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(buttons, BorderLayout.SOUTH);
        NewCD.add(mainPanel);
        NewCD.setVisible(true);
        //add(mainPanel);
        //setVisible(true);
        //mainPanel.add(displayButton, BorderLayout.EAST);
        //addButton.addActionListener();
        addButton.addActionListener(new ActionListener() {            
            @Override
            public void actionPerformed(ActionEvent e) { 
                addCD(list);
                JOptionPane.showMessageDialog(null, "Successful");
                //setDefaultCloseOperation(HIDE_ON_CLOSE);
            }            
        });        
    }

    private void addCD(ArrayList<CD> list) {
        String id = idField.getText();
        String title = titleField.getText();
        String collection = (String) this.collection.getItemAt(this.collection.getSelectedIndex());        
        String type = "";
        if (rb1.isSelected()) {
            type = "VCD";
        }
        if (rb2.isSelected()) {
            type = "CD";
        }
        String price = priceField.getText();
        String year = yearOfReleaseField.getText();
        CD cd = new CD(id, collection, type, title, price, year);
        list.add(cd);
        //saveCD(list);
        clearFields();
        
    }

    private void clearFields() {
        idField.setText("");
        titleField.setText("");
        priceField.setText("");
        yearOfReleaseField.setText("");
    }
    String fileName = "D:\\CD.dat";
    
    private void saveCD(ArrayList<CD> list) {
        try {
            FileOutputStream f = new FileOutputStream(fileName);
            ObjectOutputStream oStream = new ObjectOutputStream(f);
            oStream.writeObject(list);
            oStream.close();
        } catch (IOException e) {
            System.out.println("Error save file" + e.getMessage());
        }
    }
    
    private void loadCD(ArrayList<CD> list) {
        try {
            FileInputStream f = new FileInputStream(fileName);
            ObjectInputStream inStream = new ObjectInputStream(f);
            list = (ArrayList<CD>) inStream.readObject();
            inStream.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found");
        } catch (IOException e) {
            System.out.println("Error load file");
        }
        
    }

}
