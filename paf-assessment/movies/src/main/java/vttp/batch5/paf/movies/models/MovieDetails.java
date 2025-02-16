package vttp.batch5.paf.movies.models;

import java.util.List;

public class MovieDetails {

    private String imdbId;
    private String title;
    private String overview;
    private String tagline;
    private int imdbRating;
    private long imdbVotes;

    private List<String> directors;
    private List<String> genres;

    public String getImdbId() { return imdbId; }
    public void setImdbId(String imdbId) { this.imdbId = imdbId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getOverview() { return overview; }
    public void setOverview(String overview) { this.overview = overview; }

    public String getTagline() { return tagline; }
    public void setTagline(String tagline) { this.tagline = tagline; }

    public int getImdbRating() { return imdbRating; }
    public void setImdbRating(int imdbRating) { this.imdbRating = imdbRating; }

    public long getImdbVotes() { return imdbVotes; }
    public void setImdbVotes(long imdbVotes) { this.imdbVotes = imdbVotes; }

    public List<String> getDirectors() { return directors; }
    public void setDirectors(List<String> directors) { this.directors = directors; }

    public List<String> getGenres() { return genres; }
    public void setGenres(List<String> genres) { this.genres = genres; }

    @Override
    public String toString() {
        return "MovieDetails{imdb_id=%s, title=%s}".formatted(imdbId, title);
    }
}
