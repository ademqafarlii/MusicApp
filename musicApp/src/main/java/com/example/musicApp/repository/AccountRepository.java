package com.example.musicApp.repository;
import com.example.musicApp.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findByPassword(String password);
    void deleteAccountByUserNameAndPassword(String userName, String password);
    Optional<Account> findByUserName(String userName);
    Boolean existsByUserName(String userName);


}
