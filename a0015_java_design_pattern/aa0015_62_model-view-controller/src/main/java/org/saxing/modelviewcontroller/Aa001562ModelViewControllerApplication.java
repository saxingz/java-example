package org.saxing.modelviewcontroller;


/**
 * main
 *
 * @author saxing 2019/4/21 14:22
 */
public class Aa001562ModelViewControllerApplication {

    public static void main(String[] args) {
        // create model, view and controller
        GiantModel giant = new GiantModel(Health.HEALTHY, Fatigue.ALERT, Nourishment.SATURATED);
        GiantView view = new GiantView();
        GiantController controller = new GiantController(giant, view);
        // initial display
        controller.updateView();
        // controller receives some interactions that affect the giant
        controller.setHealth(Health.WOUNDED);
        controller.setNourishment(Nourishment.HUNGRY);
        controller.setFatigue(Fatigue.TIRED);
        // redisplay
        controller.updateView();
    }

}
