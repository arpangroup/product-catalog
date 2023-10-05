package com.arpan.productcatalog.config;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserInfoHolder {
    private String userId;
    private String username;
    private List<String> roles = new ArrayList<>();


    public boolean isAdmin() {
        return roles.stream().anyMatch(s -> s.equals("ADMIN"));
    }
}
