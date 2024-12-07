package ru.qq.mainapi.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.qq.mainapi.db.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
