package com.kusitms.finit.certification.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.type.PrimitiveCharacterArrayClobType;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetCertificationRes {
    private String category;
    private String title;
    private String nickname;
    private Date certification_date;
    private String certification_image;
    private String content;
    private int likes;

}