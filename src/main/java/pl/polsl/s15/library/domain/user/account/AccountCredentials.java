package pl.polsl.s15.library.domain.user.account;

import lombok.Getter;
import lombok.Setter;
import pl.polsl.s15.library.domain.user.User;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "credentials")
public class AccountCredentials {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false, unique = true, name = "encoced_password")
    private String encodedPassword;

    @Column(nullable = false, unique = true)
    private String salt;

    @Column(nullable = false, unique = true, name = "email_address")
    private String emailAddress;

    @OneToOne(mappedBy = "credentials")
    private User user;
}
