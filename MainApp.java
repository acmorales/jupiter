package GetCCLocation;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.dropwizard.DropwizardMetricsOptions;
import GetCCLocation.LocationVerticle;

public class MainApp {
	
	private static Vertx vertx;

    public static void main(String[] args) throws InterruptedException {
    	vertx = Vertx.vertx();
        
        try {   	
        	
        	vertx.deployVerticle(LocationVerticle.class.getName(),
        			handler -> {
        				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        				Date date = new Date();
        				
        				printLineSeps("Successfully started verticle \t" + dateFormat.format(date));
        			});
        	
        	
        } catch (Exception e) {
        	e.printStackTrace();
        }

        //vertx.deployVerticle(new ReceiverVerticle());
        
    }

	public static void printLineSeps(String status) {
		System.out
				.println("****************************************************************");

		System.out.println(status);

		System.out
				.println("****************************************************************");
	}

}
