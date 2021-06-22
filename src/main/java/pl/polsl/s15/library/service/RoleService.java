package pl.polsl.s15.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.polsl.s15.library.domain.user.account.roles.RoleType;
import pl.polsl.s15.library.dtos.users.permissions.roles.RoleDTO;
import pl.polsl.s15.library.dtos.users.permissions.roles.RoleDTOMapper;
import pl.polsl.s15.library.repository.RoleRepository;
import pl.polsl.s15.library.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class RoleService {

    private RoleRepository roleRepository;
    private UserRepository userRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    public Set<RoleDTO> getUserRoles(Long userId) {
        return userRepository.findById(userId)
                .map(user -> user.getPermissions().getRoles())
                .map(RoleDTOMapper::toDTO)
                .orElse(Collections.emptySet());
    }

    public void deleteRoleById(long roleId) {
        roleRepository.deleteById(roleId);
    }

    public void deleteRoleByName(String roleName) {
        roleRepository.deleteByRoleType(RoleType.valueOf(roleName));
    }

    public Set<RoleDTO> getAll() {
        return roleRepository.findAll()
                .stream()
                .map(RoleDTOMapper::toDTO)
                .collect(Collectors.toSet());
    }

    public Optional<RoleDTO> getRoleByName(String roleName) {
        return roleRepository.findRoleByRoleType(RoleType.valueOf(roleName))
                .map(RoleDTOMapper::toDTO);
    }
}
