package models.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class UserRequest {
    private String username;
    private String password;
    private List<PostRequest> posts;
}
