package rsrc;

import rsrc.TDABinarySearchTree;
import java.util.Scanner;

public class BinarySearchTree<K extends Comparable, T> implements TDABinarySearchTree<K, T>{

    /**nodo de un arbol binario*/
    public class BinaryNode{
	//clave del nodo
	private K key;
	//elemento del nodo
	private T element;
	//hijo derecho
	private BinaryNode nRight;
	//hijo izquierdo
	private BinaryNode nLeft;

	//metodo constructor
	public BinaryNode(T e, K clave){
	    this.key = clave;
	    this.element = e;
	    this.nRight = null;
	    this.nLeft = null;
	}

	/**
	 * metodo para definir al hijo derecho del nodo
	 * @param node - el nodo que será el hijo derecho
	 */
	public void setNR(BinaryNode node){
	    this.nRight = node;
	}

	/**
	 * metodo para definir al hijo izquierdo del nodo
	 * @param node - el nodo que será el hijo izquierdo 
	 */
	public void setNL(BinaryNode node){
	    this.nLeft = node;
	}

	/**
	 * metodo para obtener el nodo derecho
	 * @return - el hijo derecho de este nodo
	 */
	public BinaryNode getNR(){
	    return this.nRight;
	}	
	
	/**
	 * metodo para obtener el nodo izquierdo
	 * @return - el hijo izquierdo de este nodo
	 */
	public BinaryNode getNL(){
	    return this.nLeft;
	}
	
	/**
	 * metodo para obtener el elemento del nodo
	 * @return - el elemento de este nodo
	 */
	public T element(){
	    return this.element;
	}

	/**
	 * metodo para obtener la clave del nodo
	 */
	public K key(){
	    return this.key;
	}

	/**
	 * metodo para insetar un arbol en el nodo minimo de otro
	 * @param tree - arbol a insertar como hijo izquierdo a su elemento min
	 */
	public void setMinL(BinaryNode tree){
	    if(this.nLeft==null){
		this.nLeft = tree;
	    }else{
		this.nLeft.setMinL(tree);
	    }
	}

	/**
	 * metodo para insetar un arbol en el nodo máximo de otro
	 * @param tree - arbol a insertar como hijo derecho a su elemento max
	 */
	public void setMaxR(BinaryNode tree){
	    if(this.nRight==null){
		this.nRight = tree;
	    }else{
		this.nRight.setMaxR(tree);
	    }
	}

	@Override
	public String toString(){
	    String form = ""+this.element+"\n";

	    if(this.nLeft!=null){
		form+="izquierda: "+this.nLeft+"";
	    }
	    if(this.nRight!=null){
		form+="derecha: "+this.nRight+"\n";
	    }
	    
	    return form;
	}
	
    }

    //raiz del arbol
    private BinaryNode root;    

    //metodo constructor
    public BinarySearchTree(){
	this.root = null;
    }    
    
    /**
     * metodo que regresa un elemento asociado a una clave
     * @param k - clave a la que está asociado un elemento
     * @return - el elemento al que está asiciado k
     */    
    public T retrieve(K k){
	T e = null;
	
	if(this.root==null)
	    return null;
	    
	if(k.equals(this.root.key()))//si la clave es la clave de la raiz
	    return this.root.element();
	
	else if(k.compareTo(this.root.key())<0)//si la clave es menor al nodo
	    if(this.root.getNL()!=null)//si tiene hijo izquierdo
		e = retrieveAux(this.root.getNL(),k);
	    else//si no tiene hijo izquierdo
		return null;
	
	else//si la clave es mayor al nodo
	    if(this.root.getNR()!=null)//si tiene hijo derecho
		e = retrieveAux(this.root.getNR(),k);
	    else//si no tiene hijo derecho
		return null;
	    	   	    
	return e;
	
    }public T retrieveAux(BinaryNode aux,K clave){//metodo auxiliar
	
	if(clave.equals(aux.key()))//si la clave es igual a la clave del nodo
	    return aux.element();

	else if(clave.compareTo(aux.key())<0)//si la clave es menor al nodo
	    if(aux.getNL()!=null)//si el nodo tiene hijo izquierdo
		return retrieveAux(aux.getNL(),clave);
	    else//si el nodo no tiene hijo izquierdo
		return null;

	else//si la clame es mayor o igual al nodo
	    if(aux.getNR()!=null)//si el nodo tiene hijo derecho
		return retrieveAux(aux.getNR(),clave);
	    else//si el nodo no tiene hijo derecho
		return null;	
    }

