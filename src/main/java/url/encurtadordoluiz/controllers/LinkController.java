package url.encurtadordoluiz.controllers;


import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;
import url.encurtadordoluiz.dtos.LinkRequest;
import url.encurtadordoluiz.services.LinkService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/")
public class LinkController {

    private final LinkService linkService;

    public LinkController(LinkService linkService, HttpServletResponse httpServletResponse) {
        this.linkService = linkService;
    }

    @PostMapping("shorten-url")
    public String shortenUrl(@RequestBody LinkRequest linkRequest) {
        String shortenUrl = linkService.shortenUrl(linkRequest.getUrl());
        return "http://localhost:8080/" + shortenUrl;
    }


    @GetMapping("get/{url}")
    public String getOriginalUrl(@PathVariable String url) {
        return linkService.getOriginalUrl(url);
    }

    @GetMapping("/{shortUrl}")
    public void redirectToOriginalUrl(@PathVariable String shortUrl, HttpServletResponse response) throws Exception {
        String originalUrl = linkService.getOriginalUrl(shortUrl);
        response.sendRedirect(originalUrl);
    }
}
