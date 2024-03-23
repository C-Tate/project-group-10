package ca.mcgill.ecse321.scs.dto;

import ca.mcgill.ecse321.scs.model.Owner;

public class OwnerRequestDto {
    private Integer id;
    private String name;
    private String email;
    private String password;

    public OwnerRequestDto() {
    }

    public OwnerRequestDto(Integer id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public OwnerRequestDto(Owner owner) {
        this(owner.getAccountId(), owner.getName(), owner.getEmail(), owner.getPassword());
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }
}
