package api.servise;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserJson {
    private String email;
    private String password;
    private String name;
}
