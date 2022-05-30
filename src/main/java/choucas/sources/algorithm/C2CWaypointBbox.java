/**
 * Package choucas.sources
 * Provides WPS processes (services) and tools to access data sources
 * WPS processes (services) are hosted on a JavaPS WPS server
 * See https://52north.org/software/software-projects/javaps/
 * Project : LMAP/IPRA/CHOUCAS, 2017-2022
 */

package choucas.sources.algorithm;

import java.io.IOException;

import org.n52.javaps.algorithm.annotation.LiteralInput;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import choucas.metadata.rando.process.C2CWaypointProcess;
import choucas.metadata.rando.process.choix.WPointType;
import choucas.utils.WsUtils;

/**
 * This WPS process is an adapter for CamptoCamp Bbox service.
 * 
 *    example: https://api.camptocamp.org/waypoints?limit=30&wtyp=gite,hut,shelter&bbox=
 *    documentation: 
 * 
 * @author Eric Gouardères
 * @version 1.0
 */

public class C2CWaypointBbox extends C2CWaypointProcess{
	
	// ------------------------------------------------------------------------
	//  

	@Override
	public void setBbox(String bbox) {
		this.bbox = bbox;
	}
	private String bbox="";
	
	// ------------------------------------------------------------------------
	//      EXECUTE

	
	@Override
	public void execute() {
		
        String url_base =  "https://api.camptocamp.org/waypoints";
        JsonObject response;
        String separator = "****************";

        // log.debug("Running erig process");

		  try {
			  System.out.println("\n" + separator);
			  System.out.println("Adapter : Calling CamptoCamp...");
			  response = callC2C(url_base, wtyp, bbox);
			  dataOutput = response;
			  System.out.println("\nAdapter : Service Invoked Successfully !");

		  } catch (Exception e) {
			  System.out.println("\n Adapter: Error while calling REST Service");
			  e.printStackTrace();
			  }

        System.out.println("\n" + separator);
    }
	
	@Override
	public Object getDocuments(){
		return this.dataOutput;
	}
	protected Object dataOutput;
	
    protected static JsonObject callC2C(String api_url, WPointType wtyp, String bbox) throws IOException, JsonSyntaxException
    {

    	String response;
    	Gson respGson = new Gson();
    	JsonObject respJson;

    	api_url += "?wtyp="+wtyp+"&bbox="+bbox;
    	response = WsUtils.callServiceGet(api_url);
    	respJson = respGson.fromJson(response, JsonObject.class);
    	
    	return respJson;
    }
        

	
}
