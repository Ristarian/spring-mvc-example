package controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.sql.rowset.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/cars")
public class CarController {

    List<Car> cars = new ArrayList<Car>();

    CarController(){
        cars.add(new Car(1, "BMV", 740));
        cars.add(new Car(2, "Opel", 620));
    }

    @GetMapping
    @ResponseBody
    List<Car> getCars(){
        return cars;
    }

    @GetMapping("/mark")
    ResponseEntity<List<Car>> getCarByMark(@RequestParam(value = "mark", defaultValue = "BMV")String mark){
        return ResponseEntity
                .ok(getCarsBy(car -> mark.equals(car.getMark())));
    }

   private List<Car> getCarsBy(Predicate<Car> predicate) {
       return cars.stream()
               .filter(predicate)
               .collect(Collectors.toList());
   }

   @GetMapping("/{mark:[A-Za-z]}}")
    @ResponseBody
    List<Car> getCarsByMarkParthVariable(@PathVariable("mark")String mark){
        return getCarsBy(car -> mark.equals(car.getMark()));
   }



}

