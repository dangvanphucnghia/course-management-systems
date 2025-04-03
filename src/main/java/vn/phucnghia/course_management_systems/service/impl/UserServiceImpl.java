package vn.phucnghia.course_management_systems.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.crypto.password.PasswordEncoder;
import vn.phucnghia.course_management_systems.config.AppConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.phucnghia.course_management_systems.common.UserStatus;
import vn.phucnghia.course_management_systems.controller.request.UserCreationRequest;
import vn.phucnghia.course_management_systems.controller.response.UserResponse;
import vn.phucnghia.course_management_systems.exception.ResourceNotFoundException;
import vn.phucnghia.course_management_systems.model.AddressEntity;
import vn.phucnghia.course_management_systems.model.UserEntity;
import vn.phucnghia.course_management_systems.repository.AddressRepository;
import vn.phucnghia.course_management_systems.repository.UserRepository;
import vn.phucnghia.course_management_systems.service.UserService;
import vn.phucnghia.course_management_systems.controller.request.UserChangPasswordRequest;
import vn.phucnghia.course_management_systems.controller.request.UserUpdateRequest;


import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j(topic = "USER-SERVICE")
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserResponse> findAll() {
        return List.of();
    }

    @Override
    public UserResponse findById(Long id) {
        return null;
    }

    @Override
    public UserResponse findByUsername(String username) {
        return null;
    }

    @Override
    public UserResponse findByEmail(String email) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public long save(UserCreationRequest req) {
        log.info("Saving user: {}", req);
        UserEntity user = new UserEntity();
        user.setFirstName(req.getFirstName());
        user.setLastName(req.getLastName());
        user.setGender(req.getGender());
        user.setBirthday(req.getBirthday());
        user.setEmail(req.getEmail());
        user.setPhone(req.getPhone());
        user.setUsername(req.getUsername());
        user.setType(req.getType());
        user.setStatus(UserStatus.NONE);
        userRepository.save(user);
        log.info("Saved user: {}", user);

        if(user.getId() != null){
            log.info("User id: {}", user.getId());
            List<AddressEntity> addresses = new ArrayList<>();
            req.getAddresses().forEach(address ->{
                AddressEntity addressEntity = new AddressEntity();
                addressEntity.setApartmentNumber(address.getApartmentNumber());
                addressEntity.setFloor(address.getFloor());
                addressEntity.setBuilding(address.getBuilding());
                addressEntity.setStreetNumber(address.getStreetNumber());
                addressEntity.setStreet(address.getStreet());
                addressEntity.setCity(address.getCity());
                addressEntity.setCountry(address.getCountry());
                addressEntity.setAddressType(address.getAddressType());
                addressEntity.setUserId(user.getId());
                addresses.add(addressEntity);
            });

            addressRepository.saveAll(addresses);
            log.info("Saved addresses: {}", addresses);
        }
        return user.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(UserUpdateRequest req) {
        log.info("Updating user: {}", req);

        UserEntity user = getUserEntity(req.getId());
        user.setFirstName(req.getFirstName());
        user.setLastName(req.getLastName());
        user.setGender(req.getGender());
        user.setBirthday(req.getBirthday());
        user.setEmail(req.getEmail());
        user.setPhone(req.getPhone());
        user.setUsername(req.getUsername());
        userRepository.save(user);
        log.info("Updated user: {}", req);

        List<AddressEntity> addresses = new ArrayList<>();

        req.getAddresses().forEach(address->{
            AddressEntity addressEntity = addressRepository.findByUserIdAndAddressType(user.getId(), address.getAddressType());
            if(addressEntity == null)
            {
                addressEntity = new AddressEntity();
            }

            addressEntity.setApartmentNumber(address.getApartmentNumber());
            addressEntity.setFloor(address.getFloor());
            addressEntity.setBuilding(address.getBuilding());
            addressEntity.setStreetNumber(address.getStreetNumber());
            addressEntity.setStreet(address.getStreet());
            addressEntity.setCity(address.getCity());
            addressEntity.setCountry(address.getCountry());
            addressEntity.setAddressType(address.getAddressType());
            addressEntity.setUserId(user.getId());

            addresses.add(addressEntity);
            log.info("Updated addresses: {}", addresses);

        });

        addressRepository.saveAll(addresses);

    }

    @Override
    public void changePassword(UserChangPasswordRequest req) {
        log.info("Changing password for user: {}",req);

        UserEntity user = getUserEntity(req.getId());
        if(req.getPassword().equals(req.getConfirmPassword())){
            user.setPassword(passwordEncoder.encode(req.getPassword()));
        }
        userRepository.save(user);
        log.info("Changed password for user: {}",req);
    }

    @Override
    public void delete(Long id) {

    }

    private UserEntity getUserEntity(Long id){
        return userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found"));
    }
}
