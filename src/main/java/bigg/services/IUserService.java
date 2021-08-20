package bigg.services;

import bigg.model.User;

import java.util.Optional;

public interface IUserService {
    Iterable<User> findAll();
    Optional<User> findById(Integer id);
    void save(User user);
    void delete(Integer id);
}
