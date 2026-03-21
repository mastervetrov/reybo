package reybo.authentication.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import reybo.authentication.entities.user.User;
import reybo.authentication.events.NewUserRegisteredEvent;
import reybo.authentication.web.models.CreateUserRequest;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    default NewUserRegisteredEvent userToNewUserRegisteredEvent(User user, CreateUserRequest request) {
        return NewUserRegisteredEvent.builder()
                .id(user.getId())
                .email(user.getEmail())
                .roles(user.getRoles())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .build();
    }
}