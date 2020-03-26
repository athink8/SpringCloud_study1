package demo1_api.entities;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * author: jz
 * Time: 2020/3/14 16:33
 **/

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain=true)
public class User implements Serializable {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private String email;
    private String phone;
    private String status;
    private String code;
    private String  db_source; //DATABASE()

}
