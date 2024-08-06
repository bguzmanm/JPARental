package cl.praxis.JPARental.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "film")
@AllArgsConstructor
@NoArgsConstructor
@Data
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

  /*
    join film_category
      on (film.film_id = film_category.film_id)
    join category
      on (category.category_id = film_category.category_id)
   */
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name="film_category",
          joinColumns = @JoinColumn(name="film_id", referencedColumnName = "film_id"),
          inverseJoinColumns = @JoinColumn(name="category_id", referencedColumnName = "category_id")
  )
  private List<Category> categories;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name="film_actor",
    joinColumns = @JoinColumn(name="film_id", referencedColumnName = "film_id"),
    inverseJoinColumns = @JoinColumn(name="actor_id", referencedColumnName = "actor_id")
  )
  private List<Actor> actors;


}

