package pl.polsl.s15.library.api.controller.user.request;

import lombok.Getter;

@Getter
public class AddUserRoleRequest {
    private long userId;
    private String roleName;
}
