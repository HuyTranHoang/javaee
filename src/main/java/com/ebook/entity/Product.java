package com.ebook.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;

    private String name;

    private String author;

    private String description;

    private String isbn;

    private byte[] image;

    private BigDecimal price;

    @Column(name = "publish_date")
    private LocalDate publishDate;

    @UpdateTimestamp
    @Column(name = "last_update_time")
    private LocalDate lastUpdateTime;

    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category category;
}
