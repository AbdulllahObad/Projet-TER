package um.fds.agl.ter22.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;

import um.fds.agl.ter22.entities.TERProject;

public interface TERProjectRepository<T extends TERProject> extends CrudRepository<T, Long> {

	@Override
	@PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_TEACHER')")
	TERProject save(@Param("terProject") TERProject terProject);

	@Override

	@PreAuthorize("hasRole('ROLE_MANAGER') or (#terproject?.teacher == null or #terproject?.teacher?.lastName == authentication?.name)")
	void delete(@Param("terProject") TERProject terProject);

	@Override
	@PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_TEACHER')")
	void deleteById(@Param("id") Long id);
}
