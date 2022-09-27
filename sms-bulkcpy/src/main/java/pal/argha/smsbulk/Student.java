package pal.argha.smsbulk;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="student")
public class Student {
    @Id
    @SequenceGenerator(
            name="seq",
            sequenceName="seq",
            allocationSize=1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq"
    )
    private Long id;
    private String name;
    private String gender;
    private String stream;
    private String phone;
}
