package com.dajun.springbootplatform.entities.other;

import lombok.Data;

@Data
public class specialistInfo {
    private int specialist_id;
    private String specialist_name;
    private String specialist_pass;
    private String specialist_phone;
    private String specialist_address;
    private String specialist_instructions;
    private String specialist_type;
    private byte[] pic;

    public int getSpecialist_id() {
        return specialist_id;
    }

    public void setSpecialist_id(int specialist_id) {
        this.specialist_id = specialist_id;
    }

    public String getSpecialist_name() {
        return specialist_name;
    }

    public void setSpecialist_name(String specialist_name) {
        this.specialist_name = specialist_name;
    }

    public String getSpecialist_pass() {
        return specialist_pass;
    }

    public void setSpecialist_pass(String specialist_pass) {
        this.specialist_pass = specialist_pass;
    }

    public String getSpecialist_phone() {
        return specialist_phone;
    }

    public void setSpecialist_phone(String specialist_phone) {
        this.specialist_phone = specialist_phone;
    }

    public String getSpecialist_address() {
        return specialist_address;
    }

    public void setSpecialist_address(String specialist_address) {
        this.specialist_address = specialist_address;
    }

    public String getSpecialist_instructions() {
        return specialist_instructions;
    }

    public void setSpecialist_instructions(String specialist_instructions) {
        this.specialist_instructions = specialist_instructions;
    }

    public String getSpecialist_type() {
        return specialist_type;
    }

    public void setSpecialist_type(String specialist_type) {
        this.specialist_type = specialist_type;
    }

    public byte[] getPic() {
        return pic;
    }

    public void setPic(byte[] pic) {
        this.pic = pic;
    }
}
