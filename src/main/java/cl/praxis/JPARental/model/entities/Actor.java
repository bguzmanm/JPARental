package cl.praxis.JPARental.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Actor {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="actor_id")
  private int id;
  private String firstName;
  private String lastName;

  @ManyToMany(mappedBy = "actors")
  private List<Film> films;

}
