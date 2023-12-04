package pro.sky.hw35.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.hw35.model.Avatar;

@Repository
public interface AvatarRepository extends JpaRepository<Avatar, Long> {

}
