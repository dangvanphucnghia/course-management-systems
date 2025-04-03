package vn.phucnghia.course_management_systems.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.phucnghia.course_management_systems.model.UserEntity;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query(value = "select u from UserEntity u where u.status='ACTIVE' " +
            "and (lower(u.firstName) like CONCAT('%', :keyword, '%') " +
            "or lower(u.lastName) like CONCAT('%', :keyword, '%') " +
            "or lower(u.username) like CONCAT('%', :keyword, '%') " +
            "or lower(u.gender) like CONCAT('%', :keyword, '%') " +
            "or lower(u.phone) like CONCAT('%', :keyword, '%'))")
    Page<UserEntity> searchByKeyword(String keyword, Pageable pageable);
}
