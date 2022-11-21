package SoftwareEngineering.server.Domain.enums;

import lombok.Getter;

@Getter
public enum Role {
    ROLE_ADMIN("ADMIN"),
    ROLE_MEMBER("MEMBER");
    private String description;
    Role(String description){
        this.description = description;
    }
}
