package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class test{
    @Test
    void createNode(){
        Node node = new Node("Корень");
        assertEquals("Корень", node.getName());
    }
    @Test
    void addNode(){
        Node root = new Node("Корень");
        Node child = new Node("Лист");
        root.add(child);
        assertEquals(1, root.getChildren().size());
        assertEquals("Лист", root.getChildren().get(0).getName());
    }
    @Test
    void deleteNode(){
        Node root1 = new Node("Корень");
        Node child1 = new Node("Лист1");
        Node child2 = new Node("Лист2");
        Node child3 = new Node("Лист3");
        root1.add(child1);
        root1.add(child2);
        root1.add(child3);
        Node root2 = new Node("Корень");
        root2.add(child1);
        root2.add(child2);
        root1.deleteNode(child3.getId());
        assertEquals(root1.getChildren(), root2.getChildren());
    }
    @Test
    void treePrint(){
        Node root = new Node("Корень");
        Node child = new Node ("Лист");
        root.add(child);
        String actual = root.toString();
        String expected = "Корень\n\tЛист\n";
        assertEquals(expected, actual);
    }
    @Test
    void print2Json() throws IOException {
        Node root = new Node("Корень");
        Node child = new Node("Лист");

        String path = "D:\\Проекты Java\\tree\\target\\input.json";

        root.add(child);
        ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        String actual = objectMapper.writeValueAsString(root);
        Files.write(Paths.get(path), actual.getBytes());
    }
    @Test
    void readFromJson() throws IOException {
        String path = "D:\\Проекты Java\\tree\\target\\input.json";

        byte[] bytes = Files.readAllBytes(Paths.get(path));
        String jsonString = new String(bytes);

        ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        Node actual = objectMapper.readValue(jsonString, Node.class);

        assertEquals("Корень", actual.getName());
        assertEquals(1, actual.getChildren().size());
        assertEquals("Лист", actual.getChildren().get(0).getName());
    }
}