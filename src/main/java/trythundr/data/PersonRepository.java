package trythundr.data;

import java.util.List;

/**
 * Since 13.06.13
 *
 * @author Igor Artamonov, http://igorartamonov.com
 */
public interface PersonRepository {

    public List<Person> list();
    public void save(Person person);
    public void save(PersonAddress address);
    public void removeAddress(Person person);
    public void setAddress(Person person, PersonAddress address);
    public PersonAddress getAddress(long id);
    public Person get(long id);

}
