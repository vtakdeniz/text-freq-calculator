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
import java.io.File;

/**
 *
 * @author velitayyibakdeniz
 */
public class Node<T extends Comparable> {

    T data;
    LinkedList<String> list= new LinkedList<>();
    Node<T> leftChild;
    Node<T> rightChild;

    public Node(T data) {
        this.data = data;
    }
    
    /* Node kendisine verilen dosyanın ismini kendi listesine add metodu ile ekler. Böylece  nodeda saklanan 
       herbir kelime için hangi dosyalarda geçtiği bilgisi tutulabilir. 
    */
     public Node(T data,File f) {
        this.data = data;
        list.add(f.getName());
    }


    public  String toString(){
    return data+"";
    }
}