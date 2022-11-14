package com.kusitms.finit.retrospect.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RetrospectRes {
    private Long id;
    private String createDate;
    private int imageIndex;
    private String content;
    private String hashTag;
}
