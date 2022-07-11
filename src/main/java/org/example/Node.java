package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Node {
    private String id;
    private String name;
    private List<Node> children;

    public Node() {
        this.id = "";
        this.name = "";
        this.children = new ArrayList<>();
    }
    public Node(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.children = new ArrayList<>();
    }
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public List<Node> getChildren() {
        return children;
    }
    public void add(Node child) { children.add(child); }

    public void deleteNode(String ident){
        int idx = -1;
        for (int i = 0; i<children.size(); i++)
            if (children.get(i).id == ident)
                idx = i;
        if (idx != -1)
            children.remove(idx);
    }
    private String toString(Node _node, int space){
        if (_node == null)
            return "";
        StringBuilder result = new StringBuilder();
        for (int i=0;i<space;i++){
            result.append("\t");
        }
        result.append(_node.name).append("\n");
        if (_node.children == null){
            return result.toString();
        }
        for (Node child : _node.children){
            result.append(toString(child, space + 1));
        }
        return result.toString();
    }
    @Override
    public String toString(){
        return toString(this,0);
    }
    public void printTree(Node root, List<String> arr1){
        List<Node> arr = root.getChildren();
        if (arr.size() == 0) {
            arr1.add((String)root.getName());
            return;
        }
        for (int i=0;i<arr.size();i++){
            printTree((Node) arr.get(i), arr1);
        }
    }
}
