package web.project.karaokemanager.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.project.karaokemanager.model.Account;

@Service
public class AccountService {
    @Autowired
    private AccountRepository repo;

    public List<Account> listAll(){
        return repo.findAll();
    }

    public void save(Account account){
        repo.save(account);
    }

    public Account get(Long id){
        return repo.findById(id).get();
    }

    public void delete(Long id){
        repo.deleteById(id);
    }
}
