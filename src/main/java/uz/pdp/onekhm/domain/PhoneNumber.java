package uz.pdp.onekhm.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PhoneNumber{
    @Id
    @uz.pdp.onekhm.utils.annotation.PhoneNumber
    private String phone;
}