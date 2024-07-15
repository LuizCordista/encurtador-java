package url.encurtadordoluiz.services;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import url.encurtadordoluiz.models.Link;
import url.encurtadordoluiz.repositories.LinkRepository;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class LinkService {


    private final LinkRepository linkRepository;

    public LinkService(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

     public String generateShorterUrl() {
         String random;
         do {
             random = RandomStringUtils.randomAlphanumeric(5, 15);
         } while (linkRepository.findByShortenedUrl(random).isPresent());
         return random;
     }

     public String shortenUrl (String url) {
         String shorterUrl = generateShorterUrl();
         Link urlObject = new Link();
         urlObject.setOriginalUrl(url);
         urlObject.setShortenedUrl(shorterUrl);
         urlObject.setCreationDate(LocalDateTime.now());
         linkRepository.save(urlObject);
         return shorterUrl;
     }

     public String getOriginalUrl(String shorterUrl) {
        Optional<Link> url = linkRepository.findByShortenedUrl(shorterUrl);
        if (url.isEmpty()) {
            throw new IllegalArgumentException("URL not found");
        }
        if (url.get().getCreationDate().isBefore(LocalDateTime.now().minusDays(7))) {
            linkRepository.delete(url.get());
            throw new IllegalArgumentException("URL expired");
        }
        return url.get().getOriginalUrl();
     }
}
