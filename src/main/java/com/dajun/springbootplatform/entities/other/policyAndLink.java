package com.dajun.springbootplatform.entities.other;

import lombok.Data;

@Data
public class policyAndLink {
    private int policy_id;
    private String policy_time;
    private String policy_title;
    private String policy_content;
    private int specialist_id;
    private String link;
}
