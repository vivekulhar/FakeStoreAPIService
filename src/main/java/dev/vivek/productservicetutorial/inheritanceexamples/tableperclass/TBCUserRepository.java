package dev.vivek.productservicetutorial.inheritanceexamples.tableperclass;


import org.springframework.data.jpa.repository.JpaRepository;

public interface TBCUserRepository extends JpaRepository<User,Long> {
    User save(User user);
    User findUserById(Long id);
}
