/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.furkanyaman.taskmanager;

/**
 *
 * @author Furkan
 */
public class Task {
    private String name; 
    private boolean isCompleted; 
    
    public Task(String name, boolean isCompleted){
        this.name = name; 
        this.isCompleted =isCompleted; 
    }
    
    public String getName(){
        return name; 
    }
    public boolean getBoolean(){
        return isCompleted; 
    }
    
    public void setComplated(boolean completed){
        this.isCompleted=completed; 
    }
}
