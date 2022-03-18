package de.neuefische.todo.user;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Data
@RequiredArgsConstructor
public class UserModel {


    @Id
    private String id;
    private String username;
    private String password;
    private String role;

}
