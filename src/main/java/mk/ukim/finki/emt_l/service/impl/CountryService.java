package mk.ukim.finki.emt_l.service.impl;


import mk.ukim.finki.emt_l.model.Country;
import mk.ukim.finki.emt_l.model.dto.CountryDTO;
import mk.ukim.finki.emt_l.model.exceptions.CountryNotFoundException;
import mk.ukim.finki.emt_l.repository.CountryRepository;
import mk.ukim.finki.emt_l.service.CountryServiceInterface;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService implements CountryServiceInterface {

    private final CountryRepository countryRepository;


    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }


    public List<Country> listAll() {
        return countryRepository.findAll();
    }

    public Optional<Country> findById(Long id) {
        return countryRepository.findById(id);
    }

    //za add nova kniga, nova country
    public Country addCountry(CountryDTO c1) {
        Country country = new Country();
        country.setName(c1.getName());
        country.setContinent(c1.getContinent());
        return this.countryRepository.save(country);
    }

    //za edit na kniga, sledi edit na avtor, edit na drzaava
    public Country editCountry(Long id, CountryDTO country) {
        //najdi ja drzavata so toa id
        Country foundCountry = this.findById(id).orElseThrow(()->new CountryNotFoundException(id));
        foundCountry.setName(country.getName());
        foundCountry.setContinent(country.getContinent());
        return this.countryRepository.save(foundCountry);
    }

    //za delete na kniga, avtor , drzava
    public void deleteCountry(Long id) {
        this.countryRepository.deleteById(id);

    }

}
