package sample.firstspark;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

public class Main {

    public static void main(String[] args) {
        port(8080);
        
        get("/hello", (req, res) -> "hello!");

        get("/stop", (req, res) -> {
            stop();
            return null;
        });
        
        get("/param", (req, res) ->  "hello " + req.queryParams("name"));

        get("/path/:name", (req, res) ->  "hello " + req.params("name"));
        
        ThymeleafTemplateEngine te = new ThymeleafTemplateEngine();
        get("thyme", (Request req, Response res) -> {
            Map <String, Object> attr = new HashMap<>();
            attr.put("name", "x");
            return new ModelAndView(attr, "mytemplate");
        }, te);
    }
}