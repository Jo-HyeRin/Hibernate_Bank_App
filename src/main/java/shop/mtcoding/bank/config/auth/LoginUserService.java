package shop.mtcoding.bank.config.auth;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.bank.domain.user.User;
import shop.mtcoding.bank.domain.user.UserRepository;

@RequiredArgsConstructor
@Service
public class LoginUserService implements UserDetailsService {
    // login process customizing 하는 곳.

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {

        log.debug("디버그 : loadUserByUsername 실행됨");

        Optional<User> userOP = userRepository.findByUsername(username);
        if (userOP.isPresent()) {
            return new LoginUser(userOP.get());
        } else {
            return null;
        }
    }

}
