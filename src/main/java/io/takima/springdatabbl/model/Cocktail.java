package io.takima.springdatabbl.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.BatchSize;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.proxy.HibernateProxy;

import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
//@AuditTable(value = "audit_cocktail")
@FieldNameConstants
@Accessors(chain = true)
@NamedQuery(
        name = "Cocktail.findByNameByNamedQuery",
        query = "SELECT c FROM Cocktail c WHERE c.name = :name"
)
public class Cocktail {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Audited
    private String name;

    @ToString.Exclude
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.LAZY)
    @BatchSize(size = 2)
    private List<Ingredient> ingredients;

    @ToString.Exclude
//    @ManyToOne(fetch = FetchType.EAGER) // getReference nok
    @ManyToOne(fetch = FetchType.LAZY) // getReference ok
    private Barman barman;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Cocktail cocktail = (Cocktail) o;
        return getId() != null && Objects.equals(getId(), cocktail.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
