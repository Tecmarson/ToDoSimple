package com.api.todosimple.servecies;

import com.api.todosimple.models.User;
import com.api.todosimple.repositories.TaskRepository;
import com.api.todosimple.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findById(Long id) {
        Optional<User> user = this.userRepository.findById(id);

        return user.orElseThrow(() -> new RuntimeException(
                "Usuário não encontrado! id : " + id + ", Tipo: " + User.class.getName()
        ));
    }

    @Transactional
    public User create(User obj) {

        obj.setId(null);
        obj = this.userRepository.save(obj);

        return obj;
    }

    @Transactional
    public User update(User obj) {

        User newObj = findById(obj.getId());
        newObj.setPassword(obj.getPassword());

        return this.userRepository.save(newObj);
    }

    @Transactional
    public void delete(Long id) {
        findById(id);
        try {
            this.userRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir pois há enteidades relacionadas!");
        }
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

}
