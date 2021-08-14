package com.ricgonmen.ms_user.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString 
public class RamdomUserInfo {
    public String seed;
    public String results;
    public String page;
    public String version;
    public RamdomUserTime time;
}
