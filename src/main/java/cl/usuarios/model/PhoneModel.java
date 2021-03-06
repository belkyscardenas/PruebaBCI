package cl.usuarios.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="Phones")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhoneModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private Long number;

    @Column(name="city_code")
    private Long cityCode;

    @Column(name="country_code")
    private Long countryCode;
}
