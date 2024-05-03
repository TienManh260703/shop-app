package com.project.shopapp.models;
import jakarta.persistence.*;
import lombok.*;

@Entity

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categories")

@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name ;
}
