package mk.ukim.finki.emt_l.model.dto;

import jakarta.persistence.ManyToOne;
import lombok.Data;
import mk.ukim.finki.emt_l.model.Country;

@Data
public class AuthorDTO {
    private String name;
    private String surname;
    private Long country;
    //najlesno da se zema drzavata preku id
}
