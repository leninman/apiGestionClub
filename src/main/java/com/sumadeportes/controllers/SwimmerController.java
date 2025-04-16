package com.sumadeportes.controllers;

import com.sumadeportes.model.dto.SwimmerDto;
import com.sumadeportes.model.dto.RespDto;
import com.sumadeportes.model.entities.PersonId;
import com.sumadeportes.model.entities.Swimmer;
import com.sumadeportes.services.ISwimmerService;
import com.sumadeportes.services.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/swimmer")
@CrossOrigin(origins = "https://erika-github.github.io")
public class SwimmerController {

    private final ISwimmerService swimmerService;
    private final IUserService userService;

    public SwimmerController(ISwimmerService swimmerService, IUserService userService) {
        this.swimmerService = swimmerService;
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<RespDto> saveSwimmer(@RequestBody SwimmerDto swimmerDto) {
        RespDto response = new RespDto();
        try {
            Swimmer swimmer = swimmerService.getSwimmerById(new PersonId(swimmerDto.getDocumentType(), swimmerDto.getDocumentNumber()));
            if (swimmer!=null) {
                response.setMessage("Swimmer already exists");
                response.setCode("409");
                response.setData(swimmer);
                return new ResponseEntity<>(response, HttpStatus.CONFLICT);
            }
            com.sumadeportes.model.entities.Swimmer savedSwimmer = swimmerService.saveSwimmer(swimmerDto);
           // savedSwimmer.setUser(userService.getUserByEmail(swimmerDto.getEmail()).get());
            response.setMessage("Swmmer saved successfully");
            response.setCode("201");
            response.setData(savedSwimmer);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.setMessage("Error saving swimmer");
            response.setCode("500");
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/getById")
    public ResponseEntity<RespDto> getById(@RequestBody PersonId swimmerId) {
        Swimmer swimmer = swimmerService.getSwimmerById(swimmerId);
        RespDto response = new RespDto();

        if (swimmer!=null) {
            response.setMessage("Swimmer found");
            response.setCode("200");
            response.setData(swimmer);
        } else {
            response.setMessage("Swimmer not found");
            response.setCode("404");
            response.setData(null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping("/getList")
    public ResponseEntity<RespDto> getList() {
        RespDto response = new RespDto();
        response.setMessage("Swimmers found");
        response.setCode("200");
        response.setData(swimmerService.getAllSwimmers());
        return ResponseEntity.ok(response);
    }
}
