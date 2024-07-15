package url.encurtadordoluiz.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;


@Entity
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String originalUrl;
    private String shortenedUrl;
    private LocalDateTime creationDate;


    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String urlBefore) {
        this.originalUrl = urlBefore;
    }

    public String getShortenedUrl() {
        return shortenedUrl;
    }

    public void setShortenedUrl(String urlAfter) {
        this.shortenedUrl = urlAfter;
    }
}
