package com.project.shopapp.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@Builder
public class User extends BaseEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "fullname", length = 100)
    private String fullName;

    @Column(name = "phone_number", length = 10, nullable = false)
    private String phoneNumber;
    @Column(name = "address", length = 200)
    private String address;
    @Column(name = "password", nullable = false, length = 100)
    private String password;
    @Column(name = "is_active")
    private boolean isActive;
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    @Column(name = "google_account_id")
    private int googleAccountId ;
    @Column(name = "facebook_account_id")
    private int facebookAccountId;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private com.project.shopapp.models.Role role;

//    Lấy ra các quyền
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // convert Relo sang Authorities
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
       authorityList.add(new SimpleGrantedAuthority("ROLE_"+getRole().getName()));
        return authorityList;
    }
// Spring tự hiểu userName là duy nhất
    @Override
    public String getUsername() {
        return phoneNumber;
    }
// Acount có thời lượng vô thời hạn
    @Override
    public boolean isAccountNonExpired() {
//        Mình tự cấu hình mặc định là true
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
    public boolean isEnabled() {
        return true;
    }
}
