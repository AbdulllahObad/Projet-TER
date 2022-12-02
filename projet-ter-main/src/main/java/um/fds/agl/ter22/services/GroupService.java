package um.fds.agl.ter22.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.fds.agl.ter22.entities.Group;
import um.fds.agl.ter22.repositories.GroupRepository;

import java.util.Optional;

@Service
public class GroupService {
    @Autowired
    private GroupRepository groupRepository;

    public Optional<Group> getGroup(final Long id){
        return groupRepository.findById(id);
    }
    public Iterable<Group> getGroups(){
        return groupRepository.findAll();//problem iz here

    }
    public Group saveGroup(Group group){
        Group savedStudent= groupRepository.save(group);
        return savedStudent;
    }
    //deleteGroup à ajouter plustard

    public Optional<Group> findById(long id){return groupRepository.findById(id);}
}
