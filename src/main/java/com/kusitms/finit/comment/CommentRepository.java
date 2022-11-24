package com.kusitms.finit.comment;

import com.kusitms.finit.certification.Certification;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentRepository extends JpaRepository<Certification, Long> {
}
