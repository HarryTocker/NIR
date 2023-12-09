package com.find.law.portal.security;

import com.find.law.portal.repositories.UserRepository;
import com.find.law.portal.repositories.entities.users.UserEntity;
import com.find.law.portal.repositories.entities.users.UserRoleEntity;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class DatabaseUserDetailsManager implements UserDetailsManager {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public DatabaseUserDetailsManager(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            UserEntity user = userRepository.findById(username).orElseThrow(() -> new EntityNotFoundException("User [%s] not found in database".formatted(username)));
            return User.withUsername(user.getUsername())
                    .password(user.getPassword())
                    .roles(user.getRoles().stream().map(UserRoleEntity::getRole).toArray(String[]::new))
                    .build();
        } catch (Throwable cause) {
            throw new UsernameNotFoundException("Could not find user [%s]".formatted(username), cause);
        }
    }

    @Override
    public void createUser(UserDetails user) {
        throw new UnsupportedOperationException("CUD operations not supported");
    }

    @Override
    public void updateUser(UserDetails user) {
        throw new UnsupportedOperationException("CUD operations not supported");
    }

    @Override
    public void deleteUser(String username) {
        throw new UnsupportedOperationException("CUD operations not supported");
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        throw new UnsupportedOperationException("Password change not supported");
    }

    @Override
    public boolean userExists(String username) {
        return userRepository.existsById(username);
    }
}
