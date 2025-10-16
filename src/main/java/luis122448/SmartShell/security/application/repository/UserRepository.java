package luis122448.SmartShell.security.application.repository;

import luis122448.SmartShell.security.application.entity.UserEntity;
import luis122448.SmartShell.security.application.entity.primary.UserKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("UserSecurityRepository")
public interface UserRepository extends JpaRepository<UserEntity, UserKey>{

    @Query(value = "SELECT us FROM UserEntitySecurity us" +
            " INNER JOIN CompanyInfoEntitySecurity cis ON cis.idcompany = us.idcompany" +
            " WHERE us.coduser = :coduser AND cis.company = :company")
    Optional<UserEntity> findByCompanyAndCoduser(String company, String coduser);

    @Modifying
    @Query(value = "UPDATE TBL_USER SET code=:code WHERE idcompany = :idcompany AND coduser = :coduser", nativeQuery = true)
    void updateVerifyCode(@Param("idcompany") Integer idcompany, @Param("coduser") String coduser , @Param("code") String code);

}
