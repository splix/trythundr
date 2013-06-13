package trythundr;

import com.threewks.thundr.injection.BaseInjectionConfiguration;
import com.threewks.thundr.injection.UpdatableInjectionContext;
import trythundr.data.PersonRepository;
import trythundr.data.PersonRepositoryImpl;

/**
 * Since 13.06.13
 *
 * @author Igor Artamonov, http://igorartamonov.com
 */
public class InjectionConfiguration extends BaseInjectionConfiguration {

    @Override
    public void configure(UpdatableInjectionContext updatableInjectionContext) {
        updatableInjectionContext.inject(PersonRepositoryImpl.class).as(PersonRepository.class);
        updatableInjectionContext.inject(TasksImpl.class).as(Tasks.class);
    }

}
