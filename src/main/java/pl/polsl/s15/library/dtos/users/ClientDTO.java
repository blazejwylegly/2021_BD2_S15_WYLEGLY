package pl.polsl.s15.library.dtos.users;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.polsl.s15.library.dtos.ordering.CartDTO;
import pl.polsl.s15.library.dtos.reservations.ReservationDTO;
import pl.polsl.s15.library.dtos.users.credentials.AccountCredentialsDTO;
import pl.polsl.s15.library.dtos.users.permissions.AccountPermissionsDTO;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ClientDTO extends UserDTO {

    @Setter
    private CartDTO cartDTO;
    private List<ReservationDTO> reservations = new ArrayList<>();

    @Builder(builderMethodName = "clientDTOBuilder")
    public ClientDTO(Long id,
                     String firstName,
                     String lastName,
                     String photoUrl,
                     AccountCredentialsDTO accountCredentialsDTO,
                     AccountPermissionsDTO accountPermissionsDTO,
                     CartDTO cartDTO,
                     List<ReservationDTO> reservations) {
        super(id, firstName, lastName, photoUrl, accountCredentialsDTO, accountPermissionsDTO);
        this.cartDTO = cartDTO;
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
