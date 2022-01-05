package web.project.karaokemanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import web.project.karaokemanager.model.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {
    //write more query here
    
}
