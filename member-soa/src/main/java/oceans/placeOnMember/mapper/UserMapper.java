package oceans.placeOnMember.mapper;

import oceans.placeOnMember.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface UserMapper {

    User login(User user);

}
