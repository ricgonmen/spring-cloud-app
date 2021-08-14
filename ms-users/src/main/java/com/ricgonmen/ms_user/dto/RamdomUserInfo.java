package com.ricgonmen.ms_user.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString 
public class RamdomUserInfo {
    String seed;
    String results;
    String page;
    String version;
    RamdomUserTime time;
}
