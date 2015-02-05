package entities;

import org.eclipse.persistence.nosql.annotations.JoinField;

import javax.persistence.*;
import java.util.List;

@Entity
public class One {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    private String name;

    @OneToMany(cascade = {CascadeType.ALL}) // Not using `mappedBy` because it is not supported by NoSQL DBs
    @JoinField(name = "one_id")             // NoSQL-compatible version of `@JoinColumn`, emulates `mappedBy`
    private List<Many> manies;

    public One() { }

    public long getId() { return id; }
    public String getName() { return name; }
    public One setName(String name) { this.name = name; return this; }
    public List<Many> getManies() { return manies; }
    public One setManies(List<Many> manies) { this.manies = manies; return this; }

    @Override
    public String toString() {
        return "One{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", manies=" + manies +
                '}';
    }
}
