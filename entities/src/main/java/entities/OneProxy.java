package entities;

import org.eclipse.persistence.nosql.annotations.DataFormatType;
import org.eclipse.persistence.nosql.annotations.Field;
import org.eclipse.persistence.nosql.annotations.JoinField;
import org.eclipse.persistence.nosql.annotations.NoSql;

import javax.persistence.*;
import java.util.List;

@Entity
@NoSql(dataFormat = DataFormatType.MAPPED)
public class OneProxy {
    @Id @GeneratedValue
    @Field(name = "_id")
    private String id;

    @OneToOne(cascade = CascadeType.ALL)
    private One peer;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Many> manies;

    public OneProxy() { }

    public One getPeer() { return peer; }
    public OneProxy setPeer(One one) { this.peer = one; return this; }
    public List<Many> getManies() { return manies; }
    public OneProxy setManies(List<Many> manies) { this.manies = manies; return this; }
}
