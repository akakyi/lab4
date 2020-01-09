package edu.lab.back.db.entity;

import edu.lab.back.dtoPojos.ChangeTypePojo;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "change_type")
@Getter
@Setter
public class ChangeTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ch_type_generator")
    @SequenceGenerator(name = "ch_type_generator", sequenceName = "change_type_id_sequence", allocationSize = 1)
    private Integer id;

    @Column(name = "name")
    private String name;

    public static ChangeTypeEntity convert(final ChangeTypePojo pojo) {
        if (pojo == null) {
            return null;
        }

        final ChangeTypeEntity result = new ChangeTypeEntity();
        result.setId(pojo.getId());
        result.setName(pojo.getName());

        return result;
    }

}
