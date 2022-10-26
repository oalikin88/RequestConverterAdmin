/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.requestconverteradmin.data;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.TreeItem;

/**
 *
 * @author 041AlikinOS
 */
public class TreeViewManipulations {

    public static TreeItem<Record> updateTreeViewRecordList(List<Record> inputList) {

        TreeItem<Record> root = new TreeItem<>(new Record("Регионы"));

        TreeItem<Record> parent = new TreeItem<>();
        List<Record> parentsList = new ArrayList<>();

        for (int i = 0; i < inputList.size(); i++) {
            if (inputList.get(i).getOpfr().equals("000") && inputList.get(i).getUpfr().equals("000")) {
                parentsList.add(inputList.get(i));
                System.out.println(inputList.get(i));
            }
        }

        for (int i = 0; i < parentsList.size(); i++) {
            parent = new TreeItem<>(parentsList.get(i));
            root.getChildren().add(parent);
            for (int j = 0; j < inputList.size(); j++) {
                if (inputList.get(j).getSubject().equals(parentsList.get(i).getSubject())
                        && (!inputList.get(j).getOpfr().equals(parentsList.get(i).getOpfr())
                        || !inputList.get(j).getUpfr().equals(parentsList.get(i).getUpfr()))) {
                    parent.getChildren().add(new TreeItem<>(inputList.get(j)));
                }
            }
        }

        root.setExpanded(true);
        return root;

    }

    public static TreeItem<Request> updateTreeViewRequestList(List<Request> inputList) {
        TreeItem<Request> parent = new TreeItem<>(new Request("Запросы"));
        for (int i = 0; i < inputList.size(); i++) {
            parent.getChildren().add(new TreeItem<>(inputList.get(i)));

        }
        parent.setExpanded(true);
        return parent;
    }

}
