/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

/**
 *
 * @author Profesor Gilberth Chaves A <gchavesav@ucr.ac.cr>
 */
public class BTree implements Tree {
    private BTreeNode root; //representa la unica entrada al arbol

    //Constructor
    public BTree(){
        this.root = null;
    }
    
    @Override
    public BTreeNode getRoot(){
        return root;
    }
    
    @Override
    public int size() throws TreeException {
        if(isEmpty()){
            throw new TreeException("Binary Tree is empty");
        }
        return size(root);
    }
    
    private int size(BTreeNode node){
        if(node==null){
            return 0;
        }else
            return 1+size(node.left)+size(node.right);
    }

    @Override
    public void clear() {
        this.root = null;
    }

    @Override
    public boolean isEmpty() {
        return root==null;
    }

    @Override
    public boolean contains(Object element) throws TreeException {
        if(isEmpty())
            throw new TreeException("Binary Tree is empty");
        return binarySearch(root, element);
    }
    
    private boolean binarySearch(BTreeNode node, Object element){
        if(node==null){
            return false;
        }else
            if(util.Utility.equals(node.data, element)){
                return true;
            }else
                return binarySearch(node.left, element)||
                        binarySearch(node.right, element);
    }

    @Override
    public void add(Object element) {
        //this.root = add(this.root, element);
        this.root = add(this.root, element, "root");
    }
    
    private BTreeNode add(BTreeNode node, Object element){
        if(node==null){
            node = new BTreeNode(element);
        }else
            if(node.left==null){
                node.left = add(node.left, element);
            }else
                if(node.right==null){
                    node.right = add(node.right, element);
                }else{ //debemos establecer algun criterio para insertar
                    int value = util.Utility.random(99);
                    if(value%2==0){ //si el valor es par baje un nivel por la izq
                        node.left = add(node.left, element);
                    }else
                        node.right = add(node.right, element);
                }
        return node;
    }
    
     private BTreeNode add(BTreeNode node, Object element, String label){
        if(node==null){
            node = new BTreeNode(element, label);
        }else{ //debemos establecer algun criterio para insertar
            int value = util.Utility.random(99);
            if(value%2==0){ //si el valor es par baje un nivel por la izq
                node.left = add(node.left, element, label+"/left");
            }else
                node.right = add(node.right, element, label+"/right");
            }
        return node;
    }

    @Override
    public void remove(Object element) throws TreeException {
        if(isEmpty())
            throw new TreeException("Binary Tree is empty");
        root = remove(root, element);
    }
    
    private BTreeNode remove(BTreeNode node, Object element){
        if(node!=null){
            if(util.Utility.equals(node.data, element)){
                //Caso 1. El nodo a suprimir no tiene hijos
                if(node.left==null && node.right==null){
                   return null;
                }else
                //Caso 2. El nodo a suprimir solo tiene un hijo
                if(node.left!=null && node.right==null){
                    return node.left;
                }else
                if(node.left==null && node.right!=null){
                    return node.right;
                }else
                //Caso 3. El nodo a suprimir tiene dos hijos
                if(node.left!=null && node.right!=null){
                    Object value = getLeaf(node.right);
                    node.data = value;
                    node.right = removeLeaf(node.right, value);
                }
            }
            node.left = remove(node.left, element);
            node.right = remove(node.right, element);
        }
        return node;
    }
    
   /**
     * Obtiene hoja
     * @param node
     * @return 
     */
    private Object getLeaf(BTreeNode node) {
        Object aux;
        if(node==null)
            return null;
	else
            //si es una hoja
            if(node.left==null && node.right==null){
		return node.data; //es una hoja
            }else{
                aux = getLeaf(node.left);
                if(aux==null){
                    aux = getLeaf(node.right);
                }
            }
        return aux;
    }
    
    
    /**
     * Remueve hoja
     * @param node
     * @param value
     * @return 
     */
    private BTreeNode removeLeaf(BTreeNode node, Object value) {
        if(node==null)
            return null;
	else
            if(node.left==null && node.right==null 
                    &&util.Utility.equals(node.data, value))
		return null; //es una hoja y la elimina
            else{
                node.left = removeLeaf(node.left, value);
                node.right = removeLeaf(node.right, value);
            }
        return node;
    }

    @Override
    public int height(Object element) throws TreeException {
        if(isEmpty())
            throw new TreeException("Binary Tree is empty");
        return height(this.root, element, 0);
    }
    
    private int height(BTreeNode node, Object element, int counter){
        if(node==null){
            return 0;
        }else
            if(util.Utility.equals(node.data, element)){
                return counter;
            }else
                //debemos buscar por el subarbol izq y der
                return Math.max(height(node.left, element, ++counter), 
                                height(node.right, element, counter));
    }

    @Override
    public int height() throws TreeException {
        if(isEmpty())
            throw new TreeException("Binary Tree is empty");
        return height(root)-2;
    }
    
    private int height(BTreeNode node){
        if(node==null){
            return 0;
        }else
            //debemos buscar por el subarbol izq y der
            return Math.max(height(node.left), height(node.right))+1;
    }

    @Override
    public Object min() throws TreeException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Object max() throws TreeException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String preOrder() throws TreeException {
        if(isEmpty())
            throw new TreeException("Binary Tree is empty");
        return "Pre Order: "+preOrder(this.root);
    }
    
    //Transversal Tour: N-L-R
    private String preOrder(BTreeNode node){
        String result="";
        if(node!=null){
            //result=node.data+", ";
            result=node.data+", ";
            result+=preOrder(node.left);
            result+=preOrder(node.right);
        }
        return result;
    }

    @Override
    public String inOrder() throws TreeException {
        if(isEmpty())
            throw new TreeException("Binary Tree is empty");
        return "In Order: "+InOrder(this.root);
    }
    
    //Transversal Tour: L-N-R
    private String InOrder(BTreeNode node){
        String result="";
        if(node!=null){
            result=InOrder(node.left);
            result+=node.data+", ";
            result+=InOrder(node.right);
        }
        return result;
    }

    @Override
    public String postOrder() throws TreeException {
        if(isEmpty())
            throw new TreeException("Binary Tree is empty");
        return "Post Order: "+postOrder(this.root);
    }
    
    //Transversal Tour: L-R-N
    private String postOrder(BTreeNode node){
        String result="";
        if(node!=null){
            result=postOrder(node.left);
            result+=postOrder(node.right);
            result+=node.data+", ";  
        }
        return result;
    }
    
    
    
    //inOrden: recorre el árbol de la forma: izq-nodo-der
    //postOrden: recorre el árbol de la forma: izq-der-nodo
    @Override
    public String toString() {
        if(isEmpty())
            return "Binary Tree is empty";
        String result = "BINARY TREE TOUR...\n";
        result+="PreOrder Transversal Tour: "+preOrder(root)+"\n";
        result+="InOrder Transversal Tour: "+InOrder(root)+"\n";
        result+="PostOrder Transversal Tour: "+postOrder(root)+"\n";
        return result;
    }
    
}
