package trythundr;

import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskAlreadyExistsException;
import com.google.appengine.api.taskqueue.TaskOptions;
import com.google.appengine.api.taskqueue.TransientFailureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import trythundr.data.PersonAddress;

/**
 * Since 13.06.13
 *
 * @author Igor Artamonov, http://igorartamonov.com
 */
public class TasksImpl implements Tasks {

    private static final Logger log = LoggerFactory.getLogger(TasksImpl.class);

    @Override
    public void loadCoord(PersonAddress address) {
        try {
            QueueFactory.getDefaultQueue().add(
                    TaskOptions.Builder.withUrl("/_task/address")
                            .method(TaskOptions.Method.POST)
                            .param("id", address.getId().toString())
            );
        } catch (TaskAlreadyExistsException e) {
            log.warn("Already processing " + address.getId());
        } catch (TransientFailureException e) {
            log.error("Cannot add task: " + e.getMessage(), e);
        }
    }

}
