package entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class One {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "peer_id")   // Forces the use of the RDB-conventional name for the ID field
    private OneProxy peer = new OneProxy().setPeer(this);

    public One() { }

    public long getId() { return id; }
    public String getName() { return name; }
    public One setName(String name) { this.name = name; return this; }
    public List<Many> getManies() { return peer.getManies(); }
    public One setManies(List<Many> manies) { peer.setManies(manies); return this; }

    public OneProxy getPeer() { return peer; }
    // No `setPeer`: The peer is a mirror of this `One` so it should not be managed externally.

    @Override
    public String toString() {
        return "One{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", manies=" + peer.getManies() +
                '}';
    }
}
