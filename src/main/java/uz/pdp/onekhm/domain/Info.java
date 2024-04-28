package uz.pdp.onekhm.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import uz.pdp.onekhm.utils.annotation.Length;
import uz.pdp.onekhm.utils.annotation.Link;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
public class Info {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Length
    private String title;
    @Length
    private String description;
    @Link
    private String logoUrl;
    //todo behzod please add email annotation
    private String email;
}
