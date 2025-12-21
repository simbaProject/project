package com.projet.simba;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projet.simba.dto.UserDto;
import com.projet.simba.model.enumType.RoleUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("Doit retourner un statut 201 et un objet json correct")
    void testAdd_User() throws Exception {
        //Arrange
        UserDto user = new UserDto();
        user.setAdresseMail("test@example.com");
        user.setMotDePasse("StrongPass123");
        user.setLocalisation("Yaoundé");
        user.setNom("John");
        user.setRole(RoleUser.CLIENT);


        mockMvc.perform(post("http://localhost:8000/users/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.adresseMail").value("test@example.com"))
                .andExpect(jsonPath("$.nom").value("JOHN"));
    }

    @Test
    @DisplayName("Doit retourner un statut de 400 pour des champs manquants")
    void testAddUserFailed_WhileFillNotPresent() throws Exception{
        UserDto user = new UserDto();
        user.setNom("Bill");
        user.setRole(RoleUser.ADMIN);
        mockMvc.perform(post("http://localhost:8000/users/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$",hasSize(3)));
    }

    @Test
    @DisplayName("Doit retourner un statut 200 et renvoyer les informations de la personne recherchée sous for,e de json")
    void testGetUserSuccessById() throws Exception{

    }




}