    /**
     * metodo para insertar un elemento con una clave en el arbol
     * @param e - elemento dentro del nodo
     * @param clave - la clave asociada al nodo
     */
    public void insert(T e, K clave){
	if(this.root==null)
	    this.root = new BinaryNode(e,clave);
	else{
	    insertAux(this.root, e, clave);
	}
    }public void insertAux(BinaryNode aux,T e,K clave){//metodo auxiliar
	//las claves en ints
	//(int)clave<(int)aux.key()
	if(clave.compareTo(aux.key)<0)//si la clave pasada es menor a nodo aux
	    if(aux.getNL()==null)//si no tiene hijo izquierdo
		aux.setNL(new BinaryNode(e,clave));
	    else
		insertAux(aux.getNL(),e,clave);//si tiene hijo izquierdo
	
	if(clave.compareTo(aux.key)>=0)//si la clave pasada es mayor a nodo aux
	    if(aux.getNR()==null)//si no tiene hijo derecho
		aux.setNR(new BinaryNode(e,clave));
	    else
		insertAux(aux.getNR(),e,clave);//si tiene hijo derecho
    }
    
    /**
     * metodo para eliminar un elemento al que está asiciada la clave
     * @param k - clave a la que está asociado un elemento
     * @return - el elemento al que está asiciado k
     */    
    public T delete(K k){
	T e = null;
	if(this.root==null)//si el arbol es vacío
	    return null;
	    
	if(k.equals(this.root.key())){//si la clave es la misma que la raiz
	    if(this.root.getNL()==null){//si la raiz no tiene nodo izquierdo
		e = root.element();
		this.root = this.root.getNR();
		return e;
	    }else if(this.root.getNR()==null){// si la raíz no tiene nodod dere
		e = root.element();
		this.root = this.root.getNL();
		return e;
	    }
	    //si la raíz tiene ambos nodos
	    e = this.root.element();//elemento de la raiz
	    this.root.getNR().setMinL(this.root.getNL());
	    this.root = this.root.getNR();
	}else{//si la clave no es la de la raíz
	    e = deleteAux(this.root,k);
	}
	return e;
	
    }public T deleteAux(BinaryNode padre, K k){//metodo auxiliar	
	K cl = padre.key();//clave del nodo padre
	if(k.compareTo(cl)<0){//si la clave es menor al nodo padre
	    BinaryNode hi = padre.getNL();//hijo izquierdo del padre
	    if(hi==null){//si padre no tiene hijo izquierdo
		return null;
	    }else{//si padre si tiene hijo izquierdo
		K kHi = hi.key();//clave del hijo izquierdo
		if(kHi.equals(k)){//si la clave del hijo izquierdo ed igual a k
		    T deleted = hi.element();//elemento del nodo a eliminar
		    if(hi.getNR()==null){//si hijoIzq no tiene hijo derecho
			padre.setNL(hi.getNL());
			return deleted;
		    }else if(hi.getNL()==null){//si hijoIzq no tiene hijo izq
			padre.setNL(hi.getNR());
			return deleted;
		    }else{//si hijoIzq tiene ambos hijos
			hi.getNR().setMinL(hi.getNL());
			padre.setNL(hi.getNR());
			return deleted;
		    }
		}else{//si la clave del hijo izquierdo no es igual a k
		    return deleteAux(hi,k);
		}
	    }	    
	}else if (k.compareTo(cl)>=0){//si la clave es mayor al nodo padre
	    BinaryNode hd = padre.getNR();//hijo derecho del padre
	    if(hd==null){//si el padre no tiene hijo derecho
		return null;
	    }else{//si el padre si tiene hijo derecho
		K kHd = hd.key();//clave del hijo derecho
		if(kHd.equals(k)){//si la clave de hijoDer es igual a k
		    T deleted = hd.element();//elemento a eliminar
		    if(hd.getNR()==null){//si el hijoDer no tiene Hijo derecho
			padre.setNR(hd.getNL());
			return deleted;
		    }else if(hd.getNL()==null){//si el hijoDer no tiene HijoIzq
			padre.setNR(hd.getNR());
			return deleted;
		    }else{//si el hijo derecho tiene ambos hijos
			hd.getNL().setMaxR(hd.getNR());
			padre.setNR(hd.getNL());
			return deleted;
		    }
		}else{//si la clave del hijo derecho no es igual a k
		    return deleteAux(hd,k);
		}
	    }
	}/*else{//si la clave es igual al padre
	    T deleted = padre.element();
	    if(padre.getNR()==null){
		padre = padre.getNL();
		return deleted;
	    }else if(padre.getNL()==null){
		padre = padre.getNR();
		return deleted;
	    }else{
		padre.getNR().setMinL(padre.getNL());
		padre = padre.getNR();
		return deleted;
	    }
	    }*/
	return null;
    }
    
