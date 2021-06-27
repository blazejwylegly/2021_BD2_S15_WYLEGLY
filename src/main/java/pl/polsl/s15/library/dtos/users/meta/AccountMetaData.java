package pl.polsl.s15.library.dtos.users.meta;

import lombok.Builder;
import lombok.Getter;
import pl.polsl.s15.library.dtos.users.permissions.AccountPermissionsDTO;

@Builder
@Getter
public class AccountMetaData {
    private Long userId;
    private AccountPermissionsDTO permissionsDTO;
}
