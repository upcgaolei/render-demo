package com.summer.render;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import lombok.ToString;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Author: gaoleizhou@gmail.com
 * Created At 2017/10/11.
 * Desc: default
 */
@ToString
public class Example {

    List<Item> items() {
        return Arrays.asList(
                new Item("item1", "10.00", Arrays.asList(new Feature("feature1"))),
                new Item("item2", "20.00", Arrays.asList(new Feature("feature2")))
        );
    }

    static class Item {
        String name, price;
        List<Feature> features;
        Item(String name, String price, List<Feature> features) {
            this.name = name;
            this.price = price;
            this.features = features;
        }
    }

    static class Feature {
        String description;

        Feature(String description) {
            this.description = description;
        }
    }

    public static void main(String[] args) throws IOException {
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache mustache = mf.compile("template.mustache");
        mustache.execute(new PrintWriter(System.out), new Example()).flush();

        HashMap<String, Object> scopes = new HashMap<>();
        scopes.put("name", "Mustache");
        scopes.put("feature", new Feature("perfect!"));

        MustacheFactory mf1 = new DefaultMustacheFactory();
        Mustache mustache1 = mf1.compile(new StringReader("{{name}}, {{feature.description}}"), "example");
        mustache1.execute(new PrintWriter(System.out), scopes).flush();
    }
}
