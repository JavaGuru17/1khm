package uz.pdp.onekhm.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
public class Post extends Auditing{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Length
    private String title;
    @Length
    private String description;
    @Link
    private String mediaPath;
    @ManyToOne
    private Category category;
}
