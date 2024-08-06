package cl.praxis.JPARental.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="category")
public class Category {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="category_id")
  private int id;

  private String name;

  @ManyToMany(mappedBy = "categories")
  private List<Film> films;
}
