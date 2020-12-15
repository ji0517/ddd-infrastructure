package com.xwtec.infrastructure.uid.worker.entity;

import com.xwtec.infrastructure.uid.impl.JpaIdGenerator;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(
        name="ID_TEST_GEN_SN",
        uniqueConstraints=@UniqueConstraint(columnNames={"ID"})
)
public class IdDemoEntity {

    private long id;

    public void setId(long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(generator=JpaIdGenerator.NAME)
    @GenericGenerator(name=JpaIdGenerator.NAME,strategy = JpaIdGenerator.STRATEGY)
    public long getId() {
        return id;
    }
}
