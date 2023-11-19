package br.com.itneki.NekiSkills.controller;

import br.com.itneki.NekiSkills.domain.User;
import br.com.itneki.NekiSkills.domain.UserSkill;
import br.com.itneki.NekiSkills.dto.UserSkillRequestDTO;
import br.com.itneki.NekiSkills.dto.UserSkillResponseDTO;
import br.com.itneki.NekiSkills.service.UserSkillService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user-skills")
public class UserSkillController {
    @Autowired
    UserSkillService service;

    @GetMapping("/user/{id}")
    @Operation(summary = "Listagem de todas as skills de determinado usuário", description = "Endpoint responsável por listar todas as skills com base no id do usuário")
    public ResponseEntity<List<UserSkillResponseDTO>> findSkillsByUser(@PathVariable UUID id){
        return new ResponseEntity<>(service.findUserSkillsByUser(id), HttpStatus.OK);
    }

    @PutMapping
    @Operation(summary = "Atualizar Associação de skill", description = "Endpoint responsável por atualizar a relação entre usuário e skill")

    public ResponseEntity<UserSkillResponseDTO> updateSkillLevelByUser(@RequestBody UserSkillRequestDTO userSkillRequestDTO){
        return new ResponseEntity<>(service.updateSkillLevelByUser(userSkillRequestDTO), HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Salvar nova Associação de skill", description = "Endpoint responsável por salvar uma nova a relação entre usuário e skill")

    public ResponseEntity<UserSkill> linkSkillWithUser(@RequestBody UserSkillRequestDTO userSkillRequestDTO){
        return new ResponseEntity<>(service.linkSkillWithUser(userSkillRequestDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{userId}/{skillId}")
    @Operation(summary = "Apagar Associação de skill", description = "Endpoint responsável por apagar a relação entre usuário e skill")

    public ResponseEntity<User> unlinkSkillWithUser(@PathVariable UUID userId, @PathVariable UUID skillId){
        boolean isUserUnlinked = service.unlinkSkillWithUser(userId, skillId);
        return ResponseEntity.status(isUserUnlinked ? HttpStatus.OK : HttpStatus.NOT_FOUND).build();
    }
}
