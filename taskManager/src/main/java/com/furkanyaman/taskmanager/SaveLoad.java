/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.furkanyaman.taskmanager;

/**
 *
 * @author Furkan
 */

import java.io.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.List;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class SaveLoad {
    File file; 
    Gson gson; 
    TaskManager manager; 
    
    
    public SaveLoad(File file,TaskManager manager){
        this.file= file; 
        this.manager= manager;
        this.gson = new Gson(); 
        
    }
    public void saveFile(){
        try(Writer writer= new FileWriter(file)){
            gson.toJson(manager.tasks,writer);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    
    public void loadFile(){
        if(file.exists()){
            try(Reader reader= new FileReader(file)){
                Type taskListType= new TypeToken<ArrayList<Task>>(){}.getType(); 
                List<Task> tasks= gson.fromJson(reader,taskListType);
                
                if(tasks==null){
                    tasks=new ArrayList<>();
                }
                
                for(Task task: tasks){
                    manager.addTask(task.getName(), task.getBoolean());
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
    
    
}
