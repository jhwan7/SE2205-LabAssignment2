public class LinkedListQueue<E> implements Queue<E>{
  private SinglyLinkedList<E> container;
  public LinkedListQueue(){
    container = new SinglyLinkedList<>();
  }
  public int size(){
    return container.size();
  }
  public boolean isEmpty(){
    return container.isEmpty();
  }
  public E first(){
    return container.first();
  }
  public void enqueue(E node){
    container.addLast(node);
  }
  public E dequeue(){
    return container.removeFirst();
  }
}
