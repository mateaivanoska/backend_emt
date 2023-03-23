package mk.ukim.finki.emt_l.repository;

import mk.ukim.finki.emt_l.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {
    //metodite se predefinirani i nie nemame potreba da gi prezapisuvame
    //pa posle napisi vo service koi se tie metodi
    //ako imase nesto specificno togas ke zapisuvase
}
