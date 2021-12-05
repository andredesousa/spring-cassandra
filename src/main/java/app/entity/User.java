package app.entity;

import javax.validation.constraints.NotNull;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table
public class User {

    @Column
    @PrimaryKey
    public Integer id;

    @NotNull
    @Column
    public String username;

    @NotNull
    @Column
    public String email;
}
