/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Odev2;
/**
* @file Odev2
* @description Program seçilen klasör içindeki dosyaları okuyarak kendisine parametre olarak verilen kelimenin her bir okunan dosya için alaka düzeyini hesaplar.
* @assignment 2. Ödev
* @date 22/05/2020
* @author Adı : Veli Tayyib   Soyadı : Akdeniz   Mail : vtakdeniz@gmail.com
*/

/**
 *
 * @author velitayyibakdeniz
 */
public class LinkedList<T extends Comparable> {

    private NodeList<T> head;

    void add(NodeList<T> newNode) {
        if (isEmpty()) {
            head = newNode;
            head.counter++;
        } else {
            NodeList<T> temp = head;

            while (temp != null) {
                if (temp.data.compareTo(newNode.data) == 0) {
                    temp.counter = temp.counter + 1;
                    return;
                }

                if (temp.nextNode != null) {
                    temp = temp.nextNode;
                } else {
                    temp.nextNode = newNode;
                }
            }

        }
    }

    void add(T newData) {
        add(new NodeList<>(newData));
    }

    NodeList<T> remove(T data) {
        NodeList<T> removedNode = null;

        if (isEmpty()) {
            System.out.println("Empty list !");
        } else {

            if (head.data.equals(data)) {
                removedNode = head;
                head = head.nextNode;
            } else {
                NodeList<T> temp = head;

                while (temp.nextNode != null && !temp.nextNode.data.equals(data)) {
                    temp = temp.nextNode;
                }

                if (temp.nextNode != null) {
                    removedNode = temp.nextNode;
                    temp.nextNode = temp.nextNode.nextNode;
                }
            }
        }

        return removedNode;
    }

    void print() {
        NodeList<T> temp = head;

        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.nextNode;
        }

        System.out.println("null");
    }

    boolean isEmpty() {
        return head == null;
    }

    int size() {
        NodeList<T> temp = head;
        int count = 0;

        while (temp != null) {
            count++;
            temp = temp.nextNode;
        }

        return count;
    }

    public T get(int i) {
        NodeList<T> temp = head;
        int counter = 0;
        while (temp != null) {
            if (counter == i) {
                return temp.data;
            }
            counter++;
            temp = temp.nextNode;

        }
        return null;
    }

    public NodeList getNode(int i) {
        NodeList<T> temp = head;
        int counter = 0;
        while (temp != null) {
            if (counter == i) {
                return temp;
            }
            counter++;
            temp = temp.nextNode;

        }
        return null;
    }
    
    public NodeList getNode(T data) {
        NodeList<T> temp = head;
        
        while (temp != null) {
            if (temp.data.compareTo(data)==0) {
                return temp;
            }
            temp = temp.nextNode;

        }
        return null;
    }
    
    
}
