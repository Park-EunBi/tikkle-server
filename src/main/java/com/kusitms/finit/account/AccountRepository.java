package com.kusitms.finit.account;

import com.kusitms.finit.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByoAuthId(String oAuthId);
    Optional<Account> findByEmail(String email);


    @Modifying
    @Query(value = "UPDATE account a SET " +
            "a.nickname = :nickname, a.intro = :intro, a.profile_image = :profileImage, " +
            "a.monthly_income = :monthlyIncome, a.saving_cost = :savingCost, a.fixed_cost = :fixedCost " +
            "where a.account_id = :id", nativeQuery = true)
    void updateExtraInfoByAccountId(@Param(value = "id") Long id,
                                    @Param(value = "nickname") String nickname,
                                    @Param(value = "intro") String intro,
                                    @Param(value = "profileImage") int profileImage,
                                    @Param(value = "monthlyIncome") int monthlyIncome,
                                    @Param(value = "savingCost") int savingCost,
                                    @Param(value = "fixedCost") int fixedCost);
}
