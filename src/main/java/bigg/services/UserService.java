package bigg.services;

import bigg.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bigg.repository.IUserRepository;

import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    IUserRepository iUserRepository;

    @Override
    public Iterable<User> findAll() {
        return iUserRepository.findAll();
    }

    @Override
    public Optional<User> findById(Integer id) {
        return iUserRepository.findById(id);
    }

    @Override
    public void save(User user) {
        iUserRepository.save(user);
    }

    @Override
    public void delete(Integer id) {
        iUserRepository.deleteById(id);
    }
}
