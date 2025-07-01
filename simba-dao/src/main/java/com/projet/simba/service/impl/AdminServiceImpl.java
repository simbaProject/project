package com.projet.simba.service.impl;

import com.projet.simba.dto.AdminDto;
import com.projet.simba.exceptions.BusinessException;
import com.projet.simba.exceptions.ErrorModel;
import com.projet.simba.mapper.AdminMapper;
import com.projet.simba.mapper.PointMapper;
import com.projet.simba.model.Admin;
import com.projet.simba.model.Users;
import com.projet.simba.model.enumType.RoleUser;
import com.projet.simba.repository.AdminRepository;
import com.projet.simba.repository.UserRepository;
import com.projet.simba.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.projet.simba.service.impl.VendeurServiceImpl.getErrorModels;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminMapper adminMapper;
    private final AdminRepository adminRepository;
    private final UserRepository userRepository;
    private final PointMapper pointMapper;
    /**
     * @param adminDto the admin dto 
     * @return
     */
    @Override
    public AdminDto createAdmin(AdminDto adminDto) {

        Optional<Users> optionalUsers=userRepository.findByAdresseMailAndDeleteAtNull(adminDto.getAdresseMail());
        boolean userNotExits= optionalUsers.isEmpty();
        List<Admin> adminList=adminRepository.findByDeleteAtIsNull();
        if(isNotReserved(adminDto.getNom().toUpperCase()) && userNotExits) {
            if (adminList.isEmpty()) {
                adminDto.setEntryKey(UUID.randomUUID());
                adminDto.setRole(RoleUser.ADMIN);
                adminDto.setNom(adminDto.getNom().toUpperCase());
                Admin admin = adminRepository.save(adminMapper.toEntity(adminDto));
                return adminMapper.toDto(admin);
            } else if (adminList.getFirst().getEntryKey().equals(adminDto.getEntryKey())) {
                adminDto.setRole(RoleUser.ADMIN);
                adminDto.setNom(adminDto.getNom().toUpperCase());
                Admin admin = adminRepository.save(adminMapper.toEntity(adminDto));
                return adminMapper.toDto(admin);
            } else {
                List<ErrorModel> errorModelList = new ArrayList<>();
                ErrorModel errorModel = new ErrorModel();
                errorModel.setCode("AUTHENTIFICATION_FAILED");
                errorModel.setMessage("Vous n'etes pas reconnu comme un admin");
                errorModelList.add(errorModel);
                throw new BusinessException(errorModelList, HttpStatus.UNAUTHORIZED);
            }
        }
        else {
            List<ErrorModel> errorModelList = getErrorModels(userNotExits);
            throw new BusinessException(errorModelList , HttpStatus.FORBIDDEN);
        }

    }

    /**
     * @param id the id 
     * @return
     */
    @Override
    public AdminDto getAdmin(UUID id) {
        Optional<Admin> optionalAdmin=adminRepository.findByIdAndDeleteAtIsNull(id);
        if(optionalAdmin.isPresent()){
            Admin admin= optionalAdmin.get();
            return adminMapper.toDto(admin);
        }
        else
            throw new IllegalArgumentException("admin doesn't exists");
    }

    /**
     * @param nom the nom 
     * @return
     */
    @Override
    public AdminDto getAdmin(String nom) {
        Optional<Admin> optionalAdmin=adminRepository.findByNomAndDeleteAtIsNull(nom);
        if(optionalAdmin.isPresent()){
            Admin admin= optionalAdmin.get();
            return adminMapper.toDto(admin);
        }
        else
            throw new IllegalArgumentException("admin doesn't exists");
    }

    /**
     * @return 
     */
    @Override
    public List<AdminDto> getAll() {
        List<Admin> adminList=adminRepository.findByDeleteAtIsNull();
        return adminList.stream()
                .map(adminMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * @param id       the id 
     * @param adminDto the admin dto
     * @return
     */
    @Override
    public AdminDto updateAdmin(UUID id, AdminDto adminDto) {
        Optional<Admin> optionalAdmin=adminRepository.findByIdAndDeleteAtIsNull(id);
        Optional<Admin> optionalAdministrator=adminRepository.findByNomAndDeleteAtIsNull(adminDto.getNom());
        if(optionalAdmin.isPresent()) {
            Admin admin = optionalAdmin.get();
            if(optionalAdministrator.isEmpty())
                admin.setNom(adminDto.getNom().toUpperCase());
            else {
                List<ErrorModel> errorModelList=new ArrayList<>();
                ErrorModel errorModel=new ErrorModel();
                errorModel.setCode("OPERATION_DENIED");
                errorModel.setMessage("Ce nom est réservé");
                errorModelList.add(errorModel);
                throw new BusinessException(errorModelList , HttpStatus.FORBIDDEN);
            }
            admin.setLocalisation(adminDto.getLocalisation());
            admin.setGeography(pointMapper.createPoint(adminDto.getLongitude(), adminDto.getLatitude()));
            return adminMapper.toDto(adminRepository.save(admin));
        }
        else
            throw new IllegalArgumentException("admin doesn't exists");
    }

    /**
     * @param id       the id 
     * @param adminDto the admin dto
     * @return
     */
    @Override
    public AdminDto updateEntryKey(UUID id, AdminDto adminDto) {
        List<Admin> adminList=adminRepository.findByDeleteAtIsNull();
        Optional<Admin> optionalAdmin=adminRepository.findByIdAndDeleteAtIsNull(id);
        if(optionalAdmin.isPresent()){
            if(optionalAdmin.get().equals(adminList.getFirst())){
                Admin admin= optionalAdmin.get();
                admin.setEntryKey(adminDto.getEntryKey());
                System.err.println(admin.getEntryKey());
                admin=adminRepository.save(admin);
                for(Admin admin1:adminList){
                    admin1.setEntryKey(admin.getEntryKey());
                    adminRepository.save(admin1);
                }
                return adminMapper.toDto(admin);
            }
            else{
                List<ErrorModel> errorModelList=new ArrayList<>();
                ErrorModel errorModel=new ErrorModel();
                errorModel.setCode("AUTHENTIFICATION_FAILED");
                errorModel.setMessage("Cet admin n'a pas le droit de changer la clé d'entrée C'est le premier dans la liste des admins qui en a le droit veuillez consulter celle-ci et/ou le contacter(L'admin)");
                errorModelList.add(errorModel);
                throw new BusinessException(errorModelList,HttpStatus.UNAUTHORIZED);
            }
        }
        else
            throw new IllegalArgumentException("admin doesn't exists");
    }

    /**
     * @param id the id 
     * @return
     */
    @Override
    public boolean deleteAdmin(UUID id) {
        Optional<Admin> optionalAdmin=adminRepository.findByIdAndDeleteAtIsNull(id);
        if(optionalAdmin.isPresent()) {
            Admin admin = optionalAdmin.get();
            admin.setDeleteAt(LocalDateTime.now());
            adminRepository.save(admin);
            return true;
        }
        else
            return false;
    }
    private boolean  isNotReserved(String name){
        Optional<Admin> optionalAdmin =adminRepository.findByNomAndDeleteAtIsNull(name);
        return optionalAdmin.isEmpty();

    }

}
