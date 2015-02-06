package entities;

import org.eclipse.persistence.nosql.annotations.DataFormatType;
import org.eclipse.persistence.nosql.annotations.Field;
import org.eclipse.persistence.nosql.annotations.NoSql;

import javax.persistence.*;

@Entity
@NoSql(dataFormat = DataFormatType.MAPPED)
public class Many {
    @Id @GeneratedValue
    @Field(name = "_id")
    private String id;

    @Basic
    private String name;

    @ManyToOne
    private OneProxy one;

    public Many() { }

    public String getName() { return name; }
    public Many setName(String name) { this.name = name; return this; }
    public One getOne() { return one.getPeer(); }
    public Many setOne(One one) { this.one = one.getPeer(); return this; }

    @Override
    public String toString() {
        return "Many{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
