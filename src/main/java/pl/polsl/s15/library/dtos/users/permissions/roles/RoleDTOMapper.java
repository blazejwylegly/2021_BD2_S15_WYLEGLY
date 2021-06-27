package pl.polsl.s15.library.dtos.users.permissions.roles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.polsl.s15.library.commons.exceptions.BadRequestException;
import pl.polsl.s15.library.domain.user.account.roles.Role;
import pl.polsl.s15.library.repository.RoleRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RoleDTOMapper {

    private RoleRepository roleRepository;

    @Autowired
    public RoleDTOMapper(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Set<Role> toEntity(Set<RoleDTO> roles) {
        return roles.stream()
                .map(this::toEntity)
                .collect(Collectors.toSet());
    }

    public Role toEntity(RoleDTO roleDTO) {
        return roleRepository
                .findRoleByRoleType(roleDTO.getRoleName())
                .orElseThrow(() -> new BadRequestException("Role "
                        + roleDTO.getRoleName().getValue() +
                        " does not exist"));
    }

    public static Set<RoleDTO> toDTO(Set<Role> roles) {
        return roles.stream()
                .map(RoleDTOMapper::toDTO)
                .collect(Collectors.toSet());
    }

    public static RoleDTO toDTO(Role role) {
        return RoleDTO.builder()
                .id(role.getId())
                .roleName(role.getRoleType())
                .build();
    }

}
