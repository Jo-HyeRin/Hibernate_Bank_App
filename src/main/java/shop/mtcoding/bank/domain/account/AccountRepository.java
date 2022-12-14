package shop.mtcoding.bank.domain.account;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountRepository extends JpaRepository<Account, Long> {

    // @Query(value = "select * from account inner join users on account.userId =
    // users.id where user_id = :userId", nativeQuery = true)
    @Query("select ac from Account ac join fetch ac.user u where ac.user.id = :userId and ac.isActive = true") // JPQL
    List<Account> findByActiveUserId(@Param("userId") Long userId);

    @Query("select ac from Account ac where ac.user.id = :userId and ac.isActive = true")
    List<Account> findByActiveUserIdV2(@Param("userId") Long userId);

    @Query("select ac from Account ac where ac.number = :number")
    Optional<Account> findByNumber(@Param("number") Long number);
}
