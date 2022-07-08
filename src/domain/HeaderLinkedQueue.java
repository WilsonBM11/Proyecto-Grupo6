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
public class HeaderLinkedQueue implements Queue{
    private Node front; //apuntador al anterior
    private Node rear; //apuntador a posterior
    private int counter; //contador de elementos encolados

    //Constructor
    public HeaderLinkedQueue() {
        this.front = this.rear = new Node();
        this.counter = 0;
    }

    @Override
    public int size() {
        return counter;
    }

    @Override
    public void clear() {
        this.front = this.rear = new Node();
        this.counter = 0;
    }

    @Override
    public boolean isEmpty() {
        return front==rear;
    }

    @Override
    public int indexOf(Object element) throws QueueException {
        if(isEmpty())
            throw new QueueException("Header Linked Queue is Empty");
        HeaderLinkedQueue aux = new HeaderLinkedQueue();
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
        rear.next = newNode;
        rear = newNode;
        //al final actualizo el counter
        counter++;
    }

    @Override
    public Object deQueue() throws QueueException {
        if(isEmpty())
            throw new QueueException("Header Linked Queue is Empty");
        Object element = front.next.data;
        //Caso1. cuando solo hay un elemento
        if(front.next==rear){
            clear(); //anulo la cola
        }else{ //Caso2. Hay mas de un elemento
            front.next = front.next.next;
            counter--; //actulizo el contador de elementos encolados
        }
        return element;
    }

    @Override
    public boolean contains(Object element) throws QueueException {
        if(isEmpty())
            throw new QueueException("Header Linked Queue is Empty");
        HeaderLinkedQueue aux = new HeaderLinkedQueue();
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
            throw new QueueException("Header Linked Queue is Empty");
        return front.next.data;
    }

    @Override
    public Object front() throws QueueException {
        if(isEmpty())
            throw new QueueException("Linked Queue is Empty");
        return front.next.data;
    }
    
    @Override
    public String toString() {
        String result = "Header Linked Queue Content: \n";
        HeaderLinkedQueue aux = new HeaderLinkedQueue();
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
        public void remove(Object element){
        HeaderLinkedQueue aux = new HeaderLinkedQueue();
        try {
            while (!isEmpty()) {
                if (util.Utility.equals(front(), element)) {
                    deQueue();
                } else {
                    aux.enQueue(deQueue());
                }
            }
            while(!aux.isEmpty()){
                enQueue(aux.deQueue());
            }
        } catch (QueueException ex) {
            Logger.getLogger(HeaderLinkedQueue.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
         public Queue replace(Queue q, Object obj1, Object obj2) throws QueueException {
        if (q.isEmpty()) {
            throw new QueueException("Queue is empty");
        }
        LinkedQueue aux = new LinkedQueue();
        if(q.contains(obj1)){
            while(!q.isEmpty()){
                if(util.Utility.equals(q.front(), obj1)){
                    q.deQueue();
                    aux.enQueue(obj2);
                }else
                    aux.enQueue(q.deQueue());
               
            }
            while(!aux.isEmpty()){
                q.enQueue(aux.deQueue());
            }
        }
        
        return q;
                    
    }
    
}
