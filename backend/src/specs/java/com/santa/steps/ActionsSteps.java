package com.santa.steps;

import com.santa.Enfant;
import com.santa.restclient.IRestClient;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.fr.Alors;
import cucumber.api.java.fr.Etantdonné;
import cucumber.api.java.fr.Quand;
import okhttp3.internal.http.HttpCodec;
import org.springframework.http.HttpStatus;
import retrofit2.Response;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ActionsSteps {

    IRestClient user;


    public ActionsSteps(World world) {
        this.user = world.getUserConnecte().getRestClient();
    }

    @Etantdonné("^un enfant qui s'appelle \"([^\"]*)\"$")
    public void un_enfant_qui_s_appelle(String prenom) throws Throwable {
        Enfant enfant = new Enfant();
        enfant.prenom = prenom;
        Response<Void> response = this.user.enregistrerEnfant(enfant).execute();
        assertEquals(HttpStatus.CREATED, response.code());
    }

    @Etantdonné("^que \"([^\"]*)\" croit au Père Noel$")
    public void que_croit_au_Père_Noel(String arg1) throws Throwable {

    }

    @Quand("^\"([^\"]*)\" fait$")
    public void fait(String prenom, DataTable actions) throws Throwable {
        List<ActionEnfant> actionsEnfant = null;
        user.enregistrerActions(prenom, actionsEnfant).execute();// E,K,V must be a scalar (String, Integer, Date, enum etc)
    }

    @Alors("^les points de sagesse de \"([^\"]*)\" pour (\\d+) valent (\\d+)$")
    public void les_points_de_sagesse_de_pour_valent(String prenom, int annee, int pointsAttendus) throws Throwable {
        Response<Integer> pointsDeSagesse = user.recupererPointDeSagesse(prenom, annee).execute();// Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

}
