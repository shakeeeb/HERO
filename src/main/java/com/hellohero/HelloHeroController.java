package com.hellohero;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;


/**
 * Created by Terrell Mack on 3/30/16.
 */


@Controller
public class HelloHeroController{

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String printHello(ModelMap model) {

        String hero;

        int heroNumber = (int)(Math.random() * 10 + 1);
        System.out.println(heroNumber);
        switch(heroNumber) {
            case 1:
                hero = "Saitama";
                break;
            case 2:
                hero = "Genos";
                break;
            case 3:
                hero = "Batman";
                break;
            case 4:
                hero = "Superman";
                break;
            case 5:
                hero = "Spiderman";
                break;
            case 6:
                hero = "Robin";
                break;
            case 7:
                hero = "Jason";
                break;
            case 8:
                hero = "Thor";
                break;
            case 9:
                hero = "Wonder Woman";
                break;
            case 10:
                hero = "Iron Man";
                break;
            default:
                hero = ", you're not a hero";
                break;

        }
        System.out.println(hero);
        model.addAttribute("message",hero);

        return "hello";
    }

}
