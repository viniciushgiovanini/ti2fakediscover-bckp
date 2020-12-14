package maven.demo;

import org.json.JSONObject;
import maven.service.Service;
import static spark.Spark.*;

public class Principal {
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 5000; //return default port if heroku-port isn't set (i.e. on localhost)
    }
	
	public static void main(String[] args) {
		Service s = new Service();
		JSONObject all = s.getAllJson(); 
		
		System.out.println(all.toString());
		
		port(getHerokuAssignedPort());
		
		// avoid CORS policy
		options("/*", (request, response) -> {

			String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
			 
			if (accessControlRequestHeaders != null) {
				response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
			}

			String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
			if (accessControlRequestMethod != null) {
				response.header("Access-Control-Allow-Methods",
				accessControlRequestMethod);
			}

			return "OK";
		});
		

		before((request, response) -> {
			response.header("Access-Control-Allow-Origin", "*");
//			response.header("Content-Type", "aplication/json");
		});
		
		get("/dados", (request, response) -> all);
		
		s.close();
		
	}
}

