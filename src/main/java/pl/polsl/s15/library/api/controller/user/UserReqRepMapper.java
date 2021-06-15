package pl.polsl.s15.library.api.controller.user;

import org.springframework.http.HttpStatus;
import pl.polsl.s15.library.api.controller.user.response.GetAccountMetaDataResponse;
import pl.polsl.s15.library.api.controller.user.response.GetAllUsersResponse;
import pl.polsl.s15.library.dtos.users.UserDTO;
import pl.polsl.s15.library.dtos.users.meta.AccountMetaData;
import pl.polsl.s15.library.dtos.users.permissions.AccountPermissionsDTO;

import java.util.Date;
import java.util.List;

public class UserReqRepMapper {
    public static GetAllUsersResponse getAllUsersResponse(List<UserDTO> users) {
        return GetAllUsersResponse.getUsersResponseBuilder()
                .status(HttpStatus.OK)
                .message("Successfully retrieved all users")
                .timestamp(new Date())
                .users(users)
                .build();
    }

    public static GetAccountMetaDataResponse getAccountMetaDataResponse(AccountMetaData accountMetaData) {
        return GetAccountMetaDataResponse.getAccountMetaDataResponseBuilder()
                .status(HttpStatus.OK)
                .message("Successfully retrieved account meta data")
                .timestamp(new Date())
                .userId(accountMetaData.getUserId())
                .permissions(accountMetaData.getPermissionsDTO())
                .build();
    }
}
