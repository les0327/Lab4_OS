package lab4.controller;

import lab4.model.Point;
import lab4.service.SchedulerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AppController {

    private final static Logger logger = LoggerFactory.getLogger(AppController.class);

    public AppController() {
    }

    @RequestMapping(path = "/doScheduling/{n}", method = RequestMethod.GET)
    public String doScheduling(@PathVariable int n) {
        SchedulerService service = new SchedulerService(n);
        service.doScheduling();
        StringBuilder sb1 = new StringBuilder("In<p>-");
        StringBuilder sb2 = new StringBuilder("Out<br>-");
        for (int i = 0; i < n; i++) {
            sb1.append(" ").append(i);
            sb2.append(" ").append(service.getTask()[i]);
        }
        sb1.append("<br>");
        sb2.append("<br>");
        for (int i = 0; i < n; i++) {
            sb1.append(i);
            sb2.append(service.getCpu()[i]);
            for (int j = 0; j < n; j++) {
                sb1.append(" ").append(service.getStartMatrix()[i][j]);
                sb2.append(" ").append(service.getMatrix()[i][j]);
            }
            sb1.append("<br>");
            sb2.append("<br>");
        }
        sb2.append("</p>");
        System.out.println(sb1);
        System.out.println(sb2);

        return sb1.toString() + sb2.toString();
    }


    @RequestMapping(path = "/getTimeNPoints", method = RequestMethod.GET)
    public ResponseEntity<List<Point>> getEvTimePoints() {
        logger.info("/getTimeNPoints");
        List<Point> timeNPoints = new ArrayList<>();
        for (int i = 3; i < 120; i++) {
            SchedulerService service = new SchedulerService(i);
            long start = System.nanoTime();
            service.doScheduling();
            long end = System.nanoTime();
            timeNPoints.add(new Point<>(i, (end - start)/500));
        }
        return new ResponseEntity<>(timeNPoints, HttpStatus.OK);
    }

}
