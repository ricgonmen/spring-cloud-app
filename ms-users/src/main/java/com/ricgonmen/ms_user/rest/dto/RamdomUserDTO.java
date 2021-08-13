package com.ricgonmen.ms_user.rest.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class RamdomUserDTO{
    public List<UserDTO> results;
    public RamdomUserInfo info;
}