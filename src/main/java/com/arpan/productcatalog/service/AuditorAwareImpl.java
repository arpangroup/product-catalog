package com.arpan.productcatalog.service;

import com.arpan.productcatalog.config.UserInfoHolder;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
    @Autowired
    private  UserInfoHolder userInfoHolder;

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(userInfoHolder.getUsername());
    }
}
