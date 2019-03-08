public class SinglyLinkedList<E>{

  private static class Node<E>{
    Node<E> next;
    E element;
    public Node(E e, Node<E> n){
      next = n;
      element = e;
    }
    public E getElement(){
      return element;
    }
    public Node<E> getNext(){
      return next;
    }
    public void setNext(Node<E> n){
      next = n;
    }
  }
  private Node<E> head;
  private Node<E> tail;
  private int size;
  public SinglyLinkedList(){
    head = null;
    tail = null;
    size = 0;
  }
  
  public int size(){
    return size;
  }
  
  public boolean isEmpty(){
    return size==0;
  }
  
  public E first(){
    return head.getElement();
  }
  
  public E last(){
    return tail.getElement();
  }
  
  public void addFirst(E element){
    head = new Node<E>(element,head);
    if(isEmpty())tail = head;
    size++;
  }
  
  public void addLast(E element){
    if(isEmpty())addFirst(element);
    else{
      tail.next = new Node<E>(element,null);
      tail = tail.next;
      size++;
    }
  }
  
  public E removeFirst(){
    if(isEmpty()){return null;}
    E removed = head.element;
    head = head.next;
    size--;
    if(size==0){tail = null;}
    return removed;
  }

}