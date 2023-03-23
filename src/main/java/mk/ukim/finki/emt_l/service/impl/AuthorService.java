package mk.ukim.finki.emt_l.service.impl;


import mk.ukim.finki.emt_l.model.Author;
import mk.ukim.finki.emt_l.model.Country;
import mk.ukim.finki.emt_l.model.dto.AuthorDTO;
import mk.ukim.finki.emt_l.model.exceptions.AuthorNotFoundException;
import mk.ukim.finki.emt_l.model.exceptions.CountryNotFoundException;
import mk.ukim.finki.emt_l.repository.AuthorRepository;
import mk.ukim.finki.emt_l.service.AuthorServiceInterface;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService implements AuthorServiceInterface {
    private final AuthorRepository authorRepository;
    private final CountryService countryService;

    public AuthorService(AuthorRepository authorRepository, CountryService countryService) {
        this.authorRepository = authorRepository;
        this.countryService = countryService;
    }

    //izlistaj gi site
    public List<Author> listAll() {
        return authorRepository.findAll();
    }

    //po id
    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }


    //funkcija
    private Author saveHere(AuthorDTO authorDTO, Author novA) {
        //najdi ja drzavata
        Country foundCountry = countryService.findById(authorDTO.getCountry()).orElseThrow(() -> new CountryNotFoundException(authorDTO.getCountry()));
        novA.setName(authorDTO.getName());
        novA.setSurname(authorDTO.getSurname());
        novA.setCountry(foundCountry);
        return authorRepository.save(novA);
    }


    //add new
    public Author addAuthor(AuthorDTO author) {
        Author novA = new Author();
        return this.saveHere(author, novA);
    }


    //edit
    public Author editAuthor(Long id, AuthorDTO author) {
        //najdi go spored id
        Author foundAuthor = this.findById(id).orElseThrow(()->new AuthorNotFoundException(id));
        return this.saveHere(author, foundAuthor);
    }

    //delete
    public void deleteAuthor(Long id) {
        this.authorRepository.deleteById(id);
    }
}
