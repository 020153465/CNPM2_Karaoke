package web.project.karaokemanager.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.project.karaokemanager.model.Room;

@Service
public class RoomService {
    @Autowired
    private RoomRepository repo;

    public List<Room> listAll(){
        return repo.findAll();
    }

    public void save(Room room){
        repo.save(room);
    }

    public Room get(Long id){
        return repo.findById(id).get();
    }

    public void delete(Long id){
        repo.deleteById(id);
    }
}
