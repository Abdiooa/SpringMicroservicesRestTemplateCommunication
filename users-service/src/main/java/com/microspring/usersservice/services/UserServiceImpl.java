package com.microspring.usersservice.services;

import com.microspring.usersservice.dto.*;
import com.microspring.usersservice.exceptions.UserNotFoundException;
import com.microspring.usersservice.exceptions.UserWithEmailExistAlreadyException;
import com.microspring.usersservice.models.Users;
import com.microspring.usersservice.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UsersService{
    private final UsersRepository usersRepository;
    //private final RestTemplate restTemplate;
    private final WebClient webClient;
    private final ModelMapper mapper;

//    public UserServiceImpl(@Value("${departmentservice.base.url}") String departmentUrl,RestTemplateBuilder builder) {
//        this.restTemplate = builder.rootUri(departmentUrl).build();
//    }

    @Override
    public UserResponse saveUser(UserRequest userRequest) {
        if(usersRepository.existsByEmail(userRequest.getEmail())){
            throw new UserWithEmailExistAlreadyException();
        }
        validateUser(userRequest);
        Users user = mapper.map(userRequest,Users.class);
        //Users user = maptoUser(userRequest);
        usersRepository.save(user);
        log.info("User with the id {} is saved",user.getId());
        //return maptoUserResponse(user);
        UserResponse userResponse= mapper.map(user,UserResponse.class);
        return  userResponse;
    }



    @Override
    public ResponseDto getUserById(Long id) {
        ResponseDto responseDto = new ResponseDto();
        Optional<Users> usersOptional = usersRepository.findById(id);
        if(usersOptional.isEmpty()){
            throw new UserNotFoundException();
        }
        UserDto userDto = mapper.map(usersOptional.get(),UserDto.class);
        //UserDto userDto = maptoUserDto(usersOptional.get());
        String url = "http://localhost:8081/api/v1/departments/"+usersOptional.get().getDepartmentId();
        //ResponseEntity<DepartementDto> responseEntity = restTemplate.getForEntity(url, DepartementDto.class);
        //DepartementDto departementDto = responseEntity.getBody();
        //System.out.println(responseEntity.getStatusCode());
        DepartementDto departementDto = webClient.get().uri(url).retrieve()
                        .bodyToMono(DepartementDto.class).block();
        responseDto.setUserDto(userDto);
        responseDto.setDepartementDto(departementDto);
        return responseDto;
    }

    @Override
    public List<ResponseDto> getAllUsers() {
        return null;
    }

    @Override
    public void deleteUser(Long id) {

    }
    private void validateUser(UserRequest userRequest) {
        if(userRequest.getFirstName() == null || userRequest.getLastName() == null ||
                userRequest.getEmail() == null || userRequest.getDepartmentId() == null){
            throw new IllegalArgumentException(
                    "Invalid input data."
            );
        }
    }
//    private Users maptoUser(UserRequest userRequest) {
//        return Users.builder()
//                .firstName(userRequest.getFirstName())
//                .lastName(userRequest.getLastName())
//                .email(userRequest.getEmail())
//                .departmentId(userRequest.getDepartmentId())
//                .build();
//    }
//    private UserResponse maptoUserResponse(Users user) {
//        return UserResponse.builder()
//                .id(user.getId())
//                .firstName(user.getFirstName())
//                .lastName(user.getLastName())
//                .email(user.getEmail())
//                .departmentId(user.getDepartmentId())
//                .build();
//    }
//    private UserDto maptoUserDto(Users user) {
//        return UserDto.builder()
//                .id(user.getId())
//                .firstName(user.getFirstName())
//                .lastName(user.getLastName())
//                .email(user.getEmail())
//                .build();
//    }
}
