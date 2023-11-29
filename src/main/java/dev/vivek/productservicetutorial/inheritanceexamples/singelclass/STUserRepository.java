package dev.vivek.productservicetutorial.inheritanceexamples.singelclass;

import org.springframework.data.jpa.repository.JpaRepository;

public interface STUserRepository extends JpaRepository<User,Long> {
    User save(User user);
    User findUserById(Long id);
}
