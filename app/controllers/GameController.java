package controllers;

import play.data.DynamicForm;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import play.twirl.api.Html;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Random;

public class GameController extends Controller
{
    private FormFactory formFactory;


    @Inject
    public GameController(FormFactory formFactory)
    {
        this.formFactory = formFactory;
    }

    public Result getWelcome()
    {
        DynamicForm form = formFactory.form().bindFromRequest();

        String name = session().get("name");

        String playerName = "Enter Name Here";

        if (name != null)
        {
            session().put("name", playerName);
        }

        return ok(views.html.welcome.render(playerName));
    }

    public Result postStart()
    {
        DynamicForm form = formFactory.form().bindFromRequest();

        String playerName = form.get("name");

        Html Result = views.html.start.render();

        if (playerName != null)
        {
            session().put("name", playerName);
        }

        session().put("crew", "10");

        if(playerName.equalsIgnoreCase("louis"))
        {
            Result = views.html.louis.render();
        }


        return ok(Result);
    }

    public Result postLouisEasterEgg()
    {
        return ok(views.html.louis.render());
    }

    public Result postEastFromEngland()
    {
        Crew names = new Crew();
        String crew = session().get("crew");
        int crewRemaining = Integer.parseInt(crew);
        String deadman = names.crewDie(crewRemaining);
        crewRemaining--;
        String crewLeft = String.valueOf(crewRemaining);
        session().put("crew", crewLeft);
        crew = session().get("crew");

        Html Result = views.html.eastfromengland.render(crew, deadman);

        if(crewRemaining < 5)
        {
            Result = views.html.refuge.render(crew);
        }

        return ok(Result);
    }

    public Result postNorthFromEngland()
    {
        DynamicForm form = formFactory.form().bindFromRequest();

        String playerName = session().get("name");

        String crew = session().get("crew");

        Html Result = views.html.northfromengland.render(playerName, crew);

        Random random = new Random();
        int number = random.nextInt(4);

        if(number == 1)
        {
            Result = views.html.threeeyeraven.render(playerName, crew);
        }
        return ok(Result);
    }

    public Result postWestFromEngland()
    {
        Crew names = new Crew();
        String crew = session().get("crew");
        int crewRemaining = Integer.parseInt(crew);
        String deadman = names.crewDie(crewRemaining);
        crewRemaining--;
        String crewLeft = String.valueOf(crewRemaining);
        session().put("crew", crewLeft);
        crew = session().get("crew");

        Html Result = views.html.westfromengland.render(crew, deadman);

        if(crewRemaining < 5)
        {
            Result = views.html.refuge.render(crew);
        }

        return ok(Result);
    }

    public Result postEastEnd()
    {
        DynamicForm form = formFactory.form().bindFromRequest();

        String playerName = session().get("name");

        String crew = session().get("crew");
        return ok(views.html.eastend.render(playerName, crew));
    }

    public Result postWestEnd()
    {
        DynamicForm form = formFactory.form().bindFromRequest();

        String playerName = session().get("name");

        String crew = session().get("crew");
        return ok(views.html.westend.render(playerName, crew));
    }

    public Result postHomePort()
    {
        String crew = session().get("crew");

        return ok(views.html.homeport.render(crew));
    }

    public Result postLeaveToNorth()
    {
        String crew = session().get("crew");

        return ok(views.html.leavetonorth.render(crew));
    }

    public Result postRefuge()
    {
        String crew = session().get("crew");

        return ok(views.html.refuge.render(crew));
    }

    public Result postRaven()
    {
        String crew = session().get("crew");

        String playerName = session().get("name");

        return ok(views.html.threeeyeraven.render(playerName, crew));
    }

    public Result getKittens()
    {
        return ok(views.html.kittens.render());
    }
}
