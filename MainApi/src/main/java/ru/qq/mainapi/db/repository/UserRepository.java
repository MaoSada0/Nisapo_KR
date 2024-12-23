package ru.qq.mainapi.db.repository;


import ru.qq.mainapi.db.entity.User;

import java.util.List;
import java.util.Optional;

//@Repository
public interface UserRepository /* extends JpaRepository<User, Integer> */ {

    User save(User build);

    Optional<User> findById(Integer id);

    List<User> findAll();

    void deleteById(Integer id);
}
