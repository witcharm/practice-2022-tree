package org.example;

import javax.ws.rs.core.Application;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Web-приложение в котором регистрируются все ресурсы.
 */
public class WebApplication extends Application {

    private List<String> list = new ArrayList<>();
    private Node tree;

    public WebApplication() {
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        list.add("ddd");

        tree = new Node("Корень");
        Node child1 = new Node("Лист1");
        Node child2 = new Node("Лист2");
        Node child21 = new Node("Лист2_1");
        child2.add(child21);
        Node child3 = new Node("Лист3");
        tree.add(child1);
        tree.add(child2);
        tree.add(child3);
    }

    /**
     * Возвращает список всех ресурсов web-приложения.
     * @return список всех ресурсов web-приложения.
     */
    @Override
    public Set<Object> getSingletons() {
        Set<Object> resources = new HashSet<>();
        resources.add(new ListPresentationController(list, tree));
        return resources;
    }
}
