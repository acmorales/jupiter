package GetCCLocation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONObject;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Context;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

public class LocationVerticle extends AbstractVerticle{
	
	private HttpServer httpServer = null;
	
	public void returnLocation(RoutingContext routingContext){
		
		System.out.println("HTTP GET request received...");
		
		getHTML gh = new getHTML();
		JSONObject jo = new JSONObject();
		jo = gh.getLocation();
		
		if(jo.getInt("statusCode") == 200){
			System.out.println("...Location data gotten...");
			routingContext.response().setStatusCode(200).setStatusMessage("Success");
			routingContext.response().end(jo.toString());
			System.out.println("HTTP GET request responded to successfully.");
		}
		else {
			System.out.println("HTTP GET request failed; error 400");
			routingContext.response().setStatusCode(400).setStatusMessage("Failure").end();
		}
	}

	@Override
	public void start(Future<Void> startFuture){
		
		Router router = Router.router(vertx);
		
		router.route().handler(BodyHandler.create());
		
		Route getRoute = router.route().method(HttpMethod.GET);
		
		getRoute.handler(this::returnLocation);
		
		getRoute.failureHandler(frc -> {
			int statusCode = frc.statusCode();
			JsonObject js = new JsonObject();
			js.put("error", "error occured while processing GET request");
			frc.response().setStatusCode(statusCode).setStatusMessage("failure");
			frc.response()
			.end(js.toString());
		});
		
		int port = config().getInteger("http.port", 8080);
		
		System.out.println("The port is: " + port);
		
		httpServer = vertx.createHttpServer()
				.requestHandler(router::accept)
				.listen(port, result -> {
					if(result.succeeded()) startFuture.complete();
					else startFuture.fail(result.cause());
				});
		
	}
	
}
