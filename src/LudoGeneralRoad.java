import java.util.HashMap;
import java.util.Map;

public class LudoGeneralRoad {
    Map<Integer, Cells> ludoGeneralRoad;

    public LudoGeneralRoad() {
        ludoGeneralRoad = new HashMap<>();
        ludoGeneralRoad.put(0, new StartCell());
        ludoGeneralRoad.put(1, new FreeCell());
        ludoGeneralRoad.put(2, new FreeCell());
        ludoGeneralRoad.put(3, new SafetyCell());
        ludoGeneralRoad.put(4, new FreeCell());
        ludoGeneralRoad.put(5, new FreeCell());
        ludoGeneralRoad.put(6, new FreeCell());
        ludoGeneralRoad.put(7, new SafetyCell());
        ludoGeneralRoad.put(8, new FreeCell());
        ludoGeneralRoad.put(9, new FreeCell());
        ludoGeneralRoad.put(10, new FreeCell());
        ludoGeneralRoad.put(11, new FreeCell());
        ludoGeneralRoad.put(12, new SafetyCell());
        ludoGeneralRoad.put(13, new FreeCell());
        ludoGeneralRoad.put(14, new FreeCell());
        ludoGeneralRoad.put(15, new SafetyCell());
        ludoGeneralRoad.put(16, new FreeCell());
        ludoGeneralRoad.put(17, new FreeCell());
        ludoGeneralRoad.put(18, new FreeCell());
        ludoGeneralRoad.put(19, new SafetyCell());
        ludoGeneralRoad.put(20, new FreeCell());
        ludoGeneralRoad.put(21, new FreeCell());
        ludoGeneralRoad.put(22, new FreeCell());
        ludoGeneralRoad.put(23, new FreeCell());
        ludoGeneralRoad.put(24, new SafetyCell());
        ludoGeneralRoad.put(25, new FreeCell());
        ludoGeneralRoad.put(26, new FreeCell());
        ludoGeneralRoad.put(27, new SafetyCell());
        ludoGeneralRoad.put(28, new FreeCell());
        ludoGeneralRoad.put(29, new FreeCell());
        ludoGeneralRoad.put(30, new FreeCell());
        ludoGeneralRoad.put(31, new SafetyCell());
        ludoGeneralRoad.put(32, new FreeCell());
        ludoGeneralRoad.put(33, new FreeCell());
        ludoGeneralRoad.put(34, new FreeCell());
        ludoGeneralRoad.put(35, new FreeCell());
        ludoGeneralRoad.put(36, new SafetyCell());
        ludoGeneralRoad.put(37, new FreeCell());
        ludoGeneralRoad.put(38, new FreeCell());
        ludoGeneralRoad.put(39, new SafetyCell());
        ludoGeneralRoad.put(40, new FreeCell());
        ludoGeneralRoad.put(41, new FreeCell());
        ludoGeneralRoad.put(42, new FreeCell());
        ludoGeneralRoad.put(43, new SafetyCell());
        ludoGeneralRoad.put(44, new FreeCell());
        ludoGeneralRoad.put(45, new FreeCell());
        ludoGeneralRoad.put(46, new FreeCell());
        ludoGeneralRoad.put(47, new FreeCell());
        ludoGeneralRoad.put(48, new GoalCell());
        ludoGeneralRoad.put(49, new GoalCell());
        ludoGeneralRoad.put(50, new GoalCell());
        ludoGeneralRoad.put(51, new GoalCell());
    }
}
