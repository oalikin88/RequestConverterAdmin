/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.requestconverteradmin.data;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 *
 * @author 041AlikinOS
 */
public class Content {
    
    public static List<String> getContent(File file) throws IOException {
        Path path = Paths.get(file.getAbsolutePath());
        List<String> list = Files.readAllLines(path);
        return list;
    }
    
    
  
    
        public static String[][] recordListTransform(List<String> list) {
        
        int rows = list.size();
        int cells = 4;
        String[][] array = new String[rows][cells];
        int listIndex = 0;
        String[] arrayBuf = new String[4];
        for(int rowIndex = 0; rowIndex < rows; rowIndex++) {
            arrayBuf = list.get(listIndex++).split(";");
            for(int cellIndex = 0; cellIndex < arrayBuf.length; cellIndex++) {
                     array[rowIndex][cellIndex] = arrayBuf[cellIndex];
                
                } 
            }
        return array;
    }
        
        public static String[][] requestListTransform(List<String> list) {
            int rows = list.size();
            int cells = 2;
            String [][] array = new String[rows][cells];
            int listIndex = 0;
            String[] arrayBuf = new String[2];
            for(int rowIndex = 0; rowIndex < rows; rowIndex++) {
                arrayBuf = list.get(listIndex++).split(";");
                for(int cellIndex = 0; cellIndex < arrayBuf.length; cellIndex++) {
                    array[rowIndex][cellIndex] = arrayBuf[cellIndex];
                }
            }
            return array;
        }
        
}
