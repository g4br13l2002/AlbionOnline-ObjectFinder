package rsrc;

public interface TDABinarySearchTree<K extends Comparable, T>{

    /**
     * metodo que regresa un elemento asociado a una clave
     * @param k - clave a la que está asociado un elemento
     * @return - el elemento al que está asiciado k
     */    
    public T retrieve(K k);

    /**
     * metodo para insertar un elemento con una clave en el arbol
     * @param e - elemento dentro del nodo
     * @param clave - la clave asociada al nodo
     */
    public void insert(T e, K clave);   
    
    /**
     * metodo para eliminar un elemento al que está asiciada la clave
     * @param k - clave a la que está asociado un elemento
     * @return - el elemento al que está asiciado k
     */    
    public T delete(K k);

    /**
     * metodo que regresa el elemento con la menor clave del arbol
     * @return - el elemento con la menor clave del arbol
     */
    public T findMin();

    /**
     * metodo que regresa el elemento con la mayot clave del arbol
     * @return - el elemento con la mayor clave del arbol
     */    
    public T findMax();

    /**
     * metodo que verifica si un arbol está vacío
     * @return - true si está vacío, false si no
     */
    public boolean isEmpty();
    
    /**metodo que regresa el preOrden del arbol*/
    public String preOrder();
    /**metodo que regresa el inOrden del arbol*/
    public String inOrder();
    /**metodo que regresa el posOrden del arbol*/
    public String posOrder();
    
}
