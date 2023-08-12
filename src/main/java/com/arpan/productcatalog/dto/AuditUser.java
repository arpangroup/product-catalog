package com.arpan.productcatalog.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuditUser {
    private String userId;
    private String username;
    public AuditUser(@NotNull String userId, @NotNull String username) {
        this.userId = userId;
        this.username = username;
    }
}
