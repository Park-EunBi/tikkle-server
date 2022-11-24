package com.kusitms.finit.challenge;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kusitms.finit.challengeDetail.ChallengeDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Challenge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long challengeId;
    private String category;

    @JsonIgnore
    @OneToMany(mappedBy = "challenge", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChallengeDetail> challengeDetailList = new ArrayList<>();
}
