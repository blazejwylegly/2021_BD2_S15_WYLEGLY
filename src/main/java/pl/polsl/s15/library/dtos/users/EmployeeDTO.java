package pl.polsl.s15.library.dtos.users;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.polsl.s15.library.dtos.ordering.CartDTO;
import pl.polsl.s15.library.dtos.reservations.ReservationDTO;
import pl.polsl.s15.library.dtos.users.credentials.AccountCredentialsDTO;
import pl.polsl.s15.library.dtos.users.permissions.AccountPermissionsDTO;

import java.util.List;

@Getter
@Setter
public class EmployeeDTO extends UserDTO {
    @Builder(builderMethodName = "employeeDTOBuilder")
    public EmployeeDTO(Long id,
                     String firstName,
                     String lastName,
                     String photoUrl,
                     AccountCredentialsDTO accountCredentialsDTO,
                     AccountPermissionsDTO accountPermissionsDTO) {
        super(id, firstName, lastName, photoUrl, accountCredentialsDTO, accountPermissionsDTO);

    }

    public EmployeeDTO(UserDTO userDTO) {
        super(userDTO.getId(),
                userDTO.getFirstName(),
                userDTO.getFirstName(),
                userDTO.getLastName(),
                userDTO.getAccountCredentialsDTO(),
                userDTO.getAccountPermissionsDTO()
        );
    }
}
