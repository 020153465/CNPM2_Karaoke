package web.project.karaokemanager.controller;

import java.security.Provider.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.*;

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
public class RoomController {
    @Autowired
    private RoomService roomService;

    @Autowired
    private AccountService accountService;

    @RequestMapping("/")
    public String viewHomePage(Model model){
        List<Room> listRooms = roomService.listAll();
        model.addAttribute("listRooms", listRooms);

        List<Account> listAccounts = accountService.listAll();
        model.addAttribute("listAccounts", listAccounts);

        //for storing to db
        LocalDateTime currentDate = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String formattedDate = currentDate.format(myFormatObj);

        //for calculate time different
        String previousTime = "01/01/2022 21:50:55";
        SimpleDateFormat dateFormatter  = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        try {
            Date datePrevious = dateFormatter.parse(previousTime);
            Date dateCurrent = dateFormatter.parse(formattedDate);
            long gapTime = dateCurrent.getTime() - datePrevious.getTime();//in milisecond
            long gapTimeMinute = gapTime/60000;//in minutes
            //
            model.addAttribute("currentDate", gapTimeMinute);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "index";
    }

    @RequestMapping("/newRoom")
    public String showNewRoomForm(Model model){
        Room room = new Room();
        model.addAttribute("room", room);

        List<String> roomTypes = Arrays.asList("normal","VIP");
        model.addAttribute("roomTypes", roomTypes);
        // List<String> roomStatus = Arrays.asList("0","1");
        // model.addAttribute("roomStatus", roomStatus);

        return "new_room";
    }

    @RequestMapping(value = "/save", method= RequestMethod.POST)
    public String saveProduct(@ModelAttribute("room") Room room){
        roomService.save(room);
        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditRoomForm(@PathVariable(name = "id") int id, Model model){
        ModelAndView mav = new ModelAndView("edit_room");

        Room room = roomService.get(Long.valueOf(id));
        mav.addObject("room", room);

        List<String> roomTypes = Arrays.asList("normal","VIP");
        model.addAttribute("roomTypes", roomTypes);

        return mav;
    }

    @RequestMapping("/delete/{id}")
    public String deleteRoom(@PathVariable(name = "id") int id, Model model){
        roomService.delete(Long.valueOf(id));

        // List<Room> listRooms = roomService.listAll();
        // model.addAttribute("listRooms", listRooms);

        // List<Account> listAccounts = accountService.listAll();
        // model.addAttribute("listAccounts", listAccounts);

        // return "index";
        return "redirect:/";
    }
}