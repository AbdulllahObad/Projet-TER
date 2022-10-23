package um.fds.agl.ter22.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.fds.agl.ter22.entities.TERProject;
import um.fds.agl.ter22.repositories.TERProjectRepository;
import java.util.Optional;

@Service
public class TERProjectService {

	@Autowired
	private TERProjectRepository terProjectRepository;

	public Optional<TERProject> getProject(final Long id) {
		return terProjectRepository.findById(id);
	}

	public Iterable<TERProject> getProjects() {
		return terProjectRepository.findAll();
	}

	public void deleteProject(final Long id) {
		terProjectRepository.deleteById(id);
	}

	public TERProject saveProject(TERProject terProject) {
		TERProject savedProject = terProjectRepository.save(terProject);
		return savedProject;
	}

	public Optional<TERProject> findById(long id) {
		return terProjectRepository.findById(id);
	}

}
