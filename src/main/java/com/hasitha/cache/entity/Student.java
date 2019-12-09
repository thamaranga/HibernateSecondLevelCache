package com.hasitha.cache.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity

/*Below two annotations are required for enabling hibernate second level cache for Student object.
 Here CacheConcurrencyStrategy.READ_ONLY means second level cache will only enable for reading
 data from the database.
*/


@Cacheable
@Cache(usage= CacheConcurrencyStrategy.READ_ONLY)

public class Student {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String marks;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
