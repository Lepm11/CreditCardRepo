package com.ibm.creditcard.model.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "creditcarduno")
public class CreditCard implements Serializable
{
   

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "your_passion", nullable = false, length =30)
    private String yourPassion;

    @Column(name = "monthly_salary_from")
    private Double monthlySalaryFrom;

    @Column(name = "monthly_salary_to")
    private Double monthlySalaryTo;

    @Column(name = "age_from")
    private Integer ageFrom;

    @Column(name = "age_to")
    private Integer ageTo;

    @Column(name = "credit_card_suggested", length = 150)
    private String creditCardSuggested;

    @Column(name = "creation_user")
    private String creationUser;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @Column(name = "modification_date")
    private Date modificationDate;


    public CreditCard(Long id, String yourPassion, Double monthlySalaryFrom, Double monthlySalaryTo, Integer ageFrom, Integer ageTo, String creditCardSuggested, String creationUser) {
        this.id = id;
        this.yourPassion = yourPassion;
        this.monthlySalaryFrom = monthlySalaryFrom;
        this.monthlySalaryTo = monthlySalaryTo;
        this.ageFrom = ageFrom;
        this.ageTo = ageTo;
        this.creditCardSuggested = creditCardSuggested;
        this.creationUser = creationUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreditCard)) return false;
        CreditCard that = (CreditCard) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getYourPassion(), that.getYourPassion()) && Objects.equals(getMonthlySalaryFrom(), that.getMonthlySalaryFrom()) && Objects.equals(getMonthlySalaryTo(), that.getMonthlySalaryTo()) && Objects.equals(getAgeFrom(), that.getAgeFrom()) && Objects.equals(getAgeTo(), that.getAgeTo()) && Objects.equals(getCreditCardSuggested(), that.getCreditCardSuggested()) && Objects.equals(getCreationUser(), that.getCreationUser()) && Objects.equals(getCreationDate(), that.getCreationDate()) && Objects.equals(getModificationDate(), that.getModificationDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getYourPassion(), getMonthlySalaryFrom(), getMonthlySalaryTo(), getAgeFrom(), getAgeTo(), getCreditCardSuggested(), getCreationUser(), getCreationDate(), getModificationDate());
    }

    @PrePersist
    private void antesPersistir()
    {
        this.creationDate = new Date();
    }


    @PreUpdate
    private void antesActualizar()
    {
        this.modificationDate = new Date();
    }
    
    private static final long serialVersionUID = -5838579754408374048L;

}
