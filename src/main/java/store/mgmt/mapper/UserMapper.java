package store.mgmt.mapper;


import org.springframework.stereotype.Component;
import store.mgmt.dto.UserDto;
import store.mgmt.entity.User;

@Component
public class UserMapper {

    public User toEntity(UserDto dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        return user;
    }

    public UserDto toDto(User entity) {
        UserDto dto = new UserDto();
        dto.setUsername(entity.getUsername());
        dto.setPassword(entity.getPassword());
        return dto;
    }


}
