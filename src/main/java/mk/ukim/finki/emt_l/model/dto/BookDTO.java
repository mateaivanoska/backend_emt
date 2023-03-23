package mk.ukim.finki.emt_l.model.dto;

import lombok.Data;
import mk.ukim.finki.emt_l.model.enumm.Category;
@Data
public class BookDTO {
    private String name;
    private Category category;
    private Long author;
    private Integer availableCopies;
}
