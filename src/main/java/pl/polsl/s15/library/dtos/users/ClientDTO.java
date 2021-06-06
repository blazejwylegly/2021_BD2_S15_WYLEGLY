package pl.polsl.s15.library.dtos.users;

import lombok.Builder;
import lombok.Getter;
import pl.polsl.s15.library.dtos.reservations.ReservationDTO;
import pl.polsl.s15.library.dtos.users.credentials.AccountCredentialsDTO;
import pl.polsl.s15.library.dtos.users.permissions.AccountPermissionsDTO;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ClientDTO extends UserDTO {
    private Long cartId;
    private List<ReservationDTO> reservations = new ArrayList<>();

    @Builder(builderMethodName = "clientDTOBuilder")
    public ClientDTO(Long id,
                     String firstName,
                     String lastName,
                     String photoUrl,
                     AccountCredentialsDTO accountCredentialsDTO,
                     AccountPermissionsDTO acoAccountPermissionsDTO,
                     Long cartId,
                     List<ReservationDTO> reservations) {
        super(id, firstName, lastName, photoUrl, accountCredentialsDTO, acoAccountPermissionsDTO);
        this.cartId = cartId;
        this.reservations = reservations;
    }

    public ClientDTO(UserDTO userDTO) {
        super(userDTO.getId(),
                userDTO.getFirstName(),
                userDTO.getFirstName(),
                userDTO.getLastName(),
                userDTO.getAccountCredentialsDTO(),
                userDTO.getAccountPermissionsDTO()
        );
    }

}
