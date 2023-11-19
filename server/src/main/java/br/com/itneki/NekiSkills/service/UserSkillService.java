package br.com.itneki.NekiSkills.service;

import br.com.itneki.NekiSkills.domain.Skill;
import br.com.itneki.NekiSkills.domain.User;
import br.com.itneki.NekiSkills.domain.UserSkill;
import br.com.itneki.NekiSkills.domain.UserSkillKey;
import br.com.itneki.NekiSkills.dto.UserSkillRequestDTO;
import br.com.itneki.NekiSkills.dto.UserSkillResponseDTO;
import br.com.itneki.NekiSkills.repository.SkillRepository;
import br.com.itneki.NekiSkills.repository.UserRepository;
import br.com.itneki.NekiSkills.repository.UserSkillRepository;
import br.com.itneki.NekiSkills.utils.UtilsMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;


@Service
public class UserSkillService {

    @Autowired
    UserSkillRepository repository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SkillRepository skillRepository;

    @Autowired
    UtilsMethods utilsMethods;


    public List<UserSkillResponseDTO> findUserSkillsByUser(UUID userId) {
        userRepository.findById(userId)
                      .orElseThrow(() -> new NoSuchElementException("Error! Cannot find user with id: " + userId));

        List<UserSkill> userSkillList = repository.findUserSkillsByUserId(userId);
        return userSkillList.stream()
                .map(userSkill -> utilsMethods.userSkillResponseDTOFactory(userSkill))
                .toList();
    }


    public UserSkillResponseDTO updateSkillLevelByUser(UserSkillRequestDTO userSkillRequestDTO) {
        UserSkillKey userSkillKey = new UserSkillKey(userSkillRequestDTO.getUserId(), userSkillRequestDTO.getSkillId());
        Optional<UserSkill> userSkill = repository.findById(userSkillKey);

        if (userSkill.isEmpty()){
            throw new NoSuchElementException("Error! Cannot update UserSkill, id not found: " + userSkillKey);
        }

        User user = userRepository.findById(userSkillRequestDTO.getUserId())
                .orElseThrow(() -> new NoSuchElementException("Error! Cannot find user with id: " + userSkillRequestDTO.getUserId()));

        Skill skill = skillRepository.findById(userSkillRequestDTO.getSkillId())
                .orElseThrow(() -> new NoSuchElementException("Error! Cannot find skill with id: " + userSkillRequestDTO.getSkillId()));

        UserSkill userSkillUpdated = repository.save(new UserSkill(userSkillKey, user, skill, userSkillRequestDTO.getSkillLevel()));

        return utilsMethods.userSkillResponseDTOFactory(userSkillUpdated);
    }

    public UserSkill linkSkillWithUser(UserSkillRequestDTO userSkillRequestDTO) {
        UserSkillKey userSkillKey = new UserSkillKey(userSkillRequestDTO.getUserId(), userSkillRequestDTO.getSkillId());
        User user = userRepository.findById(userSkillRequestDTO.getUserId())
                .orElseThrow(() -> new NoSuchElementException("Error! Cannot find user with id: " + userSkillRequestDTO.getUserId()));
        Skill skill = skillRepository.findById(userSkillRequestDTO.getSkillId())
                .orElseThrow(() -> new NoSuchElementException("Error! Cannot find skill with id: " + userSkillRequestDTO.getSkillId()));
        return repository.save(new UserSkill(userSkillKey, user, skill, userSkillRequestDTO.getSkillLevel()));
    }

    public boolean unlinkSkillWithUser(UUID userId, UUID skillId) {
        UserSkillKey userSkillKey = new UserSkillKey(userId, skillId);

        Optional<UserSkill> userSkillFound = repository.findById(userSkillKey);
        if (userSkillFound.isPresent()) {
            repository.delete(userSkillFound.get());
            return true;
        } else
            throw new NoSuchElementException("Error! User Id or Skill Id cannot be found!");
    }



}
