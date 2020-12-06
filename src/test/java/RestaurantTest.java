import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    //REFACTOR ALL THE REPEATED LINES OF CODE
    Restaurant restaurant;

    public void restaurantInit(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = new Restaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
        createMenu();
    }

    public void createMenu() {
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }


    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time() {
        //WRITE UNIT TEST CASE HERE
        LocalTime openingTime = LocalTime.now().minusHours(1);
        LocalTime closingTime = LocalTime.now().plusHours(1);
        restaurant = new Restaurant("1Stop cafe", "Bangalore", openingTime, closingTime);
        createMenu();

        boolean restaurantState=restaurant.isRestaurantOpen();
        assertThat(restaurantState, equalTo(true));
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time() {
        //WRITE UNIT TEST CASE HERE
        LocalTime openingTime = LocalTime.now().plusHours(1);
        LocalTime closingTime = LocalTime.now().minusHours(1);
        restaurant = new Restaurant("1Stop cafe", "Bangalore", openingTime, closingTime);
        createMenu();

        boolean restaurantState=restaurant.isRestaurantOpen();
        assertThat(restaurantState, equalTo(false));
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1() {

        restaurantInit();

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie", 319);
        assertEquals(initialMenuSize + 1, restaurant.getMenu().size());
    }

    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {

        restaurantInit();

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize - 1, restaurant.getMenu().size());
    }

    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        restaurantInit();

        assertThrows(itemNotFoundException.class, () -> restaurant.removeFromMenu("French fries"));
    }

    //Added Test case for GetMenu() method
    @Test
    public void get_menu_should_return_the_menu_of_the_restaurant_when_the_restaurant_exists(){
        restaurantInit();

        assertNotEquals(restaurant.getMenu().size(),0);
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}