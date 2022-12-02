package um.fds.agl.ter22.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import um.fds.agl.ter22.entities.Group;

@NoRepositoryBean
public interface GroupBaseRepository<T extends Group>
        extends CrudRepository<T, Long> {

    public T findByGroupName(String nom);
}
