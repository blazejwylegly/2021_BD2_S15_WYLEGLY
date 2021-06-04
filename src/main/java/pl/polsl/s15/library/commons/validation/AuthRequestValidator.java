package pl.polsl.s15.library.commons.validation;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import pl.polsl.s15.library.commons.annotations.AuthRequest;
import pl.polsl.s15.library.dtos.login.AuthRequestDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@Slf4j
public class AuthRequestValidator implements
        ConstraintValidator<AuthRequest, AuthRequestDTO> {

    @Override
    public void initialize(AuthRequest authRequest) {
    }

    @Override
    public boolean isValid(AuthRequestDTO authRequestDTO, ConstraintValidatorContext constraintValidatorContext) {
        return requestContainsEmailOrUsername(authRequestDTO) && requestContainsPassword(authRequestDTO);
    }

    private boolean requestContainsEmailOrUsername(AuthRequestDTO authRequestDTO) {
        log.info("Validating authRequest");
        return StringUtils.isNotEmpty(authRequestDTO.getEmail()) ||
                StringUtils.isNotEmpty(authRequestDTO.getUsername());
    }

    private boolean requestContainsPassword(AuthRequestDTO authRequestDTO) {
        return StringUtils.isNotBlank(authRequestDTO.getPassword());
    }
}
