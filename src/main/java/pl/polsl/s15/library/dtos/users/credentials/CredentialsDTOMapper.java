package pl.polsl.s15.library.dtos.users.credentials;

import pl.polsl.s15.library.domain.user.account.AccountCredentials;

public class CredentialsDTOMapper {
    public static AccountCredentials toEntity(AccountCredentialsDTO accountCredentialsDTO) {
        return AccountCredentials.builder()
                .id(accountCredentialsDTO.getId())
                .username(accountCredentialsDTO.getUsername())
                .password(accountCredentialsDTO.getPassword())
                .emailAddress(accountCredentialsDTO.getEmailAddress())
                .build();
    }

    public static AccountCredentialsDTO toDTO(AccountCredentials accountCredentials) {
        return AccountCredentialsDTO.builder()
                .id(accountCredentials.getId())
                .username(accountCredentials.getUsername())
                .password(accountCredentials.getPassword())
                .emailAddress(accountCredentials.getEmailAddress())
                .build();
    }
}
