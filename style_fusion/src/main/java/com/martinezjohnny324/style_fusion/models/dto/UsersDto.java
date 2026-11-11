package com.martinezjohnny324.style_fusion.models.dto;

import java.time.LocalDateTime;

public class UsersDto {
    private Long id;
    private String name;
    private String username;
    private String email;
    private String password;
    private String role;
    private LocalDateTime createdAt = LocalDateTime.now();
    private ClienteDto cDto;

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public ClienteDto getcDto() {
        return cDto;
    }
    public void setcDto(ClienteDto cDto) {
        this.cDto = cDto;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    

}

