package com.santa.steps;

import com.santa.restclient.RestUtilitaires;
import com.santa.utils.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class World {
    private User userConnecte;
    private User god;
    private User assure;
    private RestUtilitaires restUtilitaires;

    @Autowired
    public World(RestUtilitaires restUtilitaires) {
        this.restUtilitaires = restUtilitaires;
    }


    public User getUserConnecte() {
        if (userConnecte == null) {
            userConnecte = restUtilitaires.getUtilisateur();
        }
        return userConnecte;
    }
}
