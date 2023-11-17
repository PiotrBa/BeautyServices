package com.piotrba.beautyservices.controllerTest;

import com.piotrba.beautyservices.controller.PostLoginController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

import static org.mockito.Mockito.*;

class PostLoginControllerTest {

    @Mock
    private Authentication authentication;
    @Mock
    private HttpServletResponse response;

    private PostLoginController postLoginController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        postLoginController = new PostLoginController();
    }

    @Test
    void testPostLoginAsAdmin() throws IOException {
        Collection<GrantedAuthority> adminAuthorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"));
        when(authentication.getAuthorities()).thenReturn((Collection) adminAuthorities);

        postLoginController.postLogin(authentication, response);

        verify(response).sendRedirect("/reservations");
    }

    @Test
    void testPostLoginAsCustomer() throws IOException {
        Collection<GrantedAuthority> userAuthorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
        when(authentication.getAuthorities()).thenReturn((Collection) userAuthorities);

        postLoginController.postLogin(authentication, response);

        verify(response).sendRedirect("/homepage");
    }
}
