package um.fds.agl.ter22.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import um.fds.agl.ter22.entities.Group;
import um.fds.agl.ter22.entities.TERProject;

public interface GroupRepository<G extends Group> extends CrudRepository<G, Long>{
    @Override
    Group save(@Param("group") Group group);
    @Override
    void deleteById(@Param("id") Long id);
    @Override
    void delete(@Param("group") Group group);
    //pre authorized à ajouter plus tard
}
