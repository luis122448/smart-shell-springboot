package luis122448.SmartShell.security.api.service.impl;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.util.object.api.ApiResponseList;
import luis122448.SmartShell.util.object.api.ApiResponseObject;
import luis122448.SmartShell.util.object.api.ApiResponsePage;
import luis122448.SmartShell.application.domain.persistence.entity.UsuarioEntity;
import luis122448.SmartShell.application.domain.persistence.repository.UsuarioRepository;
import luis122448.SmartShell.security.api.service.service.AuthService;
import luis122448.SmartShell.security.api.service.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService, AuthService{

	private UsuarioRepository usuarioRepository;
	
	public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
		super();
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public ApiResponseList<UsuarioEntity> findAll(UsuarioEntity t) throws GenericObjectServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponseList<UsuarioEntity> findByLike(UsuarioEntity t) throws GenericObjectServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponsePage<UsuarioEntity> findByPage(UsuarioEntity t, Pageable p) throws GenericObjectServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponseObject<UsuarioEntity> findById(UsuarioEntity t) throws GenericObjectServiceException {
		try {
			return new ApiResponseObject<UsuarioEntity>((short) 1,"Ok",this.usuarioRepository.findById(t.getCoduser()));
		} catch (Exception e) {
			return new ApiResponseObject<UsuarioEntity>((short) -2,e.getMessage(),null);
		}
	}

	@Override
	public ApiResponseObject<UsuarioEntity> save(UsuarioEntity t) throws GenericObjectServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponseObject<UsuarioEntity> update(UsuarioEntity t) throws GenericObjectServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponseObject<UsuarioEntity> delete(UsuarioEntity t) throws GenericObjectServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean updateVerifyCode(String coduser, String codver) {
		try {
			this.usuarioRepository.updateVerifyCode(coduser, codver);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
