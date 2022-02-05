package uz.episodeone.merchants.dto.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FailResponse {
    private String status = "fail";
    private FailData data;

    @Builder(toBuilder = true)
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FailData {
        private String errorId;
        private String message;
    }

}
