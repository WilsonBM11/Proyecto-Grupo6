/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

/**
 *
 * @author Profesor Gilberth Chaves A <gchavesav@ucr.ac.cr>
 */
public class AVL implements Tree {
    private BTreeNode root; //representa la unica entrada al arbol

    //Constructor
    public AVL(){
        this.root = null;
    }
    
    public BTreeNode getRoot(){
        return root;
    }
    
    @Override
    public int size() throws TreeException {
        if(isEmpty()){
            throw new TreeException("AVL Binary Search Tree is empty");
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
            throw new TreeException("AVL Binary Tree is empty");
        return binarySearch(root, element);
    }
    
    private boolean binarySearch(BTreeNode node, Object element){
        if(node==null){
            return false;
        }else
            if(util.Utility.equals(node.data, element)){
                return true;
            }else
                if(util.Utility.lessT(element, node.data))
                    return binarySearch(node.left, element);
                else return binarySearch(node.right, element);
    }

    @Override
    public void add(Object element) {
        this.root = add(this.root, element, "root");
    }
    
    private BTreeNode add(BTreeNode node, Object element, String sequence){
        if(node==null){
            node = new BTreeNode(element, sequence);
        }else
            if(util.Utility.lessT(element, node.data))
                node.left = add(node.left, element, sequence+"/left");
            else
            if(util.Utility.greaterT(element, node.data))  
                    node.right = add(node.right, element, sequence+"/right");
        
        //get balance factor
        int balance = getBalanceFactor(node);
        //si el nodo queda desequilibrado, entonces debemos equilibrar
        //nuevamente el arbol. Tenemos 4 casos:
        
        //Caso 1. Left Left Case
        if(balance > 1 &&util.Utility.lessT(element, node.left.data)){
            node.sequence = sequence+"/Simple Right Rotate";
            return rightRotate(node);
        }
        //Caso 2. Right Right Case
        if(balance < -1 &&util.Utility.greaterT(element, node.right.data)){
            node.sequence = sequence+"/Simple Left Rotate";
            return leftRotate(node);
        }
        //Caso 3. Left Right Case
         if(balance > 1 &&util.Utility.greaterT(element, node.left.data)){
            node.sequence = sequence+"/Double Left/Right Rotate";
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        //Caso 4. Right Left Case
        if(balance < -1 &&util.Utility.lessT(element, node.right.data)){
            node.sequence = sequence+"/Double Right/Left Rotate";
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
            
        return node;
    }
    
    private int getBalanceFactor(BTreeNode node){
        if(node==null){
            return 0;
        }else
            return height(node.left) - height(node.right);
    }
    
    private BTreeNode leftRotate(BTreeNode node) {
	BTreeNode node1 = node.right;
        	BTreeNode node2 = node1.left;
        	//se realiza la rotacion (perform rotation)
	node1.left = node;
        	node.right = node2;
	return node1;
    }
    
    private BTreeNode rightRotate(BTreeNode node) {
	BTreeNode node1 = node.left;
        	BTreeNode node2 = node1.right;
	//se realiza la rotacion (perform rotation)
	node1.right = node;
	node.left = node2;
	return node1;
    }

    @Override
    public void remove(Object element) throws TreeException {
        if(isEmpty())
            throw new TreeException("AVL Binary Tree is empty");
        root = remove(root, element);
    }
    
    private BTreeNode remove(BTreeNode node, Object element){
        if(node!=null){
            if(util.Utility.lessT(element, node.data))
                node.left = remove(node.left, element);
            else
            if(util.Utility.greaterT(element, node.data))
                node.right = remove(node.right, element);
            else
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
                    Object min = min(node.right);
                    node.data = min;
                    node.right = remove(node.right, min);
                }
            } 
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

    @Override
    public int height(Object element) throws TreeException {
        if(isEmpty())
            throw new TreeException("AVL Binary Search Tree is empty");
        return height(this.root, element, 0);
    }
    
    private int height(BTreeNode node, Object element, int counter){
        if(node==null){
            return 0;
        }else
            if(util.Utility.equals(node.data, element)){
                return counter;
            }else
            if(util.Utility.lessT(element, node.data)){
                return height(node.left, element, ++counter);
            }
            else   return height(node.right, element, ++counter);
    }

    @Override
    public int height() throws TreeException {
        if(isEmpty())
            throw new TreeException("AVL Binary Search Tree is empty");
        return height(root)-1;
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
        if(isEmpty())
            throw new TreeException("AVL Binary Search Tree is empty");
        return min(root);
    }
    
    private Object min(BTreeNode node){
        if(node.left!=null)
            return min(node.left);
        return node.data; //ya estamos en la hoja q represeta el min
    }

    @Override
    public Object max() throws TreeException {
        if(isEmpty())
            throw new TreeException("AVL Binary Search Tree is empty");
        return max(root);
    }
    
     private Object max(BTreeNode node){
        if(node.right!=null)
            return max(node.right);
        return node.data; //ya estamos en la hoja q represeta el max
    }

    @Override
    public String preOrder() throws TreeException {
        if(isEmpty())
            throw new TreeException("AVL Binary Tree is empty");
        return "PreOrder Transversal Tour: "+preOrder(this.root);
    }
    
    //Transversal Tour: N-L-R
    private String preOrder(BTreeNode node){
        String result="";
        if(node!=null){
            result=node.data+", ";
            result+=preOrder(node.left);
            result+=preOrder(node.right);
        }
        return result;
    }

    @Override
    public String inOrder() throws TreeException {
        if(isEmpty())
            throw new TreeException("AVL Binary Tree is empty");
        return "InOrder Transversal Tour: "+InOrder(this.root);
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
            throw new TreeException("AVL Binary Tree is empty");
        return "PostOrder Transversal Tour: "+postOrder(this.root);
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
            return "AVL Binary Tree is empty";
        String result = "AVL TREE TRANSVERSAL TOUR...\n";
        result+="PreOrder: "+preOrder(root)+"\n";
        result+="InOrder: "+InOrder(root)+"\n";
        result+="PostOrder: "+postOrder(root)+"\n";
        return result;
    }
    
    public boolean isBalanced() throws TreeException{
         if(isEmpty())
            throw new TreeException("AVL Binary Search Tree is empty");
         return isBalanced(root);
    }

    private boolean isBalanced(BTreeNode node) {
        if(node==null){
            return true;
        }else
            return (getBalanceFactor(node)<2&&getBalanceFactor(node)>-2)
                 &&isBalanced(node.left)&&isBalanced(node.right);
    }
    
}
