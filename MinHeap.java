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
public class MinHeap {

    private Object[] heap;
    private int size;

    // MinHeap ler obje tutacak şekilde değiştirilmiştir.
    public MinHeap(int capacity) {
        this.heap = new Object[capacity];
        size = 0;
    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private void swap(int i, int j) {

        Object temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;

    }
    
   // İnsert kontrolü yapılırken objenin counter metoduna bakılır. Obje heap'e counter bilgisine göre yerleştirilir.
    public void insert(Object newData) {
        if (size < heap.length) {
            heap[size] = newData;
            int current = size++;

            while (((NodeList) heap[current]).counter < ((NodeList) heap[parent(current)]).counter) {

                swap(current, parent(current));
                current = parent(current);

            }

        } else {
            System.out.println("heap is full !");
        }
    }

  
    public void heapify() {
        int lastIndex = size - 1;

        for (int i = parent(lastIndex); i >= 0; i--) {
            minHeap(i);
        }
    }

    private void minHeap(int i) {
        int left = 2 * 1 + 1;
        int right = 2 * i + 2;

        int min = i;
        if (left < size && ((NodeList)heap[min]).counter>((NodeList)heap[left]).counter ) {
            min = left;
        }
        if (right < size && ((NodeList)heap[min]).counter<((NodeList)heap[right]).counter ) {
            min = right;
        }

        if (min != i) {
            swap(min, i);
            minHeap(min);
        }

    }

    Object delete() {
        Object deletedElement = heap[0];
        heap[0] = heap[size - 1];
        size--;

        heapify();
        return deletedElement;

    }

  
    
    void printArray() {
        for (int i = size-1; i >= 0; i--) {
            
                 System.out.println("File : "+((NodeList)heap[i]).data+" -- Frequency : "+((NodeList)heap[i]).counter);
            
        }
        System.out.println();
    }
    
    


}
