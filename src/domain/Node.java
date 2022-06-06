/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

/**
 *
 * @author Profesor Gilberth Chaves A <gchavesav@ucr.ac.cr>
 */
public class Node {
    public Object data;
    public Integer priority; //1=low, 2=medium, 3=high
    public Node next; //apuntador al sgte node
    public Node prev;
    
    //Constructor
    public Node(Object data){
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    //Constructor sobrecargado
    public Node() {
        this.next = null;
    }
    
    //Constructor sobrecargado
    public Node(Object data, Integer priority) {
        this.data = data;
        this.priority = priority;
        this.next = null;
    }
    
}
