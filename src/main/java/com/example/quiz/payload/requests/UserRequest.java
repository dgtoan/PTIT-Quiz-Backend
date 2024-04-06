package com.example.quiz.payload.requests;

import com.example.quiz.models.ERole;
import com.example.quiz.models.Role;
import com.example.quiz.models.User;
import com.example.quiz.repositories.RoleRepository;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserRequest {
    private String username;
    private String email;
    private String password;
    private Set<String> roles;
    private String nameSearch;

    public Boolean checkInput() {
        return this.username != null && this.email != null && this.password != null && this.roles != null;
    }

    public Set<Role> getRoles(RoleRepository roleRepository) {
        Set<Role> roles = new HashSet<>();
        for (String role : this.roles) {
            switch (role) {
                case "admin":
                    Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(adminRole);
                    break;
                case "mod":
                    Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(modRole);
                    break;
                default:
                    Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(userRole);
            }
        }
        return roles;
    }

    public User toUser(RoleRepository roleRepository) {
        User user = new User();
        user.setUsername(this.username);
        user.setEmail(this.email);
        user.setPassword(this.password);
        user.setRoles(getRoles(roleRepository));
        return user;
    }
}
