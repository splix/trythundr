package trythundr.data;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Load;

import java.util.Date;

/**
 * Since 13.06.13
 *
 * @author Igor Artamonov, http://igorartamonov.com
 */
@Entity
public class Person {

    @Id
    private Long id;
    @Load
    private Ref<PersonAddress> address;
    private String name;
    private Date createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public PersonAddress getAddress() {
        return address == null ? null : address.get();
    }

    public void setAddress(PersonAddress address) {
        if (address == null) {
            this.address = null;
            return;
        }
        this.address = Ref.create(address);
    }
}
