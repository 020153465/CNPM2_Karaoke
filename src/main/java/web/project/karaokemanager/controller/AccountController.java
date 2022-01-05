package web.project.karaokemanager.controller;

import java.security.Provider.Service;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import web.project.karaokemanager.model.Account;
import web.project.karaokemanager.model.Room;
import web.project.karaokemanager.repository.AccountService;
import web.project.karaokemanager.repository.RoomService;

@Controller
public class AccountController {
    @Autowired
    private RoomService roomService;

    @Autowired
    private AccountService accountService;

    /*Account CRUD*/
    @RequestMapping("/newAccount")
    public String showNewAccountForm(Model model){
        Account account = new Account();
        model.addAttribute("account", account);
        List<String> accountStatus = Arrays.asList("true","false");
        model.addAttribute("accountStatus", accountStatus);
        return "new_account";
    }

    @RequestMapping(value = "/saveAccount", method= RequestMethod.POST)
    public String saveAccount(@ModelAttribute("account") Account account){
        accountService.save(account);
        return "redirect:/";
    }

    @RequestMapping("/editAccount/{id}")
    public ModelAndView showEditAccountForm(@PathVariable(name = "id") int id, Model model){
        ModelAndView mav = new ModelAndView("edit_account");

        Account account = accountService.get(Long.valueOf(id));
        mav.addObject("account", account);

        List<String> accountStatus = Arrays.asList("true","false");
        model.addAttribute("accountStatus", accountStatus);

        return mav;
    }

    @RequestMapping("/deleteAccount/{id}")
    public String deleteAccount(@PathVariable(name = "id") int id, Model model){
        accountService.delete(Long.valueOf(id));
        return "redirect:/";
    }

    // add: POST
    // edit: PUT
    // delete: DELETE
    // show data: GET
}
