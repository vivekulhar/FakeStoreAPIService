package dev.vivek.productservicetutorial.inheritanceexamples.jointtable;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JTUserRepository extends JpaRepository<User,Long> {
    User save(User user);
    User findUserById(Long id);
}
