package choucas.io.datahandler.generator;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.n52.javaps.annotation.Properties;
import org.n52.javaps.description.TypedProcessOutputDescription;
import org.n52.javaps.io.AbstractPropertiesInputOutputHandler;
import org.n52.javaps.io.Data;
import org.n52.javaps.io.EncodingException;
import org.n52.javaps.io.OutputHandler;
import org.n52.shetland.ogc.wps.Format;

import choucas.metadata.rando.dao.FeaturesData;

@Properties(
        defaultPropertyFileName = "features-data.properties")
public class FeaturesDataGenerator extends AbstractPropertiesInputOutputHandler implements OutputHandler {

	public FeaturesDataGenerator(){
		super();
		addSupportedBinding(FeaturesData.class);
	}

	public boolean isSupportedSchema(String schema) {
		//no schema checks
		return true;
	}

    public InputStream generate(TypedProcessOutputDescription<?> description,
            Data<?> data,
            Format format) throws IOException, EncodingException {
        if (data instanceof FeaturesData) {
        	Object featuresData = ((FeaturesData) data).getPayload();

            String dataString = featuresData.toString();

            InputStream is = new ByteArrayInputStream(dataString.getBytes());

            return is;
        }
        return null;
    }


}
