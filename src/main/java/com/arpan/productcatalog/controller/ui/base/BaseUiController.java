package com.arpan.productcatalog.controller.ui.base;

import com.arpan.productcatalog.config.UserInfoHolder;
import com.arpan.productcatalog.util.PageLayouts;
import com.arpan.productcatalog.util.WebUriConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Arrays;

import static com.arpan.productcatalog.util.WebUriConstants.WebStore_URI;

public abstract class BaseUiController {
    @Autowired
    WebUriConstants webUriConstants;
    @Autowired
    protected UserInfoHolder userInfo;

    @ModelAttribute("uriMap")
    protected WebUriConstants getUrls() {
        return webUriConstants;
    }

    @ModelAttribute("userInfo")
    protected UserInfoHolder userInfo() {
        return userInfo;
    }
}
