package cl.praxis.JPARental.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "film")
public class Film {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="film_id")
  private int id;
  private String title;
  private String description;
  @Column(name="release_year")
  private int year;

  @ManyToOne
  @JoinColumn(name="language_id", insertable = true, updatable = true)
  private Language language;

  public Film() {
  }

  public Film(int id, String title, String description, int year, Language language) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.year = year;
    this.language = language;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public Language getLanguage() {
    return language;
  }

  public void setLanguage(Language language) {
    this.language = language;
  }

  @Override
  public String toString() {
    return "Film{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", description='" + description + '\'' +
            ", year=" + year +
            ", language=" + language.toString() +
            '}';
  }
}

