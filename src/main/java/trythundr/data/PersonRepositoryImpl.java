package trythundr.data;

import static com.googlecode.objectify.ObjectifyService.ofy;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.VoidWork;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * Since 13.06.13
 * @author Igor Artamonov, http://igorartamonov.com
 */
public class PersonRepositoryImpl implements PersonRepository{

    private ObjectifyFactory objectifyFactory;

    public PersonRepositoryImpl() {
        objectifyFactory = new ObjectifyFactory();
        objectifyFactory.register(Person.class);
        objectifyFactory.register(PersonAddress.class);
    }

    @Override
    public List<Person> list() {
        Objectify ob = objectifyFactory.begin();
        List<Person> result = ob.load().type(Person.class).list();
        return result;
    }

    @Override
    public void save(Person person) {
        Objectify ob = objectifyFactory.begin();
        ob.save().entity(person).now();
    }

    @Override
    public void save(PersonAddress address) {
        Objectify ob = objectifyFactory.begin();
        ob.save().entity(address).now();
    }

    @Override
    public void removeAddress(final Person person) {
        final PersonAddress personAddress = person.getAddress();
        if (personAddress == null) {
            return;
        }
        final Objectify ob = objectifyFactory.begin();
        ob.transact(new VoidWork() {
            @Override
            public void vrun() {
                person.setAddress(null);
                ob.save().entity(person);
                ob.delete().entity(personAddress);
            }
        });
    }

    @Override
    public void setAddress(Person person, PersonAddress address) {
        assert address != null;
        assert person != null;
        Objectify ob = objectifyFactory.begin(); //don't think we need a transaction here
        if (address.getId() == null) {
            ob.save().entity(address).now();
        }
        if (person.getAddress() == null || !person.getAddress().getId().equals(address.getId())) {
            person.setAddress(address);
            ob.save().entity(person);
        }
    }

    @Override
    public PersonAddress getAddress(long id) {
        Objectify ob = objectifyFactory.begin();
        return ob.load().type(PersonAddress.class).id(id).now();
    }

    @Override
    public Person get(long id) {
        Objectify ob = objectifyFactory.begin();
        return ob.load().type(Person.class).id(id).now();
    }
}
