import models.EndangeredAnimal;
import models.NormalAnimal;
import models.Ranger;
import models.Sighting;
import spark.ModelAndView;
import spark.ResponseTransformer;
import spark.Route;
import spark.TemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");



        get("/", (Route) (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Sighting> allSightings = Sighting.all();
            model.put("sightings", allSightings);
            return new ModelAndView(model,"index.hbs");
        },new HandlebarsTemplateEngine());


        get("/animals/endangered", (Route) (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("endangered", EndangeredAnimal.all());
            return new ModelAndView(model,"animals-endangered.hbs");
        },new HandlebarsTemplateEngine());

        //get: retrieve non-endangered animals
        get("/animals/not-endangered", (Route) (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("normal", NormalAnimal.all());
            return new ModelAndView(model,"animals-normal.hbs");
        },new HandlebarsTemplateEngine());

        //get: New Sighting Form
        get("/sighting/new", (Route) (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("sightings", Sighting.all());
            return new ModelAndView(model,"sighting-form.hbs");
        },new HandlebarsTemplateEngine());

        //Post: Sighting Submission
        post("/sighting/new", (Route) (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String rangerName = request.queryParams("rangerName").trim();
            String animalName = request.queryParams("animalName").trim();
            String animalAge = request.queryParams("animalAge").trim();
            String animalHealth = request.queryParams("animalHealth").trim();
            String location = request.queryParams("location").trim();
            String animalType = request.queryParams("animalType").trim();

            Ranger newRanger = new Ranger(rangerName);
            newRanger.save();

            if(animalType.equalsIgnoreCase("Endangered")){
                EndangeredAnimal endangeredAnimal = new EndangeredAnimal(animalName,animalHealth,animalAge);
                endangeredAnimal.save();
                Sighting newSighting = new Sighting(endangeredAnimal.getName(),location,newRanger.getId());
                newSighting.save();
            }
            else{
                NormalAnimal normalAnimal = new NormalAnimal(animalName,animalHealth,animalAge);
                normalAnimal.save();
                Sighting newSighting = new Sighting(normalAnimal.getName(),location,newRanger.getId());
                newSighting.save();
            }
            return new ModelAndView(model,"success.hbs");
        },new HandlebarsTemplateEngine());

        //get: retrieve all sightings by location
        get("/sightings", (Route) (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("sightings", Sighting.all());
            return new ModelAndView(model,"sighting-locations.hbs");
        },new HandlebarsTemplateEngine());

        //get: retrieve all sightings by location
        get("/sightings/:location/details", (Route) (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String filter = request.params("location");
            model.put("location",filter);
            model.put("sightings", Sighting.getAllSightingsInLocation(filter));
            return new ModelAndView(model,"sighting-location-details.hbs");
        },new HandlebarsTemplateEngine());

        //get: ranger details
        get("/rangers/:id/details", (Route) (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int id = Integer.parseInt(request.params("id"));
            Ranger foundRanger = Ranger.find(id);
            List<Sighting> mySightings = foundRanger.mySightings();
            model.put("ranger",foundRanger);
            model.put("sightings",mySightings);
            return new ModelAndView(model,"ranger-details.hbs");
        },new HandlebarsTemplateEngine());

        //get: all rangers
        get("/rangers", (Route) (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("rangers", Ranger.all());
            return new ModelAndView(model,"all-rangers.hbs");
        },new HandlebarsTemplateEngine());

        //get: form to add animals to specific ranger
        get("/rangers/:id/sighting/new", (Route) (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int id = Integer.parseInt(request.params("id"));
            Ranger specificRanger = Ranger.find(id);
            model.put("specificRanger",specificRanger);
            model.put("sightings", Sighting.all());
            return new ModelAndView(model,"sighting-form.hbs");
        },new HandlebarsTemplateEngine());

        //post: form to add animals to specific ranger
        post("/rangers/:id/sighting/new", (Route) (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int id = Integer.parseInt(request.params("id"));
            Ranger specificRanger = Ranger.find(id);
            String animalName = request.queryParams("animalName").trim();
            String animalAge = request.queryParams("animalAge").trim();
            String animalHealth = request.queryParams("animalHealth").trim();
            String location = request.queryParams("location").trim();
            String animalType = request.queryParams("animalType").trim();

            if(animalType.equalsIgnoreCase("Endangered")){
                EndangeredAnimal endangeredAnimal = new EndangeredAnimal(animalName,animalHealth,animalAge);
                endangeredAnimal.save();
                Sighting newSighting = new Sighting(endangeredAnimal.getName(),location,specificRanger.getId());
                newSighting.save();
            }
            else{
                NormalAnimal normalAnimal = new NormalAnimal(animalName,animalHealth,animalAge);
                normalAnimal.save();
                Sighting newSighting = new Sighting(normalAnimal.getName(),location,specificRanger.getId());
                newSighting.save();
            }

            return new ModelAndView(model,"success.hbs");
        },new HandlebarsTemplateEngine());

    }

    private static class HandlebarsTemplateEngine extends TemplateEngine implements ResponseTransformer {
        @Override
        public String render(ModelAndView modelAndView) {
            return null;
        }
    }
}
