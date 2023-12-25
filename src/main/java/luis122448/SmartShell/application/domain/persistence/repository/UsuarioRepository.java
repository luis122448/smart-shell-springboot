package luis122448.SmartShell.application.domain.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import luis122448.SmartShell.application.domain.persistence.entity.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, String>{

	@Modifying
	@Query(nativeQuery = true,value = "UPDATE TBL_USER SET CODVER=:codver WHERE CODUSER = :coduser")
	void updateVerifyCode(@Param("coduser") String coduser ,@Param("codver") String codver);
}
