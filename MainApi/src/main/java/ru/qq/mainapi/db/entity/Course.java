package ru.qq.mainapi.db.entity;


import lombok.*;

import java.util.Set;

@EqualsAndHashCode
@Builder
//@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Course {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String description;

    private Integer price;

    private Integer creatorId;

    private Set<Integer> userIds;
}
