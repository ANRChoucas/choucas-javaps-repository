package choucas.utils;


import java.net.URI;
import java.util.Iterator;
import java.util.Set;

import org.n52.shetland.ogc.ows.OwsMetadata;
import org.n52.shetland.w3c.xlink.Show;

import choucas.erig.algorithm.ErigNer;
import choucas.metadata.rando.MainCreateDescribeProcess;

public class CreateDescribeProcess extends MainCreateDescribeProcess {
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainCreateDescribeProcess main = new MainCreateDescribeProcess();
			URI role = new URI("http://www.opengis.net/spec/wps/2.0/def/process-profile/concept_functionality");
			URI href = new URI("http://www.semanticweb.org/home/ontologies/2018/6/untitled-ontology-6#Service_d’annotation_géographique");
			OwsMetadata meta = new OwsMetadata(href, role);
			Set<OwsMetadata> setMetadata = main.getProcessDescription(ErigNer.class).getMetadata();
	        Iterator<OwsMetadata> it1 = setMetadata.iterator();
	        while (it1.hasNext()) {
	            System.out.println(it1.next());
	        }
	        
	        System.out.println(" ----- ");
			System.out.println(meta.getHref());
		    Class classe = Class.forName("choucas.erig.algorithm.ErigNer");
		    System.out.println("classe de l'objet chaine = "+classe.getName());
			
			main.createMetadataFiles(ErigNer.class, "./data/service_descriptions/ErigNer_DescribeService.xml");
			System.out.println("-----");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
