package com.projet.simba.service.impl;

import com.projet.simba.dto.UserDto;
import com.projet.simba.exceptions.BusinessException;
import com.projet.simba.exceptions.ErrorModel;
import com.projet.simba.mapper.PointMapper;
import com.projet.simba.mapper.UserMapper;
import com.projet.simba.model.Users;
import com.projet.simba.repository.UserRepository;
import com.projet.simba.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final PointMapper pointMapper;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    

    /**
     * @param id      of user
     * @param userDto the object dto of user
     * @return the object users
     */
    @Override
    public UserDto updateUserMail(UUID id, UserDto userDto) {
        Optional<Users> optionalUsers=userRepository.findByIdAndDeleteAtIsNull(id);
        Optional<Users> usersOptional=userRepository.findByAdresseMailAndDeleteAtNull(userDto.getAdresseMail());
        if(optionalUsers.isPresent() && usersOptional.isEmpty()){
            Users users=optionalUsers.get();
            users.setAdresseMail(userDto.getAdresseMail());
            return userMapper.toDto(userRepository.save(users));
        } else if (usersOptional.isPresent()) {
            List<ErrorModel> errorModelList=new ArrayList<>();
            ErrorModel errorModel=new ErrorModel();
            errorModel.setCode("OPERATION_DENIED");
            errorModel.setMessage("Cette adresse mail est utilisée");
            errorModelList.add(errorModel);
            throw new BusinessException(errorModelList ,HttpStatus.FORBIDDEN);

        }
        throw new IllegalArgumentException("user doesn't exists");
    }

    /**
     * @param id          the id
     * @param userDto     the user dto
     * @param oldPassWord the old pass word
     */
    @Override
    public void updateUserPassWord(UUID id, UserDto userDto, String oldPassWord) {
        Optional<Users> optionalUsers=userRepository.findByIdAndDeleteAtIsNull(id);
        if(optionalUsers.isPresent()) {
            Users users = optionalUsers.get();
            if(users.getMotDePasse().equals(oldPassWord))
            {
                users.setMotDePasse(userDto.getMotDePasse());
                userRepository.save(users);
            }
            else {
                List<ErrorModel> errorModelList = new ArrayList<>();
                ErrorModel errorModel=new ErrorModel();
                errorModel.setCode("AUTHENTICATION_FAILED");
                errorModel.setMessage("entrez d'abord votre ancien mot de passe correctement");
                errorModelList.add(errorModel);
                throw new BusinessException(errorModelList,HttpStatus.FORBIDDEN);
            }
        }
        else
            throw new IllegalArgumentException("utilisateur non trouvé");
    }

    /**
     * @param id      the id
     * @param userDto the user dto
     * @return objet user
     */
    @Override
    public UserDto updateMontantCompte(UUID id, UserDto userDto) {
        Optional<Users> optionalUsers=userRepository.findByIdAndDeleteAtIsNull(id);
        if(optionalUsers.isPresent()){
            Users users=optionalUsers.get();
            users.setMontantCompte(userDto.getMontantCompte());
            return userMapper.toDto(userRepository.save(users));
        }

        throw new IllegalArgumentException("user doesn't exists");
    }

    /**
     * @param id      of user 
     * @param userDto object
     * @return new information as object
     */
    @Override
    public UserDto updateLocation(UUID id, UserDto userDto) {
        Optional<Users> optionalUsers=userRepository.findByIdAndDeleteAtIsNull(id);
        if(optionalUsers.isPresent()){
            Users users=optionalUsers.get();
            users.setLocalisation(userDto.getLocalisation());
            return userMapper.toDto(userRepository.save(users));
        }

        throw new IllegalArgumentException("user doesn't exists");
    }

    /**
     * @param id      of user
     * @param userDto
     * @return
     */
    @Override
    public UserDto updateCoordonnees(UUID id, UserDto userDto) {
        Optional<Users> optionalUsers=userRepository.findByIdAndDeleteAtIsNull(id);
        if(optionalUsers.isPresent()){
            Users users=optionalUsers.get();
            users.setGeography(pointMapper.createPoint(userDto.getLongitude(), userDto.getLatitude()));
            return userMapper.toDto(userRepository.save(users));
        }

        throw new IllegalArgumentException("user doesn't exists");
    }

    /**
     * @param id 
     * @return
     */
    @Override
    public UserDto getUser(UUID id) {
        Optional<Users> optionalUsers=userRepository.findByIdAndDeleteAtIsNull(id);
        if (optionalUsers.isPresent()){
            Users user=optionalUsers.get();
            return userMapper.toDto(user);
        }
        else
            throw new IllegalArgumentException("user doesn't exists");
    }
}
