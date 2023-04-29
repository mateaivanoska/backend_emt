package mk.ukim.finki.emt_l.repository;

import mk.ukim.finki.emt_l.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country,Long> {
}
