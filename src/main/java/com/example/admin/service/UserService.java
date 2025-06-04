package com.example.admin.service;

import com.example.admin.model.dto.UserAllDto;
import com.example.admin.model.dto.UserDto;
import com.example.admin.model.entity.User;
import com.example.admin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void registration(UserAllDto userAllDto) {

        String login = userAllDto.login();
        String email = userAllDto.email();
        String password = passwordEncoder.encode(userAllDto.password());

        if (userRepository.existsUserByLogin(login) || userRepository.existsUserByEmail(email)) {
            throw new RuntimeException("Користувач за таким логіном або паролем існує");
        }
        User user = User.builder()
                .email(email)
                .login(login)
                .password(password)
                .build();

        userRepository.save(user);
    }


    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

    public void updateUser(UUID id, User user) {
        User oldUser = userRepository.getById(id);
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Користувача не існує");
        }

        oldUser.setEmail(user.getEmail());
        userRepository.save(oldUser);
    }

    public User getUserById(UUID uuid) {
        return userRepository.findById(uuid)
                .orElseThrow();
    }

    public User findByEmail(UserDto userDto) {
        return userRepository.findByEmail(userDto.email())
                .orElseThrow();
    }

    public List<User> getUsersForMonth(LocalDateTime endData) {
        LocalDateTime startDataTime = endData.withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
        return userRepository.findByDateTimeBetween(startDataTime, endData);
    }
}
