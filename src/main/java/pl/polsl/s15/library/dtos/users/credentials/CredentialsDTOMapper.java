package pl.polsl.s15.library.dtos.users.credentials;

import pl.polsl.s15.library.domain.user.account.AccountCredentials;

public class CredentialsDTOMapper {
    public static AccountCredentials credentialsDTOtoEntity(AccountCredentialsDTO accountCredentialsDTO) {
        return AccountCredentials.builder()
                .id(accountCredentialsDTO.getId())
                .username(accountCredentialsDTO.getUsername())
                .password(accountCredentialsDTO.getPassword())
                .emailAddress(accountCredentialsDTO.getEmailAddress())
                .build();
    }
}
