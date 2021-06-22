package pl.polsl.s15.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PermissionService {
    private AuthorityService authorityService;
    private RoleService roleService;

    @Autowired
    public PermissionService(AuthorityService authorityService,
                             RoleService roleService) {
        this.authorityService = authorityService;
        this.roleService = roleService;
    }
}
