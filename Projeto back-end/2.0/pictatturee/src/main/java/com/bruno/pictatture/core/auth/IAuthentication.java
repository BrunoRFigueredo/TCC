package com.bruno.pictatture.core.auth;

import org.springframework.security.core.Authentication;

public interface IAuthentication {
    Authentication getAuthentication();
}
