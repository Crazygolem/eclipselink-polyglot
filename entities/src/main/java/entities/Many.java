package entities;

import javax.persistence.*;

@Entity
public class Many {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    private String name;

    @ManyToOne
    private One one;

    public Many() { }

    public String getName() { return name; }
    public Many setName(String name) { this.name = name; return this; }
    public One getOne() { return one; }
    public Many setOne(One one) { this.one = one; return this; }

    @Override
    public String toString() {
        return "Many{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
