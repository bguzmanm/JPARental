package cl.praxis.JPARental.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name="language")
public class Language {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="language_id")
  private int id;
  private String name;

  public Language() {
  }
  public Language(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Language{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
  }
}
