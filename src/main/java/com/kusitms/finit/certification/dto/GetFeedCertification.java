package com.kusitms.finit.certification.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.PrivateKey;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetFeedCertification {
    private String certification_image;
    private String title;
    private String content;
    private int comment_num;
    private int heart_num;
}
