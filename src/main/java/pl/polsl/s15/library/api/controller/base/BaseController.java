package pl.polsl.s15.library.api.controller.base;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class BaseController {
    protected Authentication getCurrentUser() {
        return SecurityContextHolder
                .getContext()
                .getAuthentication();
    }
}
