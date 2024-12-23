package ru.qq.mainapi.db.entity;


import lombok.*;


@EqualsAndHashCode
@Builder
//@Entity
@NoArgsConstructor
@AllArgsConstructor
//@Table(name = "users")
@Data
public class User {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String surname;

    private String username;
}
