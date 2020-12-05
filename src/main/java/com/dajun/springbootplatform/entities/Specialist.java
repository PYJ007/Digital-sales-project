package com.dajun.springbootplatform.entities;

import lombok.Data;

@Data
public class Specialist {
    private int specialist_id;
    private String specialist_name;
    private String specialist_pass;
    private String specialist_phone;
    private String specialist_address;
    private String specialist_instructions;
    private String specialist_type;
}
