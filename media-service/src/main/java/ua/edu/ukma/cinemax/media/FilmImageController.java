package ua.edu.ukma.cinemax.media;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/media")
public class FilmImageController {
    private final ImageService imageService;
    private static final Logger LOGGER = LoggerFactory.getLogger(FilmImageController.class);

    public FilmImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/film-image-url")
    public ResponseEntity<FilmImageUrlDto> getFilmImageURL(@RequestParam("id") Long id) {
        LOGGER.info("Getting message with id: " + id);
        return ResponseEntity.ok(new FilmImageUrlDto(imageService.getImageLink(id)));
    }

    @GetMapping(
            value = "/film-image",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public @ResponseBody byte[] getFilmImage(@RequestParam("id") Long id) {
        LOGGER.info("Getting message with id: " + id);
        return imageService.getFilmImageById(id);
    }
}
