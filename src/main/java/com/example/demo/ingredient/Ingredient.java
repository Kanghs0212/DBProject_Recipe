package com.example.demo.ingredient;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "ingredient")
public class Ingredient {
    // main ingredient id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long miid;

    // 메인재료 이름
    @Column(nullable = false)
    private String ingredientName;

    // PurchaseLink 테이블의 외래키 plid
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "plid", referencedColumnName = "plid", nullable = true)
    private PurchaseLink purchaseLink;

    // 구매재료를 가졌는지 확인하는 메소드
    public boolean hasPurchaseLink() {
        return purchaseLink != null;
    }
}
