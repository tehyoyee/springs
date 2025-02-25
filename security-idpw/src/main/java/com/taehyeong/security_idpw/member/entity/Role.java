package com.taehyeong.security_idpw.member.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

//    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST }, orphanRemoval = true)
//    private final List<Member> member = new ArrayList<>();

    private String description;

    @ElementCollection(targetClass = Authority.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "authority", joinColumns = @JoinColumn(name = "role_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "authority", nullable = false)
    private Set<Authority> authorities;

    public Role(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\n=====Role=====");
        builder.append("\nname = " + name);
        builder.append("\ndescription = " + description);
        builder.append("\nauthorities = " + authorities);
        for (Authority authority : authorities) {
            builder.append("\nauthority = " + authority);
        }
        return builder.toString();
    }

}
