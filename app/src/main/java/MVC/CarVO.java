// 나중에 폴더 옮기고 package 다시설정!!!
package MVC;
/*
 * Author : 박성현
 * resent update : 11-15
 *
 * note
 * DAO의 필요가 있는지 아닌지 잘 몰라서 추가는 아직 X
 *
 */
public class CarVO {
    private String cname;
    private int maxTire;
    private int maxBreakpad;
    private int maxWiper;
    private int maxEngineOil;
    private int maxAirconditionerFilter;
    private int maxBattery;

    public String getCname() { return cname; }
    public int getMaxTire() { return maxTire; }
    public int getMaxBreakpad() { return maxBreakpad; }
    public int getMaxWiper() { return maxWiper; }
    public int getMaxEngineOil() { return maxEngineOil; }
    public int getMaxAirconditionerFilter() { return maxAirconditionerFilter; }
    public int getMaxBattery() { return maxBattery; }
}
