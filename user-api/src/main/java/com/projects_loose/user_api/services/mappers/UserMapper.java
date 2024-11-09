package com.projects_loose.user_api.services.mappers;

import com.projects_loose.user_api.entities.User;
import com.projects_loose.user_api.services.dtos.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper <UserDTO, User>{
}