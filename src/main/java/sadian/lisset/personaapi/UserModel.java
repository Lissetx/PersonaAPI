package sadian.lisset.personaapi;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserModel implements UserDetails, Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private String authority;

    public UserModel() {}

    public UserModel(String username, String password, String authority) {
        this.setUsername(username);
        this.setPassword(password);
        this.setAuthority(authority);
    }

    private void setAuthority(String authority) {
        this.authority = authority;
    }

    private void setUsername(String username) {
        this.username = username;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    public String getAuthority() {
        return this.authority;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "{Username: " + username + ", Password:" + password + ", Authorities: " + authority + "}";
    }


}
