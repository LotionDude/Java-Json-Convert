package org.lotan.utility;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lotion {
    private String name;

    @JsonProperty("display_name")
    private String displayName;

    @JsonProperty("range_info")
    private RangeInfo rangeInfo;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RangeInfo {
        private Range range;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Range {
        private Integer lt;
        private Integer gt;
    }
}
