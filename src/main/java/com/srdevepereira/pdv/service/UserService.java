package com.srdevepereira.pdv.service;

import com.srdevepereira.pdv.dto.UserDTO;
import com.srdevepereira.pdv.dto.UserResponseDTO;
import com.srdevepereira.pdv.entity.User;
import com.srdevepereira.pdv.exception.NoItemException;
import com.srdevepereira.pdv.repository.UserRepository;
import com.srdevepereira.pdv.security.SecurityConfig;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    private ModelMapper mapper = new ModelMapper();

    public List<UserResponseDTO> findAll(){
        return userRepository.findAll().stream().map(user ->
                new UserResponseDTO(user.getId(), user.getName(), user.getUsername(), user.isEnabled()))
                .collect(Collectors.toList());
    }

    public UserDTO save(UserDTO user){
        user.setPassword(SecurityConfig.passwordEncoder().encode(user.getPassword()));

        User userToSave = mapper.map(user, User.class);
        userRepository.save(userToSave);
        return new UserDTO(userToSave.getId(), userToSave.getName(), userToSave.getUsername(),
                user.getPassword(), userToSave.isEnabled());
    }

    public UserDTO findById(Long id){
        Optional<User> optional = userRepository.findById(id);

        if(!optional.isPresent()){
            throw new NoItemException("Usuario não encontrado.");
        }
        User user = optional.get();
        return new UserDTO(user.getId(), user.getName(), user.getUsername(),
                user.getPassword(), user.isEnabled());
    }

    public UserDTO update(UserDTO user){

        user.setPassword(SecurityConfig.passwordEncoder().encode(user.getPassword()));

        User userToSave = mapper.map(user, User.class);
        Optional<User> userToEdit = userRepository.findById(userToSave.getId());

        if(!userToEdit.isPresent()){
            throw new NoItemException("Usuario não encontrado.");
        }
        userRepository.save(userToSave);
        return new UserDTO(userToSave.getId(), userToSave.getName(), userToSave.getUsername(),
                userToSave.getPassword(), userToSave.isEnabled());
    }

    public void deleteById(Long id){
        userRepository.deleteById(id);
    }
}
