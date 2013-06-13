package trythundr;

import com.google.appengine.api.datastore.GeoPt;
import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;
import com.google.code.geocoder.model.GeocoderResult;
import com.google.code.geocoder.model.GeocoderStatus;
import com.threewks.thundr.view.string.StringView;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import trythundr.data.PersonAddress;
import trythundr.data.PersonRepository;

/**
 * Since 13.06.13
 *
 * @author Igor Artamonov, http://igorartamonov.com
 */
public class TasksController {

    private static final Logger log = LoggerFactory.getLogger(TasksController.class);
    private PersonRepository personRepository;

    public StringView address(Long id) {
        PersonAddress address = personRepository.getAddress(id);
        log.info("Loading coordinates for " + address);

        if (StringUtils.isEmpty(address.getAddress())) {
            log.warn("Empty address");
            return new StringView("Empty address");
        }
        Geocoder geocoder = new Geocoder();
        GeocoderRequest geocoderRequest = new GeocoderRequestBuilder()
                .setAddress(address.getAddress())
                .setLanguage("en")
                .getGeocoderRequest();
        GeocodeResponse geocoderResponse = geocoder.geocode(geocoderRequest);

        if (!geocoderResponse.getStatus().equals(GeocoderStatus.OK)) {
            log.error("Response: " + geocoderResponse.getStatus());
            return new StringView("Invalid response from Google Geocode");
        }

        if (geocoderResponse.getResults().isEmpty()) {
            log.info("Nothing found for address: " + address.getAddress());
            return new StringView("Nothing found");
        }

        GeocoderResult best = geocoderResponse.getResults().get(0);
        GeoPt point = new GeoPt(best.getGeometry().getLocation().getLat().floatValue(),
                best.getGeometry().getLocation().getLng().floatValue());
        address.setCoord(point);
        personRepository.save(address);
        return new StringView("OK");
    }

    public void setPersonRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
}
