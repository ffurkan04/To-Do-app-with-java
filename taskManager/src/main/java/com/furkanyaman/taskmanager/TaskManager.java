/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.furkanyaman.taskmanager;

/**
 *
 * @author Furkan
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {

    JFrame frame;
    JPanel taskPanel; 
    JTextField taskInput; 
    List<JPanel> taskItems;
    SaveLoad saveload; 
    File file;  //File yol vermeyi unutma 
    
    public List<Task>tasks;
    
    public TaskManager(){
        tasks= new ArrayList<>();
        taskItems= new ArrayList<>();
         initalize();
        file = new File("task.json");
        
        
        saveload=new SaveLoad(file,this);
        saveload.loadFile();
       
        
        
        
    }
    
    
    public void initalize(){
        frame = new JFrame("To-do");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new BorderLayout());
        
        JPanel input = new JPanel();
        input.setLayout(new BorderLayout());
        taskInput = new JTextField();
        input.add(taskInput,BorderLayout.CENTER);
        
        JButton addButton = new JButton("Add Task");
        addButton.addActionListener(new addTaskActionListener());
        input.add(addButton,BorderLayout.EAST);
        
        frame.add(input,BorderLayout.NORTH);
        
        taskPanel = new JPanel();
        taskPanel.setLayout(new BoxLayout(taskPanel,BoxLayout.Y_AXIS));
        JScrollPane pane= new JScrollPane(taskPanel);
       
        
        frame.add(pane,BorderLayout.CENTER); 
        
        
        
        frame.setVisible(true);
        
        
    }
    
    private class addTaskActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            String taskName = taskInput.getText();
            if(!taskName.isEmpty()||!taskName.isBlank()){
                addTask(taskName,false);
                saveload.saveFile();
                taskInput.setText("");
            }
        }
    }
    
    public void addTask(String taskName,boolean isCompleted){
        //Burada isCompleted ı eklememin nedei daha sonrasında kayıt sistemi yaparken görevi geri yüklemek için olacak. 
        JPanel taskItem = new JPanel();  
        Task task = new Task(taskName,isCompleted);
        tasks.add(task);
        taskItem.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        JCheckBox taskBox = new JCheckBox("",isCompleted);
        taskBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                task.setComplated(taskBox.isSelected());
                saveload.saveFile(); 
            }
        });
        JLabel taskLabel = new JLabel(taskName); 
        
        JButton deleteButton = new JButton("Delete");
        
        deleteButton.addActionListener(new ActionListener(){
            
        @Override
        public void actionPerformed(ActionEvent e){
             
            taskPanel.remove(taskItem);
            taskItems.remove(taskItem);
            taskPanel.revalidate();
            taskPanel.repaint();
            tasks.remove(task);
            
            saveload.saveFile();
        }
        });
        taskItem.add(taskBox);
        taskItem.add(taskLabel);
        taskItem.add(deleteButton);
        
        taskItems.add(taskItem);
        taskPanel.add(taskItem);
        
        taskPanel.revalidate();
        taskPanel.repaint();
        
        
    }
    
    
    public static void main(String[] args) {  
        SwingUtilities.invokeLater(TaskManager::new);
    }
}
