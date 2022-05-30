package choucas.sources.algorithm;

import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import choucas.metadata.rando.process.RefugesInfoProcess;
import choucas.utils.WsUtils;

/**
 * This WPS process is an adapter for Refuges.info Bbox service.
 * 
 *    example: https://www.refuges.info/api/bbox?type_points=refuge
 *    documentation: https://www.refuges.info/api/doc/#/api/bbox
 * 
 * @author Eric Gouardères
 * @version 1.0
 */

public class RefugesInfoBbox extends RefugesInfoProcess{
	
	@Override
	public void setBbox(String bbox) {
		this.bbox = bbox;
	}
	private String bbox="";
	
	// ------------------------------------------------------------------------
	//      EXECUTE

	@Override
	public void execute() {
		
        String url_base =  "https://www.refuges.info/api/bbox";
        JsonObject response;
        String separator = "****************";

        // log.debug("Running erig process");

		  try {
			  System.out.println("\n" + separator);
			  System.out.println("Adapter : Calling Refuge Info...");
			  response = callRefinfo(url_base, type_points, bbox);
			  dataOutput = response;
			  System.out.println("\nAdapter : Service Invoked Successfully !");

		  } catch (Exception e) {
			  System.out.println("\n Adapter: Error while calling REST Service");
			  e.printStackTrace();
			  }

        System.out.println("\n" + separator);
        //log.debug("Finished erig process, complex output is : {}", complexOutput);
    }
	
	@Override
	public Object getOutput(){
		return this.dataOutput;
	}
	protected Object dataOutput;
	
    protected static JsonObject callRefinfo(String api_url, TypeEnum type_points, String bbox) throws IOException, JsonSyntaxException
    {

    	String response;
    	Gson respGson = new Gson();
    	JsonObject respJson;
    	
    	api_url += "?bbox="+bbox+"&type_points="+type_points;
    	response = WsUtils.callServiceGet(api_url);
    	respJson = respGson.fromJson(response, JsonObject.class);
    	
    	return respJson;
    }
        

	
}
