package com.sina.entity;

import com.sina.enums.RoleEnum;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "category")
@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 20)
    @Column(length = 20)
    private String name;

    public Category(String name) {
        this.name = name;
    }
}
