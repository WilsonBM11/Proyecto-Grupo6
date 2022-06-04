/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

/**
 *
 * @author Profesor Gilberth Chaves A <gchavesav@ucr.ac.cr>
 */
public class CircularDoublyLinkedList implements List {
    private Node first; //apunta al inicio de la lista
     private Node last; //apunta al ult nodo de la lista

    //Constructor
    public CircularDoublyLinkedList(){
        this.first = this.last = null;
    }

    @Override
    public int size() throws ListException {
        if(isEmpty())
            throw new ListException("Circular Doubly Linked List is empty");
        Node aux = first;
        int counter = 0;
        while(aux!=last){
           counter++;
           aux = aux.next;
        }
        //sale del while cuando aux esta en el ult nodo
        return counter+1;
    }

    @Override
    public void clear() {
        this.first = this.last = null;
    }

    @Override
    public boolean isEmpty() {
        return first==null;
    }

        @Override
    public boolean contains(Object element) throws ListException {
         if(isEmpty())
            throw new ListException("Circular Doubly Linked List is empty");
        Node aux = first;
        while(aux!=last){
            if(util.Utility.equals(aux.data, element))
                return true;
           aux = aux.next;
        }
        //sale del while cuando aux esta en el ult nodo
        return util.Utility.equals(aux.data, element);
    }

    @Override
    public void add(Object element) {
        Node newNode = new Node(element);
        if(isEmpty()){
            first = last = newNode;
        }
        else{
            last.next = newNode;
            last = newNode;
            
            //hago el enlace circular
            last.next = first;
            //hago el doble enlace
            first.prev = last;
        }
    }

    @Override
    public void addFirst(Object element) {
        Node newNode = new Node(element);
        if(isEmpty())
            first = last = newNode;
        else{
           newNode.next = first; 
           first = newNode;
        }
        //hago el enlace circular y doble
        last.next = first;
        first.prev = last;
    }

    @Override
    public void addLast(Object element) {
        add(element);
    }

    @Override
    public void addInSortedList(Object element) {
        Node newNode = new Node(element);
        if(isEmpty())
            first = last = newNode;    
            else{
                if(util.Utility.lessT(newNode.data, first.data,util.Utility.getCriterioOrdenamiento()))
                    addFirst(element);
                else if(util.Utility.greaterT(newNode.data, last.data,util.Utility.getCriterioOrdenamiento()))
                    addLast(element);
                else{
                Node prev = first;
                Node aux = first.next;
                while(aux != last && util.Utility.greaterT(newNode.data, aux.data,util.Utility.getCriterioOrdenamiento())){
                    prev = aux;
                    aux = aux.next;
                }
                prev.next = newNode;
                newNode.next = aux;
                //Se hace el doble enlace
                newNode.prev = prev;
                aux.prev = newNode;
                }
                
            }
    }

    @Override
    public void remove(Object element) throws ListException {
        if(isEmpty())
            throw new ListException("Circular Doubly Linked List is empty");
        //caso 1. El elemento a suprimir es el primero
        if(util.Utility.equals(first.data, element)){
            first = first.next;
        }  
        //case 2. El elemento a suprimir puede estar donde sea
        else{
            Node prev = first; //elemento anterior
            Node aux = first.next; //elemento sgte
            while(aux!=last&&!util.Utility.equals(aux.data, element)){
                prev = aux; //actualizo anterior
                aux = aux.next;
            }
            //se sale cuando alcanza aux==last
            //o cuando encuentra el elemento a suprimir
            if(util.Utility.equals(aux.data, element)){
                //desenlanza el nodo
                prev.next = aux.next;
                //mantengo el doble enlace
                aux.next.prev = prev; //opcion 1
                //otra alternativa, opcion 2
                //Node aux2 = aux.next;
                //aux2.prev = prev;
                
                //debo asegurarme q last apunte al ultimo nodo
                //entendiendo que aux esta en el nodo a suprimir
                if(aux==last){ //estamos en el ultimo nodo
                    last=prev;
                }
            }
        }
        //mantengo el enlace circular y doble
        last.next = first;
        first.prev = last;
        //q pasa si solo queda un nodo
        //y es el q quiero eliminar
        if(first==last&&util.Utility.equals(first.data, element)){
            clear(); //anulo la lista
        }
    }


