/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package domain;

/**
 *
 * @author Profesor Gilberth Chaves A <gchavesav@ucr.ac.cr>
 */
public interface Tree {
     //devuelve el número de elementos en el árbol
    public int size() throws TreeException; 
    //private int size(BTreeNode nodo)
    
    //anula el árbol
    public void clear();
    
    //true si el árbol está vacío
    public boolean isEmpty(); 
    
    //true si el elemento existe en el árbol
    public boolean contains(Object element) throws TreeException; 
    //private boolean binarySearch(BTreeNode node, Object element)

    //inserta un elemento en el árbol
    public void add (Object element); 
    //private BTreeNode add(BtreeNode node, Object element)

    //suprime un elemento del árbol
    //Caso 1. El nodo a suprimir no tiene hijos
    //Caso 2. El nodo a suprimir solo tiene un hijo
    //Caso 3. El nodo a suprimir tiene dos hijos
    public void remove(Object element) throws TreeException; 
    //private BTreeNode remove(BTreeNode node, Object element)

    //devuelve la altura de un nodo (el número de ancestros)
    public int height(Object element) throws TreeException; 
    //private int height(BTreeNode node, Object element)
    
    //devuelve la altura del árbol (altura máxima de la raíz a 
    //cualquier hoja del árbol)
    public int height() throws TreeException; 
    //private int height(BTreeNode node)
    
    //devuelve el valor mínimo contenido en el árbol
    public Object min() throws TreeException; 
    //private Object min(BTreeNode node)
    
    //devuelve el valor máximo contenido en el árbol
    public Object max() throws TreeException; 
    //private Object max(BTreeNode node)
    
    //recorre el árbol de la forma: nodo-hijo izq-hijo der, 
    //para mostrar todos los elementos existentes
    public String preOrder() throws TreeException; 
    //private String preOrder(BTreeNode node)
    
    //recorre el árbol de la forma: left-node-right,
    //para mostrar todos los elementos existentes en el arbol
    public String inOrder() throws TreeException;
    //Tranversal Tour: L-N-R
    //private String InOrder(BTreeNode node)
    
    ////recorre el árbol de la forma: left-right-node, 
    //para mostrar todos los elementos existentes
    public String postOrder() throws TreeException; 
    
    //private String postOrder(BTreeNode node)
    
    public BTreeNode getRoot();
}
