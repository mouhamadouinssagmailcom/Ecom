package com.myvision.Super.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "Produit")

public class Product implements Serializable {


    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Min(value = 1, message = "La  quantité ne doit pas être inférieur à 1")
    //@NotBlank(message = "Veuillez remplir ce champ")
    private int qty;

    @Column(name = "Designation", nullable = false)
    @NotBlank(message = "Veuillez remplir ce champ")
    private String design;

    @Column(name = "Marque", nullable = false)
    @NotBlank(message = "Veuillez remplir ce champ")
    private String marque;

    @Column(name = "Description", nullable = false)
    @Size(min = 10, max = 65, message = "La description doit comporter minimun 10 et maximun 65 caractères ")
    private String desc;

    @Column(name = "Détails", nullable = false)
    @Size(min = 10, max = 255, message = "Les details doit comporter minimun 10 et maximun 255 caractères")
    private String detail;


    @Min(value = 100, message = "Le prix ne doit pas être inférieur à 100")
    @Column(name = "Prix", nullable = false)
    private BigDecimal prix;

    @Column(name = "Etat", nullable = true)
    private boolean available = true;

    @Column(name = "Catégorie", nullable = false)
    @NotBlank(message = "Veuillez choisir une catégorie")
    private String category;

    @Lob
    @Column(name = "Image", length = Integer.MAX_VALUE, nullable = true)
    private byte[] image;

    @Column(name = "Image_name")
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;

        return id == product.getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getDesign() {
        return design;
    }

    public void setDesign(String design) {
        this.design = design;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}





