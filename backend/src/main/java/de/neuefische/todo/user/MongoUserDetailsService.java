package de.neuefische.todo.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class MongoUserDetailsService implements UserDetailsService {



    private final UserService userService;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userService.findByName(username)
                .map(userModel -> new User(userModel.getUsername(), userModel.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_" + userModel.getRole()))))
                .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
    }
}



