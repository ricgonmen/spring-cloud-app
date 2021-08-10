package com.ricgonmen.ms_user.rest.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RamdomUserDTO{
    public List<UserDTO> results;
    public RamdomUserInfo info;
}