/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Odev2;

/**
 * @file Odev2
 * @description Program seçilen klasör içindeki dosyaları okuyarak kendisine
 * parametre olarak verilen kelimenin her bir okunan dosya için alaka düzeyini
 * hesaplar.
 * @assignment 2. Ödev
 * @date 22/05/2020
 * @author Adı : Veli Tayyib Soyadı : Akdeniz Mail : vtakdeniz@gmail.com
 */
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JFileChooser;

/**
 *
 * @author velitayyibakdeniz
 */
public class BinarySearchTree<T extends Comparable> {

    private Node<T> root;

    private LinkedList<File> subFile;
    private boolean isCalled=false;   
    
    /* calculateFreq : Programın kelime frekansını hesaplayan ana kısmıdır. Bütün dosyaları bu ödev için istenilen spesifikasyonlara 
    göre kelime kelime okuyup ağaca ekler. String olarak verilen word parametresini parçalara ayırarak BST'de arar. Bulunan nodeların dosya
    bilgilerini ve frekanslarını LinkedList nodu olarak oluşturulan bir başka değişkene ekler.  Bu LinkedList nodeları ise bir linked 
    liste eklenir. Sonrasında bu nodelar MinHeap' eklenerek sıralı bir yapı oluşturulur.
    MinHeap.printArray() metodu ile bütün bilgiler konsola yazdırılır.
    */
    public void calculateFreq(String word) {
        
        if (isCalled==false) {
            subFile = getSubFileList();
            constructBST(subFile);
            isCalled=true;
        }
        
        LinkedList<String> words = splitAndTrim(word);
        MinHeap m = new MinHeap(subFile.size());
        LinkedList<String> nodes = new LinkedList<>();

        for (int i = 0; i < subFile.size(); i++) {
            for (int j = 0; j < words.size(); j++) {
                Node n = search((T) words.get(j));
                if (n.list.getNode((T) subFile.get(i).getName()) != null) {
                    nodes.add(subFile.get(i).getName());
                    nodes.getNode(subFile.get(i).getName()).counter -= 1;
                    nodes.getNode(subFile.get(i).getName()).counter += n.list.getNode(subFile.get(i).getName()).counter;

                }
            }

        }

        for (int i = 0; i < nodes.size(); i++) {
            m.insert(nodes.getNode(i));
        }
        System.out.println(word);
        m.printArray();

    }

    // Kendisine verilen Stringi içindeki kelime harici charları çıkartarak parçalara ayırıp bir linkedlist'e ekler ve bu değişkeni döndürür.
    public LinkedList splitAndTrim(String word) {
        LinkedList<String> l = new LinkedList<>();
        for (int i = 0; i < word.split(" ").length; i++) {
            String s = word.split(" ")[i].replaceAll("[^a-zA-Z]", "");
            if (!s.equals("")) {
                l.add(s);
            }

        }
        return l;
    }

    // Kendisine verilen dosya listesi ile addWord metodunu kullanarak kelime ağacını inşa eder. Dosya bilgisi kelime ağacında
    // herbir kelimenin hangi dosyada kaç kere geçtiğini tutabilmek için gerekir. 
    public void constructBST(LinkedList<File> a) {
        for (int i = 0; i < a.size(); i++) {
            addWord(a.get(i));
        }

    }

    // Kelime ağacına data ekler. 
    void insertRecursive(T newData, File f) {
        root = insertRecursive(root, newData, f);

    }

    // Kelime ağacına data ekler
    private Node<T> insertRecursive(Node<T> node, T newData, File f) {
        if (node == null) {
            return new Node<>(newData, f);

        } else {

            if (newData.compareTo(node.data) > 0) {
                node.rightChild = insertRecursive(node.rightChild, newData, f);

            } else if (newData.compareTo(node.data) < 0) {
                node.leftChild = insertRecursive(node.leftChild, newData, f);

            } else if (newData.compareTo(node.data) == 0) {
                node.list.add(f.getName());

            }
            return node;
        }
    }

    
    //Kendisine verilen dosyayı okuyarak kelimeleri BST ye yerleştirir.
    public void addWord(File f) {
        try {

            Scanner s = new Scanner(f);

            while (s.hasNext()) {
                String str = s.next().replaceAll("[^a-zA-Z]", "").toLowerCase();
                if (!str.equals("")) {
                    insertRecursive((T) str, f);
                }
            }

            s.close();
        } catch (IOException e) {
            System.out.println("Error accessing input file!");
        }

    }

    // Verilen datayı BST de arar.
    Node search(T searchData) {
        if (isEmpty()) {
            System.out.println("empty bst");
        } else {
            Node<T> temp = root;
            while (temp != null) {
                if (searchData.compareTo(temp.data) > 0) {
                    temp = temp.rightChild;
                } else if (searchData.compareTo(temp.data) < 0) {
                    temp = temp.leftChild;
                } else {
                    return temp;
                }

            }
        }
        return null;
    }

    // Seçilen dosya içinde istenilen formattaki bütün dosyaları getFileExtension metodunu da kullanarak döndürür.
    public static LinkedList getSubFileList() {

        LinkedList<File> l = new LinkedList<>();
        JFileChooser fileChoice = new JFileChooser();
        fileChoice.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int returnVal = fileChoice.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File folder = fileChoice.getSelectedFile();
            File[] listOfFiles = folder.listFiles();

            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) {
                    File f = listOfFiles[i];
                    String str = getFileExtension(f);
                    if (str!="") {
                        l.add(f);
                    }

                }
            }

        }

        return l;
        
    }

    // Kendisine verilen dosyasının tipini döndürür.
     public static String getFileExtension(File file) {
        String extension = "";

        try {
            if (file != null && file.exists()) {
                String name = file.getName();
                extension = name.substring(name.lastIndexOf("."));
            }
        } catch (Exception e) {
            extension = "";
        }

        return extension;

    }

    // BST yi inorder şekilde konsola yazdırır
    void inorder() {
        System.out.println("inorder : ");
        inorder(root);
        System.out.println();

    }

    // BST yi inorder şekilde konsola yazdırır
    private void inorder(Node<T> node) {
        if (node != null) {

            inorder(node.leftChild);
            System.out.print(node.data + " ");
            for (int i = 0; i < node.list.size(); i++) {
                System.out.println(node.list.get(i) + " counter  " + node.list.getNode(i).counter);
            }
            inorder(node.rightChild);

        }

    }

    private boolean isEmpty() {

        return root == null;
    }

}
