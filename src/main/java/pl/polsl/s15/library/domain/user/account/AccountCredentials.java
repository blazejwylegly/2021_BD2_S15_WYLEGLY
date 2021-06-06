package pl.polsl.s15.library.domain.user.account;

import lombok.*;
import pl.polsl.s15.library.domain.user.User;
import pl.polsl.s15.library.dtos.users.credentials.AccountCredentialsDTO;

import javax.persistence.*;

@Entity
@Table(name = "credentials")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountCredentials {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String password;

    @Column(unique = true)
    private String emailAddress;

    @OneToOne(mappedBy = "credentials")
    private User user;

    public static AccountCredentials of(AccountCredentialsDTO accountCredentialsDTO) {
        return AccountCredentials.builder()
                .id(accountCredentialsDTO.getId())
                .username(accountCredentialsDTO.getUsername())
                .password(accountCredentialsDTO.getPassword())
                .emailAddress(accountCredentialsDTO.getEmailAddress())
                .build();
    }
}
