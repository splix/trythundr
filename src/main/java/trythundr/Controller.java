package trythundr;

import com.threewks.thundr.http.exception.HttpStatusException;
import com.threewks.thundr.view.jsp.JspView;
import com.threewks.thundr.view.redirect.RedirectView;
import com.threewks.thundr.view.string.StringView;
import org.apache.commons.lang3.StringUtils;
import trythundr.data.Person;
import trythundr.data.PersonAddress;
import trythundr.data.PersonRepository;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Since 13.06.13
 *
 * @author Igor Artamonov, http://igorartamonov.com
 */
public class Controller {

    private PersonRepository personRepository;
    private Tasks tasks;

    public JspView home() {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("current", personRepository.list());
        return new JspView("home.jsp", model);
    }

    public JspView addShow() {
        Map<String, Object> model = new HashMap<String, Object>();
        return new JspView("add.jsp", model);
    }

    public Object addSave(String name, String address) {
        Map<String, Object> model = new HashMap<String, Object>();
        if (StringUtils.isEmpty(name)) {
            model.put("error", "You have to specify person's name");
            return new JspView("add.jsp", model);
        }
        Person person = new Person();
        person.setName(name);
        personRepository.save(person);
        if (StringUtils.isNotEmpty(address)) {
            PersonAddress personAddress = new PersonAddress();
            personAddress.setAddress(address);
            personRepository.setAddress(person, personAddress);
            tasks.loadCoord(personAddress);
        }
        return new RedirectView("/");
    }

    public JspView show(Long id) {
        Person person = personRepository.get(id);
        if (person == null) {
            throw new HttpStatusException(HttpServletResponse.SC_NOT_FOUND, "Person not found %d", id);
        }
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("person", person);
        return new JspView("show.jsp", model);
    }
    public JspView editShow(Long id) {
        Person person = personRepository.get(id);
        if (person == null) {
            throw new HttpStatusException(HttpServletResponse.SC_NOT_FOUND, "Person not found %d", id);
        }
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("person", person);
        return new JspView("edit.jsp", model);
    }
    public Object editSave(Long id, String name, String address) {
        Person person = personRepository.get(id);
        if (person == null) {
            throw new HttpStatusException(HttpServletResponse.SC_NOT_FOUND, "Person not found %d", id);
        }
        Map<String, Object> model = new HashMap<String, Object>();
        if (StringUtils.isEmpty(name)) {
            model.put("error", "You have to specify person's name");
            Map<String, String> command = new HashMap<String, String>();
            command.put("name", name);
            command.put("address", address);
            model.put("person", command);
            return new JspView("edit.jsp", model);
        }
        if (!name.equals(person.getName())) {
            person.setName(name);
            personRepository.save(person);
        }
        if (StringUtils.isNotEmpty(address)) {
            if (person.getAddress() == null) {
                PersonAddress personAddress = new PersonAddress();
                personAddress.setAddress(address);
                personRepository.setAddress(person, personAddress);
                tasks.loadCoord(personAddress);
            } else if (!person.getAddress().getAddress().equals(address)) {
                PersonAddress personAddress = person.getAddress();
                personAddress.setAddress(address);
                personAddress.setCoord(null);
                personRepository.save(personAddress);
                tasks.loadCoord(personAddress);
            }
        } else if (person.getAddress() != null) {
            personRepository.removeAddress(person);
        }
        return new RedirectView("/");
    }

    public void setPersonRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void setTasks(Tasks tasks) {
        this.tasks = tasks;
    }
}
