package uz.pdp.onekhm.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import uz.pdp.onekhm.domain.enums.PostType;
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
    @Enumerated(EnumType.STRING)
    private PostType postType;
    @ManyToOne
    private Category category;
}