    /**
     * metodo que regresa el elemento con la menor clave del arbol
     * @return - el elemento con la menor clave del arbol
     */
    public T findMin(){
	if(this.root==null){//si el arbol es vacío
	    return null;
	}
	if(this.root.getNL()==null)//si la raiz no tiene hijo izquierdo
	    return this.root.element();
	else//si la raiz si tiene hijo izquierdo
	    return findMinAux(this.root.getNL());
	    
    }public T findMinAux(BinaryNode aux){//metodo auxiliar
	if(aux.getNL()==null)//si el nodo no tiene hijo izquierdo
	    return aux.element();
	else//si el nodo si tiene hijo izquierdo
	    return findMinAux(aux.getNL());
    }

    /**
     * metodo que regresa el elemento con la mayor clave del arbol
     * @return - el elemento con la mayor clave del arbol
     */    
    public T findMax(){
	if(this.root==null){
	    return null;
	}
	if(this.root.getNR()==null)//si la raiz no tiene hijo derecho
	    return this.root.element();
	else//si la raiz si tiene hijo derecho
	    return findMaxAux(this.root.getNR());
    }public T findMaxAux(BinaryNode aux){//metodo auxiliar
	if(aux.getNR()==null)//si el nodo no tiene hijo derecho
	    return aux.element();
	else//si el nodo si tiene hijo derecho
	    return findMaxAux(aux.getNR());
    }

    /**
     * metodo que verifica si un arbol está vacío
     * @return - true si está vacío, false si no
     */
    public boolean isEmpty(){
	if(this.root==null)
	    return true;
	else
	    return false;
    }
    
    /**metodo que regresa el preOrden del arbol*/
    public String preOrder(){
	if(isEmpty())//si el arbol es vacío
	    return "arbol vacío";
	//si el arbol no es vacío
        return preOrderAux(this.root);
    }public String preOrderAux(BinaryNode aux){
	String form = "";	
	form += aux.element()+",";
	if(aux.getNL()!=null)//si hay nodo izquierdo
	    form += preOrderAux(aux.getNL());
	if(aux.getNR()!=null)//si hay nodo derecho
	    form += preOrderAux(aux.getNR());
	return form;
    }
    
    /**metodo que regresa el inOrden del arbol*/
    public String inOrder(){
	if(isEmpty())//si el arbol es vacío
	    return "arbol vacío";
	//si el arbol no es vacío
        return inOrderAux(this.root);
    }public String inOrderAux(BinaryNode aux){
	String form = "";		
	if(aux.getNL()!=null)//si hay nodo izquierdo
	    form += inOrderAux(aux.getNL());
	
	form += aux.element()+",";
	
	if(aux.getNR()!=null)//si hay nodo derecho
	    form += inOrderAux(aux.getNR());
	return form;
    }

     /**metodo que regresa el inOrden del arbol en lista*/
    public String inOrderList(){
	if(isEmpty())//si el arbol es vacío
	    return "arbol vacío";
	//si el arbol no es vacío
        return inOrderListAux(this.root);
    }public String inOrderListAux(BinaryNode aux){
	String form = "";		
	if(aux.getNL()!=null)//si hay nodo izquierdo
	    form += inOrderListAux(aux.getNL());
	
	form += aux.element()+"\n";
	
	if(aux.getNR()!=null)//si hay nodo derecho
	    form += inOrderListAux(aux.getNR());
	return form;
    }
	
