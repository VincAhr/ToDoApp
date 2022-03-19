package de.neuefische.todo.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserModel createUser(UserModel user){
        return userRepository.save(user);
    }

    public Optional<UserModel> findByName(String username) {
        return userRepository.findByUsername(username);
    }

}
