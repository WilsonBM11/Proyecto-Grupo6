/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Duran Family
 */
public class LinkedStack implements Stack {

    private Node top;
    private int counter;

    public LinkedStack() {
        this.top = null;
        this.counter = 0;

    }

    @Override
    public int size() {
        return counter;
    }

    @Override
    public void clear() {
        this.top = null;
        this.counter = 0;
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }

    @Override
    public Object peek() throws StackException {
        if (isEmpty()) {
            throw new StackException("Linked Stack is empty");
        }
        return top.data;
    }

    @Override
    public Object top() throws StackException {
        if (isEmpty()) {
            throw new StackException("Linked Stack is empty");
        }
        return top.data;
    }

    @Override
    public void push(Object element) throws StackException {
        Node newNode = new Node(element);
        if (isEmpty()) {
            //Tenemos que crear un nuevo nodo
            this.top = newNode;
        } else {
            newNode.next = top;//Hacemos el enlace entre nodos
            top = newNode;//Le decimos a top que apunta a newNode
        }
        this.counter++;
    }

    @Override
    public String toString() {
        String result = "Linked Stack Content: \n";
        try {
            LinkedStack auxStack = new LinkedStack();
            while (!isEmpty()) {
                result += peek() + "\n";
                auxStack.push(pop());
            }
            //Se debe dejar la pila como al inicio
            while (!auxStack.isEmpty()) {
                push(auxStack.pop());
            }
        } catch (StackException ex) {
            Logger.getLogger(ArrayStack.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    @Override
    public Object pop() throws StackException {
        if (isEmpty()) {
            throw new StackException("Linked Stack is empty");
        }
        Object element = top.data;
        top = top.next;
        this.counter--;
        return element;
    }
    
    public boolean checkParenthesis(String expression) throws StackException{
        
        expression = expression.replaceAll("\\s", "");  //Limpia la cadena con la expresion aritmetica
        
        if(expression.charAt(0) == ')' || expression.charAt(expression.length() - 1) == '(') //Verifico que no inicie con parentesis cerrado o termine con parentesis cerrad0
            return false;
        else{
        LinkedStack s = new LinkedStack();
        for (int i = 0; i < expression.length(); i++) {
            if(expression.charAt(i) == '(')
                s.push(expression.charAt(i));
            else if(expression.charAt(i) == ')'){
                if(s.isEmpty())
                    return false;
                s.pop();
            }
        }
        return s.isEmpty();
        }
    }
    
    
}
