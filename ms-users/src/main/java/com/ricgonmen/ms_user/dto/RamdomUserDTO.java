package com.ricgonmen.ms_user.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class RamdomUserDTO{
    List<UserDTO> results;
    RamdomUserInfo info;
}