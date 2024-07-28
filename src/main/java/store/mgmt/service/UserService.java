package store.mgmt.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import store.mgmt.dto.UserDto;
import store.mgmt.dto.UserLoginDto;
import store.mgmt.entity.Role;
import store.mgmt.entity.User;
import store.mgmt.entity.UserRole;
import store.mgmt.mapper.UserMapper;
import store.mgmt.repository.RoleRepository;
import store.mgmt.repository.UserRepository;



import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.authentication.AuthenticationManager;

import java.util.HashSet;
import java.util.Set;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;


    @Autowired
    private AuthenticationManager authenticationManager;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    public void registerUser(UserDto userDto) {
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());

        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(encodedPassword);

        Set<Role> roles = new HashSet<>();
        Role userRole = getRoleByName(UserRole.USER);
        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);

        for (Role role : roles) {
            role.getUsers().add(user);
            roleRepository.save(role);
        }
    }

    public String loginUser(UserLoginDto userLoginDto) {

        try {

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userLoginDto.getUsername(), userLoginDto.getPassword())
            );

            return "User logged in successfully";
        } catch (AuthenticationException e) {

            e.printStackTrace();
            return "Invalid username or password";
        }
    }

    public Role getRoleByName(UserRole roleName) {
        return roleRepository.findByRoleName(roleName);
    }
}
