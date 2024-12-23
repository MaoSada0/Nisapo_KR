package ru.qq.mainapi.db.repository.temp;

import org.springframework.stereotype.Component;
import ru.qq.mainapi.db.entity.User;
import ru.qq.mainapi.db.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class TempUserRepository implements UserRepository {

    private final List<User> users = new ArrayList<>();

    @Override
    public User save(User build) {
        users.add(build);
        return build;
    }

    @Override
    public Optional<User> findById(Integer id) {
        return users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public void deleteById(Integer id) {
        var us = users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();

        if(us.isPresent()) {
            users.remove(us);
        }
    }
}
