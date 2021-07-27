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
public class NodeList<T> {

    T data;
    int counter;
    NodeList<T> nextNode;

    public NodeList(T data) {
        this.data = data;
    }
    
}
