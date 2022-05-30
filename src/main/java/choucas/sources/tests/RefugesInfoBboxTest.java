/**
 * Package choucas.sources
 * Provides WPS processes (services) and tools to access data sources
 * WPS processes (services) are hosted on a JavaPS WPS server
 * See https://52north.org/software/software-projects/javaps/
 * Project : LMAP/IPRA/CHOUCAS, 2017-2022
 */

package choucas.sources.tests;

import choucas.sources.algorithm.RefugesInfoBbox;
import choucas.metadata.rando.process.RefugesInfoProcess.TypeEnum;

/**
 * Test application for Refuge Info Bbox adapter.
 *
 * @author Eric Gouardères
 * @date february 2022
 */

public class RefugesInfoBboxTest 
{
    public static void main( String[] args )
    {
    	RefugesInfoBbox service = new RefugesInfoBbox();
        
        System.out.println( "--------------------------------" );
        System.out.println( "Test : Calling Refuge Info Service" );
        service.type_points = TypeEnum.refuge;
        service.setBbox("5.003586,44.517563,6.634007,45.850803"); // zone étude Choucas
        service.execute();
        System.out.println( "\n--------------------------------------");
        System.out.println( "Test: Result of Refuge Info Service\n\n" + service.getOutput());       
        
    }
}
