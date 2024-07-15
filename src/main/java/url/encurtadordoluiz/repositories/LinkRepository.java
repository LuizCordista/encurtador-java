package url.encurtadordoluiz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import url.encurtadordoluiz.models.Link;

import java.util.Optional;

@Repository
public interface LinkRepository extends JpaRepository<Link, String> {

    Optional<Link> findByShortenedUrl(String url);
}
