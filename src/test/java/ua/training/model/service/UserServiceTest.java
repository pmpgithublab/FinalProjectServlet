package ua.training.model.service;

import org.junit.Test;
import ua.training.model.dto.UserDTO;
import ua.training.model.entity.UserRole;
import ua.training.model.exception.SuchEmailExistsException;
import ua.training.util.SecurityUtil;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import static org.junit.Assert.*;

public class UserServiceTest {
    private static final String TEST_STRING = "Test";
    private final UserService userService = new UserService();
    private final Random random = new Random(System.currentTimeMillis());

    @Test
    public void successfulSaveCorrectUserAndFindUser() throws Exception {
        UserDTO userDTO = buildDefaultTestUserDTO();

        userService.saveUser(userDTO);

        Optional<UserDTO> userDTOFromDB =
                userService.findUserByEmailAndPassword(userDTO.getEmail(), userDTO.getPassword());
        if (userDTOFromDB.isPresent()) {
            assertEquals(userDTO, userDTOFromDB.get());
        } else {
            fail();
        }
    }

    private UserDTO buildDefaultTestUserDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(TEST_STRING);
        userDTO.setNameUK(TEST_STRING);
        userDTO.setEmail(TEST_STRING + LocalDateTime.now() + random.nextInt(10_000));
        userDTO.setPassword(SecurityUtil.encryptString(TEST_STRING));
        userDTO.setRole(UserRole.ROLE_USER);
        return userDTO;
    }

    @Test(expected = SuchEmailExistsException.class)
    public void failSaveUserDuplicate() throws Exception {
        UserDTO userDTO = buildDefaultTestUserDTO();

        userService.saveUser(userDTO);
        userService.saveUser(userDTO);
    }

    @Test
    public void failSaveUserWithEmptyFields() {
        UserDTO userDTO = buildDefaultTestUserDTO();

        userDTO.setName(null);
        try {
            userService.saveUser(userDTO);
            fail();
        } catch (Exception ignored) {
        }

        userDTO.setName(TEST_STRING);
        userDTO.setNameUK(null);
        try {
            userService.saveUser(userDTO);
            fail();
        } catch (Exception ignored) {
        }

        userDTO.setNameUK(TEST_STRING);
        userDTO.setPassword(null);
        try {
            userService.saveUser(userDTO);
            fail();
        } catch (Exception ignored) {
        }

        userDTO.setPassword(TEST_STRING);
        userDTO.setEmail(null);
        try {
            userService.saveUser(userDTO);
            fail();
        } catch (Exception ignored) {
        }
    }

    @Test(expected = Exception.class)
    public void failSaveEmptyUser() throws SuchEmailExistsException {
        UserDTO userDTO = new UserDTO();

        userService.saveUser(userDTO);
    }
}