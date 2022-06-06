/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Profesor Gilberth Chaves A <gchavesav@ucr.ac.cr>
 */
public class PriorityLinkedQueue implements Queue{
    private Node front; //apuntador al anterior
    private Node rear; //apuntador a posterior
    private int counter; //contador de elementos encolados

    //Constructor
    public PriorityLinkedQueue() {
        this.front = this.rear = null;
        this.counter = 0;
    }

    @Override
    public int size() {
        return counter;
    }

    @Override
    public void clear() {
        this.front = this.rear = null;
        this.counter = 0;
    }

    @Override
    public boolean isEmpty() {
        return front==null;
    }

    @Override
    public int indexOf(Object element) throws QueueException {
        if(isEmpty())
            throw new QueueException("Linked Queue is Empty");
        PriorityLinkedQueue aux = new PriorityLinkedQueue();
        int i = 0; //index
        int j = -1; //si es -1 no existe
        try {
            while(!isEmpty()){
                if(util.Utility.equals(front(), element)){
                    j = i;
                }
                aux.enQueue(deQueue());
                i++;
            }
            //al final dejamos la cola en su estado original
            while(!aux.isEmpty()){
                enQueue(aux.deQueue());
            }
                
        } catch (QueueException ex) {
            Logger.getLogger(ArrayQueue.class.getName()).log(Level.SEVERE, null, ex);
        }
        return j; //retorna -1 si el elemento no fue encolado
    }

    @Override
    public void enQueue(Object element) throws QueueException {
        Node newNode = new Node(element);
        if(isEmpty()){
            rear = newNode;
            //garantizo q anterior quede apuntando al primer nodo
            front = rear;
        }else{
            rear.next = newNode;
            rear = newNode;
        }
        //al final actualizo el counter
        counter++;
    }
    
    /**
     * @param element     
     * @param priority 1=low, 2=medium, 3=high     
     * @throws domain.QueueException     
     */
    public void enQueue(Object element, Integer priority) throws QueueException {
        Node newNode = new Node(element, priority);
        if(isEmpty()){
            rear = newNode;
            //garantizo q anterior quede apuntando al primer nodo
            front = rear;
        }else{
            Node aux = front;
            Node prev = front; //dejar rastro
            while(aux!=null&&aux.priority>=priority){
                prev = aux; //dejar rastro
                aux = aux.next;
            }
            //se sale cuando alcanza nulo o la prioridad del elemento a encolar es mayor
            if(aux==front){ //caso 0. significa que el elemento a encolar va de primero
                newNode.next = front;
                front = newNode;
            }else
                if(aux==null){ //caso1. cuando alcanza nulo
                    prev.next = newNode;
                    rear = newNode;
                }else{ //caso. para todo lo demas
                    prev.next = newNode;
                    newNode.next = aux;
                }
        }
        //al final actualizo el counter
        counter++;
    }

    @Override
    public Object deQueue() throws QueueException {
        if(isEmpty())
            throw new QueueException("Linked Queue is Empty");
        Object element = front.data;
        //Caso1. cuando solo hay un elemento
        if(front==rear){
            clear(); //anulo la cola
        }else{ //Caso2. Hay mas de un elemento
            front = front.next;
            counter--; //actulizo el contador de elementos encolados
        }
        return element;
    }

    @Override
    public boolean contains(Object element) throws QueueException {
        if(isEmpty())
            throw new QueueException("Linked Queue is Empty");
        PriorityLinkedQueue aux = new PriorityLinkedQueue();
        boolean finded = false; //encontrado
        try {
            while(!isEmpty()){
                if(util.Utility.equals(front(), element)){
                    finded = true;
                }
                aux.enQueue(deQueue());
            }
            //al final dejamos la cola en su estado original
            while(!aux.isEmpty()){
                enQueue(aux.deQueue());
            }
                
        } catch (QueueException ex) {
            Logger.getLogger(ArrayQueue.class.getName()).log(Level.SEVERE, null, ex);
        }
        return finded;
    }

    @Override
    public Object peek() throws QueueException {
        if(isEmpty())
            throw new QueueException("Linked Queue is Empty");
        return front.data;
    }

    @Override
    public Object front() throws QueueException {
        if(isEmpty())
            throw new QueueException("Linked Queue is Empty");
        return front.data;
    }
    
    @Override
    public String toString() {
        String result = "Linked Queue Content: \n";
        PriorityLinkedQueue aux = new PriorityLinkedQueue();
        try {
            while(!isEmpty()){
                    result+=front()+"\n";
                    aux.enQueue(deQueue());
            }
            //al final dejamos la cola en su estado original
            while(!aux.isEmpty()){
                enQueue(aux.deQueue());
            }
                
        } catch (QueueException ex) {
            Logger.getLogger(ArrayQueue.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
}
