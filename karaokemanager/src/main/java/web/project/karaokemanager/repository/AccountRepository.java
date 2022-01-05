package web.project.karaokemanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import web.project.karaokemanager.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{
    // @Query("select a from Account a")
    // public List<Account> getAllAccounts();

    @Query("select a from Account a where a.username=: x and a.password=: y")
    public Account getAccountByUsernamePassword(@Param("x") String username, @Param("y") String password);
}
