package by.milosh.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity to check application features.
 * <p/>
 * Created on 2021-03-15
 * <p/>
 *
 * @author Alexander Milosh
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Integer id;
    private String code;
}
