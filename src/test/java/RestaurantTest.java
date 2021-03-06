import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

class RestaurantTest {
    Restaurant restaurant;


    @BeforeEach
    public void addNewRestaurantfortesting() {
        restaurant = new Restaurant("Amelie's cafe", "Chennai", LocalTime.parse("10:30:00"), LocalTime.parse("22:00:00"));
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 269);


    }
    //REFACTOR ALL THE REPEATED LINES OF CODE

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE

    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time() {

        Restaurant newRestaurant = Mockito.spy(restaurant);
        LocalTime afterOpentime = LocalTime.parse("12:00");
        when(newRestaurant.getCurrentTime()).thenReturn(afterOpentime);
        assertTrue(newRestaurant.isRestaurantOpen());
    }

    //WRITE UNIT TEST CASE HERE

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time() {

        Restaurant newRestaurant = Mockito.spy(restaurant);
        LocalTime outsideOpentime = LocalTime.parse("09:00");
        when(newRestaurant.getCurrentTime()).thenReturn(outsideOpentime);
        assertFalse(newRestaurant.isRestaurantOpen());

        //WRITE UNIT TEST CASE HERE

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1() {

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie", 319);
        assertEquals(initialMenuSize + 1, restaurant.getMenu().size());
    }

    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize - 1, restaurant.getMenu().size());
    }

    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {

        assertThrows(itemNotFoundException.class, () -> restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //<<<<<<<<<<<<<<<<<<<<<<<<<<<<TotalCost>>>>>>>>>>>>>>>>>>>>>>>>

    @Test
    public void total_cost_should_be_zero_when_no_item_is_added() {
        List<String> menuItem= new ArrayList<>();
        int initialSelectedItems = menuItem.size();
        int totalPrice= restaurant.getTotalCost(menuItem);
        assertEquals(0,0);

    }

    @Test
    public void total_cost_should_increase_by_388_when_items_are_added_to_list(){
        List<String> menuItem= new ArrayList<>();
        menuItem.add(new String("Sweet corn soup"));
        menuItem.add(new String("Vegetable lasagne"));
        int totalCost= restaurant.getTotalCost(menuItem);
        assertEquals(388,388);

    }

    @Test
    public void total_cost_should_decrease_to_119_when_one_item_is_removed(){
        List<String> menuItem= new ArrayList<>();
        menuItem.add(new String("Sweet corn soup"));
        menuItem.add(new String("Vegetable lasagne"));
        menuItem.remove("Vegetable lasagne");
        int totalCost= restaurant.getTotalCost(menuItem);
        assertEquals(119,119);

    }
}

//<<<<<<<<<<<<<<<<<<<<<<<<<<<<TotalCost>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
