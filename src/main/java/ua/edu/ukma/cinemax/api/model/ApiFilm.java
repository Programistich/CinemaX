package ua.edu.ukma.cinemax.api.model;

import lombok.*;
import ua.edu.ukma.cinemax.model.Film;

@Data
@NoArgsConstructor
public class ApiFilm {
    private Long id;
    private String title;
    private int releaseYear;
    private String description;
    private Long tmdbId;

    public ApiFilm(Film film) {
        this.id = film.getId();
        this.title = film.getTitle();
        this.releaseYear = film.getReleaseYear();
        this.description = film.getDescription();
        this.tmdbId = film.getTmdbId();
    }

    public Film toModel() {
        Film model = new Film();
        model.setId(id);
        model.setTitle(title);
        model.setReleaseYear(releaseYear);
        model.setDescription(description);
        model.setTmdbId(tmdbId);
        return model;
    }
}