    @Override
    public Object removeFirst() throws ListException {
        if(isEmpty())
            throw new ListException("Circular Doubly Linked List is empty");
        Object element = first.data;
        first = first.next;
        //hago el enlace circular y doble
        last.next = first;
        first.prev = last;
        return element;
    }

    @Override
    public Object removeLast() throws ListException {
        if(isEmpty())
            throw new ListException("Circular Doubly Linked List is empty");
        else{
            Object element = last.data;
            Node prev = first;
            Node aux = first.next;
            while(aux != last){
                prev = aux;
                aux = aux.next;
            }
            prev.next = first;
            first.prev = prev;
            last = prev;
            return element;
        }
    }

    @Override
    public void sort() throws ListException {
        if(isEmpty())
            throw new ListException("Circular Doubly Linked List is empty");
        for(int i=1;i<=size();i++){
    	     for(int j=i+1;j<=size();j++){
    		if(util.Utility.lessT(getNode(j).data, getNode(i).data,util.Utility.getCriterioOrdenamiento())){
    		   Object aux=getNode(i).data;
                    getNode(i).data=getNode(j).data;
    		    getNode(j).data=aux;
    		}//if
            }//for j
        }//for i
    }

    @Override
    public int indexOf(Object element) throws ListException {
        if(isEmpty()){
            throw new ListException("Circular Doubly Linked List is empty");
        }
        Node aux = first;
        int index=1;
        while(aux!=last){
            if(util.Utility.equals(aux.data, element)){
                return index;
            }
            index++;
            aux = aux.next; //lo movemos al sgte nodo
        }
        //se sale cuando aux apunta a last (al ultimo nodo)
        if(util.Utility.equals(aux.data, element)){
            return index;
        }
        return -1; //indica q el elemento no existe
    }

    @Override
    public Object getFirst() throws ListException {
        if(isEmpty())
            throw new ListException("Circular Doubly Linked List is empty");
        return first.data;
    }

    @Override
    public Object getLast() throws ListException {
        if(isEmpty()){
            throw new ListException("Circular Doubly Linked List is empty");
        }
        return last.data; //es el ultimo en la lista
    }

    @Override
    public Object getPrev(Object element) throws ListException {
        if(isEmpty())
            throw new ListException("Circular Linked List is empty");
        else
            if(util.Utility.equals(first.data, element))
                return last.data;
            else{
            Node prev = first;
            Node aux = first.next;
            while(aux != last && !util.Utility.equals(aux.data, element)){
                prev = aux;
                aux = aux.next;
            }
            if(util.Utility.equals(aux.data, element))
                return prev.data;
            
            return null;
        }
    }

    @Override
    public Object getNext(Object element) throws ListException {
        if(isEmpty())
            throw new ListException("Circular Linked List is empty");
        else{
            Node prev = first;
            Node aux = first.next;
            while(prev != last && !util.Utility.equals(prev.data, element)){
                prev = aux;
                aux = aux.next;
            }
            
            if(util.Utility.equals(prev.data, element))
                return aux.data;
            
            return null;
        }
    }

    @Override
    public Node getNode(int index) throws ListException {
        if(isEmpty()){
            throw new ListException("Circular Doubly Linked List is empty");
        }
        Node aux = first;
        int i = 1; //posicion del primer nodo
        while(aux!=last){
            if(util.Utility.equals(i, index)){
                return aux;
            }
            i++;
            aux = aux.next; //lo movemos al sgte nodo
        }
        //se sale cuando aux==last
        if(util.Utility.equals(i, index)){
            return aux;
        }
        return null; //si llega aqui no encontro el nodo
    }
      public Object getNodeById(Object element) throws ListException{
        if(isEmpty())
            throw new ListException("Doubly Linked List is empty");
        Node aux = first;
        while(aux!=null&&!util.Utility.equals(aux.data, element)){
            aux = aux.next;
        }
        //sale cuando aux==null o encontro el elemento
        if(aux!=null&&util.Utility.equals(aux.data, element)){
            return aux.data;
        }
        return null; //el elemento no existe
    }

    @Override
    public String toString() {
        String result = "Circular Doubly Linked List Content\n";
        Node aux = first;
        while(aux!=last){
           result+=aux.data+"\n";
           aux = aux.next;
        }
        //agregamos la info del ultimo nodo
        return result+"\n"+aux.data;
    }
    
    
    
}
