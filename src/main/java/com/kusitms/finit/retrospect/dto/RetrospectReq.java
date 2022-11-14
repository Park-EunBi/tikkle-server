package com.kusitms.finit.retrospect.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RetrospectReq {
    private int imageIndex;
    private String content;
    private String hashTag;
}
