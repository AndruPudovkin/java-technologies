package com.example.javaprojectpudovkin.mapper;

import com.example.javaprojectpudovkin.dto.UserFulInfoDto;
import com.example.javaprojectpudovkin.persistent.enam.Role;
import com.example.javaprojectpudovkin.persistent.model.User;
import com.example.javaprojectpudovkin.persistent.model.UserRole;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UserMapper {

    @Mapping(source = "roles", target = "roles", qualifiedByName = "rolesMapper")
    UserFulInfoDto toDto(User userDao);

    User toEntity(UserFulInfoDto userFulInfoDto);

    @Named("rolesMapper")
    default Set<Role> rolesMapper(Set<UserRole> userRoles) {
        return userRoles.stream()
                .map(UserRole::getUserRole)
                .collect(Collectors.toSet());
    }
}
