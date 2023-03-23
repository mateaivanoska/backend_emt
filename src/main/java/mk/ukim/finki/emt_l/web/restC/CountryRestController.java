package mk.ukim.finki.emt_l.web.restC;


import mk.ukim.finki.emt_l.model.Country;
import mk.ukim.finki.emt_l.model.dto.CountryDTO;
import mk.ukim.finki.emt_l.model.exceptions.CountryNotFoundException;
import mk.ukim.finki.emt_l.service.impl.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/country")
public class CountryRestController {

    private final CountryService countryService;

    public CountryRestController(CountryService countryService) {
        this.countryService = countryService;
    }


    @GetMapping("/countryList")
    //site da se izlistaat
    public List<Country> listAll() {
        return this.countryService.listAll();
    }

    //da se najdat po id, id kako path variable

    @GetMapping("/{id}")
    public ResponseEntity<Country> findById(@PathVariable Long id) {
        return this.countryService.findById(id).
                map(country -> ResponseEntity.ok().body(country))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    //za dodavanje

    @PostMapping("/add")
    public ResponseEntity<Country> create(@RequestBody CountryDTO countryDTO) {
        Country novaC = countryService.addCountry(countryDTO);
        if (novaC == null) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(novaC);
        }

    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Country> update(@PathVariable Long id, @RequestBody CountryDTO countryDTO) {
        //prvo najdi ja sporeed id
        Country najdiC = this.countryService.editCountry(id, countryDTO);
        if (najdiC == null) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok().body(najdiC);
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Country> delete(@PathVariable Long id) {
        Country najdiC = this.countryService.findById(id).orElseThrow(() -> new CountryNotFoundException(id));

        if (najdiC == null) {
            return ResponseEntity.notFound().build();
        } else {
            //ako e najdena brisi
            this.countryService.deleteCountry(id);
            return ResponseEntity.ok().build();
        }
    }
}
