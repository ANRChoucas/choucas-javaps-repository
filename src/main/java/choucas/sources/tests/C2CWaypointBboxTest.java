/**
 * Package choucas.sources
 * Provides WPS processes (services) and tools to access data sources
 * WPS processes (services) are hosted on a JavaPS WPS server
 * See https://52north.org/software/software-projects/javaps/
 * Project : LMAP/IPRA/CHOUCAS, 2017-2022
 */

package choucas.sources.tests;

import choucas.sources.algorithm.C2CWaypointBbox;
import choucas.metadata.rando.process.choix.WPointType;

/**
 * Test application for CamptoCamp Bbox adapter.
 *
 * @author Eric Gouardères
 * @date April 2022
 */

public class C2CWaypointBboxTest 
{
    public static void main( String[] args )
    {
    	C2CWaypointBbox service = new C2CWaypointBbox();
        
        System.out.println( "--------------------------------" );
        System.out.println( "Test : Calling C2C Service" );
        service.wtyp = WPointType.REFUGE; 
        service.setBbox("556996.6457,5545888.7356,738494.3023,5756472.4469"); // zone étude Choucas
        System.out.println(service.wtyp);
        service.execute();
        System.out.println( "\n--------------------------------------");
        System.out.println( "Test: Result of C2C Service\n\n" + service.getDocuments());       
        
    }
}
