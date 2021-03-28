package com.coursework.demo.controller;

import com.coursework.demo.dto.AddCrewDTO;
import com.coursework.demo.dto.CrewDTO;
import com.coursework.demo.entity.Crew;
import com.coursework.demo.mapper.CrewMapper;
import com.coursework.demo.service.CrewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = "Crew API")
@RequestMapping("/v1/crews")
public class CrewController {

    private final CrewService crewService;
    private final CrewMapper crewMapper;

    @Autowired
    public CrewController(CrewService crewService, CrewMapper crewMapper) {
        this.crewService = crewService;
        this.crewMapper = crewMapper;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get crew info by id")
    public ResponseEntity<CrewDTO> get(@PathVariable("id") long id) {
        Crew crew = crewService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(crewMapper.convertToDto(crew));
    }


    @GetMapping
    @ApiOperation(value = "Get the list of all crews")
    public ResponseEntity<List<CrewDTO>> getAll(@PageableDefault(sort = {"id"}) Pageable pageable) {
        return ResponseEntity.ok().body(crewMapper.convertToDtoList(crewService.getAll(pageable)));
    }


    @PostMapping
    @ApiOperation(value = "Create new crew")
    public ResponseEntity<CrewDTO> save(@RequestBody AddCrewDTO addCrewDTO) {
        Crew crew = crewService.save(crewMapper.convertToEntity(addCrewDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(crewMapper.convertToDto(crew));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update existing crew by id")
    public ResponseEntity<CrewDTO> update(@PathVariable("id") Long id, @RequestBody CrewDTO crewDTO) {
        if (id == crewDTO.getId()) {
            Crew crew = crewService.save(crewMapper.convertToEntity(crewDTO));
            return ResponseEntity.status(HttpStatus.OK).body(crewMapper.convertToDto(crew));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete crew by id")
    public ResponseEntity delete(@PathVariable("id") long id) {
        Crew crew = crewService.getById(id);
        crewService.delete(crew);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
