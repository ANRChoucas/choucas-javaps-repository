/**
 * Package choucas.erig
 * Provides WPS processes (services) and tools to access services hosted on erig.univ-pau.fr HTTP server
 * WPS processes (services) are hosted on a JavaPS WPS server
 * See http://erig.univ-pau.fr/PERDIDO/api.jsp
 * See https://52north.org/software/software-projects/javaps/
 * Project : LMAP/IPRA/CHOUCAS, 2017-2022
 */


package choucas.erig.algorithm;

import java.io.IOException;
import org.json.JSONException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlObject.Factory;
import org.n52.javaps.algorithm.annotation.LiteralInput;
import org.n52.javaps.algorithm.annotation.LiteralOutput;
import org.n52.javaps.algorithm.annotation.Metadata;
import org.n52.javaps.algorithm.annotation.Algorithm;
import org.n52.javaps.algorithm.annotation.Execute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import choucas.utils.WsUtils;

/**
 * This WPS process is an adapter for Erig Nerc Service.
 *
 * @author Eric Gouardères
 * @version 1.0
 */


@Algorithm(	
		version = "1.0.0", 
		title = "Erig Nerc Service Adapter", 
		abstrakt = "Extraction of spatial features and spatial relationships from text. "
				+ "Provides an XML document. "
				+ "API : http://erig.univ-pau.fr/PERDIDO/api/nerc/txt_xml/"
				+ "Documentation : http://erig.univ-pau.fr/PERDIDO/api.jsp#esne. ",
		metadata = {
					@Metadata(
							role = "http://www.opengis.net/spec/wps/2.0/def/process-profile/concept_functionality",
							href = "http://www.semanticweb.org/home/ontologies/2018/6/untitled-ontology-6#Service_d’annotation_géographique"),
					@Metadata(
							role = "http://www.opengis.net/spec/wps/2.0/def/process/description/documentation",
							href = "http://erig.univ-pau.fr/PERDIDO/api.jsp")
						}
				)
public class ErigNerc{
	
	// private static final Logger log = LoggerFactory.getLogger(ErigNerc.class);	
	
    private String content=null, api_key="choucas", lang="French";

    private String dataOutput;

    @LiteralInput(
    		identifier = "api_key",
    		abstrakt = "Your API key",
    		defaultValue = "choucas",
    		minOccurs = 0,
    		maxOccurs = 1
    		)
    public void setLiteralKey(String literalInput) {
        this.api_key = literalInput;
    }

    @LiteralInput(
    		identifier = "lang",
    		abstrakt = "Content language",
    		defaultValue = "French",
    		allowedValues = {"French", "Spanish", "English"},
    		minOccurs = 0,
    		maxOccurs = 1
    		)
    public void setLiteralLang(String literalInput) {
        this.lang = literalInput;
    }

    @LiteralInput(
    		identifier = "content",
    		abstrakt = "Textual content to annotate",
    		minOccurs = 1,
    		maxOccurs = 1
    		)
    public void setLiteralContent(String literalInput) {
        this.content = literalInput;
    }

    @LiteralOutput(
    		identifier = "dataOutput")
    public String getDataOutput() {
        return dataOutput;
    }
	
    @Execute
    public void run() {
    	
        String url_base =  "http://erig.univ-pau.fr";
        String response = "";
        String separator = "****************";
    	
        // log.debug("Running erig process");
        
        if (content != null) {
		  
		  try {
			  System.out.println("\n" + separator);               
			  System.out.println("Adapter : Calling Nerc...");
			  response = callNERC(url_base, api_key, lang, content);
			  //dataOutput = Factory.parse(response);
			  dataOutput = response;
			  if (WsUtils.getStdoutFlag()) {
				  System.out.println("\nInput :\n" + content);
				  System.out.println("\nOutput :\n"+ dataOutput);
			  }
			  System.out.println("\nAdapter : Service Invoked Successfully !");

		  } catch (Exception e) {
			  System.out.println("\n Adapter: Error while calling REST Service");
			  e.printStackTrace();
			  }
		  }
        else {
        	System.out.println("No content");
        	//log.debug("No content");
        	}
        System.out.println("\n" + separator);  
        //log.debug("Finished erig process, complex output is : {}", complexOutput);
    }
    
    
    // Expanded named entity recognition and classification service
    protected static String callNERC(String api_url, String api_key, String lang, String content) throws JSONException, IOException
    {
    		api_url += "/PERDIDO/api/nerc/txt_xml/";
    		
        	String request = "{\"api_key\":\""+api_key+"\",\"content\":\""+content+"\",\"lang\":\""+lang+"\"}";
   		
    		return WsUtils.callServicePost(api_url, request);
    }
    
}
