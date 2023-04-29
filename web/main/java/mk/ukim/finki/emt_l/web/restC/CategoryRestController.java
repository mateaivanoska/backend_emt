package mk.ukim.finki.emt_l.web.restC;


import mk.ukim.finki.emt_l.model.enumm.Category;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin("http://localhost:3000")
public class CategoryRestController {

    //site vrednosti da se prikazat, stavi gi vo lista pa prikazi gi vrednostite na enumeracijata
    @GetMapping()
    public List<Category> listAll(){
        return Arrays.asList(Category.values());
    }
}
