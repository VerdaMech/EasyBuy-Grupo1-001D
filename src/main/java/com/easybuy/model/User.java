package com.easybuy.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 20, nullable = false)
    private String password;
    
    @Column(unique = true, length = 13, nullable = false )
    private String run;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 50, nullable = false)
    private String last_Name;

    @Column(length = 50, nullable = false)
    private String email;

    @Column(nullable = false)
    private String address;

    @Column(length = 11, nullable = false)
    private Long phone_Number;

    @ManyToOne
    @JoinColumn(name = "tipo_usuario", nullable = false)
    private UserType userType;
}
