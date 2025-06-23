
package com.mycompany.studentinformation;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author IP
 */
public class StudentDataBase {

     JFrame frame=new JFrame("student information");
        JButton b1=new JButton("save");
        JButton b2=new JButton("delate");
        JButton b3=new JButton("update");
        JButton b4=new JButton("Exit");
        JButton b5=new JButton("search");
        JButton b6=new JButton("to file");
        JPanel p1=new JPanel();
        JLabel l1=new JLabel("Student Name");
        JLabel l2=new JLabel("ID");
        JLabel l3=new JLabel();
        JLabel l4=new JLabel("enter id");
        JTextField t1=new JTextField();
        JTextField t2=new JTextField();
        JTextField t3=new JTextField();
        String []nn={"communication","computer science","electric power","archeteacher"};
         JComboBox <String> cb=new<String>JComboBox(nn);
         String[]columname={"student name","ID","major"};
        Object[][]data={};
        DefaultTableModel model=new DefaultTableModel(data,columname);
        JTable table=new JTable(model);
        JScrollPane pane=new JScrollPane(table);
        JFileChooser fch=new JFileChooser();
            public StudentDataBase(boolean loadFromFile) {
        if (loadFromFile) {
            int result = fch.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fch.getSelectedFile();
                loadDataFromFile(selectedFile);
            }
        }
            }
        public void setstudentnum(){
            l3.setText("STUDENT NUMBER"+model.getRowCount());
        }
        public void loadDataFromFile(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 3) {
                model.addRow(new Object[]{parts[0], parts[1], parts[2]});
            }
        }
        setstudentnum(); // تحديث عدد الطلاب بعد التحميل
        } catch (IOException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error reading from file.");
         }
        }
        public StudentDataBase(){
        int option = JOptionPane.showConfirmDialog(null, "Do you want to load data from a file?", "Load Data", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
        int result = fch.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
        File selectedFile = fch.getSelectedFile();
        loadDataFromFile(selectedFile);
            }
            }
        l3.setBounds(0, 320, 200, 40);
        p1.setBounds(0, 0, 300, 400);
        p1.setLayout(null);
        l1.setBounds(0, 0, 60, 30);
        l2.setBounds(0, 50, 60, 30);
        t1.setBounds(80, 0, 200, 30);
        t2.setBounds(80, 50, 200, 30);
        b1.setBounds(0, 250, 80, 30);
        b2.setBounds(100, 250, 80, 30);
        b3.setBounds(200, 250, 80, 30);
        b4.setBounds(0, 300, 80, 30);
        b5.setBounds(100, 300, 80, 30);
        b6.setBounds(200, 300, 80, 30);
        l4.setBounds(0, 150, 60, 30);
        t3.setBounds(80, 150, 50, 30);
        p1.add(l4);p1.add(t3);p1.add(b6);
        p1.add(b2);p1.add(b3);p1.add(b4);p1.add(l3);p1.add(b5);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setBounds(100, 100, 800, 400);
        p1.add(l1);p1.add(t1);p1.add(l2);p1.add(t2);
        frame.add(p1);
        p1.add(b1);
        p1.add(cb);
        cb.setBounds(0, 100, 70, 30);
        pane.setBounds(300, 0, 500, 400);
        frame.add(pane);
        b1.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e) {
            String name=t1.getText();
            String ID=t2.getText();
            String lager=(String)cb.getSelectedItem();
            if(!name.isEmpty()&&!ID.isEmpty()&&!lager.isEmpty()){
                model.addRow(new Object[]{name,ID,lager});
                setstudentnum();
                t1.setText("");t2.setText("");
            }
            else{
            JOptionPane.showMessageDialog(null, "Please fill the empty fields.");
            }
            
        
        }});
        b2.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e) {
            int rowselected =table.getSelectedRow();
            if(rowselected>=0){
                 int result=JOptionPane.showConfirmDialog(null,"are you sure to delete this row","DELETE",JOptionPane.YES_NO_CANCEL_OPTION);
               if(result==JOptionPane.YES_OPTION){
                model.removeRow(rowselected);
                setstudentnum();
               }
            }
            else{
                JOptionPane.showMessageDialog(null, "Please select a row to delete.");    
            }
        }});
        b3.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e) {
           int rowselected =table.getSelectedRow();
            if(rowselected>=0){
                model.setValueAt(t1.getText(), rowselected, 0);
                model.setValueAt(t2.getText(), rowselected, 1);
                model.setValueAt((String)cb.getSelectedItem(), rowselected, 2);
                setstudentnum();
                t1.setText("");t2.setText("");
            }
            else{
              JOptionPane.showMessageDialog(null, "Please select a row to update.");   
            }
        }});
        b4.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e) {
        System.exit(0);
        }});
        b5.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e) {
          String sr=t3.getText();
          boolean f=false;
          for(int i=0;i<model.getRowCount();i++){
              String id=model.getValueAt(i, 1).toString();
              if(id.equals(sr)){
                  table.setRowSelectionInterval(i, i);
                  JOptionPane.showConfirmDialog(null, "Student found:"+model.getValueAt(i, 0));
                  f=true;
                  break;
              }}
         if(!f){
                  JOptionPane.showConfirmDialog(null, "student not found");
              }
         t3.setText("");
          
        }});
        b6.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        int result = fch.showSaveDialog(null); // استخدمي showSaveDialog للحفظ
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fch.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile))) {
                for (int i = 0; i < model.getRowCount(); i++) {
                    String name = model.getValueAt(i, 0).toString();
                    String id = model.getValueAt(i, 1).toString();
                    String major = model.getValueAt(i, 2).toString();
                    writer.write(name + "," + id + "," + major);
                    writer.newLine(); // يعمل سطر جديد
                }
                writer.flush(); // اختياري هنا لأن close() بيعمله
                JOptionPane.showMessageDialog(null, "Data saved successfully.");
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error saving to file.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No file selected.");
        }
    }
});
    
    
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.studentinformation;

/**
 *
 * @author IP
 */
public class StudentInformation {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        StudentDataBase d1=new StudentDataBase();
    }
