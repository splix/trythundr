package trythundr.data;

import com.google.appengine.api.datastore.GeoPt;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;


/**
 * Since 13.06.13
 *
 * @author Igor Artamonov, http://igorartamonov.com
 */
@Entity
public class PersonAddress {

    @Id
    private Long id;
    private String address;
    private GeoPt coord;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public GeoPt getCoord() {
        return coord;
    }

    public void setCoord(GeoPt coord) {
        this.coord = coord;
    }
}
