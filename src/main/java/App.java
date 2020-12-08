import models.Animal;
import models.EndangeredAnimal;
import models.Sighting;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");




        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("sighting", Sighting.getAllSightings());
            return new ModelAndView(model, "index.hbs");
            }, new HandlebarsTemplateEngine());

//        animals
        get("/animals/endangered", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "animals-endangered.hbs");
        }, new HandlebarsTemplateEngine());
//
        post("/sighting-animal-new", (request, response) -> {

            Map<String, Object> model = new HashMap<>();
            String animalName = request.queryParams("animalName");

            Animal animals = new Animal(animalName);
            animals.save();
            model.put("animals", animals);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        get("/Animals", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("Animals", Animal.allAnimals());
            return new ModelAndView(model, "Animals.hbs");
        }, new HandlebarsTemplateEngine());


//endangered
        get("/EndangeredForm", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "EndangeredForm.hbs");
        }, new HandlebarsTemplateEngine());


        post("/report", (request, response) -> {

            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("animalName");
            String health = request.queryParams("animalHealth");
            String age = request.queryParams("age");
            EndangeredAnimal endangered = new EndangeredAnimal(name, health,age);
            endangered.save();
            model.put("endangered", endangered);
            return new ModelAndView(model, "SuccessDanger.hbs");
        }, new HandlebarsTemplateEngine());

        get("/Endangered", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("Endangered", EndangeredAnimal.all());
            return new ModelAndView(model, "Endangered.hbs");
        }, new HandlebarsTemplateEngine());

//        Sightings
        get("/sighting/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "sighting-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/sighting/new", (request, response) -> {

            Map<String, Object> model = new HashMap<>();
            String location = request.queryParams("location");
            String rangerName = request.queryParams("rangerName");
            String aniName = request.queryParams("animalName");

            Sighting sightings = new Sighting(location,rangerName, aniName);
            sightings.save();
            model.put("sighting", sightings);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        get("/sightings", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("Sightings", Sighting.getAllSightings());
            return new ModelAndView(model, "sightings.hbs");
        }, new HandlebarsTemplateEngine());

    }

}