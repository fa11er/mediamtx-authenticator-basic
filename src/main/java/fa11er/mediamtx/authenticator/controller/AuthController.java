package fa11er.mediamtx.authenticator.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fa11er.mediamtx.authenticator.entity.AuthUser;
import fa11er.mediamtx.authenticator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static fa11er.mediamtx.authenticator.MediamtxAuthenticatorBasicApplication.LOGGER;

@RestController
@RequestMapping("/api")
public class AuthController {

    private static final ResponseEntity<String> RESPONSE_200 = ResponseEntity.status(HttpStatus.OK).body("200 OK\n");
    private static final ResponseEntity<String> RESPONSE_401 = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body
            ("401 Unauthorized: Access is denied due to invalid credentials\n");
    private static final String LOGGER_STATUS401_EMPTY = "(401) [USER_EMPTY] ip:{},login:{},password:{}";
    private static final String LOGGER_STATUS200_READ = "(200) [USER_READ] ip:{},login:{},password:{}";
    private static final String LOGGER_STATUS401_READ = "(401) [USER_READ] ip:{},login:{},password:{}";
    private static final String LOGGER_STATUS200_PUBLISH = "(200) [USER_PUBLISH] ip:{},login:{},password:{}";
    private static final String LOGGER_STATUS401_PUBLISH = "(401) [USER_PUBLISH] ip:{},login:{},password:{}";
    private static final String LOGGER_STATUS401_ACTION_UNKNOWN = "(401) [UNKNOWN_ACTION] ip:{},login:{},password:{}";
    private static final String LOGGER_STATUS401_SELECT_FAILED = "(401) [SELECT_FAILED] ip:{},login:{},password:{}";

    @Autowired
    private UserService userService;

    @PostMapping(value = "/auth", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus
    public ResponseEntity<String> responseEntity(@RequestBody String jsonString) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        AuthUser authUser = objectMapper.readValue(jsonString, AuthUser.class);
        try {
            if (authUser.getUser().isEmpty() || authUser.getPassword().isEmpty()) {
                LOGGER.debug(LOGGER_STATUS401_EMPTY,authUser.getIp(),authUser.getUser(),authUser.getPassword());
                return RESPONSE_401;

            } else if (authUser.getAction().equals("read")) {
                boolean isUserExist = userService.isReadUserExist(authUser.getUser(), authUser.getPassword());
                if (isUserExist) {
                    LOGGER.debug(LOGGER_STATUS200_READ,authUser.getIp(),authUser.getUser(),authUser.getPassword());
                    return RESPONSE_200;
                } else {
                    LOGGER.debug(LOGGER_STATUS401_READ,authUser.getIp(),authUser.getUser(),authUser.getPassword());
                    return RESPONSE_401;
                }


            } else if (authUser.getAction().equals("publish")) {

                boolean isUserExist = userService.isPublishUserExist(authUser.getUser(), authUser.getPassword());
                if (isUserExist) {
                    LOGGER.debug(LOGGER_STATUS200_PUBLISH,authUser.getIp(),authUser.getUser(),authUser.getPassword());
                    return RESPONSE_200;
                } else {
                    LOGGER.debug(LOGGER_STATUS401_PUBLISH,authUser.getIp(),authUser.getUser(),authUser.getPassword());
                    return RESPONSE_401;
                }


            } else {
                LOGGER.debug(LOGGER_STATUS401_ACTION_UNKNOWN,authUser.getIp(),authUser.getUser(),authUser.getPassword());
                return RESPONSE_401;
            }


        } catch (Exception e) {
            LOGGER.error(LOGGER_STATUS401_SELECT_FAILED,authUser.getIp(),authUser.getUser(),authUser.getPassword()) ;
            return RESPONSE_401;
        }
    }
}
