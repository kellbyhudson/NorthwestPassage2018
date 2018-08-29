package controllers;

import java.util.LinkedList;
import java.util.List;

public class Crew
{
    private List<String> names = new LinkedList<>();

    public Crew()
    {
        names.add("Sansa");
        names.add("Arya");
        names.add("Bran");
        names.add("Tyrion");
        names.add("Jaime");
        names.add("Hound");
        names.add("Tyrion");
        names.add("Varys");
        names.add("Brienne");
        names.add("Tywin");
        names.add("LittleFinger");
    }

    String crewDie(int i)
    {
       return names.remove(i);
    }

    public void refreshCrew()
    {
        names.clear();
    }
}
