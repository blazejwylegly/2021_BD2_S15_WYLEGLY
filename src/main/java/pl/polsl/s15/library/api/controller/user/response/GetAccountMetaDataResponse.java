package pl.polsl.s15.library.api.controller.user.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import pl.polsl.s15.library.api.controller.base.response.ResponseDTO;
import pl.polsl.s15.library.dtos.users.permissions.AccountPermissionsDTO;

import java.util.Date;

@Getter
@AllArgsConstructor
public class GetAccountMetaDataResponse extends ResponseDTO {

    private long userId;
    private AccountPermissionsDTO permissions;

    @Builder(builderMethodName = "getAccountMetaDataResponseBuilder")
    public GetAccountMetaDataResponse(HttpStatus status,
                                      String message,
                                      Date timestamp,
                                      long userId,
                                      AccountPermissionsDTO permissions) {
        super(status, message, timestamp);
        this.userId = userId;
        this.permissions = permissions;
    }
}
