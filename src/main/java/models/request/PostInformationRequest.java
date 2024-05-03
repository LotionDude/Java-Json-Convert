package models.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PostInformationRequest {
    private Integer likes;
    private String date;
    private String content;
}
