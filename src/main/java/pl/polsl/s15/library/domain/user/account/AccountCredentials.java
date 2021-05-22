package pl.polsl.s15.library.domain.user.account;

import lombok.*;
import pl.polsl.s15.library.domain.user.User;

import javax.persistence.*;

@Entity
@Table(name = "credentials")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountCredentials {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String password;

    @Column(nullable = false, unique = true)
    private String salt;

    @Column(nullable = false, unique = true)
    private String emailAddress;

    @OneToOne(mappedBy = "credentials")
    private User user;
}
