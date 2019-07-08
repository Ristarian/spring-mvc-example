package controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/cars")
public class CarController {

    List<Car> cars = new ArrayList<Car>();

    public CarController() {
        cars.add(new Car(1, "BMV", 720));
        cars.add(new Car(2, "AUDI", 690));
    }

    @GetMapping("/view")
    ModelAndView getCarsView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("cars");
        modelAndView.addObject("cars", cars);
        return modelAndView;
    }

    @GetMapping
    @ResponseBody
    List<Car> getCars() {
        return cars;
    }

    @GetMapping("/marks")
    @ResponseBody
    ResponseEntity<List<Car>> getCarsByMark(@RequestParam(value = "mark",
            defaultValue = "BMV") String mark) {
        return ResponseEntity
                .ok(getCarsBy(car -> mark.equals(car.getMark())));
    }

    @GetMapping("/power")
    @ResponseBody
    List<Car> getCarsByMarkAndPower(
            @RequestParam(value = "mark") String mark,
            @RequestParam(value = "power", required = false) Integer power) {
        if (StringUtils.isEmpty(power)) {
            return getCarsBy(car -> mark.equals(car.getMark()));
        }
        return getCarsBy(car -> mark.equals(car.getMark()) && (power == car.getPower(
        )));
    }

    private List<Car> getCarsBy(Predicate<Car> predicate) {
        return cars.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    @GetMapping("/{mark:[A-Za-z]+}")
    @ResponseBody List<Car> getCarsByMarkPathVariable(@PathVariable("mark") String mark) {
       return getCarsBy(car -> mark.equals(car.getMark()));
    }

    @GetMapping("/{id:\\d+}")
    @ResponseBody
    List<Car> getCarsByIdPathVariable(@PathVariable("id") int id) {
        return getCarsBy(car -> id == car.getId());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    Car addCar(@RequestBody Car car){
        cars.add(car);
        return car;
    }
}

