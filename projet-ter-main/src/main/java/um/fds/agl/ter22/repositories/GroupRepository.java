package um.fds.agl.ter22.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import um.fds.agl.ter22.entities.Group;
import um.fds.agl.ter22.entities.TERProject;

public interface GroupRepository<G extends Group> extends CrudRepository<G, Long>{
    TERProject save(@Param("terProject") TERProject terProject);
}
