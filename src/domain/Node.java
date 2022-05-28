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
    public Node next; //apuntador al sgte node
    public Node prev; //apuntador al nodo anterior
    
    //Constructor
    public Node(Object data){
        this.data = data;
        this.prev = this.next = null;
    }
}