    /**metodo que regresa el posOrden del arbol*/
    public String posOrder(){
	if(isEmpty())//si el arbol es vacío
	    return "arbol vacío";
	//si el arbol no es vacío
	return posOrderAux(this.root);
    }public String posOrderAux(BinaryNode aux){
	String form = "";		
	if(aux.getNL()!=null)//si hay nodo izquierdo
	    form += posOrderAux(aux.getNL());       	
	if(aux.getNR()!=null)//si hay nodo derecho
	    form += posOrderAux(aux.getNR());

	form += aux.element()+",";
	
	return form;
    }    
    
    /**metodo para mostrar el arbol*/
    public void show(){
	System.out.println(this.root);
    }
    
    public static void main(String[] holi){
	BinarySearchTree tree = new BinarySearchTree();
	Scanner rdLine = new Scanner(System.in);
	Scanner rdInt = new Scanner(System.in);
	int x=0;
	int error = 0;
	int clave = 0;
	do{
	    System.out.println("1 - Retrieve");
	    System.out.println("2 - Insert");
	    System.out.println("3 - Delete");
	    System.out.println("4 - FindMin");
	    System.out.println("5 - FindMax");
	    System.out.println("6 - isEmpty");
	    System.out.println("7 - Recorridos");
	    System.out.println("0 - salir");
	    do{
		try{
		    x=rdInt.nextInt();
		    error = 0;
		}catch(Exception e){
		    System.out.println("parametro invalido");
		    rdInt.nextLine();
		    error = 1;
		}
	    }while(error==1);

	    switch(x){
		
	    case 1://retrieve
		System.out.println("clave: ");
		do{
		    try{
			clave = rdInt.nextInt();
			error = 0;
		    }catch(Exception e){
			System.out.println("parametro invalido");
			rdInt.nextLine();
			error = 1;
		    }
		}while(error==1);
		System.out.println(tree.retrieve(clave)+"\n");
		System.out.println("presiona Enter");
		rdLine.nextLine();break;
		
	    case 2://insert     
		System.out.println("elemento: ");
		String elem = rdLine.nextLine();
		System.out.println("clave: ");
		do{
		    try{
			clave = rdInt.nextInt();
			error = 0;
		    }catch(Exception e){
			System.out.println("parametro invalido");
			rdInt.nextLine();
			error = 1;
		    }
		}while(error==1);
		tree.insert(elem,clave);break;

	    case 3://delete
		System.out.println("clave: ");
		do{
		    try{
			clave = rdInt.nextInt();
			error = 0;
		    }catch(Exception e){
			System.out.println("parametro invalido");
			rdInt.nextLine();
			error = 1;
		    }
		}while(error==1);
		System.out.println("elemento eliminado: "+tree.delete(clave)+"\n");
		System.out.println("presiona Enter");
		rdLine.nextLine();break;
		
	    case 4://findMin
		System.out.println("elemento minimo: "+tree.findMin()+"\n");
		System.out.println("presiona Enter");
		rdLine.nextLine();break;

	    case 5://findMax
		System.out.println("elemento máximo: "+tree.findMax()+"\n");
		System.out.println("presiona Enter");
		rdLine.nextLine();break;

	    case 6://isEmpty
		System.out.println(tree.isEmpty()+"\n");
		System.out.println("presiona Enter");
		rdLine.nextLine();break;

	    case 7://recorridos
		System.out.println("1 - pre-Orden");
		System.out.println("2 - in-Orden");
		System.out.println("3 - pos-Orden");
		do{
		    try{
			clave = rdInt.nextInt();
			error = 0;
		    }catch(Exception e){
			System.out.println("parametro invalido");
			rdInt.nextLine();
			error = 1;
		    }
		}while(error==1);
		
		switch(clave){
		case 1://pre-orden
		    System.out.println(tree.preOrder()+"\n");
		    System.out.println("presiona Enter");
		    rdLine.nextLine();break;
		    
		case 2://in-orden
		    System.out.println(tree.inOrder()+"\n");
		    System.out.println("presiona Enter");
		    rdLine.nextLine();break;

		case 3://pos-orden
		    System.out.println(tree.posOrder()+"\n");
		    System.out.println("presiona Enter");
		    rdLine.nextLine();break;

		default:
		    System.out.println("numero invalido\n");break;
		}
		
	    case 0:
		System.out.println("adios c:");break;
		
	    default:
		System.out.println("numero invalido\n");break;		
	    }
	}while(x!=0);
    }
}
