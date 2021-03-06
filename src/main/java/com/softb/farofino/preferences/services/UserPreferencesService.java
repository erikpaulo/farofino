package com.softb.farofino.preferences.services;

import com.softb.farofino.preferences.model.UserPreferences;
import com.softb.farofino.preferences.repository.UserPreferencesRepository;
import com.softb.system.security.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Created by eriklacerda on 3/1/16.
 */
@Service
public class UserPreferencesService {

    @Autowired
    private UserPreferencesRepository userPreferencesRepository;

    @Inject
    private UserAccountService userAccountService;

    public UserPreferences get(Integer groupId){
        UserPreferences up = userPreferencesRepository.findOneByGroup(groupId);

        if (up == null){
            up = new UserPreferences();
            up.setGroupId(groupId);
            userPreferencesRepository.save(up);
        }

        return up;
    }

}
