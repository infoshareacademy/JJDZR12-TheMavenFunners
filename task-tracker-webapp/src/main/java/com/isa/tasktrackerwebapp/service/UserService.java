package com.isa.tasktrackerwebapp.service;

import com.isa.tasktrackerwebapp.model.dto.UserDto;
import com.isa.tasktrackerwebapp.model.entity.User;
import com.isa.tasktrackerwebapp.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public User mapDTOToEntity(UserDto userDTO) {
        User user = new User();

        user.setLogin(userDTO.getLogin());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setEmail(userDTO.getEmail());
        user.setActive(userDTO.isActive());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());

        return user;
    }

    public UserDto mapEntityToDto(User user) {
        UserDto userDto = new UserDto();

        userDto.setLogin(user.getLogin());
        userDto.setPassword(passwordEncoder.encode(user.getPassword()));
        userDto.setEmail(user.getEmail());
        userDto.setActive(user.isActive());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());

        return userDto;
    }

    public List<UserDto> mapEntityToDtoList() {
        return userRepository.findAll().stream()
                .map(this::mapEntityToDto).toList();
    }
}
