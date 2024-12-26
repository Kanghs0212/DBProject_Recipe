package com.example.demo.ingredient;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "purchase_link")
public class PurchaseLink {
    // purchase link id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long plid;

    // 링크
    @Column(nullable = false)
    private String link;

    // 판매자
    private String seller;
}
